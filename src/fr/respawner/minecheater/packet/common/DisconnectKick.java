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
package fr.respawner.minecheater.packet.common;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.packet.PacketIdentifier;
import fr.respawner.minecheater.worker.IHandler;

public final class DisconnectKick extends Packet {
    private String reason;

    public DisconnectKick(IHandler handler) {
        super(handler, PacketIdentifier.DISCONNECT_KICK);
    }

    @Override
    public void read() throws IOException {
        this.reason = this.readUnicodeString();
    }

    @Override
    public void write() throws IOException {
        this.reason = "Quitting";

        this.writeUnicodeString(this.reason);
    }

    @Override
    public void process() {
        final String[] serverInfos;

        /*
         * We were kicked :'(
         */
        if (!this.reason.contains(STRING_DELIMITER)) {
            this.handler.println("Disconnected: " + this.reason);
            this.handler.stopHandler();
        } else {
            serverInfos = this.reason.split(Packet.STRING_DELIMITER);

            this.handler.println();
            this.handler.println("  ** Server name:        " + serverInfos[0]);
            this.handler.println("  ** Number of players:  " + serverInfos[1]);
            this.handler.println("  ** Maximum of players: " + serverInfos[2]);
            this.handler.println();
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
        return this.reason;
    }
}
