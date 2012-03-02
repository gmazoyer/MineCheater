package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.entity.MCBed;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class UseBed extends Packet {
    private int entityID;
    private byte inBed;
    private int x;
    private byte y;
    private int z;

    private MCBed instance;

    public UseBed(PacketsHandler handler) {
        super(handler, (byte) 0x11);
    }

    @Override
    public void read() throws IOException {
        this.entityID = this.readInt();
        this.inBed = this.readByte();
        this.x = this.readInt();
        this.y = this.readByte();
        this.z = this.readInt();
    }

    @Override
    public void write() throws IOException {
        /*
         * We don't write this packet.
         */
    }

    @Override
    public void parse() {
        this.instance = new MCBed(this.entityID, this.inBed, this.x, this.y,
                this.z);
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
        return this.instance;
    }
}
