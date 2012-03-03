package fr.respawner.minecheater.structure.entity;

public final class MCPickup extends MCEntity {
    private short itemID;
    private byte count;
    private short damage;

    public MCPickup(int entityID, short itemID, byte count, short damage,
            int x, int y, int z, byte rotation, byte pitch, byte roll) {
        super(entityID, x, y, z);

        this.location.setPitch(pitch);

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

        builder.append("Entity ID = ");
        builder.append(this.getEntityID());
        builder.append(" | Item ID = ");
        builder.append(MCItemType.itemForID(this.itemID));
        builder.append(" | Count = ");
        builder.append(this.count);
        builder.append(" |");
        builder.append(this.getLocation());

        return builder.toString();
    }
}
