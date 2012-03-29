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
package fr.respawner.minecheater.metadata;

public class Triplet<I, K, V> {
    private I index;
    private K key;
    private V value;

    public Triplet(I index, K key, V value) {
        this.index = index;
        this.key = key;
        this.value = value;
    }

    public I getIndex() {
        return this.index;
    }

    public void setIndex(I index) {
        this.index = index;
    }

    public K getKey() {
        return this.key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return this.value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        final int hashIndex, hashKey, hashValue;

        hashIndex = this.index.hashCode();
        hashKey = this.key.hashCode();
        hashValue = this.key.hashCode();

        return ((hashIndex + hashKey + hashValue) * hashValue) + hashKey
                + hashIndex;
    }

    @Override
    public boolean equals(Object other) {
        final Triplet<?, ?, ?> otherTriplet;

        if (other instanceof Triplet) {
            otherTriplet = (Triplet<?, ?, ?>) other;

            return (((this.index == otherTriplet.index) || ((this.index != null)
                    && (otherTriplet.index != null) && this.index
                        .equals(otherTriplet.index)))
                    && ((this.key == otherTriplet.key) || ((this.key != null)
                            && (otherTriplet.key != null) && this.key
                                .equals(otherTriplet.key))) && ((this.value == otherTriplet.value) || ((this.value != null)
                    && (otherTriplet.value != null) && this.value
                        .equals(otherTriplet.value))));
        }

        return false;
    }

    @Override
    public String toString() {
        return "(" + this.index + ", " + this.key + ", " + this.value + ")";
    }
}
