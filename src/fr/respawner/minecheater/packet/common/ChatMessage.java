package fr.respawner.minecheater.packet.common;

import java.io.IOException;

import fr.respawner.minecheater.Config;
import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class ChatMessage extends Packet {
    private String message;

    public ChatMessage(PacketsHandler handler) {
        super(handler, (byte) 0x03);
    }

    public ChatMessage(PacketsHandler handler, String message) {
        this(handler);

        this.message = message;
    }

    @Override
    public void read() throws IOException {
        this.message = this.readUnicodeString();
    }

    @Override
    public void write() throws IOException {
        this.writeByte(this.id);
        this.writeUnicodeString(this.message);
        this.send();
    }

    @Override
    public void process() {
        final String prefix;

        prefix = this.message.startsWith("<" + Config.USERNAME + ">") ? "Message sent: "
                : "Message received: ";

        /*
         * Ignore the first 2 characters if needed.
         */
        if (this.message.contains(Packet.STRING_DELIMITER)) {
            this.message = this.message.substring(2);
        }

        this.handler.println(prefix + this.message);
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
        return this.message;
    }
}
