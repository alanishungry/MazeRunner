import java.util.*;

public class Path {
    List<Point> points;
    
    public Path() {
        this.points = new ArrayList<>();
    }
    
    public Path(Path path) {
        this.points = new ArrayList<>(path.getPoints());
    }
    
    public List<Point> getPoints() {
        return points;
    }
    
    public void add(Point p) {
        points.add(p);
    }
    
    public Point getLast() {
        if (points.size() < 1) {
            return null;
        }
        return points.get(points.size() - 1);
    }
    
    public void visualizeOnMaze(int[][] maze) {
        for (Point p : points) {
            maze[p.row][p.col] = 2;
        }
        for (int[] row : maze) {
            System.out.println(Arrays.toString(row));
        }
    }

    public boolean contains(Point p) {
        for (Point point : points) {
            if (point.equals(p)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String toString() {
        return points.toString();
    }
}