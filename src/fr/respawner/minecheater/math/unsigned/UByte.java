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

public final class UByte extends Number implements Comparable<UByte> {
    private static final long serialVersionUID = 9154696250031259103L;

    public static final short MIN_VALUE = 0x00;
    public static final short MAX_VALUE = 0xFF;

    private final short value;

    public UByte(byte value) {
        this.value = (short) (value & MAX_VALUE);
    }

    public UByte(short value) throws NumberFormatException {
        this.rangeCheck(value);
        this.value = value;
    }

    private void rangeCheck(short value) throws NumberFormatException {
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
        return this.value;
    }

    @Override
    public long longValue() {
        return this.value;
    }

    @Override
    public int compareTo(UByte o) {
        return (this.value < o.value ? -1 : (this.value == o.value ? 0 : 1));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof UByte) {
            return (this.value == ((UByte) obj).value);
        }

        return false;
    }

    @Override
    public String toString() {
        return Short.valueOf(this.value).toString();
    }

    @Override
    public int hashCode() {
        return Short.valueOf(this.value).hashCode();
    }
}
