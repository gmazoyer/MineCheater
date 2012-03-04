package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.metadata.Slotdata;
import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class SetSlot extends Packet {
    private byte windowID;
    private short slot;
    private Slotdata data;

    public SetSlot(PacketsHandler handler) {
        super(handler, (byte) 0x67);
    }

    @Override
    public void read() throws IOException {
        this.windowID = this.readByte();
        this.slot = this.readShort();
        this.data = new Slotdata(this.handler);
        this.data.parse((short) 1);
    }

    @Override
    public void write() throws IOException {
        /*
         * We don't write this packet.
         */
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

        builder.append("Window ID = ");
        builder.append(this.windowID);
        builder.append(" | Slot = ");
        builder.append(this.slot);
        builder.append(" | Content = ");
        builder.append(this.data);

        return builder.toString();
    }
}
