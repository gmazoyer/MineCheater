package fr.respawner.minecheater.structure.player;

import java.util.List;

import fr.respawner.minecheater.structure.MCIdentifiable;

public final class MCPlayer extends MCIdentifiable {
    private PositionAndLook position;
    private Health health;
    private MCExperience experience;
    private List<MCStatistic> statistics;

    public MCPlayer(int entityID) {
        super(entityID);
    }

    public MCPlayer() {
        super(0);
    }

    public final PositionAndLook getPosition() {
        return this.position;
    }

    public final void setPosition(PositionAndLook position) {
        this.position = position;
    }

    public final Health getHealth() {
        return this.health;
    }

    public final void setHealth(Health health) {
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

    @Override
    public String toString() {
        final StringBuilder builder;

        builder = new StringBuilder();

        builder.append("Player ID = ");
        builder.append(this.getEntityID());
        builder.append(" | ");
        builder.append(this.position);
        builder.append(" | ");
        builder.append(this.health);
        builder.append(" | ");
        builder.append(this.experience);

        return builder.toString();
    }
}
