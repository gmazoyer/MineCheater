package fr.respawner.minecheater.nbt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import fr.respawner.minecheater.packet.Packet;

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

        builder = new StringBuilder();

        builder.append("TAG_Compound");

        if ((this.name != null) && !this.name.equals("")) {
            builder.append("(\"");
            builder.append(this.name);
            builder.append("\")");
        }

        builder.append(": ");
        builder.append(this.value.size());
        builder.append(" entries");
        builder.append(Packet.LINE_SEPARATOR);
        builder.append("{");
        builder.append(Packet.LINE_SEPARATOR);

        for (Map.Entry<String, Tag> entry : this.value.entrySet()) {
            builder.append("   ");
            builder.append(entry
                    .getValue()
                    .toString()
                    .replaceAll(Packet.LINE_SEPARATOR,
                            Packet.LINE_SEPARATOR + "   "));
            builder.append(Packet.LINE_SEPARATOR);
        }

        builder.append("}");

        return builder.toString();
    }
}
