package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    //instance variables
    private PercolationFactory makePercolation;
    private int timesRepeated;
    private int sideSize;
    private int[] openSitesArray;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        makePercolation = pf;
        timesRepeated = T;
        sideSize = N;
        openSitesArray = new int[timesRepeated];
        makeArray();
    }

    public double mean() {
        return StdStats.mean(openSitesArray);
    }

    public double stddev() {
        return StdStats.stddev(openSitesArray);

    }

    public double confidenceLow() {
        return (mean() - ((1.96 * (stddev())) / Math.sqrt(timesRepeated)));

    }

    public double confidenceHigh() {
        return (mean() + ((1.96 * (stddev())) / Math.sqrt(timesRepeated)));
    }

    //helper methods
    private int findNumberOfOpenSites() {
        //initialize all sites to be blocked
        Percolation percolation = makePercolation.make(sideSize);
        //repeat until system percolates
        while (!percolation.percolates()) {
            //choose a random blocked site and open
            int randomRow = StdRandom.uniform(sideSize);
            int randomCol = StdRandom.uniform(sideSize);
            while (percolation.isOpen(randomRow, randomCol)) {
                randomRow = StdRandom.uniform(sideSize);
                randomCol = StdRandom.uniform(sideSize);
            }
            percolation.open(randomRow, randomCol);
        }
        return percolation.numberOfOpenSites();
    }

    private void makeArray() {
        for (int i = 0; i < timesRepeated; i++) {
            openSitesArray[i] = findNumberOfOpenSites();
        }
    }

}
