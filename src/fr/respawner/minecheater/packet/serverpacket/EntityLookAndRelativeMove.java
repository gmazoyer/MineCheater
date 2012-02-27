package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.entity.MCEntity;
import fr.respawner.minecheater.structure.entity.MCLookAndMove;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class EntityLookAndRelativeMove extends Packet {
	private int entityID;
	private byte dX;
	private byte dY;
	private byte dZ;
	private byte yaw;
	private byte pitch;

	private MCLookAndMove instance;

	public EntityLookAndRelativeMove(PacketsHandler handler) {
		super(handler, (byte) 0x21);
	}

	@Override
	public void read() throws IOException {
		this.entityID = this.readInt();
		this.dX = this.readByte();
		this.dY = this.readByte();
		this.dZ = this.readByte();
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
		final MCEntity entity;

		/*
		 * Find the entity to set its look and move.
		 */
		entity = (MCEntity) this.getWorld().findObjectByID(this.entityID);
		this.instance = new MCLookAndMove(this.entityID, this.dX, this.dY,
				this.dZ, this.yaw, this.pitch);

		if (entity != null) {
			entity.setMove(this.dX, this.dY, this.dZ);
			entity.setYaw(this.yaw);
			entity.setPitch(this.pitch);
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
