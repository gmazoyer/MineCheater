package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.entity.MCEntity;
import fr.respawner.minecheater.structure.type.MCStatusType;
import fr.respawner.minecheater.worker.IHandler;

public final class EntityStatus extends Packet {
    private int entityID;
    private byte status;

    public EntityStatus(IHandler handler) {
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
    public void process() {
        final MCEntity entity;

        /*
         * Find the entity to set the status.
         */
        entity = (MCEntity) this.getWorld().findObjectByID(this.entityID);

        if (entity != null) {
            entity.setStatus(this.status);
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
    public String getDataAsString() {
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append("Entity ID = ");
        builder.append(this.entityID);
        builder.append(" | Status = ");
        builder.append(MCStatusType.statusForID(this.status));

        return builder.toString();
    }
}
