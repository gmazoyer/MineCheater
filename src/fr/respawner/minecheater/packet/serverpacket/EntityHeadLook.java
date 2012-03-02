package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.entity.MCEntity;
import fr.respawner.minecheater.structure.entity.MCHeadYaw;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class EntityHeadLook extends Packet {
    private int entityID;
    private byte headYaw;

    private MCHeadYaw instance;

    public EntityHeadLook(PacketsHandler handler) {
        super(handler, (byte) 0x23);
    }

    @Override
    public void read() throws IOException {
        this.entityID = this.readInt();
        this.headYaw = this.readByte();
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
         * Find the entity to set its look.
         */
        entity = (MCEntity) this.getWorld().findObjectByID(this.entityID);
        this.instance = new MCHeadYaw(this.entityID, this.headYaw);

        if (entity != null) {
            entity.setHeadYaw(this.headYaw);
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
        return this.instance;
    }
}
