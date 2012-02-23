package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.RelativeMove;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class EntityRelativeMove extends Packet {
	private int entityID;
	private byte dX;
	private byte dY;
	private byte dZ;

	public EntityRelativeMove(PacketsHandler handler) {
		super(handler, (byte) 0x1F);
	}

	@Override
	public void read() throws IOException {
		this.entityID = this.readInt();
		this.dX = this.readByte();
		this.dY = this.readByte();
		this.dZ = this.readByte();
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
		return new RelativeMove(this.entityID, this.dX, this.dY, this.dZ);
	}
}
