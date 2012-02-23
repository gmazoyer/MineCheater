package fr.respawner.minecheater.structure.world;

public enum DifficultyType {
	PEACEFUL, EASY, NORMAL, HARD;

	public static DifficultyType difficultyForID(byte difficulty) {
		switch (difficulty) {
		case 0:
			return PEACEFUL;
		case 1:
			return EASY;
		case 2:
			return NORMAL;
		default:
			return HARD;
		}
	}

	@Override
	public String toString() {
		switch (this) {
		case PEACEFUL:
			return "Peaceful";
		case EASY:
			return "Easy";
		case NORMAL:
			return "Normal";
		case HARD:
			return "Hard";
		default:
			return "Unknown";
		}
	}
}
