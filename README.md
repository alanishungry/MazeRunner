###Summary
Finds shortest path on a grid between a start and end point. Grid is rectangular and contains 0s and 1s, where 0 
represents a valid space we can walk over and 1 represents a wall we cannot walk onto.

###How to run
Just run the main method in MazeRunnerTestMain. You can create your own grid to test that it works.
The code now will print this, showing you the path marked with 2s:
```
Original: 
[0, 1, 1, 1, 0, 0, 0, 0]
[0, 1, 1, 1, 0, 1, 1, 0]
[0, 0, 0, 1, 1, 0, 0, 0]
[0, 1, 0, 0, 0, 1, 0, 0]
[0, 1, 1, 0, 1, 0, 1, 0]
[1, 0, 0, 0, 0, 0, 0, 0]
Path: 
[2, 1, 1, 1, 2, 2, 2, 2]
[2, 1, 1, 1, 2, 1, 1, 2]
[2, 2, 2, 1, 1, 0, 0, 2]
[0, 1, 2, 2, 0, 1, 0, 2]
[0, 1, 1, 2, 1, 0, 1, 2]
[1, 0, 0, 2, 2, 2, 2, 2]
```

###Algorithm

Algorithm is essentially BFS from both start and end until they intersect, at which point we merge the path at the
intersection. This guarantees us to find one of the shortest paths between start and end.

### Runtime / Space Analysis

####Runtime Analysis
The runtime is the same as normal BFS. So, the runtime is O(n*m) where n and m are the grid dimensions. 
This is because worst case we will check all grid spaces.

####Space Analysis
Space is also O(n*m) because we remember all the paths we took during the BFS. We can imagine a worst case scenario
where our BFS contains all possible spaces in the grid.

We save space by a factor of ~2 compared to normal BFS, so the big O space remains the same.

Why by a factor of 2?

Imagine doing normal BFS from start point only. Let the distance to end point be 2r. Then, the area of a circle with
radius 2r is pi * 4 * r^2.

Now, imagine a BFS from start and BFS from end. These will each produce their own circle of radius r (roughly). Each
circle's area will be pi * r^2. Total area will be 2 * pi * r^2. This is half the area of the circle for normal BFS from 
start only.

###Other considerations:
- A* search could find us a good enough solution, but might tell us there is no path, when there is one
- Single BFS is much simpler to write, and maybe better to tradeoff this less complexity for a little more space usage
