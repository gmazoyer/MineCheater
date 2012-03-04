package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.MCPlayerListEntry;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class PlayerListItem extends Packet {
    private String playerName;
    private boolean online;
    private short ping;

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
        MCPlayerListEntry entry;

        entry = this.getWorld().findPeopleByName(this.playerName);

        if (entry == null) {
            entry = new MCPlayerListEntry(this.playerName, this.online,
                    this.ping);
            this.getWorld().addPeople(entry);
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
    public String getDataAsString() {
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append("Player name = ");
        builder.append(this.playerName);
        builder.append(" | ");
        builder.append(this.online ? "Online" : "Not online");
        builder.append(" | Ping = ");
        builder.append(this.ping);

        return builder.toString();
    }
}
