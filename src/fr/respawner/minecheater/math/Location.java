package fr.respawner.minecheater.math;

public final class Location {
    private double x;
    private double y;
    private double z;
    private double stance;
    private float yaw;
    private float pitch;
    private float roll;
    private boolean onGround;

    public Location(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.onGround = true;
    }

    public Location(int x, int y, int z) {
        this((x / 32.0), (y / 32.0), (z / 32.0));
    }

    public Location(Vector vector) {
        this(vector.getX(), vector.getY(), vector.getZ());
    }

    public double getX() {
        return this.x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return this.y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return this.z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getStance() {
        return this.stance;
    }

    public void setStance(double stance) {
        this.stance = stance;
    }

    public float getYaw() {
        return this.yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public float getPitch() {
        return this.pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public float getRoll() {
        return this.roll;
    }

    public void setRoll(float roll) {
        this.roll = roll;
    }

    public boolean isOnGround() {
        return this.onGround;
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }

    public void setPosition(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void setRotation(float yaw, float pitch) {
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public void setRotationFromPacket(byte yaw, byte pitch) {
        this.yaw = (float) ((yaw * 2 * Math.PI) / 256);
        this.pitch = (float) ((pitch * 2 * Math.PI) / 256);
    }

    public void setRotation(float yaw, float pitch, float roll) {
        this.yaw = yaw;
        this.pitch = pitch;
        this.roll = roll;
    }

    public void setRotationFromPacket(byte yaw, byte pitch, byte roll) {
        this.yaw = (float) ((yaw * 2 * Math.PI) / 256);
        this.pitch = (float) ((pitch * 2 * Math.PI) / 256);
        this.roll = (float) ((roll * 2 * Math.PI) / 256);
    }

    public Vector toVector() {
        return new Vector((int) Math.floor(this.x), (int) Math.floor(this.y),
                (int) Math.floor(this.z));
    }

    @Override
    public String toString() {
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append("Location: x = ");
        builder.append(this.x);
        builder.append(", y = ");
        builder.append(this.y);
        builder.append(", z = ");
        builder.append(this.z);
        builder.append(", stance = ");
        builder.append(this.stance);
        builder.append(", yaw = ");
        builder.append(this.yaw);
        builder.append(", pitch = ");
        builder.append(this.pitch);
        builder.append(", roll = ");
        builder.append(this.roll);
        builder.append(" | ");
        builder.append(this.onGround ? "Walking or Swimming"
                : "Jumping or Falling");

        return builder.toString();
    }
}
