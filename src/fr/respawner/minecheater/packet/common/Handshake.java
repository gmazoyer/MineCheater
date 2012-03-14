package fr.respawner.minecheater.packet.common;

import java.io.IOException;

import fr.respawner.minecheater.Config;
import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.worker.IHandler;

public final class Handshake extends Packet {
    private String usernameAndHostOrHash;

    public Handshake(IHandler handler) {
        super(handler, (byte) 0x02);

        this.usernameAndHostOrHash = String.format("%s;%s:%d", Config.USERNAME,
                Config.SERVER_HOST, Config.SERVER_PORT);
    }

    @Override
    public void read() throws IOException {
        this.usernameAndHostOrHash = this.readUnicodeString();
    }

    @Override
    public void write() throws IOException {
        this.writeUnicodeString(this.usernameAndHostOrHash);
    }

    @Override
    public void process() {
        /*
         * Nothing to do.
         */
    }

    @Override
    public Packet response() {
        return new LoginRequest(this.handler);
    }

    @Override
    public String getDataAsString() {
        return (this.usernameAndHostOrHash.equals("-") ? "Name authentication enabled."
                : this.usernameAndHostOrHash);
    }
}
