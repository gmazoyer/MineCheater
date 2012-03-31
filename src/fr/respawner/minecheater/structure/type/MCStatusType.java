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
package fr.respawner.minecheater.structure.type;

public enum MCStatusType {
    UNKNOWN, HURT, DEAD, TAMING, TAMED, SHAKING_WATER, EATING, EATING_GRASS;

    public static MCStatusType statusForID(byte id) {
        switch (id) {
        case 2:
            return HURT;
        case 3:
            return DEAD;
        case 6:
            return TAMING;
        case 7:
            return TAMED;
        case 8:
            return SHAKING_WATER;
        case 9:
            return EATING;
        case 10:
            return EATING_GRASS;
        default:
            return UNKNOWN;
        }
    }

    @Override
    public String toString() {
        switch (this) {
        case HURT:
            return "Entity hurt";
        case DEAD:
            return "Entity dead";
        case TAMING:
            return "Wolf taming";
        case TAMED:
            return "Wolf tamed";
        case SHAKING_WATER:
            return "Wolf shaking water off itself";
        case EATING:
            return "Eating";
        case EATING_GRASS:
            return "Sheep eating grass";
        default:
            return "Unknown";
        }
    }
}
