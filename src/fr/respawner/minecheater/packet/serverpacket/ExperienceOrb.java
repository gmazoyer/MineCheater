package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.entity.MCExperienceOrb;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class ExperienceOrb extends Packet {
    private int entityID;
    private int x;
    private int y;
    private int z;
    private short count;

    private MCExperienceOrb instance;

    public ExperienceOrb(PacketsHandler handler) {
        super(handler, (byte) 0x1A);
    }

    @Override
    public void read() throws IOException {
        this.entityID = this.readInt();
        this.x = this.readInt();
        this.y = this.readInt();
        this.z = this.readInt();
        this.count = this.readShort();
    }

    @Override
    public void write() throws IOException {
        /*
         * We don't write this packet.
         */
    }

    @Override
    public void process() {
        this.instance = new MCExperienceOrb(this.entityID, this.x, this.y,
                this.z, this.count);
        this.handler.getWorld().addObject(this.instance);
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
