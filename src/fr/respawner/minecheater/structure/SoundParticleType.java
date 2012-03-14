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
package fr.respawner.minecheater.structure;

public enum SoundParticleType {
    UNKNOWN,
    CLICK1,
    CLICK2,
    BOW_FIRE,
    DOOR_TOGGLE,
    EXTINGUISH,
    RECORD_PLAY,
    CHARGE,
    FIREBALL,
    SMOKE,
    BLOCK_BREAK,
    SPLASH_POTION,
    PORTAL,
    BLAZE;

    public static SoundParticleType effectForID(int id) {
        switch (id) {
        case 1000:
            return CLICK2;
        case 1001:
            return CLICK1;
        case 1002:
            return BOW_FIRE;
        case 1003:
            return DOOR_TOGGLE;
        case 1004:
            return EXTINGUISH;
        case 1005:
            return RECORD_PLAY;
        case 1007:
            return CHARGE;
        case 1008:
        case 1009:
            return FIREBALL;
        case 2000:
            return SMOKE;
        case 2001:
            return BLOCK_BREAK;
        case 2002:
            return SPLASH_POTION;
        case 2003:
            return PORTAL;
        case 2004:
            return BLAZE;
        default:
            return UNKNOWN;
        }
    }

    @Override
    public String toString() {
        switch (this) {
        case CLICK2:
            return "Click 2";
        case CLICK1:
            return "Click 1";
        case BOW_FIRE:
            return "Bow fire";
        case DOOR_TOGGLE:
            return "Door toggle";
        case EXTINGUISH:
            return "Extinguish";
        case RECORD_PLAY:
            return "Record play";
        case CHARGE:
            return "Charge";
        case FIREBALL:
            return "Fireball";
        case SMOKE:
            return "Smoke";
        case BLOCK_BREAK:
            return "Block break";
        case SPLASH_POTION:
            return "Splash potion";
        case PORTAL:
            return "Portal";
        case BLAZE:
            return "Blaze";
        default:
            return "Unknown";
        }
    }
}
