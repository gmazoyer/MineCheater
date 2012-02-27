package fr.respawner.minecheater.nbt;

public final class Utils {
    private Utils() {
    }

    public static String getTypeName(Class<? extends Tag> clazz) {
        if (clazz.equals(ByteArrayTag.class)) {
            return "TAG_Byte_Array";
        } else if (clazz.equals(ByteTag.class)) {
            return "TAG_Byte";
        } else if (clazz.equals(CompoundTag.class)) {
            return "TAG_Compound";
        } else if (clazz.equals(DoubleTag.class)) {
            return "TAG_Double";
        } else if (clazz.equals(EndTag.class)) {
            return "TAG_End";
        } else if (clazz.equals(FloatTag.class)) {
            return "TAG_Float";
        } else if (clazz.equals(IntTag.class)) {
            return "TAG_Int";
        } else if (clazz.equals(ListTag.class)) {
            return "TAG_List";
        } else if (clazz.equals(LongTag.class)) {
            return "TAG_Long";
        } else if (clazz.equals(ShortTag.class)) {
            return "TAG_Short";
        } else if (clazz.equals(StringTag.class)) {
            return "TAG_String";
        } else {
            throw new IllegalArgumentException("Invalid tag classs ("
                    + clazz.getName() + ").");
        }
    }

    public static int getTypeCode(Class<? extends Tag> clazz) {
        if (clazz.equals(ByteArrayTag.class)) {
            return Constants.TYPE_BYTE_ARRAY;
        } else if (clazz.equals(ByteTag.class)) {
            return Constants.TYPE_BYTE;
        } else if (clazz.equals(CompoundTag.class)) {
            return Constants.TYPE_COMPOUND;
        } else if (clazz.equals(DoubleTag.class)) {
            return Constants.TYPE_DOUBLE;
        } else if (clazz.equals(EndTag.class)) {
            return Constants.TYPE_END;
        } else if (clazz.equals(FloatTag.class)) {
            return Constants.TYPE_FLOAT;
        } else if (clazz.equals(IntTag.class)) {
            return Constants.TYPE_INT;
        } else if (clazz.equals(ListTag.class)) {
            return Constants.TYPE_LIST;
        } else if (clazz.equals(LongTag.class)) {
            return Constants.TYPE_LONG;
        } else if (clazz.equals(ShortTag.class)) {
            return Constants.TYPE_SHORT;
        } else if (clazz.equals(StringTag.class)) {
            return Constants.TYPE_STRING;
        } else {
            throw new IllegalArgumentException("Invalid tag classs ("
                    + clazz.getName() + ").");
        }
    }

    public static Class<? extends Tag> getTypeClass(int type) {
        switch (type) {
        case Constants.TYPE_END:
            return EndTag.class;
        case Constants.TYPE_BYTE:
            return ByteTag.class;
        case Constants.TYPE_SHORT:
            return ShortTag.class;
        case Constants.TYPE_INT:
            return IntTag.class;
        case Constants.TYPE_LONG:
            return LongTag.class;
        case Constants.TYPE_FLOAT:
            return FloatTag.class;
        case Constants.TYPE_DOUBLE:
            return DoubleTag.class;
        case Constants.TYPE_BYTE_ARRAY:
            return ByteArrayTag.class;
        case Constants.TYPE_STRING:
            return StringTag.class;
        case Constants.TYPE_LIST:
            return ListTag.class;
        case Constants.TYPE_COMPOUND:
            return CompoundTag.class;
        default:
            throw new IllegalArgumentException("Invalid tag type : " + type
                    + ".");
        }
    }
}
