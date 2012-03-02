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

    private MCEquipment instance;

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
    public void parse() {
        final MCCharacter character;

        /*
         * Find the character to set its equipment.
         */
        character = (MCCharacter) this.getWorld().findObjectByID(this.entityID);
        this.instance = new MCEquipment(this.entityID, this.slot, this.itemID,
                this.damage);

        if (character != null) {
            character.setEquipment(this.instance);
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
    public Object getData() {
        return this.instance;
    }
}
