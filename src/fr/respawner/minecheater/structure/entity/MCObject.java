package fr.respawner.minecheater.structure.entity;

/**
 * This is the mother class of all objects in the Minecraft world (players,
 * mobs, paintings, etc...).
 * 
 * @author Guillaume Mazoyer
 */
public abstract class MCObject {
    private int entityID;

    /*
     * The coordinates of the entity.
     */
    private int x;
    private int y;
    private int z;

    public MCObject(int entityID) {
        this.entityID = entityID;
    }

    public MCObject(int entityID, int x, int y, int z) {
        this.entityID = entityID;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public final int getEntityID() {
        return this.entityID;
    }

    public final void setEntityID(int entityID) {
        this.entityID = entityID;
    }

    public final int getX() {
        return this.x;
    }

    public final void setX(int x) {
        this.x = x;
    }

    public final int getY() {
        return this.y;
    }

    public final void setY(int y) {
        this.y = y;
    }

    public final int getZ() {
        return this.z;
    }

    public final void setZ(int z) {
        this.z = z;
    }

    public final int[] getCoordinates() {
        return new int[] { this.x, this.y, this.z };
    }

    public final void setCoordinates(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
