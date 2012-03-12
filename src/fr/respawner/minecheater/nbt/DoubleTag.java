package fr.respawner.minecheater.nbt;

public final class DoubleTag extends Tag {
    private final double value;

    public DoubleTag(String name, double value) {
        super(name);
        this.value = value;
    }

    @Override
    public Double getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append("TAG_Double");

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
