package fr.respawner.minecheater.packet.clientpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class Player extends Packet {
    private boolean onGround;

    public Player(PacketsHandler handler, boolean onGround) {
        super(handler, (byte) 0x0A);

        this.onGround = onGround;
    }

    public Player(PacketsHandler handler) {
        this(handler, true);
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
    public void parse() {
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
    public Object getData() {
        return this.onGround ? "Walking or Swimming" : "Jumping or Falling";
    }
}
