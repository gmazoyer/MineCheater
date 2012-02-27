package fr.respawner.minecheater.structure.entity;

public final class MCObjectVehicle extends MCEntity {
    private byte type;
    private int throwerID;
    private short speedX;
    private short speedY;
    private short speedZ;

    public MCObjectVehicle(int entityID, byte type, int x, int y, int z,
            int throwerID, short speedX, short speedY, short speedZ) {
        super(entityID, x, y, z);
        this.type = type;
        this.throwerID = throwerID;
        this.speedX = speedX;
        this.speedY = speedY;
        this.speedZ = speedZ;
    }

    public final byte getType() {
        return this.type;
    }

    public final int getThrowerID() {
        return this.throwerID;
    }

    public final short getSpeedX() {
        return this.speedX;
    }

    public final short getSpeedY() {
        return this.speedY;
    }

    public final short getSpeedZ() {
        return this.speedZ;
    }

    @Override
    public String toString() {
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append("Entity ID = ");
        builder.append(this.getEntityID());
        builder.append(" | Type = ");
        builder.append(ObjectVehicleType.objectForID(this.type));
        builder.append(" | Position: x = ");
        builder.append(this.getX());
        builder.append(", y = ");
        builder.append(this.getY());
        builder.append(", z = ");
        builder.append(this.getZ());

        if (this.throwerID > 0) {
            builder.append(" | Throwed of = ");
            builder.append(this.throwerID);
            builder.append(" | Speed: x = ");
            builder.append(this.speedX);
            builder.append(", y = ");
            builder.append(this.speedY);
            builder.append(", z = ");
            builder.append(this.speedZ);
        }

        return builder.toString();
    }

    public enum ObjectVehicleType {
        UNKNOWN,
        BOAT,
        MINECART,
        MINECART_STORAGE,
        MINECART_POWERED,
        ACTIVATED_TNT,
        ENDER_CRYSTAL,
        ARROW,
        SNOWBALL,
        EGG,
        FALLING_SAND,
        FALLING_GRAVEL,
        EYE_OF_ENDER,
        FALLING_DRAGON_EGG,
        FISHING_FLOAT;

        public static ObjectVehicleType objectForID(byte id) {
            switch (id) {
            case 1:
                return BOAT;
            case 10:
                return MINECART;
            case 11:
                return MINECART_STORAGE;
            case 12:
                return MINECART_POWERED;
            case 50:
                return ACTIVATED_TNT;
            case 51:
                return ENDER_CRYSTAL;
            case 60:
                return ARROW;
            case 61:
                return SNOWBALL;
            case 62:
                return EGG;
            case 70:
                return FALLING_SAND;
            case 71:
                return FALLING_GRAVEL;
            case 72:
                return EYE_OF_ENDER;
            case 74:
                return FALLING_DRAGON_EGG;
            case 90:
                return FISHING_FLOAT;
            default:
                return UNKNOWN;
            }
        }

        @Override
        public String toString() {
            switch (this) {
            case BOAT:
                return "Boat";
            case MINECART:
                return "Minecart";
            case MINECART_STORAGE:
                return "Minecart (storage)";
            case MINECART_POWERED:
                return "Minecart (powered)";
            case ACTIVATED_TNT:
                return "Activated TNT";
            case ENDER_CRYSTAL:
                return "Ender crystal";
            case ARROW:
                return "Arrow";
            case SNOWBALL:
                return "Snowball";
            case EGG:
                return "Egg";
            case FALLING_SAND:
                return "Falling sand";
            case FALLING_GRAVEL:
                return "Falling gravel";
            case EYE_OF_ENDER:
                return "Eye of Ender";
            case FALLING_DRAGON_EGG:
                return "Falling dragon egg";
            case FISHING_FLOAT:
                return "Fishing float";
            default:
                return "Unknown";
            }
        }
    }
}
