package fr.respawner.minecheater.nbt;

public final class FloatTag extends Tag {
    private final float value;

    public FloatTag(String name, float value) {
        super(name);
        this.value = value;
    }

    @Override
    public Float getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append("TAG_Float");

        if ((this.name != null) && !this.name.equals("")) {
            builder.append("(\"");
            builder.append(this.name);
            builder.append("\")");
        }

        builder.append(": ");
        builder.append(this.value);

        return builder.toString();
    }
}
