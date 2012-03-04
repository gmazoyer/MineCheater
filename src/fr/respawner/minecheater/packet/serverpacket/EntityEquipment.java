package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.entity.MCCharacter;
import fr.respawner.minecheater.structure.inventory.MCEquipment;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class EntityEquipment extends Packet {
    private int entityID;
    private short slot;
    private short itemID;
    private short damage;

    public EntityEquipment(PacketsHandler handler) {
        super(handler, (byte) 0x05);
    }

    @Override
    public void read() throws IOException {
        this.entityID = this.readInt();
        this.slot = this.readShort();
        this.itemID = this.readShort();
        this.damage = this.readShort();
    }

    @Override
    public void write() throws IOException {
        /*
         * We don't write this packet.
         */
    }

    @Override
    public void process() {
        final MCCharacter character;
        final MCEquipment equipment;

        /*
         * Find the character to set its equipment.
         */
        character = (MCCharacter) this.getWorld().findObjectByID(this.entityID);
        equipment = new MCEquipment(this.entityID, this.slot, this.itemID,
                this.damage);

        if (character != null) {
            character.setEquipment(equipment);
        }
    }

    @Override
    public Packet response() {
        /*
         * We don't send a response to this packet.
         */
        return null;
    }

    @Override
    public String getDataAsString() {
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append("Entity ID = ");
        builder.append(this.entityID);
        builder.append(" | Slot = ");
        builder.append(this.slot == 0 ? "held" : ((this.slot) >= 1)
                && (this.slot <= 4) ? "armor" : this.slot);
        builder.append(" | Item ID = ");
        builder.append(this.itemID == -1 ? "no item" : this.itemID);
        builder.append(" | Damage = ");
        builder.append(this.damage);

        return builder.toString();
    }
}
