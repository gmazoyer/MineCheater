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
        this.writeUnicodeString("Quitting");
        this.send();
    }

    @Override
    public void process() {
        final String[] serverInfos;

        /*
         * We were kicked :'(
         */
        if (!this.reason.contains(STRING_DELIMITER)) {
            this.handler.println("Disconnected: " + this.reason);
            this.handler.stopHandler();
        } else {
            serverInfos = this.reason.split(Packet.STRING_DELIMITER);

            this.handler.println();
            this.handler.println("  ** Server name:        " + serverInfos[0]);
            this.handler.println("  ** Number of players:  " + serverInfos[1]);
            this.handler.println("  ** Maximum of players: " + serverInfos[2]);
            this.handler.println();
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
    public String getDataAsString() {
        return this.reason;
    }
}
