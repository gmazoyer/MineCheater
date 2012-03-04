package fr.respawner.minecheater.packet.common;

import java.io.IOException;

import fr.respawner.minecheater.Config;
import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class Handshake extends Packet {
    /*
     * Client -> Server field
     */
    private String usernameAndHost;

    /*
     * Server -> Client field
     */
    private String hash;

    public Handshake(PacketsHandler handler) {
        super(handler, (byte) 0x02);

        this.usernameAndHost = String.format("%s;%s:%d", Config.USERNAME,
                Config.SERVER_HOST, Config.SERVER_PORT);
    }

    @Override
    public void read() throws IOException {
        this.hash = this.readUnicodeString();
    }

    @Override
    public void write() throws IOException {
        this.writeUnicodeString(this.usernameAndHost);
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
        if (this.hash == null) {
            return this.usernameAndHost;
        } else {
            switch (this.hash) {
            case "-":
                return "Name authentication disabled.";
            case "+":
                return "Password authentication enabled.";
            default:
                return this.hash;
            }
        }
    }
}
