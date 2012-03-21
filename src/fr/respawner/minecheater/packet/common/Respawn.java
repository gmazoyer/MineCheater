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
package fr.respawner.minecheater.packet.common;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.packet.PacketIdentifier;
import fr.respawner.minecheater.structure.type.MCDifficultyType;
import fr.respawner.minecheater.structure.type.MCDimensionType;
import fr.respawner.minecheater.worker.IHandler;

public final class Respawn extends Packet {
    private int dimension;
    private byte difficulty;
    private byte mode;
    private short worldHeight;
    private String levelType;

    public Respawn(IHandler handler) {
        super(handler, PacketIdentifier.RESPAWN);
    }

    @Override
    public void read() throws IOException {
        this.dimension = this.readInt();
        this.difficulty = this.readByte();
        this.mode = this.readByte();
        this.worldHeight = this.readShort();
        this.levelType = this.readUnicodeString();
    }

    @Override
    public void write() throws IOException {
        this.writeInt(this.getWorld().getLevel().getDimension());
        this.writeByte((byte) 1);
        this.writeByte((byte) this.getWorld().getLevel().getServerMode());
        this.writeShort((short) this.getWorld().getLevel().getWorldHeight());
        this.writeUnicodeString(this.getWorld().getLevel().getLevelType());
    }

    @Override
    public void process() {
        /*
         * Nothing to do.
         */
    }

    @Override
    public Packet response() {
        /*
         * We don't send a response to this packet.
         */
        return null;
    }

    @Override
    public String getDataAsString() {
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append("Dimension = ");
        builder.append(MCDimensionType.dimensionForID(this.dimension));
        builder.append(" | Difficulty = ");
        builder.append(MCDifficultyType.difficultyForID(this.difficulty));
        builder.append(" | Mode = ");
        builder.append(this.mode);
        builder.append(" | WorldHeight = ");
        builder.append(this.worldHeight);
        builder.append(" | LevelType = ");
        builder.append(this.levelType);

        return builder.toString();
    }
}
