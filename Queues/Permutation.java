import Week1.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class Permutation {
	public static void main(String[] args) {
		RandomizedQueue<String> queue = new RandomizedQueue<String>();
		int k = Integer.parseInt(args[2]);
		while (!StdIn.isEmpty()) {
			queue.enqueue(StdIn.readString());
		}
		while (k-- > 0) {
			StdOut.println(queue.dequeue());
		}
	}
}
