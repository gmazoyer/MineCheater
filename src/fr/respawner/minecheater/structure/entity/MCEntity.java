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

import fr.respawner.minecheater.math.Vector3D;
import fr.respawner.minecheater.metadata.Metadata;
import fr.respawner.minecheater.structure.type.MCAnimationType;
import fr.respawner.minecheater.structure.type.MCStatusType;

/**
 * This class of all objects that can move in the Minecraft world (players,
 * mobs, etc...).
 * 
 * @author Guillaume Mazoyer
 */
public class MCEntity extends MCObject {
    private Vector3D velocity;
    private byte dX;
    private byte dY;
    private byte dZ;
    private byte headYaw;
    private Metadata metadata;
    private byte status;
    private byte lastAnimation;

    public MCEntity(int entityID, int x, int y, int z) {
        super(entityID, x, y, z);
    }

    public final byte getRelativeX() {
        return this.dX;
    }

    public final void setRelativeX(byte dX) {
        this.dX = dX;
    }

    public final byte getRelativeY() {
        return this.dY;
    }

    public final void setRelativeY(byte dY) {
        this.dY = dY;
    }

    public final byte getRelativeZ() {
        return this.dZ;
    }

    public final void setRelativeZ(byte dZ) {
        this.dZ = dZ;
    }

    public final byte[] getMove() {
        return new byte[] { this.dX, this.dY, this.dZ };
    }

    public final void setMove(byte dX, byte dY, byte dZ) {
        this.dX = dX;
        this.dY = dY;
        this.dZ = dZ;
    }

    public byte getHeadYaw() {
        return this.headYaw;
    }

    public void setHeadYaw(byte headYaw) {
        this.headYaw = headYaw;
    }

    public final Metadata getMetadata() {
        return this.metadata;
    }

    public final void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public final byte getStatus() {
        return this.status;
    }

    public final void setStatus(byte status) {
        this.status = status;
    }

    public final Vector3D getVelocity() {
        return this.velocity;
    }

    public final void setVelocity(Vector3D velocity) {
        this.velocity = velocity;
    }

    public final byte getLastAnimation() {
        return this.lastAnimation;
    }

    public final void setLastAnimation(byte lastAnimation) {
        this.lastAnimation = lastAnimation;
    }

    @Override
    public String toString() {
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append(super.toString());
        builder.append(" | Velocity = ");
        builder.append(this.velocity);
        builder.append(" | Derivative location: x = ");
        builder.append(this.dX);
        builder.append(", y = ");
        builder.append(this.dY);
        builder.append(", z = ");
        builder.append(this.dZ);
        builder.append(" | Head yaw = ");
        builder.append(this.headYaw);
        builder.append(" | Metadata = ");
        builder.append(this.metadata);
        builder.append(" | Status = ");
        builder.append(MCStatusType.statusForID(this.status));
        builder.append(" | Last animation = ");
        builder.append(MCAnimationType.animationForID(this.lastAnimation));

        return builder.toString();
    }
}
