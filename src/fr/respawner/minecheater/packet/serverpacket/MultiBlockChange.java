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
import fr.respawner.minecheater.worker.IHandler;

public final class MultiBlockChange extends Packet {
    private int chunkX;
    private int chunkZ;
    private short[] coordinates;
    private byte[] types;
    private byte[] metadatas;

    public MultiBlockChange(IHandler handler) {
        super(handler, (byte) 0x34);
    }

    @Override
    public void read() throws IOException {
        final short length;

        this.chunkX = this.readInt();
        this.chunkZ = this.readInt();

        length = this.readShort();

        this.coordinates = this.readShortArray(length);
        this.types = this.readByteArray(length);
        this.metadatas = this.readByteArray(length);
    }

    @Override
    public void write() throws IOException {
        /*
         * We don't write this packet.
         */
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

        builder.append("Chunk: x = ");
        builder.append(this.chunkX);
        builder.append(", z = ");
        builder.append(this.chunkZ);
        builder.append(" | Coordinates = { ");
        for (short coordinate : this.coordinates) {
            builder.append(coordinate);
            builder.append(", ");
        }
        builder.replace(builder.length() - 2, builder.length(), " }");
        builder.append(" | Types = { ");
        for (byte type : this.types) {
            builder.append(type);
            builder.append(", ");
        }
        builder.replace(builder.length() - 2, builder.length(), " }");
        builder.append(" | Metadatas = { ");
        for (byte metadata : this.metadatas) {
            builder.append(metadata);
            builder.append(", ");
        }
        builder.replace(builder.length() - 2, builder.length(), " }");

        return builder.toString();
    }
}
