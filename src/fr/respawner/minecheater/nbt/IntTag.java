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
        final String name;
        String append;

        name = this.getName();

        append = "";
        if ((name != null) && !name.equals("")) {
            append = "(\"" + this.getName() + "\")";
        }

        return "TAG_Int" + append + ": " + this.value;
    }
}
