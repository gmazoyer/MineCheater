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
