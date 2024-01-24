import static org.junit.Assert.assertEquals;

import lists.ArrayList;
import lists.LinkedList;
import lists.List;

public class mainProgram {

    public static void main(String[] args) {
        List<String> l = new LinkedList<>();
        l.add(0, "a");
        System.out.println(l.size());
        // l.add(1, "b");
        // l.add(2, "c");
    }

}
