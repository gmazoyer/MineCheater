package fr.respawner.minecheater.structure.world;

public final class MCWorld {
    private int entityID;
    private String levelType;
    private int serverMode;
    private int dimension;
    private byte difficulty;
    private int worldHeight;
    private int maxPlayers;

    public MCWorld(int entityID, String levelType, int serverMode,
            int dimension, byte difficulty, int worldHeight, int maxPlayers) {
        this.entityID = entityID;
        this.levelType = levelType;
        this.serverMode = serverMode;
        this.dimension = dimension;
        this.difficulty = difficulty;
        this.worldHeight = worldHeight;
        this.maxPlayers = maxPlayers;
    }

    public final int getEntityID() {
        return this.entityID;
    }

    public final String getLevelType() {
        return this.levelType;
    }

    public final int getServerMode() {
        return this.serverMode;
    }

    public final int getDimension() {
        return this.dimension;
    }

    public final byte getDifficulty() {
        return this.difficulty;
    }

    public final int getWorldHeight() {
        return this.worldHeight;
    }

    public final int getMaxPlayers() {
        return this.maxPlayers;
    }

    @Override
    public String toString() {
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append("EntityID = ");
        builder.append(this.entityID);
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
