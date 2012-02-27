package fr.respawner.minecheater.structure.world;

public final class State {
	private byte reason;
	private byte mode;

	public State(byte reason, byte mode) {
		this.reason = reason;
		this.mode = mode;
	}

	public byte getReason() {
		return this.reason;
	}

	public byte getMode() {
		return this.mode;
	}

	@Override
	public String toString() {
		switch (this.reason) {
		case 0:
			return "Invalid bed use";

		case 1:
			return "Begin raining";

		case 2:
			return "End raining";

		case 3:
			return "Game mode = " + (this.mode == 0 ? "survival" : "creative");

		case 4:
			return "Enter credits";

		default:
			return "Unknown";
		}
	}
}
