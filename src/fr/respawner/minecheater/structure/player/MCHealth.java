package fr.respawner.minecheater.structure.player;

public final class MCHealth {
    private short health;
    private short food;
    private float foodSaturation;

    public MCHealth(short health, short food, float foodSaturation) {
        this.health = health;
        this.food = food;
        this.foodSaturation = foodSaturation;
    }

    public short getHealth() {
        return this.health;
    }

    public short getFood() {
        return this.food;
    }

    public float getFoodSaturation() {
        return this.foodSaturation;
    }

    @Override
    public String toString() {
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append("Health = ");
        builder.append(this.health);
        if (this.health == 0) {
            builder.append(" (dead)");
        } else if (this.health == 20) {
            builder.append(" (full HP)");
        }
        builder.append(" | Food = ");
        builder.append(this.food);
        builder.append(" | Food saturation = ");
        builder.append(this.foodSaturation);

        return builder.toString();
    }
}
