package shortSkills;

import main.alertBox.AlertBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import main.MainGameMethods;
import main.Player;
import userInterface.MainController;

public class ShortSkills {
    MainGameMethods mainGame = new MainGameMethods();

    public static int amountOfChangeDiceUses = 0;

    Player[] playerList;

    Label[][] attributeLabels;

    Label[] labelList;
    Label[] skNormalLabelList;
    Label[] cubeList;

    Button[] buttonList;
    Button[] skNormalButtonList;

    // triesLabel, pointsLabel, roundsLabel, lastRoll, currentPlayerLabel,
    // playerNameTabel, playerPointsTabel,
    // playerCreditsTabel, playerDrinksTabel, playerRanksTabel,
    // amountOfSkChangeDice, priceOfSkChangeDice,
    // amountOfSkChangeName, priceOfSkChangeName;

    // newGameButton, rollADice, nextPlayerButton, methodTest, afterMiniGame,

    // buySkChangeDice, useSkChangeDice, buySkChangeName, useSkChangeName,
    // buyChangePunishment, useChangePunishment, buySkChangeAwardAll,
    // buySkChangeAwardGLE, buySkChangeAwardGLO, buySkChangeOPDice,
    // useOtherTurnSkill
    //

    public ShortSkills(Player[] playerList, Label[] cubeList, Label[][] attributeLabels, Label[] labelList,
                       Label[] skNormalLabelList, Button[] buttonList, Button[] skNormalButtonList) {
        this.playerList = playerList;
        this.cubeList = cubeList;
        this.attributeLabels = attributeLabels;
        this.labelList = labelList;
        this.skNormalLabelList = skNormalLabelList;
        this.buttonList = buttonList;
        this.skNormalButtonList = skNormalButtonList;

    }

    public void buy(int costs, int skillNumber) {

        if (MainController.holdCurrentPlayer.getCredits() >= costs) {

            MainController.holdCurrentPlayer.setCredits(MainController.holdCurrentPlayer.getCredits() - costs);

            attributeLabels[MainController.holdCurrentPlayer.getPlayerPosition() - 1][2]
                    .setText(String.valueOf(MainController.holdCurrentPlayer.getCredits()));

            skillNumber += 5;

            switch (skillNumber) {
                case 6:
                    MainController.holdCurrentPlayer
                            .setSkChangeDice(MainController.holdCurrentPlayer.getSkChangeDice() + 1);

                    attributeLabels[MainController.holdCurrentPlayer.getPlayerPosition() - 1][5]
                            .setText(String.valueOf(MainController.holdCurrentPlayer.getSkChangeDice()));
                    attributeLabels[MainController.holdCurrentPlayer.getPlayerPosition() - 1][5].setVisible(true);
                    if (MainController.holdCurrentPlayer.getCredits() < MainController.skNormalOnePrice) {

                        skNormalButtonList[MainController.skNormalButtonListEnum.buySkChangeDice.ordinal()].setDisable(true);

                    }
                    break;
                case 7:
                    skNormalButtonList[3].setDisable(false);
                    MainController.holdCurrentPlayer
                            .setSkChangeName(MainController.holdCurrentPlayer.getSkChangeName() + 1);

                    attributeLabels[MainController.holdCurrentPlayer.getPlayerPosition() - 1][6]
                            .setText(String.valueOf(MainController.holdCurrentPlayer.getSkChangeName()));
                    attributeLabels[MainController.holdCurrentPlayer.getPlayerPosition() - 1][6].setVisible(true);
                    if (MainController.holdCurrentPlayer.getCredits() < MainController.skNormalTwoPrice) {

                        skNormalButtonList[MainController.skNormalButtonListEnum.buySkChangeName.ordinal()].setDisable(true);
                    }
                    break;
                case 8:
                    MainController.holdCurrentPlayer
                            .setSkChangePunishment(MainController.holdCurrentPlayer.getSkChangePunishment() + 1);

                    attributeLabels[MainController.holdCurrentPlayer.getPlayerPosition() - 1][7]
                            .setText(String.valueOf(MainController.holdCurrentPlayer.getSkChangePunishment()));
                    attributeLabels[MainController.holdCurrentPlayer.getPlayerPosition() - 1][7].setVisible(true);
                    if (MainController.holdCurrentPlayer.getCredits() < MainController.skNormalThreePrice) {

                        skNormalButtonList[MainController.skNormalButtonListEnum.buyChangePunishment.ordinal()].setDisable(true);
                    }
                    break;
            }


        } else {
            new AlertBox().information("Achtung", "Du hast nicht genug Credits.");
        }

    }

    public void changeDice() {

        if (MainController.holdCurrentPlayer.getSkChangeDice() >= 1) {
            int diceNumber;
            do {
                new AlertBox().userInput("Würfel wählen", "Welchen Würfel willst du ändern?", "Würfelnummer", true);
                if(AlertBox.userInputInteger<1 || AlertBox.userInputInteger>6){
                    new AlertBox().information("Achtung","Einen Wert zwischen 1 und 6 eingeben.");
                }
            } while (AlertBox.userInputInteger < 1 || AlertBox.userInputInteger > 6);
            diceNumber = AlertBox.userInputInteger;
            do {
                new AlertBox().userInput("Würfel wählen", null, "Neuer Würfelwert", true);
                if(AlertBox.userInputInteger<1 || AlertBox.userInputInteger>6){
                    new AlertBox().information("Achtung","Einen Wert zwischen 1 und 6 eingeben.");
                }
            } while (AlertBox.userInputInteger < 1 || AlertBox.userInputInteger > 6);


            MainGameMethods.currentDices[diceNumber - 1] = AlertBox.userInputInteger;

            MainController.holdCurrentPlayer.setSkChangeDice(MainController.holdCurrentPlayer.getSkChangeDice() - 1);

            cubeList[diceNumber - 1].setText(String.valueOf(MainGameMethods.currentDices[diceNumber - 1]));

            if (MainController.holdCurrentPlayer.getSkChangeDice() == 0) {
                attributeLabels[MainController.holdCurrentPlayer.getPlayerPosition() - 1][5].setVisible(false);
                skNormalButtonList[MainController.skNormalButtonListEnum.useSkChangeDice.ordinal()].setDisable(true);
            } else {
                attributeLabels[MainController.holdCurrentPlayer.getPlayerPosition() - 1][5]
                        .setText(String.valueOf(MainController.holdCurrentPlayer.getSkChangeDice()));

                amountOfChangeDiceUses++;
                if (!MainController.holdCurrentPlayer.getSRTTChangeDice() || amountOfChangeDiceUses > 1) {
                    skNormalButtonList[MainController.skNormalButtonListEnum.useSkChangeDice.ordinal()].setDisable(true);
                }

            }
        } /*else {
            System.out.println("Das musst du erst kaufen");
        }*/

    }

    public void changeName() {

        if (MainController.holdCurrentPlayer.getSkChangeName() >= 1) {


            new AlertBox().userInput("Spieler auswählen",null,"Spieler Position",true);
            int chosenPlayer = AlertBox.userInputInteger;

            new AlertBox().userInput("Spielernamen ändern",null,"Neuer Name",false);

            playerList[chosenPlayer - 1].setName(AlertBox.userInputString);

            attributeLabels[playerList[chosenPlayer - 1].getPlayerPosition()-1][Player.playerAttributesEnum.name.ordinal()].setText(AlertBox.userInputString);

            if (chosenPlayer == MainController.currentPlayer) {
                labelList[MainController.labelListEnum.currentPlayerLabel.ordinal()].setText(AlertBox.userInputString + "'s turn, Good luck!");
            }

            MainController.holdCurrentPlayer.setSkChangeName(MainController.holdCurrentPlayer.getSkChangeName() - 1);

            attributeLabels[MainController.holdCurrentPlayer.getPlayerPosition() - 1][6]
                    .setText(String.valueOf(MainController.holdCurrentPlayer.getSkChangeName()));

            if (MainController.holdCurrentPlayer.getSkChangeName() == 0) {
                skNormalButtonList[3].setDisable(true);
                attributeLabels[MainController.holdCurrentPlayer.getPlayerPosition() - 1][6].setVisible(false);
            }
        } /*else {
            System.out.println("Das musst du erst kaufen");
        }*/

    }

    public void pointChanger(int costs) {

        if (MainController.holdCurrentPlayer.getPoints() > 0 && MainController.holdCurrentPlayer.getSRPointsChanger()) {

            if (MainController.holdCurrentPlayer.getPoints() > costs) {
                MainController.holdCurrentPlayer.setCredits(MainController.holdCurrentPlayer.getCredits() + 100
                );
            } else {
                MainController.holdCurrentPlayer.setCredits(MainController.holdCurrentPlayer.getCredits() + MainController.holdCurrentPlayer.getPoints() * costs);
            }
            MainController.holdCurrentPlayer.setPoints(MainController.holdCurrentPlayer.getPoints() - costs);

            attributeLabels[MainController.holdCurrentPlayer.getPlayerPosition() - 1][2]
                    .setText(String.valueOf(MainController.holdCurrentPlayer.getCredits()));

            attributeLabels[MainController.holdCurrentPlayer.getPlayerPosition() - 1][1]
                    .setText(String.valueOf(MainController.holdCurrentPlayer.getPoints()));

        } /*else {
            System.out.println("Du hast zu wenige Punkte.");
        }*/
    }
}
