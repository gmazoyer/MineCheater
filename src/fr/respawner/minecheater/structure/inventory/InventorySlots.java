package fr.respawner.minecheater.structure.inventory;

import fr.respawner.minecheater.metadata.Slotdata;

public final class InventorySlots {
    private byte windowID;
    private short count;
    private Slotdata slots;

    public InventorySlots(byte windowID, short count, Slotdata slots) {
        this.windowID = windowID;
        this.count = count;
        this.slots = slots;
    }

    public byte getWindowID() {
        return this.windowID;
    }

    public short getCount() {
        return this.count;
    }

    public Slotdata getSlots() {
        return this.slots;
    }

    @Override
    public String toString() {
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append("Window ID = ");
        builder.append(this.windowID);
        builder.append(" | Slots = ");
        builder.append(this.slots);

        return builder.toString();
    }
}
