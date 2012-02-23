package fr.respawner.minecheater.structure.entity;

public enum MobType {
	UNKNOWN, CREEPER, SKELETON, SPIDER, GIANT_ZOMBIE, ZOMBIE, SLIME, GHAST, ZOMBIE_PIGMAN, ENDERMAN, CAVE_SPIDER, SILVERFISH, BLAZE, MAGMA_CUBE, ENDER_DRAGON, PIG, SHEEP, COW, CHICKEN, SQUID, WOLF, MOOSHROOM, SNOWMAN, VILLAGER;

	public static MobType mobForID(byte id) {
		switch (id) {
		case 50:
			return CREEPER;
		case 51:
			return SKELETON;
		case 52:
			return SPIDER;
		case 53:
			return GIANT_ZOMBIE;
		case 54:
			return ZOMBIE;
		case 55:
			return SLIME;
		case 56:
			return GHAST;
		case 57:
			return ZOMBIE_PIGMAN;
		case 58:
			return ENDERMAN;
		case 59:
			return CAVE_SPIDER;
		case 60:
			return SILVERFISH;
		case 62:
			return MAGMA_CUBE;
		case 63:
			return ENDER_DRAGON;
		case 90:
			return PIG;
		case 91:
			return SHEEP;
		case 92:
			return COW;
		case 93:
			return CHICKEN;
		case 94:
			return SQUID;
		case 95:
			return WOLF;
		case 96:
			return MOOSHROOM;
		case 97:
			return SNOWMAN;
		case 120:
			return VILLAGER;
		default:
			return UNKNOWN;
		}
	}

	@Override
	public String toString() {
		switch (this) {
		case CREEPER:
			return "Creeper";
		case SKELETON:
			return "Skeleton";
		case SPIDER:
			return "Spider";
		case GIANT_ZOMBIE:
			return "Giant zombie";
		case ZOMBIE:
			return "Zombie";
		case SLIME:
			return "Slime";
		case GHAST:
			return "Ghast";
		case ZOMBIE_PIGMAN:
			return "Zombie pigman";
		case ENDERMAN:
			return "Enderman";
		case CAVE_SPIDER:
			return "Cave spider";
		case SILVERFISH:
			return "Silverfish";
		case MAGMA_CUBE:
			return "Magma cube";
		case ENDER_DRAGON:
			return "Ender dragon";
		case PIG:
			return "Pig";
		case SHEEP:
			return "Sheep";
		case COW:
			return "Cow";
		case CHICKEN:
			return "Chicken";
		case SQUID:
			return "Squid";
		case WOLF:
			return "Wolf";
		case MOOSHROOM:
			return "Mooshroom";
		case SNOWMAN:
			return "Snowman";
		case VILLAGER:
			return "Villager";
		default:
			return "Unknown";
		}
	}
}
