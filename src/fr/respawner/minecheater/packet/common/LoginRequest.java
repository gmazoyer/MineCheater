package fr.respawner.minecheater.packet.common;

import java.io.IOException;

import fr.respawner.minecheater.Config;
import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.type.MCDifficultyType;
import fr.respawner.minecheater.structure.type.MCDimensionType;
import fr.respawner.minecheater.structure.world.MCWorld;
import fr.respawner.minecheater.worker.IHandler;

public final class LoginRequest extends Packet {
    private int protocolVersionOrEntityID;
    private String username;
    private String levelType;
    private int serverMode;
    private int dimension;
    private byte difficulty;
    private int worldHeight;
    private int maxPlayers;

    public LoginRequest(IHandler handler) {
        super(handler, (byte) 0x01);

        this.protocolVersionOrEntityID = Config.PROTOCOL_VERSION;
        this.username = Config.USERNAME;
    }

    @Override
    public void read() throws IOException {
        this.protocolVersionOrEntityID = this.readInt();
        this.username = this.readUnicodeString();
        this.levelType = this.readUnicodeString();
        this.serverMode = this.readInt();
        this.dimension = this.readInt();
        this.difficulty = this.readByte();
        this.worldHeight = this.readUnsignedByte();
        this.maxPlayers = this.readUnsignedByte();
    }

    @Override
    public void write() throws IOException {
        this.writeInt(this.protocolVersionOrEntityID);
        this.writeUnicodeString(this.username);
        this.writeUnicodeString("");
        this.writeInt(0);
        this.writeInt(0);
        this.writeByte((byte) 0);
        this.writeUnsignedByte(0);
        this.writeUnsignedByte(0);
        this.send();
    }

    @Override
    public void process() {
        final MCWorld world;

        world = new MCWorld(this.protocolVersionOrEntityID, this.levelType,
                this.serverMode, this.dimension, this.difficulty,
                this.worldHeight, this.maxPlayers);
        this.getWorld().getPlayer().setEntityID(this.protocolVersionOrEntityID);
        this.getWorld().setCurrentWorld(world);
    }

    @Override
    public Packet response() {
        return null;
    }

    @Override
    public String getDataAsString() {
        final StringBuilder builder;

        builder = new StringBuilder();

        if (this.action == PacketAction.WRITING) {
            builder.append("Protocol version = ");
            builder.append(this.protocolVersionOrEntityID);
            builder.append(" | Username = ");
            builder.append(this.username);
        } else {
            builder.append("EntityID = ");
            builder.append(this.protocolVersionOrEntityID);
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
        }

        return builder.toString();
    }
}
