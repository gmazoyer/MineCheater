package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.worker.IHandler;

public final class MapChunk extends Packet {
    private int x;
    private int z;
    private boolean groundUpContinuous;
    private short primaryBitMap;
    private short addBitMap;
    private int compressedSize;
    private int unused;
    private byte[] zlibData;

    public MapChunk(IHandler handler) {
        super(handler, (byte) 0x33);
    }

    @Override
    public void read() throws IOException {
        this.x = this.readInt();
        this.z = this.readInt();
        this.groundUpContinuous = this.readBoolean();
        this.primaryBitMap = (short) this.readUnsignedShort();
        this.addBitMap = (short) this.readUnsignedShort();
        this.compressedSize = this.readInt();
        this.unused = this.readInt();
        this.zlibData = this.readUnsignedByteArray(this.compressedSize);
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
        builder.append(", z = ");
        builder.append(this.z);
        builder.append(" | Ground up continuous = ");
        builder.append(this.groundUpContinuous);
        builder.append(" | Primary bit map = ");
        builder.append(this.primaryBitMap);
        builder.append(" | Add bit map = ");
        builder.append(this.addBitMap);
        builder.append(" | Compressed size = ");
        builder.append(this.compressedSize);
        builder.append(" | Unused = ");
        builder.append(this.unused);
        builder.append(" | Data = { ");
        for (byte b : this.zlibData) {
            builder.append(b);
            builder.append(", ");
        }
        builder.replace(builder.length() - 2, builder.length(), " }");

        return builder.toString();
    }
}
