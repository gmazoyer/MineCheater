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
package fr.respawner.minecheater.packet.common;

import java.io.IOException;

import fr.respawner.minecheater.math.Location;
import fr.respawner.minecheater.math.Rotation;
import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.packet.PacketIdentifier;
import fr.respawner.minecheater.worker.IHandler;

public final class PlayerPositionAndLook extends Packet {
    private double x;
    private double y;
    private double stance;
    private double z;
    private float yaw;
    private float pitch;
    private boolean onGround;

    public PlayerPositionAndLook(IHandler handler) {
        super(handler, PacketIdentifier.PLAYER_POSITION_AND_LOOK);

        final Location location;
        final Rotation rotation;

        location = this.getWorld().getPlayer().getLocation();
        rotation = this.getWorld().getPlayer().getRotation();

        if (location != null) {
            this.x = location.getPosition().getX();
            this.y = location.getPosition().getY();
            this.z = location.getPosition().getZ();
            this.stance = location.getStance();
            this.onGround = location.isOnGround();
        }

        if (rotation != null) {
            this.yaw = rotation.getX();
            this.pitch = rotation.getY();
        }
    }

    @Override
    public void read() throws IOException {
        this.x = this.readDouble();
        this.stance = this.readDouble();
        this.y = this.readDouble();
        this.z = this.readDouble();
        this.yaw = this.readFloat();
        this.pitch = this.readFloat();
        this.onGround = this.readBoolean();
    }

    @Override
    public void write() throws IOException {
        /*
         * From Client to Server, 'stance' is sent after 'y'.
         */
        this.writeDouble(this.x);
        this.writeDouble(this.y);
        this.writeDouble(this.stance);
        this.writeDouble(this.z);
        this.writeFloat(this.yaw);
        this.writeFloat(this.pitch);
        this.writeBoolean(this.onGround);
    }

    @Override
    public void process() {
        Location location;
        Rotation rotation;

        location = this.getWorld().getPlayer().getLocation();
        rotation = this.getWorld().getPlayer().getRotation();

        if (location != null) {
            location.setPosition(x, y, z);
            location.setOnGround(this.onGround);
        } else {
            location = new Location(this.x, this.y, this.z);
            location.setStance(this.stance);
            location.setOnGround(this.onGround);
            this.getWorld().getPlayer().setLocation(location);
        }

        if (rotation != null) {
            rotation.setX(this.yaw);
            rotation.setY(this.pitch);
        } else {
            rotation = new Rotation(this.yaw, this.pitch);
            this.getWorld().getPlayer().setRotation(rotation);
        }

        if (!this.getWorld().isLoggedIn()) {
            this.getWorld().setLoggedIn(true);
        }
    }

    @Override
    public Packet response() {
        /*
         * We just send the exact same packet.
         */
        return this;
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
        builder.append(", yaw = ");
        builder.append(this.yaw);
        builder.append(", pitch = ");
        builder.append(this.pitch);
        builder.append(" | ");
        builder.append(this.onGround ? "Walking or Swimming"
                : "Jumping or Falling");

        return builder.toString();
    }
}
