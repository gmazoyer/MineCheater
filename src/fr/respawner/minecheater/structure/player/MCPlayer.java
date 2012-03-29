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
package fr.respawner.minecheater.structure.player;

import java.util.ArrayList;
import java.util.List;

import fr.respawner.minecheater.World;
import fr.respawner.minecheater.math.Location;
import fr.respawner.minecheater.math.MathHelper;
import fr.respawner.minecheater.math.Rotation;
import fr.respawner.minecheater.structure.MCIdentifiable;
import fr.respawner.minecheater.structure.inventory.MCInventory;
import fr.respawner.minecheater.structure.world.MCBlock;
import fr.respawner.minecheater.structure.world.MCMapColumn;

public final class MCPlayer extends MCIdentifiable {
    private Location location;
    private Rotation rotation;
    private MCHealth health;
    private MCExperience experience;
    private MCInventory inventory;
    private List<MCStatistic> statistics;
    private World world;

    public MCPlayer(World world, int entityID) {
        super(entityID);

        this.health = new MCHealth();
        this.experience = new MCExperience();
        this.inventory = new MCInventory();
        this.statistics = new ArrayList<>();
        this.world = world;
    }

    public final Location getLocation() {
        return this.location;
    }

    public final void setLocation(Location location) {
        this.location = location;
    }

    public Rotation getRotation() {
        return this.rotation;
    }

    public void setRotation(Rotation rotation) {
        this.rotation = rotation;
    }

    public final MCHealth getHealth() {
        return this.health;
    }

    public final MCExperience getExperience() {
        return this.experience;
    }

    public final MCInventory getInventory() {
        return this.inventory;
    }

    public final List<MCStatistic> getAllStatistics() {
        return this.statistics;
    }

    public final void addStatistic(MCStatistic statistic) {
        this.statistics.add(statistic);
    }

    public final void removeStatistic(MCStatistic statistic) {
        this.statistics.remove(statistic);
    }

    public final MCStatistic findStatisticByID(int id) {
        for (MCStatistic statistic : this.statistics) {
            if (id == statistic.getStatisticID()) {
                return statistic;
            }
        }

        return null;
    }

    public final World getWorld() {
        return this.world;
    }

    public void move(double x, double y, double z) {
        final double newX, newY, newZ;

        newX = this.location.getPosition().getX() + x;
        newY = this.location.getPosition().getY() + y;
        newZ = this.location.getPosition().getZ() + z;

        this.location.setPosition(newX, newY, newZ);
    }

    public void putOnGround() {
        MCMapColumn column;
        MCBlock block;
        final int x, z;

        x = MathHelper.floorDouble(this.location.getPosition().getX() / 16);
        z = MathHelper.floorDouble(this.location.getPosition().getZ() / 16);
        block = null;

        do {
            column = this.world.getMap().getColumnAt(x, z);
            if (column == null) {
                System.out.println("No column found at (" + x + ", " + z + ")");
                return;
            }

            if (!this.location.isInside(column)) {
                System.out.println("Not inside the column, weird!");
            }

            block = this.location.findBlockBelow(column);
            if (block == null) {
                System.out.println("No block found!");
                return;
            }

            this.move(0, -1, 0);
        } while ((block == null) || block.isAir());

        this.location.setOnGround(true);
    }

    @Override
    public String toString() {
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append("Player ID = ");
        builder.append(this.getEntityID());
        builder.append(" | ");
        builder.append(this.location);
        builder.append(" | ");
        builder.append(this.rotation);
        builder.append(" | ");
        builder.append(this.health);
        builder.append(" | ");
        builder.append(this.experience);
        builder.append(" | ");
        builder.append(this.inventory);

        return builder.toString();
    }
}
