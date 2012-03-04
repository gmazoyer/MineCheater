package fr.respawner.minecheater.structure.entity;

import fr.respawner.minecheater.structure.type.MCEffectType;

public final class MCEffect {
    private byte effectID;
    private byte amplifier;
    private short duration;

    public MCEffect(byte effectID, byte amplifier, short duration) {
        this.effectID = effectID;
        this.amplifier = amplifier;
        this.duration = duration;
    }

    public byte getEffectID() {
        return this.effectID;
    }

    public byte getAmplifier() {
        return this.amplifier;
    }

    public short getDuration() {
        return this.duration;
    }

    @Override
    public String toString() {
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append("Effect = ");
        builder.append(MCEffectType.effectForID(this.effectID));
        builder.append(" | Amplifier = ");
        builder.append(this.amplifier);
        builder.append(" | Duration = ");
        builder.append(this.duration);

        return builder.toString();
    }
}
