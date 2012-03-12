package fr.respawner.minecheater.nbt;

public final class StringTag extends Tag {
    private final String value;

    public StringTag(String name, String value) {
        super(name);
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append("TAG_String");

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
