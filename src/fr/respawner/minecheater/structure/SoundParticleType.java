package fr.respawner.minecheater.structure;

public enum SoundParticleType {
	UNKNOWN, CLICK1, CLICK2, BOW_FIRE, DOOR_TOGGLE, EXTINGUISH, RECORD_PLAY, CHARGE, FIREBALL, SMOKE, BLOCK_BREAK, SPLASH_POTION, PORTAL, BLAZE;

	public static SoundParticleType effectForID(int id) {
		switch (id) {
		case 1000:
			return CLICK2;
		case 1001:
			return CLICK1;
		case 1002:
			return BOW_FIRE;
		case 1003:
			return DOOR_TOGGLE;
		case 1004:
			return EXTINGUISH;
		case 1005:
			return RECORD_PLAY;
		case 1007:
			return CHARGE;
		case 1008:
		case 1009:
			return FIREBALL;
		case 2000:
			return SMOKE;
		case 2001:
			return BLOCK_BREAK;
		case 2002:
			return SPLASH_POTION;
		case 2003:
			return PORTAL;
		case 2004:
			return BLAZE;
		default:
			return UNKNOWN;
		}
	}

	@Override
	public String toString() {
		switch (this) {
		case CLICK2:
			return "Click 2";
		case CLICK1:
			return "Click 1";
		case BOW_FIRE:
			return "Bow fire";
		case DOOR_TOGGLE:
			return "Door toggle";
		case EXTINGUISH:
			return "Extinguish";
		case RECORD_PLAY:
			return "Record play";
		case CHARGE:
			return "Charge";
		case FIREBALL:
			return "Fireball";
		case SMOKE:
			return "Smoke";
		case BLOCK_BREAK:
			return "Block break";
		case SPLASH_POTION:
			return "Splash potion";
		case PORTAL:
			return "Portal";
		case BLAZE:
			return "Blaze";
		default:
			return "Unknown";
		}
	}
}
