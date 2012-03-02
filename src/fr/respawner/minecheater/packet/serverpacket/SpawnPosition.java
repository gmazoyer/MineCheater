package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.world.MCSpawn;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class SpawnPosition extends Packet {
    private int x;
    private int y;
    private int z;

    private MCSpawn instance;

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
    public void parse() {
        this.instance = new MCSpawn(this.x, this.y, this.z);
        this.getWorld().setSpawn(this.instance);
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
        return this.instance;
    }
}
