package fr.respawner.minecheater.structure.entity;

import fr.respawner.minecheater.structure.type.MCEffectType;

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
        builder.append(MCEffectType.effectForID(this.effectID));

        return builder.toString();
    }
}
