package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.entity.RemovePlayerEffect;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class RemoveEntityEffect extends Packet {
    private int entityID;
    private byte effectID;

    public RemoveEntityEffect(PacketsHandler handler) {
        super(handler, (byte) 0x2A);
    }

    @Override
    public void read() throws IOException {
        this.entityID = this.readInt();
        this.effectID = this.readByte();
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
    public Object getData() {
        // TODO Auto-generated method stub
        return new RemovePlayerEffect(this.entityID, this.effectID);
    }
}
