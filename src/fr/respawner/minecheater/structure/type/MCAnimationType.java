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

public enum MCAnimationType {
    UNKNOWN,
    NO_ANIMATION,
    SWING_ARM,
    DAMAGE_ANIMATION,
    LEAVE_BED,
    EAT_FOOD,
    CROUCH,
    UNCROUCH;

    public static MCAnimationType animationForID(byte id) {
        switch (id) {
        case 0:
            return NO_ANIMATION;
        case 1:
            return SWING_ARM;
        case 2:
            return DAMAGE_ANIMATION;
        case 3:
            return LEAVE_BED;
        case 5:
            return EAT_FOOD;
        case 104:
            return CROUCH;
        case 105:
            return UNCROUCH;
        default:
            return UNKNOWN;
        }
    }

    @Override
    public String toString() {
        switch (this) {
        case NO_ANIMATION:
            return "No animation";
        case SWING_ARM:
            return "Swing arm";
        case DAMAGE_ANIMATION:
            return "Damage animation";
        case LEAVE_BED:
            return "Leave bed";
        case EAT_FOOD:
            return "Eat food";
        case CROUCH:
            return "Crouch";
        case UNCROUCH:
            return "Uncrouch";
        default:
            return "Unknown";
        }
    }
}
