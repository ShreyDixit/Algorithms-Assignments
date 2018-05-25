
/******************************************************************************
 *  Compilation:  javac Percolation.java
 *  Execution:    java Percolation
 *  Dependencies: WeightedQuickUnionUF.java
 *  Name:		  Shrey Dixit
 *  Date:         25th May 2018
 *  Implements percolation grid
 *
 ******************************************************************************/
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private WeightedQuickUnionUF grid;
	private boolean[] status;
	private int count, num = 0;

	public Percolation(int n) // create n-by-n grid, with all sites blocked
	{
		if (n < 1)
			throw new java.lang.IllegalArgumentException("n should be greater than 0");
		grid = new WeightedQuickUnionUF(n * n + 2);
		status = new boolean[n * n];
		count = n;
	}

	public void open(int row, int col) // open site (row, col) if it is not open already
	{
		if (row < 1 || row > count || col < 1 || col > count)
			throw new java.lang.IllegalArgumentException("row/column out of index");
		else if (status[(row - 1) * count + col - 1])
			return;
		status[(row - 1) * count + col - 1] = true;
		num++;
		if (row == count)
			grid.union((row - 1) * count + col - 1, count * count + 1);
		else if (row < count && status[(row) * count + col - 1])
			grid.union((row - 1) * count + col - 1, (row) * count + col - 1);
		if (row == 1)
			grid.union((row - 1) * count + col - 1, count * count);
		else if (row > 1 && status[(row - 2) * count + col - 1])
			grid.union((row - 1) * count + col - 1, (row - 2) * count + col - 1);
		if (col < count && status[(row - 1) * count + col])
			grid.union((row - 1) * count + col - 1, (row - 1) * count + col);
		if (col > 1 && status[(row - 1) * count + col - 2])
			grid.union((row - 1) * count + col - 1, (row - 1) * count + col - 2);
	}

	public boolean isOpen(int row, int col) // is site (row, col) open?
	{
		if (row < 1 || row > count || col < 1 || col > count)
			throw new java.lang.IllegalArgumentException("row/column out of index");
		return status[(row - 1) * count + col - 1];
	}

	public boolean isFull(int row, int col) // is site (row, col) full?
	{
		if (row < 1 || row > count || col < 1 || col > count)
			throw new java.lang.IllegalArgumentException("row/column out of index");
		return grid.connected((row - 1) * count + col - 1, count * count);
	}

	public int numberOfOpenSites() // number of open sites
	{
		return num;
	}

	public boolean percolates() // does the system percolate?
	{
		return grid.connected(count * count + 1, count * count);
	}

}
