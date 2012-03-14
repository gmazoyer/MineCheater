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
package fr.respawner.minecheater.math;

public final class Rotation {
    private float x;
    private float y;
    private float roll;

    public Rotation(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Rotation() {
        this(0, 0);
    }

    public float getX() {
        return this.x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return this.y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getRoll() {
        return this.roll;
    }

    public void setRoll(float roll) {
        this.roll = roll;
    }

    public void setRotationFromPacket(byte x, byte y) {
        this.x = (float) ((x * 2 * Math.PI) / 256);
        this.y = (float) ((y * 2 * Math.PI) / 256);
    }

    public void setRotation(float x, float y, float roll) {
        this.x = x;
        this.y = y;
        this.roll = roll;
    }

    public void setRotationFromPacket(byte x, byte y, byte roll) {
        this.x = (float) ((x * 2 * Math.PI) / 256);
        this.y = (float) ((y * 2 * Math.PI) / 256);
        this.roll = (float) ((roll * 2 * Math.PI) / 256);
    }

    @Override
    public String toString() {
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append("Rotation: x = ");
        builder.append(this.x);
        builder.append(", y = ");
        builder.append(this.y);
        builder.append(", roll = ");
        builder.append(this.roll);

        return builder.toString();
    }
}
