import java.util.ArrayList;
import java.util.List;

import hashtables.ChainingHashTable;

public class mainProgram {
    public static void main(String[] args){
        ChainingHashTable<Integer> t = new ChainingHashTable<>();
        for (int i = 0; i < 6; i++) {
            t.add(i);
        }

        for (int i = 0; i < t.capacity(); i++){
            System.out.println(t.retrieveCurList(i));
        }

        

        List<Integer> l = new ArrayList<>();
        int count = 0;
        for (Integer i : t) {
            if(count == 100) break; //break the infinite loop
            l.add(i);
            System.out.println(i);
            count ++;
        }
    }
}
