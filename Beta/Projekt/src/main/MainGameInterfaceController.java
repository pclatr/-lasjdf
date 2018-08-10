package main;

import gains.Credits;
import gains.Points;
import gains.Ranks;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import main.alertBox.AlertBox;
import main.standardMethods.SetAttributeLabels;
import main.standardMethods.SortPlayerList;
import userInterface.MainController;

/**
 * @author Johannes Seith and Jakob Seith
 */
public class MainGameInterfaceController {

    Points givePlayerPoints = new Points();
    Player player = new Player();
    Ranks ranks = new Ranks();
    Credits givePlayerCredits = new Credits();
    MainGameMethods mainGame = new MainGameMethods();

    private Player[] playerList;

    private Label[] cubeList;

    private Label[] labelList;

    private Label[][] attributeLabels;

    private CheckBox[] checkBoxList;
    private boolean[] checkBoxValues;

    private Button[] buttonList;

    // triesLabel , pointsLabel, roundsLabel, lastRoll, currentPlayerLabel,
    // playerNameTabel, playerPointsTabel, playerCreditsTabel, playerDrinksTabel,
    // playerRanksTabel

    // newGameButton, rollADice,nextPlayerButton, afterMiniGame,methodTest,
    // shortSkChangeDice, ;

    /**
     * To start the UI. You can start the game in a test modus.
     */
    public MainGameInterfaceController(Player[] playerList, Label[] cubes, Label[] labelList, Label[][] attributeLabels,
                                       CheckBox[] checkboxList, Button[] buttonList) {

        this.playerList = playerList;
        this.cubeList = cubes;
        this.labelList = labelList;
        this.attributeLabels = attributeLabels;
        this.checkBoxList = checkboxList;
        this.buttonList = buttonList;

        checkBoxValues = new boolean[checkboxList.length];
    }

    public void prepareMainGame() {

        buttonList[MainController.buttonListEnum.rollADice.ordinal()].setDisable(false);
        buttonList[MainController.buttonListEnum.nextPlayerButton.ordinal()].setDisable(true);
        MainController.tries = 3;
        labelList[MainController.labelListEnum.triesLabel.ordinal()].setText("Versuche: " + String.valueOf(MainController.tries));
        MainController.roundCounter = 0;
        MainController.currentPlayer = 1;
        MainController.nextPlayer = MainController.currentPlayer + 1;
        MainController.roundHolder = MainController.currentRound;
        MainController.miniGameActive = false;
        labelList[MainController.labelListEnum.currentPlayerLabel.ordinal()].setText(playerList[MainController.currentPlayer - 1].getName() + "'s turn. Good luck!");
        for (CheckBox check : checkBoxList) {
            check.setSelected(false);
        }
        for (int i = 0; i < checkBoxList.length; i++) {
            checkBoxValues[i] = checkBoxList[i].isSelected();
        }
        for (Label cube : cubeList) {
            cube.setText("-");
        }
        setAttributeLabels();

        new AlertBox().information("Los geht's", "Das MainGame beginnt. " + playerList[0].getName() + " beginnt.");
    }

    public void rollDices() {
        // All CheckBoxes have to be false when the Dices are generated the first time.
        if (MainController.tries == 3) {

            for (CheckBox check : checkBoxList) {
                check.setSelected(false);
            }

        }
        // set the NextPlayer button none disable
        buttonList[MainController.buttonListEnum.nextPlayerButton.ordinal()].setDisable(false);

        // Connect the mainGame.CheckBoxes with the checkBoxelabels. It is necessary
        // because a hold up dice should't be rolled.

        for (int i = 0; i < checkBoxList.length; i++) {
            checkBoxValues[i] = checkBoxList[i].isSelected();
        }

        mainGame.generateDices(checkBoxValues);

        // Every time you roll a dice, your tries will be reduce by 1.
        labelList[MainController.labelListEnum.triesLabel.ordinal()].setText(String.valueOf("Versuche: " + MainController.tries));

        // To show the dices on the UI.
        for (int i = 0; i < cubeList.length; i++) {
            cubeList[i].setText(String.valueOf(MainGameMethods.currentDices[i]));

        }

        // Your tries are exhausted so the roll dice button are disable now.
        if (MainController.tries == 0) {
            buttonList[1].setDisable(true);
        }

    }

    public void evaluatePoints() {

        int currentPoints = mainGame.evaluatePoints(MainController.tries)* MainController.currentRound;

        if (MainController.tries != 0 && currentPoints == 0) {
            System.out
                    .println("Wirklich den Zug beenden? Du wirst keine Punkte bekommen und hast noch Versuche übrig.");
            if (Input.stringInput().equals("j")) {
                MainController.tries = 0;
            }
        }

        if (currentPoints != 0 || MainController.tries == 0) {

            if (currentPoints != 0) {
                givePlayerPoints.setPlayerPoints(true, false, false, currentPoints, MainController.playerInOrder,
                        MainController.currentPlayer);
                givePlayerCredits.givePlayerCredits(true, false, false, currentPoints, MainController.playerInOrder,
                        MainController.currentPlayer);
                MainController.lastPoints = currentPoints;
                MainController.lastCredits = currentPoints * 10;
            }
            chooseNextPlayer(currentPoints);
        }

    }

    /**
     * An extra method to prepare everything for the new turn.
     *
     * @param currentPoints to assign the points to the current player. There is a
     *                      playercounter to select on any time the current player.
     */
    private void chooseNextPlayer(int currentPoints) {
        if (currentPoints != 0) {
            ranks.generateRanks(playerList);
        }

        MainController.miniGame = false;
        MainController.roundCounter++;
        if (MainController.roundCounter == Player.amountOfPlayers) {
            MainController.currentRound++;
            labelList[MainController.labelListEnum.roundsLabel.ordinal()].setText("Round: " + String.valueOf(MainController.currentRound));
            MainController.roundCounter = 0;
            MainController.miniGame = true;

        }

        if (!MainController.miniGame) {
            MainController.maxAvaiablepointChangeUses = 3;
            if (currentPoints != 0) {
                new AlertBox().information("Hinweis!", MainController.playerInOrder[MainController.currentPlayer - 1].getName()
                        + " gets " + currentPoints + " point(s), has now "
                        + MainController.playerInOrder[MainController.currentPlayer - 1].getCredits()
                        + " credits \nand is on the "
                        + MainController.playerInOrder[MainController.currentPlayer - 1].getRank() + "th rank.\n"
                        + MainController.playerInOrder[MainController.nextPlayer - 1].getName() + " gets the dices.");
            } else {
                new AlertBox().information("Hinweis!", MainController.playerInOrder[MainController.currentPlayer - 1].getName()
                        + " gets 0 points and is still on the "
                        + MainController.playerInOrder[MainController.currentPlayer - 1].getRank() + "th rank.\n"
                        + MainController.playerInOrder[MainController.nextPlayer - 1].getName() + " gets the dices.");
            }
        } else {
            MainController.maxAvaiablepointChangeUses = 0;
            if (currentPoints != 0) {
                new AlertBox().information("Hinweis!",
                        MainController.playerInOrder[MainController.currentPlayer - 1].getName() + " gets "
                                + currentPoints + " point(s), has now "
                                + MainController.playerInOrder[MainController.currentPlayer - 1].getCredits()
                                + " credits \nand is on the "
                                + MainController.playerInOrder[MainController.currentPlayer - 1].getRank()
                                + "th rank.\n" + "The " + MainController.currentRound
                                + "th round will begin, but first be ready for a Minigame");
            } else {
                new AlertBox().information("Hinweis!",
                        MainController.playerInOrder[MainController.currentPlayer - 1].getName()
                                + " gets 0 points and is still on the "
                                + MainController.playerInOrder[MainController.currentPlayer - 1].getRank()
                                + "th rank.\n" + "The " + MainController.currentRound
                                + "th round will begin, but first be ready for a Minigame");
            }
        }

        setAttributeLabels();

        labelList[MainController.labelListEnum.lastRoll.ordinal()].setText("Last Roll: " + cubeList[0].getText() + " | " + cubeList[1].getText() + " | "
                + cubeList[2].getText() + " | " + cubeList[3].getText() + " | " + cubeList[4].getText() + " | "
                + cubeList[5].getText());

        for (Label cube : cubeList) {
            cube.setText("-");
        }

        if (MainController.currentPlayer == Player.amountOfPlayers) {
            MainController.currentPlayer = 1;
        } else {
            MainController.currentPlayer++;
        }

        if (MainController.currentPlayer == Player.amountOfPlayers) {
            MainController.nextPlayer = 1;
        } else {
            MainController.nextPlayer = MainController.currentPlayer + 1;
        }
        // To reset the game when one player has finished his turn.
        MainController.tries = 3;
        labelList[MainController.labelListEnum.triesLabel.ordinal()].setText("Versuche: " + String.valueOf(MainController.tries));

        labelList[MainController.labelListEnum.currentPlayerLabel.ordinal()].setText(
                MainController.playerInOrder[MainController.currentPlayer - 1].getName() + "'s turn. Good luck!");

        for (CheckBox check : checkBoxList) {
            check.setSelected(false);
        }

        for (int i = 0; i < checkBoxList.length; i++) {
            checkBoxValues[i] = checkBoxList[i].isSelected();
        }

        buttonList[MainController.buttonListEnum.rollADice.ordinal()].setDisable(false);
        buttonList[MainController.buttonListEnum.nextPlayerButton.ordinal()].setDisable(true);

    }

    private void setAttributeLabels() {
        new SortPlayerList().sortPlayerList(playerList);
        new SetAttributeLabels().setAttributeLabels(playerList, attributeLabels);
    }
}
