package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.world.MCTime;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class TimeUpdate extends Packet {
    private long time;

    private MCTime instance;

    public TimeUpdate(PacketsHandler handler) {
        super(handler, (byte) 0x04);
    }

    @Override
    public void read() throws IOException {
        this.time = this.readLong() % 24000;
    }

    @Override
    public void write() throws IOException {
        /*
         * We don't write this packet.
         */
    }

    @Override
    public void process() {
        this.instance = new MCTime(this.time);
        this.getWorld().setTime(this.instance);
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
