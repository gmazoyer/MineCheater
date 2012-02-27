package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.Collected;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class CollectItem extends Packet {
    private int collectedID;
    private int collectorID;

    public CollectItem(PacketsHandler handler) {
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
    public Object getData() {
        return new Collected(this.collectedID, this.collectorID);
    }
}
