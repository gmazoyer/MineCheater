package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.entity.MCPainting;
import fr.respawner.minecheater.worker.IHandler;

public final class SpawnPainting extends Packet {
    private int entityID;
    private String title;
    private int x;
    private int y;
    private int z;
    private int direction;

    public SpawnPainting(IHandler handler) {
        super(handler, (byte) 0x19);
    }

    @Override
    public void read() throws IOException {
        this.entityID = this.readInt();
        this.title = this.readUnicodeString();
        this.x = this.readInt();
        this.y = this.readInt();
        this.z = this.readInt();
        this.direction = this.readInt();
    }

    @Override
    public void write() throws IOException {
        /*
         * We don't write this packet.
         */
    }

    @Override
    public void process() {
        final MCPainting painting;

        painting = new MCPainting(this.entityID, this.title, this.x, this.y,
                this.z, this.direction);
        this.getWorld().addObject(painting);
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
        builder.append(" | Title = ");
        builder.append(this.title);
        builder.append(" | Location: x = ");
        builder.append(this.x);
        builder.append(", y = ");
        builder.append(this.y);
        builder.append(", z = ");
        builder.append(this.z);
        builder.append(", direction = ");
        builder.append(this.direction);

        return builder.toString();
    }
}
