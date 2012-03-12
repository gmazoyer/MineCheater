package fr.respawner.minecheater.nbt;


public final class ByteArrayTag extends Tag {
    private final byte[] value;

    public ByteArrayTag(String name, byte[] value) {
        super(name);
        this.value = value;
    }

    @Override
    public byte[] getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append("TAG_Byte_Array");

        if ((this.name != null) && !this.name.equals("")) {
            builder.append("(\"");
            builder.append(this.name);
            builder.append("\")");
        }

        builder.append(": ");

        for (byte b : this.value) {
            final String hexDigits;

            hexDigits = Integer.toHexString(b).toUpperCase();

            if (hexDigits.length() == 1) {
                builder.append("0");
            }

            builder.append(hexDigits).append(" ");
        }

        return builder.toString();
    }
}
