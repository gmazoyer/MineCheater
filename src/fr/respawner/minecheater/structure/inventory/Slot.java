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

import java.util.List;

import fr.respawner.minecheater.metadata.Pair;
import fr.respawner.minecheater.structure.type.MCItemType;

public final class Slot {
    private byte windowID;
    private short slotID;
    private short itemID;
    private byte count;
    private short metadata;
    private List<Pair<Short, Short>> enchantments;

    public Slot(byte windowID, short slotID, short itemID, byte count,
            short metadata, List<Pair<Short, Short>> enchantments) {
        this.windowID = windowID;
        this.slotID = slotID;
        this.itemID = itemID;
        this.count = count;
        this.metadata = metadata;
        this.enchantments = enchantments;
    }

    public byte getWindowID() {
        return this.windowID;
    }

    public short getSlotID() {
        return this.slotID;
    }

    public short getItemID() {
        return this.itemID;
    }

    public void setItemID(short itemID) {
        this.itemID = itemID;
    }

    public byte getCount() {
        return this.count;
    }

    public void setCount(byte count) {
        this.count = count;
    }

    public short getMetadata() {
        return this.metadata;
    }

    public void setMetadata(short metadata) {
        this.metadata = metadata;
    }

    public List<Pair<Short, Short>> getEnchantments() {
        return this.enchantments;
    }

    public boolean isEmpty() {
        return (this.itemID == -1);
    }

    public void update(Slot slot) {
        this.itemID = slot.itemID;
        this.count = slot.count;
        this.metadata = slot.metadata;
        this.enchantments = slot.enchantments;
    }

    @Override
    public String toString() {
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append("Window ID = ");
        builder.append(this.windowID);
        builder.append(" | Slot ID = ");
        builder.append(this.slotID);

        if (this.isEmpty()) {
            builder.append(" | Empty");
        } else {
            builder.append(" | Item ID = ");
            builder.append(MCItemType.itemForID(this.itemID));
            builder.append(" | Count = ");
            builder.append(this.count);
            builder.append(" | Metadata = ");
            builder.append(this.metadata);

            if (this.enchantments != null) {
                builder.append(" | Enchantments = { ");
                for (Pair<Short, Short> enchantment : this.enchantments) {
                    builder.append(enchantment);
                    builder.append(", ");
                }
                builder.replace(builder.length() - 2, builder.length(), " }");
            }
        }

        return builder.toString();
    }
}
