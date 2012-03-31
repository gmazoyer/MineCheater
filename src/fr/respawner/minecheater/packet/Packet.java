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
package fr.respawner.minecheater.packet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;

import fr.respawner.minecheater.Config;
import fr.respawner.minecheater.World;
import fr.respawner.minecheater.worker.IHandler;

public abstract class Packet {
    /*
     * Packet IDs.
     */
    public static final byte KEEP_ALIVE = (byte) 0x00;
    public static final byte LOGIN_REQUEST = (byte) 0x01;
    public static final byte HANDSHAKE = (byte) 0x02;
    public static final byte CHAT_MESSAGE = (byte) 0x03;
    public static final byte TIME_UPDATE = (byte) 0x04;
    public static final byte ENTITY_EQUIPMENT = (byte) 0x05;
    public static final byte SPAWN_POSITION = (byte) 0x06;
    public static final byte USE_ENTITY = (byte) 0x07;
    public static final byte UPDATE_HEALTH = (byte) 0x08;
    public static final byte RESPAWN = (byte) 0x09;
    public static final byte PLAYER = (byte) 0x0A;
    public static final byte PLAYER_POSITION = (byte) 0x0B;
    public static final byte PLAYER_LOOK = (byte) 0x0C;
    public static final byte PLAYER_POSITION_AND_LOOK = (byte) 0x0D;
    public static final byte PLAYER_DIGGING = (byte) 0x0E;
    public static final byte PLAYER_BLOCK_PLACEMENT = (byte) 0x0F;
    public static final byte HELD_ITEM_CHANGE = (byte) 0x10;
    public static final byte USE_BED = (byte) 0x11;
    public static final byte ANIMATION = (byte) 0x12;
    public static final byte ENTITY_ACTION = (byte) 0x13;
    public static final byte SPAWN_NAMED_ENTITY = (byte) 0x14;
    public static final byte SPAWN_DROPPED_ITEM = (byte) 0x15;
    public static final byte COLLECT_ITEM = (byte) 0x16;
    public static final byte SPAWN_OBJECT_VEHICLE = (byte) 0x17;
    public static final byte SPAWN_MOB = (byte) 0x18;
    public static final byte SPAWN_PAINTING = (byte) 0x19;
    public static final byte SPAWN_EXPERIENCE_ORB = (byte) 0x1A;
    public static final byte ENTITY_VELOCITY = (byte) 0x1C;
    public static final byte DESTROY_ENTITY = (byte) 0x1D;
    public static final byte ENTITY = (byte) 0x1E;
    public static final byte ENTITY_RELATIVE_MOVE = (byte) 0x1F;
    public static final byte ENTITY_LOOK = (byte) 0x20;
    public static final byte ENTITY_LOOK_AND_RELATIVE_MOVE = (byte) 0x21;
    public static final byte ENTITY_TELEPORT = (byte) 0x22;
    public static final byte ENTITY_HEAD_LOOK = (byte) 0x23;
    public static final byte ENTITY_STATUS = (byte) 0x26;
    public static final byte ATTACH_ENTITY = (byte) 0x27;
    public static final byte ENTITY_METADATA = (byte) 0x28;
    public static final byte ENTITY_EFFECT = (byte) 0x29;
    public static final byte REMOVE_ENTITY_EFFECT = (byte) 0x2A;
    public static final byte SET_EXPERIENCE = (byte) 0x2B;
    public static final byte MAP_COLUMN_ALLOCATION = (byte) 0x32;
    public static final byte MAP_CHUNKS = (byte) 0x33;
    public static final byte MULTI_BLOCK_CHANGE = (byte) 0x34;
    public static final byte BLOCK_CHANGE = (byte) 0x35;
    public static final byte BLOCK_ACTION = (byte) 0x36;
    public static final byte EXPLOSION = (byte) 0x3C;
    public static final byte SOUND_PARTICLE_EFFECT = (byte) 0x3D;
    public static final byte CHANGE_GAME_STATE = (byte) 0x46;
    public static final byte THUNDERBOLT = (byte) 0x47;
    public static final byte OPEN_WINDOW = (byte) 0x64;
    public static final byte CLOSE_WINDOW = (byte) 0x65;
    public static final byte CLICK_WINDOW = (byte) 0x66;
    public static final byte SET_SLOT = (byte) 0x67;
    public static final byte SET_WINDOW_ITEMS = (byte) 0x68;
    public static final byte UPDATE_WINDOW_PROPERTY = (byte) 0x69;
    public static final byte CONFIRM_TRANSACTION = (byte) 0x6A;
    public static final byte UPDATE_SIGN = (byte) 0x82;
    public static final byte ITEM_DATA = (byte) 0x83;
    public static final byte UPDATE_TILE_ENTITY = (byte) 0x84;
    public static final byte INCREMENT_STATISTIC = (byte) 0xC8;
    public static final byte PLAYER_LIST_ITEM = (byte) 0xC9;
    public static final byte PLAYER_ABILITIES = (byte) 0xCA;
    public static final byte PLUGIN_MESSAGE = (byte) 0xFA;
    public static final byte SERVER_LIST_PING = (byte) 0xFE;
    public static final byte DISCONNECT_KICK = (byte) 0xFF;

    protected final ByteArrayOutputStream packetReceived;
    protected final ByteArrayOutputStream packetToSend;
    protected final IHandler handler;
    protected final byte id;

    protected PacketAction action;

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
     * Read and return an unsigned short as integer.
     */
    protected final int readUnsignedShort() throws IOException {
        final int read;
        final byte[] bytes;

        read = this.handler.getInput().readUnsignedShort();
        bytes = ByteBuffer.allocate(4).putInt(read).array();

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
    protected final byte[] readUnsignedByteArray(int length) throws IOException {
        final byte[] array;

        /*
         * Initialize the array.
         */
        array = new byte[length];

        /*
         * Fill it up.
         */
        for (int i = 0; i < length; i++) {
            array[i] = (byte) this.readUnsignedByte();
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
     * Write an array of bytes.
     */
    protected final void writeByteArray(byte[] array) throws IOException {
        for (byte b : array) {
            this.writeByte(b);
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
    public final void free() {
        try {
            this.packetReceived.close();
            this.packetToSend.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Actually send the packet (the buffer of bytes).
     */
    public final void send() throws IOException {
        final byte[] bytes;

        /*
         * Write all the packet in the buffer.
         */
        this.write();

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

        builder.append((this.action == PacketAction.READING) ? "[Server -> Client] - "
                : "[Client -> Server] - ");
        builder.append("Packet ");
        builder.append(String.format("0x%02X", this.id));
        builder.append(" : ");
        builder.append(this.getClass().getSimpleName());
        builder.append(Config.LINE_SEPARATOR);
        builder.append("  * Structure   -> [ ");
        builder.append(Byte.class.getSimpleName().toLowerCase());
        builder.append(" | ");

        for (Field field : fields) {
            builder.append(field.getType().getSimpleName().toLowerCase());
            builder.append(" | ");
        }

        builder.delete(builder.length() - 3, builder.length());
        builder.append(" ]");
        builder.append(Config.LINE_SEPARATOR);
        builder.append("  * Raw packet  -> '");

        for (Byte b : this.getUsedBuffer().toByteArray()) {
            builder.append(String.format("%02X ", b));
        }

        builder.deleteCharAt(builder.length() - 1);
        builder.append("'");
        builder.append(Config.LINE_SEPARATOR);
        builder.append("  * Parsed data -> '");
        builder.append(this.getDataAsString());
        builder.append("'");
        builder.append(Config.LINE_SEPARATOR);

        return builder.toString();
    }
}
