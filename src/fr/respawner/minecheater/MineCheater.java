package fr.respawner.minecheater;

import java.io.IOException;

import org.apache.log4j.PropertyConfigurator;

public final class MineCheater {
    public static void main(String[] args) {
        final Thread worker;

        try {
            /*
             * Load configuration.
             */
            PropertyConfigurator.configure("logging.properties");
            Config.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Config.showStartupInfo();
        Config.showConnectionInfo();

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
