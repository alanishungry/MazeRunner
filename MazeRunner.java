import java.util.*;
/**
 * This class models path finding within a maze.
 *
 * @author Alan Guo
 * @version 5/3/2020
 */
public class MazeRunner
{
    private class InnerMazeRunner {
        private Queue<Path> q;
        private int[][] grid;
        
        public InnerMazeRunner(int[][] grid, Point start) {
            this.q = new LinkedList<>();
            Path path = new Path();
            path.add(start);
            this.q.offer(path);
            
            this.grid = grid;
        }
        
        public List<Path> getPaths() {
            return new ArrayList<>(q);
        }
        
        public void runNextLevel() {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Path currPath = q.poll();
                for (Point neighbor : getNeighbors(currPath.getLast())) {
                    if (!currPath.contains(neighbor) && grid[neighbor.row][neighbor.col] != 1) {
                        Path nextPath = new Path(currPath);
                        nextPath.add(neighbor);
                        q.offer(nextPath);
                    }
                }
            }
        }
        
        private List<Point> getNeighbors(Point p) {
            List<Point> neighbors = new ArrayList<>();
            if (p.row - 1 >= 0) { // up
                neighbors.add(new Point(p.row - 1, p.col));
            }
            if (p.col - 1 >= 0) { // left
                neighbors.add(new Point(p.row, p.col - 1));
            }
            if (p.row + 1 < grid.length) { // down
                neighbors.add(new Point(p.row + 1, p.col));
            }
            if (p.col + 1 < grid[0].length) { // right
                neighbors.add(new Point(p.row, p.col + 1));
            }
            return neighbors;
        }
    }

    /**
     * Starts two "inner maze runners" one at start and one at end.
     * They take turns their next possible moves and as soon as they have
     * intersecting possibilities / paths, we return the path that meets
     * at that intersection.
     *
     * This algorithm guarantees one of the shortest paths between start and end.
     * @param maze / grid
     * @param start point
     * @param end point
     * @return A possible shortest path between start and end
     * @throws Exception if no possible path between start and end
     */
    public Path run(int[][] maze, Point start, Point end) throws Exception {
        if (start == end) {
            Path p = new Path();
            p.add(start);
            return p;
        }
        
        boolean toggle = false;
        InnerMazeRunner startRunner = new InnerMazeRunner(maze, start);
        InnerMazeRunner endRunner = new InnerMazeRunner(maze, end);
        while (!startRunner.getPaths().isEmpty() && !endRunner.getPaths().isEmpty()) {
            Path path = mergePathsIfPossible(startRunner.getPaths(), endRunner.getPaths());
            if (path != null) {
                return path;
            } else {
                if (toggle) {
                    startRunner.runNextLevel();
                } else {
                    endRunner.runNextLevel();
                }
                toggle = !toggle;
            }
        }
        
        throw new Exception("No possible path found.");
    }
    
    /**
     * Checks if ends of both paths intersect at any point.
     * If they do, merge paths at the first intersection we encounter.
     * If not, return null.
     */
    private Path mergePathsIfPossible(List<Path> a, List<Path> b) {
        for(int i = 0; i < a.size(); i++) {
            for (int j = 0; j < b.size(); j++) {
                if (a.get(i).getLast().equals(b.get(j).getLast())) {
                    return mergePaths(a.get(i), b.get(i));
                }
            }
        }

        return null;
    }
    
    // Merges a to the reverse of b
    private Path mergePaths(Path a, Path b) {
        List<Point> bPoints = b.getPoints();
        for (int i = bPoints.size() - 1; i >= 0 ; i--) {
            a.add(bPoints.get(i));
        }
        return a;
    }
}
