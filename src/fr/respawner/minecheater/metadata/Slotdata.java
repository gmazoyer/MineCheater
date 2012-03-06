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

    /*
     * TODO: support enchanted items.
     */
    public void parse(short slots) throws IOException {
        for (short s = 0; s < slots; s++) {
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
            } else if (!isEnchantable(id)) {
                /*
                 * Non enchantable item.
                 */
                count = this.readByte();
                metadata = this.readShort();
            } else {
                length = this.readShort();
                enchants = this.readByteArray(length);

                /*
                 * This will go automatically deeper in the packet.
                 */
                nbtReader = new NBTInputStream(new ByteArrayInputStream(
                        enchants));
                nbtTag = (CompoundTag) nbtReader.readTag();

                /*
                 * Slot is empty.
                 */
                count = -1;
                metadata = -1;
            }

            triplet = new Triplet<>(id, count, metadata);
            this.slotdata.add(triplet);
        }
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
