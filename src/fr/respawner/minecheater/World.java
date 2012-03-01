package fr.respawner.minecheater;

import java.util.ArrayList;
import java.util.List;

import fr.respawner.minecheater.structure.MCExperience;
import fr.respawner.minecheater.structure.MCPlayerListEntry;
import fr.respawner.minecheater.structure.MCStatistic;
import fr.respawner.minecheater.structure.PositionAndLook;
import fr.respawner.minecheater.structure.entity.MCObject;
import fr.respawner.minecheater.structure.world.MCSpawn;
import fr.respawner.minecheater.structure.world.MCTime;
import fr.respawner.minecheater.structure.world.MCWorld;

public final class World {
    private boolean loggedIn;

    private MCWorld currentWorld;
    private MCSpawn spawn;
    private MCTime time;

    private PositionAndLook position;
    private MCExperience experience;
    private List<MCStatistic> statistics;

    private List<MCPlayerListEntry> people;
    private List<MCObject> objects;

    public World() {
        this.loggedIn = false;
        this.statistics = new ArrayList<>();
        this.people = new ArrayList<>();
        this.objects = new ArrayList<>();
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public MCWorld getCurrentWorld() {
        return this.currentWorld;
    }

    public void setCurrentWorld(MCWorld world) {
        this.currentWorld = world;
    }

    public MCSpawn getSpawn() {
        return this.spawn;
    }

    public void setSpawn(MCSpawn spawn) {
        this.spawn = spawn;
    }

    public MCTime getTime() {
        return this.time;
    }

    public void setTime(MCTime time) {
        this.time = time;
    }

    public PositionAndLook getPosition() {
        return this.position;
    }

    public void setPosition(PositionAndLook position) {
        this.position = position;
    }

    public MCExperience getExperience() {
        return this.experience;
    }

    public void setExperience(MCExperience experience) {
        this.experience = experience;
    }

    public List<MCPlayerListEntry> getOnlinePeople() {
        return this.people;
    }

    public void addPeople(MCPlayerListEntry entry) {
        this.people.add(entry);
    }

    public void removePeople(MCPlayerListEntry entry) {
        this.people.remove(entry);
    }

    public MCPlayerListEntry findPeopleByName(String name) {
        for (MCPlayerListEntry entry : this.people) {
            if (name.equals(entry.getPlayerName())) {
                return entry;
            }
        }

        return null;
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

    public List<MCObject> getAllObjects() {
        return this.objects;
    }

    public void addObject(MCObject object) {
        this.objects.add(object);
    }

    public void removeObject(MCObject object) {
        this.objects.remove(object);
    }

    public MCObject findObjectByID(int id) {
        for (MCObject object : this.objects) {
            if (id == object.getEntityID()) {
                return object;
            }
        }

        return null;
    }
}
