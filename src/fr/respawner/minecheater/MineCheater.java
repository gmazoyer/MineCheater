package fr.respawner.minecheater;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public final class MineCheater {
    private static final PrintStream stdout;

    static {
        stdout = System.out;
    }

    public static void main(String[] args) {
        final File logs;
        final Thread worker;

        try {
            Config.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
         * TODO: Rework all the logging to use the Java Logging API.
         */
        if (Config.DEBUG && Config.DEBUG_TYPE.equals("file")) {
            try {
                logs = new File(Config.LOGS_FILE);

                if (!logs.exists()) {
                    logs.createNewFile();
                }

                System.setOut(new PrintStream(new FileOutputStream(logs)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        stdout.println("================================================================================");
        stdout.println("==== Connecting to " + Config.SERVER_HOST + " on port "
                + Config.SERVER_PORT + ".");
        stdout.println("==== Using name: " + Config.USERNAME);
        stdout.println("================================================================================");

        /*
         * Start the worker thread that handles packets and user inputs.
         */
        worker = new Thread(new ClientWorker(Config.SERVER_HOST,
                Config.SERVER_PORT));
        worker.start();

        try {
            /*
             * Wait for the thread to terminate.
             */
            worker.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                Config.save();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                System.exit(0);
            }
        }
    }
}
