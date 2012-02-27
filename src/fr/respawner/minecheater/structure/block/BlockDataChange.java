package fr.respawner.minecheater.structure.block;

public final class BlockDataChange {
    private int x;
    private byte y;
    private int z;
    private byte type;
    private byte metadata;

    public BlockDataChange(int x, byte y, int z, byte type, byte metadata) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.type = type;
        this.metadata = metadata;
    }

    public int getX() {
        return this.x;
    }

    public byte getY() {
        return this.y;
    }

    public int getZ() {
        return this.z;
    }

    public byte getType() {
        return this.type;
    }

    public byte getMetadata() {
        return this.metadata;
    }

    @Override
    public String toString() {
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append("Position: x = ");
        builder.append(this.x);
        builder.append(", y = ");
        builder.append(this.y);
        builder.append(", z = ");
        builder.append(this.z);
        builder.append(" | Type = ");
        builder.append(this.type);
        builder.append(" | Metadata = ");
        builder.append(this.metadata);

        return builder.toString();
    }
}
