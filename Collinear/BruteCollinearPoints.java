import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;

public class BruteCollinearPoints {
	private int N = 0;
	private ArrayList<LineSegment> array = new ArrayList<LineSegment>();

	public BruteCollinearPoints(Point[] points) // finds all line segments containing 4 points
	{
		if (points == null)
			throw new java.lang.IllegalArgumentException("");
		int length = points.length;
		Point[] temp = new Point[length];
		for (int i = 0; i < length; i++) {
			if (points[i] == null)
				throw new java.lang.IllegalArgumentException("");
			temp[i] = points[i];
		}
		Arrays.sort(temp);
		for(int i=0;i<length-1;i++)
		{
			if(temp[i].slopeTo(temp[i+1])==Double.NEGATIVE_INFINITY)
				throw new java.lang.IllegalArgumentException("");
		}
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < i; j++) {
				for (int k = 0; k < j; k++) {
					if (temp[i].slopeTo(temp[j]) == temp[i].slopeTo(temp[k])) {
						for (int l = 0; l < k; l++) {
							if ((temp[i].slopeTo(temp[j]) == temp[i].slopeTo(temp[l]))) {
								N++;
								array.add(new LineSegment(temp[i], temp[l]));
							}
						}
					}
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
		BruteCollinearPoints collinear = new BruteCollinearPoints(points);
		for (LineSegment segment : collinear.segments()) {
			System.out.println(segment.toString());
			segment.draw();
		}
		StdDraw.show();
	}

}
