package shortSkills;

import main.alertBox.AlertBox;
import gains.Ranks;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import main.MainGameMethods;
import main.Player;
import userInterface.MainController;

public class ShortSkillChangeGains {

    MainGameMethods mainGame = new MainGameMethods();
    Ranks ranks = new Ranks();

    Player[] playerList;

    Label[][] attributeLabels;

    Label[] labelList;
    Label[] shortSkillLabelList;
    Label[] skChangeLabelList;
    Label[] cubeList;

    Button[] buttonList;
    Button[] shortSkillButtonList;
    Button[] skChangeButtonList;
    Button[] specialRightsButtonList;

    static int currentPlayer;


    public ShortSkillChangeGains(Player[] playerList, Label[] cubeList, Label[][] attributeLabels, Label[] labelList,
                                 Label[] skChangeLabelList, Button[] buttonList, Button[] skChangeButtonList) {
        this.playerList = playerList;
        this.cubeList = cubeList;
        this.attributeLabels = attributeLabels;
        this.labelList = labelList;
        this.skChangeLabelList = skChangeLabelList;
        this.buttonList = buttonList;
        this.skChangeButtonList = skChangeButtonList;

    }

    public void useChangeSkill() {
        int skillNumber;

        new AlertBox().userInput("Welcher Spieler?", "Welcher Spieler moechte einen Skill einsetzten? (Spielernummer ist gleich Position.)", "Spielernummer", true);
        int playerNumber = AlertBox.userInputInteger;

        // test
        System.out.println(playerList[playerNumber].getName());

        new AlertBox().userInput("Skill auswählen", "Du hast von Skill 1 " + playerList[playerNumber].getSkChangeGainsG() + ", vom Skill 2 "
                + playerList[playerNumber].getSkChangeAwardGLE() + ", vom Skill 3 "
                + playerList[playerNumber].getSkChangeAwardGLO() + " und vom Skill 4 "
                + playerList[playerNumber].getSkChangeOPDice() + " Stueck." + "\nWelchen moechstest du einsetzten?", "Skillnummer eingeben", true);
        skillNumber = AlertBox.userInputInteger;

        if (!(playerNumber == MainController.holdCurrentPlayer.getPlayerPosition())) {
            switch (skillNumber) {
                case 1:
                    changeGainsGeneral(playerNumber);
                    break;
                case 2:
                    changeAwardGameLeader(playerNumber);
                    break;
                case 3:
                    changeAwardGameLoser(playerNumber);
                    break;
                case 4:
                    changeOPDice(playerNumber);
                    break;
            }
        } else {

            new AlertBox().information("Achtung", "Du kannst mit diese Skills nicht gegen dich selbst einsetzten.");
        }


    }

    public void buyChangeSkill(int skillNumber, int cost) {
        skillNumber += 8;
        if (skillNumber == 9) {
            MainController.holdCurrentPlayer
                    .setSkChangeGainsG(MainController.holdCurrentPlayer.getSkChangeGainsG() + 1);
            MainController.holdCurrentPlayer.setCredits(MainController.holdCurrentPlayer.getCredits() - cost);
            attributeLabels[MainController.holdCurrentPlayer.getPlayerPosition() - 1][Player.playerAttributesEnum.credits.ordinal()]

                    .setText(String.valueOf(MainController.holdCurrentPlayer.getCredits()));

            attributeLabels[MainController.holdCurrentPlayer.getPlayerPosition() - 1][Player.playerAttributesEnum.skChangeGainsG.ordinal()]
                    .setText(String.valueOf(MainController.holdCurrentPlayer.getSkChangeGainsG()));
            attributeLabels[MainController.holdCurrentPlayer.getPlayerPosition() - 1][Player.playerAttributesEnum.skChangeGainsG.ordinal()].setVisible(true);
            if (MainController.holdCurrentPlayer.getCredits() < MainController.skChangeOnePrice) {
                skChangeButtonList[MainController.skChangeButtonListEnum.buySkChangeAwardAll.ordinal()].setDisable(true);
            }
        } else if (skillNumber == 10) {
            MainController.holdCurrentPlayer
                    .setSkChangeAwardGLE(MainController.holdCurrentPlayer.getSkChangeAwardGLE() + 1);
            MainController.holdCurrentPlayer.setCredits(MainController.holdCurrentPlayer.getCredits() - cost);
            attributeLabels[MainController.holdCurrentPlayer.getPlayerPosition() - 1][Player.playerAttributesEnum.credits.ordinal()]
                    .setText(String.valueOf(MainController.holdCurrentPlayer.getCredits()));

            attributeLabels[MainController.holdCurrentPlayer.getPlayerPosition() - 1][Player.playerAttributesEnum.skChangeAwardGLE.ordinal()]
                    .setText(String.valueOf(MainController.holdCurrentPlayer.getSkChangeAwardGLE()));
            attributeLabels[MainController.holdCurrentPlayer.getPlayerPosition() - 1][Player.playerAttributesEnum.skChangeAwardGLE.ordinal()].setVisible(true);
            if (MainController.holdCurrentPlayer.getCredits() < MainController.skChangeTwoPrice) {
                skChangeButtonList[MainController.skChangeButtonListEnum.buySkChangeAwardGLE.ordinal()].setDisable(true);
            }
        } else if (skillNumber == 11) {
            MainController.holdCurrentPlayer
                    .setSkChangeAwardGLO(MainController.holdCurrentPlayer.getSkChangeAwardGLO() + 1);
            MainController.holdCurrentPlayer.setCredits(MainController.holdCurrentPlayer.getCredits() - cost);
            attributeLabels[MainController.holdCurrentPlayer.getPlayerPosition() - 1][Player.playerAttributesEnum.credits.ordinal()]
                    .setText(String.valueOf(MainController.holdCurrentPlayer.getCredits()));
            attributeLabels[MainController.holdCurrentPlayer.getPlayerPosition() - 1][Player.playerAttributesEnum.skChangeAwardGLO.ordinal()]
                    .setText(String.valueOf(MainController.holdCurrentPlayer.getSkChangeAwardGLO()));
            attributeLabels[MainController.holdCurrentPlayer.getPlayerPosition() - 1][Player.playerAttributesEnum.skChangeAwardGLO.ordinal()].setVisible(true);
            if (MainController.holdCurrentPlayer.getCredits() < MainController.skChangeThreePrice) {
                skChangeButtonList[MainController.skChangeButtonListEnum.buySkChangeAwardGLO.ordinal()].setDisable(true);
            }
        } else if (skillNumber == 12) {

            MainController.holdCurrentPlayer
                    .setSkChangeOPDice(MainController.holdCurrentPlayer.getSkChangeOPDice() + 1);
            MainController.holdCurrentPlayer.setCredits(MainController.holdCurrentPlayer.getCredits() - cost);
            attributeLabels[MainController.holdCurrentPlayer.getPlayerPosition() - 1][Player.playerAttributesEnum.credits.ordinal()]
                    .setText(String.valueOf(MainController.holdCurrentPlayer.getCredits()));
            attributeLabels[MainController.holdCurrentPlayer.getPlayerPosition() - 1][Player.playerAttributesEnum.skChangeOPDice.ordinal()]
                    .setText(String.valueOf(MainController.holdCurrentPlayer.getSkChangeOPDice()));
            attributeLabels[MainController.holdCurrentPlayer.getPlayerPosition() - 1][Player.playerAttributesEnum.skChangeOPDice.ordinal()].setVisible(true);
            if (MainController.holdCurrentPlayer.getCredits() < MainController.skChangeFourPrice) {
                skChangeButtonList[MainController.skChangeButtonListEnum.buySkChangeOPDice.ordinal()].setDisable(true);
            }
        }

    }

    private void changeGainsGeneral(int playerNumber) {

        if (playerList[playerNumber].getSkChangeGainsG() != 0) {
            MainController.holdLastPlayer
                    .setPoints(MainController.holdLastPlayer.getPoints() - MainController.lastPoints * 2);
            MainController.holdLastPlayer
                    .setCredits(MainController.holdLastPlayer.getCredits() - MainController.lastCredits * 2);

            ranks.generateRanks(playerList);

            new AlertBox().information("Erfolgreich", "Du hast " + MainController.holdLastPlayer.getName() + " gerade " + (MainController.lastPoints * 2)
                    + " Punkte und " + (MainController.lastCredits * 2) + " Credits abgezogen."
                    + "\nEr ist nun auf dem " + MainController.holdLastPlayer.getRank() + "ten Rang.");

            playerList[playerNumber].setSkChangeGainsG(playerList[playerNumber].getSkChangeGainsG() - 1);

        } else {

            new AlertBox().information("Achtung", "Du hast diesen Skill nicht!");
        }
        setAttributeLabels();
    }

    private void changeAwardGameLeader(int playerNumber) {
        int hold = 0;
        for (int i = 0; i < playerList.length; i++) {
            if (playerList[i].getRank() == 1) {
                hold = i;
                break;
            }
        }

        if (MainController.holdLastPlayer == playerList[hold]) {

            if (playerList[playerNumber].getSkChangeAwardGLE() != 0) {
                MainController.holdLastPlayer
                        .setPoints(MainController.holdLastPlayer.getPoints() - MainController.lastPoints * 2);
                MainController.holdLastPlayer
                        .setCredits(MainController.holdLastPlayer.getCredits() - MainController.lastCredits * 2);

                ranks.generateRanks(playerList);


                new AlertBox().information("Erfolgreich", "Du hast " + MainController.holdLastPlayer.getName() + " gerade "
                        + (MainController.lastPoints * 2) + " Punkte und " + (MainController.lastCredits * 2)
                        + " Credits abgezogen." + "\nEr ist nun auf dem " + MainController.holdLastPlayer.getRank()
                        + "ten Rang.");
                playerList[playerNumber].setSkChangeAwardGLE(playerList[playerNumber].getSkChangeAwardGLE() - 1);

                setAttributeLabels();
            } else {
                new AlertBox().information("Achtung", "Du hast diesen Skill nicht!");
            }

        } else {
            new AlertBox().information("Achtung", "Der Game Leader ist nicht der letzte Spieler!");
        }

    }

    private void changeAwardGameLoser(int playerNumber) {
        int hold = 0;
        for (int i = playerList.length - 1; i >= 0; i--) {
            if (playerList[i].getRank() == playerList.length) {
                hold = i;
                break;
            }
        }

        if (MainController.holdLastPlayer == playerList[hold]) {
            playerNumber -= 1;

            if (playerList[playerNumber].getSkChangeAwardGLO() != 0) {
                MainController.holdLastPlayer
                        .setPoints(MainController.holdLastPlayer.getPoints() - MainController.lastPoints * 2);
                MainController.holdLastPlayer
                        .setCredits(MainController.holdLastPlayer.getCredits() - MainController.lastCredits * 2);

                ranks.generateRanks(playerList);

                new AlertBox().information("Erfolgreich", "Du hast " + MainController.holdLastPlayer.getName() + " gerade "
                        + (MainController.lastPoints * 2) + " Punkte und " + (MainController.lastCredits * 2)
                        + " Credits abgezogen." + "\nEr ist nun auf dem " + MainController.holdLastPlayer.getRank()
                        + "ten Rang.");
                playerList[playerNumber].setSkChangeAwardGLO(playerList[playerNumber].getSkChangeAwardGLO() - 1);

                setAttributeLabels();
            } else {

                new AlertBox().information("Achtung", "Du hast diesen Skill nicht!");
            }

        } else {

            new AlertBox().information("Achtung", "Der Game Loser ist nicht der letzte Spieler!");
        }
    }

    private void changeOPDice(int playerNumber) {

        if (MainController.tries != 0) {

            if (playerList[playerNumber].getSkChangeOPDice() != 0) {
                int diceNumber;
                do {
                    new AlertBox().userInput("Würfel auswählen", "Welchen Würfel möchtest du verändern?", "Würfelnummer", true);
                    if(AlertBox.userInputInteger<1 || AlertBox.userInputInteger>6){
                        new AlertBox().information("Achtung","Einen Wert zwischen 1 und 6 eingeben.");
                    }
                } while (AlertBox.userInputInteger<1 || AlertBox.userInputInteger>6);
                diceNumber = AlertBox.userInputInteger;
                do {
                    new AlertBox().userInput("Neuer Würfelwert", "Welchen Wert soll er haben?", "Würfelwert", true);
                    if(AlertBox.userInputInteger<1 || AlertBox.userInputInteger>6){
                        new AlertBox().information("Achtung","Einen Wert zwischen 1 und 6 eingeben.");
                    }
                } while (AlertBox.userInputInteger < 1 || AlertBox.userInputInteger > 6);

                MainGameMethods.currentDices[diceNumber-1] = AlertBox.userInputInteger;

                playerList[playerNumber].setSkChangeOPDice(playerList[playerNumber].getSkChangeOPDice() - 1);

                if (playerList[playerNumber].getSkChangeOPDice() == 0) {
                    attributeLabels[MainController.holdCurrentPlayer.getPlayerPosition() - 1][Player.playerAttributesEnum.skChangeOPDice.ordinal()].setVisible(false);
                } else {
                    attributeLabels[MainController.holdCurrentPlayer.getPlayerPosition() - 1][Player.playerAttributesEnum.skChangeOPDice.ordinal()]
                            .setText(String.valueOf(playerList[playerNumber].getSkChangeOPDice()));
                }

                cubeList[diceNumber].setText(String.valueOf(MainGameMethods.currentDices[diceNumber]));

            } else {
                new AlertBox().information("Achtung","Du hast diesen Skill nicht!");
            }

        } else {
            new AlertBox().information("Achtung","Diesen skill kannst du nicht nach dem letzten Wurf einsetzten!");
        }

    }

    private void setAttributeLabels() {

        Player hold;
        for (int i = 0; i < playerList.length; i++) {
            for (int j = playerList.length - 1; j > 0; j--) {

                if (playerList[j - 1].getRank() > playerList[j].getRank()) {
                    hold = playerList[j];
                    playerList[j] = playerList[j - 1];
                    playerList[j - 1] = hold;
                }

            }
        }

        for (int i = 0; i < playerList.length; i++) {
            for (int j = 0; j < MainController.amountOfAttributes; ) {

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
