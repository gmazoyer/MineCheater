package fr.respawner.minecheater.metadata;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.respawner.minecheater.worker.IHandler;

public class Metadata {
    private final IHandler handler;
    private final List<Triplet<?, ?, ?>> metadata;

    public Metadata(IHandler handler) {
        this.handler = handler;
        this.metadata = new ArrayList<>();
    }

    /**
     * Read and return a byte.
     */
    protected final byte readByte() throws IOException {
        return this.handler.getInput().readByte();
    }

    /**
     * Read and return an unsigned byte as integer.
     */
    protected final int readUnsignedByte() throws IOException {
        return this.handler.getInput().readUnsignedByte();
    }

    /**
     * Read and return a short.
     */
    protected final short readShort() throws IOException {
        return this.handler.getInput().readShort();
    }

    /**
     * Read and return a integer.
     */
    protected final int readInt() throws IOException {
        return this.handler.getInput().readInt();
    }

    /**
     * Read and return a long.
     */
    protected final long readLong() throws IOException {
        return this.handler.getInput().readLong();
    }

    /**
     * Read and return a float.
     */
    protected final float readFloat() throws IOException {
        return this.handler.getInput().readFloat();
    }

    /**
     * Read and return a double.
     */
    protected final double readDouble() throws IOException {
        return this.handler.getInput().readDouble();
    }

    /**
     * Read and return a boolean.
     */
    protected final boolean readBoolean() throws IOException {
        return this.handler.getInput().readBoolean();
    }

    /**
     * Read and return a UTF-16 string.
     */
    protected final String readUnicodeString() throws IOException {
        final short length;
        final byte[] buffer;

        /*
         * Read the length of the string.
         */
        length = this.readShort();

        /*
         * UTF-16 uses 16-bit word so since we read bytes which have 8 bits
         * each, it doubles the length of the string to read.
         */
        buffer = new byte[length * 2];

        for (short s = 0; s < buffer.length; s++) {
            buffer[s] = this.handler.getInput().readByte();
        }

        return new String(buffer, "UTF-16");
    }

    public List<Triplet<?, ?, ?>> getMetadata() {
        return this.metadata;
    }

    public void parse() throws IOException {
        byte x;

        do {
            byte index, key;
            Object value;
            Triplet<Byte, Byte, Object> triplet;

            x = this.readByte();
            index = (byte) (x & 0x1F);
            key = (byte) (x >> 5);

            switch (key) {
            case 0:
                value = this.readByte();
                break;
            case 1:
                value = this.readShort();
                break;
            case 2:
                value = this.readInt();
                break;
            case 4:
                value = this.readFloat();
                break;
            case 5:
                value = new int[] { this.readShort(), this.readByte(),
                        this.readShort() };
                break;
            case 6:
                value = new int[] { this.readInt(), this.readInt(),
                        this.readInt() };
                break;
            default:
                value = null;
                break;
            }

            triplet = new Triplet<>(index, key, value);
            this.metadata.add(triplet);
        } while (x != 127);
    }

    @Override
    public String toString() {
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append("{ ");
        for (Triplet<?, ?, ?> data : this.metadata) {
            builder.append(data);
            builder.append(", ");
        }

        builder.replace(builder.length() - 2, builder.length(), " }");

        return builder.toString();
    }
}
