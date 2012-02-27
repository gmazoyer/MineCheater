package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.world.Chunk;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class MapChunk extends Packet {
    private int x;
    private short y;
    private int z;
    private byte sizeX;
    private byte sizeY;
    private byte sizeZ;
    private byte[] zlibData;

    public MapChunk(PacketsHandler handler) {
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
    public Object getData() {
        return new Chunk(this.x, this.y, this.z, this.sizeX, this.sizeY,
                this.sizeZ, this.zlibData);
    }
}
