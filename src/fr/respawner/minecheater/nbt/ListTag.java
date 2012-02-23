package fr.respawner.minecheater.nbt;

import java.util.Collections;
import java.util.List;

public final class ListTag extends Tag {
	private final Class<? extends Tag> type;
	private final List<Tag> value;

	public ListTag(String name, Class<? extends Tag> type, List<Tag> value) {
		super(name);
		this.type = type;
		this.value = Collections.unmodifiableList(value);
	}

	public Class<? extends Tag> getType() {
		return this.type;
	}

	@Override
	public List<Tag> getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		final StringBuilder builder;
		final String name;
		String append;

		name = this.getName();

		append = "";
		if ((name != null) && !name.equals("")) {
			append = "(\"" + this.getName() + "\")";
		}

		builder = new StringBuilder();
		builder.append("TAG_List" + append + ": " + this.value.size()
				+ " entries of type " + Utils.getTypeName(type) + "\r\n{\r\n");
		for (Tag t : this.value) {
			builder.append("   " + t.toString().replaceAll("\r\n", "\r\n   ")
					+ "\r\n");
		}
		builder.append("}");

		return builder.toString();
	}
}
