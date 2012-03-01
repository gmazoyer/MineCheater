package fr.respawner.minecheater.structure.entity;

public final class MCHeadYaw {
    private int entityID;
    private byte headYaw;

    public MCHeadYaw(int entityID, byte headYaw) {
        this.entityID = entityID;
        this.headYaw = headYaw;
    }

    public int getEntityID() {
        return this.entityID;
    }

    public byte getHeadYaw() {
        return this.headYaw;
    }

    @Override
    public String toString() {
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append("Entity ID = ");
        builder.append(this.entityID);
        builder.append(" | Head yaw = ");
        builder.append(this.headYaw);

        return builder.toString();
    }
}
