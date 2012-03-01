package fr.respawner.minecheater.structure.entity;

public final class MCBed extends MCObject {
    private byte inBed;

    public MCBed(int entityID, byte inBed, int x, byte y, int z) {
        super(entityID, x, y, z);

        this.inBed = inBed;
    }

    public byte getInBed() {
        return inBed;
    }

    @Override
    public String toString() {
        return super.toString() + " | In bed = " + this.inBed;
    }
}
