package fr.respawner.minecheater.nbt;

public abstract class Tag {
    protected final String name;

    public Tag(String name) {
        this.name = name;
    }

    public final String getName() {
        return this.name;
    }

    public abstract Object getValue();
}
