package fr.respawner.minecheater.structure.world;

public final class MCMapColumn {
    private int x;
    private int z;
    private MCChunk[] chunks;
    private byte[] biomes;

    public MCMapColumn(int x, int z) {
        this.x = x;
        this.z = z;
        this.chunks = new MCChunk[16];
        this.biomes = new byte[256];
    }

    public int getX() {
        return this.x;
    }

    public int getZ() {
        return this.z;
    }

    public MCChunk[] getChunks() {
        return this.chunks;
    }

    public byte[] getBiomes() {
        return this.biomes;
    }

    public boolean isAt(int x, int z) {
        return ((this.x == x) && (this.z == z));
    }

    @Override
    public String toString() {
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append("MapColumn: x = ");
        builder.append(this.x);
        builder.append(", z = ");
        builder.append(this.z);

        return builder.toString();
    }
}
