package fr.respawner.minecheater.structure.entity;

import fr.respawner.minecheater.structure.inventory.MCEquipment;

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

    public MCCharacter(int entityID, String name, int x, int y, int z,
            byte rotation, byte pitch, short item) {
        super(entityID, x, y, z);

        this.location.setPitch(pitch);

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
        return effect;
    }

    public void setEffect(MCEffect effect) {
        this.effect = effect;
    }

    @Override
    public String toString() {
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append(super.toString());
        builder.append(" | Name = ");
        builder.append(this.name);
        builder.append(" | Item = ");
        builder.append(this.item == 0 ? "Nothing" : this.item);
        builder.append(" | Equipment = ");
        builder.append(this.equipment);
        builder.append(" | Effect = ");
        builder.append(this.effect);

        return builder.toString();
    }
}
