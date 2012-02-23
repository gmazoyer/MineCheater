package fr.respawner.minecheater.structure.entity;

public final class MCPickup extends MCEntity {
	private short itemID;
	private byte count;
	private short damage;
	private byte rotation;
	private byte pitch;
	private byte roll;

	public MCPickup(int entityID, short itemID, byte count, short damage,
			int x, int y, int z, byte rotation, byte pitch, byte roll) {
		super(entityID, x, y, z);

		this.itemID = itemID;
		this.count = count;
		this.damage = damage;
		this.rotation = rotation;
		this.pitch = pitch;
		this.roll = roll;
	}

	public short getItemID() {
		return this.itemID;
	}

	public byte getCount() {
		return this.count;
	}

	public short getDamage() {
		return this.damage;
	}

	public byte getRotation() {
		return this.rotation;
	}

	public byte getPitch() {
		return this.pitch;
	}

	public byte getRoll() {
		return this.roll;
	}

	@Override
	public String toString() {
		final StringBuilder builder;

		builder = new StringBuilder();

		builder.append("Entity ID = ");
		builder.append(this.getEntityID());
		builder.append(" | Item ID = ");
		builder.append(MCItemType.itemForID(this.itemID));
		builder.append(" | Count = ");
		builder.append(this.count);
		builder.append(" | Position: x = ");
		builder.append(this.getX());
		builder.append(", y = ");
		builder.append(this.getY());
		builder.append(", z = ");
		builder.append(this.getZ());
		builder.append(", rotation = ");
		builder.append(this.rotation);
		builder.append(", pitch = ");
		builder.append(this.pitch);
		builder.append(", roll = ");
		builder.append(this.roll);

		return builder.toString();
	}
}
