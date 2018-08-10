package challenges;

import main.alertBox.AlertBox;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import main.Player;
import userInterface.MainController;

public class ChallengeCombinationTasks extends ChallengeMainMethods{
	public ChallengeCombinationTasks(Player[] playerList, Label[][] attributeLabels, Label[] cubeList,
			Label[] labelList, CheckBox[] checkBoxList, Button[] buttonList) {
		super(playerList, attributeLabels, cubeList, labelList, checkBoxList, buttonList);
		// TODO Auto-generated constructor stub
	}

	public boolean rollSpecificCombination() {

		System.out.println();

		new AlertBox().confirm("Challenge", "Deine Aufgabe ist es mit einer vorgegebenen Versuchsanzahl eine bestimmt Kombination zu würfeln."
				+ "\nDu wirst eine Belohnung erhalten, wenn du bestehst. Falls nicht bekommst du eine Strafe."
				+ "\nWenn du zustimmst, wird dir deine Aufgabe präsentiert.");

		if (AlertBox.confirm) {
			super.startChallenge();

			int amountOfChallenges = 9;
			selectedCombination = (int) (Math.random() * amountOfChallenges);

			// Zum Testen
			//selectedCombination = 8;

			if (MainController.currentPlayer == 1 && MainController.currentRound != 1) {
				double selection = Math.random();
				if (selection >= 0.6) {
					selectedCombination = 8;
				} else if (selection >= 0.2) {
					selectedCombination = 7;
				}
			}
			if (selectedCombination == 0) {
				System.out.println();
				new AlertBox().information("Task","Dein nächster Wurf muss 2 Einsen enthalten. Du erhälst dafür 2 Punkte und 30 Credits.");
				firstPartPased = true;
				
				super.prepareChallenge(1, true, false);
			}

			else if (selectedCombination == 1) {
				System.out.println();
				new AlertBox().information("Task","Dein nächster Wurf muss 3 Einsen enthalten. Du erhälst dafür 6 Punkte und 80 Credits.");

				super.prepareChallenge(1, true, false);

			} else if (selectedCombination == 2) {
				System.out.println();
				new AlertBox().information("Task","Dein nächster Wurf muss eine 1 und eine 6 enthalten. Du erhälst dafür 100 Credits und wirst zum Game Leader. Verlierst du, "
						+ "bekommt der Game Leader 100 Credits, bist du jener, Glück gehabt!");
				// for (int i = 0; i < checkBoxList.length; i++) {
				// checkBoxList[i].setDisable(true);
				// }
				super.prepareChallenge(1, true, false);

			} else if (selectedCombination == 3) {
				System.out.println();
				new AlertBox().information("Task","Du musst mit deinem nächsten Wurf eine Straße aus 3 Würfeln vorzeigen können. Deine Belohnung "
						+ "die Summe der gezeigten Würfel sein\n"
						+ "sowie das 10-fache in Credits. Verlierst du, wirst du das 2-fache des höchsten "
						+ "gefallenen Würfels an Punkten verlieren.");
				super.prepareChallenge(1, true, false);

			} else if (selectedCombination == 4) {
				System.out.println();
				new AlertBox().information("Task","Du musst mit deinen nächsten 2 Würfen eine 4er Straße vorzeigen können, deine Belohnung wird das 20-fache der 3 höchsten Würfel in Credits sein."
						+ "\nDeine Strafe die Summe in Punkten.");
				super.prepareChallenge(2, true, false);

			} else if (selectedCombination == 5) {
				System.out.println(
						);
				new AlertBox().information("Task","Du musst mit deinem nächsten Wurf 3 Würfel nach dem Regelwerk auswählen und halten. Hast du das getan, muss du mit deinem nächsten\n"
						+ "Wurf die gleiche Kombination von Würfeln noch einmal würfeln. Schaffst du es nicht 3 Würfel zu finden, steigt der letzte Spieler zum Game Leader auf\n"
						+ "und erhält 300 Credits. Schaffst du es nicht die erste Kombination wieder zu würfeln, steigst du zum Game Leader auf und erhälst 200 Credits.");
				super.prepareChallenge(1, false, false);

				buttonList[2].setText("Next round.");
			} else if (selectedCombination == 6) {
				System.out.println();
				new AlertBox().information("Task","Zeige mir, dass du mit 6 Würfen eine 3er Straße aus Paaren würfeln kannst. "
						+ "Deine Belohung ist das Produkt der verbleibenden Versuche (mind. 1) mal der 4-fachen Summe der Würfel in Credits und 5 Punkte.\n"
						+ "Deine Strafe die Summe aller Würfel mal 5 in Credits.");
				super.prepareChallenge(6, true, false);

			} else if (selectedCombination == 7) {

				System.out.println();
				new AlertBox().confirm("HARD-CHALLENGE","Die Belohnung ist riesig, es gibt sie aber nicht umsonst! Und die Strafe ist hart. Willst du annehmen?");
				if (AlertBox.confirm) {
					System.out.println();
					new AlertBox().information("Task","Du hast nun 5 Versuche um alle Würfel auf den selben Wert zu bringen. "
							+ "Danach bekommst du wieder 2 Versuche, um die selbe Zahl wieder mindestens 3-mal zu würfeln.\n"
							+ "1. 3-mal: Du bekommst 400 Credits.\n"
							+ "2. 4-mal: Du bekommst 10 Punkte und 400 Credits.\n"
							+ "3. 5/6-mal: Du bekommst das Sechsfache deiner Zahl (aber mind. die 4) ins Quadrat und die Summe als Punkte.");
					super.prepareChallenge(5, false, true);

				} else {
					MainController.skipTurn = true;
					double ifPunishment = Math.random();
					if (ifPunishment > 0.3) {
						System.out.println();
						new AlertBox().information("Strafe","200 Credits weniger und außerdem schenkst du dem nächsten Spieler 10 deiner "
								+ "Punkte.");
						MainController.holdCurrentPlayer.setPoints(MainController.holdCurrentPlayer.getPoints() - 10);

						MainController.playerInOrder[MainController.nextPlayer - 1].setPoints(
								MainController.playerInOrder[MainController.nextPlayer - 1].getPoints() + 10);

						MainController.holdCurrentPlayer
								.setCredits(MainController.holdCurrentPlayer.getCredits() - 200);

					} else {
						System.out.println();
						new AlertBox().information("Keine Strafe!","Noch einmal Glück gehabt.");
					}

					ranks.generateRanks(MainController.playerInOrder);
					MainController.rollCounter = 0;
					ChallengeMainMethods.resetCounter = true;
					super.chooseNextPlayer();
				}
			} else if (selectedCombination == 8) {
				System.out.println();
				new AlertBox().confirm("HARD-CHALLENGE","Die Belohnung ist riesig, es gibt sie aber nicht umsonst! Und die Strafe ist hart. Willst du annehmen?");
				if (AlertBox.confirm) {
					System.out.println();
					new AlertBox().information("Task","Du hast nun 4 Würfe Zeit, um eine 6er Straße zu würfel.\n"
							+ "1. Nach dem ersten Wurf: Du bekommst 1000 Credits und bist auf dem ersten Platz.\n"
							+ "2. Nach dem zweiten Wurf: Du bekommst 700 Credits und bekommst sämtliche Punkte des vorletzten Platzes gutgeschrieben, ja auch negative Punkte.\n"
							+ "3. Nach dem dritten oder vierten Wurf: 500 Credits und 10 Punkte, als Trostpreis!");
					super.prepareChallenge(4, true, false);

				} else {
					MainController.skipTurn = true;
					double ifPunishment = Math.random();
					if (ifPunishment < 0.08) {
						System.out.println();
						new AlertBox().information("Strafe","Naja, irgendwie ist das jetzt Pech, du verliest 500 Credits und bist auf dem letzten Platz..");
						MainController.holdCurrentPlayer
								.setCredits(MainController.holdCurrentPlayer.getCredits() - 500);

						MainController.holdCurrentPlayer.setPoints(playerList[playerList.length - 1].getPoints() - 1);

					} else if (ifPunishment > 0.1 && ifPunishment < 0.8) {
						System.out.println();
						new AlertBox().information("Strafe","Hätte schlimmer kommen können, du verlierst 100 Credits und 10 Punkte.");
						MainController.holdCurrentPlayer
								.setCredits(MainController.holdCurrentPlayer.getCredits() - 100);

						MainController.holdCurrentPlayer.setPoints(MainController.holdCurrentPlayer.getPoints() - 10);

					} else {
						System.out.println();
						new AlertBox().information("Keine Strafe","Noch einmal Glück gehabt.");
					}

					ranks.generateRanks(MainController.playerInOrder);
					MainController.rollCounter = 0;
					ChallengeMainMethods.resetCounter = true;
					super.chooseNextPlayer();
				}

			}
			// else if (selectedCombination == 9) {
			// System.out.println("DAS IST EINE HARD-CHALLENGE.\n"
			// + "Die Belohnung ist riesig, es gibt sie aber nicht umsonst! Und die Strafe
			// ist hart. Willst du annehmen?");
			// if (Input.stringInput().equals("j")) {
			// }
			//
			// MainController.tries = 5;
			// labelList[0].setText("Tries: " + String.valueOf(MainController.tries));
			// }
			return true;
		} else {
			MainController.skipTurn = true;

			double ifPunishment = Math.random();
			if (ifPunishment > 0.3) {
				MainController.holdCurrentPlayer.setPoints(MainController.holdCurrentPlayer.getPoints() - 3);
				MainController.holdCurrentPlayer.setCredits(MainController.holdCurrentPlayer.getCredits() - 30);
				MainController.playerInOrder[Player.amountOfPlayers - 1 - 1]
						.setCredits(MainController.playerInOrder[Player.amountOfPlayers - 1 - 1].getCredits() + 30);

				ranks.generateRanks(MainController.playerInOrder);

				System.out.println(	);
				new AlertBox().information("Strafe","Angsthase!! Zur Strafe verlierst du 3 Punkte und musst 30 Credits an den vorletzten Platz zahlen." +
						"\nDu bist nun auf dem "
						+ MainController.holdCurrentPlayer.getRank() + "ten Rang. "
						+ MainController.playerInOrder[MainController.nextPlayer - 1].getName() + " ist dran.");
			} else {
				MainController.playerInOrder[MainController.nextPlayer - 1]
						.setCredits(MainController.playerInOrder[MainController.nextPlayer - 1].getCredits() + 30);

				ranks.generateRanks(MainController.playerInOrder);

				System.out.println();
				new AlertBox().information("Keine Strafe","Da hat der Angsthase aber noch einmal Glück gehabt. " +
						"Du wirst nicht bestraft, gibst deinen Zug aber dennoch ab und schenkst"
						+ " dem nächsten Spieler 30 Credits. "
						+ MainController.playerInOrder[MainController.nextPlayer - 1].getName() + " ist dran.");
			}
			
			MainController.holdLastPlayer = MainController.playerInOrder[MainController.currentPlayer - 1];
			super.chooseNextPlayer();
			MainController.holdCurrentPlayer = MainController.playerInOrder[MainController.currentPlayer -1];
			MainController.holdNextPlayer = MainController.playerInOrder[MainController.nextPlayer - 1];
			return false;
		}
	}
}
