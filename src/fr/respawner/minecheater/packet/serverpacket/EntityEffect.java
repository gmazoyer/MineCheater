package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.entity.MCCharacter;
import fr.respawner.minecheater.structure.entity.MCEffect;
import fr.respawner.minecheater.structure.type.MCEffectType;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class EntityEffect extends Packet {
    private int entityID;
    private byte effectID;
    private byte amplifier;
    private short duration;

    public EntityEffect(PacketsHandler handler) {
        super(handler, (byte) 0x29);
    }

    @Override
    public void read() throws IOException {
        this.entityID = this.readInt();
        this.effectID = this.readByte();
        this.amplifier = this.readByte();
        this.duration = this.readShort();
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
        final MCEffect effect;

        /*
         * Find the character to set its effect.
         */
        character = (MCCharacter) this.getWorld().findObjectByID(this.entityID);
        effect = new MCEffect(this.effectID, this.amplifier, this.duration);

        if (character != null) {
            character.setEffect(effect);
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
        builder.append(" | Effect = ");
        builder.append(MCEffectType.effectForID(this.effectID));
        builder.append(" | Amplifier = ");
        builder.append(this.amplifier);
        builder.append(" | Duration = ");
        builder.append(this.duration);

        return builder.toString();
    }
}
