package fr.respawner.minecheater.structure.entity;

public final class MCThunderbolt extends MCObject {
    private boolean unknown;

    public MCThunderbolt(int entityID, boolean unknown, int x, int y, int z) {
        super(entityID, x, y, z);

        this.unknown = unknown;
    }

    public final boolean isUnknown() {
        return this.unknown;
    }
}
