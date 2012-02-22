package fr.respawner.minecheater.structure;

public final class Look {
	private int entityID;
	private byte yaw;
	private byte pitch;

	public Look(int entityID, byte yaw, byte pitch) {
		this.entityID = entityID;
		this.yaw = yaw;
		this.pitch = pitch;
	}

	public int getEntityID() {
		return this.entityID;
	}

	public byte getYaw() {
		return this.yaw;
	}

	public byte getPitch() {
		return this.pitch;
	}

	@Override
	public String toString() {
		final StringBuilder builder;

		builder = new StringBuilder();

		builder.append("Entity ID = ");
		builder.append(this.entityID);
		builder.append(" | Rotation: yaw = ");
		builder.append(this.yaw);
		builder.append(", pitch = ");
		builder.append(this.pitch);

		return builder.toString();
	}
}
