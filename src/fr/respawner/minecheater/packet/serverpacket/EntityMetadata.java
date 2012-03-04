package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.metadata.Metadata;
import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.entity.MCEntity;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class EntityMetadata extends Packet {
    private int entityID;
    private Metadata metadata;

    public EntityMetadata(PacketsHandler handler) {
        super(handler, (byte) 0x28);
    }

    @Override
    public void read() throws IOException {
        this.entityID = this.readInt();
        this.metadata = new Metadata(this.handler);
        this.metadata.parse();
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
         * Find the entity to set the metadata.
         */
        entity = (MCEntity) this.getWorld().findObjectByID(this.entityID);

        if (entity != null) {
            entity.setMetadata(this.metadata);
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
        builder.append(" | Metadata = ");
        builder.append(this.metadata);

        return builder.toString();
    }
}
