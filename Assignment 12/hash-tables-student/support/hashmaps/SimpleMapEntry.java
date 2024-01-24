/*
 * Copyright 2023 Marc Liberatore.
 */
package hashmaps;

/**
 * An entry representing a key-value pair to be used in an implementation of
 * SimpleMap.
 * 
 * The SimpleMapEntry associates a given key and value as a pair.
 * SimpleMapEntries are considered equal on the basis of their keys -- their
 * value is ignored.
 */
public class SimpleMapEntry<K, V> {
    final K k;
    final V v;

    @Override
    public String toString() {
        return k + "=" + v;
    }

    public SimpleMapEntry(K k, V v) {
        this.k = k;
        this.v = v;
    }

    @Override
    public int hashCode() {
        //!hash code is calculated based on k
        final int prime = 31;
        int result = 1;
        result = prime * result + ((k == null) ? 0 : k.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        //compare based on key only
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SimpleMapEntry other = (SimpleMapEntry) obj;
        if (k == null) {
            if (other.k != null)
                return false;
        } else if (!k.equals(other.k))
            return false;
        return true;
    }
}
