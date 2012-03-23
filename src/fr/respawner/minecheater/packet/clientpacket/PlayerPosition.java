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
package fr.respawner.minecheater.packet.clientpacket;

import java.io.IOException;

import fr.respawner.minecheater.math.Location;
import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.worker.IHandler;

public final class PlayerPosition extends Packet {
    private double x;
    private double y;
    private double stance;
    private double z;
    private boolean onGround;

    public PlayerPosition(IHandler handler) {
        super(handler, PLAYER_POSITION);

        final Location location;

        location = this.getWorld().getPlayer().getLocation();

        if (location != null) {
            this.x = location.getPosition().getX();
            this.y = location.getPosition().getY();
            this.z = location.getPosition().getZ();
            this.stance = location.getStance();
            this.onGround = location.isOnGround();
        }
    }

    @Override
    public void read() throws IOException {
        /*
         * We don't receive this packet.
         */
    }

    @Override
    public void write() throws IOException {
        this.writeDouble(this.x);
        this.writeDouble(this.y);
        this.writeDouble(this.stance);
        this.writeDouble(this.z);
        this.writeBoolean(this.onGround);
    }

    @Override
    public void process() {
        /*
         * Nothing to do.
         */
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

        builder.append("Location: x = ");
        builder.append(this.x);
        builder.append(", y = ");
        builder.append(this.y);
        builder.append(", z = ");
        builder.append(this.z);
        builder.append(", stance = ");
        builder.append(this.stance);
        builder.append(" | ");
        builder.append(this.onGround ? "Walking or Swimming"
                : "Jumping or Falling");

        return builder.toString();
    }
}
