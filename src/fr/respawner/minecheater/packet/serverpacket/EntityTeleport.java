package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.entity.MCEntity;
import fr.respawner.minecheater.structure.entity.MCTeleport;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class EntityTeleport extends Packet {
    private int entityID;
    private int x;
    private int y;
    private int z;
    private byte yaw;
    private byte pitch;

    private MCTeleport instance;

    public EntityTeleport(PacketsHandler handler) {
        super(handler, (byte) 0x22);
    }

    @Override
    public void read() throws IOException {
        this.entityID = this.readInt();
        this.x = this.readInt();
        this.y = this.readInt();
        this.z = this.readInt();
        this.yaw = this.readByte();
        this.pitch = this.readByte();
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
         * Find the entity to set the new position.
         */
        entity = (MCEntity) this.getWorld().findObjectByID(this.entityID);
        this.instance = new MCTeleport(this.entityID, this.x, this.y, this.z,
                this.yaw, this.pitch);

        if (entity != null) {
            entity.setCoordinates(this.x, this.y, this.z);
            entity.setYaw(this.yaw);
            entity.setPitch(this.pitch);
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
