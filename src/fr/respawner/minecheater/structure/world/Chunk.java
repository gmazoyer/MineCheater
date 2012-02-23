package fr.respawner.minecheater.structure.world;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.InflaterInputStream;

public final class Chunk {
	private int x;
	private short y;
	private int z;
	private byte sizeX;
	private byte sizeY;
	private byte sizeZ;
	private byte[] data;

	public Chunk(int x, short y, int z, byte sizeX, byte sizeY, byte sizeZ,
			byte[] data) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.sizeZ = sizeZ;
		this.data = this.inflateData(data);
	}

	private byte[] inflateData(byte[] data) {
		final byte[] inflated;
		final List<Byte> uncompressed;
		final InflaterInputStream inflater;
		int read;

		/*
		 * Initialize the inflater.
		 */
		uncompressed = new ArrayList<>();
		inflater = new InflaterInputStream(new ByteArrayInputStream(data));

		try {
			/*
			 * Inflate the data.
			 */
			while ((read = inflater.read()) != -1) {
				uncompressed.add((byte) read);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inflater.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		/*
		 * Put them in an array.
		 */
		inflated = new byte[uncompressed.size()];
		for (int i = 0; i < inflated.length; i++) {
			inflated[i] = uncompressed.get(i);
		}

		return inflated;
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

	public byte getSizeX() {
		return this.sizeX;
	}

	public byte getSizeY() {
		return this.sizeY;
	}

	public byte getSizeZ() {
		return this.sizeZ;
	}

	public byte[] getData() {
		return this.data;
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
		builder.append(" | Size: x = ");
		builder.append(this.sizeX);
		builder.append(", y = ");
		builder.append(this.sizeY);
		builder.append(", z = ");
		builder.append(this.sizeZ);
		builder.append(" | Data = { ");
		for (byte b : this.data) {
			builder.append(b);
			builder.append(", ");
		}
		builder.replace(builder.length() - 2, builder.length(), " }");

		return builder.toString();
	}
}
