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

import fr.respawner.minecheater.metadata.Metadata;
import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.packet.PacketIdentifier;
import fr.respawner.minecheater.structure.entity.MCMob;
import fr.respawner.minecheater.structure.type.MCMobType;
import fr.respawner.minecheater.worker.IHandler;

public final class SpawnMob extends Packet {
    private int entityID;
    private byte type;
    private int x;
    private int y;
    private int z;
    private byte yaw;
    private byte pitch;
    private byte headYaw;
    private Metadata metadata;

    public SpawnMob(IHandler handler) {
        super(handler, PacketIdentifier.SPAWN_MOB);
    }

    @Override
    public void read() throws IOException {
        this.entityID = this.readInt();
        this.type = this.readByte();
        this.x = this.readInt();
        this.y = this.readInt();
        this.z = this.readInt();
        this.yaw = this.readByte();
        this.pitch = this.readByte();
        this.headYaw = this.readByte();
        this.metadata = new Metadata(this.handler);
        this.metadata.parse();
    }

    @Override
    public void write() throws IOException {
        /*
         * We don't write this packet.
         */
    }

    @Override
    public void process() {
        final MCMob mob;

        mob = new MCMob(this.entityID, this.type, this.x, this.y, this.z,
                this.yaw, this.pitch, this.headYaw, this.metadata);
        this.getWorld().addObject(mob);
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
        builder.append(" | Type = ");
        builder.append(MCMobType.mobForID(this.type));
        builder.append(" | Location: x = ");
        builder.append(this.x);
        builder.append(", y = ");
        builder.append(this.y);
        builder.append(", z = ");
        builder.append(this.z);
        builder.append(", yaw = ");
        builder.append(this.yaw);
        builder.append(", pitch = ");
        builder.append(this.pitch);
        builder.append(", head yaw = ");
        builder.append(this.headYaw);
        builder.append(" | Metadata = ");
        builder.append(this.metadata);

        return builder.toString();
    }
}
