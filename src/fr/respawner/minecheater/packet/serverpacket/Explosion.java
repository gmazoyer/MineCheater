package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.ExplosionAnimation;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class Explosion extends Packet {
	private double x;
	private double y;
	private double z;
	private float unknown;
	private byte[][] records;

	public Explosion(PacketsHandler handler) {
		super(handler, (byte) 0x3C);
	}

	@Override
	public void read() throws IOException {
		final int count;

		this.x = this.readDouble();
		this.y = this.readDouble();
		this.z = this.readDouble();
		this.unknown = this.readFloat();

		count = this.readInt();
		this.records = new byte[count][3];
		for (int i = 0; i < count; i++) {
			for (byte b = 0; b < 3; b++) {
				this.records[i][b] = this.readByte();
			}
		}
	}

	@Override
	public void write() throws IOException {
		/*
		 * We don't write this packet.
		 */
	}

	@Override
	public void process() {
		/*
		 * Nothing to do.
		 */
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
		return new ExplosionAnimation(this.x, this.y, this.z, this.unknown,
				this.records);
	}
}
