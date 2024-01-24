package sequencer;

import java.util.ArrayList;

public class mainProgram {
    public static void main(String[] args) {
        // Fragment frag1 = new Fragment("AAGAA");
        // Fragment frag2 = new Fragment("AAGAA");

        // System.out.println(frag1.calculateOverlap(frag2));
        // System.out.println(frag1.mergedWith(frag2));

        ArrayList<Fragment> dupFullOverlap = new ArrayList<Fragment>();
        dupFullOverlap.add(new Fragment("AAGAA"));
        dupFullOverlap.add(new Fragment("AAGAA"));

        Assembler a = new Assembler(dupFullOverlap);
        System.out.println(a.getFragments());
        System.out.println(a.assembleOnce());
    }
}
