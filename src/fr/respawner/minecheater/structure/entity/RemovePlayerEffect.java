package fr.respawner.minecheater.structure.entity;

public final class RemovePlayerEffect {
    private int entityID;
    private byte effectID;

    public RemovePlayerEffect(int entityID, byte effectID) {
        this.entityID = entityID;
        this.effectID = effectID;
    }

    public int getEntityID() {
        return this.entityID;
    }

    public byte getEffectID() {
        return this.effectID;
    }

    @Override
    public String toString() {
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append("Entity ID = ");
        builder.append(this.entityID);
        builder.append(" | Effect = ");
        builder.append(EntityEffectType.effectForID(this.effectID));

        return builder.toString();
    }
}
