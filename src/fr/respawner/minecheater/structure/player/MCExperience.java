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
package fr.respawner.minecheater.structure.player;

public final class MCExperience {
    private float experienceBar;
    private short level;
    private short total;

    public MCExperience(float experienceBar, short level, short total) {
        this.experienceBar = experienceBar;
        this.level = level;
        this.total = total;
    }

    public MCExperience() {
        this(0f, (short) 0, (short) 0);
    }

    public float getExperienceBar() {
        return this.experienceBar;
    }

    public short getLevel() {
        return this.level;
    }

    public short getTotal() {
        return this.total;
    }

    public void update(float experienceBar, short level, short total) {
        this.experienceBar = experienceBar;
        this.level = level;
        this.total = total;
    }

    @Override
    public String toString() {
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append("Experience bar = ");
        builder.append(this.experienceBar);
        builder.append(" | Level = ");
        builder.append(this.level);
        builder.append(" | Total = ");
        builder.append(this.total);

        return builder.toString();
    }
}
