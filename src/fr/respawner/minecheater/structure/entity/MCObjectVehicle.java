package fr.respawner.minecheater.structure.entity;

public final class MCObjectVehicle extends MCEntity {
	private byte type;
	private int throwerID;
	private short speedX;
	private short speedY;
	private short speedZ;

	public MCObjectVehicle(int entityID, byte type, int x, int y, int z,
			int throwerID, short speedX, short speedY, short speedZ) {
		super(entityID, x, y, z);
		this.type = type;
		this.throwerID = throwerID;
		this.speedX = speedX;
		this.speedY = speedY;
		this.speedZ = speedZ;
	}

	public byte getType() {
		return this.type;
	}

	public int getThrowerID() {
		return this.throwerID;
	}

	public short getSpeedX() {
		return this.speedX;
	}

	public short getSpeedY() {
		return this.speedY;
	}

	public short getSpeedZ() {
		return this.speedZ;
	}

	@Override
	public String toString() {
		final StringBuilder builder;

		builder = new StringBuilder();

		builder.append("Entity ID = ");
		builder.append(this.getEntityID());
		builder.append(" | Type = ");
		builder.append(ObjectVehicleType.objectForID(this.type));
		builder.append(" | Position: x = ");
		builder.append(this.getX());
		builder.append(", y = ");
		builder.append(this.getY());
		builder.append(", z = ");
		builder.append(this.getZ());

		if (this.throwerID > 0) {
			builder.append(" | Throwed of = ");
			builder.append(this.throwerID);
			builder.append(" | Speed: x = ");
			builder.append(this.speedX);
			builder.append(", y = ");
			builder.append(this.speedY);
			builder.append(", z = ");
			builder.append(this.speedZ);
		}

		return builder.toString();
	}
}
