package fr.respawner.minecheater.structure.entity;

public final class MCVelocity {
	private int entityID;
	private short velocityX;
	private short velocityY;
	private short velocityZ;

	public MCVelocity(int entityID, short velocityX, short velocityY,
			short velocityZ) {
		this.entityID = entityID;
		this.velocityX = velocityX;
		this.velocityY = velocityY;
		this.velocityZ = velocityZ;
	}

	public int getEntityID() {
		return this.entityID;
	}

	public short getVelocityX() {
		return this.velocityX;
	}

	public short getVelocityY() {
		return this.velocityY;
	}

	public short getVelocityZ() {
		return this.velocityZ;
	}

	@Override
	public String toString() {
		final StringBuilder builder;

		builder = new StringBuilder();

		builder.append("Entity ID = ");
		builder.append(this.entityID);
		builder.append(" | Velocity X = ");
		builder.append(this.velocityX);
		builder.append(" | Velocity Y = ");
		builder.append(this.velocityY);
		builder.append(" | Velocity Z = ");
		builder.append(this.velocityZ);

		return builder.toString();
	}
}