package fr.respawner.minecheater.structure.entity;

public enum EntityEffectType {
    UNKNOWN,
    MOVE_SPEED,
    MOVE_SLOWDOWN,
    DIG_SPEED,
    DIG_SLOWDOWN,
    DAMAGE_BOOST,
    HEAL,
    HARM,
    JUMP,
    CONFUSION,
    REGENERATION,
    RESISTANCE,
    FIRE_RESISTANCE,
    WATER_BREATHING,
    INVISIBILITY,
    BLINDNESS,
    NIGHT_VISION,
    HUNGER,
    WEAKNESS,
    POISON;

    public static EntityEffectType effectForID(byte id) {
        switch (id) {
        case 1:
            return MOVE_SPEED;
        case 2:
            return MOVE_SLOWDOWN;
        case 3:
            return DIG_SPEED;
        case 4:
            return DIG_SLOWDOWN;
        case 5:
            return DAMAGE_BOOST;
        case 6:
            return HEAL;
        case 7:
            return HARM;
        case 8:
            return JUMP;
        case 9:
            return CONFUSION;
        case 10:
            return REGENERATION;
        case 11:
            return RESISTANCE;
        case 12:
            return FIRE_RESISTANCE;
        case 13:
            return WATER_BREATHING;
        case 14:
            return INVISIBILITY;
        case 15:
            return BLINDNESS;
        case 16:
            return NIGHT_VISION;
        case 17:
            return HUNGER;
        case 18:
            return WEAKNESS;
        case 19:
            return POISON;
        default:
            return UNKNOWN;
        }
    }

    @Override
    public String toString() {
        switch (this) {
        case MOVE_SPEED:
            return "Move speed";
        case MOVE_SLOWDOWN:
            return "Move slowdown";
        case DIG_SPEED:
            return "Dig speed";
        case DIG_SLOWDOWN:
            return "Dig slowdown";
        case DAMAGE_BOOST:
            return "Damage boost";
        case HEAL:
            return "Heal";
        case HARM:
            return "Harm";
        case JUMP:
            return "Jump";
        case CONFUSION:
            return "Confusion";
        case REGENERATION:
            return "Regeneration";
        case RESISTANCE:
            return "Resistance";
        case FIRE_RESISTANCE:
            return "Fire resistance";
        case WATER_BREATHING:
            return "Water breathing";
        case INVISIBILITY:
            return "Invisibility";
        case BLINDNESS:
            return "Blindness";
        case NIGHT_VISION:
            return "Night vision";
        case HUNGER:
            return "Hunger";
        case WEAKNESS:
            return "Weakness";
        case POISON:
            return "Poison";
        default:
            return "Unknown";
        }
    }
}
