package fr.respawner.minecheater.structure.block;

public final class PreChunk {
	private int x;
	private int z;
	private boolean mode;

	public PreChunk(int x, int z, boolean mode) {
		this.x = x;
		this.z = z;
		this.mode = mode;
	}

	public int getX() {
		return this.x;
	}

	public int getZ() {
		return this.z;
	}

	public boolean isMode() {
		return this.mode;
	}

	@Override
	public String toString() {
		final StringBuilder builder;

		builder = new StringBuilder();

		builder.append("X = ");
		builder.append(this.x);
		builder.append(" | Z = ");
		builder.append(this.z);
		builder.append(" | Mode = ");
		builder.append(this.mode ? "Initializing chunk" : "Unloading chunk");

		return builder.toString();
	}
}
