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

import fr.respawner.minecheater.metadata.Slotdata;
import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.inventory.MCInventory;
import fr.respawner.minecheater.structure.inventory.Slot;
import fr.respawner.minecheater.worker.IHandler;

public final class SetWindowItems extends Packet {
    private byte windowID;
    private short count;
    private Slotdata[] slots;

    public SetWindowItems(IHandler handler) {
        super(handler, SET_WINDOW_ITEMS);
    }

    @Override
    public void read() throws IOException {
        this.windowID = this.readByte();
        this.count = this.readShort();
        this.slots = new Slotdata[this.count];

        for (short i = 0; i < count; i++) {
            slots[i] = new Slotdata(this.handler, windowID, i);
        }
    }

    @Override
    public void write() throws IOException {
        /*
         * We don't write this packet.
         */
    }

    @Override
    public void process() {
        final MCInventory inventory;

        /*
         * Operation on the player inventory.
         */
        if (this.windowID == 0) {
            /*
             * Find the inventory.
             */
            inventory = this.getWorld().getPlayer().getInventory();

            /*
             * Update all slots.
             */
            for (short i = 0; i < this.count; i++) {
                final Slot slot;

                slot = inventory.getSlot(i);

                if (slot == null) {
                    /*
                     * Add a new slot.
                     */
                    inventory.getSlots().add(this.slots[i].getSlot());
                } else {
                    /*
                     * Update the existing slot.
                     */
                    slot.update(this.slots[i].getSlot());
                }
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

        builder.append("Window ID = ");
        builder.append(this.windowID);
        builder.append(" | Slots = ");
        builder.append(this.slots);

        return builder.toString();
    }
}
