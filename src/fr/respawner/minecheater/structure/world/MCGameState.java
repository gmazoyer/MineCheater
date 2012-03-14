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
package fr.respawner.minecheater.structure.world;

public final class MCGameState {
    private byte reason;
    private byte mode;

    public MCGameState(byte reason, byte mode) {
        this.reason = reason;
        this.mode = mode;
    }

    public byte getReason() {
        return this.reason;
    }

    public byte getMode() {
        return this.mode;
    }

    @Override
    public String toString() {
        switch (this.reason) {
        case 0:
            return "Invalid bed use";

        case 1:
            return "Begin raining";

        case 2:
            return "End raining";

        case 3:
            return "Game mode = " + (this.mode == 0 ? "survival" : "creative");

        case 4:
            return "Enter credits";

        default:
            return "Unknown";
        }
    }
}
