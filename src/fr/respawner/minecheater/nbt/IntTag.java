package fr.respawner.minecheater.nbt;

public final class IntTag extends Tag {
    private final int value;

    public IntTag(String name, int value) {
        super(name);
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append("TAG_Int");

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
