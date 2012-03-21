package fr.respawner.minecheater.packet;

public final class PacketIdentifier {
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
    public static final byte PLUGIN_MESSAGE = (byte) 0xFA;
    public static final byte SERVER_LIST_PING = (byte) 0xFF;
    public static final byte DISCONNECT_KICK = (byte) 0xFF;

    private PacketIdentifier() {
        /*
         * No instance.
         */
    }
}
