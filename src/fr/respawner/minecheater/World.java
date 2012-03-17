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
package fr.respawner.minecheater;

import java.util.ArrayList;
import java.util.List;

import fr.respawner.minecheater.math.Vector;
import fr.respawner.minecheater.structure.MCPlayerListEntry;
import fr.respawner.minecheater.structure.entity.MCObject;
import fr.respawner.minecheater.structure.player.MCPlayer;
import fr.respawner.minecheater.structure.world.MCLevel;
import fr.respawner.minecheater.structure.world.MCMap;
import fr.respawner.minecheater.structure.world.MCTime;

public final class World {
    private boolean loggedIn;

    private MCLevel level;
    private Vector spawn;
    private MCTime time;

    private MCPlayer player;

    private List<MCPlayerListEntry> people;
    private List<MCObject> objects;

    private MCMap map;

    public World() {
        this.loggedIn = false;
        this.player = new MCPlayer();
        this.people = new ArrayList<>();
        this.objects = new ArrayList<>();
        this.map = new MCMap();
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public MCLevel getLevel() {
        return this.level;
    }

    public void setLevel(MCLevel level) {
        this.level = level;
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

    public MCMap getMap() {
        return this.map;
    }
}
