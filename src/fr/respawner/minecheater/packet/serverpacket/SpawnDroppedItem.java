package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.entity.MCPickup;
import fr.respawner.minecheater.structure.type.MCItemType;
import fr.respawner.minecheater.worker.IHandler;

public final class SpawnDroppedItem extends Packet {
    private int entityID;
    private short itemID;
    private byte count;
    private short damage;
    private int x;
    private int y;
    private int z;
    private byte rotation;
    private byte pitch;
    private byte roll;

    public SpawnDroppedItem(IHandler handler) {
        super(handler, (byte) 0x15);
    }

    @Override
    public void read() throws IOException {
        this.entityID = this.readInt();
        this.itemID = this.readShort();
        this.count = this.readByte();
        this.damage = this.readShort();
        this.x = this.readInt();
        this.y = this.readInt();
        this.z = this.readInt();
        this.rotation = this.readByte();
        this.pitch = this.readByte();
        this.roll = this.readByte();
    }

    @Override
    public void write() throws IOException {
        /*
         * We don't write this packet.
         */
    }

    @Override
    public void process() {
        final MCPickup pickup;

        pickup = new MCPickup(this.entityID, this.itemID, this.count,
                this.damage, this.x, this.y, this.z, this.rotation, this.pitch,
                this.roll);
        this.getWorld().addObject(pickup);
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
        builder.append(" | Item ID = ");
        builder.append(MCItemType.itemForID(this.itemID));
        builder.append(" | Count = ");
        builder.append(this.count);
        builder.append(" | Location: x = ");
        builder.append(this.x);
        builder.append(", y = ");
        builder.append(this.y);
        builder.append(", z = ");
        builder.append(this.z);
        builder.append(", rotation = ");
        builder.append(this.rotation);
        builder.append(", pitch = ");
        builder.append(this.pitch);
        builder.append(", roll = ");
        builder.append(this.roll);

        return builder.toString();
    }
}
