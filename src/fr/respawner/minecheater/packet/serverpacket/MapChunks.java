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
import fr.respawner.minecheater.packet.PacketIdentifier;
import fr.respawner.minecheater.structure.world.MCMap;
import fr.respawner.minecheater.structure.world.MCMapColumn;
import fr.respawner.minecheater.worker.IHandler;

public final class MapChunks extends Packet {
    private int x;
    private int z;
    private boolean groundUpContinuous;
    private short primaryBitMap;
    private short addBitMap;
    private int compressedSize;
    private int unused;
    private byte[] zlibData;

    public MapChunks(IHandler handler) {
        super(handler, PacketIdentifier.MAP_CHUNKS);
    }

    @Override
    public void read() throws IOException {
        this.x = this.readInt();
        this.z = this.readInt();
        this.groundUpContinuous = this.readBoolean();
        this.primaryBitMap = (short) this.readUnsignedShort();
        this.addBitMap = (short) this.readUnsignedShort();
        this.compressedSize = this.readInt();
        this.unused = this.readInt();
        this.zlibData = this.readUnsignedByteArray(this.compressedSize);
    }

    @Override
    public void write() throws IOException {
        /*
         * We don't write this packet.
         */
    }

    @Override
    public void process() {
        final MCMap map;
        MCMapColumn column;

        map = this.handler.getWorld().getMap();
        column = map.getColumnAt(this.x, this.z);

        if (column == null) {
            column = new MCMapColumn(this.x, this.z, this.groundUpContinuous,
                    this.primaryBitMap, this.addBitMap, this.zlibData);
            map.addColumn(column);
        }

        column.load();

        if (!this.handler.getWorld().getPlayer().getLocation().isOnGround()) {
            this.handler.getWorld().getPlayer().putOnGround();
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

        builder.append("Position: x = ");
        builder.append(this.x);
        builder.append(", z = ");
        builder.append(this.z);
        builder.append(" | Ground up continuous = ");
        builder.append(this.groundUpContinuous);
        builder.append(" | Primary bit map = ");
        builder.append(this.primaryBitMap);
        builder.append(" | Add bit map = ");
        builder.append(this.addBitMap);
        builder.append(" | Compressed size = ");
        builder.append(this.compressedSize);
        builder.append(" | Unused = ");
        builder.append(this.unused);
        builder.append(" | Data = { ");
        for (byte b : this.zlibData) {
            builder.append(b);
            builder.append(", ");
        }
        builder.replace(builder.length() - 2, builder.length(), " }");

        return builder.toString();
    }
}
