package fr.respawner.minecheater.structure.world;

public final class MCWorld {
	private int entityID;
	private long mapSeed;
	private String levelType;
	private int serverMode;
	private byte dimension;
	private byte difficulty;
	private int worldHeight;
	private int maxPlayers;

	public MCWorld(int entityID, long mapSeed, String levelType, int serverMode,
			byte dimension, byte difficulty, int worldHeight, int maxPlayers) {
		this.entityID = entityID;
		this.mapSeed = mapSeed;
		this.levelType = levelType;
		this.serverMode = serverMode;
		this.dimension = dimension;
		this.difficulty = difficulty;
		this.worldHeight = worldHeight;
		this.maxPlayers = maxPlayers;
	}

	public int getEntityID() {
		return this.entityID;
	}

	public long getMapSeed() {
		return this.mapSeed;
	}

	public String getLevelType() {
		return this.levelType;
	}

	public int getServerMode() {
		return this.serverMode;
	}

	public byte getDimension() {
		return this.dimension;
	}

	public byte getDifficulty() {
		return this.difficulty;
	}

	public int getWorldHeight() {
		return this.worldHeight;
	}

	public int getMaxPlayers() {
		return this.maxPlayers;
	}

	@Override
	public String toString() {
		final StringBuilder builder;

		builder = new StringBuilder();

		builder.append("EntityID = ");
		builder.append(this.entityID);
		builder.append(" | MapSeed = ");
		builder.append(this.mapSeed);
		builder.append(" | LevelType = ");
		builder.append(this.levelType);
		builder.append(" | ServerMode = ");
		builder.append(this.serverMode);
		builder.append(" | Dimension = ");
		builder.append(DimensionType.dimensionForID(this.dimension));
		builder.append(" | Difficulty = ");
		builder.append(DifficultyType.difficultyForID(this.difficulty));
		builder.append(" | WorldHeight = ");
		builder.append(this.worldHeight);
		builder.append(" | MaxPlayers = ");
		builder.append(this.maxPlayers);

		return builder.toString();
	}
}
