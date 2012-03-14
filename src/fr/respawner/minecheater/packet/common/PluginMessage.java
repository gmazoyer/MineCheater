package fr.respawner.minecheater.packet.common;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class PluginMessage extends Packet {
    private String channel;
    private short length;
    private byte[] data;

    public PluginMessage(PacketsHandler handler) {
        super(handler, (byte) 0xFA);
    }

    @Override
    public void read() throws IOException {
        this.channel = this.readUnicodeString();
        this.length = this.readShort();
        this.data = this.readByteArray(this.length);
    }

    @Override
    public void write() throws IOException {
        this.writeUnicodeString(this.channel);
        this.writeShort(this.length);
        this.writeByteArray(this.data);
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

        builder.append("Channel = ");
        builder.append(this.channel);
        builder.append("| Length = ");
        builder.append(this.length);
        builder.append(" | Data = { ");
        for (byte b : this.data) {
            builder.append(b);
            builder.append(", ");
        }
        builder.replace(builder.length() - 2, builder.length(), " }");

        return builder.toString();
    }
}
