package fr.respawner.minecheater.structure;

/**
 * This is the mother class of everything in Minecraft that can be identified.
 * 
 * @author Guillaume Mazoyer
 */
public abstract class MCIdentifiable {
    protected int entityID;

    public MCIdentifiable(int entityID) {
        this.entityID = entityID;
    }

    public final int getEntityID() {
        return this.entityID;
    }

    public final void setEntityID(int entityID) {
        this.entityID = entityID;
    }
}
