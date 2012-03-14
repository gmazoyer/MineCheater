/*
 * Copyright (c) 2012 Guillaume Mazoyer
 * 
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
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
    private final DataInputStream input;

    public NBTInputStream(InputStream input) throws IOException {
        this.input = new DataInputStream(new GZIPInputStream(input));
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
            return new ByteTag(name, this.input.readByte());
        case Constants.TYPE_SHORT:
            return new ShortTag(name, this.input.readShort());
        case Constants.TYPE_INT:
            return new IntTag(name, this.input.readInt());
        case Constants.TYPE_LONG:
            return new LongTag(name, this.input.readLong());
        case Constants.TYPE_FLOAT:
            return new FloatTag(name, this.input.readFloat());
        case Constants.TYPE_DOUBLE:
            return new DoubleTag(name, this.input.readDouble());
        case Constants.TYPE_BYTE_ARRAY:
            length = this.input.readInt();
            bytes = new byte[length];

            this.input.readFully(bytes);

            return new ByteArrayTag(name, bytes);
        case Constants.TYPE_STRING:
            length = this.input.readShort();
            bytes = new byte[length];

            this.input.readFully(bytes);

            return new StringTag(name, new String(bytes, Constants.CHARSET));
        case Constants.TYPE_LIST:
            childType = this.input.readByte();
            length = this.input.readInt();

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
        final String name;

        type = this.input.readByte() & 0xFF;

        if (type == Constants.TYPE_END) {
            name = "";
        } else {
            nameLength = input.readShort() & 0xFFFF;
            nameBytes = new byte[nameLength];

            this.input.readFully(nameBytes);

            name = new String(nameBytes, Constants.CHARSET);
        }

        return this.readTagPayload(type, name, depth);
    }

    public Tag readTag() throws IOException {
        return this.readTag(0);
    }

    @Override
    public void close() throws IOException {
        this.input.close();
    }
}
