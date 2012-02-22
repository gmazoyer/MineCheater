package fr.respawner.minecheater.structure.world;


public final class PlayerRespawn {
	private byte dimension;
	private byte difficulty;
	private byte mode;
	private short worldHeight;
	private long mapSeed;
	private String levelType;

	public PlayerRespawn(byte dimension, byte difficulty, byte mode,
			short worldHeight, long mapSeed, String levelType) {
		this.dimension = dimension;
		this.difficulty = difficulty;
		this.mode = mode;
		this.worldHeight = worldHeight;
		this.mapSeed = mapSeed;
		this.levelType = levelType;
	}

	public byte getDimension() {
		return this.dimension;
	}

	public byte getDifficulty() {
		return this.difficulty;
	}

	public byte getMode() {
		return this.mode;
	}

	public short getWorldHeight() {
		return this.worldHeight;
	}

	public long getMapSeed() {
		return this.mapSeed;
	}

	public String getLevelType() {
		return this.levelType;
	}

	@Override
	public String toString() {
		final StringBuilder builder;

		builder = new StringBuilder();

		builder.append("Dimension = ");
		builder.append(DimensionType.dimensionForID(this.dimension));
		builder.append(" | Difficulty = ");
		builder.append(DifficultyType.difficultyForID(this.difficulty));
		builder.append(" | Mode = ");
		builder.append(this.mode);
		builder.append(" | WorldHeight = ");
		builder.append(this.worldHeight);
		builder.append(" | MapSeed = ");
		builder.append(this.mapSeed);
		builder.append(" | LevelType = ");
		builder.append(this.levelType);

		return builder.toString();
	}
}
