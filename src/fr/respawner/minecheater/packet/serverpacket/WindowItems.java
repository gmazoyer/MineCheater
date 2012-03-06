package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.metadata.Slotdata;
import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.worker.IHandler;

public final class WindowItems extends Packet {
    private byte windowID;
    private short count;
    private Slotdata slots;

    public WindowItems(IHandler handler) {
        super(handler, (byte) 0x68);
    }

    @Override
    public void read() throws IOException {
        this.windowID = this.readByte();
        this.count = this.readShort();
        this.slots = new Slotdata(this.handler);
        this.slots.parse(this.count);
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
        builder.append(" | Slots = ");
        builder.append(this.slots);

        return builder.toString();
    }
}
