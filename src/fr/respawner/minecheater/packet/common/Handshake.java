package fr.respawner.minecheater.packet.common;

import java.io.IOException;

import fr.respawner.minecheater.Config;
import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class Handshake extends Packet {
	/*
	 * Client -> Server field
	 */
	private String username;

	/*
	 * Server -> Client field
	 */
	private String hash;

	public Handshake(PacketsHandler handler) {
		super(handler, (byte) 0x02);

		this.username = Config.USERNAME;
	}

	@Override
	public void read() throws IOException {
		this.hash = this.readUnicodeString();
	}

	@Override
	public void write() throws IOException {
		this.writeByte(this.id);
		this.writeUnicodeString(this.username);
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
		return new LoginRequest(this.handler);
	}

	@Override
	public Object getData() {
		switch (this.hash) {
		case "-":
			return "Name authentication disabled.";
		case "+":
			return "Password authentication enabled.";
		default:
			return this.hash;
		}
	}
}
