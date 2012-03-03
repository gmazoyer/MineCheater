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
        final Runtime runtime;
        final long maxMemory, totalMemory, freeMemory, usedMemory;

        runtime = Runtime.getRuntime();
        maxMemory = runtime.maxMemory();
        totalMemory = runtime.totalMemory();
        freeMemory = runtime.freeMemory();
        usedMemory = totalMemory - freeMemory;

        stdout.println("================================================================================");
        stdout.println("  __  __ _        ____ _                _");
        stdout.println(" |  \\/  (_)_ __  / ___| |__   ___  __ _| |_ ___ _ __");
        stdout.println(" | |\\/| | | '_ \\| |   | '_ \\ / _ \\/ _` | __/ _ \\ '__|");
        stdout.println(" | |  | | | | | | |___| | | |  __/ (_| | |_  __/ |");
        stdout.println(" |_|  |_|_|_| |_|\\____|_| |_|\\___|\\__,_|\\__\\___|_|");
        stdout.println();
        stdout.println("[Version " + Config.VERSION + "]");
        stdout.println();
        stdout.println("[System informations]");
        stdout.println("== Available processors: "
                + runtime.availableProcessors());
        stdout.println("== Max usable memory:    " + formatSize(maxMemory));
        stdout.println("== Total memory:         " + formatSize(totalMemory));
        stdout.println("== Used memory:          " + formatSize(usedMemory));
        stdout.println("== Free memory:          " + formatSize(freeMemory));
        stdout.println();
    }

    public static void showConnectionInfo() {
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

        config = new File("minecheat.properties");

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
                "protocol_version", "23"));
        SERVER_HOST = properties.getProperty("server_host", "127.0.0.1");
        SERVER_PORT = Integer.parseInt(properties.getProperty("server_port",
                "25565"));
        USERNAME = properties.getProperty("username", "MineCheater");
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
