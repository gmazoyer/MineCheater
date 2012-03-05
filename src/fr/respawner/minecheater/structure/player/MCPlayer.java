package fr.respawner.minecheater.structure.player;

import java.util.ArrayList;
import java.util.List;

import fr.respawner.minecheater.math.Location;
import fr.respawner.minecheater.structure.MCIdentifiable;

public final class MCPlayer extends MCIdentifiable {
    private Location location;
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

        newX = this.location.getX() + x;
        newY = this.location.getY() + y;
        newZ = this.location.getZ() + z;

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
        builder.append(this.health);
        builder.append(" | ");
        builder.append(this.experience);

        return builder.toString();
    }
}
