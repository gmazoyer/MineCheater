package fr.respawner.minecheater.packet.common;

import java.io.IOException;

import fr.respawner.minecheater.Config;
import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class Handshake extends Packet {
    private String usernameAndHostOrHash;

    public Handshake(PacketsHandler handler) {
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
        return new LoginRequest(this.handler);
    }

    @Override
    public String getDataAsString() {
        switch (this.usernameAndHostOrHash) {
        case "-":
            return "Name authentication disabled.";
        case "+":
            return "Password authentication enabled.";
        default:
            return this.usernameAndHostOrHash;
        }
    }
}
