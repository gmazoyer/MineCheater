package fr.respawner.minecheater.structure.player;

public final class MCStatistic {
    private int statisticID;
    private byte amount;

    public MCStatistic(int statisticID, byte amount) {
        this.statisticID = statisticID;
        this.amount = amount;
    }

    public final int getStatisticID() {
        return this.statisticID;
    }

    public final byte getAmount() {
        return this.amount;
    }

    public final void setAmount(byte amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append("Statistic ID = ");
        builder.append(this.statisticID);
        builder.append(" | Amount = ");
        builder.append(this.amount);

        return builder.toString();
    }
}
