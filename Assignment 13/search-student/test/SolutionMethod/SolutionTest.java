package SolutionMethod;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import integers.FindIntegersProblem;
import search.SearchProblem;
import search.Searcher;

public class SolutionTest {

    @Test
    public void testCaseSimple(){

        SearchProblem<Integer> s = new FindIntegersProblem(7, -2, false);
        Searcher<Integer> searcher = new Searcher<>(s);
        List<Integer> solution = searcher.findSolution();
        List<Integer> test = new ArrayList<>();
        test.add(0);
        test.add(-1);
        test.add(-2);

        assertTrue(solution.equals(test));

    }

    @Test
    public void testEqualDistance(){
        SearchProblem<Integer> s = new FindIntegersProblem(10, -10, true);
        Searcher<Integer> searcher = new Searcher<>(s);

        List<Integer> solution = searcher.findSolution();

        List<Integer> test = Arrays.asList(new Integer[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10});

        assertTrue(solution.equals(test));
    }

    
    
}
