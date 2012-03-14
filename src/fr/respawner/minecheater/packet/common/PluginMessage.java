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
import fr.respawner.minecheater.worker.PacketsHandler;

public final class PluginMessage extends Packet {
    private String channel;
    private short length;
    private byte[] data;

    public PluginMessage(PacketsHandler handler) {
        super(handler, (byte) 0xFA);
    }

    @Override
    public void read() throws IOException {
        this.channel = this.readUnicodeString();
        this.length = this.readShort();
        this.data = this.readByteArray(this.length);
    }

    @Override
    public void write() throws IOException {
        this.writeUnicodeString(this.channel);
        this.writeShort(this.length);
        this.writeByteArray(this.data);
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
    public String getDataAsString() {
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append("Channel = ");
        builder.append(this.channel);
        builder.append("| Length = ");
        builder.append(this.length);
        builder.append(" | Data = { ");
        for (byte b : this.data) {
            builder.append(b);
            builder.append(", ");
        }
        builder.replace(builder.length() - 2, builder.length(), " }");

        return builder.toString();
    }
}
