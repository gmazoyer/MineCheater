package fr.respawner.minecheater.structure.entity;

/**
 * This class of all objects that can move in the Minecraft world (players,
 * mobs, etc...).
 * 
 * @author Guillaume Mazoyer
 */
public class MCEntity extends MCObject {
    private byte dX;
    private byte dY;
    private byte dZ;
    private byte yaw;
    private byte pitch;
    private MCEntityMetadata metadata;
    private MCStatus status;
    private MCVelocity velocity;
    private MCAnimation lastAnimation;

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

    public final byte getYaw() {
        return this.yaw;
    }

    public final void setYaw(byte yaw) {
        this.yaw = yaw;
    }

    public final byte getPitch() {
        return this.pitch;
    }

    public final void setPitch(byte pitch) {
        this.pitch = pitch;
    }

    public final MCEntityMetadata getMetadata() {
        return this.metadata;
    }

    public final void setMetadata(MCEntityMetadata metadata) {
        this.metadata = metadata;
    }

    public final MCStatus getStatus() {
        return this.status;
    }

    public final void setStatus(MCStatus status) {
        this.status = status;
    }

    public final MCVelocity getVelocity() {
        return this.velocity;
    }

    public final void setVelocity(MCVelocity velocity) {
        this.velocity = velocity;
    }

    public final MCAnimation getLastAnimation() {
        return this.lastAnimation;
    }

    public final void setLastAnimation(MCAnimation lastAnimation) {
        this.lastAnimation = lastAnimation;
    }
}
