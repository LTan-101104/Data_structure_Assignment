/*
 * Copyright 2023 Marc Liberatore.
 */
package hashmaps;

import java.util.HashSet;
import java.util.Set;

import hashtables.ChainingHashTable;



/**
 * An implementation of a SimpleMap, built using the ChainingHashTable and 
 * SimpleMapEntry classes. This class should behave similarly to the built-in
 * java.util.HashMap, though it is much simpler!
 */
public class SimpleHashMap<K, V> implements SimpleMap<K, V> {

    ChainingHashTable<SimpleMapEntry<K, V>> table;

    public SimpleHashMap() {
        this.table = new ChainingHashTable<SimpleMapEntry<K, V>>();
    }

    @Override
    public int size() {
        return this.table.size();
    }

    @Override
    public void put(K k, V v) {
        SimpleMapEntry<K, V> newEntry = new SimpleMapEntry<K, V>(k, v);

        this.table.add(newEntry);
    }

    @Override
    public V get(K k) {
        //get() method for chaining hashtable: it will receive a simpleMapEntry and then retrieve the actual entry inside the map that has the same hashcode as the input entry
        //in other word, we just puts in a dummyEntry with similar key, then it will retrieve the true entry with actual value for us
        SimpleMapEntry<K,V> dummyEntry = new SimpleMapEntry<K,V>(k, null);
        SimpleMapEntry<K, V> resEntry = this.table.get(dummyEntry);

        if (resEntry == null) return null;
        return resEntry.v;
    }

    @Override
    public V getOrDefault(K k, V defaultValue) {
        V temp = this.get(k);
        V res = (temp == null) ? defaultValue : temp;

        return res;
    }

    @Override
    public V remove(K k) {
        //this is a little bit inefficient because technically the get method makes one loop and the remove makes another loop
        SimpleMapEntry<K,V> dummy = new SimpleMapEntry<K, V>(k, null);
        SimpleMapEntry<K, V> cur = this.table.get(dummy);

        if (cur == null) return null;

        V res = cur.v;
        this.table.remove(cur);

        return res;
    }

    @Override
    public Set<K> keys() {
        Set<K> res = new HashSet<>();
        for(SimpleMapEntry<K,V> var : this.table){
            res.add(var.k);
        }
        return res;
    }    
}
