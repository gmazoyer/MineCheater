package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.entity.MCEntity;
import fr.respawner.minecheater.structure.entity.MCRelativeMove;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class EntityRelativeMove extends Packet {
    private int entityID;
    private byte dX;
    private byte dY;
    private byte dZ;

    private MCRelativeMove instance;

    public EntityRelativeMove(PacketsHandler handler) {
        super(handler, (byte) 0x1F);
    }

    @Override
    public void read() throws IOException {
        this.entityID = this.readInt();
        this.dX = this.readByte();
        this.dY = this.readByte();
        this.dZ = this.readByte();
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
         * Find the entity to set its look and move.
         */
        entity = (MCEntity) this.getWorld().findObjectByID(this.entityID);
        this.instance = new MCRelativeMove(this.entityID, this.dX, this.dY,
                this.dZ);

        if (entity != null) {
            entity.setMove(this.dX, this.dY, this.dZ);
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
