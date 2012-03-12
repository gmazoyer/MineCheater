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
    public static final String VERSION;

    public static int PROTOCOL_VERSION;
    public static String SERVER_HOST;
    public static int SERVER_PORT;
    public static String USERNAME;

    private static File config;

    static {
        stdout = System.out;
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

        config = new File("minecheater.properties");

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
                "protocol_version", "28"));
        SERVER_HOST = properties.getProperty("server_host", "127.0.0.1");
        SERVER_PORT = Integer.parseInt(properties.getProperty("server_port",
                "25565"));
        USERNAME = properties.getProperty("username", "MineCheater");
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
        writer.write("server_host      = " + SERVER_HOST + "\n");
        writer.write("server_port      = " + SERVER_PORT + "\n");
        writer.write("username         = " + USERNAME + "\n");

        writer.close();
    }
}
