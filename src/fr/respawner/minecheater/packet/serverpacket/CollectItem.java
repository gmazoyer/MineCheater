package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.worker.IHandler;

public final class CollectItem extends Packet {
    private int collectedID;
    private int collectorID;

    public CollectItem(IHandler handler) {
        super(handler, (byte) 0x16);
    }

    @Override
    public void read() throws IOException {
        this.collectedID = this.readInt();
        this.collectorID = this.readInt();
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

        builder.append("Collected ID = ");
        builder.append(this.collectedID);
        builder.append(" | Collector ID = ");
        builder.append(this.collectorID);

        return builder.toString();
    }
}
