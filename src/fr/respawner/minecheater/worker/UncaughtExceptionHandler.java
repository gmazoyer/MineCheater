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
package fr.respawner.minecheater.worker;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;

import fr.respawner.minecheater.Config;

public final class UncaughtExceptionHandler implements
        Thread.UncaughtExceptionHandler {
    private static final Logger log;

    static {
        log = Logger.getLogger(UncaughtExceptionHandler.class);
    }

    /**
     * Register this class as the default uncaught exception handler.
     */
    public UncaughtExceptionHandler() {
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    private static final String getStackTrace(Throwable exception) {
        final StringWriter swriter;
        final PrintWriter pwriter;

        swriter = new StringWriter();
        pwriter = new PrintWriter(swriter, true);

        exception.printStackTrace(pwriter);
        pwriter.flush();
        swriter.flush();

        pwriter.close();
        try {
            swriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return swriter.toString();
    }

    @Override
    public void uncaughtException(Thread thread, Throwable exception) {
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append("UncaughtException in thread: " + thread.getName()
                + Config.LINE_SEPARATOR);
        builder.append("  Thread ID      = " + thread.getId()
                + Config.LINE_SEPARATOR);
        builder.append("  Thread prority = " + thread.getPriority()
                + Config.LINE_SEPARATOR);
        builder.append("Exception stacktrace:" + Config.LINE_SEPARATOR);
        builder.append(getStackTrace(exception));

        log.error(builder.toString());
    }
}
