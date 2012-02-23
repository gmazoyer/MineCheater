package fr.respawner.minecheater.structure;

public final class ExplosionAnimation {
	private double x;
	private double y;
	private double z;
	private float unknown;
	private byte[][] records;

	public ExplosionAnimation(double x, double y, double z, float unknown,
			byte[][] records) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.unknown = unknown;
		this.records = records;
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	public double getZ() {
		return this.z;
	}

	public float getUnknown() {
		return this.unknown;
	}

	public byte[][] getRecords() {
		return this.records;
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
		builder.append(" | Records = { ");
		for (int i = 0; i < this.records.length; i++) {
			builder.append("{ ");

			for (byte b = 0; b < 3; b++) {
				builder.append(this.records[i][b]);
				builder.append(", ");
			}

			builder.replace(builder.length() - 2, builder.length(), " }");
			builder.append(", ");
		}
		builder.replace(builder.length() - 2, builder.length(), " }");

		return builder.toString();
	}
}
