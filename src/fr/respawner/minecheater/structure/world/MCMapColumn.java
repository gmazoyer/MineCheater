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

public final class MCMapColumn {
    private int x;
    private int z;
    private MCChunk[] chunks;
    private byte[] biomes;

    public MCMapColumn(int x, int z) {
        this.x = x;
        this.z = z;
        this.chunks = new MCChunk[16];
        this.biomes = new byte[256];
    }

    public int getX() {
        return this.x;
    }

    public int getZ() {
        return this.z;
    }

    public MCChunk[] getChunks() {
        return this.chunks;
    }

    public byte[] getBiomes() {
        return this.biomes;
    }

    public boolean isAt(int x, int z) {
        return ((this.x == x) && (this.z == z));
    }

    @Override
    public String toString() {
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append("MapColumn: x = ");
        builder.append(this.x);
        builder.append(", z = ");
        builder.append(this.z);
        builder.append(" | Chunks: ");
        for (MCChunk chunk : this.chunks) {
            builder.append(chunk);
        }

        return builder.toString();
    }
}
