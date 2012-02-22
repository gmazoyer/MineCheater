package fr.respawner.minecheater.structure;

import fr.respawner.minecheater.structure.entity.EntityEffectType;

public final class PlayerEffect {
	private int entityID;
	private byte effectID;
	private byte amplifier;
	private short duration;

	public PlayerEffect(int entityID, byte effectID, byte amplifier,
			short duration) {
		this.entityID = entityID;
		this.effectID = effectID;
		this.amplifier = amplifier;
		this.duration = duration;
	}

	public int getEntityID() {
		return this.entityID;
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

		builder.append("Entity ID = ");
		builder.append(this.entityID);
		builder.append(" | Effect = ");
		builder.append(EntityEffectType.effectForID(this.effectID));
		builder.append(" | Amplifier = ");
		builder.append(this.amplifier);
		builder.append(" | Duration = ");
		builder.append(this.duration);

		return builder.toString();
	}
}
