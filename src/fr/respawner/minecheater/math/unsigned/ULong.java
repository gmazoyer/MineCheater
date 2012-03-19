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

import java.math.BigInteger;

public final class ULong extends Number implements Comparable<ULong> {
    private static final long serialVersionUID = -8300061296876306543L;

    public static final BigInteger MIN_VALUE = BigInteger.ZERO;
    public static final BigInteger MAX_VALUE = new BigInteger(
            "18446744073709551615");
    public static final BigInteger MAX_VALUE_LONG = new BigInteger(
            "9223372036854775808");

    private final BigInteger value;

    public ULong(long value) {
        if (value >= 0) {
            this.value = BigInteger.valueOf(value);
        } else {
            this.value = BigInteger.valueOf(value & Long.MAX_VALUE).add(
                    MAX_VALUE_LONG);
        }
    }

    public ULong(BigInteger value) throws NumberFormatException {
        this.rangeCheck(value);
        this.value = value;
    }

    private void rangeCheck(BigInteger value) throws NumberFormatException {
        if ((value.compareTo(MIN_VALUE) < 0)
                || (value.compareTo(MAX_VALUE) > 0)) {
            throw new NumberFormatException("Value is out of range: " + value);
        }
    }

    @Override
    public double doubleValue() {
        return this.value.doubleValue();
    }

    @Override
    public float floatValue() {
        return this.value.floatValue();
    }

    @Override
    public int intValue() {
        return this.value.intValue();
    }

    @Override
    public long longValue() {
        return this.value.longValue();
    }

    @Override
    public int compareTo(ULong o) {
        return this.value.compareTo(o.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ULong) {
            return this.value.equals(((ULong) obj).value);
        }

        return false;
    }

    @Override
    public String toString() {
        return this.value.toString();
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }
}
