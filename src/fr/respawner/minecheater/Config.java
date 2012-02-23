package fr.respawner.minecheater;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class Config {
	public static boolean DEBUG;
	public static String DEBUG_TYPE;
	public static String LOGS_FILE;

	public static String SERVER_HOST;
	public static int SERVER_PORT;
	public static int PROTOCOL_VERSION;

	public static String USERNAME;

	private static File config;

	private Config() {
		/*
		 * No instantiation.
		 */
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

		DEBUG = Boolean.parseBoolean(properties.getProperty("debug", "false"));
		DEBUG_TYPE = properties.getProperty("debug_type", "console");
		LOGS_FILE = properties.getProperty("logs_file", "logs.txt");

		SERVER_HOST = properties.getProperty("server_host", "127.0.0.1");
		SERVER_PORT = Integer.parseInt(properties.getProperty("server_port",
				"25565"));
		PROTOCOL_VERSION = Integer.parseInt(properties.getProperty(
				"protocol_version", "23"));

		USERNAME = properties.getProperty("username", "MineCheater");
	}

	/*
	 * Save the properties.
	 */
	public static void save() throws IOException {
		final FileWriter writer;

		writer = new FileWriter(config);

		writer.write("debug            = " + DEBUG + "\n");
		writer.write("debug_type       = " + DEBUG_TYPE + "\n");
		writer.write("logs_file        = " + LOGS_FILE + "\n");

		writer.write("server_host      = " + SERVER_HOST + "\n");
		writer.write("server_port      = " + SERVER_PORT + "\n");
		writer.write("protocol_version = " + PROTOCOL_VERSION + "\n");

		writer.write("username         = " + USERNAME + "\n");

		writer.close();
	}
}
