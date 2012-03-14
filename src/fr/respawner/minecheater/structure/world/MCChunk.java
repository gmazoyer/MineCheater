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
package fr.respawner.minecheater.structure.world;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.InflaterInputStream;

public final class MCChunk {
    private int x;
    private short y;
    private int z;
    private byte sizeX;
    private byte sizeY;
    private byte sizeZ;
    private byte[] data;

    public MCChunk(int x, short y, int z, byte sizeX, byte sizeY, byte sizeZ,
            byte[] data) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.sizeZ = sizeZ;
        this.data = this.inflateData(data);
    }

    private byte[] inflateData(byte[] data) {
        final byte[] inflated;
        final List<Byte> uncompressed;
        final InflaterInputStream inflater;
        int read;

        /*
         * Initialize the inflater.
         */
        uncompressed = new ArrayList<>();
        inflater = new InflaterInputStream(new ByteArrayInputStream(data));

        try {
            /*
             * Inflate the data.
             */
            while ((read = inflater.read()) != -1) {
                uncompressed.add((byte) read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inflater.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /*
         * Put them in an array.
         */
        inflated = new byte[uncompressed.size()];
        for (int i = 0; i < inflated.length; i++) {
            inflated[i] = uncompressed.get(i);
        }

        return inflated;
    }

    public int getX() {
        return this.x;
    }

    public short getY() {
        return this.y;
    }

    public int getZ() {
        return this.z;
    }

    public byte getSizeX() {
        return this.sizeX;
    }

    public byte getSizeY() {
        return this.sizeY;
    }

    public byte getSizeZ() {
        return this.sizeZ;
    }

    public byte[] getData() {
        return this.data;
    }

    @Override
    public String toString() {
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append("Position: x = ");
        builder.append(this.x);
        builder.append(", y = ");
        builder.append(this.y);
        builder.append(", z = ");
        builder.append(this.z);
        builder.append(" | Size: x = ");
        builder.append(this.sizeX);
        builder.append(", y = ");
        builder.append(this.sizeY);
        builder.append(", z = ");
        builder.append(this.sizeZ);
        builder.append(" | Data = { ");
        for (byte b : this.data) {
            builder.append(b);
            builder.append(", ");
        }
        builder.replace(builder.length() - 2, builder.length(), " }");

        return builder.toString();
    }
}
