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
import fr.respawner.minecheater.structure.inventory.MCEquipment;
import fr.respawner.minecheater.structure.type.MCItemType;

/**
 * This is the class that represents a named entity. It could be a player - but
 * not our player - or a NPC.
 * 
 * @author Guillaume Mazoyer
 */
public final class MCCharacter extends MCEntity {
    private String name;
    private short item;
    private MCEquipment equipment;
    private MCEffect effect;
    private boolean useBed;

    public MCCharacter(int entityID, String name, int x, int y, int z,
            byte rotation, byte pitch, short item) {
        super(entityID, x, y, z);

        this.rotation = new Rotation();
        this.rotation.setRotationFromPacket(rotation, pitch);

        this.name = name;
        this.item = item;
    }

    public final String getName() {
        return this.name;
    }

    public final short getItem() {
        return this.item;
    }

    public final MCEquipment getEquipment() {
        return this.equipment;
    }

    public final void setEquipment(MCEquipment equipment) {
        this.equipment = equipment;
    }

    public MCEffect getEffect() {
        return this.effect;
    }

    public void setEffect(MCEffect effect) {
        this.effect = effect;
    }

    public boolean isUsingBed() {
        return this.useBed;
    }

    public void setUseBed(boolean useBed) {
        this.useBed = useBed;
    }

    @Override
    public String toString() {
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append(super.toString());
        builder.append(" | Name = ");
        builder.append(this.name);
        builder.append(" | Item = ");
        builder.append(this.item == 0 ? "Nothing" : MCItemType
                .itemForID(this.item));
        builder.append(" | ");
        builder.append(this.equipment);
        builder.append(" | ");
        builder.append(this.effect);
        builder.append(" | In bed = ");
        builder.append(this.useBed);

        return builder.toString();
    }
}
