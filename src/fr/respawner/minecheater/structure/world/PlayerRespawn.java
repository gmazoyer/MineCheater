package fr.respawner.minecheater.structure.world;

public final class PlayerRespawn {
    private int dimension;
    private byte difficulty;
    private byte mode;
    private short worldHeight;
    private String levelType;

    public PlayerRespawn(int dimension, byte difficulty, byte mode,
            short worldHeight, String levelType) {
        this.dimension = dimension;
        this.difficulty = difficulty;
        this.mode = mode;
        this.worldHeight = worldHeight;
        this.levelType = levelType;
    }

    public int getDimension() {
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
        builder.append(" | LevelType = ");
        builder.append(this.levelType);

        return builder.toString();
    }
}
