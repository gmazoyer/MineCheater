package fr.respawner.minecheater.structure.entity;

public final class MCStatus {
    private int entityID;
    private byte status;

    public MCStatus(int entityID, byte status) {
        this.entityID = entityID;
        this.status = status;
    }

    public final int getEntityID() {
        return this.entityID;
    }

    public final byte getStatus() {
        return this.status;
    }

    @Override
    public String toString() {
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append("Entity ID = ");
        builder.append(this.entityID);
        builder.append(" | Status = ");
        builder.append(CurrentStatus.statusForID(this.status));

        return builder.toString();
    }

    public enum CurrentStatus {
        UNKNOWN, HURT, DEAD, TAMING, TAMED, SHAKING_WATER, EATING;

        public static CurrentStatus statusForID(byte id) {
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
}
