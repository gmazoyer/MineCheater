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
