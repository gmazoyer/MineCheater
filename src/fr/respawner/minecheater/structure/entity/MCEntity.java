package fr.respawner.minecheater.structure.entity;

import fr.respawner.minecheater.math.VectorDouble;
import fr.respawner.minecheater.metadata.Metadata;
import fr.respawner.minecheater.structure.type.MCAnimationType;
import fr.respawner.minecheater.structure.type.MCStatusType;

/**
 * This class of all objects that can move in the Minecraft world (players,
 * mobs, etc...).
 * 
 * @author Guillaume Mazoyer
 */
public class MCEntity extends MCObject {
    private VectorDouble velocity;
    private byte dX;
    private byte dY;
    private byte dZ;
    private byte headYaw;
    private Metadata metadata;
    private byte status;
    private byte lastAnimation;

    public MCEntity(int entityID, int x, int y, int z) {
        super(entityID, x, y, z);
    }

    public final byte getRelativeX() {
        return this.dX;
    }

    public final void setRelativeX(byte dX) {
        this.dX = dX;
    }

    public final byte getRelativeY() {
        return this.dY;
    }

    public final void setRelativeY(byte dY) {
        this.dY = dY;
    }

    public final byte getRelativeZ() {
        return this.dZ;
    }

    public final void setRelativeZ(byte dZ) {
        this.dZ = dZ;
    }

    public final byte[] getMove() {
        return new byte[] { this.dX, this.dY, this.dZ };
    }

    public final void setMove(byte dX, byte dY, byte dZ) {
        this.dX = dX;
        this.dY = dY;
        this.dZ = dZ;
    }

    public byte getHeadYaw() {
        return this.headYaw;
    }

    public void setHeadYaw(byte headYaw) {
        this.headYaw = headYaw;
    }

    public final Metadata getMetadata() {
        return this.metadata;
    }

    public final void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public final byte getStatus() {
        return this.status;
    }

    public final void setStatus(byte status) {
        this.status = status;
    }

    public final VectorDouble getVelocity() {
        return this.velocity;
    }

    public final void setVelocity(VectorDouble velocity) {
        this.velocity = velocity;
    }

    public final byte getLastAnimation() {
        return this.lastAnimation;
    }

    public final void setLastAnimation(byte lastAnimation) {
        this.lastAnimation = lastAnimation;
    }

    @Override
    public String toString() {
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append(super.toString());
        builder.append(" | Velocity = ");
        builder.append(this.velocity);
        builder.append(" | Derivative location: x = ");
        builder.append(this.dX);
        builder.append(", y = ");
        builder.append(this.dY);
        builder.append(", z = ");
        builder.append(this.dZ);
        builder.append(" | Head yaw = ");
        builder.append(this.headYaw);
        builder.append(" | Metadata = ");
        builder.append(this.metadata);
        builder.append(" | Status = ");
        builder.append(MCStatusType.statusForID(this.status));
        builder.append(" | Last animation = ");
        builder.append(MCAnimationType.animationForID(this.lastAnimation));

        return builder.toString();
    }
}
