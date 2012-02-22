package fr.respawner.minecheater.nbt;

public final class ByteTag extends Tag {
	private final byte value;

	public ByteTag(String name, byte value) {
		super(name);
		this.value = value;
	}

	@Override
	public Byte getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		final String name;
		String append;

		name = getName();
		append = "";

		if ((name != null) && !name.equals("")) {
			append = "(\"" + this.getName() + "\")";
		}

		return "TAG_Byte" + append + ": " + this.value;
	}
}
