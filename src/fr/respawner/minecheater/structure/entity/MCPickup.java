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
package fr.respawner.minecheater.structure.entity;

import fr.respawner.minecheater.math.Rotation;
import fr.respawner.minecheater.structure.type.MCItemType;

public final class MCPickup extends MCEntity {
    private short itemID;
    private byte count;
    private short damage;

    public MCPickup(int entityID, short itemID, byte count, short damage,
            int x, int y, int z, byte rotation, byte pitch, byte roll) {
        super(entityID, x, y, z);

        this.rotation = new Rotation();
        this.rotation.setRotationFromPacket(rotation, pitch);

        this.itemID = itemID;
        this.count = count;
        this.damage = damage;
    }

    public final short getItemID() {
        return this.itemID;
    }

    public final byte getCount() {
        return this.count;
    }

    public final short getDamage() {
        return this.damage;
    }

    @Override
    public String toString() {
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append(super.toString());
        builder.append(" | Item ID = ");
        builder.append(MCItemType.itemForID(this.itemID));
        builder.append(" | Count = ");
        builder.append(this.count);

        return builder.toString();
    }
}
