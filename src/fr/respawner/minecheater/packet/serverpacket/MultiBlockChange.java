package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class MultiBlockChange extends Packet {
    private int chunkX;
    private int chunkZ;
    private short[] coordinates;
    private byte[] types;
    private byte[] metadatas;

    public MultiBlockChange(PacketsHandler handler) {
        super(handler, (byte) 0x34);
    }

    @Override
    public void read() throws IOException {
        final short length;

        this.chunkX = this.readInt();
        this.chunkZ = this.readInt();

        length = this.readShort();

        this.coordinates = this.readShortArray(length);
        this.types = this.readByteArray(length);
        this.metadatas = this.readByteArray(length);
    }

    @Override
    public void write() throws IOException {
        /*
         * We don't write this packet.
         */
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
