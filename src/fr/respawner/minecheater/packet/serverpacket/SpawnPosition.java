package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.math.Vector;
import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class SpawnPosition extends Packet {
    private int x;
    private int y;
    private int z;

    public SpawnPosition(PacketsHandler handler) {
        super(handler, (byte) 0x06);
    }

    @Override
    public void read() throws IOException {
        this.x = this.readInt();
        this.y = this.readInt();
        this.z = this.readInt();
    }

    @Override
    public void write() throws IOException {
        /*
         * We don't write this packet.
         */
    }

    @Override
    public void process() {
        final Vector spawn;

        spawn = new Vector(this.x, this.y, this.z);
        this.getWorld().setSpawn(spawn);
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
        builder.append(", y = ");
        builder.append(this.y);
        builder.append(", z = ");
        builder.append(this.z);

        return builder.toString();
    }
}
