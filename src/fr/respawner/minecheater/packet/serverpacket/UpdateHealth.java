package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.Health;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class UpdateHealth extends Packet {
    private short health;
    private short food;
    private float foodSaturation;

    private Health instance;

    public UpdateHealth(PacketsHandler handler) {
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
        this.instance = new Health(this.health, this.food, this.foodSaturation);
        this.handler.println(this.instance);
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
