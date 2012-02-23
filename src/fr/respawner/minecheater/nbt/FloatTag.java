package fr.respawner.minecheater.nbt;

public final class FloatTag extends Tag {
	private final float value;

	public FloatTag(String name, float value) {
		super(name);
		this.value = value;
	}

	@Override
	public Float getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		final String name;
		String append;

		name = this.getName();

		append = "";
		if ((name != null) && !name.equals("")) {
			append = "(\"" + this.getName() + "\")";
		}

		return "TAG_Float" + append + ": " + this.value;
	}
}
