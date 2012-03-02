package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.entity.MCEntity;
import fr.respawner.minecheater.structure.entity.MCStatus;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class EntityStatus extends Packet {
    private int entityID;
    private byte status;

    private MCStatus instance;

    public EntityStatus(PacketsHandler handler) {
        super(handler, (byte) 0x26);
    }

    @Override
    public void read() throws IOException {
        this.entityID = this.readInt();
        this.status = this.readByte();
    }

    @Override
    public void write() throws IOException {
        /*
         * We don't write this packet.
         */
    }

    @Override
    public void parse() {
        final MCEntity entity;

        /*
         * Find the entity to set the status.
         */
        entity = (MCEntity) this.getWorld().findObjectByID(this.entityID);
        this.instance = new MCStatus(this.entityID, this.status);

        if (entity != null) {
            entity.setStatus(this.instance);
        }
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
        return status;
    }
}
