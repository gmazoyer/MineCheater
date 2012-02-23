package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.Teleport;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class EntityTeleport extends Packet {
	private int entityID;
	private int x;
	private int y;
	private int z;
	private byte yaw;
	private byte pitch;

	public EntityTeleport(PacketsHandler handler) {
		super(handler, (byte) 0x22);
	}

	@Override
	public void read() throws IOException {
		this.entityID = this.readInt();
		this.x = this.readInt();
		this.y = this.readInt();
		this.z = this.readInt();
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
		return new Teleport(this.entityID, this.x, this.y, this.z, this.yaw,
				this.pitch);
	}
}
