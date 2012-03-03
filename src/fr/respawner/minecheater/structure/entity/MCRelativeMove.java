package fr.respawner.minecheater.structure.entity;

public final class MCRelativeMove {
    private int entityID;
    private byte dX;
    private byte dY;
    private byte dZ;

    public MCRelativeMove(int entityID, byte dX, byte dY, byte dZ) {
        this.entityID = entityID;
        this.dX = dX;
        this.dY = dY;
        this.dZ = dZ;
    }

    public int getEntityID() {
        return this.entityID;
    }

    public byte getdX() {
        return this.dX;
    }

    public byte getdY() {
        return this.dY;
    }

    public byte getdZ() {
        return this.dZ;
    }

    @Override
    public String toString() {
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append("Entity ID = ");
        builder.append(this.entityID);
        builder.append(" | Move : x = ");
        builder.append(this.dX);
        builder.append(", y = ");
        builder.append(this.dY);
        builder.append(", z = ");
        builder.append(this.dZ);

        return builder.toString();
    }
}
