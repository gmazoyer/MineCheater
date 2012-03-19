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
package fr.respawner.minecheater.math.unsigned;

public final class UInt extends Number implements Comparable<UInt> {
    private static final long serialVersionUID = 7498079902115790156L;

    public static final long MIN_VALUE = 0x00000000;
    public static final long MAX_VALUE = 0xFFFFFFFFL;

    private final long value;

    public UInt(int value) {
        this.value = value & MAX_VALUE;
    }

    public UInt(long value) throws NumberFormatException {
        this.rangeCheck(value);
        this.value = value;
    }

    private void rangeCheck(long value) throws NumberFormatException {
        if ((value < MIN_VALUE) || (value > MAX_VALUE)) {
            throw new NumberFormatException("Value is out of range: " + value);
        }
    }

    @Override
    public double doubleValue() {
        return this.value;
    }

    @Override
    public float floatValue() {
        return this.value;
    }

    @Override
    public int intValue() {
        return (int) this.value;
    }

    @Override
    public long longValue() {
        return this.value;
    }

    @Override
    public int compareTo(UInt o) {
        return (value < o.value ? -1 : (value == o.value ? 0 : 1));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof UInt) {
            return (value == ((UInt) obj).value);
        }

        return false;
    }

    @Override
    public String toString() {
        return Long.valueOf(this.value).toString();
    }

    @Override
    public int hashCode() {
        return Long.valueOf(this.value).hashCode();
    }
}
