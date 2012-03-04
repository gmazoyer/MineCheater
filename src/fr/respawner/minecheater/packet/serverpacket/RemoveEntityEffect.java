package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.entity.MCCharacter;
import fr.respawner.minecheater.structure.type.MCEffectType;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class RemoveEntityEffect extends Packet {
    private int entityID;
    private byte effectID;

    public RemoveEntityEffect(PacketsHandler handler) {
        super(handler, (byte) 0x2A);
    }

    @Override
    public void read() throws IOException {
        this.entityID = this.readInt();
        this.effectID = this.readByte();
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

        /*
         * Find the character to remove its effect.
         */
        character = (MCCharacter) this.getWorld().findObjectByID(this.entityID);

        if (character != null) {
            character.setEffect(null);
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

        return builder.toString();
    }
}
