package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.player.MCExperience;
import fr.respawner.minecheater.worker.IHandler;

public final class SetExperience extends Packet {
    private float experienceBar;
    private short level;
    private short total;

    public SetExperience(IHandler handler) {
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
        MCExperience experience;

        experience = this.getWorld().getPlayer().getExperience();

        if (experience == null) {
            experience = new MCExperience(this.experienceBar, this.level,
                    this.total);
            this.getWorld().getPlayer().setExperience(experience);
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
    public String getDataAsString() {
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append("Experience bar = ");
        builder.append(this.experienceBar);
        builder.append(" | Level = ");
        builder.append(this.level);
        builder.append(" | Total = ");
        builder.append(this.total);

        return builder.toString();
    }
}
