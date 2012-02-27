package fr.respawner.minecheater.nbt;

import java.io.Closeable;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

public final class NBTInputStream implements Closeable {
    private final DataInputStream is;

    public NBTInputStream(InputStream is) throws IOException {
        this.is = new DataInputStream(new GZIPInputStream(is));
    }

    private Tag readTagPayload(int type, String name, int depth)
            throws IOException {
        final int length, childType;
        final byte[] bytes;
        final List<Tag> tagList;
        final Map<String, Tag> tagMap;

        switch (type) {
        case Constants.TYPE_END:
            if (depth == 0) {
                throw new IOException(
                        "TAG_End found without a TAG_Compound/TAG_List tag preceding it.");
            } else {
                return new EndTag();
            }
        case Constants.TYPE_BYTE:
            return new ByteTag(name, is.readByte());
        case Constants.TYPE_SHORT:
            return new ShortTag(name, is.readShort());
        case Constants.TYPE_INT:
            return new IntTag(name, is.readInt());
        case Constants.TYPE_LONG:
            return new LongTag(name, is.readLong());
        case Constants.TYPE_FLOAT:
            return new FloatTag(name, is.readFloat());
        case Constants.TYPE_DOUBLE:
            return new DoubleTag(name, is.readDouble());
        case Constants.TYPE_BYTE_ARRAY:
            length = is.readInt();
            bytes = new byte[length];

            is.readFully(bytes);

            return new ByteArrayTag(name, bytes);
        case Constants.TYPE_STRING:
            length = is.readShort();
            bytes = new byte[length];

            is.readFully(bytes);

            return new StringTag(name, new String(bytes, Constants.CHARSET));
        case Constants.TYPE_LIST:
            childType = is.readByte();
            length = is.readInt();

            tagList = new ArrayList<Tag>();
            for (int i = 0; i < length; i++) {
                final Tag tag;

                tag = readTagPayload(childType, "", depth + 1);
                if (tag instanceof EndTag) {
                    throw new IOException("TAG_End not permitted in a list.");
                }
                tagList.add(tag);
            }

            return new ListTag(name, Utils.getTypeClass(childType), tagList);
        case Constants.TYPE_COMPOUND:
            tagMap = new HashMap<String, Tag>();

            while (true) {
                final Tag tag;

                tag = readTag(depth + 1);
                if (tag instanceof EndTag) {
                    break;
                } else {
                    tagMap.put(tag.getName(), tag);
                }
            }

            return new CompoundTag(name, tagMap);
        default:
            throw new IOException("Invalid tag type: " + type + ".");
        }
    }

    private Tag readTag(int depth) throws IOException {
        final int type, nameLength;
        final byte[] nameBytes;
        String name;

        type = is.readByte() & 0xFF;

        if (type == Constants.TYPE_END) {
            name = "";
        } else {
            nameLength = is.readShort() & 0xFFFF;
            nameBytes = new byte[nameLength];

            is.readFully(nameBytes);

            name = new String(nameBytes, Constants.CHARSET);
        }

        return this.readTagPayload(type, name, depth);
    }

    public Tag readTag() throws IOException {
        return this.readTag(0);
    }

    @Override
    public void close() throws IOException {
        is.close();
    }
}
