package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.metadata.Metadata;
import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.entity.MCMob;
import fr.respawner.minecheater.structure.entity.MCMob.MobType;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class MobSpawn extends Packet {
    private int entityID;
    private byte type;
    private int x;
    private int y;
    private int z;
    private byte yaw;
    private byte pitch;
    private byte headYaw;
    private Metadata metadata;

    public MobSpawn(PacketsHandler handler) {
        super(handler, (byte) 0x18);
    }

    @Override
    public void read() throws IOException {
        this.entityID = this.readInt();
        this.type = this.readByte();
        this.x = this.readInt();
        this.y = this.readInt();
        this.z = this.readInt();
        this.yaw = this.readByte();
        this.pitch = this.readByte();
        this.headYaw = this.readByte();
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
        final MCMob mob;

        mob = new MCMob(this.entityID, this.type, this.x, this.y, this.z,
                this.yaw, this.pitch, this.headYaw, this.metadata);
        this.getWorld().addObject(mob);
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
        builder.append(" | Type = ");
        builder.append(MobType.mobForID(this.type));
        builder.append(" | Location: x = ");
        builder.append(this.x);
        builder.append(", y = ");
        builder.append(this.y);
        builder.append(", z = ");
        builder.append(this.z);
        builder.append(", yaw = ");
        builder.append(this.yaw);
        builder.append(", pitch = ");
        builder.append(this.pitch);
        builder.append(", head yaw = ");
        builder.append(this.headYaw);
        builder.append(" | Metadata = ");
        builder.append(this.metadata);

        return builder.toString();
    }
}
