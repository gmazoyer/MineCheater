package fr.respawner.minecheater.structure.entity;


public final class MCThunderbolt extends MCObject {
	private boolean unknown;

	public MCThunderbolt(int entityID, boolean unknown, int x, int y, int z) {
		super(entityID, x, y, z);

		this.unknown = unknown;
	}

	public boolean isUnknown() {
		return this.unknown;
	}

	@Override
	public String toString() {
		final StringBuilder builder;

		builder = new StringBuilder();

		builder.append("Entity ID = ");
		builder.append(this.getEntityID());
		builder.append(" | Position: x = ");
		builder.append(this.getX());
		builder.append(", y = ");
		builder.append(this.getY());
		builder.append(", z = ");
		builder.append(this.getZ());

		return builder.toString();
	}
}
