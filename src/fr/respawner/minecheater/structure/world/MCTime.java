package fr.respawner.minecheater.structure.world;

public final class MCTime {
    private long time;

    public MCTime(long time) {
        this.time = time;
    }

    public final long getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        final String description;

        if (this.time == 0) {
            description = "Sunrise";
        } else if (this.time == 6000) {
            description = "Noon";
        } else if (this.time == 12000) {
            description = "Sunset";
        } else if (this.time == 18000) {
            description = "Midnight";
        } else if ((this.time > 0) && (this.time < 6000)) {
            description = "Morning";
        } else if ((this.time > 6000) && (this.time < 12000)) {
            description = "Afternoon";
        } else if (this.time > 12000) {
            description = "Night";
        } else {
            description = "What time is it (" + this.time + ")?";
        }

        return description;
    }
}
