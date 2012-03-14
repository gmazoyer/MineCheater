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
import fr.respawner.minecheater.math.Rotation;
import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.worker.IHandler;

public final class PlayerLook extends Packet {
    private float yaw;
    private float pitch;
    private boolean onGround;

    public PlayerLook(IHandler handler) {
        super(handler, (byte) 0x0C);

        final Location location;
        final Rotation rotation;

        location = this.getWorld().getPlayer().getLocation();
        rotation = this.getWorld().getPlayer().getRotation();

        if (location != null) {
            this.onGround = location.isOnGround();
        }

        if (rotation != null) {
            this.yaw = rotation.getX();
            this.pitch = rotation.getY();
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
        this.writeFloat(this.yaw);
        this.writeFloat(this.pitch);
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

        builder.append("Look: yaw = ");
        builder.append(this.yaw);
        builder.append(", pitch = ");
        builder.append(this.pitch);
        builder.append(" | ");
        builder.append(this.onGround ? "Walking or Swimming"
                : "Jumping or Falling");

        return builder.toString();
    }
}
