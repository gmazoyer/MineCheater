package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.entity.MCCharacter;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class UseBed extends Packet {
    private int entityID;
    private byte inBed;
    private int x;
    private byte y;
    private int z;

    public UseBed(PacketsHandler handler) {
        super(handler, (byte) 0x11);
    }

    @Override
    public void read() throws IOException {
        this.entityID = this.readInt();
        this.inBed = this.readByte();
        this.x = this.readInt();
        this.y = this.readByte();
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
        final MCCharacter character;

        /*
         * Find the character to set its equipment.
         */
        character = (MCCharacter) this.getWorld().findObjectByID(this.entityID);

        if (character != null) {
            character.setUseBed(this.inBed == 0 ? true : false);
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

        builder.append("Entity ID = ");
        builder.append(this.entityID);
        builder.append(" | Location: x = ");
        builder.append(this.x);
        builder.append(", y = ");
        builder.append(this.y);
        builder.append(", z = ");
        builder.append(this.z);
        builder.append(" | In bed = ");
        builder.append(this.inBed);

        return builder.toString();
    }
}
