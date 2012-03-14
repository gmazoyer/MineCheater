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

import fr.respawner.minecheater.structure.type.MCItemType;

public final class MCEquipment {
    private short slot;
    private short itemID;
    private short damage;

    public MCEquipment(short slot, short itemID, short damage) {
        this.slot = slot;
        this.itemID = itemID;
        this.damage = damage;
    }

    public short getSlot() {
        return this.slot;
    }

    public short getItemID() {
        return this.itemID;
    }

    public short getDamage() {
        return this.damage;
    }

    @Override
    public String toString() {
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append("Slot: ");
        builder.append(this.slot == 0 ? "held" : ((this.slot) >= 1)
                && (this.slot <= 4) ? "armor" : this.slot);
        builder.append(" | Item ID = ");
        builder.append(this.itemID == -1 ? "no item" : MCItemType
                .itemForID(this.itemID));
        builder.append(" | Damage = ");
        builder.append(this.damage);

        return builder.toString();
    }
}
