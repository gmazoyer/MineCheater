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

import fr.respawner.minecheater.math.Location;
import fr.respawner.minecheater.math.Rotation;
import fr.respawner.minecheater.structure.MCIdentifiable;

public final class MCPlayer extends MCIdentifiable {
    private Location location;
    private Rotation rotation;
    private MCHealth health;
    private MCExperience experience;
    private List<MCStatistic> statistics;

    public MCPlayer(int entityID) {
        super(entityID);

        this.statistics = new ArrayList<>();
    }

    public MCPlayer() {
        this(0);
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

    public final void setHealth(MCHealth health) {
        this.health = health;
    }

    public final MCExperience getExperience() {
        return this.experience;
    }

    public final void setExperience(MCExperience experience) {
        this.experience = experience;
    }

    public List<MCStatistic> getAllStatistics() {
        return this.statistics;
    }

    public void addStatistic(MCStatistic statistic) {
        this.statistics.add(statistic);
    }

    public void removeStatistic(MCStatistic statistic) {
        this.statistics.remove(statistic);
    }

    public MCStatistic findStatisticByID(int id) {
        for (MCStatistic statistic : this.statistics) {
            if (id == statistic.getStatisticID()) {
                return statistic;
            }
        }

        return null;
    }

    public void move(double x, double y, double z) {
        final double newX, newY, newZ;

        newX = this.location.getPosition().getX() + x;
        newY = this.location.getPosition().getY() + y;
        newZ = this.location.getPosition().getZ() + z;

        this.location.setPosition(newX, newY, newZ);
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

        return builder.toString();
    }
}
