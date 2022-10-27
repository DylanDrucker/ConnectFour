/**
 * This file is to be completed by you.
 *
 * @s2077148 <Please enter your matriculation number, not your name>
 */
public final class Model
{
	// ===========================================================================
	// ================================ CONSTANTS ================================
	// ===========================================================================
	// The most common version of Connect Four has 6 rows and 7 columns.
	public static final int DEFAULT_NR_ROWS = 6;
	public static final int DEFAULT_NR_COLS = 7;
	public static final int DEFAULT_NR_CONNECTS = 4;

	// ========================================================================
	// ================================ FIELDS ================================
	// ========================================================================
	// The size of the board.
	private int nrRows;
	private int nrCols;
	private int nrConnects;

	static String firstPlayersName;
	static String secondPlayersName;
	static boolean firstPlayersTurn;
	static boolean secondPlayersTurn;
	static boolean secondPlayerCPU = false;
	static boolean customization = false;
	static boolean fullBoard = false;
	static boolean win = false;
	static boolean replay = true;

	public static void changePlayer() {
		boolean tmpFirstPlayersTurn = firstPlayersTurn;
		firstPlayersTurn = secondPlayersTurn;
		secondPlayersTurn = tmpFirstPlayersTurn;
	}

	public void setNrRows(int r) {
		nrRows = r;
	}

	public void setNrCols(int c) {
		nrCols = c;
	}

	public void setNrConnects(int c) {
		nrConnects = c;
	}

	//Each grid space has 3 states, 0 (no piece), 1 (player 1's piece), 2 (player 2's piece)
	static int[][] grid = new int[1000][1000];

	int[] columnHeight = new int[1000];

	// =============================================================================
	// ================================ CONSTRUCTOR ================================
	// =============================================================================
	public Model()
	{
		// Initialise the board size to its default values.
		if (customization == false) {
			nrRows = DEFAULT_NR_ROWS;
			nrCols = DEFAULT_NR_COLS;
			nrConnects = DEFAULT_NR_CONNECTS;
		}


		//Set each grid space to 0
		for (int i = 0 ; i < nrRows; i++) {
			for (int j = 0; j < nrCols; j++) {
				grid[i][j] = 0;
			}
		}
		//set each column space to max value (it counts down to 0 every time a piece is placed)
		for (int i = 0 ; i < nrCols; i++) {
			columnHeight[i] = nrRows - 1;
		}


	}
	
	// ====================================================================================
	// ================================ MODEL INTERACTIONS ================================
	// ====================================================================================

	public void startingPlayer(int rand) {
		if (rand == 1) {
			firstPlayersTurn = true;
			secondPlayersTurn = false;
		}
		else {
			firstPlayersTurn = false;
			secondPlayersTurn = true;
		}
	}

	public boolean isMoveValid(int move)
	{
		boolean valid = false;
		if (move == 0) {
			valid = false;
		}
		else if ((move - 1) < nrCols) {
			if (columnHeight[move - 1] >= 0) {
				valid = true;
			}
		}
		else {
			valid = false;
			}
		return valid;
	}

	public static int winningPlayer() {
		int w;
		if (firstPlayersTurn) {
			w = 2;
		}
		else {
			w = 1;
		}
		return w;
	}

	public boolean isPlayerGivingUp(int move) {
		boolean givingUp = false;
		if (move == 123456789) {
			givingUp = true;
		}
		return givingUp;
	}

	public boolean isBoardFull() {
		boolean full = true;
		for (int i = 0 ; i < nrRows; i++) {
			for (int j = 0; j < nrCols; j++) {
				if (grid[i][j] == 0) {
					full = false;
					break;
				}
			}
		}
		return full;
	}

	public void makeMove(int move)
	{
		if (firstPlayersTurn) {
			grid[columnHeight[move - 1]][move - 1] = 1;
		}
		if (secondPlayersTurn) {
			grid[columnHeight[move - 1]][move - 1] = 2;
		}
		columnHeight[move - 1] --;
		changePlayer();
	}

	public int generateRandomInt(int min, int max) {
		return ((int)(Math.random() * (max - min + 1) + min));
	}

	public boolean checkHorizontalWin() {
		boolean connected = false;
		int consecutive = 0;
		for (int i = 0; i < nrRows; i++) {
			if (consecutive == nrConnects -1) {
				break;
			}
			else {
				consecutive = 0;
			}
			for (int j = 0; j <= (nrCols - nrConnects); j++) {
				if (consecutive == nrConnects -1) {
					break;
				}
				else {
					consecutive = 0;
				}
				//checks if nrConnects consecutive pieces are equal to each other and aren't blank spaces
				for (int k = 0; k < nrConnects - 1; k++) {
					if (consecutive == nrConnects-1) {
						break;
					}
					if (grid [i][j + k] != 0) {
						if (grid[i][j + k] == grid[i][j + k + 1]) {
							consecutive += 1;
						}
						else {
							consecutive = 0;
						}
					}
					else {
						consecutive = 0;
					}
				}
			}
		}
		if (consecutive == nrConnects - 1) {
			connected = true;
		}
		return connected;
	}

	public boolean checkVerticalWin() {
		boolean connected = false;
		int consecutive = 0;
		for (int j = 0; j < nrCols; j++) {
			if (consecutive == nrConnects -1) {
				break;
			}
			else {
				consecutive = 0;
			}
			for (int i = 0; i <= (nrRows - nrConnects); i++) {
				if (consecutive == nrConnects -1) {
					break;
				}
				else {
					consecutive = 0;
				}
				//checks if nrConnects consecutive pieces are equal to each other and aren't blank spaces
				for (int k = 0; k < nrConnects - 1; k++) {
					if (consecutive == nrConnects-1) {
						break;
					}
					if (grid [i + k][j] != 0) {
						if (grid[i + k][j] == grid[i + k + 1][j]) {
							consecutive += 1;
						}
						else {
							consecutive = 0;
						}

					}
					else {
						consecutive = 0;
					}
				}
			}
		}
		if (consecutive == nrConnects - 1) {
			connected = true;
		}
		return connected;
	}

	public boolean checkLeftDiagonalWin() {
		boolean connected = false;
		int consecutive = 0;
		for (int i = 0; i <= (nrRows - nrConnects); i++) {
			if (consecutive == nrConnects - 1) {
				break;
			}
			else {
				consecutive = 0;
			}
			for (int j = 0; j <= (nrCols - nrConnects); j++) {
				if (consecutive == nrConnects - 1) {
					break;
				}
				else {
					consecutive = 0;
				}
				//checks if nrConnects consecutive pieces are equal to each other and aren't blank spaces
				for (int k = 0; k < nrConnects - 1; k++) {
					if (consecutive == nrConnects - 1) {
						break;
					}
					if (grid[i + k][j + k] != 0) {
						if (grid[i + k][j + k] == grid[i + k + 1][j + k + 1]) {
							consecutive += 1;
						} else {
							consecutive = 0;
						}
					}
					else {
						consecutive = 0;
					}
				}
			}

		}
		if (consecutive == nrConnects - 1) {
			connected = true;
		}
		return connected;
	}

	public boolean checkRightDiagonalWin() {
		boolean connected = false;
		int consecutive = 0;
		for (int i = 0; i <= (nrRows - nrConnects); i++) {
			if (consecutive == nrConnects - 1) {
				break;
			}
			else {
				consecutive = 0;
			}
			for (int j = nrCols - 1; j >= nrConnects - 1; j--) {
				if (consecutive == nrConnects - 1) {
					break;
				}
				else {
					consecutive = 0;
				}
				//checks if nrConnects consecutive pieces are equal to each other and aren't blank spaces
				for (int k = 0; k < nrConnects - 1; k++) {
					if (consecutive == nrConnects - 1) {
						break;
					}
					if (grid[i + k][j - k] != 0) {
						if (grid[i + k][j - k] == grid[i + k + 1][j - k - 1]) {
							consecutive += 1;
						}
						else {
							consecutive = 0;
						}
					}
					else {
						consecutive = 0;
					}
				}
			}

		}
		if (consecutive == nrConnects - 1) {
			connected = true;
		}
		return connected;
	}

	//Function that tests all of the win conditions at once
	public void checkWin() {
		if (checkHorizontalWin() || checkVerticalWin() || checkLeftDiagonalWin() || checkRightDiagonalWin()) {
			Model.win = true;
		}
	}
	//sets grid and columnheight back to initial values
	public void restartGame() {
		for (int i = 0 ; i < nrRows; i++) {
			for (int j = 0; j < nrCols; j++) {
				grid[i][j] = 0;
			}
		}
		for (int i = 0 ; i < nrCols; i++) {
			columnHeight[i] = nrRows - 1;
		}
	}
	
	// =========================================================================
	// ================================ GETTERS ================================
	// =========================================================================
	public int getNrRows()
	{
		return nrRows;
	}
	
	public int getNrCols()
	{
		return nrCols;
	}
	public int getGrid(int i, int j)
	{
		return grid[i][j];
	}
}
