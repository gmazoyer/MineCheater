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
package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.world.MCTime;
import fr.respawner.minecheater.worker.IHandler;

public final class TimeUpdate extends Packet {
    private long time;

    public TimeUpdate(IHandler handler) {
        super(handler, TIME_UPDATE);
    }

    @Override
    public void read() throws IOException {
        this.time = this.readLong() % 24000;
    }

    @Override
    public void write() throws IOException {
        /*
         * We don't write this packet.
         */
    }

    @Override
    public void process() {
        final MCTime time;

        time = new MCTime(this.time);
        this.getWorld().setTime(time);
    }

    @Override
    public Packet response() {
        /*
         * We don't send a response to this packet.
         */
        return null;
    }

    @Override
    public String getDataAsString() {
        final String description;

        if (this.time == 0) {
            description = "Sunrise";
        } else if (this.time == 6000) {
            description = "Noon";
        } else if (this.time == 12000) {
            description = "Sunset";
        } else if (this.time == 18000) {
            description = "Midnight";
        } else if ((this.time > 0) && (this.time < 6000)) {
            description = "Morning";
        } else if ((this.time > 6000) && (this.time < 12000)) {
            description = "Afternoon";
        } else if (this.time > 12000) {
            description = "Night";
        } else {
            description = "What time is it (" + this.time + ")?";
        }

        return description;
    }
}
