package fr.respawner.minecheater.packet.common;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.worker.IHandler;

public final class KeepAlive extends Packet {
    private int keepAliveID;

    public KeepAlive(IHandler handler) {
        super(handler, (byte) 0x00);

        this.keepAliveID = 0;
    }

    @Override
    public void read() throws IOException {
        this.keepAliveID = this.readInt();
    }

    @Override
    public void write() throws IOException {
        this.writeInt(this.keepAliveID);
    }

    @Override
    public void process() {
        /*
         * Nothing to do.
         */
    }

    @Override
    public Packet response() {
        final KeepAlive response;

        response = new KeepAlive(this.handler);
        response.keepAliveID = this.keepAliveID;

        return response;
    }

    @Override
    public String getDataAsString() {
        return Integer.toString(this.keepAliveID);
    }
}
