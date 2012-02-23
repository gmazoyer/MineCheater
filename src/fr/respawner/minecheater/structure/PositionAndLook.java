package fr.respawner.minecheater.structure;

public final class PositionAndLook {
	private double x;
	private double y;
	private double z;
	private double stance;
	private float yaw;
	private float pitch;
	private boolean onGround;

	public PositionAndLook(double x, double y, double z, double stance,
			float yaw, float pitch, boolean onGround) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.stance = stance;
		this.yaw = yaw;
		this.pitch = pitch;
		this.onGround = onGround;
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	public double getZ() {
		return this.z;
	}

	public double getStance() {
		return this.stance;
	}

	public float getYaw() {
		return this.yaw;
	}

	public float getPitch() {
		return this.pitch;
	}

	public boolean isOnGround() {
		return this.onGround;
	}

	@Override
	public String toString() {
		final StringBuilder builder;

		builder = new StringBuilder();

		builder.append("Position: x = ");
		builder.append(this.x);
		builder.append(", y = ");
		builder.append(this.y);
		builder.append(", z = ");
		builder.append(this.z);
		builder.append(", yaw = ");
		builder.append(this.yaw);
		builder.append(", pitch = ");
		builder.append(this.pitch);
		builder.append(" | ");
		builder.append(this.onGround ? "Walking or Swimming"
				: "Jumping or Falling");

		return builder.toString();
	}
}
