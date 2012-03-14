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
package fr.respawner.minecheater.structure.entity;

import fr.respawner.minecheater.math.Rotation;
import fr.respawner.minecheater.metadata.Metadata;
import fr.respawner.minecheater.structure.type.MCMobType;

/**
 * This is the class that represents a mob.
 * 
 * @author Guillaume Mazoyer
 */
public final class MCMob extends MCEntity {
    private byte type;

    public MCMob(int entityID, byte type, int x, int y, int z, byte yaw,
            byte pitch, byte headYaw, Metadata metadata) {
        super(entityID, x, y, z);

        this.type = type;

        this.rotation = new Rotation();
        this.rotation.setRotationFromPacket(yaw, pitch);

        this.setHeadYaw(headYaw);
        this.setMetadata(metadata);
    }

    public final byte getType() {
        return this.type;
    }

    @Override
    public String toString() {
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append(super.toString());
        builder.append(" | Type = ");
        builder.append(MCMobType.mobForID(this.type));
        builder.append(" | Head yaw = ");
        builder.append(this.getHeadYaw());

        return builder.toString();
    }
}
