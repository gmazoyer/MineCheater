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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Properties;

public final class Config {
    private static final PrintStream stdout;

    public static final String FILE_SEPARATOR;
    public static final String LINE_SEPARATOR;
    public static final String STRING_DELIMITER;
    public static final String VERSION;

    public static int PROTOCOL_VERSION;
    public static int CLIENT_VERSION;
    public static boolean AUTHENTICATE;
    public static String SERVER_HOST;
    public static int SERVER_PORT;
    public static String USERNAME;
    public static String PASSWORD;

    private static File config;

    static {
        stdout = System.out;
        FILE_SEPARATOR = File.separator;
        LINE_SEPARATOR = System.lineSeparator();
        STRING_DELIMITER = new String(new char[] { 0xA7 });
        VERSION = "1.2.3";
    }

    private Config() {
        /*
         * No instantiation.
         */
    }

    private static String formatSize(long size) {
        final String formatted;

        if (size >= (1024 * 1024 * 1024)) {
            formatted = (size / (1024 * 1024 * 1024)) + " GiB";
        } else if (size >= (1024 * 1024)) {
            formatted = (size / (1024 * 1024)) + " MiB";
        } else if (size >= 1024) {
            formatted = (size / 1024) + " KiB";
        } else {
            formatted = size + " bytes";
        }

        return formatted;
    }

    public static void showStartupInfo() {
        stdout.println("================================================================================");
        stdout.println("  __  __ _             ____ _                _");
        stdout.println(" |  \\/  (_)_ __   ___ / ___| |__   ___  __ _| |_ ___ _ __");
        stdout.println(" | |\\/| | | '_ \\ / _ \\ |   | '_ \\ / _ \\/ _` | __/ _ \\ '__|");
        stdout.println(" | |  | | | | | |  __/ |___| | | |  __/ (_| | |_  __/ |");
        stdout.println(" |_|  |_|_|_| |_|\\___|\\____|_| |_|\\___|\\__,_|\\__\\___|_|");
        stdout.println();
        stdout.println("[Version " + Config.VERSION + "]");
        stdout.println();

    }

    public static void showSystemInfo() {
        final Runtime runtime;
        final long maxMemory, totalMemory, freeMemory, usedMemory;

        runtime = Runtime.getRuntime();
        maxMemory = runtime.maxMemory();
        totalMemory = runtime.totalMemory();
        freeMemory = runtime.freeMemory();
        usedMemory = totalMemory - freeMemory;

        stdout.println("[System informations]");
        stdout.println("== Available processors: "
                + runtime.availableProcessors());
        stdout.println("== Max usable memory:    " + formatSize(maxMemory));
        stdout.println("== Total memory:         " + formatSize(totalMemory));
        stdout.println("== Used memory:          " + formatSize(usedMemory));
        stdout.println("== Free memory:          " + formatSize(freeMemory));
    }

    public static void showConnectionInfo() {
        stdout.println();
        stdout.println("[Server informations]");
        stdout.println("== Hostname: " + Config.SERVER_HOST);
        stdout.println("== Port:     " + Config.SERVER_PORT);
        stdout.println("== Username: " + Config.USERNAME);
        stdout.println();
        stdout.println("================================================================================");
    }

    /*
     * Load all the properties.
     */
    public static void load() throws IOException {
        final Properties properties;
        final InputStream reader;

        config = new File("conf" + FILE_SEPARATOR + "minecheater.properties");

        /*
         * Create the configuration file if needed.
         */
        if (!config.exists()) {
            config.createNewFile();
        }

        properties = new Properties();
        reader = new FileInputStream(config);

        /*
         * Parse the properties file.
         */
        properties.load(reader);
        reader.close();

        PROTOCOL_VERSION = Integer.parseInt(properties.getProperty(
                "protocol_version", "29"));
        CLIENT_VERSION = Integer.parseInt(properties.getProperty(
                "client_version", "13"));
        AUTHENTICATE = Boolean.parseBoolean(properties.getProperty(
                "authenticate", "false"));
        SERVER_HOST = properties.getProperty("server_host", "127.0.0.1");
        SERVER_PORT = Integer.parseInt(properties.getProperty("server_port",
                "25565"));
        USERNAME = properties.getProperty("username", "MineCheater");
        PASSWORD = properties.getProperty("password", "useless");

        if (USERNAME.length() > 16) {
            USERNAME = USERNAME.substring(0, 16);
        }
    }

    /*
     * Save the properties.
     */
    public static void save() throws IOException {
        final FileWriter writer;

        writer = new FileWriter(config);

        writer.write("protocol_version = " + PROTOCOL_VERSION + "\n");
        writer.write("client_version   = " + CLIENT_VERSION + "\n");
        writer.write("authenticate     = " + AUTHENTICATE + "\n");
        writer.write("server_host      = " + SERVER_HOST + "\n");
        writer.write("server_port      = " + SERVER_PORT + "\n");
        writer.write("username         = " + USERNAME + "\n");
        writer.write("password         = " + PASSWORD + "\n");

        writer.close();
    }
}
