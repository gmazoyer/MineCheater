package fr.respawner.minecheater.structure.entity;

import fr.respawner.minecheater.structure.inventory.MCEquipment;

/**
 * This is the class that represents a named entity. It could be a player - but
 * not our player - or a NPC.
 * 
 * @author Guillaume Mazoyer
 */
public final class MCCharacter extends MCEntity {
	private String name;
	private byte rotation;
	private byte pitch;
	private short item;
	private MCEquipment equipment;

	public MCCharacter(int entityID, String name, int x, int y, int z,
			byte rotation, byte pitch, short item) {
		super(entityID, x, y, z);

		this.name = name;
		this.rotation = rotation;
		this.pitch = pitch;
		this.item = item;
	}

	public String getName() {
		return this.name;
	}

	public byte getRotation() {
		return this.rotation;
	}

	public byte getPitch() {
		return this.pitch;
	}

	public short getItem() {
		return this.item;
	}

	public MCEquipment getEquipment() {
		return this.equipment;
	}

	public void setEquipment(MCEquipment equipment) {
		this.equipment = equipment;
	}

	@Override
	public String toString() {
		final StringBuilder builder;

		builder = new StringBuilder();

		builder.append("Entity ID = ");
		builder.append(this.getEntityID());
		builder.append(" | Name = ");
		builder.append(this.name);
		builder.append(" | Position: x = ");
		builder.append(this.getX());
		builder.append(", y = ");
		builder.append(this.getY());
		builder.append(", z = ");
		builder.append(this.getZ());
		builder.append(", rotation = ");
		builder.append(this.rotation);
		builder.append(", pitch = ");
		builder.append(this.pitch);
		builder.append(" | Item = ");
		builder.append(this.item == 0 ? "Nothing" : this.item);

		return builder.toString();
	}
}
