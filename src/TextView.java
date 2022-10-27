/**
 * This file is to be completed by you.
 *
 * @s2077148 <Please enter your matriculation number, not your name>
 */
import java.util.Random;
public final class TextView
{
	public TextView()
	{
	
	}

	public final void displayWelcome() {
		System.out.println("Welcome to s2077148's Connect 4 game!");
		System.out.println("__________________________________________________________________________");
		System.out.println("--------------------------------------------------------------------------");
		System.out.println(" _______  _______  __    _  __    _  _______  _______  _______    _   ___ ");
		System.out.println("|       ||       ||  |  | ||  |  | ||       ||       ||       |  | | |   |");
		System.out.println("|    ___||   _   ||   |_| ||   |_| ||    ___||    ___||_     _|  | |_|   |");
		System.out.println("|   |    |  | |  ||       ||       ||   |___ |   |      |   |    |       |");
		System.out.println("|   |    |  |_|  ||  _    ||  _    ||    ___||   |      |   |    |___    |");
		System.out.println("|   |___ |       || | |   || | |   ||   |___ |   |___   |   |        |   |");
		System.out.println("|_______||_______||_|  |__||_|  |__||_______||_______|  |___|        |___|");
		System.out.println("__________________________________________________________________________");
		System.out.println("--------------------------------------------------------------------------");
	}

	public final String displayColorCollection1() {
		String response = " ";
		System.out.print("Player 1: Choose the 'color' you'd like to play as: ");
		response = InputUtil.readStringFromUser();
		return response;
	}

	public final String displayColorCollection2() {
		String response = " ";
		boolean valid = false;
		while (valid == false) {
			System.out.print("Player 2: Choose the 'color' you'd like to play as (or write CPU to play against the AI): ");
			response = InputUtil.readStringFromUser();
			//checks that first letters of each name arent the same, to avoid confusion
			if (((response.substring(0,1).equals(Model.firstPlayersName.substring(0,1))) == false) || (response.equals("CPU"))) {
				valid = true;
			}
			else {
				System.out.println("Color cannot have same first letter as player 1's color. Try again");
			}
		}
		return response;
	}

	public final void displayCPUConfirmation() {
		System.out.println("Beep Boop. I am the CPU. Prepare to get destroyed human");
	}

	public final char displayCustomization() {
		char response = ' ';
		boolean valid = false;
		while (valid == false) {
			System.out.print("Would you like to customize your game? (y/n): ");
			response = InputUtil.readCharFromUser();
			if (response == 'y' || response == 'n') {
				valid = true;
			}
			else {
				System.out.println("Not a valid answer, try again");
			}
		}
		return response;
	}

	public final int displayCustomizeRows() {
		int response = 0;
		boolean valid = false;
		while (valid == false) {
			System.out.print("How many rows would you like the board to have: ");
			response = InputUtil.readIntFromUser();
			if (response <= 0) {
				System.out.println("Not a valid answer, there must be at least 1 row. Try again");
			}
			else if (response > 999) {
				System.out.println("Not a valid answer, rows/columns cannot exceed 999. Try again");
			}
			else {
				valid = true;
			}
		}
		return response;
	}

	public final int displayCustomizeCols() {
		int response = 0;
		boolean valid = false;
		while (valid == false) {
			System.out.print("How many columns would you like the board to have: ");
			response = InputUtil.readIntFromUser();
			if (response <= 0) {
				System.out.println("Not a valid answer, there must be at least 1 column. Try again");
			}
			else if (response > 999) {
				System.out.println("Not a valid answer, rows/columns cannot exceed 999. Try again");
			}
			else {
				valid = true;
			}
		}
		return response;
	}

	public final int displayCustomizeConnects(int r, int c) {
		int response = 0;
		boolean valid = false;
		while (valid == false) {
			System.out.print("How many pieces in a row would you like to win the game with: ");
			response = InputUtil.readIntFromUser();
			if (response > 0 && (response <= c || response <= r)) {
				valid = true;
			}
			else if (response == 0) {
				System.out.println("Not a valid answer, must be greater than 0");
			}
			else {
				System.out.println("Not a valid answer. Must be less than number of rows/columns");
			}
		}
		return response;
	}

	public final void displayNewGameMessage() {
		System.out.println("---- NEW GAME STARTED ----");
	}

	public final void displayGiveUpInfo() {
		System.out.println("If you wish to give up, type in 123456789");
	}

	public final void displayGameOver()  {
		System.out.println("------- GAME OVER --------");
	}

	public final void displayGameWinner() {
		System.out.println("Congratulations player " + Model.winningPlayer() +" , you are the Connect 4 Champion!");
	}

	public final char displayContinue() {
		char response = ' ';
		boolean valid = false;
		while (valid == false) {
			System.out.print("Would you like to start a new game? (y/n): ");
			response = InputUtil.readCharFromUser();
			if (response == 'y' || response == 'n') {
				valid = true;
			}
			else {
				System.out.println("Not a valid answer, try again");
			}
		}
		return response;
	}

	public final void displayPlayerTurn() {
		if (Model.firstPlayersTurn) {
			System.out.println("Player 1's Turn");
		}
		else if (Model.secondPlayersTurn) {
			System.out.println("Player 2's Turn");
		}
	}
	
	public final int askForMove(int rand) {
		int move = 0;
		if (Model.firstPlayersTurn || (Model.secondPlayerCPU == false)) {
			System.out.print("Select a free column: ");
			move = InputUtil.readIntFromUser();
		}
		else {
			//if the second player is a CPU then use the random integer to move
			move = rand;
		}
		return move;
	}

	public final void displayNotValid(int move) {
		System.out.println(move + " is not a valid move");

	}

	public final char displayGivingUpConfirmation() {
		char response = ' ';
		boolean valid = false;
		while (valid == false) {
			System.out.print("Are you sure you want to give up? (y/n): ");
			response = InputUtil.readCharFromUser();
			if (response == 'y' || response == 'n') {
				valid = true;
			}
			else {
				System.out.println("Not a valid answer, try again");
			}
		}
		return response;
	}

	public final void displayBoardFull() {
		System.out.println("Tie: The board is full");
	}

	public final void displayFarewell() {
		System.out.println("Thanks for Playing!");
	}
	
	public final void displayBoard(Model model)
	{
		String blankSpace   = "   |";
		String firstSpace   = " " + Model.firstPlayersName.substring(0,1) +" |";
		String secondSpace = " ";
		if (Model.secondPlayersName.equals("CPU")) {
			secondSpace = "CPU|";
		}
		else {
			secondSpace = " " + Model.secondPlayersName.substring(0,1) +" |";
		}
		int nrRows = model.getNrRows();
		int nrCols = model.getNrCols();
		// Get your board representation.
		String[] displayRow = new String[nrRows];
		for (int i = 0; i < nrRows; i++) {
			displayRow[i] = "|";
		}
		for (int i = 0; i < nrRows; i++) {
			for (int j = 0; j < nrCols; j++) {
				if (model.getGrid(i, j) == 0) {
					displayRow[i] = displayRow[i] + blankSpace;
				}
				if (model.getGrid(i, j) == 1) {
					displayRow[i] = displayRow[i] + firstSpace;
				}
				if (model.getGrid(i, j) == 2) {
					displayRow[i] = displayRow[i] + secondSpace;
				}
			}
		}
		// This can be used to print a line between each row.
		// You may need to vary the length to fit your representation.
		String rowDivider = "-".repeat(4 * nrCols + 1);
		String columnLabels = "  ";

			//makes the column labels below the board. The if statements are to change the number of spaces between numbers
			//depending on the size of the number and how many characters it takes to write it
			for (int i = 1; i <= nrCols; i++) {
				if (i > 99) {
					columnLabels += (i + " ");
				}
				else if (i > 9) {
					columnLabels += (i + "  ");
				}
				else {
					columnLabels += (i + "   ");
				}

	}

		// A StringBuilder is used to assemble longer Strings more efficiently.
		StringBuilder sb = new StringBuilder();
		
		// You can add to the String using its append method.

		sb.append(rowDivider);

		for (int i = 0; i < nrRows; i++) {
			sb.append("\n");
			sb.append(displayRow[i]);
			sb.append("\n");
			sb.append(rowDivider);
		}

		sb.append("\n");
		sb.append(columnLabels);

		// Then print out the assembled String.
		System.out.println(sb);
	}
}
