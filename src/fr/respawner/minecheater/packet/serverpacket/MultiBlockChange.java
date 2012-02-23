package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.block.MultiBlockDataChange;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class MultiBlockChange extends Packet {
	private int chunkX;
	private int chunkZ;
	private short[] coordinates;
	private byte[] types;
	private byte[] metadatas;

	public MultiBlockChange(PacketsHandler handler) {
		super(handler, (byte) 0x34);
	}

	@Override
	public void read() throws IOException {
		final short length;

		this.chunkX = this.readInt();
		this.chunkZ = this.readInt();

		length = this.readShort();

		this.coordinates = this.readShortArray(length);
		this.types = this.readByteArray(length);
		this.metadatas = this.readByteArray(length);
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
		return new MultiBlockDataChange(this.chunkX, this.chunkZ,
				this.coordinates, this.types, this.metadatas);
	}
}
