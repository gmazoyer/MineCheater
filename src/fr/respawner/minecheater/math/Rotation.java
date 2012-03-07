package fr.respawner.minecheater.math;

public final class Rotation {
    private float x;
    private float y;
    private float roll;

    public Rotation(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Rotation() {
        this(0, 0);
    }

    public float getX() {
        return this.x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return this.y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getRoll() {
        return this.roll;
    }

    public void setRoll(float roll) {
        this.roll = roll;
    }

    public void setRotationFromPacket(byte x, byte y) {
        this.x = (float) ((x * 2 * Math.PI) / 256);
        this.y = (float) ((y * 2 * Math.PI) / 256);
    }

    public void setRotation(float x, float y, float roll) {
        this.x = x;
        this.y = y;
        this.roll = roll;
    }

    public void setRotationFromPacket(byte x, byte y, byte roll) {
        this.x = (float) ((x * 2 * Math.PI) / 256);
        this.y = (float) ((y * 2 * Math.PI) / 256);
        this.roll = (float) ((roll * 2 * Math.PI) / 256);
    }

    @Override
    public String toString() {
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append("Rotation: x = ");
        builder.append(this.x);
        builder.append(", y = ");
        builder.append(this.y);
        builder.append(", roll = ");
        builder.append(this.roll);

        return builder.toString();
    }
}
