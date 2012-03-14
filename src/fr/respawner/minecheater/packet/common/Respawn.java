package fr.respawner.minecheater.packet.common;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
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
        super(handler, (byte) 0x09);
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
        this.writeInt(this.getWorld().getCurrentWorld().getDimension());
        this.writeByte((byte) 1);
        this.writeByte((byte) this.getWorld().getCurrentWorld().getServerMode());
        this.writeShort((short) this.getWorld().getCurrentWorld()
                .getWorldHeight());
        this.writeUnicodeString(this.getWorld().getCurrentWorld()
                .getLevelType());
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
