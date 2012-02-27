package fr.respawner.minecheater.structure;

public final class MCExperience {
    private float experienceBar;
    private short level;
    private short total;

    public MCExperience(float experienceBar, short level, short total) {
        this.experienceBar = experienceBar;
        this.level = level;
        this.total = total;
    }

    public final float getExperienceBar() {
        return this.experienceBar;
    }

    public final void setExperienceBar(float experienceBar) {
        this.experienceBar = experienceBar;
    }

    public final short getLevel() {
        return this.level;
    }

    public final void setLevel(short level) {
        this.level = level;
    }

    public final short getTotal() {
        return this.total;
    }

    public final void setTotal(short total) {
        this.total = total;
    }

    @Override
    public String toString() {
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append("Experience bar = ");
        builder.append(this.experienceBar);
        builder.append(" | Level = ");
        builder.append(this.level);
        builder.append(" | Total = ");
        builder.append(this.total);

        return builder.toString();
    }
}
