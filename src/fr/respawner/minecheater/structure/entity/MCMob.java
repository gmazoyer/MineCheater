package fr.respawner.minecheater.structure.entity;

import fr.respawner.minecheater.math.Rotation;
import fr.respawner.minecheater.metadata.Metadata;
import fr.respawner.minecheater.structure.type.MCMobType;

/**
 * This is the class that represents a mob.
 * 
 * @author Guillaume Mazoyer
 */
public final class MCMob extends MCEntity {
    private byte type;

    public MCMob(int entityID, byte type, int x, int y, int z, byte yaw,
            byte pitch, byte headYaw, Metadata metadata) {
        super(entityID, x, y, z);

        this.type = type;

        this.rotation = new Rotation();
        this.rotation.setRotationFromPacket(yaw, pitch);

        this.setHeadYaw(headYaw);
        this.setMetadata(metadata);
    }

    public final byte getType() {
        return this.type;
    }

    @Override
    public String toString() {
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append(super.toString());
        builder.append(" | Type = ");
        builder.append(MCMobType.mobForID(this.type));
        builder.append(" | Head yaw = ");
        builder.append(this.getHeadYaw());

        return builder.toString();
    }
}
