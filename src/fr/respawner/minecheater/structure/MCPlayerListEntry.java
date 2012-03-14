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
package fr.respawner.minecheater.structure;

public final class MCPlayerListEntry {
    private String playerName;
    private boolean online;
    private short ping;

    public MCPlayerListEntry(String playerName, boolean online, short ping) {
        this.playerName = playerName;
        this.online = online;
        this.ping = ping;
    }

    public final String getPlayerName() {
        return this.playerName;
    }

    public final boolean isOnline() {
        return this.online;
    }

    public final void setOnline(boolean online) {
        this.online = online;
    }

    public final short getPing() {
        return this.ping;
    }

    public final void setPing(short ping) {
        this.ping = ping;
    }

    @Override
    public String toString() {
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
