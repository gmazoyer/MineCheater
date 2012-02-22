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
		final StringBuilder builder;

		builder = new StringBuilder();

		switch (this.reason) {
		case 0:
			builder.append("Invalid bed use");
			break;

		case 1:
			builder.append("Begin raining");
			break;

		case 2:
			builder.append("End raining");
			break;

		case 3:
			builder.append("Game mode = ");
			builder.append(this.mode == 0 ? "survival" : "creative");
			break;

		case 4:
			builder.append("Enter credits");
			break;

		default:
			break;
		}

		return builder.toString();
	}
}
