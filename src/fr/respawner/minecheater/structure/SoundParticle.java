package fr.respawner.minecheater.structure;

public final class SoundParticle {
	private int effectID;
	private int x;
	private byte y;
	private int z;
	private int data;

	public SoundParticle(int effectID, int x, byte y, int z, int data) {
		this.effectID = effectID;
		this.x = x;
		this.y = y;
		this.z = z;
		this.data = data;
	}

	public int getEffectID() {
		return this.effectID;
	}

	public int getX() {
		return this.x;
	}

	public byte getY() {
		return this.y;
	}

	public int getZ() {
		return this.z;
	}

	public int getData() {
		return this.data;
	}

	@Override
	public String toString() {
		final StringBuilder builder;

		builder = new StringBuilder();

		builder.append("Effect = ");
		builder.append(SoundParticleType.effectForID(this.effectID));
		builder.append(" | Position: x = ");
		builder.append(this.x);
		builder.append(", y = ");
		builder.append(this.y);
		builder.append(", z = ");
		builder.append(this.z);
		builder.append(" | Data = ");
		builder.append(this.data);

		return builder.toString();
	}
}
