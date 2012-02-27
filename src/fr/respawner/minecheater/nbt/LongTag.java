package fr.respawner.minecheater.nbt;

public final class LongTag extends Tag {
    private final long value;

    public LongTag(String name, long value) {
        super(name);
        this.value = value;
    }

    @Override
    public Long getValue() {
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

        return "TAG_Long" + append + ": " + this.value;
    }

}
