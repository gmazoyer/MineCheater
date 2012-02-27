package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.entity.MCObjectVehicle;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class AddObjectVehicle extends Packet {
    private int entityID;
    private byte type;
    private int x;
    private int y;
    private int z;
    private int throwerID;
    private short speedX;
    private short speedY;
    private short speedZ;

    private MCObjectVehicle instance;

    public AddObjectVehicle(PacketsHandler handler) {
        super(handler, (byte) 0x17);
    }

    @Override
    public void read() throws IOException {
        this.entityID = this.readInt();
        this.type = this.readByte();
        this.x = this.readInt();
        this.z = this.readInt();
        this.y = this.readInt();
        this.throwerID = this.readInt();

        if (this.throwerID > 0) {
            this.speedX = this.readShort();
            this.speedY = this.readShort();
            this.speedZ = this.readShort();
        }
    }

    @Override
    public void write() throws IOException {
        /*
         * We don't write this packet.
         */
    }

    @Override
    public void process() {
        this.instance = new MCObjectVehicle(this.entityID, this.type, this.x,
                this.y, this.z, this.throwerID, this.speedX, this.speedY,
                this.speedZ);
        this.getWorld().addObject(this.instance);
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
