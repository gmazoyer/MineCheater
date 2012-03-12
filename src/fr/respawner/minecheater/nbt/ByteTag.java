package fr.respawner.minecheater.nbt;

public final class ByteTag extends Tag {
    private final byte value;

    public ByteTag(String name, byte value) {
        super(name);
        this.value = value;
    }

    @Override
    public Byte getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append("TAG_Byte");

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
