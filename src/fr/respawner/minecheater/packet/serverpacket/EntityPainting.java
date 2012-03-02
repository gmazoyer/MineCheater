package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.entity.MCPainting;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class EntityPainting extends Packet {
    private int entityID;
    private String title;
    private int x;
    private int y;
    private int z;
    private int direction;

    private MCPainting instance;

    public EntityPainting(PacketsHandler handler) {
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
    public void parse() {
        this.instance = new MCPainting(this.entityID, this.title, this.x,
                this.y, this.z, this.direction);
        this.getWorld().addObject(this.instance);
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
