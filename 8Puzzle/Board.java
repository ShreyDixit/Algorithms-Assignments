import java.util.ArrayList;

public class Board {
	private int mScore = 0, hScore = 0, n, x, y;
	private int[][] board;

	public Board(int[][] blocks) // construct a board from an n-by-n array of blocks
	{ // (where blocks[i][j] = block in row i, column j)
		n = blocks.length;
		board = new int[n][n];
		for (int i = 0, temp; i < n; i++) {
			for (int j = 0; j < n; j++) {
				temp = blocks[i][j];
				board[i][j] = temp;
				if (temp != i * n + j + 1)
					hScore++;
			}
		}
		hScore--;
		for (int i = 0, temp; i < n; i++) {
			for (int j = 0; j < n; j++) {
				temp = blocks[i][j];
				if (temp == 0) {
					x = i;
					y = j;
				} else if (temp != i * n + j + 1) {
					mScore += Math.abs((temp - 1) / n - i) + Math.abs((temp - 1) % n - j);
				}
			}
		}
	}

	public int dimension() // board dimension n
	{
		return n;
	}

	public int hamming() // number of blocks out of place
	{
		return hScore;
	}

	public int manhattan() // sum of Manhattan distances between blocks and goal
	{
		return mScore;
	}

	public boolean isGoal() // is this board the goal board?
	{
		return hScore == 0;
	}

	public Board twin() // a board that is obtained by exchanging any pair of blocks
	{
		int[][] board1 = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				board1[i][j] = board[i][j];
		}

		if (board[0][0] == 0 || board[0][1] == 0) {
			board1[n - 1][n - 1] = board[n - 1][n - 2];
			board1[n - 1][n - 2] = board[n - 1][n - 1];
		} else {
			board1[0][0] = board[0][1];
			board1[0][1] = board[0][0];
		}
		return new Board(board1);
	}

	public boolean equals(Object y) // does this board equal y?
	{
		if (y == this)
			return true;
		if (y == null)
			return false;
		if (y.getClass() != this.getClass())
			return false;
		Board that = (Board) y;
		if (n != that.dimension())
			return false;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				if (that.board[i][j] != this.board[i][j])
					return false;
		}
		return true;
	}

	public Iterable<Board> neighbors() // all neighboring boards
	{
		ArrayList<Board> array = new ArrayList<Board>();
		if (x != 0) {
			board[x][y] = board[x - 1][y];
			board[x - 1][y] = 0;
			array.add(new Board(board));
			board[x - 1][y] = board[x][y];
			board[x][y] = 0;
		}
		if (x != n - 1) {
			board[x][y] = board[x + 1][y];
			board[x + 1][y] = 0;
			array.add(new Board(board));
			board[x + 1][y] = board[x][y];
			board[x][y] = 0;
		}
		if (y != 0) {
			board[x][y] = board[x][y - 1];
			board[x][y - 1] = 0;
			array.add(new Board(board));
			board[x][y - 1] = board[x][y];
			board[x][y] = 0;
		}
		if (y != n - 1) {
			board[x][y] = board[x][y + 1];
			board[x][y + 1] = 0;
			array.add(new Board(board));
			board[x][y + 1] = board[x][y];
			board[x][y] = 0;
		}
		return array;
	}

	public String toString() // string representation of this board (in the output format specified below)
	{
		String rep = n + "\n ";
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				rep += " " + board[i][j] + " ";
			rep += "\n";
		}
		return rep;
	}

	public static void main(String[] args) // unit tests (not graded)
	{

	}
}
