package fr.respawner.minecheater.packet.clientpacket;

import java.io.IOException;

import fr.respawner.minecheater.math.Location;
import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class PlayerLook extends Packet {
    private float yaw;
    private float pitch;
    private boolean onGround;

    public PlayerLook(PacketsHandler handler) {
        super(handler, (byte) 0x0C);

        final Location location;

        location = this.getWorld().getPlayer().getLocation();

        if (location != null) {
            this.yaw = location.getYaw();
            this.pitch = location.getPitch();
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
        this.writeFloat(this.yaw);
        this.writeFloat(this.pitch);
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

        builder.append("Look: yaw = ");
        builder.append(this.yaw);
        builder.append(", pitch = ");
        builder.append(this.pitch);
        builder.append(" | ");
        builder.append(this.onGround ? "Walking or Swimming"
                : "Jumping or Falling");

        return builder.toString();
    }
}
