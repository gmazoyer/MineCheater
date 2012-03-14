package fr.respawner.minecheater.packet.common;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class UpdateSign extends Packet {
    private int x;
    private short y;
    private int z;
    private String firstLine;
    private String secondLine;
    private String thirdLine;
    private String fourthLine;

    public UpdateSign(PacketsHandler handler) {
        super(handler, (byte) 0x82);
    }

    @Override
    public void read() throws IOException {
        this.x = this.readInt();
        this.y = this.readShort();
        this.z = this.readInt();
        this.firstLine = this.readUnicodeString();
        this.secondLine = this.readUnicodeString();
        this.thirdLine = this.readUnicodeString();
        this.fourthLine = this.readUnicodeString();
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
    public String getDataAsString() {
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append("Location: x = ");
        builder.append(this.x);
        builder.append(", y = ");
        builder.append(this.y);
        builder.append(", z = ");
        builder.append(this.z);
        builder.append(" | Line 1 = ");
        builder.append(this.firstLine);
        builder.append(" | Line 2 = ");
        builder.append(this.secondLine);
        builder.append(" | Line 3 = ");
        builder.append(this.thirdLine);
        builder.append(" | Line 4 = ");
        builder.append(this.fourthLine);

        return builder.toString();
    }
}
