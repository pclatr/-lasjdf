package main.standardMethods;

import javafx.scene.control.Label;
import main.Player;
import userInterface.MainController;

public class SetAttributeLabels {

    public void setAttributeLabels(Player[] playerList, Label[][]attributeLabels){
        for (int i = 0; i < playerList.length; i++) {
            for (int j = 0; j < MainController.amountOfAttributes; ) {
                playerList[i].setPlayerPosition(i+1);
                attributeLabels[i][j].setText(playerList[i].getName());
                j++;
                attributeLabels[i][j].setText(String.valueOf(playerList[i].getPoints()));
                j++;
                attributeLabels[i][j].setText(String.valueOf(playerList[i].getCredits()));
                j++;
                attributeLabels[i][j].setText(String.valueOf(playerList[i].getDrinks()));
                j++;
                attributeLabels[i][j].setText(String.valueOf(playerList[i].getRank()));
                j++;
                if (playerList[i].getSkChangeDice() == 0) {
                    attributeLabels[i][j].setVisible(false);
                    j++;
                } else {
                    attributeLabels[i][j].setText(String.valueOf(playerList[i].getSkChangeDice()));
                    attributeLabels[i][j].setVisible(true);
                    j++;
                }
                if (playerList[i].getSkChangeName() == 0) {
                    attributeLabels[i][j].setVisible(false);
                    j++;
                } else {
                    attributeLabels[i][j].setText(String.valueOf(playerList[i].getSkChangeName()));
                    attributeLabels[i][j].setVisible(true);
                    j++;
                }
                if (playerList[i].getSkChangePunishment() == 0) {
                    attributeLabels[i][j].setVisible(false);
                    j++;
                } else {
                    attributeLabels[i][j].setText(String.valueOf(playerList[i].getSkChangePunishment()));
                    attributeLabels[i][j].setVisible(true);
                    j++;
                }
                if (playerList[i].getSkChangeGainsG() == 0) {
                    attributeLabels[i][j].setVisible(false);
                    j++;
                } else {
                    attributeLabels[i][j].setText(String.valueOf(playerList[i].getSkChangeGainsG()));
                    attributeLabels[i][j].setVisible(true);
                    j++;
                }
                if (playerList[i].getSkChangeAwardGLE() == 0) {
                    attributeLabels[i][j].setVisible(false);
                    j++;
                } else {
                    attributeLabels[i][j].setText(String.valueOf(playerList[i].getSkChangeAwardGLE()));
                    attributeLabels[i][j].setVisible(true);
                    j++;
                }
                if (playerList[i].getSkChangeAwardGLO() == 0) {
                    attributeLabels[i][j].setVisible(false);
                    j++;
                } else {
                    attributeLabels[i][j].setText(String.valueOf(playerList[i].getSkChangeAwardGLO()));
                    attributeLabels[i][j].setVisible(true);
                    j++;
                }
                if (playerList[i].getSkChangeOPDice() == 0) {
                    attributeLabels[i][j].setVisible(false);
                    j++;
                } else {
                    attributeLabels[i][j].setText(String.valueOf(playerList[i].getSkChangeOPDice()));
                    attributeLabels[i][j].setVisible(true);
                    j++;
                }
            }
        }
    }
}
