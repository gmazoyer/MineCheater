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
import java.util.List;
import java.util.zip.InflaterInputStream;

import fr.respawner.minecheater.math.MathHelper;
import fr.respawner.minecheater.math.Vector3D;

public final class MCMapColumn {
    private boolean groundUpContinuous;
    private short primaryBitMap;
    private short addBitMap;
    private byte[] data;

    private int x;
    private int z;
    private MCChunk[] chunks;
    private byte[] biomes;

    public MCMapColumn(int x, int z, boolean groundUpContinuous,
            short primaryBitMap, short addBitMap, byte[] zlibData) {
        this.groundUpContinuous = groundUpContinuous;
        this.primaryBitMap = primaryBitMap;
        this.addBitMap = addBitMap;
        this.data = this.inflateData(zlibData);

        this.x = x;
        this.z = z;
        this.chunks = new MCChunk[16];
        this.biomes = new byte[256];
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
            final MCBlock[] blocks;
            final byte[] blockIDs;

            this.chunks[i] = new MCChunk(new Vector3D(this.x, i, this.z));
            blocks = this.chunks[i].getBlocks();
            blockIDs = new byte[4096];

            if ((this.primaryBitMap & (1 << i)) == 1) {
                try {
                    /*
                     * Get all block types data.
                     */
                    buffer.read(blockIDs);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                for (short j = 0; j < blockIDs.length; j++) {
                    final Vector3D coordinates;

                    /*
                     * Block coordinates.
                     */
                    coordinates = MathHelper.coordinatesFromIndexes(j, i);

                    /*
                     * Put the block in the array with an index that match its
                     * coordinates.
                     */
                    blocks[MathHelper.indexFromCoordinates(coordinates)] = new MCBlock(
                            blockIDs[j]);
                }
            }
        }

        /*
         * Extract all the block metadata.
         */
        for (byte i = 0; i < 16; i++) {
            if ((this.primaryBitMap & (1 << i)) == 1) {
                final MCBlock[] blocks;
                final byte[] metadata;

                blocks = this.chunks[i].getBlocks();
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
                    final Vector3D[] coordinates;

                    data = new byte[2];
                    coordinates = new Vector3D[2];

                    /*
                     * Get the data for 2 blocks.
                     */
                    data[0] = (byte) (metadata[j] & 0x0F);
                    data[1] = (byte) (metadata[j] >> 4);

                    /*
                     * Get the blocks coordinates.
                     */
                    coordinates[0] = MathHelper.coordinatesFromIndexes(j, i);
                    coordinates[1] = MathHelper
                            .coordinatesFromIndexes(j + 1, i);

                    /*
                     * Set the block data.
                     */
                    blocks[MathHelper.indexFromCoordinates(coordinates[0])]
                            .setMetadata(data[0]);
                    blocks[MathHelper.indexFromCoordinates(coordinates[1])]
                            .setMetadata(data[1]);
                }
            }
        }

        /*
         * Extract all the block lights.
         */
        for (byte i = 0; i < 16; i++) {
            if ((this.primaryBitMap & (1 << i)) == 1) {
                final MCBlock[] blocks;
                final byte[] lights;

                blocks = this.chunks[i].getBlocks();
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
                    final Vector3D[] coordinates;

                    data = new byte[2];
                    coordinates = new Vector3D[2];

                    /*
                     * Get the data for 2 blocks.
                     */
                    data[0] = (byte) (lights[j] & 0x0F);
                    data[1] = (byte) (lights[j] >> 4);

                    /*
                     * Get the blocks coordinates.
                     */
                    coordinates[0] = MathHelper.coordinatesFromIndexes(j, i);
                    coordinates[1] = MathHelper
                            .coordinatesFromIndexes(j + 1, i);

                    /*
                     * Set the block data.
                     */
                    blocks[MathHelper.indexFromCoordinates(coordinates[0])]
                            .setLight(data[0]);
                    blocks[MathHelper.indexFromCoordinates(coordinates[1])]
                            .setLight(data[1]);
                }
            }
        }

        /*
         * Extract all the block sky lights.
         */
        for (byte i = 0; i < 16; i++) {
            if ((this.primaryBitMap & (1 << i)) == 1) {
                final MCBlock[] blocks;
                final byte[] skyLights;

                blocks = this.chunks[i].getBlocks();
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
                    final Vector3D[] coordinates;

                    data = new byte[2];
                    coordinates = new Vector3D[2];

                    /*
                     * Get the data for 2 blocks.
                     */
                    data[0] = (byte) (skyLights[j] & 0x0F);
                    data[1] = (byte) (skyLights[j] >> 4);

                    /*
                     * Get the blocks coordinates.
                     */
                    coordinates[0] = MathHelper.coordinatesFromIndexes(j, i);
                    coordinates[1] = MathHelper
                            .coordinatesFromIndexes(j + 1, i);

                    /*
                     * Set the block data.
                     */
                    blocks[MathHelper.indexFromCoordinates(coordinates[0])]
                            .setSkyLight(data[0]);
                    blocks[MathHelper.indexFromCoordinates(coordinates[1])]
                            .setSkyLight(data[1]);
                }
            }
        }

        /*
         * Extract all the additional data.
         */
        for (byte i = 0; i < 16; i++) {
            if ((this.addBitMap & (1 << i)) == 1) {
                final MCBlock[] blocks;
                final byte[] add;

                blocks = this.chunks[i].getBlocks();
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
                    final Vector3D[] coordinates;

                    data = new byte[2];
                    coordinates = new Vector3D[2];

                    /*
                     * Get the data for 2 blocks.
                     */
                    data[0] = (byte) (add[j] & 0x0F);
                    data[1] = (byte) (add[j] >> 4);

                    /*
                     * Get the blocks coordinates.
                     */
                    coordinates[0] = MathHelper.coordinatesFromIndexes(j, i);
                    coordinates[1] = MathHelper
                            .coordinatesFromIndexes(j + 1, i);

                    /*
                     * Set the block data.
                     */
                    blocks[MathHelper.indexFromCoordinates(coordinates[0])]
                            .setAdd(data[0]);
                    blocks[MathHelper.indexFromCoordinates(coordinates[1])]
                            .setAdd(data[1]);
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
                this.biomes[i] = biomes[i];
            }
        }

        try {
            buffer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getX() {
        return this.x;
    }

    public int getZ() {
        return this.z;
    }

    public MCChunk[] getChunks() {
        return this.chunks;
    }

    public byte[] getBiomes() {
        return this.biomes;
    }

    public boolean isAt(int x, int z) {
        return ((this.x == x) && (this.z == z));
    }

    @Override
    public String toString() {
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append("MapColumn: x = ");
        builder.append(this.x);
        builder.append(", z = ");
        builder.append(this.z);
        builder.append(" | Chunks: ");
        for (MCChunk chunk : this.chunks) {
            builder.append(chunk);
        }

        return builder.toString();
    }
}
