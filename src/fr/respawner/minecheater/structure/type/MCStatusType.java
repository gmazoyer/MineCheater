package fr.respawner.minecheater.structure.type;

public enum MCStatusType {
        UNKNOWN, HURT, DEAD, TAMING, TAMED, SHAKING_WATER, EATING;

        public static MCStatusType statusForID(byte id) {
            switch (id) {
            case 2:
                return HURT;
            case 3:
                return DEAD;
            case 6:
                return TAMING;
            case 7:
                return TAMED;
            case 8:
                return SHAKING_WATER;
            case 9:
                return EATING;
            default:
                return UNKNOWN;
            }
        }

        @Override
        public String toString() {
            switch (this) {
            case HURT:
                return "Hurt";
            case DEAD:
                return "Dead";
            case TAMING:
                return "Taming";
            case TAMED:
                return "Tamed";
            case SHAKING_WATER:
                return "Shaking water off itself";
            case EATING:
                return "Eating";
            default:
                return "Unknown";
            }
        }
}
