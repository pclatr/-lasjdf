package main.standardMethods;

import main.alertBox.AlertBox;
import gains.Ranks;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import main.Player;
import userInterface.MainController;

public class EndMiniGame {

    public void endMiniGame(Player[] playerList, Button[] buttonList, CheckBox[] checkBoxList, boolean[] checkBoxValues, Label[] cubeList, Label[] labelList, Label[][] attributeLabels) {
        new Ranks().generateRanks(playerList);
        buttonList[MainController.buttonListEnum.rollADice.ordinal()].setDisable(false);
        buttonList[MainController.buttonListEnum.nextPlayerButton.ordinal()].setDisable(true);
        MainController.tries = 3;
        MainController.roundCounter = 0;
        MainController.currentPlayer = 1;
        MainController.nextPlayer = MainController.currentPlayer + 1;
        MainController.roundHolder = MainController.currentRound;
        MainController.miniGameActive = false;
        for (CheckBox check : checkBoxList) {
            check.setSelected(false);
        }
        for (int i = 0; i < checkBoxList.length; i++) {
            checkBoxValues[i] = checkBoxList[i].isSelected();
        }
        for (Label cube : cubeList) {
            cube.setText("-");
        }
        new SortPlayerList().sortPlayerList(playerList);
        for (int i = 0; i < playerList.length; i++) {
            MainController.playerInOrder[i] = playerList[i];
            MainController.playerInOrder[i].setPlayerPosition(i+1);
        }
        labelList[MainController.labelListEnum.triesLabel.ordinal()]
                .setText("Tries: " + MainController.tries);
        labelList[MainController.labelListEnum.roundsLabel.ordinal()]
                .setText("Round: " + MainController.currentRound);
        labelList[MainController.labelListEnum.currentPlayerLabel.ordinal()]
                .setText(playerList[MainController.currentPlayer - 1].getName() + "'s turn. Good luck!");
        labelList[MainController.labelListEnum.lastRoll.ordinal()].setText("");
        new AlertBox().information("Runde " + MainController.currentRound, "Das MainGame beginnt. " + playerList[0].getName() + " beginnt.");
        MainController.miniGameIsOver = true;
        new SetAttributeLabels().setAttributeLabels(playerList, attributeLabels);
    }
}
