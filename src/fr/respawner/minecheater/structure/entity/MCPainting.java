package fr.respawner.minecheater.structure.entity;

/**
 * This is the class that represents a painting.
 * 
 * @author Guillaume Mazoyer
 */
public final class MCPainting extends MCObject {
	private String title;
	private int direction;

	public MCPainting(int entityID, String title, int x, int y, int z,
			int direction) {
		super(entityID, x, y, z);

		this.title = title;
		this.direction = direction;
	}

	public String getTitle() {
		return this.title;
	}

	public int getDirection() {
		return this.direction;
	}

	@Override
	public String toString() {
		final StringBuilder builder;

		builder = new StringBuilder();

		builder.append("Entity ID = ");
		builder.append(this.getEntityID());
		builder.append(" | Title = ");
		builder.append(this.title);
		builder.append(" | Position: x = ");
		builder.append(this.getX());
		builder.append(", y = ");
		builder.append(this.getY());
		builder.append(", z = ");
		builder.append(this.getZ());
		builder.append(", direction = ");
		builder.append(this.direction);

		return builder.toString();
	}
}
