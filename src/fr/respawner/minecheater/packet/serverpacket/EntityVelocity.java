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

import fr.respawner.minecheater.math.Vector3D;
import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.entity.MCEntity;
import fr.respawner.minecheater.worker.IHandler;

public final class EntityVelocity extends Packet {
    private int entityID;
    private short velocityX;
    private short velocityY;
    private short velocityZ;

    public EntityVelocity(IHandler handler) {
        super(handler, (byte) 0x1C);
    }

    @Override
    public void read() throws IOException {
        this.entityID = this.readInt();
        this.velocityX = this.readShort();
        this.velocityY = this.readShort();
        this.velocityZ = this.readShort();
    }

    @Override
    public void write() throws IOException {
        /*
         * We don't write this packet.
         */
    }

    @Override
    public void process() {
        final MCEntity entity;
        Vector3D velocity;

        /*
         * Find the entity to set the velocity.
         */
        entity = (MCEntity) this.getWorld().findObjectByID(this.entityID);

        if (entity != null) {
            velocity = entity.getVelocity();

            if (velocity == null) {
                velocity = new Vector3D(this.velocityX, this.velocityY,
                        this.velocityZ);
                entity.setVelocity(velocity);
            } else {
                entity.getVelocity().setX(this.velocityX);
                entity.getVelocity().setY(this.velocityY);
                entity.getVelocity().setZ(this.velocityZ);
            }
        }
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

        builder.append("Entity ID = ");
        builder.append(this.entityID);
        builder.append(" | Velocity X = ");
        builder.append(this.velocityX);
        builder.append(" | Velocity Y = ");
        builder.append(this.velocityY);
        builder.append(" | Velocity Z = ");
        builder.append(this.velocityZ);

        return builder.toString();
    }
}
