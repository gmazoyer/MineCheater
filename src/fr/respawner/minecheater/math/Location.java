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

import fr.respawner.minecheater.structure.world.MCMapColumn;

public final class Location {
    private VectorDouble position;
    private double stance;
    private boolean onGround;

    public Location(double x, double y, double z) {
        this.position = new VectorDouble(x, y, z);
        this.onGround = true;
    }

    public Location(int x, int y, int z) {
        this((x / 32.0), (y / 32.0), (z / 32.0));
    }

    public Location(Vector vector) {
        this(vector.getX(), vector.getY(), vector.getZ());
    }

    public VectorDouble getPosition() {
        return this.position;
    }

    public void setPosition(VectorDouble position) {
        this.position = position;
    }

    public double getStance() {
        return this.stance;
    }

    public void setStance(double stance) {
        this.stance = stance;
    }

    public boolean isOnGround() {
        return this.onGround;
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }

    public boolean isInside(MCMapColumn column) {
        final int columnX, columnZ;
        final int playerX, playerZ;

        /*
         * Get the block coordinates.
         */
        columnX = column.getX() * 16;
        columnZ = column.getZ() * 16;

        /*
         * Put player coordinates into integer so we skip the figures after the
         * comma after converting them into blocks.
         */
        playerX = (int) (this.position.getX() / 16);
        playerZ = (int) (this.position.getZ() / 16);

        return ((columnX == playerX) && (columnZ == playerZ));
    }

    public void setPosition(double x, double y, double z) {
        this.position.setX(x);
        this.position.setY(y);
        this.position.setZ(z);
        this.stance = 1 + y;
    }

    public Vector toVector() {
        return new Vector((int) Math.floor(this.position.getX()),
                (int) Math.floor(this.position.getY()),
                (int) Math.floor(this.position.getZ()));
    }

    @Override
    public String toString() {
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append("Location: ");
        builder.append(this.position);
        builder.append(" | Stance = ");
        builder.append(this.stance);
        builder.append(" | ");
        builder.append(this.onGround ? "Walking or Swimming"
                : "Jumping or Falling");

        return builder.toString();
    }
}
