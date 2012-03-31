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

import java.io.IOException;

import org.apache.log4j.PropertyConfigurator;

import fr.respawner.minecheater.worker.UncaughtExceptionHandler;

public final class MineCheater {
    public static void main(String[] args) {
        final MinecraftClient worker;

        new UncaughtExceptionHandler();

        try {
            /*
             * Load configuration.
             */
            PropertyConfigurator.configure("conf" + Config.FILE_SEPARATOR
                    + "logging.properties");
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
