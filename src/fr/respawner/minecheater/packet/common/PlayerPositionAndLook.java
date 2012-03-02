package fr.respawner.minecheater.packet.common;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.player.PositionAndLook;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class PlayerPositionAndLook extends Packet {
    private double x;
    private double y;
    private double stance;
    private double z;
    private float yaw;
    private float pitch;
    private boolean onGround;

    private PositionAndLook instance;

    public PlayerPositionAndLook(PacketsHandler handler) {
        super(handler, (byte) 0x0D);
    }

    public PlayerPositionAndLook(PacketsHandler handler, boolean init) {
        this(handler);

        final PositionAndLook position;

        if (init) {
            this.x = 8.5;
            this.y = 65;
            this.stance = this.y + 1.62;
            this.z = 8.5;
            this.yaw = -180f;
            this.pitch = 0.0f;
            this.onGround = false;
        } else {
            position = this.getWorld().getPlayer().getPosition();

            if (position != null) {
                this.x = position.getX();
                this.y = position.getY();
                this.stance = position.getStance();
                this.z = position.getZ();
                this.yaw = position.getYaw();
                this.pitch = position.getPitch();
                this.onGround = position.isOnGround();
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
        this.writeByte(this.id);
        this.writeDouble(this.x);
        this.writeDouble(this.y);
        this.writeDouble(this.stance);
        this.writeDouble(this.z);
        this.writeFloat(this.yaw);
        this.writeFloat(this.pitch);
        this.writeBoolean(this.onGround);
    }

    @Override
    public void parse() {
        this.instance = new PositionAndLook(this.x, this.y, this.z,
                this.stance, this.yaw, this.pitch, this.onGround);
        this.getWorld().getPlayer().setPosition(this.instance);

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
    public Object getData() {
        return this.instance;
    }
}
