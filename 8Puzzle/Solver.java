import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

public class Solver {
	private int steps = 0;
	private Stack<Board> array = new Stack<Board>();
	private boolean solvable;

	public Solver(Board initial) // find a solution to the initial board (using the A* algorithm)
	{
		if (initial == null)
			throw new java.lang.IllegalArgumentException("");
		SearchNode current, currentTwin;
		MinPQ<SearchNode> tree = new MinPQ<SearchNode>();
		tree.insert(new SearchNode(initial, null, 0));
		MinPQ<SearchNode> tree2 = new MinPQ<SearchNode>();
		tree2.insert(new SearchNode(initial.twin(), null, 0));
		current = tree.delMin();
		currentTwin = tree2.delMin();
		Board board = null, boardTwin = null;

		while (!current.board.isGoal() && !currentTwin.board.isGoal()) {
			for (Board check : current.board.neighbors()) {
				if (!check.equals(board)) {
					tree.insert(new SearchNode(check, current, current.steps + 1));
				}
			}
			current = tree.delMin();
			board = current.previous.board;
			for (Board check : currentTwin.board.neighbors()) {
				if (!check.equals(boardTwin)) {
					tree2.insert(new SearchNode(check, currentTwin, currentTwin.steps + 1));
				}
			}
			currentTwin = tree2.delMin();
			boardTwin = currentTwin.previous.board;
		}
		if (currentTwin.board.isGoal()) {
			solvable = false;
			steps = -1;
			array=null;
		} else {
			steps = current.steps;
			array.push(current.board);
			solvable = true;
			current = current.previous;
			while (current != null) {
				array.push(current.board);
				current = current.previous;
			}
		}
	}

	public boolean isSolvable() // is the initial board solvable?
	{
		return solvable;
	}

	public int moves() // min number of moves to solve initial board; -1 if unsolvable
	{
		return steps;
	}

	public Iterable<Board> solution() // sequence of boards in a shortest solution; null if unsolvable
	{
		return array;
	}

	private class SearchNode implements Comparable<SearchNode> {
		public Board board;
		public SearchNode previous;
		public int steps;
		private int manhattan;

		public SearchNode(Board current, SearchNode node, int n) {
			board = current;
			previous = node;
			steps = n;
			manhattan = this.board.manhattan() + this.steps;
		}

		public int compareTo(SearchNode that) {
			return this.manhattan - that.manhattan;
		}
	}
}

