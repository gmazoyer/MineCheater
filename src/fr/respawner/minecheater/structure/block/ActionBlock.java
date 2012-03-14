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
package fr.respawner.minecheater.structure.block;

public final class ActionBlock {
    private int x;
    private short y;
    private int z;
    private byte firstByte;
    private byte secondByte;

    public ActionBlock(int x, short y, int z, byte firstByte, byte secondByte) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.firstByte = firstByte;
        this.secondByte = secondByte;
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

    public byte getFirstByte() {
        return this.firstByte;
    }

    public byte getSecondByte() {
        return this.secondByte;
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
        builder.append(" | Byte 1 = ");
        builder.append(this.firstByte);
        builder.append(" | Byte 2 = ");
        builder.append(this.secondByte);

        return builder.toString();
    }
}
