package fr.respawner.minecheater.structure.block;

public final class ActionBlock {
	private int x;
	private short y;
	private int z;
	private byte firstByte;
	private byte secondByte;

	public ActionBlock(int x, short y, int z, byte firstByte, byte secondByte) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.firstByte = firstByte;
		this.secondByte = secondByte;
	}

	public int getX() {
		return this.x;
	}

	public short getY() {
		return this.y;
	}

	public int getZ() {
		return this.z;
	}

	public byte getFirstByte() {
		return this.firstByte;
	}

	public byte getSecondByte() {
		return this.secondByte;
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
		builder.append(" | Byte 1 = ");
		builder.append(this.firstByte);
		builder.append(" | Byte 2 = ");
		builder.append(this.secondByte);

		return builder.toString();
	}
}
