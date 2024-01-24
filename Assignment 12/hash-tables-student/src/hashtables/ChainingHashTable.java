package hashtables;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.spi.CurrencyNameProvider;

/**
 * An implementation of HashTable.
 * 
 * This implementation uses chaining to resolve collisions. Chaining means 
 * the underlying array stores references to growable structures (like LinkedLists
 * or ArrayLists) that we expect to remain small in size. When there is a 
 * collision, the element is added to the end of the growable structure. It
 * must search the entire growable structure whenever checking membership
 * or removing elements.
 * 
 * This implementation maintains a capacity equal to 2^n - 1 for some positive
 * integer n. When the load factor exceeds 0.75, the next add() triggers a
 * resize by incrementing n (by one). For example, when n=3, then capacity=7.
 * When size=6, then load factor ~=0.86. The addition of the seventh item would
 * trigger a resize, increasing the capacity of the array to 15.
 */
public class ChainingHashTable<E> implements HashTable<E> {

    int size;
    ArrayList<E>[] table;
    double loadFactor;
    
    
    /**
     * Instantiate a new hash table. The initial capacity should be 7.
     */
    public ChainingHashTable() {
        this.size = 0;
        
        this.table =  (ArrayList<E>[]) new ArrayList[7];
        this.loadFactor = (double) size / (double) this.table.length; //!may become a potential issue

    }

    /**
     * Instantiate a new hash table. The initial capacity should be 
     * at least sufficient to hold n elements, but must be one less
     * than a power of two.
     */
    public ChainingHashTable(int n) {
        this.size = 0;
        int tempPower = (int) Math.ceil(Math.log(n + 1) / Math.log(2.0));
        int newLength = (int) Math.pow(2.0, tempPower) - 1;
        this.table =  (ArrayList<E>[]) new ArrayList[newLength];
        this.loadFactor = (double) size / (double) this.table.length; //!may become a potential issue
    }

    @Override
    public int capacity() {
        return this.table.length;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public double loadFactor() {
        return this.loadFactor;
    }

    public void enLarge(){
        int newLength = (this.table.length + 1) * 2 - 1;
        ArrayList<E>[] newArr = (ArrayList<E>[]) new ArrayList[newLength];


        for (int i = 0; i < this.table.length; i ++){
            ArrayList<E> curArray = this.table[i];

            if (curArray == null) continue;

            for(E var : curArray){
                add(var, newArr, newLength);
            }
        }

        this.table = newArr;

        //!update load factor
        this.loadFactor = (double) this.size / (double) newLength;

    }

    public boolean add(E e, ArrayList<E>[] arr, int capacity){
        int index = Math.abs(e.hashCode()) % capacity;

        ArrayList<E> curArray = arr[index];

        if(curArray == null) arr[index] = curArray = new ArrayList<>();

        for (int i = 0; i < curArray.size(); i ++){
            if (curArray.get(i).equals(e)) {
                curArray.set(i, e);
                return false;
            }
        }

        // in case we did not find the equals element

        curArray.add(e);

        return true;

    }

    @Override
    public boolean add(E e) {

        //if current loaf factor is larger than 0.75 then we will enlarge
        if (loadFactor > 0.75) enLarge();


        boolean res = add(e, this.table, this.table.length);

        if(res){
            //!always update load factor and size
            this.size ++;
            this.loadFactor = (double) this.size / (double) this.capacity();

        }
        return res;
    }

    @Override
    public boolean remove(E e) {

        int index = Math.abs(e.hashCode()) % this.capacity();

        ArrayList<E> curArray = this.table[index];

        if (curArray == null) return false;

        boolean res = curArray.remove(e);

        if (res){
            this.size --;
            this.loadFactor = (double) this.size / (double) this.capacity();
        }
        return res;
    }

    @Override
    public boolean contains(E e) {
        int index = Math.abs(e.hashCode()) % this.capacity();

        ArrayList<E> curArray = this.table[index];

        if(curArray == null) return false;

        for (E var : curArray){
            if (var.equals(e)) return true;
        }

        return false;
    }

    @Override
    public E get(E e) {
        int index = Math.abs(e.hashCode()) % this.capacity();

        ArrayList<E> curArray = this.table[index];

        if (curArray == null) return null;

        for (E var : curArray){
            if (var.equals(e)) return var;
        }

        return null;
    }

    public ArrayList<E> retrieveCurList(int tableIndex){
        return this.table[tableIndex];
    }

    @Override
    public Iterator<E> iterator() {

        Iterator<E> res = new HashTableIterator<E>(this);
        return res;

        
    }
}
