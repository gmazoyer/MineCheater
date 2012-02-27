package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.MCExperience;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class Experience extends Packet {
    private float experienceBar;
    private short level;
    private short total;

    private MCExperience instance;

    public Experience(PacketsHandler handler) {
        super(handler, (byte) 0x2B);
    }

    @Override
    public void read() throws IOException {
        this.experienceBar = this.readFloat();
        this.level = this.readShort();
        this.total = this.readShort();
    }

    @Override
    public void write() throws IOException {
        /*
         * We don't write this packet.
         */
    }

    @Override
    public void process() {
        final MCExperience experience;

        experience = this.getWorld().getExperience();
        this.instance = new MCExperience(this.experienceBar, this.level,
                this.total);

        if (experience == null) {
            this.getWorld().setExperience(this.instance);
        } else {
            experience.setExperienceBar(this.experienceBar);
            experience.setLevel(this.level);
            experience.setTotal(this.total);
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
    public Object getData() {
        return this.instance;
    }
}
