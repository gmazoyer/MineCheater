package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.world.MCState;
import fr.respawner.minecheater.worker.IHandler;

public final class NewOrInvalidState extends Packet {
    private byte reason;
    private byte mode;

    public NewOrInvalidState(IHandler handler) {
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
    public void process() {
        final MCState state;

        state = new MCState(this.reason, this.mode);
        this.handler.println(state);
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
        switch (this.reason) {
        case 0:
            return "Invalid bed use";

        case 1:
            return "Begin raining";

        case 2:
            return "End raining";

        case 3:
            return "Game mode = " + (this.mode == 0 ? "survival" : "creative");

        case 4:
            return "Enter credits";

        default:
            return "Unknown";
        }
    }
}
