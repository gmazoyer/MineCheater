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
        final String name;
        String append;

        name = this.getName();

        append = "";
        if ((name != null) && !name.equals("")) {
            append = "(\"" + this.getName() + "\")";
        }

        return "TAG_Double" + append + ": " + this.value;
    }
}
