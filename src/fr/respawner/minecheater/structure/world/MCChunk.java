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
package fr.respawner.minecheater.structure.world;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.InflaterInputStream;

import fr.respawner.minecheater.math.Vector;

public final class MCChunk {
    private MCMapColumn column;
    private boolean groundUpContinuous;
    private short primaryBitMap;
    private short addBitMap;
    private byte[] data;
    private List<MCBlock> blocks;

    public MCChunk(MCMapColumn column, boolean groundUpContinuous,
            short primaryBitMap, short addBitMap, byte[] zlibData) {
        this.column = column;
        this.groundUpContinuous = groundUpContinuous;
        this.primaryBitMap = primaryBitMap;
        this.addBitMap = addBitMap;
        this.data = this.inflateData(zlibData);
        this.blocks = new ArrayList<>();
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

    public void load() {
        final ByteArrayInputStream buffer;

        buffer = new ByteArrayInputStream(this.data);

        /*
         * Extract all the blocks from the column.
         */
        for (byte i = 0; i < 16; i++) {
            final byte[] blockIDs;

            blockIDs = new byte[4096];

            if ((this.primaryBitMap & (1 << i)) != 1) {
                /*
                 * The chunk is just full of air.
                 */
                Arrays.fill(blockIDs, (byte) 0);
            } else {
                try {
                    /*
                     * Get all block types data.
                     */
                    buffer.read(blockIDs);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                for (short j = 0; j < blockIDs.length; j++) {
                    final MCBlock block;
                    final int x, y, z;

                    /*
                     * Block coordinates.
                     */
                    x = ((this.column.getX() * 16) + j) & 0x0F;
                    y = ((i * 16) + j) >> 8;
                    z = ((this.column.getZ() * 16) + (j & 0xF0)) >> 4;
                    block = new MCBlock(blockIDs[j], x, y, z);

                    this.blocks.add(block);
                }
            }
        }

        /*
         * Extract all the block metadata.
         */
        for (byte i = 0; i < 16; i++) {
            int block;

            block = 0;

            if ((this.primaryBitMap & (1 << i)) == 1) {
                final byte[] metadata;

                metadata = new byte[2048];

                try {
                    /*
                     * Get all block types data.
                     */
                    buffer.read(metadata);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                for (short j = 0; j < metadata.length; j++) {
                    final byte[] data;

                    data = new byte[2];

                    /*
                     * Get the data for 2 blocks.
                     */
                    data[0] = (byte) (metadata[j] & 0x0F);
                    data[1] = (byte) (metadata[j] >> 4);

                    /*
                     * Set the block data.
                     */
                    this.blocks.get(block++).setMetadata(data[0]);
                    this.blocks.get(block++).setMetadata(data[1]);
                }
            }
        }

        /*
         * Extract all the block lights.
         */
        for (byte i = 0; i < 16; i++) {
            int block;

            block = 0;

            if ((this.primaryBitMap & (1 << i)) == 1) {
                final byte[] lights;

                lights = new byte[2048];

                try {
                    /*
                     * Get all block types data.
                     */
                    buffer.read(lights);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                for (short j = 0; j < lights.length; j++) {
                    final byte[] data;

                    data = new byte[2];

                    /*
                     * Get the data for 2 blocks.
                     */
                    data[0] = (byte) (lights[j] & 0x0F);
                    data[1] = (byte) (lights[j] >> 4);

                    /*
                     * Set the block data.
                     */
                    this.blocks.get(block++).setLight(data[0]);
                    this.blocks.get(block++).setLight(data[1]);
                }
            }
        }

        /*
         * Extract all the block sky lights.
         */
        for (byte i = 0; i < 16; i++) {
            int block;

            block = 0;

            if ((this.primaryBitMap & (1 << i)) == 1) {
                final byte[] skyLights;

                skyLights = new byte[2048];

                try {
                    /*
                     * Get all block types data.
                     */
                    buffer.read(skyLights);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                for (short j = 0; j < skyLights.length; j++) {
                    final byte[] data;

                    data = new byte[2];

                    /*
                     * Get the data for 2 blocks.
                     */
                    data[0] = (byte) (skyLights[j] & 0x0F);
                    data[1] = (byte) (skyLights[j] >> 4);

                    /*
                     * Set the block data.
                     */
                    this.blocks.get(block++).setSkyLight(data[0]);
                    this.blocks.get(block++).setSkyLight(data[1]);
                }
            }
        }

        /*
         * Extract all the additional data.
         */
        for (byte i = 0; i < 16; i++) {
            int block;

            block = 0;

            if ((this.addBitMap & (1 << i)) == 1) {
                final byte[] add;

                add = new byte[2048];

                try {
                    /*
                     * Get all block additional data.
                     */
                    buffer.read(add);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                for (short j = 0; j < add.length; j++) {
                    final byte[] data;

                    data = new byte[2];

                    /*
                     * Get the data for 2 blocks.
                     */
                    data[0] = (byte) (add[j] & 0x0F);
                    data[1] = (byte) (add[j] >> 4);

                    /*
                     * Set the block data.
                     */
                    this.blocks.get(block++).setAdd(data[0]);
                    this.blocks.get(block++).setAdd(data[1]);
                }
            }
        }

        if (this.groundUpContinuous) {
            final byte[] biomes;

            biomes = new byte[256];

            try {
                /*
                 * Need to parse biomes data.
                 */
                buffer.read(biomes);
            } catch (IOException e) {
                e.printStackTrace();
            }

            for (short i = 0; i < 256; i++) {
                this.column.getBiomes()[i] = biomes[i];
            }
        }

        try {
            buffer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<MCBlock> getBlocks() {
        return this.blocks;
    }

    public MCBlock getBlockAt(Vector location) {
        for (MCBlock block : this.blocks) {
            if (block.getLocation().equals(location)) {
                return block;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append(this.column);
        builder.append(" | Ground up continuous = ");
        builder.append(this.groundUpContinuous);
        builder.append(" | Primary bit map = ");
        builder.append(this.primaryBitMap);
        builder.append(" | Add bit map = ");
        builder.append(this.addBitMap);
        builder.append(" | Data = { ");
        for (byte b : this.data) {
            builder.append(b);
            builder.append(", ");
        }
        builder.replace(builder.length() - 2, builder.length(), " }");

        return builder.toString();
    }
}
