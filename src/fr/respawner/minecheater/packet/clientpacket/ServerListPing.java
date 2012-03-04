package fr.respawner.minecheater.packet.clientpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class ServerListPing extends Packet {
    public ServerListPing(PacketsHandler handler) {
        super(handler, (byte) 0xFE);
    }

    @Override
    public void read() throws IOException {
        /*
         * We don't receive this packet.
         */
    }

    @Override
    public void write() throws IOException {
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
        return null;
    }
}
