package fr.respawner.minecheater;

import java.io.IOException;

import org.apache.log4j.PropertyConfigurator;

public final class MineCheater {
    public static void main(String[] args) {
        final MinecraftClient worker;

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
        Config.showSystemInfo();
        Config.showConnectionInfo();

        /*
         * Start the worker thread that handles packets and user inputs.
         */
        worker = new MinecraftClient(Config.SERVER_HOST, Config.SERVER_PORT);
        worker.start();

        /*
         * Setup the shutdown hook.
         */
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                worker.stopClient();
            }
        });

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
