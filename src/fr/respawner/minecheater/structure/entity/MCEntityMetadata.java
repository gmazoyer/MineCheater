package fr.respawner.minecheater.structure.entity;

import fr.respawner.minecheater.metadata.Metadata;

public final class MCEntityMetadata {
	private int entityID;
	private Metadata metadata;

	public MCEntityMetadata(int entityID, Metadata metadata) {
		this.entityID = entityID;
		this.metadata = metadata;
	}

	public int getEntityID() {
		return this.entityID;
	}

	public Metadata getMetadata() {
		return this.metadata;
	}

	@Override
	public String toString() {
		final StringBuilder builder;

		builder = new StringBuilder();

		builder.append("Entity ID = ");
		builder.append(this.entityID);
		builder.append(" | Metadata = ");
		builder.append(this.metadata);

		return builder.toString();
	}
}
