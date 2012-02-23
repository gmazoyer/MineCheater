package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.MCStatistic;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class IncrementStatistic extends Packet {
	private int statisticID;
	private byte amount;

	private MCStatistic instance;

	public IncrementStatistic(PacketsHandler handler) {
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
		final MCStatistic statistic;

		statistic = this.getWorld().findStatisticByID(this.statisticID);
		this.instance = new MCStatistic(this.statisticID, this.amount);

		if (statistic != null) {
			statistic.setAmount(this.amount);
		} else {
			this.getWorld().addStatistic(this.instance);
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
	public Object getData() {
		return this.instance;
	}
}
