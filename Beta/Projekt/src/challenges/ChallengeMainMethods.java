package challenges;

import gains.Ranks;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import main.MainGameMethods;
import main.Player;
import main.standardMethods.SetAttributeLabels;
import main.standardMethods.SortPlayerList;
import userInterface.MainController;

public class ChallengeMainMethods {
    MainGameMethods mainGameMethods = new MainGameMethods();
    Ranks ranks = new Ranks();
    ChallengeCombinationTasks combinationTasks;
    CheckChallenge checkCombination;

    protected static boolean resetCounter = true;
    protected boolean firstPartPased;
    protected boolean firstRoll;
    protected boolean challengePased;

    protected static int selectedCombination;

    protected int itsTimeForChallenge;

    protected boolean[] checkBoxValues;

    protected Player[] playerList;

    protected Label[][] attributeLabels;

    protected Label[] cubeList;
    protected Label[] labelList;

    protected CheckBox[] checkBoxList;

    protected Button[] buttonList;


    public ChallengeMainMethods(Player[] playerList, Label[][] attributeLabels, Label[] cubeList, Label[] labelList,
                                CheckBox[] checkBoxList, Button[] buttonList) {

        this.playerList = playerList;
        this.attributeLabels = attributeLabels;
        this.cubeList = cubeList;
        this.labelList = labelList;
        this.checkBoxList = checkBoxList;
        this.buttonList = buttonList;
        this.checkBoxValues = new boolean[checkBoxList.length];
    }


    public boolean isItTimeForChallenge() {
        if (resetCounter) {
            itsTimeForChallenge = (int) (Math.random() * 10 + 3);
            resetCounter = false;
        }

        /*Zum Testen*/
       // itsTimeForChallenge = 1;

        if (itsTimeForChallenge == MainController.rollCounter) {

            combinationTasks = new ChallengeCombinationTasks(playerList, attributeLabels, cubeList, labelList, checkBoxList,
                    buttonList);
            checkCombination = new CheckChallenge(playerList, attributeLabels, cubeList, labelList, checkBoxList,
                    buttonList);

            int amountDifferentChallengeTypes = 1;

            itsTimeForChallenge = (int) (Math.random() * amountDifferentChallengeTypes + 1);

            if (itsTimeForChallenge == 1) {

                return combinationTasks.rollSpecificCombination();


            }
        }

        return false;
    }

    public void startChallenge() {
        resetCounter = true;
        MainController.challengeActive = true;
        firstRoll = true;
    }

    public void rollDice() {
        if (firstRoll) {
            for (CheckBox check : checkBoxList) {
                check.setSelected(false);
            }
            firstRoll = false;

        }

        for (int i = 0; i < checkBoxValues.length; i++) {
            checkBoxValues[i] = checkBoxList[i].isSelected();
        }

        mainGameMethods.generateDices(checkBoxValues);

        for (int i = 0; i < cubeList.length; i++) {
            cubeList[i].setText(String.valueOf(MainGameMethods.currentDices[i]));
        }
        labelList[MainController.labelListEnum.triesLabel.ordinal()].setText("Tries: " + String.valueOf(MainController.tries));

        buttonList[2].setDisable(false);

        if (MainController.tries == 0) {
            buttonList[MainController.buttonListEnum.rollADice.ordinal()].setDisable(true);
        }
    }

    public void nextRound() {

        checkCombination.checkCombination();
        buttonList[MainController.buttonListEnum.rollADice.ordinal()].setDisable(false);
        buttonList[MainController.buttonListEnum.nextPlayerButton.ordinal()].setDisable(true);

    }

    public void chooseNextPlayer() {

        labelList[MainController.labelListEnum.lastRoll.ordinal()].setText("Last Roll: " + cubeList[0].getText() + " | " + cubeList[1].getText() + " | "
                + cubeList[2].getText() + " | " + cubeList[3].getText() + " | " + cubeList[4].getText() + " | "
                + cubeList[5].getText());

        for (Label cube : cubeList) {
            cube.setText("-");
        }

        MainController.roundCounter++;
        if (MainController.roundCounter == MainController.playerInOrder.length) {
            MainController.currentRound++;

            labelList[MainController.labelListEnum.roundsLabel.ordinal()].setText("Mini Game!");

            MainController.roundCounter = 0;

            System.out.println(
                    "The " + MainController.currentRound + "th round will begin, but first be ready for a Minigame.");
        } else {
            MainController.maxAvaiablepointChangeUses = 3;
        }

        if (MainController.currentPlayer == MainController.playerInOrder.length) {
            MainController.currentPlayer = 1;
        } else {
            MainController.currentPlayer++;
        }

        if (MainController.currentPlayer == MainController.playerInOrder.length) {
            MainController.nextPlayer = 1;
        } else {
            MainController.nextPlayer = MainController.currentPlayer + 1;
        }
        // To reset the game when one player has finished his turn.
        MainController.tries = 3;
        labelList[MainController.labelListEnum.triesLabel.ordinal()].setText("Tries: " + String.valueOf(MainController.tries));

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
        buttonList[MainController.buttonListEnum.nextPlayerButton.ordinal()].setText("Next Player");
        resetCounter = true;
        firstPartPased = false;
        MainController.challengeActive = false;
        MainController.rollCounter = 0;
        setAttributeLabels();
    }

    public void setAttributeLabels() {
        int counter = 0;
        for (Player player : MainController.playerInOrder) {
            playerList[counter] = player;
            counter++;
        }
        new SortPlayerList().sortPlayerList(playerList);
        new SetAttributeLabels().setAttributeLabels(playerList,attributeLabels);
    }

    public void prepareChallenge(int tries, boolean firstPartPased, boolean changeNextButton) {

        for (CheckBox check : checkBoxList) {
            check.setSelected(false);
        }
        for (Label cube : cubeList) {
            cube.setText("-");
        }

        MainController.tries = tries;
        labelList[MainController.labelListEnum.triesLabel.ordinal()].setText(String.valueOf("Tries: " + MainController.tries));

        this.firstPartPased = firstPartPased;
        if (changeNextButton) {
            buttonList[MainController.buttonListEnum.nextPlayerButton.ordinal()].setText("Next Round");
        }
    }

}