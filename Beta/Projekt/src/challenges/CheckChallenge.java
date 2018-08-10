package challenges;

import main.alertBox.AlertBox;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import main.Input;
import main.MainGameMethods;
import main.Player;
import userInterface.MainController;

public class CheckChallenge extends Punishments {
	private int firstDice, secondDice, thirdDice;

	public CheckChallenge(Player[] playerList, Label[][] attributeLabels, Label[] cubeList, Label[] labelList,
			CheckBox[] checkBoxList, Button[] buttonList) {
		super(playerList, attributeLabels, cubeList, labelList, checkBoxList, buttonList);
		// TODO Auto-generated constructor stub
	}

	// Methode für jede Challenge schreiben

	public void checkCombination() {
		challengePased = false;

		if (ChallengeMainMethods.selectedCombination == 0) {
			combinationZero();
		} else if (ChallengeMainMethods.selectedCombination == 1) {
			combinationOne();

		} else if (ChallengeMainMethods.selectedCombination == 2) {
			combinationTwo();

		} else if (ChallengeMainMethods.selectedCombination == 3) {
			combinationThree();

		} else if (ChallengeMainMethods.selectedCombination == 4) {
			combinationFour();

		} else if (ChallengeMainMethods.selectedCombination == 5) {
			combinationFive();

		} else if (ChallengeMainMethods.selectedCombination == 6) {
			combinationSix();

		} else if (ChallengeMainMethods.selectedCombination == 7) {
			combinationSeven();

		} else if (ChallengeMainMethods.selectedCombination == 8) {
			combinationEight();

		}
	}

	private void combinationZero() {
		for (int j = 0; j < MainGameMethods.currentDices.length; j++) {
			if (MainGameMethods.currentDices[j] == 1 && j != 5) {
				if (MainGameMethods.currentDices[j + 1] == 1) {

					MainController.holdCurrentPlayer.setPoints(MainController.holdCurrentPlayer.getPoints() + 2);
					MainController.holdCurrentPlayer.setCredits(MainController.holdCurrentPlayer.getCredits() + 30);

					MainController.lastPoints = 2;
					MainController.lastCredits = 30;

					ranks.generateRanks(MainController.playerInOrder);



					new AlertBox().information("Bestanden","Du hast die Challenge bestanden und erhälst deine Belohnung. Du bist auf dem "
							+ MainController.holdCurrentPlayer.getRank() + "ten Rang!" + "\n"
							+ MainController.playerInOrder[MainController.nextPlayer - 1].getName() + " ist dran.");

					challengePased = true;
					break;
				}
			}
		}
		if (!challengePased) {

			if (Integer.parseInt(
					attributeLabels[MainController.playerInOrder[MainController.currentPlayer - 1].getRank() - 1][7]
							.getText()) != 0) {

				new AlertBox().confirm("Verloren","Du hast verloren, willst du deinen Skill einsetzten?");
				if (AlertBox.confirm) {
					super.punishments(0, true);
				} else {
					super.punishments(0, false);
				}
			} else {
				super.punishments(0, false);
			}
		}
		super.chooseNextPlayer();
	}

	private void combinationOne() {
		for (int j = 0; j < MainGameMethods.currentDices.length; j++) {
			if (MainGameMethods.currentDices[j] == 1 && j != 4) {
				if (MainGameMethods.currentDices[j + 1] == 1) {
					if (MainGameMethods.currentDices[j + 2] == 1) {

						MainController.holdCurrentPlayer.setPoints(MainController.holdCurrentPlayer.getPoints() + 6);
						MainController.holdCurrentPlayer.setCredits(MainController.holdCurrentPlayer.getCredits() + 80);

						MainController.lastPoints = 6;
						MainController.lastCredits = 80;

						ranks.generateRanks(MainController.playerInOrder);

						System.out
								.println("Du hast die Challenge bestanden und erhälst deine Belohnung. Du bist auf dem "
										+ MainController.holdCurrentPlayer.getRank() + "ten Rang!" + "\n"
										+ MainController.playerInOrder[MainController.nextPlayer - 1].getName()
										+ " ist dran.");

						challengePased = true;
						break;
					}
				}
			}
		}
		if (!challengePased) {

			if (Integer.parseInt(
					attributeLabels[MainController.playerInOrder[MainController.currentPlayer - 1].getRank() - 1][7]
							.getText()) != 0) {
				new AlertBox().confirm("Verloren","Du hast verloren, willst du deinen Skill einsetzten?");
				if (AlertBox.confirm) {
					super.punishments(1, true);
				} else {
					super.punishments(1, false);
				}
			} else {
				super.punishments(1, false);
			}
		}

		super.chooseNextPlayer();
	}

	private void combinationTwo() {
		for (int i = 0; i < MainGameMethods.currentDices.length; i++) {
			if (MainGameMethods.currentDices[i] == 1 && i != 5) {
				i = MainGameMethods.currentDices.length;
				for (int j = 0; j < MainGameMethods.currentDices.length; j++) {
					if (MainGameMethods.currentDices[j] == 6) {

						MainController.holdCurrentPlayer
								.setCredits(MainController.holdCurrentPlayer.getCredits() + 100);

						int hold = 0;
						for (int k = 0; k < MainController.playerInOrder.length; k++) {
							if (MainController.playerInOrder[k].getRank() == 1) {
								hold = k;
								break;
							}
						}
						MainController.holdCurrentPlayer.setPoints(MainController.playerInOrder[hold].getPoints() + 1);

						MainController.lastPoints = MainController.playerInOrder[hold].getPoints() + 1;
						MainController.lastCredits = 100;

						ranks.generateRanks(playerList);

						new AlertBox().information("Bestanden","Du hast die Challenge bestanden und bekommst deine Belohung. Wie versprochen bist du nun auf dem 1ten Rang.\n"
								+ MainController.playerInOrder[MainController.nextPlayer - 1].getName()
								+ " ist dran.");
						challengePased = true;
						break;
					}
				}
			}
		}
		if (!challengePased) {

			playerList[0].setCredits(playerList[0].getCredits() + 100);

			new AlertBox().information("Nicht bestanden","Du hast die Challenge nicht bestanden. " + playerList[0].getName() + " bekommt nun 100 Credits.\n"
					+ MainController.playerInOrder[MainController.nextPlayer - 1].getName() + " ist dran.");
		}
		super.chooseNextPlayer();
	}

	private void combinationThree() {
		for (int i = 0; i < MainGameMethods.currentDices.length - 2; i++) {
			for (int j = i + 1; j < MainGameMethods.currentDices.length - 1; j++) {
				if (MainGameMethods.currentDices[i] + 1 == MainGameMethods.currentDices[j]) {
					for (int k = j + 1; k < MainGameMethods.currentDices.length; k++) {
						if (MainGameMethods.currentDices[j] + 1 == MainGameMethods.currentDices[k]) {
							MainController.holdCurrentPlayer.setPoints(
									MainController.holdCurrentPlayer.getPoints() + MainGameMethods.currentDices[i]
											+ MainGameMethods.currentDices[j] + MainGameMethods.currentDices[k]);

							MainController.holdCurrentPlayer.setCredits(
									MainController.holdCurrentPlayer.getCredits() + (MainGameMethods.currentDices[i]
											+ MainGameMethods.currentDices[j] + MainGameMethods.currentDices[k]) * 10);

							MainController.lastPoints = MainGameMethods.currentDices[i]
									+ MainGameMethods.currentDices[j] + MainGameMethods.currentDices[k];
							MainController.lastCredits = (MainGameMethods.currentDices[i]
									+ MainGameMethods.currentDices[j] + MainGameMethods.currentDices[k]) * 10;

							ranks.generateRanks(MainController.playerInOrder);


							new AlertBox().information("Bestanden","Du hast es geschaffst und bekommst deine Belohung über "
									+ (MainGameMethods.currentDices[i]
									+ MainGameMethods.currentDices[j] + MainGameMethods.currentDices[k])
									+ " Punkte und "
									+ (MainGameMethods.currentDices[i] + MainGameMethods.currentDices[j]
									+ MainGameMethods.currentDices[k]) * 10
									+ " Credits. Du bist nun auf dem " + MainController.holdCurrentPlayer.getRank()
									+ "ten Rang.\n"
									+ MainController.playerInOrder[MainController.nextPlayer - 1].getName()
									+ " ist dran.");

							i = MainGameMethods.currentDices.length;
							j = MainGameMethods.currentDices.length;

							challengePased = true;

							break;
						}
					}

				}
			}

		}

		if (!challengePased) {

			if (Integer.parseInt(
					attributeLabels[MainController.playerInOrder[MainController.currentPlayer - 1].getRank() - 1][7]
							.getText()) != 0) {
				new AlertBox().confirm("Verloren","Du hast verloren, willst du deinen Skill einsetzten?");
				if (AlertBox.confirm) {
					super.punishments(3, true);

				} else {
					super.punishments(3, false);
				}
			} else {

				super.punishments(3, false);
			}
		}
		super.chooseNextPlayer();
	}

	private void combinationFour() {
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

		for (int i = 0; i < MainGameMethods.currentDices.length - 3; i++) {
			for (int j = i + 1; j < MainGameMethods.currentDices.length - 2; j++) {
				if (MainGameMethods.currentDices[i] + 1 == MainGameMethods.currentDices[j]) {
					for (int k = j + 1; k < MainGameMethods.currentDices.length - 1; k++) {
						if (MainGameMethods.currentDices[j] + 1 == MainGameMethods.currentDices[k]) {
							for (int l = k + 1; l < MainGameMethods.currentDices.length; l++) {
								if (MainGameMethods.currentDices[k] + 1 == MainGameMethods.currentDices[l]) {

									MainController.holdCurrentPlayer
											.setCredits(MainController.holdCurrentPlayer.getCredits()
													+ (MainGameMethods.currentDices[j] + MainGameMethods.currentDices[k]
															+ MainGameMethods.currentDices[l]) * 20);

									MainController.lastPoints = 0;
									MainController.lastCredits = (MainGameMethods.currentDices[j]
											+ MainGameMethods.currentDices[k] + MainGameMethods.currentDices[l]) * 20;

									ranks.generateRanks(MainController.playerInOrder);


									new AlertBox().information("Bestanden","Du hast es geschaffst und bekommst deine Belohung über "
											+ (MainGameMethods.currentDices[j] + MainGameMethods.currentDices[k]
											+ MainGameMethods.currentDices[l]) * 20
											+ " Credits.\n"
											+ MainController.playerInOrder[MainController.nextPlayer - 1].getName()
											+ " ist dran.");
									i = MainGameMethods.currentDices.length;
									j = MainGameMethods.currentDices.length;
									k = MainGameMethods.currentDices.length;

									challengePased = true;

									break;
								}
							}
						}
					}
				}
			}
		}
		if (!challengePased) {

			if (Integer.parseInt(
					attributeLabels[MainController.playerInOrder[MainController.currentPlayer - 1].getRank() - 1][7]
							.getText()) != 0) {
				new AlertBox().confirm("Verloren","Du hast verloren, willst du deinen Skill einsetzten?");
				if (AlertBox.confirm) {
					super.punishments(4, true);

				} else {
					super.punishments(4, false);
				}
			} else {
				super.punishments(4, false);
			}

		}
		super.chooseNextPlayer();

	}

	private void combinationFive() {

		int[] dices = new int[3];
		if (!firstPartPased) {
			for (int i = 0; i < checkBoxList.length - 2; i++) {
				if (checkBoxList[i].isSelected()) {
					for (int j = i + 1; j < checkBoxList.length - 1; j++) {
						if (checkBoxList[j].isSelected()) {
							for (int k = j + 1; k < checkBoxList.length; k++) {
								if (checkBoxList[k].isSelected()) {

									dices[0] = MainGameMethods.currentDices[i];
									dices[1] = MainGameMethods.currentDices[j];
									dices[2] = MainGameMethods.currentDices[k];

									int hold;
									for (int m = 0; m < dices.length; m++) {
										for (int n = dices.length - 1; n > 0; n--) {
											if (dices[n - 1] > dices[n]) {
												hold = dices[n];
												dices[n] = dices[n - 1];
												dices[n - 1] = hold;
											}
										}
									}

									if ((dices[0] == dices[1] && dices[1] == dices[2])
											|| (dices[0] + 1 == dices[1] && dices[1] + 1 == dices[2])) {

										firstDice = dices[0];
										secondDice = dices[1];
										thirdDice = dices[2];


										new AlertBox().information("Erster Teil Bestanden","Du hast den ersten Teil bestanden und solltest nun mit deinem nächsten Wurf keine "
												+ firstDice + ", " + secondDice + " und " + thirdDice
												+ " würfeln.");
										i = checkBoxList.length;
										j = checkBoxList.length;

										MainController.tries = 1;

										labelList[3].setText("Keine " + firstDice + ", " + secondDice + " und "
												+ thirdDice + " würfeln.");
										labelList[0].setText("Tries: " + String.valueOf(MainController.tries));
										for (int n = 0; n < cubeList.length; n++) {
											cubeList[n].setText("-");
											checkBoxList[n].setSelected(false);
										}

										firstPartPased = true;
										break;
									}
								}
							}
						}
					}
				}
			}
			if (!firstPartPased) {

				// int hold = 0;
				// for (int k = 0; k < MainController.playerInOrder.length; k++) {
				// if (MainController.playerInOrder[k].getRank() == 1) {
				// hold = k;
				// break;
				// }
				// }

				playerList[MainController.playerInOrder.length - 1].setPoints(playerList[0].getPoints() + 1);
				playerList[MainController.playerInOrder.length - 1]
						.setCredits(playerList[MainController.playerInOrder.length - 1].getCredits() + 300);

				MainController.lastPoints = playerList[0].getPoints() + 1;
				MainController.lastCredits = 300;

				ranks.generateRanks(MainController.playerInOrder);


				new AlertBox().information("Nicht bestanden","Du hast den ersten Teil der Challenge nicht geschafft und damit dem letzten Spieler einen Gefallen getan. "
						+ "Glückwunsch!" + "\n"
						+ MainController.playerInOrder[MainController.nextPlayer - 1].getName() + " ist dran.");
				super.chooseNextPlayer();
			}
		} else {
			int hold;
			for (int i = 0; i < MainGameMethods.currentDices.length; i++) {
				if (MainGameMethods.currentDices[i] == firstDice) {
					hold = i;
					i = MainGameMethods.currentDices.length;
					for (int j = 0; j < MainGameMethods.currentDices.length; j++) {
						if (MainGameMethods.currentDices[j] == secondDice && j != hold) {
							hold = j;
							j = MainGameMethods.currentDices.length;
							for (int k = 0; k < MainGameMethods.currentDices.length; k++) {
								if (MainGameMethods.currentDices[k] == thirdDice && k != hold) {

									if (Integer.parseInt(
											attributeLabels[MainController.playerInOrder[MainController.currentPlayer
													- 1].getRank() - 1][7].getText()) != 0) {
										new AlertBox().confirm("\"Bestanden\"","Du hast \"Bestanden\", willst du deinen Skill einsetzten?");
										if (AlertBox.confirm) {

											super.punishments(5, true);
										} else {
											super.punishments(5, false);
										}

									} else {
										super.punishments(5, false);
									}

									challengePased = true;
									super.chooseNextPlayer();
									break;

								}
							}
						}
					}
				}
			}

			if (!challengePased && firstPartPased) {
				// int hold = 0;
				// for (int i = 0; i < MainController.playerInOrder.length; i++) {
				// if (MainController.playerInOrder[i].getRank() == 1) {
				// hold = i;
				// break;
				// }
				// }
				MainController.holdCurrentPlayer.setPoints(playerList[0].getPoints() + 1);
				MainController.holdCurrentPlayer.setCredits(MainController.holdCurrentPlayer.getCredits() + 200);

				MainController.lastPoints = playerList[0].getPoints() + 1;
				MainController.lastCredits = 200;

				ranks.generateRanks(playerList);


				new AlertBox().information("Bestanden durch nicht bestehen","Glückwunsch, du hast deine Challenge nicht bestanden, bist nun Game Leader und bekommst 200 Credits. "
						+ "\n" + MainController.playerInOrder[MainController.nextPlayer - 1].getName()
						+ " ist dran.");
				challengePased = true;
				super.chooseNextPlayer();
			}
		}
	}

	private void combinationSix() {
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
		int sum = 0;
		if (MainGameMethods.currentDices[0] == MainGameMethods.currentDices[1]) {
			if (MainGameMethods.currentDices[1] + 1 == MainGameMethods.currentDices[2]) {
				if (MainGameMethods.currentDices[2] == MainGameMethods.currentDices[3]) {
					if (MainGameMethods.currentDices[3] + 1 == MainGameMethods.currentDices[4]) {
						if (MainGameMethods.currentDices[4] == MainGameMethods.currentDices[5]) {

							for (int i = 0; i < MainGameMethods.currentDices.length; i++) {
								sum += MainGameMethods.currentDices[i];
							}

							if (MainController.tries == 0) {
								MainController.tries = 1;
							}
							MainController.holdCurrentPlayer
									.setPoints(MainController.holdCurrentPlayer.getPoints() + 5);
							MainController.holdCurrentPlayer.setCredits(
									MainController.holdCurrentPlayer.getCredits() + (4 * sum * MainController.tries));

							MainController.lastPoints = 5;
							MainController.lastCredits = (4 * sum * MainController.tries);

							ranks.generateRanks(MainController.playerInOrder);


							new AlertBox().information("Bestanden","Du hast die Challenge bestanden und bekommst 5 Punkte und "
									+ (4 * sum * MainController.tries) + " Credits.\n" + "Du bist auf dem "
									+ MainController.holdCurrentPlayer.getRank() + "ten Rang." + "\n"
									+ MainController.playerInOrder[MainController.nextPlayer - 1].getName()
									+ " ist dran.");
							super.chooseNextPlayer();
							challengePased = true;
						}
					}
				}
			}
		}
		if (!challengePased) {

			if (Integer.parseInt(
					attributeLabels[MainController.playerInOrder[MainController.currentPlayer - 1].getRank() - 1][7]
							.getText()) != 0) {
				new AlertBox().confirm("Verloren","Du hast verloren, willst du deinen Skill einsetzten?");
				if (AlertBox.confirm) {

					super.punishments(6, true);
				} else {
					super.punishments(6, false);
				}
			} else {
				super.punishments(6, false);
			}

			//

			super.chooseNextPlayer();
		}
	}

	private void combinationSeven() {
		if (!firstPartPased) {
			if (MainGameMethods.currentDices[0] == MainGameMethods.currentDices[1]
					&& MainGameMethods.currentDices[0] == MainGameMethods.currentDices[2]
					&& MainGameMethods.currentDices[0] == MainGameMethods.currentDices[3]
					&& MainGameMethods.currentDices[0] == MainGameMethods.currentDices[4]
					&& MainGameMethods.currentDices[0] == MainGameMethods.currentDices[5]) {
				firstPartPased = true;
				firstDice = MainGameMethods.currentDices[0];


				new AlertBox().information("Erster Teil bestanden","Der erste Teil wäre geschafft, jetzt bitte noch mind. 3 mal die " + firstDice + " würfeln!");
				labelList[3].setText("Mind. 3-mal die " + firstDice);

				for (int n = 0; n < cubeList.length; n++) {
					cubeList[n].setText("-");
					checkBoxList[n].setSelected(false);

				}

				MainController.tries = 2;
				labelList[0].setText(String.valueOf("Tries: " + MainController.tries));
			} else {
				int randomDiceNumber = (int) (Math.random() * 6 + 1);
				if (Integer.parseInt(
						attributeLabels[MainController.playerInOrder[MainController.currentPlayer - 1].getRank() - 1][7]
								.getText()) != 0) {

					if (Input.stringInput().equals("j")) {

						MainController.holdCurrentPlayer.setCredits(MainController.holdCurrentPlayer.getCredits()
								+ (MainGameMethods.currentDices[randomDiceNumber] * 100));

						MainController.lastPoints = 0;
						MainController.lastCredits = MainGameMethods.currentDices[randomDiceNumber] * 100;


						new AlertBox().information("Skill eingesetzt","Dank deinen Skill hast du nun "
								+ (MainGameMethods.currentDices[randomDiceNumber] * 100) + " Credits bekommen.\n"
								+ MainController.holdCurrentPlayer.getRank() + "ten Rang!" + "\n"
								+ MainController.playerInOrder[MainController.nextPlayer - 1].getName() + " ist dran.");
					} else {

						MainController.holdCurrentPlayer.setCredits(MainController.holdCurrentPlayer.getCredits()
								- (MainGameMethods.currentDices[randomDiceNumber] * 100));


						new AlertBox().information("Nicht bestanden","Du hast die Challenge nicht bestanden. "
								+ "\nDu verlierst nun das 100-fache eines zufälligen von dir gewürfelten Würfels, die "
								+ MainGameMethods.currentDices[randomDiceNumber] + ", an Credtis." + "\n"
								+ MainController.playerInOrder[MainController.nextPlayer - 1].getName() + " ist dran.");
					}
				} else {

					MainController.holdCurrentPlayer.setCredits(MainController.holdCurrentPlayer.getCredits()
							- (MainGameMethods.currentDices[randomDiceNumber] * 100));


					new AlertBox().information("Nicht bestanden","Du hast die Challenge nicht bestanden. "
							+ "\nDu verlierst nun das 100-fache eines zufälligen von dir gewürfelten Würfels, die "
							+ MainGameMethods.currentDices[randomDiceNumber] + ", an Credtis." + "\n"
							+ MainController.playerInOrder[MainController.nextPlayer - 1].getName() + " ist dran.");
				}

				super.chooseNextPlayer();
			}

		} // Second Part
		else {
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
			int counter = 0;
			for (int i = 0; i < MainGameMethods.currentDices.length; i++) {
				if (MainGameMethods.currentDices[i] == firstDice) {
					counter++;
				}

			}
			if (counter == 0) {

				if (Integer.parseInt(
						attributeLabels[MainController.playerInOrder[MainController.currentPlayer - 1].getRank() - 1][7]
								.getText()) != 0) {

					if (Input.stringInput().equals("j")) {


						new AlertBox().information("Skill eingesetzt","Dank deinen Skill hast du nun hast du nun evtl. immer noch mehr als 2 Punkte und 5 Credits.\n"
								+ MainController.holdCurrentPlayer.getRank() + "ten Rang!" + "\n"
								+ MainController.playerInOrder[MainController.nextPlayer - 1].getName()
								+ " ist dran.");
					} else {
						MainController.holdCurrentPlayer.setPoints(2);
						MainController.holdCurrentPlayer.setCredits(5);
						ranks.generateRanks(MainController.playerInOrder);


						new AlertBox().information("Nicht bestanden","Du hast die " + firstDice + " " + counter
								+ "-mal gewürfelt. Das nenne ich mal Pech."
								+ "\nDu hast jetzt nur noch 2 Punkte und 5 Credits, einfach so, weil es witzig ist. Hattest du vorher weniger, Glückwunsch."
								+ "\nDu bist auf dem " + MainController.holdCurrentPlayer.getRank() + "ten Rang." + "\n"
								+ MainController.playerInOrder[MainController.nextPlayer - 1].getName() + " ist dran.");
					}
				} else {
					MainController.holdCurrentPlayer.setPoints(2);
					MainController.holdCurrentPlayer.setCredits(5);
					ranks.generateRanks(MainController.playerInOrder);


					new AlertBox().information("Nicht bestanden","Du hast die " + firstDice + " " + counter
							+ "-mal gewürfelt. Das nenne ich mal Pech."
							+ "\nDu hast jetzt nur noch 2 Punkte und 5 Credits, einfach so, weil es witzig ist. Hattest du vorher weniger, Glückwunsch."
							+ "\nDu bist auf dem " + MainController.holdCurrentPlayer.getRank() + "ten Rang." + "\n"
							+ MainController.playerInOrder[MainController.nextPlayer - 1].getName() + " ist dran.");
				}

				super.chooseNextPlayer();

			} else if (counter < 3) {

				if (Integer.parseInt(
						attributeLabels[MainController.playerInOrder[MainController.currentPlayer - 1].getRank() - 1][7]
								.getText()) != 0) {

					if (Input.stringInput().equals("j")) {

						super.punishments(7, true);
					} else {
						super.punishments(7, false);
					}
				} else {
					super.punishments(7, false);
				}

				super.chooseNextPlayer();

			} else if (counter == 3) {
				MainController.holdCurrentPlayer.setCredits(MainController.holdCurrentPlayer.getCredits() + 400);

				MainController.lastPoints = 0;
				MainController.lastCredits = 400;


				new AlertBox().information("Bestanden","Du hast die " + firstDice + " 3-mal gewürfelt. Du bekommst 400 Credits." + "\n"
						+ MainController.playerInOrder[MainController.nextPlayer - 1].getName() + " ist dran.");

				super.chooseNextPlayer();
			} else if (counter == 4) {
				MainController.holdCurrentPlayer.setCredits(MainController.holdCurrentPlayer.getCredits() + 400);
				MainController.holdCurrentPlayer.setPoints(MainController.holdCurrentPlayer.getPoints() + 10);

				MainController.lastPoints = 10;
				MainController.lastCredits = 400;

				ranks.generateRanks(playerList);


				new AlertBox().information("Bestanden","Du hast die " + firstDice
						+ " 4-mal gewürfelt. Du bekommst 400 Credits und 10 Punkte.\nDu bist auf dem "
						+ MainController.holdCurrentPlayer.getRank() + "ten Rang.\n"
						+ MainController.playerInOrder[MainController.nextPlayer - 1].getName() + " ist dran.");
				super.chooseNextPlayer();

			} else {
				int newFirstDice = firstDice;

				if (firstDice < 4) {
					newFirstDice = 4;
				}

				MainController.holdCurrentPlayer.setCredits(
						MainController.holdCurrentPlayer.getCredits() + (newFirstDice * 6) * (newFirstDice * 6));
				MainController.holdCurrentPlayer
						.setPoints(MainController.holdCurrentPlayer.getPoints() + (newFirstDice * counter));

				MainController.lastPoints = (newFirstDice * counter);
				MainController.lastCredits = (newFirstDice * 6) * (newFirstDice * 6);

				ranks.generateRanks(playerList);


				new AlertBox().information("Bestanden","Glückwunsch! Du hast die " + newFirstDice + " " + counter + "-mal gewürfelt."
						+ "\nDu bekommst " + (newFirstDice * counter) + " Punkte und "
						+ (newFirstDice * 6) * (newFirstDice * 6) + " Credits." + "\n" + "\nDu bist auf dem "
						+ MainController.holdCurrentPlayer.getRank() + "ten Rang.\n"
						+ MainController.playerInOrder[MainController.nextPlayer - 1].getName() + " ist dran.");
				super.chooseNextPlayer();

			}
		}
	}

	private void combinationEight() {
		int holdDice;
		for (int i = 0; i < MainGameMethods.currentDices.length; i++) {
			for (int j = MainGameMethods.currentDices.length - 1; j > 0; j--) {
				if (MainGameMethods.currentDices[j - 1] > MainGameMethods.currentDices[j]) {
					holdDice = MainGameMethods.currentDices[j];
					MainGameMethods.currentDices[j] = MainGameMethods.currentDices[j - 1];
					MainGameMethods.currentDices[j - 1] = holdDice;
				}

			}
		}
		for (int i = 0; i < MainGameMethods.currentDices.length;) {
			if (MainGameMethods.currentDices[i] == i + 1) {
				i++;
				if (i == MainGameMethods.currentDices.length - 1) {
					challengePased = true;
					i++;
					;
				}
			} else {
				break;
			}
		}
		if (challengePased) {
			if (MainController.tries == 3) {

				new AlertBox().information("Bestanden","Du hast wirklich nach einem Wurf eine 6er Straße geworfen, lucker! Du bekommst nun 1000 Credits und bist Game Leader."
						+ "\n" + MainController.playerInOrder[MainController.nextPlayer - 1].getName()
						+ " ist dran.");

				MainController.holdCurrentPlayer.setCredits(MainController.holdCurrentPlayer.getCredits() + 1000);

				MainController.holdCurrentPlayer.setPoints(playerList[0].getPoints() + 1);

				MainController.lastPoints = playerList[0].getPoints() + 1;
				MainController.lastCredits = 1000;

				ranks.generateRanks(playerList);

			} else if (MainController.tries == 2) {
				MainController.holdCurrentPlayer.setCredits(MainController.holdCurrentPlayer.getCredits() + 700);

				MainController.holdCurrentPlayer.setPoints(
						MainController.holdCurrentPlayer.getPoints() + playerList[playerList.length - 2].getPoints());

				MainController.lastPoints = playerList[playerList.length - 2].getPoints();
				MainController.lastCredits = 700;

				ranks.generateRanks(playerList);


				new AlertBox().information("Bestanden","Nicht schlecht! Du bekommst deine 700 credits und "
						+ playerList[MainController.playerInOrder.length - 2].getPoints() + " Punkte."
						+ "\nDu bist auf dem " + MainController.holdCurrentPlayer.getRank() + "ten Rang." + "\n"
						+ MainController.playerInOrder[MainController.nextPlayer - 1].getName() + " ist dran.");
			} else {
				MainController.holdCurrentPlayer.setCredits(MainController.holdCurrentPlayer.getCredits() + 500);

				MainController.holdCurrentPlayer.setPoints(MainController.holdCurrentPlayer.getPoints() + 10);

				MainController.lastPoints = 10;
				MainController.lastCredits = 500;

				ranks.generateRanks(playerList);


			}
			new AlertBox().information("Bestanden","Naja, gut gemacht, schätze ich. 500 Credits und 10 Punkte als Trostpreis."
					+ "\nDu bist auf dem " + MainController.holdCurrentPlayer.getRank() + "ten Rang." + "\n"
					+ MainController.playerInOrder[MainController.nextPlayer - 1].getName() + " ist dran.");
		} else {

			if (Integer.parseInt(
					attributeLabels[MainController.playerInOrder[MainController.currentPlayer - 1].getRank() - 1][7]
							.getText()) != 0) {

				new AlertBox().confirm("Verloren","Du hast verloren, willst du deinen Skill einsetzten?");
				if (AlertBox.confirm) {

					super.punishments(8, true);
				} else {
					super.punishments(8, false);
				}
			} else {
				super.punishments(8, false);
			}

		}
		super.chooseNextPlayer();

	}

}
