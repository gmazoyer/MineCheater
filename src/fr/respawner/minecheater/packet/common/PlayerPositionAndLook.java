package fr.respawner.minecheater.packet.common;

import java.io.IOException;

import fr.respawner.minecheater.math.Location;
import fr.respawner.minecheater.math.Rotation;
import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.worker.IHandler;

public final class PlayerPositionAndLook extends Packet {
    private double x;
    private double y;
    private double stance;
    private double z;
    private float yaw;
    private float pitch;
    private boolean onGround;

    public PlayerPositionAndLook(IHandler handler) {
        super(handler, (byte) 0x0D);
    }

    public PlayerPositionAndLook(IHandler handler, boolean init) {
        this(handler);

        final Location location;
        final Rotation rotation;

        if (init) {
            this.x = 8.5;
            this.y = 65;
            this.stance = this.y + 1.62;
            this.z = 8.5;
            this.yaw = -180f;
            this.pitch = 0.0f;
            this.onGround = false;
        } else {
            location = this.getWorld().getPlayer().getLocation();
            rotation = this.getWorld().getPlayer().getRotation();

            if (location != null) {
                this.x = location.getX();
                this.y = location.getY();
                this.stance = location.getStance();
                this.z = location.getZ();
                this.onGround = location.isOnGround();
            }

            if (rotation != null) {
                this.yaw = rotation.getX();
                this.pitch = rotation.getY();
            }
        }
    }

    @Override
    public void read() throws IOException {
        this.x = this.readDouble();
        this.stance = this.readDouble();
        this.y = this.readDouble();
        this.z = this.readDouble();
        this.yaw = this.readFloat();
        this.pitch = this.readFloat();
        this.onGround = this.readBoolean();
    }

    @Override
    public void write() throws IOException {
        /*
         * From Client to Server, 'stance' is sent after 'y'.
         */
        this.writeDouble(this.x);
        this.writeDouble(this.y);
        this.writeDouble(this.stance);
        this.writeDouble(this.z);
        this.writeFloat(this.yaw);
        this.writeFloat(this.pitch);
        this.writeBoolean(this.onGround);
    }

    @Override
    public void process() {
        Location location;
        Rotation rotation;

        location = this.getWorld().getPlayer().getLocation();
        rotation = this.getWorld().getPlayer().getRotation();

        if (location != null) {
            location.setPosition(x, y, z);
            location.setOnGround(this.onGround);
        } else {
            location = new Location(this.x, this.y, this.z);
            location.setStance(this.stance);
            location.setOnGround(this.onGround);
            this.getWorld().getPlayer().setLocation(location);
        }

        if (rotation != null) {
            rotation.setX(this.yaw);
            rotation.setY(this.pitch);
        } else {
            rotation = new Rotation(this.yaw, this.pitch);
            this.getWorld().getPlayer().setRotation(rotation);
        }

        if (!this.getWorld().isLoggedIn()) {
            this.getWorld().setLoggedIn(true);
        }
    }

    @Override
    public Packet response() {
        /*
         * We just send the exact same packet.
         */
        return this;
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
        builder.append(", yaw = ");
        builder.append(this.yaw);
        builder.append(", pitch = ");
        builder.append(this.pitch);
        builder.append(" | ");
        builder.append(this.onGround ? "Walking or Swimming"
                : "Jumping or Falling");

        return builder.toString();
    }
}
