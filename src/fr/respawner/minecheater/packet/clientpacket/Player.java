package fr.respawner.minecheater.packet.clientpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.worker.IHandler;

public final class Player extends Packet {
    private boolean onGround;

    public Player(IHandler handler, boolean onGround) {
        super(handler, (byte) 0x0A);

        this.onGround = onGround;
    }

    public Player(IHandler handler) {
        this(handler, handler.getWorld().getPlayer().getLocation().isOnGround());
    }

    @Override
    public void read() throws IOException {
        /*
         * We don't receive this packet.
         */
    }

    @Override
    public void write() throws IOException {
        this.writeBoolean(this.onGround);
        this.send();
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
        return this.onGround ? "Walking or Swimming" : "Jumping or Falling";
    }
}
