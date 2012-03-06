package fr.respawner.minecheater.worker;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;

import fr.respawner.minecheater.MinecraftClient;
import fr.respawner.minecheater.World;
import fr.respawner.minecheater.packet.clientpacket.ServerListPing;
import fr.respawner.minecheater.packet.common.DisconnectKick;

public final class PingHandler extends Thread implements IHandler {
    private static final Logger log;
    private static final PrintStream stdout;

    private MinecraftClient client;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    static {
        log = Logger.getLogger(PacketsHandler.class);
        stdout = System.out;
    }

    public PingHandler(MinecraftClient client) {
        this.client = client;
    }

    @Override
    public void println(Object... messages) {
        if (messages.length == 0) {
            stdout.println();
        } else {
            for (Object message : messages) {
                stdout.println(message);
            }
        }
    }

    @Override
    public DataInputStream getInput() {
        return this.in;
    }

    @Override
    public DataOutputStream getOutput() {
        return this.out;
    }

    @Override
    public World getWorld() {
        return null;
    }

    @Override
    public void stopHandler() {
    }

    @Override
    public void run() {
        final String ip;
        final int port;
        final ServerListPing ping;
        final DisconnectKick pong;

        byte packetID;
        InetAddress address;

        ip = this.client.getIP();
        port = this.client.getPort();
        address = null;

        try {
            /*
             * Translate the given string to a usable address.
             */
            address = InetAddress.getByName(ip);
        } catch (UnknownHostException e) {
            log.warn("Can't find server at " + ip + ".");
        }

        try {
            /*
             * Try to open the connection with the server.
             */
            this.socket = new Socket(address, port);
            this.socket.setTcpNoDelay(true);
        } catch (IOException e) {
            log.warn("Can't connect to server at " + ip + " with port " + port
                    + ".");
        }

        try {
            /*
             * Open the IO channels to be able to communicate.
             */
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            /*
             * First we send a ping.
             */
            ping = new ServerListPing(this);
            ping.write();

            /*
             * Look for a packet ID.
             */
            packetID = (byte) this.in.read();
            if (packetID != (byte) 0xFF) {
                throw new IOException();
            }

            /*
             * And we get the pong.
             */
            pong = new DisconnectKick(this);
            pong.read();
            pong.process();
        } catch (IOException e) {
            log.error("Can't ping the server.");
        } finally {
            /*
             * Since the connection is closed, release our objects.
             */
            try {
                this.in.close();
                this.out.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
