/*
 * Copyright (c) 2012 Guillaume Mazoyer
 * 
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package fr.respawner.minecheater.structure.world;

import fr.respawner.minecheater.structure.type.MCDifficultyType;
import fr.respawner.minecheater.structure.type.MCDimensionType;

public final class MCLevel {
    private int entityID;
    private String levelType;
    private int serverMode;
    private int dimension;
    private byte difficulty;
    private int worldHeight;
    private int maxPlayers;

    public MCLevel(int entityID, String levelType, int serverMode,
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
        builder.append(MCDimensionType.dimensionForID(this.dimension));
        builder.append(" | Difficulty = ");
        builder.append(MCDifficultyType.difficultyForID(this.difficulty));
        builder.append(" | WorldHeight = ");
        builder.append(this.worldHeight);
        builder.append(" | MaxPlayers = ");
        builder.append(this.maxPlayers);

        return builder.toString();
    }
}
