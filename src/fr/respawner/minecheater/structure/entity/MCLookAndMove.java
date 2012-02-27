package fr.respawner.minecheater.structure.entity;

public final class MCLookAndMove {
	private int entityID;
	private byte dX;
	private byte dY;
	private byte dZ;
	private byte yaw;
	private byte pitch;

	public MCLookAndMove(int entityID, byte dX, byte dY, byte dZ, byte yaw,
			byte pitch) {
		this.entityID = entityID;
		this.dX = dX;
		this.dY = dY;
		this.dZ = dZ;
		this.yaw = yaw;
		this.pitch = pitch;
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
		builder.append(" | Move : x = ");
		builder.append(this.dX);
		builder.append(", y = ");
		builder.append(this.dY);
		builder.append(", z = ");
		builder.append(this.dZ);
		builder.append(" | Look: yaw = ");
		builder.append(this.yaw);
		builder.append(", pitch = ");
		builder.append(this.pitch);

		return builder.toString();
	}
}
