package fr.respawner.minecheater.math;

public final class Location {
    private double x;
    private double y;
    private double z;
    private double stance;
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
        builder.append(" | ");
        builder.append(this.onGround ? "Walking or Swimming"
                : "Jumping or Falling");

        return builder.toString();
    }
}
