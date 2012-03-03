package fr.respawner.minecheater;

import java.util.ArrayList;
import java.util.List;

import fr.respawner.minecheater.math.Vector;
import fr.respawner.minecheater.structure.MCPlayerListEntry;
import fr.respawner.minecheater.structure.entity.MCObject;
import fr.respawner.minecheater.structure.player.MCPlayer;
import fr.respawner.minecheater.structure.world.MCTime;
import fr.respawner.minecheater.structure.world.MCWorld;

public final class World {
    private boolean loggedIn;

    private MCWorld currentWorld;
    private Vector spawn;
    private MCTime time;

    private MCPlayer player;

    private List<MCPlayerListEntry> people;
    private List<MCObject> objects;

    public World() {
        this.loggedIn = false;
        this.player = new MCPlayer();
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

    public Vector getSpawn() {
        return this.spawn;
    }

    public void setSpawn(Vector spawn) {
        this.spawn = spawn;
    }

    public MCTime getTime() {
        return this.time;
    }

    public void setTime(MCTime time) {
        this.time = time;
    }

    public MCPlayer getPlayer() {
        return this.player;
    }

    public void setPlayer(MCPlayer player) {
        this.player = player;
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
