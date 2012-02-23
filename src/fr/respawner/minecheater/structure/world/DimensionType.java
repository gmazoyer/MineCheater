package fr.respawner.minecheater.structure.world;

public enum DimensionType {
	NETHER, OVERWORLD, END;

	public static DimensionType dimensionForID(byte dimension) {
		switch (dimension) {
		case -1:
			return NETHER;
		case 1:
			return END;
		default:
			return OVERWORLD;
		}
	}

	@Override
	public String toString() {
		switch (this) {
		case NETHER:
			return "The Nether";
		case OVERWORLD:
			return "The Overworld";
		case END:
			return "The End";
		default:
			return "Unknown";
		}
	}
}
