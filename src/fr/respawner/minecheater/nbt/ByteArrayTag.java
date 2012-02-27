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
        final StringBuilder hex;
        final String name;
        String append;

        hex = new StringBuilder();

        for (byte b : this.value) {
            final String hexDigits;

            hexDigits = Integer.toHexString(b).toUpperCase();

            if (hexDigits.length() == 1) {
                hex.append("0");
            }

            hex.append(hexDigits).append(" ");
        }

        name = this.getName();

        append = "";
        if ((name != null) && !name.equals("")) {
            append = "(\"" + this.getName() + "\")";
        }

        return "TAG_Byte_Array" + append + ": " + hex.toString();
    }
}
