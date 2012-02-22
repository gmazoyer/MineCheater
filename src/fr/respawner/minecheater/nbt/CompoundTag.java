package fr.respawner.minecheater.nbt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class CompoundTag extends Tag {
	private final Map<String, Tag> value;

	public CompoundTag(String name, Map<String, Tag> value) {
		super(name);
		this.value = Collections.unmodifiableMap(value);
	}

	public List<short[]> toEnchantList() {
		final List<short[]> list;
		final ListTag listTag;

		list = new ArrayList<>();
		listTag = (ListTag) this.value.get("ench");

		for (Tag tag : listTag.getValue()) {
			final CompoundTag compound;
			final short[] enchant;

			compound = (CompoundTag) tag;
			enchant = new short[2];

			enchant[0] = ((ShortTag) compound.value.get("id")).getValue();
			enchant[1] = ((ShortTag) compound.value.get("lvl")).getValue();

			list.add(enchant);
		}

		return list;
	}

	@Override
	public Map<String, Tag> getValue() {
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
		builder.append("TAG_Compound" + append + ": " + this.value.size()
				+ " entries\r\n{\r\n");

		for (Map.Entry<String, Tag> entry : this.value.entrySet()) {
			builder.append("   "
					+ entry.getValue().toString().replaceAll("\r\n", "\r\n   ")
					+ "\r\n");
		}
		builder.append("}");

		return builder.toString();
	}
}
