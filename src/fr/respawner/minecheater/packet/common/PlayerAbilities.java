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
import fr.respawner.minecheater.worker.PacketsHandler;

public final class PlayerAbilities extends Packet {
    private boolean invulnerability;
    private boolean isFlying;
    private boolean canFly;
    private boolean instantDestroy;

    public PlayerAbilities(PacketsHandler handler) {
        super(handler, PacketIdentifier.PLAYER_ABILITIES);
    }

    @Override
    public void read() throws IOException {
        this.invulnerability = this.readBoolean();
        this.isFlying = this.readBoolean();
        this.canFly = this.readBoolean();
        this.instantDestroy = this.readBoolean();
    }

    @Override
    public void write() throws IOException {
        this.writeBoolean(this.invulnerability);
        this.writeBoolean(this.isFlying);
        this.writeBoolean(this.canFly);
        this.writeBoolean(this.instantDestroy);
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

        builder.append("Invulnerability = ");
        builder.append(this.invulnerability);
        builder.append(" | Is flying = ");
        builder.append(this.isFlying);
        builder.append(" | Can fly = ");
        builder.append(this.canFly);
        builder.append(" | Instant destroy = ");
        builder.append(this.instantDestroy);

        return builder.toString();
    }

}
