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

public final class Explosion extends Packet {
    private double x;
    private double y;
    private double z;
    private byte[][] records;

    @SuppressWarnings("unused")
    private float unknown;

    public Explosion(IHandler handler) {
        super(handler, (byte) 0x3C);
    }

    @Override
    public void read() throws IOException {
        final int count;

        this.x = this.readDouble();
        this.y = this.readDouble();
        this.z = this.readDouble();
        this.unknown = this.readFloat();

        count = this.readInt();
        this.records = new byte[count][3];
        for (int i = 0; i < count; i++) {
            for (byte b = 0; b < 3; b++) {
                this.records[i][b] = this.readByte();
            }
        }
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

        builder.append("Position: x = ");
        builder.append(this.x);
        builder.append(", y = ");
        builder.append(this.y);
        builder.append(", z = ");
        builder.append(this.z);
        builder.append(" | Records = { ");
        for (int i = 0; i < this.records.length; i++) {
            builder.append("{ ");

            for (byte b = 0; b < 3; b++) {
                builder.append(this.records[i][b]);
                builder.append(", ");
            }

            builder.replace(builder.length() - 2, builder.length(), " }");
            builder.append(", ");
        }
        builder.replace(builder.length() - 2, builder.length(), " }");

        return builder.toString();
    }
}
