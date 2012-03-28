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
import fr.respawner.minecheater.nbt.NBTInputStream;
import fr.respawner.minecheater.worker.IHandler;

public final class Slotdata {
    private final static short[] enchantables = new short[] { 0x103, 0x105,
            0x15A, 0x167, 0x10C, 0x10D, 0x10E, 0x10F, 0x122, 0x110, 0x111,
            0x112, 0x113, 0x123, 0x10B, 0x100, 0x101, 0x102, 0x124, 0x114,
            0x115, 0x116, 0x117, 0x125, 0x11B, 0x11C, 0x11D, 0x11E, 0x126,
            0x12A, 0x12B, 0x12C, 0x12D, 0x12E, 0x12F, 0x130, 0x131, 0x132,
            0x133, 0x134, 0x135, 0x136, 0x137, 0x138, 0x139, 0x13A, 0x13B,
            0x13C, 0x13D };

    private final IHandler handler;
    private final List<Triplet<Short, Byte, Short>> slotdata;

    public Slotdata(IHandler handler) {
        this.handler = handler;
        this.slotdata = new ArrayList<>();
    }

    private static boolean isEnchantable(short id) {
        for (short s : enchantables) {
            if (s == id) {
                return true;
            }
        }

        return false;
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

    public List<Triplet<Short, Byte, Short>> getSlotdata() {
        return this.slotdata;
    }

    public void parse() throws IOException {
        final short id, metadata, length;
        final byte count;
        final byte[] enchants;
        final NBTInputStream nbtReader;
        final CompoundTag nbtTag;
        final Triplet<Short, Byte, Short> triplet;

        id = this.readShort();
        if (id == -1) {
            /*
             * Slot is empty.
             */
            count = -1;
            metadata = -1;
        } else {
            count = this.readByte();
            metadata = this.readShort();

            if (isEnchantable(id)) {
                /*
                 * Length of the following array.
                 */
                length = this.readShort();

                if (length != -1) {
                    /*
                     * Compressed array using the NBT format.
                     */
                    enchants = this.readByteArray(length);

                    /*
                     * This will go automatically deeper in the packet.
                     */
                    nbtReader = new NBTInputStream(new ByteArrayInputStream(
                            enchants));
                    nbtTag = (CompoundTag) nbtReader.readTag();

                    /*
                     * TODO: what do we do with the NBT data now?
                     */
                }
            }
        }

        triplet = new Triplet<>(id, count, metadata);
        this.slotdata.add(triplet);
    }

    @Override
    public String toString() {
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append("{ ");
        for (Triplet<Short, Byte, Short> data : this.slotdata) {
            if (data.getIndex() == -1) {
                builder.append("Empty");
            } else {
                builder.append(data);
            }
            builder.append(", ");
        }

        builder.replace(builder.length() - 2, builder.length(), " }");

        return builder.toString();
    }
}
