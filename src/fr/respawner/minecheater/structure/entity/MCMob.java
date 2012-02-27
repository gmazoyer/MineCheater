package fr.respawner.minecheater.structure.entity;

import fr.respawner.minecheater.metadata.Metadata;

/**
 * This is the class that represents a mob.
 * 
 * @author Guillaume Mazoyer
 */
public final class MCMob extends MCEntity {
	private byte type;

	public MCMob(int entityID, byte type, int x, int y, int z, byte yaw,
			byte pitch, Metadata metadata) {
		super(entityID, x, y, z);

		this.type = type;

		this.setYaw(yaw);
		this.setPitch(pitch);
		this.setMetadata(new MCEntityMetadata(entityID, metadata));
	}

	public final byte getType() {
		return this.type;
	}

	@Override
	public String toString() {
		final StringBuilder builder;

		builder = new StringBuilder();

		builder.append("Entity ID = ");
		builder.append(this.getEntityID());
		builder.append(" | Type = ");
		builder.append(MobType.mobForID(this.type));
		builder.append(" | Position: x = ");
		builder.append(this.getX());
		builder.append(", y = ");
		builder.append(this.getY());
		builder.append(", z = ");
		builder.append(this.getZ());
		builder.append(", yaw = ");
		builder.append(this.getYaw());
		builder.append(", pitch = ");
		builder.append(this.getPitch());
		builder.append(" | Metadata = ");
		builder.append(this.getMetadata().getMetadata());

		return builder.toString();
	}
}
