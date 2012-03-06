package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.world.MCTime;
import fr.respawner.minecheater.worker.IHandler;

public final class TimeUpdate extends Packet {
    private long time;

    public TimeUpdate(IHandler handler) {
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
        final MCTime time;

        time = new MCTime(this.time);
        this.getWorld().setTime(time);
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
        final String description;

        if (this.time == 0) {
            description = "Sunrise";
        } else if (this.time == 6000) {
            description = "Noon";
        } else if (this.time == 12000) {
            description = "Sunset";
        } else if (this.time == 18000) {
            description = "Midnight";
        } else if ((this.time > 0) && (this.time < 6000)) {
            description = "Morning";
        } else if ((this.time > 6000) && (this.time < 12000)) {
            description = "Afternoon";
        } else if (this.time > 12000) {
            description = "Night";
        } else {
            description = "What time is it (" + this.time + ")?";
        }

        return description;
    }
}
