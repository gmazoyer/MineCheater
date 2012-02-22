package fr.respawner.minecheater.structure.block;

public final class MultiBlockDataChange {
	private int chunkX;
	private int chunkZ;
	private short[] coordinates;
	private byte[] types;
	private byte[] metadatas;

	public MultiBlockDataChange(int chunkX, int chunkZ, short[] coordinates,
			byte[] types, byte[] metadatas) {
		this.chunkX = chunkX;
		this.chunkZ = chunkZ;
		this.coordinates = coordinates;
		this.types = types;
		this.metadatas = metadatas;
	}

	public int getChunkX() {
		return this.chunkX;
	}

	public int getChunkZ() {
		return this.chunkZ;
	}

	public short[] getCoordinates() {
		return this.coordinates;
	}

	public byte[] getTypes() {
		return this.types;
	}

	public byte[] getMetadatas() {
		return this.metadatas;
	}

	@Override
	public String toString() {
		final StringBuilder builder;

		builder = new StringBuilder();

		builder.append("Chunk: x = ");
		builder.append(this.chunkX);
		builder.append(", z = ");
		builder.append(this.chunkZ);
		builder.append(" | Coordinates = { ");
		for (short coordinate : this.coordinates) {
			builder.append(coordinate);
			builder.append(", ");
		}
		builder.replace(builder.length() - 2, builder.length(), " }");
		builder.append(" | Types = { ");
		for (byte type : this.types) {
			builder.append(type);
			builder.append(", ");
		}
		builder.replace(builder.length() - 2, builder.length(), " }");
		builder.append(" | Metadatas = { ");
		for (byte metadata : this.metadatas) {
			builder.append(metadata);
			builder.append(", ");
		}
		builder.replace(builder.length() - 2, builder.length(), " }");

		return builder.toString();
	}
}
