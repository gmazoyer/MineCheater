package fr.respawner.minecheater.structure.inventory;

import fr.respawner.minecheater.metadata.Slotdata;

public final class Slot {
	private byte windowID;
	private short slot;
	private Slotdata data;

	public Slot(byte windowID, short slot, Slotdata data) {
		this.windowID = windowID;
		this.slot = slot;
		this.data = data;
	}

	public byte getWindowID() {
		return this.windowID;
	}

	public short getSlot() {
		return this.slot;
	}

	public Slotdata getData() {
		return this.data;
	}

	@Override
	public String toString() {
		final StringBuilder builder;

		builder = new StringBuilder();

		builder.append("Window ID = ");
		builder.append(this.windowID);
		builder.append(" | Slot = ");
		builder.append(this.slot);
		builder.append(" | Content = ");
		builder.append(this.data);

		return builder.toString();
	}
}
