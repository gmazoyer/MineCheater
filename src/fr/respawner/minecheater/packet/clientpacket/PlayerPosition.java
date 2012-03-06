package fr.respawner.minecheater.packet.clientpacket;

import java.io.IOException;

import fr.respawner.minecheater.math.Location;
import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class PlayerPosition extends Packet {
    private double x;
    private double y;
    private double stance;
    private double z;
    private boolean onGround;

    public PlayerPosition(PacketsHandler handler) {
        super(handler, (byte) 0x0B);

        final Location location;

        location = this.getWorld().getPlayer().getLocation();

        if (location != null) {
            this.x = location.getX();
            this.y = location.getY();
            this.stance = location.getStance();
            this.z = location.getZ();
            this.onGround = location.isOnGround();
        }
    }

    @Override
    public void read() throws IOException {
        /*
         * We don't receive this packet.
         */
    }

    @Override
    public void write() throws IOException {
        this.writeDouble(this.x);
        this.writeDouble(this.y);
        this.writeDouble(this.stance);
        this.writeDouble(this.z);
        this.writeBoolean(this.onGround);
    }

    @Override
    public void process() {
        /*
         * Nothing to do.
         */
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

        builder.append("Location: x = ");
        builder.append(this.x);
        builder.append(", y = ");
        builder.append(this.y);
        builder.append(", z = ");
        builder.append(this.z);
        builder.append(", stance = ");
        builder.append(this.stance);
        builder.append(" | ");
        builder.append(this.onGround ? "Walking or Swimming"
                : "Jumping or Falling");

        return builder.toString();
    }
}
