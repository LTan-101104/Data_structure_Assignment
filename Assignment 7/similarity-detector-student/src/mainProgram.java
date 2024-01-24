import java.util.HashSet;
import java.util.Set;

import sets.SetUtilities;

public class mainProgram {
    public static void main(String[] args){
        Set <Integer> evens = new HashSet<Integer>();
		for (int i = 0; i < 10; i += 2) {
			evens.add(i);
		}

        Set <Integer> zeroToNine = new HashSet<Integer>();
		for (int i = 0; i < 10; i++) {
			zeroToNine.add(i);
		}
        System.out.println(evens);
        System.out.println(zeroToNine);

        System.out.println(SetUtilities.jaccardIndex(evens, zeroToNine));
		

    }
}
