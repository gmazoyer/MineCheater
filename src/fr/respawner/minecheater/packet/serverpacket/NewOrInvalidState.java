package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.world.State;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class NewOrInvalidState extends Packet {
    private byte reason;
    private byte mode;

    private State instance;

    public NewOrInvalidState(PacketsHandler handler) {
        super(handler, (byte) 0x46);
    }

    @Override
    public void read() throws IOException {
        this.reason = this.readByte();
        this.mode = this.readByte();
    }

    @Override
    public void write() throws IOException {
        /*
         * We don't write this packet.
         */
    }

    @Override
    public void parse() {
        this.instance = new State(this.reason, this.mode);
        this.handler.println(this.instance);
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
        return this.instance;
    }
}
