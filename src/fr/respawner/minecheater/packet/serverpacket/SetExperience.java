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
import fr.respawner.minecheater.structure.player.MCExperience;
import fr.respawner.minecheater.worker.IHandler;

public final class SetExperience extends Packet {
    private float experienceBar;
    private short level;
    private short total;

    public SetExperience(IHandler handler) {
        super(handler, SET_EXPERIENCE);
    }

    @Override
    public void read() throws IOException {
        this.experienceBar = this.readFloat();
        this.level = this.readShort();
        this.total = this.readShort();
    }

    @Override
    public void write() throws IOException {
        /*
         * We don't write this packet.
         */
    }

    @Override
    public void process() {
        MCExperience experience;

        experience = this.getWorld().getPlayer().getExperience();

        if (experience == null) {
            experience = new MCExperience(this.experienceBar, this.level,
                    this.total);
            this.getWorld().getPlayer().setExperience(experience);
        } else {
            experience.setExperienceBar(this.experienceBar);
            experience.setLevel(this.level);
            experience.setTotal(this.total);
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

        builder.append("Experience bar = ");
        builder.append(this.experienceBar);
        builder.append(" | Level = ");
        builder.append(this.level);
        builder.append(" | Total = ");
        builder.append(this.total);

        return builder.toString();
    }
}
