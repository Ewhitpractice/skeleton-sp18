package hw2;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    //instance variables
    private boolean[] grid;
    private int sideSize;
    private int openSpaces;
    private WeightedQuickUnionUF totalGrid;
    private WeightedQuickUnionUF backwash;
    private int topOfGrid;
    private int bottomOfGrid;

    /** creates a new grid of size N*N. closes all of the squares on the grid **/
    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        grid = new boolean[N * N + 2];
        sideSize = N;
        totalGrid = new WeightedQuickUnionUF(N * N + 2);
        backwash = new WeightedQuickUnionUF(N * N + 1);
        topOfGrid = N * N;
        bottomOfGrid = N * N + 1;
        openSpaces = 0;
        initializeGrid();
    }

    /** if a square is clicked on, open that square if it is closed
     * check to see if the neighboring squares are open and union if true**/
    public void open(int row, int col) {
        checkArgument(row, col);
        int currentSquare = xyTo1D(row, col);
        int left = currentSquare - 1;
        int right = currentSquare + 1;
        int up = currentSquare - sideSize;
        int down = currentSquare + sideSize;

        if (!grid[currentSquare]) {
            grid[currentSquare] = true;
            openSpaces++;
        }

        if (left > 0 && left < sideSize * sideSize && grid[left] && currentSquare % sideSize != 0) {
            backwash.union(currentSquare, left);
            totalGrid.union(currentSquare, left);
            if (backwash.connected(left, topOfGrid)) {
                totalGrid.union(topOfGrid, currentSquare);
                backwash.union(topOfGrid, currentSquare);
            }
        }
        if (right > 0 && right < sideSize * sideSize && grid[right]) {
            backwash.union(currentSquare, right);
            totalGrid.union(currentSquare, right);
            if (backwash.connected(right, topOfGrid)) {
                totalGrid.union(topOfGrid, currentSquare);
                backwash.union(topOfGrid, currentSquare);
            }
        }
        if (up > 0 && up < sideSize * sideSize && grid[up]) {
            backwash.union(currentSquare, up);
            totalGrid.union(currentSquare, up);
            if (backwash.connected(up, topOfGrid)) {
                totalGrid.union(topOfGrid, currentSquare);
                backwash.union(topOfGrid, currentSquare);
            }
        }
        if (down > 0 && down < sideSize * sideSize && grid[down]) {
            backwash.union(currentSquare, down);
            totalGrid.union(currentSquare, down);
            if (backwash.connected(down, topOfGrid)) {
                totalGrid.union(topOfGrid, currentSquare);
                backwash.union(topOfGrid, currentSquare);
            }
        }
    }


    /** check to see if a certain spot in the grid is open **/
    public boolean isOpen(int row, int col) {
        checkArgument(row, col);
        int currentSquare = xyTo1D(row, col);
        return grid[currentSquare];
    }

    /** check to see if a certain spot on the grid is full **/
    public boolean isFull(int row, int col) {
        checkArgument(row, col);
        int currentSquare = xyTo1D(row, col);
        if(backwash.connected(topOfGrid, currentSquare) && grid[currentSquare])
        {
            return true;
        }
        return false;
    }

    public int numberOfOpenSites() {
        return openSpaces;
    }

    public boolean percolates() {
        return totalGrid.connected(topOfGrid, bottomOfGrid);
    }

    //helper method
    private void initializeGrid() {
        for (int i = 0; i < sideSize; i++) {
            backwash.union(topOfGrid, i);
        }
        for (int i = sideSize * sideSize; i > sideSize * sideSize - sideSize; i--) {
            totalGrid.union(bottomOfGrid, i);
        }
    }

    private int xyTo1D(int r, int c) {
        return (r * sideSize + c);
    }

    private void checkArgument(int row, int col) {
        if (row < 0 || row > sideSize - 1 || col < 0 || col > sideSize - 1) {
            throw new java.lang.IndexOutOfBoundsException();
        }
    }


}
