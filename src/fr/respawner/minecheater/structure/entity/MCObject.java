package fr.respawner.minecheater.structure.entity;

import fr.respawner.minecheater.math.Location;
import fr.respawner.minecheater.math.Rotation;
import fr.respawner.minecheater.structure.MCIdentifiable;

/**
 * This is the mother class of all objects in the Minecraft world (players,
 * mobs, paintings, etc...).
 * 
 * @author Guillaume Mazoyer
 */
public abstract class MCObject extends MCIdentifiable {
    protected Location location;
    protected Rotation rotation;

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

    public Rotation getRotation() {
        return this.rotation;
    }

    public void setRotation(Rotation rotation) {
        this.rotation = rotation;
    }

    @Override
    public String toString() {
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append("Entity ID = ");
        builder.append(this.getEntityID());
        builder.append(" | ");
        builder.append(this.location);
        builder.append(" | ");
        builder.append(this.rotation);

        return builder.toString();
    }
}
