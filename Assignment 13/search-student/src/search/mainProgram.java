package search;

import java.util.ArrayList;
import java.util.List;

import mazes.Cell;
import mazes.Maze;
import mazes.MazeGenerator;

public class mainProgram {

    public static void main(String[] args){
        Maze maze = new MazeGenerator(3, 3, 2).generateDfs();

        List<Cell> solution = new ArrayList<Cell>();
		final Searcher<Cell> s = new Searcher<Cell>(maze);
		solution.add(new Cell(1, 0));
		solution.add(new Cell(0, 0));
		solution.add(new Cell(0, 1));
		solution.add(new Cell(0, 2));
		solution.add(new Cell(1, 2));
        

        System.out.println(maze.getInitialState());
        System.out.println(solution.get(0));
        System.out.println(solution.get(0).equals(maze.getInitialState()));


    }
    
}
