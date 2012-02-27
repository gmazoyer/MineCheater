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
        final String name;
        String append;

        name = this.getName();

        append = "";
        if ((name != null) && !name.equals("")) {
            append = "(\"" + this.getName() + "\")";
        }

        return "TAG_String" + append + ": " + this.value;
    }
}
