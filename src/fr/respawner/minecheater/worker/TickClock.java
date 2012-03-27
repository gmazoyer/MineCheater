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

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public final class TickClock {
    private final Timer clock;
    private final List<ClockReceiver> receivers;

    private long ticks;

    public TickClock() {
        this.clock = new Timer(true);
        this.receivers = new ArrayList<>();
        this.ticks = 0;
    }

    public void addReceiver(ClockReceiver receiver) {
        this.receivers.add(receiver);
    }

    public void removeReceiver(ClockReceiver receiver) {
        this.receivers.remove(receiver);
    }

    public long getTicks() {
        return this.ticks;
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
            ticks++;

            for (ClockReceiver receiver : receivers) {
                receiver.tick();
            }
        }
    }

    public interface ClockReceiver {
        /**
         * Tell the receiver that a tick has passed.
         */
        void tick();
    }
}
