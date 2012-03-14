package fr.respawner.minecheater.packet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;

import fr.respawner.minecheater.World;
import fr.respawner.minecheater.worker.IHandler;

public abstract class Packet {
    public static final String LINE_SEPARATOR;
    public static final String STRING_DELIMITER;

    protected final ByteArrayOutputStream packetReceived;
    protected final ByteArrayOutputStream packetToSend;
    protected final IHandler handler;
    protected final byte id;

    protected PacketAction action;

    static {
        LINE_SEPARATOR = System.lineSeparator();
        STRING_DELIMITER = new String(new char[] { 0xA7 });
    }

    public enum PacketAction {
        READING, WRITING;
    }

    public Packet(IHandler handler, byte id) {
        this.handler = handler;
        this.packetReceived = new ByteArrayOutputStream();
        this.packetToSend = new ByteArrayOutputStream();
        this.action = PacketAction.READING;
        this.id = id;

        this.packetReceived.write(this.id);
        this.packetToSend.write(this.id);
    }

    /**
     * Return the buffer that is currently used.
     */
    private ByteArrayOutputStream getUsedBuffer() {
        return (this.action == PacketAction.READING) ? this.packetReceived
                : this.packetToSend;
    }

    /**
     * Read and return a byte.
     */
    protected final byte readByte() throws IOException {
        final byte read;

        read = this.handler.getInput().readByte();

        this.packetReceived.write(read);

        return read;
    }

    /**
     * Read and return an unsigned byte as integer.
     */
    protected final int readUnsignedByte() throws IOException {
        final int read;
        final byte[] bytes;

        read = this.handler.getInput().readUnsignedByte();
        bytes = ByteBuffer.allocate(4).putInt(read).array();

        this.packetReceived.write(bytes);

        return read;
    }

    /**
     * Read and return a short.
     */
    protected final short readShort() throws IOException {
        final short read;
        final byte[] bytes;

        read = this.handler.getInput().readShort();
        bytes = ByteBuffer.allocate(2).putShort(read).array();

        this.packetReceived.write(bytes);

        return read;
    }

    /**
     * Read and return a integer.
     */
    protected final int readInt() throws IOException {
        final int read;
        final byte[] bytes;

        read = this.handler.getInput().readInt();
        bytes = ByteBuffer.allocate(4).putInt(read).array();

        this.packetReceived.write(bytes);

        return read;
    }

    /**
     * Read and return a long.
     */
    protected final long readLong() throws IOException {
        final long read;
        final byte[] bytes;

        read = this.handler.getInput().readLong();
        bytes = ByteBuffer.allocate(8).putLong(read).array();

        this.packetReceived.write(bytes);

        return read;
    }

    /**
     * Read and return a float.
     */
    protected final float readFloat() throws IOException {
        final float read;
        final byte[] bytes;

        read = this.handler.getInput().readFloat();
        bytes = ByteBuffer.allocate(4).putFloat(read).array();

        this.packetReceived.write(bytes);

        return read;
    }

    /**
     * Read and return a double.
     */
    protected final double readDouble() throws IOException {
        final double read;
        final byte[] bytes;

        read = this.handler.getInput().readDouble();
        bytes = ByteBuffer.allocate(8).putDouble(read).array();

        this.packetReceived.write(bytes);

        return read;
    }

    /**
     * Read and return a boolean.
     */
    protected final boolean readBoolean() throws IOException {
        final boolean read;

        read = this.handler.getInput().readBoolean();

        this.packetReceived.write((byte) (read ? 0x01 : 0x00));

        return read;
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
            buffer[s] = this.readByte();
        }

        return new String(buffer, "UTF-16BE");
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

    /**
     * Read and return an array of bytes.
     */
    protected final short[] readShortArray(int length) throws IOException {
        final short[] array;

        /*
         * Initialize the array.
         */
        array = new short[length];

        /*
         * Fill it up.
         */
        for (int i = 0; i < length; i++) {
            array[i] = this.readShort();
        }

        return array;
    }

    /**
     * Write a byte.
     */
    protected final void writeByte(byte v) throws IOException {
        this.packetToSend.write(v);
    }

    /**
     * Write an unsigned byte.
     */
    protected final void writeUnsignedByte(int v) throws IOException {
        final byte unsigned;

        unsigned = (byte) ((v < 128) ? v : (v - 256));
        this.packetToSend.write(unsigned);
    }

    /**
     * Write a short.
     */
    protected final void writeShort(short v) throws IOException {
        final byte[] bytes;

        bytes = ByteBuffer.allocate(2).putShort(v).array();

        this.packetToSend.write(bytes);
    }

    /**
     * Write an integer.
     */
    protected final void writeInt(int v) throws IOException {
        final byte[] bytes;

        bytes = ByteBuffer.allocate(4).putInt(v).array();

        this.packetToSend.write(bytes);
    }

    /**
     * Write a long.
     */
    protected final void writeLong(long v) throws IOException {
        final byte[] bytes;

        bytes = ByteBuffer.allocate(8).putLong(v).array();

        this.packetToSend.write(bytes);
    }

    /**
     * Write a float.
     */
    protected final void writeFloat(float v) throws IOException {
        final byte[] bytes;

        bytes = ByteBuffer.allocate(4).putFloat(v).array();

        this.packetToSend.write(bytes);
    }

    /**
     * Write a double.
     */
    protected final void writeDouble(double v) throws IOException {
        final byte[] bytes;

        bytes = ByteBuffer.allocate(8).putDouble(v).array();

        this.packetToSend.write(bytes);
    }

    /**
     * Write a boolean.
     */
    protected final void writeBoolean(boolean v) throws IOException {
        this.packetToSend.write((byte) (v ? 0x01 : 0x00));
    }

    /**
     * Write a UTF-16 string.
     */
    protected final void writeUnicodeString(String string) throws IOException {
        final byte[] bytes;

        /*
         * We first send the size of the string.
         */
        this.writeShort((short) string.length());

        /*
         * Convert the string to unicode and send it byte per byte.
         */
        bytes = string.getBytes("UTF-16BE");

        this.packetToSend.write(bytes);
    }

    /**
     * Actually send the packet (the buffer of bytes).
     */
    protected final void send() throws IOException {
        final byte[] bytes;

        bytes = this.packetToSend.toByteArray();

        /*
         * Since we can write packets from 2 different threads we should lock
         * the output so we can ensure that a thread will write a packet without
         * being interrupted by the other one.
         */
        synchronized (this.handler.getOutput()) {
            this.handler.getOutput().write(bytes);
        }
    }

    /**
     * Get the world used by the server.
     */
    protected final World getWorld() {
        return this.handler.getWorld();
    }

    /**
     * The action to do with the packet (read or write).
     */
    public final PacketAction getAction() {
        return this.action;
    }

    /**
     * Change the action to do with the packet (read or write).
     */
    public final void setAction(PacketAction action) {
        this.action = action;
    }

    /**
     * The ID of the packet.
     */
    public final int getID() {
        return this.id;
    }

    /**
     * Close the buffers that contains bytes so we can release them later.
     */
    public final void freeBuffers() {
        try {
            this.packetReceived.close();
            this.packetToSend.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read the packet from the network.
     */
    public abstract void read() throws IOException;

    /**
     * Write the packet on the network.
     */
    public abstract void write() throws IOException;

    /**
     * Process the packet.
     * 
     * <p>
     * This method is not used to send a response or get the data of the packet.
     * It is used to make a link between what's happening on the network and
     * what should be done in our code.
     */
    public abstract void process();

    /**
     * Get the packet that should be sent as a response to this current packet.
     */
    public abstract Packet response();

    /**
     * Get the data contained by the packet.
     */
    public abstract String getDataAsString();

    @Override
    public final String toString() {
        final StringBuilder builder;
        final Field[] fields;

        builder = new StringBuilder();
        fields = this.getClass().getDeclaredFields();

        builder.append("Packet ");
        builder.append(String.format("0x%02X", this.id));
        builder.append(" : ");
        builder.append(this.getClass().getSimpleName());
        builder.append(LINE_SEPARATOR);
        builder.append("  * Structure   -> [ ");
        builder.append(Byte.class.getSimpleName().toLowerCase());
        builder.append(" | ");

        for (Field field : fields) {
            builder.append(field.getType().getSimpleName().toLowerCase());
            builder.append(" | ");
        }

        builder.delete(builder.length() - 3, builder.length());
        builder.append(" ]");
        builder.append(LINE_SEPARATOR);
        builder.append("  * Raw packet  -> '");

        for (Byte b : this.getUsedBuffer().toByteArray()) {
            builder.append(String.format("%02X ", b));
        }

        builder.deleteCharAt(builder.length() - 1);
        builder.append("'");
        builder.append(LINE_SEPARATOR);
        builder.append("  * Parsed data -> '");
        builder.append(this.getDataAsString());
        builder.append("'");
        builder.append(LINE_SEPARATOR);

        return builder.toString();
    }
}
