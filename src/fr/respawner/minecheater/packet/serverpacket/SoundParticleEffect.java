package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.SoundParticle;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class SoundParticleEffect extends Packet {
	private int effectID;
	private int x;
	private byte y;
	private int z;
	private int data;

	public SoundParticleEffect(PacketsHandler handler) {
		super(handler, (byte) 0x3D);
	}

	@Override
	public void read() throws IOException {
		this.effectID = this.readInt();
		this.x = this.readInt();
		this.y = this.readByte();
		this.z = this.readInt();
		this.data = this.readInt();
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
		return new SoundParticle(this.effectID, this.x, this.y, this.z,
				this.data);
	}
}
