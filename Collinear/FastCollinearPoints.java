package Week3;

import java.util.ArrayList;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;

import java.util.Arrays;

public class FastCollinearPoints {
	private int N = 0;
	private ArrayList<LineSegment> array = new ArrayList<LineSegment>();

	public FastCollinearPoints(Point[] points) // finds all line segments containing 4 or more points
	{
		if (points == null)
			throw new java.lang.IllegalArgumentException("");
		int length = points.length;
		int last, count = 0, j = 1;
		double slope = 0;
		Point[] temp = new Point[length];
		for (int i = 0; i < length; i++) {
			if (points[i] == null)
				throw new java.lang.IllegalArgumentException("");
			temp[i] = points[i];
		}
		for (int i = 0; i < length; i++) {
			Arrays.sort(temp, points[i].slopeOrder());
			if(temp[Math.min(1, length-1)].slopeTo(temp[0])==Double.NEGATIVE_INFINITY&&length>1)
				throw new java.lang.IllegalArgumentException("");
			last = 1;
			slope = temp[0].slopeTo(temp[0]);
			count = 0;
			for (j = 1; j < length; j++) {
				if (slope == temp[0].slopeTo(temp[j])) {
					count++;
					if (temp[0].compareTo(temp[j]) > 0) {
						count = 0;
						while (++j < length && slope == temp[0].slopeTo(temp[j]))
							;
						--j;
					} else if (temp[last].compareTo(temp[j]) < 0)
						last = j;
				} else {
					if (count > 2) {
						N++;
						array.add(new LineSegment(temp[0], temp[last]));
					}
					if (temp[0].compareTo(temp[j]) > 0) {
						slope = temp[0].slopeTo(temp[j]);
						while (++j < length && slope == temp[0].slopeTo(temp[j]))
							;
						--j;
					}
					else
					{
						slope = temp[0].slopeTo(temp[j]);
						last=j;
					}
					count = 1;
				}
			}
			if (count > 2) {
				if (j == length) {
					N++;
					array.add(new LineSegment(temp[0], temp[last]));
				}
			}
		}
	}

	public int numberOfSegments() // the number of line segments
	{
		return N;
	}

	public LineSegment[] segments() // the line segments
	{
		LineSegment[] array2 = new LineSegment[N];
		int j = 0;
		for (LineSegment i : array) {
			array2[j++] = i;
		}
		return array2;
	}

	public static void main(String[] args) {
		// read the n points from a file
		int n = StdIn.readInt();
		Point[] points = new Point[n];
		for (int i = 0; i < n; i++) {
			int x = StdIn.readInt();
			int y = StdIn.readInt();
			points[i] = new Point(x, y);
		}

		// draw the points
		StdDraw.enableDoubleBuffering();
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		for (Point p : points) {
			p.draw();
		}
		StdDraw.show();

		// print and draw the line segments
		FastCollinearPoints collinear = new FastCollinearPoints(points);
		for (LineSegment segment : collinear.segments()) {
			System.out.println(segment.toString());
			segment.draw();
		}
		StdDraw.show();
	}
}
