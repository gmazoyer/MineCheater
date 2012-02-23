package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.Look;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class EntityLook extends Packet {
	private int entityID;
	private byte yaw;
	private byte pitch;

	public EntityLook(PacketsHandler handler) {
		super(handler, (byte) 0x20);
	}

	@Override
	public void read() throws IOException {
		this.entityID = this.readInt();
		this.yaw = this.readByte();
		this.pitch = this.readByte();
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
		return new Look(this.entityID, this.yaw, this.pitch);
	}
}
