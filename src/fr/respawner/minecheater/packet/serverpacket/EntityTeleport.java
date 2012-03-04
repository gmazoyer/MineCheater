package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.math.Location;
import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.entity.MCEntity;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class EntityTeleport extends Packet {
    private int entityID;
    private int x;
    private int y;
    private int z;
    private byte yaw;
    private byte pitch;

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
    public void process() {
        final MCEntity entity;
        Location location;

        /*
         * Find the entity to set the new position.
         */
        entity = (MCEntity) this.getWorld().findObjectByID(this.entityID);

        if (entity != null) {
            location = entity.getLocation();

            if (location != null) {
                location.setRotation(this.yaw, this.pitch);
            } else {
                location = new Location(this.x, this.y, this.z);
                location.setRotation(this.yaw, this.pitch);
                entity.setLocation(location);
            }
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
        builder.append(" | Position: x = ");
        builder.append(this.x);
        builder.append(", y = ");
        builder.append(this.y);
        builder.append(", z = ");
        builder.append(this.z);
        builder.append(", yaw = ");
        builder.append(this.yaw);
        builder.append(", pitch = ");
        builder.append(this.pitch);

        return builder.toString();
    }
}
