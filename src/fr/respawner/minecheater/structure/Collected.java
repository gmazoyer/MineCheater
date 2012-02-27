package fr.respawner.minecheater.structure;

public final class Collected {
    private int collectedID;
    private int collectorID;

    public Collected(int collectedID, int collectorID) {
        this.collectedID = collectedID;
        this.collectorID = collectorID;
    }

    public int getCollectedID() {
        return this.collectedID;
    }

    public int getCollectorID() {
        return this.collectorID;
    }

    @Override
    public String toString() {
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append("Collected ID = ");
        builder.append(this.collectedID);
        builder.append(" | Collector ID = ");
        builder.append(this.collectorID);

        return builder.toString();
    }
}
