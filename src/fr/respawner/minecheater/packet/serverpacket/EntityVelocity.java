package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.entity.MCEntity;
import fr.respawner.minecheater.structure.entity.MCVelocity;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class EntityVelocity extends Packet {
    private int entityID;
    private short velocityX;
    private short velocityY;
    private short velocityZ;

    private MCVelocity instance;

    public EntityVelocity(PacketsHandler handler) {
        super(handler, (byte) 0x1C);
    }

    @Override
    public void read() throws IOException {
        this.entityID = this.readInt();
        this.velocityX = this.readShort();
        this.velocityY = this.readShort();
        this.velocityZ = this.readShort();
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
         * Find the entity to set the velocity.
         */
        entity = (MCEntity) this.getWorld().findObjectByID(this.entityID);
        this.instance = new MCVelocity(this.entityID, this.velocityX,
                this.velocityY, this.velocityZ);

        if (entity != null) {
            entity.setVelocity(this.instance);
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
