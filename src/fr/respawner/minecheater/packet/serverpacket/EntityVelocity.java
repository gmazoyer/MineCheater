package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.math.VectorDouble;
import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.entity.MCEntity;
import fr.respawner.minecheater.worker.IHandler;

public final class EntityVelocity extends Packet {
    private int entityID;
    private short velocityX;
    private short velocityY;
    private short velocityZ;

    public EntityVelocity(IHandler handler) {
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
        VectorDouble velocity;

        /*
         * Find the entity to set the velocity.
         */
        entity = (MCEntity) this.getWorld().findObjectByID(this.entityID);

        if (entity != null) {
            velocity = entity.getVelocity();

            if (velocity == null) {
                velocity = new VectorDouble(this.velocityX, this.velocityY,
                        this.velocityZ);
                entity.setVelocity(velocity);
            } else {
                entity.getVelocity().setX(this.velocityX);
                entity.getVelocity().setY(this.velocityY);
                entity.getVelocity().setZ(this.velocityZ);
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
        builder.append(" | Velocity X = ");
        builder.append(this.velocityX);
        builder.append(" | Velocity Y = ");
        builder.append(this.velocityY);
        builder.append(" | Velocity Z = ");
        builder.append(this.velocityZ);

        return builder.toString();
    }
}
