package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.worker.PacketsHandler;

public class PreChunk extends Packet {
	private int x;
	private int z;
	private boolean mode;

	public PreChunk(PacketsHandler handler) {
		super(handler, (byte) 0x32);
	}

	@Override
	public void read() throws IOException {
		this.x = this.readInt();
		this.z = this.readInt();
		this.mode = this.readBoolean();
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
		return new fr.respawner.minecheater.structure.block.PreChunk(this.x,
				this.z, this.mode);
	}
}
