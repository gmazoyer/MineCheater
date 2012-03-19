package fr.respawner.minecheater.math.unsigned;

import java.math.BigInteger;

public final class Unsigned {
    private Unsigned() {
        /*
         * No instantiation.
         */
    }

    public static UByte ubyte(byte value) throws NumberFormatException {
        return new UByte(value);
    }

    public static UByte ubyte(short value) throws NumberFormatException {
        return new UByte(value);
    }

    public static UShort ushort(short value) throws NumberFormatException {
        return new UShort(value);
    }

    public static UShort ushort(int value) throws NumberFormatException {
        return new UShort(value);
    }

    public static UInt uint(int value) throws NumberFormatException {
        return new UInt(value);
    }

    public static UInt uint(long value) throws NumberFormatException {
        return new UInt(value);
    }

    public static ULong ulong(long value) throws NumberFormatException {
        return new ULong(value);
    }

    public static ULong ulong(BigInteger value) throws NumberFormatException {
        return new ULong(value);
    }
}
