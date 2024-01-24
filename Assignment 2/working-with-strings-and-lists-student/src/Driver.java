import java.util.ArrayList;

import list.exercises.ListExercises;
public class Driver {
    public static void main(String[] args){
        ArrayList<String> temp = new ArrayList<>();
        temp.add("Josh");
        temp.add("Linda");
        ListExercises.insertInOrder("Marc", temp);
        System.out.println(temp);
    }
    
}
