package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.block.BlockDataChange;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class BlockChange extends Packet {
	private int x;
	private byte y;
	private int z;
	private byte type;
	private byte metadata;

	public BlockChange(PacketsHandler handler) {
		super(handler, (byte) 0x35);
	}

	@Override
	public void read() throws IOException {
		this.x = this.readInt();
		this.y = this.readByte();
		this.z = this.readInt();
		this.type = this.readByte();
		this.metadata = this.readByte();
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
		return new BlockDataChange(this.x, this.y, this.z, this.type,
				this.metadata);
	}
}
