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
package fr.respawner.minecheater.structure.inventory;

import fr.respawner.minecheater.metadata.Slotdata;

public final class Slot {
    private byte windowID;
    private short slot;
    private Slotdata data;

    public Slot(byte windowID, short slot, Slotdata data) {
        this.windowID = windowID;
        this.slot = slot;
        this.data = data;
    }

    public byte getWindowID() {
        return this.windowID;
    }

    public short getSlot() {
        return this.slot;
    }

    public Slotdata getData() {
        return this.data;
    }

    @Override
    public String toString() {
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append("Window ID = ");
        builder.append(this.windowID);
        builder.append(" | Slot = ");
        builder.append(this.slot);
        builder.append(" | Content = ");
        builder.append(this.data);

        return builder.toString();
    }
}
