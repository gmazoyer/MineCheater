/*
 * Copyright (c) 2012 Guillaume Mazoyer
 * 
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.MCPlayerListEntry;
import fr.respawner.minecheater.worker.IHandler;

public final class PlayerListItem extends Packet {
    private String playerName;
    private boolean online;
    private short ping;

    public PlayerListItem(IHandler handler) {
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
