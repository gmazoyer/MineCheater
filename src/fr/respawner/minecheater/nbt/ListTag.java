package fr.respawner.minecheater.nbt;

import java.util.Collections;
import java.util.List;

import fr.respawner.minecheater.packet.Packet;

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

        builder = new StringBuilder();

        builder.append("TAG_List");

        if ((this.name != null) && !this.name.equals("")) {
            builder.append("(\"");
            builder.append(this.name);
            builder.append("\")");
        }

        builder.append(": ");
        builder.append(this.value.size());
        builder.append(" entries of type ");
        builder.append(Utils.getTypeName(this.type));
        builder.append(Packet.LINE_SEPARATOR);
        builder.append("{");
        builder.append(Packet.LINE_SEPARATOR);

        for (Tag tag : this.value) {
            builder.append("   ");
            builder.append(tag.toString().replaceAll(Packet.LINE_SEPARATOR,
                    Packet.LINE_SEPARATOR + "   "));
            builder.append(Packet.LINE_SEPARATOR);
        }

        builder.append("}");

        return builder.toString();
    }
}
