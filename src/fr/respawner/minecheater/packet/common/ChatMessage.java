package fr.respawner.minecheater.packet.common;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class ChatMessage extends Packet {
	private String message;

	public ChatMessage(PacketsHandler handler) {
		super(handler, (byte) 0x03);
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public void read() throws IOException {
		this.message = this.readUnicodeString();
	}

	@Override
	public void write() throws IOException {
		this.writeByte(this.id);
		this.writeUnicodeString(this.message);
		this.send();
	}

	@Override
	public void process() {
		this.handler.println("Message received: " + this.message);
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
		return this.message;
	}
}
