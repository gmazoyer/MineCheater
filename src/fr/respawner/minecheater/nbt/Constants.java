package fr.respawner.minecheater.nbt;

import java.nio.charset.Charset;

public final class Constants {
    public static final Charset CHARSET = Charset.forName("UTF-8");

    public static final int TYPE_END = 0;
    public static final int TYPE_BYTE = 1;
    public static final int TYPE_SHORT = 2;
    public static final int TYPE_INT = 3;
    public static final int TYPE_LONG = 4;
    public static final int TYPE_FLOAT = 5;
    public static final int TYPE_DOUBLE = 6;
    public static final int TYPE_BYTE_ARRAY = 7;
    public static final int TYPE_STRING = 8;
    public static final int TYPE_LIST = 9;
    public static final int TYPE_COMPOUND = 10;

    private Constants() {
        /*
         * No instance.
         */
    }
}
