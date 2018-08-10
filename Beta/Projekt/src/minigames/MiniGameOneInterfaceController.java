package minigames;

import main.alertBox.AlertBox;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import main.MainGameInterfaceController;
import main.MainGameMethods;
import main.Player;
import main.standardMethods.EndMiniGame;
import main.standardMethods.SetAttributeLabels;
import main.standardMethods.SortPlayerList;
import userInterface.MainController;
import gains.*;

public class MiniGameOneInterfaceController {

	MainGameMethods mainGameMethods = new MainGameMethods();
	MainGameInterfaceController mainGame;

	Player player = new Player();
	Points points = new Points();
	Credits credits = new Credits();
	Ranks ranks = new Ranks();

	private int playerCounter;
	private int roundCounter;
	private int pointsGameLeader;
	private int creditsGameLeader;
	private int miniGameRound;
	private int endRoundCounter;

	private Label[][] attributeLabels = new Label[Player.amountOfPlayers][MainController.amountOfAttributes];

	private Player[] playerList;

	private int[] playerOrder;

	private CheckBox[] checkBoxList;

	private Button[] buttonList;

	private Label[] cubeList;
	private Label[] labelList;

	private boolean[] checkBoxValues;;

	public MiniGameOneInterfaceController(Player[] playerList, Label[] cubes, Label[] labelList,
			Label[][] attributeLabels, CheckBox[] checkBoxList, Button[] buttonList) {

		this.playerList = playerList;
		this.cubeList = cubes;
		this.labelList = labelList;
		this.attributeLabels = attributeLabels;
		this.checkBoxList = checkBoxList;
		this.buttonList = buttonList;

		playerOrder = new int[playerList.length];
		checkBoxValues = new boolean[checkBoxList.length];
	}

	// triesLabel, pointsLabel, roundsLabel, lastRoll, currentPlayerLabel,
	// playerNameTabel, playerPointsTabel,
	// playerCreditsTabel, playerDrinksTabel, playerRanksTabel,
	// amountOfSkChangeDice, priceOfSkChangeDice,
	// amountOfSkChangeName, priceOfSkChangeName;

	// newGameButton, nextPlayerButton, rollADice, methodTest, afterMiniGame,
	// buySkChangeDice,
	// useSkChangeDice, buySkChangeName, useSkChangeName;

	public void startGame() {

		int rankCounter = 1;
		for (int counter = 0; counter < playerList.length; counter++) {
			for (int i = 0; i < playerList.length; i++) {
				if (playerList[i].getRank() == rankCounter) {
					playerOrder[counter] = i + 1;
					rankCounter++;
					i = playerList.length;
				}
			}
		}

		MainController.currentPlayer = playerOrder[0];
		MainController.nextPlayer = playerOrder[1];
		playerCounter = 1;
		miniGameRound = 1;
		roundCounter = 1;
		MainController.tries = 1;

		labelList[MainController.labelListEnum.triesLabel.ordinal()]
				.setText(String.valueOf("Tries: " + MainController.tries));
		labelList[MainController.labelListEnum.currentPlayerLabel.ordinal()]
				.setText(playerList[MainController.currentPlayer - 1].getName() + "'s turn. Good luck!");
		labelList[MainController.labelListEnum.roundsLabel.ordinal()].setText("MiniGame Round: 1");

		buttonList[MainController.buttonListEnum.rollADice.ordinal()].setDisable(false);
		buttonList[MainController.buttonListEnum.nextPlayerButton.ordinal()].setDisable(true);

		new AlertBox().information("Mini Game","Mini Game 1 wurde ausgewählt");
	}

	public void nextPlayer() {

		// if (MainController.tries == miniGameRound) {
		// for (CheckBox check : checkBoxList) {
		// check.setSelected(false);
		// }
		//
		// }

		for (int i = 0; i < checkBoxList.length; i++) {
			checkBoxValues[i] = checkBoxList[i].isSelected();
		}

		if (mainGameMethods.checkHoldDices(checkBoxValues, MainGameMethods.currentDices)) {

			int hold;
			for (int i = 0; i < MainGameMethods.currentDices.length; i++) {
				for (int j = MainGameMethods.currentDices.length - 1; j > 0; j--) {

					if (MainGameMethods.currentDices[j - 1] > MainGameMethods.currentDices[j]) {
						hold = MainGameMethods.currentDices[j];
						MainGameMethods.currentDices[j] = MainGameMethods.currentDices[j - 1];
						MainGameMethods.currentDices[j - 1] = hold;
					}
				}
			}
			// Pairs
			boolean roundOne = false;
			boolean roundTwo = false;
			boolean roundThree = false;
			boolean roundFour = false;
			boolean roundFive = false;

			for (int i = 0; i < MainGameMethods.currentDices.length - 1; i++) {
				if (MainGameMethods.currentDices[i] == MainGameMethods.currentDices[i + 1]) {
					roundOne = true;
					int holder = i;

					for (int j = i + 1; j < MainGameMethods.currentDices.length - 1; j++) {
						if (miniGameRound == 1) {
							i = MainGameMethods.currentDices.length;
						}
						if (MainGameMethods.currentDices[holder] == MainGameMethods.currentDices[j + 1]) {
							roundTwo = true;

							for (int k = j + 1; k < MainGameMethods.currentDices.length - 1; k++) {
								if (miniGameRound <= 2) {
									i = MainGameMethods.currentDices.length;
									j = MainGameMethods.currentDices.length;
								}

								if (MainGameMethods.currentDices[holder] == MainGameMethods.currentDices[k + 1]) {
									roundThree = true;
									for (int l = k + 1; l < MainGameMethods.currentDices.length - 1; l++) {
										if (miniGameRound <= 3) {
											i = MainGameMethods.currentDices.length;
											j = MainGameMethods.currentDices.length;
											k = MainGameMethods.currentDices.length;
										}

										if (MainGameMethods.currentDices[holder] == MainGameMethods.currentDices[l
												+ 1]) {
											roundFour = true;
											for (int m = l + 1; m < MainGameMethods.currentDices.length - 1; m++) {
												if (miniGameRound <= 4) {
													i = MainGameMethods.currentDices.length;
													j = MainGameMethods.currentDices.length;
													k = MainGameMethods.currentDices.length;
													l = MainGameMethods.currentDices.length;
												}

												if (MainGameMethods.currentDices[holder] == MainGameMethods.currentDices[m
														+ 1]) {
													roundFive = true;
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}

			for (int i = 0; i < MainGameMethods.currentDices.length - 1; i++) {
				if (MainGameMethods.currentDices[i] + 1 == MainGameMethods.currentDices[i + 1]) {
					roundOne = true;
					int holder = i + 1;
					for (int j = i + 1; j < MainGameMethods.currentDices.length - 1; j++) {
						if (miniGameRound == 1) {
							i = MainGameMethods.currentDices.length;
						}
						if (MainGameMethods.currentDices[holder] + 1 == MainGameMethods.currentDices[j + 1]) {
							roundTwo = true;
							holder = j + 1;
							for (int k = j + 1; k < MainGameMethods.currentDices.length - 1; k++) {
								if (miniGameRound <= 2) {
									i = MainGameMethods.currentDices.length;
									j = MainGameMethods.currentDices.length;
								}
								if (MainGameMethods.currentDices[holder] + 1 == MainGameMethods.currentDices[k + 1]) {
									roundThree = true;
									holder = k + 1;
									for (int l = k + 1; l < MainGameMethods.currentDices.length - 1; l++) {
										if (miniGameRound <= 3) {
											i = MainGameMethods.currentDices.length;
											j = MainGameMethods.currentDices.length;
											k = MainGameMethods.currentDices.length;
										}
										if (MainGameMethods.currentDices[holder]
												+ 1 == MainGameMethods.currentDices[l + 1]) {
											roundFour = true;
											holder = l + 1;
											for (int m = l + 1; m < MainGameMethods.currentDices.length - 1; m++) {
												if (miniGameRound <= 4) {
													i = MainGameMethods.currentDices.length;
													j = MainGameMethods.currentDices.length;
													k = MainGameMethods.currentDices.length;
													l = MainGameMethods.currentDices.length;
												}
												if (MainGameMethods.currentDices[holder] == MainGameMethods.currentDices[m
														+ 1]) {
													roundFive = true;
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}

			int pointsHolder = playerList[MainController.currentPlayer - 1].getPoints();
			int creditsHolder = playerList[MainController.currentPlayer - 1].getCredits();
			boolean pased = false;

			if (miniGameRound == 1 && roundOne) {
				playerList[MainController.currentPlayer - 1]
						.setPoints(1 + MainController.tries + playerList[MainController.currentPlayer - 1].getPoints());
				playerList[MainController.currentPlayer - 1].setCredits(
						10 + MainController.tries * 10 + playerList[MainController.currentPlayer - 1].getCredits());
				MainController.lastPoints = 1;
				MainController.lastCredits = 10;
				if (MainController.currentPlayer == playerOrder[0]) {
					pointsGameLeader += 1;
					creditsGameLeader += 10;

				}
				pased = true;
			} else if (miniGameRound == 2 && roundTwo) {
				playerList[MainController.currentPlayer - 1]
						.setPoints(3 + MainController.tries + playerList[MainController.currentPlayer - 1].getPoints());
				playerList[MainController.currentPlayer - 1].setCredits(
						30 + MainController.tries * 10 + playerList[MainController.currentPlayer - 1].getCredits());
				MainController.lastPoints = 3;
				MainController.lastCredits = 30;
				if (MainController.currentPlayer == playerOrder[0]) {
					pointsGameLeader += 3;
					creditsGameLeader += 30;

				}
				pased = true;
			} else if (miniGameRound == 3 && roundThree) {
				playerList[MainController.currentPlayer - 1]
						.setPoints(6 + MainController.tries + playerList[MainController.currentPlayer - 1].getPoints());
				playerList[MainController.currentPlayer - 1].setCredits(
						60 + MainController.tries * 10 + playerList[MainController.currentPlayer - 1].getCredits());
				MainController.lastPoints = 6;
				MainController.lastCredits = 60;
				if (MainController.currentPlayer == playerOrder[0]) {
					pointsGameLeader += 6;
					creditsGameLeader += 60;

				}
				pased = true;
			} else if (miniGameRound == 4 && roundFour) {
				playerList[MainController.currentPlayer - 1]
						.setPoints(8 + MainController.tries + playerList[MainController.currentPlayer - 1].getPoints());
				playerList[MainController.currentPlayer - 1].setCredits(
						80 + MainController.tries * 10 + playerList[MainController.currentPlayer - 1].getCredits());
				MainController.lastPoints = 8;
				MainController.lastCredits = 80;
				if (MainController.currentPlayer == playerOrder[0]) {
					pointsGameLeader += 8;
					creditsGameLeader += 80;

				}
				pased = true;
			} else if (miniGameRound >= 5 && roundFive) {
				playerList[MainController.currentPlayer - 1].setPoints(
						15 + MainController.tries + playerList[MainController.currentPlayer - 1].getPoints());
				playerList[MainController.currentPlayer - 1].setCredits(
						200 + MainController.tries * 10 + playerList[MainController.currentPlayer - 1].getCredits());
				MainController.lastPoints = 15;
				MainController.lastCredits = 200;

				if (MainController.currentPlayer == playerOrder[0]) {
					pointsGameLeader += 15;
					creditsGameLeader += 200;

				}
				pased = true;
			}
			if (pased) {
				chooseNextPlayer(pointsHolder, creditsHolder);
			}
			// Verloren
			else {
				if (MainController.tries != 0) {

					new AlertBox().confirm("Warnung","Du hast noch Versuche übrig und bist dabei zu verlieren. Fortfahren?");
					if (AlertBox.confirm) {

						MainController.tries = 0;
					}
				}

				if (MainController.tries == 0) {
					int pointsLeaderBefore = playerList[0].getPoints();
					int creditsLeaderBefore = playerList[0].getCredits();
					if (MainController.currentPlayer != playerOrder[0]) {

						MainController.lastPoints = 0;
						MainController.lastCredits = 0;
						for (int i = MainController.currentPlayer - 1; i < playerList.length; i++) {
							if (miniGameRound == 1) {
								playerList[0].setPoints(1 + playerList[0].getPoints());
								playerList[0].setCredits(10 + playerList[0].getCredits());
								MainController.lastPoints += 1;
								MainController.lastCredits += 10;
							} else if (miniGameRound == 2) {
								playerList[0].setPoints(3 + playerList[0].getPoints());
								playerList[0].setCredits(30 + playerList[0].getCredits());
								MainController.lastPoints += 3;
								MainController.lastCredits += 30;
							} else if (miniGameRound == 3) {
								playerList[0].setPoints(6 + playerList[0].getPoints());
								playerList[0].setCredits(60 + playerList[0].getCredits());
								MainController.lastPoints += 6;
								MainController.lastCredits += 60;
							} else if (miniGameRound == 4) {
								playerList[0].setPoints(8 + playerList[0].getPoints());
								playerList[0].setCredits(80 + playerList[0].getCredits());
								MainController.lastPoints += 8;
								MainController.lastCredits += 80;
							} else if (miniGameRound >= 5) {
								playerList[0].setPoints(15 + playerList[0].getPoints());
								playerList[0].setCredits(200 + playerList[0].getCredits());
								MainController.lastPoints += 15;
								MainController.lastCredits += 200;
							}
						}
						new AlertBox().information("Spiel beendet","Du hast es nicht geschafft, das Spiel dadurch beendet und dem Game-Leader damit "
								+ (playerList[0].getPoints() - pointsLeaderBefore) + " zusätzliche Punkte und "
								+ (playerList[0].getCredits() - creditsLeaderBefore) + " Credits eingebracht.");
						attributeLabels[playerOrder[0]][1]
								.setText(String.valueOf(playerList[playerOrder[0]].getPoints()));
						attributeLabels[playerOrder[0]][2]
								.setText(String.valueOf(playerList[playerOrder[0]].getCredits()));

					} else {

						new AlertBox().information("Der Game Leader hat das Spiel beendet","Game Leader hat verloren!!" + "Jeder Spieler bekommt nun "
								+ pointsGameLeader + " Punkte " + creditsGameLeader
								+ " Credits, die der Game Leader erspielt hat, gutgeschrieben.");

						for (int i = 0; i < playerList.length; i++) {
							if (playerList[i].getRank() == 1) {
								for (int j = 0; j < playerList.length; j++) {
									if (j != i) {
										playerList[j].setPoints(playerList[j].getPoints() + pointsGameLeader);
										playerList[j].setCredits(playerList[j].getCredits() + creditsGameLeader);
									}
								}
								i = playerList.length;
							}
						}

					}
					new EndMiniGame().endMiniGame(playerList,buttonList,checkBoxList,checkBoxValues,cubeList,labelList,attributeLabels);
				}
			}

		} else {

			new AlertBox().information("Achtung","Die Kombination der gehaltenen Würfel ist so nicht möglich, bitte überprüfen!");
		}

	}

	public void rollDices() {
		// Connect the mainGame.CheckBoxes with the checkBoxelabels to generate a
		// checkboxlist.
		if (MainController.tries == miniGameRound) {
			for (CheckBox check : checkBoxList) {
				check.setSelected(false);
			}

		}
		for (int i = 0; i < checkBoxList.length; i++) {
			checkBoxValues[i] = checkBoxList[i].isSelected();
		}

		mainGameMethods.generateDices(checkBoxValues);

		labelList[MainController.labelListEnum.triesLabel.ordinal()]
				.setText(String.valueOf("Versuche: " + MainController.tries));

		// To show the dices on the UI.
		for (int i = 0; i < cubeList.length; i++) {
			cubeList[i].setText(String.valueOf(MainGameMethods.currentDices[i]));

		}

		if (MainController.tries == 0) {
			buttonList[MainController.buttonListEnum.rollADice.ordinal()].setDisable(true);
		}

		buttonList[MainController.buttonListEnum.nextPlayerButton.ordinal()].setDisable(false);

	}

	private void chooseNextPlayer(int pointsHolder, int creditsHolder) {
		setAttributeLabels();

		new AlertBox().information("Geschafft","Du hast bestanden und bekommst "
				+ (playerList[MainController.currentPlayer - 1].getPoints() - pointsHolder) + " Punkte und "
				+ (playerList[MainController.currentPlayer - 1].getCredits() - creditsHolder) + " Credits.\n"
				+ playerList[MainController.nextPlayer - 1].getName() + " ist an der Reihe.");
		MainController.currentPlayer = MainController.nextPlayer;
		playerCounter++;

		if (playerCounter == playerOrder.length) {
			playerCounter = 0;
			MainController.nextPlayer = playerOrder[playerCounter];
		} else {
			MainController.nextPlayer = playerOrder[playerCounter];
		}

		// To reset the game when one player has finished his turn.

		if (roundCounter == playerList.length) {
			miniGameRound++;
			roundCounter = 0;
			if (miniGameRound == 5) {
				endRoundCounter = 0;
			} else {
				endRoundCounter += 2;
			}
			if (miniGameRound >= 5) {

				new AlertBox().information("Neue Runde","Runde " + miniGameRound
						+ " beginnt. Ihr befindet euch nun in der Endrunde. Eure Versuche verringern sich nun immer um 1. Nun habt ihr "
						+ (miniGameRound - endRoundCounter) + " Versuche und müsst auf 6 Würfel achten!");
			} else {

				new AlertBox().information("Neue Runde","Runde " + miniGameRound + " beginnt. Nun habt ihr " + miniGameRound
						+ " Versuche und müsst auf " + (miniGameRound + 1) + " Würfel achten!");
			}
		}
		if (miniGameRound > 5) {
			MainController.tries = miniGameRound - endRoundCounter;
		} else {
			MainController.tries = miniGameRound;
		}

		roundCounter++;
		labelList[MainController.labelListEnum.roundsLabel.ordinal()]
				.setText("Mini Game Round: " + String.valueOf(miniGameRound));
		labelList[MainController.labelListEnum.triesLabel.ordinal()]
				.setText("Tries: " + String.valueOf(MainController.tries));

		labelList[MainController.labelListEnum.lastRoll.ordinal()].setText("Last Roll: " + cubeList[0].getText() + " | "
				+ cubeList[1].getText() + " | " + cubeList[2].getText() + " | " + cubeList[3].getText() + " | "
				+ cubeList[4].getText() + " | " + cubeList[5].getText());

		labelList[MainController.labelListEnum.currentPlayerLabel.ordinal()]
				.setText(playerList[MainController.currentPlayer - 1].getName() + "'s turn. Good luck!");

		for (CheckBox check : checkBoxList) {
			check.setSelected(false);
		}

		for (Label cube : cubeList) {
			cube.setText("-");

		}

		buttonList[MainController.buttonListEnum.rollADice.ordinal()].setDisable(false);
		buttonList[MainController.buttonListEnum.nextPlayerButton.ordinal()].setDisable(true);
	}

	private void setAttributeLabels() {
		new SortPlayerList().sortPlayerList(playerList);
		new SetAttributeLabels().setAttributeLabels(playerList,attributeLabels);
	}

}
