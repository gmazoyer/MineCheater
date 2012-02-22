package fr.respawner.minecheater.packet.common;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.world.PlayerRespawn;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class Respawn extends Packet {
	private byte dimension;
	private byte difficulty;
	private byte mode;
	private short worldHeight;
	private long mapSeed;
	private String levelType;

	public Respawn(PacketsHandler handler) {
		super(handler, (byte) 0x09);
	}

	@Override
	public void read() throws IOException {
		this.dimension = this.readByte();
		this.difficulty = this.readByte();
		this.mode = this.readByte();
		this.worldHeight = this.readShort();
		this.mapSeed = this.readLong();
		this.levelType = this.readUnicodeString();
	}

	@Override
	public void write() throws IOException {
		this.writeByte(this.id);
		this.writeByte(this.getWorld().getCurrentWorld().getDimension());
		this.writeByte((byte) 1);
		this.writeByte((byte) this.getWorld().getCurrentWorld().getServerMode());
		this.writeShort((short) this.getWorld().getCurrentWorld()
				.getWorldHeight());
		this.writeLong(this.getWorld().getCurrentWorld().getMapSeed());
		this.writeUnicodeString(this.getWorld().getCurrentWorld()
				.getLevelType());
		this.send();
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
		return new PlayerRespawn(this.dimension, this.difficulty, this.mode,
				this.worldHeight, this.mapSeed, this.levelType);
	}
}
