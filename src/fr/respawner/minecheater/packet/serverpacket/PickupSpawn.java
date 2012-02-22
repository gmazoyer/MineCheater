package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.entity.MCPickup;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class PickupSpawn extends Packet {
	private int entityID;
	private short itemID;
	private byte count;
	private short damage;
	private int x;
	private int y;
	private int z;
	private byte rotation;
	private byte pitch;
	private byte roll;

	private MCPickup instance;

	public PickupSpawn(PacketsHandler handler) {
		super(handler, (byte) 0x15);
	}

	@Override
	public void read() throws IOException {
		this.entityID = this.readInt();
		this.itemID = this.readShort();
		this.count = this.readByte();
		this.damage = this.readShort();
		this.x = this.readInt();
		this.y = this.readInt();
		this.z = this.readInt();
		this.rotation = this.readByte();
		this.pitch = this.readByte();
		this.roll = this.readByte();
	}

	@Override
	public void write() throws IOException {
		/*
		 * We don't write this packet.
		 */
	}

	@Override
	public void process() {
		this.instance = new MCPickup(this.entityID, this.itemID, this.count,
				this.damage, this.x, this.y, this.z, this.rotation, this.pitch,
				this.roll);
		this.getWorld().addObject(this.instance);
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
