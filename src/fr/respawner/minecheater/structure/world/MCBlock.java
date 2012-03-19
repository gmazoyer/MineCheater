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

public final class MCBlock {
    private byte id;
    private byte metadata;
    private byte light;
    private byte skyLight;
    private byte add;

    public MCBlock(byte id) {
        this.id = id;
    }

    public MCBlock() {
        this((byte) 0);
    }

    public byte getID() {
        return this.id;
    }

    public void setID(byte id) {
        this.id = id;
    }

    public byte getMetadata() {
        return this.metadata;
    }

    public void setMetadata(byte metadata) {
        this.metadata = metadata;
    }

    public byte getLight() {
        return this.light;
    }

    public void setLight(byte light) {
        this.light = light;
    }

    public byte getSkyLight() {
        return this.skyLight;
    }

    public void setSkyLight(byte skyLight) {
        this.skyLight = skyLight;
    }

    public byte getAdd() {
        return this.add;
    }

    public void setAdd(byte add) {
        this.add = add;
    }

    public boolean isAir() {
        return (this.id == 0);
    }

    @Override
    public String toString() {
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append("Block: Type = ");
        builder.append(this.id);
        builder.append(" | Metadata = ");
        builder.append(this.metadata);
        builder.append(" | Light = ");
        builder.append(this.light);
        builder.append(" | Sky light = ");
        builder.append(this.skyLight);
        builder.append(" | Add = ");
        builder.append(this.add);

        return builder.toString();
    }
}
