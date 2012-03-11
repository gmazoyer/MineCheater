package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.entity.MCExperienceOrb;
import fr.respawner.minecheater.worker.IHandler;

public final class SpawnExperienceOrb extends Packet {
    private int entityID;
    private int x;
    private int y;
    private int z;
    private short count;

    public SpawnExperienceOrb(IHandler handler) {
        super(handler, (byte) 0x1A);
    }

    @Override
    public void read() throws IOException {
        this.entityID = this.readInt();
        this.x = this.readInt();
        this.y = this.readInt();
        this.z = this.readInt();
        this.count = this.readShort();
    }

    @Override
    public void write() throws IOException {
        /*
         * We don't write this packet.
         */
    }

    @Override
    public void process() {
        final MCExperienceOrb orb;

        orb = new MCExperienceOrb(this.entityID, this.x, this.y, this.z,
                this.count);
        this.handler.getWorld().addObject(orb);
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
        builder.append(" | Location: x = ");
        builder.append(this.x);
        builder.append(", y = ");
        builder.append(this.y);
        builder.append(", z = ");
        builder.append(this.z);
        builder.append(" | Count = ");
        builder.append(this.count);

        return builder.toString();
    }
}
