package fr.respawner.minecheater.structure.type;

public enum MCAnimationType {
    UNKNOWN,
    NO_ANIMATION,
    SWING_ARM,
    DAMAGE_ANIMATION,
    LEAVE_BED,
    EAT_FOOD,
    CROUCH,
    UNCROUCH;

    public static MCAnimationType animationForID(byte id) {
        switch (id) {
        case 0:
            return NO_ANIMATION;
        case 1:
            return SWING_ARM;
        case 2:
            return DAMAGE_ANIMATION;
        case 3:
            return LEAVE_BED;
        case 5:
            return EAT_FOOD;
        case 104:
            return CROUCH;
        case 105:
            return UNCROUCH;
        default:
            return UNKNOWN;
        }
    }

    @Override
    public String toString() {
        switch (this) {
        case NO_ANIMATION:
            return "No animation";
        case SWING_ARM:
            return "Swing arm";
        case DAMAGE_ANIMATION:
            return "Damage animation";
        case LEAVE_BED:
            return "Leave bed";
        case EAT_FOOD:
            return "Eat food";
        case CROUCH:
            return "Crouch";
        case UNCROUCH:
            return "Uncrouch";
        default:
            return "Unknown";
        }
    }
}
