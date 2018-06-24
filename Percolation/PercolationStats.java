
/******************************************************************************
 *  Compilation:  javac PercolationStats.java
 *  Execution:    java PercolationStats
 *  Dependencies: StdRandom.java StdOut.java StdRandom.java
 *  Name:		  Shrey Dixit
 *  Date:         25th May 2018
 *  Returns stats of percolation
 *
 ******************************************************************************/
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {
	private double[] exp;
	private double mean, stddev;
	private int T;

	public PercolationStats(int n, int trials) // perform trials independent experiments on an n-by-n grid
	{
		Percolation grid;
		if (n < 1 || trials < 1)
			throw new java.lang.IllegalArgumentException("n and trials should be greater than 0");
		int index, num;
		T = trials;
		exp = new double[trials];
		while (trials > 0) {
			num = 0;
			grid = new Percolation(n);
			while (!grid.percolates()) {
				do {
					index = StdRandom.uniform(0, n * n);
				} while (grid.isOpen(index / n + 1, index % n + 1));
				grid.open(index / n + 1, index % n + 1);
				num++;
			}
			exp[--trials] = (double) num / (double) (n * n);
		}
	}

	public double mean() // sample mean of percolation threshold
	{
		return mean = StdStats.mean(exp);
	}

	public double stddev() // sample standard deviation of percolation threshold
	{
		return stddev = StdStats.stddev(exp);
	}

	public double confidenceLo() // low endpoint of 95% confidence interval
	{
		return (mean - 1.96 * stddev / Math.sqrt(T));
	}

	public double confidenceHi() // high endpoint of 95% confidence interval
	{
		return (mean + 1.96 * stddev / Math.sqrt(T));
	}

	public static void main(String[] args) // test client
	{
		PercolationStats grid = new PercolationStats(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
		StdOut.print("mean =" + grid.mean() + "\nstddev = " + grid.stddev() + "\n95% confidence interval = ["
				+ grid.confidenceLo() + ", " + grid.confidenceHi() + "]");
	}
}
