/*
 * Copyright (c) 2012 Guillaume Mazoyer
 * 
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
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
import fr.respawner.minecheater.packet.Packet;
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
        final byte packetID;

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

            return;
        }

        try {
            /*
             * Try to open the connection with the server.
             */
            this.socket = new Socket(address, port);
            this.socket.setTcpNoDelay(true);
        } catch (IOException e) {
            log.warn("Can't connect to server at " + ip + " on port " + port
                    + ".");

            return;
        }

        try {
            /*
             * Open the IO channels to be able to communicate.
             */
            this.in = new DataInputStream(this.socket.getInputStream());
            this.out = new DataOutputStream(this.socket.getOutputStream());
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
            if (packetID != Packet.DISCONNECT_KICK) {
                throw new IllegalStateException(
                        "Wrong packet received, expected 0xFF");
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
                this.socket.shutdownInput();
                this.socket.shutdownOutput();

                this.in.close();
                this.out.close();

                this.socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
