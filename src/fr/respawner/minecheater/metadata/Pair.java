package fr.respawner.minecheater.metadata;

public class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
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
        final int hashKey, hashValue;

        hashKey = this.key.hashCode();
        hashValue = this.key.hashCode();

        return (hashKey + hashValue) * hashValue + hashKey;
    }

    @Override
    public boolean equals(Object other) {
        final Pair<?, ?> otherPair;

        if (other instanceof Pair) {
            otherPair = (Pair<?, ?>) other;

            return (((this.key == otherPair.key) || ((this.key != null)
                    && (otherPair.key != null) && this.key
                        .equals(otherPair.key))) && ((this.value == otherPair.value) || ((this.value != null)
                    && (otherPair.value != null) && this.value
                        .equals(otherPair.value))));
        }

        return false;
    }

    @Override
    public String toString() {
        return "(" + this.key + ", " + this.value + ")";
    }
}
