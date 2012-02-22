package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.block.ActionBlock;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class BlockAction extends Packet {
	private int x;
	private short y;
	private int z;
	private byte firstByte;
	private byte secondByte;

	public BlockAction(PacketsHandler handler) {
		super(handler, (byte) 0x36);
	}

	@Override
	public void read() throws IOException {
		this.x = this.readInt();
		this.y = this.readShort();
		this.z = this.readInt();
		this.firstByte = this.readByte();
		this.secondByte = this.readByte();
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
		return new ActionBlock(this.x, this.y, this.z, this.firstByte,
				this.secondByte);
	}
}
