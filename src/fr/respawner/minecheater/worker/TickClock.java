package fr.respawner.minecheater.worker;

import java.util.Timer;
import java.util.TimerTask;

public final class TickClock {
    private final Timer clock;
    private final ClockReceiver receiver;

    public TickClock(ClockReceiver receiver) {
        this.clock = new Timer(true);
        this.receiver = receiver;
    }

    public void start() {
        this.clock.schedule(new TickTask(), 0, 50);
    }

    public void stop() {
        this.clock.cancel();
        this.clock.purge();
    }

    private class TickTask extends TimerTask {
        @Override
        public void run() {
            receiver.tick();
        }
    }

    public interface ClockReceiver {
        /**
         * Tell the receiver that a tick has passed.
         */
        void tick();
    }
}
