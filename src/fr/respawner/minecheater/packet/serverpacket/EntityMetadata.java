package fr.respawner.minecheater.packet.serverpacket;

import java.io.IOException;

import fr.respawner.minecheater.metadata.Metadata;
import fr.respawner.minecheater.packet.Packet;
import fr.respawner.minecheater.structure.entity.MCEntity;
import fr.respawner.minecheater.structure.entity.MCEntityMetadata;
import fr.respawner.minecheater.worker.PacketsHandler;

public final class EntityMetadata extends Packet {
	private int entityID;
	private Metadata metadata;

	private MCEntityMetadata instance;

	public EntityMetadata(PacketsHandler handler) {
		super(handler, (byte) 0x28);
	}

	@Override
	public void read() throws IOException {
		this.entityID = this.readInt();
		this.metadata = new Metadata(this.handler);
		this.metadata.parse();
	}

	@Override
	public void write() throws IOException {
		/*
		 * We don't write this packet.
		 */
	}

	@Override
	public void process() {
		final MCEntity entity;

		/*
		 * Find the entity to set the metadata.
		 */
		entity = (MCEntity) this.getWorld().findObjectByID(this.entityID);
		this.instance = new MCEntityMetadata(this.entityID, this.metadata);

		if (entity != null) {
			entity.setMetadata(this.instance);
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
