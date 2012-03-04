package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.entity.MCEntity;
import fr.respawner.minecheater.structure.type.MCAnimationType;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class Animation extends Packet {
    private int entityID;
    private byte animationID;

    public Animation(PacketsHandler handler) {
        super(handler, (byte) 0x12);
    }

    @Override
    public void read() throws IOException {
        this.entityID = this.readInt();
        this.animationID = this.readByte();
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
         * Find the entity to apply the animation on.
         */
        entity = (MCEntity) this.getWorld().findObjectByID(this.entityID);

        if (entity != null) {
            entity.setLastAnimation(this.animationID);
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
        builder.append(" | Animation = ");
        builder.append(MCAnimationType.animationForID(this.animationID));

        return builder.toString();
    }
}
