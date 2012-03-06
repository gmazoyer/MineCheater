package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.worker.IHandler;

public final class MapChunk extends Packet {
    private int x;
    private short y;
    private int z;
    private byte sizeX;
    private byte sizeY;
    private byte sizeZ;
    private byte[] zlibData;

    public MapChunk(IHandler handler) {
        super(handler, (byte) 0x33);
    }

    @Override
    public void read() throws IOException {
        final int length;

        this.x = this.readInt();
        this.y = this.readShort();
        this.z = this.readInt();
        this.sizeX = this.readByte();
        this.sizeY = this.readByte();
        this.sizeZ = this.readByte();

        length = this.readInt();

        this.zlibData = this.readByteArray(length);
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

        builder.append("Position: x = ");
        builder.append(this.x);
        builder.append(", y = ");
        builder.append(this.y);
        builder.append(", z = ");
        builder.append(this.z);
        builder.append(" | Size: x = ");
        builder.append(this.sizeX);
        builder.append(", y = ");
        builder.append(this.sizeY);
        builder.append(", z = ");
        builder.append(this.sizeZ);
        builder.append(" | Data = { ");
        for (byte b : this.zlibData) {
            builder.append(b);
            builder.append(", ");
        }
        builder.replace(builder.length() - 2, builder.length(), " }");

        return builder.toString();
    }
}
