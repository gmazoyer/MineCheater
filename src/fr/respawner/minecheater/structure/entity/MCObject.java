package fr.respawner.minecheater.structure.entity;

import fr.respawner.minecheater.math.Location;
import fr.respawner.minecheater.structure.MCIdentifiable;

/**
 * This is the mother class of all objects in the Minecraft world (players,
 * mobs, paintings, etc...).
 * 
 * @author Guillaume Mazoyer
 */
public abstract class MCObject extends MCIdentifiable {
    protected Location location;

    public MCObject(int entityID) {
        super(entityID);
    }

    public MCObject(int entityID, int x, int y, int z) {
        this(entityID);

        this.location = new Location(x, y, z);
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append("Entity ID = ");
        builder.append(this.getEntityID());
        builder.append(" | ");
        builder.append(this.location);

        return builder.toString();
    }
}
