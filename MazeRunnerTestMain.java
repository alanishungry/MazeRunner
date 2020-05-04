import java.util.*;
public class MazeRunnerTestMain
{
    public static void main (String[] args) {
        int[][] maze = new int[][]{
            {0,1,1,1,0,0,0,0},
            {0,1,1,1,0,1,1,0},
            {0,0,0,1,1,0,0,0},
            {0,1,0,0,0,1,0,0},
            {0,1,1,0,1,0,1,0},
            {1,0,0,0,0,0,0,0}
        };
        MazeRunner runner = new MazeRunner();
        Path path;
        try {
            path = runner.run(maze, new Point(0,0), new Point(1,4));
            System.out.println("Original: " );
            printMaze(maze);
            System.out.println("Path: ");
            path.visualizeOnMaze(maze);
        } catch (Exception e) {
            System.out.println("No possible path found.");
        }
    }

    public static void printMaze(int[][] grid) {
        for (int[] row : grid) {
            System.out.println(Arrays.toString(row));
        }
    }
}
