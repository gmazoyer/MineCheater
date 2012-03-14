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
import fr.respawner.minecheater.structure.player.MCHealth;
import fr.respawner.minecheater.worker.IHandler;

public final class UpdateHealth extends Packet {
    private short health;
    private short food;
    private float foodSaturation;

    public UpdateHealth(IHandler handler) {
        super(handler, (byte) 0x08);
    }

    @Override
    public void read() throws IOException {
        this.health = this.readShort();
        this.food = this.readShort();
        this.foodSaturation = this.readFloat();
    }

    @Override
    public void write() throws IOException {
        /*
         * We don't write this packet.
         */
    }

    @Override
    public void process() {
        final MCHealth health;

        health = new MCHealth(this.health, this.food, this.foodSaturation);
        this.handler.println(health);
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
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append("Health = ");
        builder.append(this.health);
        if (this.health == 0) {
            builder.append(" (dead)");
        } else if (this.health == 20) {
            builder.append(" (full HP)");
        }
        builder.append(" | Food = ");
        builder.append(this.food);
        builder.append(" | Food saturation = ");
        builder.append(this.foodSaturation);

        return builder.toString();
    }
}
