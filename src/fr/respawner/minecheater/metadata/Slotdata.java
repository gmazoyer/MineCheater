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
package fr.respawner.minecheater.metadata;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.respawner.minecheater.nbt.CompoundTag;
import fr.respawner.minecheater.nbt.ListTag;
import fr.respawner.minecheater.nbt.NBTInputStream;
import fr.respawner.minecheater.nbt.ShortTag;
import fr.respawner.minecheater.nbt.Tag;
import fr.respawner.minecheater.structure.inventory.Slot;
import fr.respawner.minecheater.worker.IHandler;

public final class Slotdata {
    private IHandler handler;
    private byte windowID;
    private short slotID;
    private Slot slot;

    public Slotdata(IHandler handler, byte windowID, short slotID) {
        this.handler = handler;
        this.windowID = windowID;
        this.slotID = slotID;

        try {
            this.slot = this.parse();
        } catch (IOException e) {
            e.printStackTrace();

            this.slot = null;
        }
    }

    private static boolean isEnchantable(short id) {
        return (((id >= 256) && (id <= 259)) || ((id >= 267) && (id <= 279))
                || ((id >= 283) && (id <= 286)) || ((id >= 290) && (id <= 294))
                || ((id >= 298) && (id <= 317)) || (id == 261) || (id == 359) || (id == 346));
    }

    private Slot parse() throws IOException {
        final short itemID, metadata, length;
        final byte count;
        final byte[] buffer;
        final NBTInputStream nbtReader;
        final CompoundTag rootTag;
        final List<Tag> enchantList;
        List<Pair<Short, Short>> enchantments;

        itemID = this.readShort();
        enchantments = null;

        if (itemID == -1) {
            /*
             * Slot is empty.
             */
            count = -1;
            metadata = -1;
        } else {
            count = this.readByte();
            metadata = this.readShort();

            if (isEnchantable(itemID)) {
                /*
                 * Length of the following array.
                 */
                length = this.readShort();

                if (length != -1) {
                    /*
                     * Compressed array using the NBT format.
                     */
                    enchantments = new ArrayList<>();
                    buffer = this.readByteArray(length);

                    /*
                     * This will go automatically deeper in the packet.
                     */
                    nbtReader = new NBTInputStream(new ByteArrayInputStream(
                            buffer));
                    rootTag = (CompoundTag) nbtReader.readTag();

                    /*
                     * Get the list of enchantments.
                     */
                    enchantList = ((ListTag) rootTag.getValue().get("ench"))
                            .getValue();

                    for (Tag tag : enchantList) {
                        final Pair<Short, Short> enchantment;
                        final CompoundTag enchant;
                        final ShortTag enchantID, enchantLevel;

                        /*
                         * Retrieve the values of ID and level for each
                         * enchantment.
                         */
                        enchant = (CompoundTag) tag;
                        enchantID = (ShortTag) enchant.getValue().get("id");
                        enchantLevel = (ShortTag) enchant.getValue().get("lvl");

                        enchantment = new Pair<Short, Short>(
                                enchantID.getValue(), enchantLevel.getValue());
                        enchantments.add(enchantment);
                    }
                }
            }
        }

        return new Slot(this.windowID, this.slotID, itemID, count, metadata,
                enchantments);
    }

    /**
     * Read and return a byte.
     */
    protected final byte readByte() throws IOException {
        return this.handler.getInput().readByte();
    }

    /**
     * Read and return a short.
     */
    protected final short readShort() throws IOException {
        return this.handler.getInput().readShort();
    }

    /**
     * Read and return an array of bytes.
     */
    protected final byte[] readByteArray(int length) throws IOException {
        final byte[] array;

        /*
         * Initialize the array.
         */
        array = new byte[length];

        /*
         * Fill it up.
         */
        for (int i = 0; i < length; i++) {
            array[i] = this.readByte();
        }

        return array;
    }

    public Slot getSlot() {
        return this.slot;
    }
}
