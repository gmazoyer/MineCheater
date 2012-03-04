package fr.respawner.minecheater.structure.inventory;

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

        builder.append("Slot = ");
        builder.append(this.slot == 0 ? "held" : ((this.slot) >= 1)
                && (this.slot <= 4) ? "armor" : this.slot);
        builder.append(" | Item ID = ");
        builder.append(this.itemID == -1 ? "no item" : this.itemID);
        builder.append(" | Damage = ");
        builder.append(this.damage);

        return builder.toString();
    }
}
