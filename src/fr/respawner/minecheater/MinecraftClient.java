package fr.respawner.minecheater;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import fr.respawner.minecheater.structure.MCPlayerListEntry;
import fr.respawner.minecheater.structure.entity.MCCharacter;
import fr.respawner.minecheater.structure.entity.MCMob;
import fr.respawner.minecheater.structure.entity.MCObject;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class MinecraftClient extends Thread {
    private static final Logger log;
    private static final InputStream stdin;
    private static final PrintStream stdout;

    private Socket socket;
    private boolean running;

    static {
        log = Logger.getLogger(MinecraftClient.class);
        stdin = System.in;
        stdout = System.out;
    }

    public MinecraftClient(String ip, int port) {
        InetAddress address;

        address = null;
        try {
            address = InetAddress.getByName(ip);
        } catch (UnknownHostException e) {
            log.warn("Can't find server at " + ip + ".");
        }

        try {
            this.socket = new Socket(address, port);
            this.socket.setTcpNoDelay(true);
        } catch (IOException e) {
            log.warn("Can't connect to server at " + ip + " with port " + port
                    + ".");
        }
        this.running = true;
    }

    public boolean isRunning() {
        return this.running;
    }

    public void stopClient() {
        this.running = false;
    }

    public Socket getSocket() {
        return this.socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public InputStream getNetworkInput() {
        try {
            return this.socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public OutputStream getNetworkOutput() {
        try {
            return this.socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void reopenSocket() {
        try {
            this.socket = new Socket(this.socket.getInetAddress(),
                    this.socket.getPort());
            this.socket.setTcpNoDelay(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeSocket() {
        try {
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        final PacketsHandler handler;
        final BufferedReader userInput;

        String command;
        String[] args;

        List<MCObject> objects;

        if (this.socket == null) {
            this.running = false;
            return;
        }

        /*
         * Handle incoming packet.
         */
        handler = new PacketsHandler(this);
        handler.start();

        /*
         * Handle user inputs.
         */
        userInput = new BufferedReader(new InputStreamReader(stdin));
        while (this.running) {
            try {
                command = userInput.readLine();

                /*
                 * No input?
                 */
                if (command == null) {
                    continue;
                }

                /*
                 * The command is only the first element of the array.
                 */
                args = command.split(" ", 2);

                switch (args[0]) {
                case "help":
                case "?":
                    stdout.println("Available commands:");
                    stdout.println("  * help|?         - print this help");
                    stdout.println("  * message <text> - send a message");
                    stdout.println("  * mobs           - show all the nearest mobs");
                    stdout.println("  * objects        - list all objects of the world");
                    stdout.println("  * online         - show who is online");
                    stdout.println("  * player         - show information about the player");
                    stdout.println("  * players         - show all the nearest players and NPC");
                    stdout.println("  * quit|exit      - stop this program");
                    stdout.println("  * respawn        - respawn the player");
                    stdout.println("  * time           - display the time of the world");
                    stdout.println("  * system         - show informations about the system");
                    break;

                case "message":
                    if (args.length < 2) {
                        stdout.println("The 'message' command needs an argument.");
                    } else {
                        handler.sendPacket((byte) 0x03, args[1]);
                    }
                    break;

                case "mobs":
                    objects = new ArrayList<>();

                    for (MCObject object : handler.getWorld().getAllObjects()) {
                        if (object instanceof MCMob) {
                            objects.add(object);
                        }
                    }

                    for (MCObject object : objects) {
                        stdout.println(object);
                    }

                    break;

                case "move":
                    double[] move = new double[3];
                    String[] numb = args[1].split(" ");

                    for (byte b = 0; b < move.length; b++) {
                        move[b] = Double.parseDouble(numb[b]);
                    }

                    handler.getWorld().getPlayer().getLocation()
                            .setOnGround(true);
                    handler.getWorld().getPlayer()
                            .move(move[0], move[1], move[2]);
                    handler.sendPacket((byte) 0x0D, false);
                    break;

                case "objects":
                    objects = handler.getWorld().getAllObjects();

                    for (MCObject object : objects) {
                        stdout.println(object);
                    }

                    break;

                case "online":
                    final List<MCPlayerListEntry> people;

                    people = handler.getWorld().getOnlinePeople();

                    for (MCPlayerListEntry entry : people) {
                        stdout.println(entry);
                    }

                    break;

                case "player":
                    stdout.println(handler.getWorld().getPlayer());
                    break;

                case "players":
                    objects = new ArrayList<>();

                    for (MCObject object : handler.getWorld().getAllObjects()) {
                        if (object instanceof MCCharacter) {
                            objects.add(object);
                        }
                    }

                    for (MCObject object : objects) {
                        stdout.println(object);
                    }

                    break;

                case "quit":
                case "exit":
                    if (!this.socket.isClosed()) {
                        handler.sendPacket((byte) 0xFF);
                    }
                    this.running = false;
                    break;

                case "respawn":
                    handler.sendPacket((byte) 0x09);
                    break;

                case "time":
                    stdout.println(handler.getWorld().getTime());
                    break;

                case "system":
                    Config.showSystemInfo();
                    break;

                default:
                    stdout.println("Unknown command '" + args[0] + "'.");
                    break;
                }
            } catch (IOException e) {
                log.error("Can't read user input.");
            }
        }

        /*
         * Stop the packets handler.
         */
        if (handler.isRunning()) {
            handler.stopHandler();
        }

        try {
            /*
             * Wait for the packet handler to stop.
             */
            handler.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            /*
             * End the connection with the server.
             */
            if (!this.socket.isClosed()) {
                this.socket.close();
            }
            userInput.close();
        } catch (IOException e) {
            log.error("Socket already closed.");
        }

        log.info("Shutting down client worker.");
    }
}
