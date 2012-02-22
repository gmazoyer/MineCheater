package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.PlayerEffect;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class EntityEffect extends Packet {
	private int entityID;
	private byte effectID;
	private byte amplifier;
	private short duration;

	public EntityEffect(PacketsHandler handler) {
		super(handler, (byte) 0x29);
	}

	@Override
	public void read() throws IOException {
		this.entityID = this.readInt();
		this.effectID = this.readByte();
		this.amplifier = this.readByte();
		this.duration = this.readShort();
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
		return new PlayerEffect(this.entityID, this.effectID, this.amplifier,
				this.duration);
	}
}
