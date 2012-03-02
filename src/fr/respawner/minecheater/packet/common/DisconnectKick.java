package fr.respawner.minecheater.packet.common;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class DisconnectKick extends Packet {
    private String reason;

    public DisconnectKick(PacketsHandler handler) {
        super(handler, (byte) 0xFF);
    }

    @Override
    public void read() throws IOException {
        this.reason = this.readUnicodeString();
    }

    @Override
    public void write() throws IOException {
        this.writeByte(this.id);
        this.writeUnicodeString("Quitting");
        this.send();
    }

    @Override
    public void parse() {
        /*
         * We were kicked :'(
         */
        if (!this.reason.contains(STRING_DELIMITER)) {
            this.handler.println("Disconnected: " + this.reason);
            this.handler.stopHandler();
        }
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
        return this.reason;
    }
}
