package fr.respawner.minecheater.nbt;

public final class ShortTag extends Tag {
    private final short value;

    public ShortTag(String name, short value) {
        super(name);
        this.value = value;
    }

    @Override
    public Short getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        final String name;
        String append;

        name = this.getName();

        append = "";
        if ((name != null) && !name.equals("")) {
            append = "(\"" + this.getName() + "\")";
        }

        return "TAG_Short" + append + ": " + this.value;
    }
}
