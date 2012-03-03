package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.entity.MCEntity;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class DestroyEntity extends Packet {
    private int entityID;

    public DestroyEntity(PacketsHandler handler) {
        super(handler, (byte) 0x1D);
    }

    @Override
    public void read() throws IOException {
        this.entityID = this.readInt();
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
         * Find the entity to set the velocity.
         */
        entity = (MCEntity) this.getWorld().findObjectByID(this.entityID);

        if (entity != null) {
            this.getWorld().removeObject(entity);
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
        return ("Entity ID = " + this.entityID);
    }
}
