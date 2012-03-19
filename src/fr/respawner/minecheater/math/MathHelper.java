package fr.respawner.minecheater.math;

public final class MathHelper {
    private MathHelper() {
        /*
         * No instance.
         */
    }

    public static int floorDouble(double value) {
        final int i;

        i = (int) value;

        return (value >= i) ? i : (i - 1);
    }

    public static int log2(int value) {
        int result;

        result = 0;

        if (value > 0xFFFF) {
            value >>= 16;
            result = 16;
        }

        if (value > 0x00FF) {
            value >>= 8;
            result += 8;
        }

        if (value > 0x000F) {
            value >>= 4;
            result += 4;
        }

        if (value > 0x0003) {
            value >>= 2;
            result += 2;
        }

        return (result + (value >> 1));
    }

    public static Vector3D coordinatesFromIndexes(int i, int y) {
        return new Vector3D(i & 0x0F, (y * 16) + (i >> 8), i & (0xF0 >> 4));
    }

    public static int indexFromCoordinates(Vector3D coordinates) {
        final int x, y, z;

        x = (int) coordinates.getX();
        y = (int) coordinates.getY();
        z = (int) coordinates.getZ();

        return (x + (y * 16) + (z * 16 * 16));
    }

    public static Vector3D coordinatesForIndex(int index) {
        return new Vector3D(index & (16 - 1), (index >> log2(16)) & (16 - 1),
                index >> log2(16 * 16));
    }
}
