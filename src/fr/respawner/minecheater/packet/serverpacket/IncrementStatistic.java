package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.player.MCStatistic;
import fr.respawner.minecheater.worker.IHandler;

public final class IncrementStatistic extends Packet {
    private int statisticID;
    private byte amount;

    public IncrementStatistic(IHandler handler) {
        super(handler, (byte) 0xC8);
    }

    @Override
    public void read() throws IOException {
        this.statisticID = this.readInt();
        this.amount = this.readByte();
    }

    @Override
    public void write() throws IOException {
        /*
         * We don't write this packet.
         */
    }

    @Override
    public void process() {
        MCStatistic statistic;

        statistic = this.getWorld().getPlayer()
                .findStatisticByID(this.statisticID);

        if (statistic != null) {
            statistic.setAmount(this.amount);
        } else {
            statistic = new MCStatistic(this.statisticID, this.amount);
            this.getWorld().getPlayer().addStatistic(statistic);
        }
    }

    @Override
    public Packet response() {
        /*
         * We don't send a response to this packet.
         */
        return null;
    }

    @Override
    public String getDataAsString() {
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append("Statistic ID = ");
        builder.append(this.statisticID);
        builder.append(" | Amount = ");
        builder.append(this.amount);

        return builder.toString();
    }
}
