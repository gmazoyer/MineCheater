package fr.respawner.minecheater.structure.entity;

/**
 * This class of all objects that can move in the Minecraft world (players,
 * mobs, etc...).
 * 
 * @author Guillaume Mazoyer
 */
public class MCEntity extends MCObject {
	private MCEntityMetadata metadata;
	private MCStatus status;
	private MCVelocity velocity;
	private MCAnimation lastAnimation;

	public MCEntity(int entityID, int x, int y, int z) {
		super(entityID, x, y, z);
	}

	public MCEntityMetadata getMetadata() {
		return this.metadata;
	}

	public void setMetadata(MCEntityMetadata metadata) {
		this.metadata = metadata;
	}

	public MCStatus getStatus() {
		return this.status;
	}

	public void setStatus(MCStatus status) {
		this.status = status;
	}

	public MCVelocity getVelocity() {
		return this.velocity;
	}

	public void setVelocity(MCVelocity velocity) {
		this.velocity = velocity;
	}

	public MCAnimation getLastAnimation() {
		return this.lastAnimation;
	}

	public void setLastAnimation(MCAnimation lastAnimation) {
		this.lastAnimation = lastAnimation;
	}
}
