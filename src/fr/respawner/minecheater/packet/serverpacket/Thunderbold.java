package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.entity.MCThunderbolt;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class Thunderbold extends Packet {
    private int entityID;
    private boolean unknown;
    private int x;
    private int y;
    private int z;

    private MCThunderbolt instance;

    public Thunderbold(PacketsHandler handler) {
        super(handler, (byte) 0x47);
    }

    @Override
    public void read() throws IOException {
        this.entityID = this.readInt();
        this.unknown = this.readBoolean();
        this.x = this.readInt();
        this.y = this.readInt();
        this.z = this.readInt();
    }

    @Override
    public void write() throws IOException {
        /*
         * We don't write this packet.
         */
    }

    @Override
    public void process() {
        this.instance = new MCThunderbolt(this.entityID, this.unknown, this.x,
                this.y, this.z);
        this.getWorld().addObject(this.instance);
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
