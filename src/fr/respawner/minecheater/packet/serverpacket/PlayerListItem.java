package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.MCPlayerListEntry;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class PlayerListItem extends Packet {
    private String playerName;
    private boolean online;
    private short ping;

    private MCPlayerListEntry instance;

    public PlayerListItem(PacketsHandler handler) {
        super(handler, (byte) 0xC9);
    }

    @Override
    public void read() throws IOException {
        this.playerName = this.readUnicodeString();
        this.online = this.readBoolean();
        this.ping = this.readShort();
    }

    @Override
    public void write() throws IOException {
        /*
         * We don't write this packet.
         */
    }

    @Override
    public void process() {
        final MCPlayerListEntry entry;

        entry = this.getWorld().findPeopleByName(this.playerName);
        this.instance = new MCPlayerListEntry(this.playerName, this.online,
                this.ping);

        if (entry == null) {
            this.getWorld().addPeople(this.instance);
        } else {
            if (this.online) {
                entry.setPing(this.ping);
            } else {
                this.getWorld().removePeople(entry);
            }
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
