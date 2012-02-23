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

		return (hashIndex + hashKey + hashValue) * hashValue + hashKey
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
