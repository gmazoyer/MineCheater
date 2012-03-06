package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.worker.IHandler;

public class PreChunk extends Packet {
    private int x;
    private int z;
    private boolean mode;

    public PreChunk(IHandler handler) {
        super(handler, (byte) 0x32);
    }

    @Override
    public void read() throws IOException {
        this.x = this.readInt();
        this.z = this.readInt();
        this.mode = this.readBoolean();
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

        builder.append("Position: x = ");
        builder.append(this.x);
        builder.append(", z = ");
        builder.append(this.z);
        builder.append(" | Mode = ");
        builder.append(this.mode ? "Initializing chunk" : "Unloading chunk");

        return builder.toString();
    }
}
