package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.player.MCHealth;
import fr.respawner.minecheater.worker.IHandler;

public final class UpdateHealth extends Packet {
    private short health;
    private short food;
    private float foodSaturation;

    public UpdateHealth(IHandler handler) {
        super(handler, (byte) 0x08);
    }

    @Override
    public void read() throws IOException {
        this.health = this.readShort();
        this.food = this.readShort();
        this.foodSaturation = this.readFloat();
    }

    @Override
    public void write() throws IOException {
        /*
         * We don't write this packet.
         */
    }

    @Override
    public void process() {
        final MCHealth health;

        health = new MCHealth(this.health, this.food, this.foodSaturation);
        this.handler.println(health);
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

        builder.append("Health = ");
        builder.append(this.health);
        if (this.health == 0) {
            builder.append(" (dead)");
        } else if (this.health == 20) {
            builder.append(" (full HP)");
        }
        builder.append(" | Food = ");
        builder.append(this.food);
        builder.append(" | Food saturation = ");
        builder.append(this.foodSaturation);

        return builder.toString();
    }
}
