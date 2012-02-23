package fr.respawner.minecheater.structure;

public final class Teleport {
	private int entityID;
	private int x;
	private int y;
	private int z;
	private byte yaw;
	private byte pitch;

	public Teleport(int entityID, int x, int y, int z, byte yaw, byte pitch) {
		this.entityID = entityID;
		this.x = x;
		this.y = y;
		this.z = z;
		this.yaw = yaw;
		this.pitch = pitch;
	}

	public int getEntityID() {
		return this.entityID;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public int getZ() {
		return this.z;
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

		builder.append("Position: x = ");
		builder.append(this.x);
		builder.append(", y = ");
		builder.append(this.y);
		builder.append(", z = ");
		builder.append(this.z);
		builder.append(", yaw = ");
		builder.append(this.yaw);
		builder.append(", pitch = ");
		builder.append(this.pitch);

		return builder.toString();
	}
}
