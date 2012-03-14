package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class UpdateTileEntity extends Packet {
    private int x;
    private short y;
    private int z;
    private byte action;
    private int custom1;
    private int custom2;
    private int custom3;

    public UpdateTileEntity(PacketsHandler handler) {
        super(handler, (byte) 0x84);
    }

    @Override
    public void read() throws IOException {
        this.x = this.readInt();
        this.y = this.readShort();
        this.z = this.readInt();
        this.action = this.readByte();
        this.custom1 = this.readInt();
        this.custom2 = this.readInt();
        this.custom3 = this.readInt();
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

        builder.append("Tile : x = ");
        builder.append(this.x);
        builder.append(", y = ");
        builder.append(this.y);
        builder.append(", z = ");
        builder.append(this.z);
        builder.append(" | Action = ");
        builder.append(this.action);
        builder.append(" | Custom 1 = ");
        builder.append(this.custom1);
        builder.append(" | Custom 2 = ");
        builder.append(this.custom2);
        builder.append(" | Custom 3 = ");
        builder.append(this.custom3);

        return builder.toString();
    }
}
