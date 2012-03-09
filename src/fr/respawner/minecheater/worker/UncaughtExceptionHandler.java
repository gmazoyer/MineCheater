package fr.respawner.minecheater.worker;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;

import fr.respawner.minecheater.packet.Packet;

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
                + Packet.LINE_SEPARATOR);
        builder.append("  Thread ID      = " + thread.getId()
                + Packet.LINE_SEPARATOR);
        builder.append("  Thread prority = " + thread.getPriority()
                + Packet.LINE_SEPARATOR);
        builder.append("Exception stacktrace:" + Packet.LINE_SEPARATOR);
        builder.append(getStackTrace(exception));

        log.error(builder.toString());
    }
}
