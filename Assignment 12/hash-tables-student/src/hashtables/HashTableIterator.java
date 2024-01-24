package hashtables;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class HashTableIterator<E> implements Iterator<E> {

    ChainingHashTable<E> table;
    int tableIndex;
    int listIndex;

    public HashTableIterator(ChainingHashTable<E> table){
        this.table = table;
        this.tableIndex = 0;
        this.listIndex = 0;
    }

    
    public int retrieveClosestNonNullList(int tableIndex){
        //this will return the closest index of a NonNullList from tableIndex onward (exclusive tableIndex)
        //if there is no more then it will return -1
        
        if (tableIndex >= this.table.capacity() - 1) return -1;
        
        tableIndex += 1;
        
        ArrayList<E> curArray = this.table.retrieveCurList(tableIndex);
        
        while(curArray == null){
            tableIndex += 1;
            if (tableIndex >= this.table.capacity()) return -1; //!in case there is no more spot left
            curArray = this.table.retrieveCurList(tableIndex);
        }
        
        return tableIndex;
        
    }
    
    @Override
    public boolean hasNext(){

        ArrayList<E> curArray = this.table.retrieveCurList(this.tableIndex);

        if(curArray != null && this.listIndex < curArray.size()) return true;

        //reach here because either curArray is null or the listIndex got out of bound

        int nextAvailableArray = this.retrieveClosestNonNullList(tableIndex);

        return nextAvailableArray != -1;

    }


    @Override
    public E next(){
        // return the current value and then moves next

        //remember to update the listIndex again if moves to the next list

        if (!this.hasNext()) throw new NoSuchElementException(); //rules out case where after 

        
        ArrayList<E> curArray = this.table.retrieveCurList(this.tableIndex); // return arrayList at the current table index (index of underlying array)


        if(curArray == null || this.listIndex >= curArray.size()){
            int temp = this.tableIndex;
            int nextAvailableIndex = this.retrieveClosestNonNullList(temp);
            curArray = this.table.retrieveCurList(nextAvailableIndex); //!this already updated the tableIndex to the next non-null table
            this.listIndex = 0;
            this.tableIndex = nextAvailableIndex; //! must update tableIndex
            System.out.println("___________" + this.tableIndex + "__________");
        } 


        E res =  curArray.get(this.listIndex);

        this.listIndex ++;

        return res;
    }

    
}
