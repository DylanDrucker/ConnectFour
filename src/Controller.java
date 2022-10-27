/**
 * This file is to be completed by you.
 *
 * s2077148 <Please enter your matriculation number, not your name>
 */
public final class Controller
{
	private final Model model;
	private final TextView view;
	
	public Controller(Model model, TextView view)
	{
		this.model = model;
		this.view = view;
	}
	
	public void startSession() {
		view.displayWelcome();
		Model.firstPlayersName = view.displayColorCollection1();
		Model.secondPlayersName = view.displayColorCollection2();
		if (Model.secondPlayersName.equals("CPU")) {
			Model.secondPlayerCPU = true;
			view.displayCPUConfirmation();
		}
		if (view.displayCustomization() == 'y') {
			Model.customization = true;
			model.setNrRows(view.displayCustomizeRows());
			model.setNrCols(view.displayCustomizeCols());
			model.setNrConnects(view.displayCustomizeConnects(model.getNrRows(), model.getNrCols()));
			model.restartGame();
		}
		while (Model.replay) {
			//determines whether the starting player is player 1 or player 2
			model.startingPlayer(model.generateRandomInt(1, 2));
			view.displayNewGameMessage();
			view.displayBoard(model);
			view.displayGiveUpInfo();
			while (Model.win == false && model.isBoardFull() == false) {
				view.displayPlayerTurn();
				int move = view.askForMove(model.generateRandomInt(1, model.getNrCols()));
				if (model.isPlayerGivingUp(move)) {
					if (view.displayGivingUpConfirmation() == 'y') {
						Model.win = true;
						break;
					}
				}
				while (model.isMoveValid(move) == false) {
					view.displayNotValid(move);
					move = view.askForMove(model.generateRandomInt(1, model.getNrCols()));
				}
				model.makeMove(move);
				view.displayBoard(model);

				model.checkWin();
			}
			if (model.isBoardFull() && Model.win == false) {
				view.displayBoardFull();
				Model.fullBoard = true;
			}
			view.displayGameOver();
			if (Model.fullBoard == false) {
				view.displayGameWinner();
			}
			if (view.displayContinue() == 'y') {
				Model.win = false;
				model.restartGame();
			}
			else {
				Model.replay = false;
			}
		}
		view.displayFarewell();
	}
}
