package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.entity.MCCharacter;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class NamedEntitySpawn extends Packet {
    private int entityID;
    private String name;
    private int x;
    private int y;
    private int z;
    private byte rotation;
    private byte pitch;
    private short item;

    public NamedEntitySpawn(PacketsHandler handler) {
        super(handler, (byte) 0x14);
    }

    @Override
    public void read() throws IOException {
        this.entityID = this.readInt();
        this.name = this.readUnicodeString();
        this.x = this.readInt();
        this.y = this.readInt();
        this.z = this.readInt();
        this.rotation = this.readByte();
        this.pitch = this.readByte();
        this.item = this.readShort();
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

        character = new MCCharacter(this.entityID, this.name, this.x, this.y,
                this.z, this.rotation, this.pitch, this.item);
        this.getWorld().addObject(character);
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
        builder.append(" | Name = ");
        builder.append(this.name);
        builder.append(" | Location: x = ");
        builder.append(this.x);
        builder.append(", y = ");
        builder.append(this.y);
        builder.append(", z = ");
        builder.append(this.z);
        builder.append(", rotation = ");
        builder.append(this.rotation);
        builder.append(", pitch = ");
        builder.append(this.pitch);
        builder.append(" | Item = ");
        builder.append(this.item == 0 ? "Nothing" : this.item);

        return builder.toString();
    }
}
