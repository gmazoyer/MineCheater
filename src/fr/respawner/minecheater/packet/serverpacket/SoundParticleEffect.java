package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.SoundParticleType;
import fr.respawner.minecheater.worker.IHandler;

public final class SoundParticleEffect extends Packet {
    private int effectID;
    private int x;
    private byte y;
    private int z;
    private int data;

    public SoundParticleEffect(IHandler handler) {
        super(handler, (byte) 0x3D);
    }

    @Override
    public void read() throws IOException {
        this.effectID = this.readInt();
        this.x = this.readInt();
        this.y = this.readByte();
        this.z = this.readInt();
        this.data = this.readInt();
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

        builder.append("Effect = ");
        builder.append(SoundParticleType.effectForID(this.effectID));
        builder.append(" | Position: x = ");
        builder.append(this.x);
        builder.append(", y = ");
        builder.append(this.y);
        builder.append(", z = ");
        builder.append(this.z);
        builder.append(" | Data = ");
        builder.append(this.data);

        return builder.toString();
    }
}
