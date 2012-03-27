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
package fr.respawner.minecheater;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import fr.respawner.minecheater.structure.MCPlayerListEntry;
import fr.respawner.minecheater.structure.entity.MCCharacter;
import fr.respawner.minecheater.structure.entity.MCMob;
import fr.respawner.minecheater.structure.entity.MCObject;
import fr.respawner.minecheater.structure.world.MCMapColumn;
import fr.respawner.minecheater.web.MCLogin;
import fr.respawner.minecheater.worker.PacketsHandler;
import fr.respawner.minecheater.worker.PingHandler;

public final class MinecraftClient extends Thread {
    private static final Logger log;
    private static final InputStream stdin;
    private static final PrintStream stdout;

    private String ip;
    private int port;
    private boolean running;
    private MCLogin login;

    static {
        log = Logger.getLogger(MinecraftClient.class);
        stdin = System.in;
        stdout = System.out;
    }

    public MinecraftClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
        this.running = true;
        this.login = new MCLogin();
    }

    public String getIP() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public boolean isRunning() {
        return this.running;
    }

    public void stopClient() {
        this.running = false;
    }

    @Override
    public void run() {
        final BufferedReader userInput;

        PingHandler ping;
        PacketsHandler handler;

        String command;
        String[] args;

        List<MCObject> objects;

        ping = null;
        handler = null;

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
                 * We have been disconnected (handler is not running anymore).
                 */
                if ((handler != null) && !handler.isRunning()) {
                    handler = null;
                }

                /*
                 * The command is only the first element of the array.
                 */
                args = command.split(" ", 2);

                switch (args[0].toLowerCase()) {
                case "connect":
                    /*
                     * Handle incoming packet.
                     */
                    if (handler != null) {
                        stdout.println("Already connected to the server.");
                    } else {
                        handler = new PacketsHandler(this);
                        handler.start();
                    }
                    break;

                case "disconnect":
                    if (handler == null) {
                        stdout.println("You need to connect to the server.");
                    } else {
                        if (handler.isRunning()) {
                            handler.stopHandler();
                        }

                        handler = null;
                    }
                    break;

                case "help":
                case "?":
                    stdout.println("Available commands:");
                    stdout.println("  * connect        - connect to the server and login");
                    stdout.println("  * disconnect     - disconnect from the server");
                    stdout.println("  * help|?         - print this help");
                    stdout.println("  * login          - login with an official account");
                    stdout.println("  * message <text> - send a message");
                    stdout.println("  * mobs           - show all the nearest mobs");
                    stdout.println("  * objects        - list all objects of the world");
                    stdout.println("  * online         - show who is online");
                    stdout.println("  * ping           - try to ping the server");
                    stdout.println("  * player         - show information about the player");
                    stdout.println("  * players         - show all the nearest players and NPC");
                    stdout.println("  * quit|exit      - stop this program");
                    stdout.println("  * respawn        - respawn the player");
                    stdout.println("  * time           - display the time of the world");
                    stdout.println("  * system         - show informations about the system");
                    break;

                case "login":
                    if (this.login.isLoggedIn()) {
                        stdout.println("You are already logged in.");
                    } else {
                        if (this.login.doLogin()) {
                            stdout.println("Login successful ("
                                    + login.getSessionID() + ").");
                        } else {
                            stdout.println("Login failed, reason = "
                                    + login.getError());
                        }
                    }
                    break;

                case "map":
                    stdout.println("Number of loaded columns: "
                            + handler.getWorld().getMap().getColumnsCount());
                    for (MCMapColumn column : handler.getWorld().getMap()
                            .getColumns()) {
                        stdout.println(column);
                    }
                    break;

                case "message":
                    if (handler == null) {
                        stdout.println("You need to connect to the server.");
                    } else if (args.length < 2) {
                        stdout.println("The 'message' command needs an argument.");
                    } else {
                        handler.sendPacket((byte) 0x03, args[1]);
                    }
                    break;

                case "mobs":
                    if (handler == null) {
                        stdout.println("You need to connect to the server.");
                    } else {
                        objects = new ArrayList<>();

                        for (MCObject object : handler.getWorld()
                                .getAllObjects()) {
                            if (object instanceof MCMob) {
                                objects.add(object);
                            }
                        }

                        for (MCObject object : objects) {
                            stdout.println(object);
                        }
                    }

                    break;

                case "move":
                    final double[] move;
                    final String[] numb;

                    if (handler == null) {
                        stdout.println("You need to connect to the server.");
                    } else {
                        move = new double[3];
                        numb = args[1].split(" ");

                        for (byte b = 0; b < move.length; b++) {
                            move[b] = Double.parseDouble(numb[b]);
                        }

                        handler.getWorld().getPlayer().getLocation()
                                .setOnGround(true);
                        handler.getWorld().getPlayer()
                                .move(move[0], move[1], move[2]);
                        handler.sendPacket((byte) 0x0D);
                    }

                    break;

                case "objects":
                    if (handler == null) {
                        stdout.println("You need to connect to the server.");
                    } else {
                        objects = handler.getWorld().getAllObjects();

                        for (MCObject object : objects) {
                            stdout.println(object);
                        }
                    }

                    break;

                case "online":
                    final List<MCPlayerListEntry> people;

                    if (handler == null) {
                        stdout.println("You need to connect to the server.");
                    } else {
                        people = handler.getWorld().getOnlinePeople();

                        for (MCPlayerListEntry entry : people) {
                            stdout.println(entry);
                        }
                    }

                    break;

                case "ping":
                    /*
                     * Just a simple handler that ping the server.
                     */
                    if (handler != null) {
                        stdout.println("Already connected to the server.");
                    } else {
                        ping = new PingHandler(this);
                        ping.start();

                        try {
                            /*
                             * Wait for the ping to terminate.
                             */
                            ping.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;

                case "player":
                    if (handler == null) {
                        stdout.println("You need to connect to the server.");
                    } else {
                        stdout.println(handler.getWorld().getPlayer());
                    }
                    break;

                case "players":
                    if (handler == null) {
                        stdout.println("You need to connect to the server.");
                    } else {
                        objects = new ArrayList<>();

                        for (MCObject object : handler.getWorld()
                                .getAllObjects()) {
                            if (object instanceof MCCharacter) {
                                objects.add(object);
                            }
                        }

                        for (MCObject object : objects) {
                            stdout.println(object);
                        }
                    }

                    break;

                case "quit":
                case "exit":
                    this.running = false;
                    break;

                case "respawn":
                    if (handler == null) {
                        stdout.println("You need to connect to the server.");
                    } else {
                        handler.sendPacket((byte) 0x09);
                    }
                    break;

                case "time":
                    if (handler == null) {
                        stdout.println("You need to connect to the server.");
                    } else {
                        stdout.println(handler.getWorld().getTime());
                    }
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
         * Stop the packets handler if it is still running.
         */
        if ((handler != null) && handler.isRunning()) {
            handler.stopHandler();

            try {
                /*
                 * Wait for the packet handler to stop.
                 */
                handler.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            userInput.close();
        } catch (IOException e) {
            log.error("User input already closed.");
        }

        log.info("Shutting down client worker.");
    }
}
