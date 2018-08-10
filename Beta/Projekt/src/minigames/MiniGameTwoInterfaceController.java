package minigames;

import main.alertBox.AlertBox;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import main.MainGameMethods;
import main.Player;
import main.standardMethods.EndMiniGame;
import main.standardMethods.SetSkillAttributes;
import main.standardMethods.SortDices;
import userInterface.MainController;
import gains.*;

public class MiniGameTwoInterfaceController {

    MainGameMethods mainGameMethods = new MainGameMethods();
    Player player = new Player();
    Points points = new Points();
    Credits credits = new Credits();
    Ranks ranks = new Ranks();

    private boolean pairs;
    private boolean firstRoll = false;
    private boolean stateFirstPlayer;
    private boolean gameOver;

    private int amountOfPlayersNext;
    private int amountOfPlayersCurrent;
    private int roundCounter;
    private int miniGameRound;
    private int endRoundCounter;

    private Player[] playerList;
    private Player[] playerListNext;

    private Label[][] attributeLabels;
    private Label[] cubeList;
    private Label[] labelList;

    private CheckBox[] checkBoxList;

    private Button[] buttonList;

    private boolean[] playerSeparatedCurrent;
    private boolean[] playerSeparatedNext;
    private boolean[] checkBoxValues;

    // triesLabel , pointsLabel, roundsLabel, lastRoll, currentPlayerLabel,
    // playerNameTabel, playerPointsTabel, playerCreditsTabel, playerDrinksTabel,
    // playerRanksTabel

    // newGameButton, rollADice,nextPlayerButton, afterMiniGame,methodTest,
    // shortSkChangeDice, ;

    public MiniGameTwoInterfaceController(Player[] playerList, Label[] cubes, Label[] labelList,
                                          Label[][] attributeLabels, CheckBox[] checkBoxList, Button[] buttonList) {

        this.playerList = playerList;
        this.cubeList = cubes;
        this.labelList = labelList;
        this.attributeLabels = attributeLabels;
        this.checkBoxList = checkBoxList;
        this.buttonList = buttonList;

        playerListNext = new Player[playerList.length];
        playerSeparatedCurrent = new boolean[playerList.length];
        playerSeparatedNext = new boolean[playerList.length];

        checkBoxValues = new boolean[checkBoxList.length];
    }

    public void startGame() {

        for (int i = 0; i < playerList.length; i++) {

            playerListNext[i] = playerList[i];

            playerSeparatedCurrent[i] = false;
            playerSeparatedNext[i] = false;

        }

        MainController.currentPlayer = 1;
        MainController.nextPlayer = MainController.currentPlayer + 1;
        amountOfPlayersNext = playerList.length;
        amountOfPlayersCurrent = playerList.length;
        miniGameRound = 1;
        roundCounter = 1;
        MainController.tries = 1;

        MainController.holdCurrentPlayer = playerList[MainController.currentPlayer - 1];
        MainController.holdNextPlayer = playerList[MainController.nextPlayer - 1];
        // lastRoll.setText(");
        labelList[MainController.labelListEnum.triesLabel.ordinal()].setText(String.valueOf("Tries: " + MainController.tries));
        labelList[MainController.labelListEnum.currentPlayerLabel.ordinal()].setText(playerList[MainController.currentPlayer - 1].getName() + "'s turn. Good luck! 2tes");
        labelList[MainController.labelListEnum.roundsLabel.ordinal()].setText("MiniGame Round: 1");

        buttonList[MainController.buttonListEnum.rollADice.ordinal()].setDisable(false);
        buttonList[MainController.buttonListEnum.nextPlayerButton.ordinal()].setDisable(true);


        new AlertBox().information("Mini Game","Mini Game 2 wurde ausgewählt.");

    }

    public void nextPlayer() {
        gameOver = false;
        for (int i = 0; i < checkBoxList.length; i++) {
            checkBoxValues[i] = checkBoxList[i].isSelected();
        }
        if (mainGameMethods.checkHoldDices(checkBoxValues, MainGameMethods.currentDices)) {
            boolean roundOne = false;
            boolean roundTwo = false;
            boolean roundThree = false;
            boolean roundFour = false;
            boolean roundFive = false;

            if (MainController.currentPlayer == 1) {
                if (firstRoll()) {
                    roundOne = true;
                    roundTwo = true;
                    roundThree = true;
                    roundFour = true;
                    roundFive = true;
                }
            }

            if (firstRoll) {

                new SortDices().sortDices(MainGameMethods.currentDices);
                // Pairs

                if (pairs && MainController.currentPlayer != 1) {

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

                                        if (MainGameMethods.currentDices[holder] == MainGameMethods.currentDices[k
                                                + 1]) {
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
                                                    for (int m = l + 1; m < MainGameMethods.currentDices.length
                                                            - 1; m++) {
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
                }
                if (!pairs && MainController.currentPlayer != 1) {

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
                                        if (MainGameMethods.currentDices[holder]
                                                + 1 == MainGameMethods.currentDices[k + 1]) {
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
                                                        if (MainGameMethods.currentDices[holder] + 1 == MainGameMethods.currentDices[m
                                                                + 1]) {
                                                            roundFive = true;
                                                            i = MainGameMethods.currentDices.length;
                                                            j = MainGameMethods.currentDices.length;
                                                            k = MainGameMethods.currentDices.length;
                                                            l = MainGameMethods.currentDices.length;
                                                            m = MainGameMethods.currentDices.length;
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
                }

                int pointsHolder = playerList[MainController.currentPlayer - 1].getPoints();
                int creditsHolder = playerList[MainController.currentPlayer - 1].getCredits();
                boolean pased = false;

                if (miniGameRound == 1 && roundOne) {
                    playerList[MainController.currentPlayer - 1]
                            .setPoints(1 * (MainController.tries + 1) + playerList[MainController.currentPlayer - 1].getPoints());
                    playerList[MainController.currentPlayer - 1]
                            .setCredits(10 * (MainController.tries + 1) + playerList[MainController.currentPlayer - 1].getCredits());
                    MainController.lastPoints = 1;
                    MainController.lastCredits = 10;

                    pased = true;
                } else if (miniGameRound == 2 && roundTwo) {
                    playerList[MainController.currentPlayer - 1]
                            .setPoints(3 * (MainController.tries + 1) + playerList[MainController.currentPlayer - 1].getPoints());
                    playerList[MainController.currentPlayer - 1]
                            .setCredits(30 * (MainController.tries + 1) + playerList[MainController.currentPlayer - 1].getCredits());
                    MainController.lastPoints = 3;
                    MainController.lastCredits = 30;

                    pased = true;
                } else if (miniGameRound == 3 && roundThree) {
                    playerList[MainController.currentPlayer - 1]
                            .setPoints(6 * (MainController.tries + 1) + playerList[MainController.currentPlayer - 1].getPoints());
                    playerList[MainController.currentPlayer - 1]
                            .setCredits(60 * (MainController.tries + 1) + playerList[MainController.currentPlayer - 1].getCredits());
                    MainController.lastPoints = 6;
                    MainController.lastCredits = 60;

                    pased = true;
                } else if (miniGameRound == 4 && roundFour) {
                    playerList[MainController.currentPlayer - 1]
                            .setPoints(8 * (MainController.tries + 1) + playerList[MainController.currentPlayer - 1].getPoints());
                    playerList[MainController.currentPlayer - 1]
                            .setCredits(80 * (MainController.tries + 1) + playerList[MainController.currentPlayer - 1].getCredits());
                    MainController.lastPoints = 8;
                    MainController.lastCredits = 80;

                    pased = true;
                } else if (miniGameRound >= 5 && roundFive) {
                    playerList[MainController.currentPlayer - 1]
                            .setPoints(15 * (MainController.tries + 1) + playerList[MainController.currentPlayer - 1].getPoints());
                    playerList[MainController.currentPlayer - 1]
                            .setCredits(200 * (MainController.tries + 1) + playerList[MainController.currentPlayer - 1].getCredits());
                    MainController.lastPoints = 15;
                    MainController.lastCredits = 200;

                    pased = true;
                }
                if (pased) {
                    chooseNextPlayer(pointsHolder, creditsHolder, true);
                }
                // Verloren
                else {
                    if (MainController.tries != 0) {

                        new AlertBox().confirm("Warnung","Du hast noch Versuche übrig und bist dabei zu verlieren. Fortfahren?");
                        if (AlertBox.confirm){

                            MainController.tries = 0;
                        }
                    }

                    if (MainController.tries == 0) {

                        int pointsLeaderBefore = playerList[0].getPoints();
                        int creditsLeaderBefore = playerList[0].getCredits();

                        if (miniGameRound == 1) {
                            playerList[0].setPoints(1 + playerList[0].getPoints());
                            playerList[0].setCredits(10 + playerList[0].getCredits());
                            MainController.lastPoints = 1;
                            MainController.lastCredits = 10;
                        } else if (miniGameRound == 2) {
                            playerList[0].setPoints(3 + playerList[0].getPoints());
                            playerList[0].setCredits(30 + playerList[0].getCredits());
                            MainController.lastPoints = 3;
                            MainController.lastCredits = 30;
                        } else if (miniGameRound == 3) {
                            playerList[0].setPoints(6 + playerList[0].getPoints());
                            playerList[0].setCredits(60 + playerList[0].getCredits());
                            MainController.lastPoints = 6;
                            MainController.lastCredits = 60;
                        } else if (miniGameRound == 4) {
                            playerList[0].setPoints(8 + playerList[0].getPoints());
                            playerList[0].setCredits(80 + playerList[0].getCredits());
                            MainController.lastPoints = 8;
                            MainController.lastCredits = 80;
                        } else if (miniGameRound >= 5) {
                            playerList[0].setPoints(15 + playerList[0].getPoints());
                            playerList[0].setCredits(200 + playerList[0].getCredits());
                            MainController.lastPoints = 15;
                            MainController.lastCredits = 200;
                        }

                        if (amountOfPlayersNext == 2) {
                            gameOver = true;

                            new AlertBox().information("Spiel beendet","Es ist nur noch ein Spieler übrig. Damit ist das Spiel vorbei.");
                           new EndMiniGame().endMiniGame(playerList,buttonList,checkBoxList,checkBoxValues,cubeList,labelList,attributeLabels);
                        } else {

                            if (!(MainController.currentPlayer == amountOfPlayersCurrent)) {
                                new AlertBox().information("Nicht geschafft",  "Du hast es nicht geschafft, bist aus dem Spiel ausgschieden und hast dem Spielführer "
                                        + (playerList[0].getPoints() - pointsLeaderBefore) + "  Punkt(e) und "
                                        + (playerList[0].getCredits() - creditsLeaderBefore)
                                        + " Credits eingebracht." + "\n"
                                        + playerList[MainController.nextPlayer - 1].getName() + " ist dran.");
                            } else {

                                labelList[MainController.labelListEnum.lastRoll.ordinal()].setText("Ihr müsst auf " + (miniGameRound + 2) + " Würfel achten.");

                                int newGameLeader = 0;
                                for (int i = 1; i < playerSeparatedCurrent.length; i++) {
                                    if (!playerSeparatedCurrent[i]) {
                                        newGameLeader = i;
                                        break;
                                    }
                                }
                                if (miniGameRound >= 5) { new AlertBox().information("Nicht geschafft",  "Du hast es nicht geschafft, bist aus dem Spiel ausgschieden und hast dem Spielführer "
                                            + (playerList[0].getPoints() - pointsLeaderBefore)
                                            + "  Punkt(e) und "
                                            + (playerList[0].getCredits() - creditsLeaderBefore)
                                            + " Credits eingebracht." + "\n" + " Credits.\n"
                                            + "Es beginnt eine neue Runde, "
                                            + playerList[newGameLeader].getName() + " ist nun Spielführer."
                                            + "\nIhr habt nur noch " + (miniGameRound - endRoundCounter)
                                            + " Versuche und müsst immer noch auf 6 Würfel achten!");
                                    labelList[MainController.labelListEnum.lastRoll.ordinal()].setText("Ihr müsst auf 6 Würfel achten.");
                                } else if (miniGameRound == 4) {
                                    new AlertBox().information("Nicht geschafft","Du hast es nicht geschafft, bist aus dem Spiel ausgschieden und hast dem Spielführer "
                                            + (playerList[0].getPoints() - pointsLeaderBefore)
                                            + "  Punkt(e) und "
                                            + (playerList[0].getCredits() - creditsLeaderBefore)
                                            + " Credits eingebracht." + "\n" + "Es beginnt eine neue Runde, "
                                            + playerList[newGameLeader].getName() + " ist nun Spielführer."
                                            + "\nIhr seit nun in der Endrunde. eure Versuche verringern sich nun immer um 1."
                                            + "\nNun habt ihr " + (miniGameRound + 1)
                                            + " Versuche und müsst auf " + (miniGameRound + 2)
                                            + " Würfel achten!");
                                    labelList[MainController.labelListEnum.lastRoll.ordinal()].setText("Ihr müsst auf " + (miniGameRound + 2) + " Würfel achten.");
                                } else {

                                    new AlertBox().information("Nicht geschafft","Du hast es nicht geschafft, bist aus dem Spiel ausgschieden und hast dem Spielführer "
                                            + (playerList[0].getPoints() - pointsLeaderBefore)
                                            + "  Punkt(e) und "
                                            + (playerList[0].getCredits() - creditsLeaderBefore)
                                            + " Credits eingebracht." + "\n" + "Es beginnt eine neue Runde, "
                                            + playerList[newGameLeader].getName() + " ist nun Spielführer."
                                            + "\nNun habt ihr " + (miniGameRound + 1)
                                            + " Versuche und müsst auf " + (miniGameRound + 2)
                                            + " Würfel achten!");
                                    labelList[MainController.labelListEnum.lastRoll.ordinal()].setText("Ihr müsst auf " + (miniGameRound + 2) + " Würfel achten.");
                                }

                            }


                            playerSeparatedCurrent[MainController.currentPlayer - 1] = true;
                            chooseNextPlayer(0, 0, false);
                        }
                    }
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

        labelList[MainController.labelListEnum.triesLabel.ordinal()].setText(String.valueOf("Tries: " + MainController.tries));

        // To show the dices on the UI.
        for (int i = 0; i < cubeList.length; i++) {
            cubeList[i].setText(String.valueOf(MainGameMethods.currentDices[i]));

        }

        if (MainController.tries <= 0) {
            buttonList[MainController.buttonListEnum.rollADice.ordinal()].setDisable(true);
        }

        buttonList[MainController.buttonListEnum.nextPlayerButton.ordinal()].setDisable(false);

    }

    private void chooseNextPlayer(int pointsHolder, int creditsHolder, boolean pased) {
        if (firstRoll && pased) {
            if (MainController.currentPlayer == 1) {
                if (pairs) {
                    if (miniGameRound >= 5) {


                        new AlertBox().information("Bestanden","Du hast bestanden und bekommst "
                                + (playerList[MainController.currentPlayer - 1].getPoints() - pointsHolder)
                                + " Punkte und "
                                + (playerList[MainController.currentPlayer - 1].getCredits() - creditsHolder
                                + " Credits.")
                                + "\nIhr müsst 6 gleiche würfeln." + "\n"
                                + playerList[MainController.nextPlayer - 1].getName() + " ist an der Reihe.");
                        labelList[MainController.labelListEnum.lastRoll.ordinal()].setText("Ihr müsst 6 gleiche Würfeln");
                    } else {

                        new AlertBox().information("Bestanden","Du hast bestanden und bekommst "
                                + (playerList[MainController.currentPlayer - 1].getPoints() - pointsHolder)
                                + " Punkte und "
                                + (playerList[MainController.currentPlayer - 1].getCredits() - creditsHolder
                                + " Credits.")
                                + "\nIhr müsst " + (miniGameRound + 1) + " gleiche würfeln." + "\n"
                                + playerList[MainController.nextPlayer - 1].getName() + " ist an der Reihe.");
                    }
                    labelList[MainController.labelListEnum.lastRoll.ordinal()].setText("Ihr müsst " + (miniGameRound + 1) + " gleiche Würfeln");
                } else if (miniGameRound >= 5) {

                    new AlertBox().information("Bestanden","Du hast bestanden und bekommst "
                            + (playerList[MainController.currentPlayer - 1].getPoints() - pointsHolder) + " Punkte und "
                            + (playerList[MainController.currentPlayer - 1].getCredits() - creditsHolder + " Credits.")
                            + "\nIhr müsst eine 6er Straße würfeln." + "\n"
                            + playerList[MainController.nextPlayer - 1].getName() + " ist an der Reihe.");
                    labelList[MainController.labelListEnum.lastRoll.ordinal()].setText("Ihr müsst eine 6er Straße würfeln.");
                } else {

                    new AlertBox().information("Bestanden","Du hast bestanden und bekommst "
                            + (playerList[MainController.currentPlayer - 1].getPoints() - pointsHolder) + " Punkte und "
                            + (playerList[MainController.currentPlayer - 1].getCredits() - creditsHolder + " Credits.")
                            + "\nIhr müsst eine " + (miniGameRound + 1) + "er Straße würfeln." + "\n"
                            + playerList[MainController.nextPlayer - 1].getName() + " ist an der Reihe.");
                    labelList[MainController.labelListEnum.lastRoll.ordinal()].setText("Ihr müsst eine " + (miniGameRound + 1) + "er Straße würfeln.");
                }
            } else if (MainController.currentPlayer == amountOfPlayersCurrent) {

                int newGameLeader = 0;
                for (int i = 1; i < playerSeparatedCurrent.length; i++) {
                    if (!playerSeparatedCurrent[i]) {
                        newGameLeader = i;
                        break;
                    }
                }

                if (miniGameRound >= 5) {

                    new AlertBox().information("Bestanden","Du hast bestanden und bekommst "
                            + (playerList[MainController.currentPlayer - 1].getPoints() - pointsHolder) + " Punkte und "
                            + (playerList[MainController.currentPlayer - 1].getCredits() - creditsHolder)
                            + " Credits.\n" + "Es beginnt eine neue Runde, " + playerList[newGameLeader].getName()
                            + " ist nun Spielführer." + "\nIhr habt nur noch " + (miniGameRound  - endRoundCounter)
                            + " Versuche und müsst immer noch auf 6 Würfel achten!");
                    labelList[MainController.labelListEnum.lastRoll.ordinal()].setText("Ihr müsst auf 6 Würfel achten.");
                } else if (miniGameRound == 4) {


                    new AlertBox().information("Bestanden","Du hast bestanden und bekommst "
                            + (playerList[MainController.currentPlayer - 1].getPoints() - pointsHolder) + " Punkte und "
                            + (playerList[MainController.currentPlayer - 1].getCredits() - creditsHolder)
                            + " Credits.\n" + "Es beginnt eine neue Runde, " + playerList[newGameLeader].getName()
                            + " ist nun Spielführer."
                            + "\nIhr seit nun in der Endrunde. eure Versuche verringern sich nun immer um 1."
                            + "\nIhr habt " + (miniGameRound + 1) + " Versuche und müsst auf " + (miniGameRound + 2)
                            + " Würfel achten!");
                    labelList[MainController.labelListEnum.lastRoll.ordinal()].setText("Ihr müsst auf " + (miniGameRound + 2) + " Würfel achten.");
                } else {

                    new AlertBox().information("Bestanden","Du hast bestanden und bekommst "
                            + (playerList[MainController.currentPlayer - 1].getPoints() - pointsHolder) + " Punkte und "
                            + (playerList[MainController.currentPlayer - 1].getCredits() - creditsHolder)
                            + " Credits.\n" + "Es beginnt eine neue Runde, " + playerList[newGameLeader].getName()
                            + " ist nun Spielführer." + "\nNun habt ihr " + (miniGameRound + 1)
                            + " Versuche und müsst auf " + (miniGameRound + 2) + " Würfel achten!");
                    labelList[MainController.labelListEnum.lastRoll.ordinal()].setText("Ihr müsst auf " + (miniGameRound + 2) + " Würfel achten.");
                }
            } else {

                new AlertBox().information("Bestanden","Du hast bestanden und bekommst "
                        + (playerList[MainController.currentPlayer - 1].getPoints() - pointsHolder) + " Punkte und "
                        + (playerList[MainController.currentPlayer - 1].getCredits() - creditsHolder) + " Credits.\n"
                        + playerList[MainController.nextPlayer - 1].getName() + " ist an der Reihe.");
            }
        }

        if (roundCounter == amountOfPlayersCurrent) {
            miniGameRound++;
            if (miniGameRound == 5) {
                endRoundCounter = 0;
            }
            if (miniGameRound > 5) {
                endRoundCounter += 2;
            }
            roundCounter = 0;
            resetPlayerList(stateFirstPlayer);
            amountOfPlayersCurrent = amountOfPlayersNext;

            for (int i = 0; i < playerListNext.length; i++) {
                playerList[i] = playerListNext[i];
                playerSeparatedCurrent[i] = playerSeparatedNext[i];
            }
        }
        roundCounter++;

        if (MainController.currentPlayer == amountOfPlayersNext) {

            MainController.currentPlayer = 1;
            labelList[MainController.labelListEnum.currentPlayerLabel.ordinal()].setText(playerList[MainController.currentPlayer - 1].getName() + "'s turn. Good luck!");
            MainController.nextPlayer = MainController.currentPlayer + 1;
        } else {
            MainController.currentPlayer = MainController.nextPlayer;
            labelList[MainController.labelListEnum.currentPlayerLabel.ordinal()].setText(playerList[MainController.currentPlayer - 1].getName() + "'s turn. Good luck!");
            if (MainController.currentPlayer == amountOfPlayersNext) {
                resetPlayerList(true);
                MainController.nextPlayer = 1;
                MainController.holdNextPlayer = playerList[MainController.nextPlayer - 1];
                MainController.holdNextPlayer.setPlayerPosition(MainController.nextPlayer);
            } else {
                MainController.nextPlayer = MainController.currentPlayer + 1;
                MainController.holdNextPlayer = playerList[MainController.nextPlayer - 1];
                MainController.holdNextPlayer.setPlayerPosition(MainController.nextPlayer);
            }
        }

        // To reset the game when one player has finished his turn.
        if (miniGameRound > 5) {
            MainController.tries = miniGameRound - endRoundCounter;

            if (MainController.tries < 1) {
                MainController.tries = 1;
            }
        } else {
            MainController.tries = miniGameRound;
        }

        labelList[MainController.labelListEnum.roundsLabel.ordinal()].setText("Round: " + String.valueOf(miniGameRound));
        labelList[MainController.labelListEnum.triesLabel.ordinal()].setText("Versuche: " + String.valueOf(MainController.tries));

        for (CheckBox check : checkBoxList) {
            check.setSelected(false);
        }

        for (Label cube : cubeList) {
            cube.setText("-");

        }

        for (int i = 0; i < playerList.length; i++) {
            playerList[i].setRank(i + 1);
        }

        setAttributeLabels();

        buttonList[MainController.buttonListEnum.rollADice.ordinal()].setDisable(false);
        buttonList[MainController.buttonListEnum.nextPlayerButton.ordinal()].setDisable(true);
    }

    private void setAttributeLabels() {

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
                if (!gameOver) {
                    if (!playerSeparatedCurrent[i]) {
                        attributeLabels[i][j].setText(String.valueOf(i + 1));
                        j++;
                    } else {
                        attributeLabels[i][j].setText("OUT");
                        j++;

                    }
                } else {
                    attributeLabels[i][j].setText(String.valueOf(playerList[i].getRank()));
                    j++;
                }
               j= new SetSkillAttributes().setSkillAttributes(playerList,attributeLabels,i,j);
            }
        }
    }

    private boolean firstRoll() {

        gameOver = false;
        stateFirstPlayer = false;

        int checkboxCounter = 0;
        int[] trueCheckboxes = new int[checkBoxValues.length];

        pairs = false;
        firstRoll = false;

        // for (int i = 0; i < checkBoxList.length; i++) {
        // checkBoxValues[i] = checkBoxList[i].isSelected();
        // }

        for (int i = 0; i < checkBoxValues.length; i++) {
            if (checkBoxValues[i]) {
                trueCheckboxes[checkboxCounter] = i;
                checkboxCounter++;
            }
            if (checkboxCounter == miniGameRound + 1 || checkboxCounter == 6) {
                break;
            }
        }
        int[] dices = new int[checkboxCounter];

        for (int i = 0; i < checkboxCounter; i++) {
            dices[i] = MainGameMethods.currentDices[trueCheckboxes[i]];
        }

        new SortDices().sortDices(dices);

        if (checkboxCounter == miniGameRound + 1 || checkboxCounter == 6) {

            for (int i = 0; i < checkboxCounter - 1; ) {
//
                if (dices[i] == dices[i + 1]) {
                    i++;
                } else {
                    for (int j = 0; j < checkboxCounter - 1; ) {
                        i = checkboxCounter;
//
                        if (dices[j] + 1 == dices[j + 1]) {
                            j++;
                        } else {
                            break;
                        }
                        if (j == checkboxCounter - 1) {
                            firstRoll = true;
                        }
                    }

                }
                if (i == checkboxCounter - 1) {
                    pairs = true;
                    firstRoll = true;
                }

            }
        } else {

            new AlertBox().confirm("Warunung","Du hälst zu wenige Würfel, Aufgeben?");

            if (AlertBox.confirm) {

                for (int i = 1; i < amountOfPlayersNext; i++) {
                    if (miniGameRound == 1) {
                        playerList[i].setPoints(1 + playerList[i].getPoints());
                        playerList[i].setCredits(10 + playerList[i].getCredits());
                    } else if (miniGameRound == 2) {
                        playerList[i].setPoints(3 + playerList[i].getPoints());
                        playerList[i].setCredits(30 + playerList[i].getCredits());
                    } else if (miniGameRound == 3) {
                        playerList[i].setPoints(6 + playerList[i].getPoints());
                        playerList[i].setCredits(60 + playerList[i].getCredits());
                    } else if (miniGameRound == 4) {
                        playerList[i].setPoints(8 + playerList[i].getPoints());
                        playerList[i].setCredits(80 + playerList[i].getCredits());
                    } else if (miniGameRound >= 5) {
                        playerList[i].setPoints(15 + playerList[i].getPoints());
                        playerList[i].setCredits(200 + playerList[i].getCredits());
                    }
                }

                if (amountOfPlayersNext == 2) {
                    gameOver = true;

                    new AlertBox().information("Spiel beendet","Es ist nur noch 1 Spieler übrig. Damit ist das Spiel vorbei.");
                   new EndMiniGame().endMiniGame(playerList,buttonList,checkBoxList,checkBoxValues,cubeList,labelList,attributeLabels);
                } else {
                    if (miniGameRound >= 5) {

                        new AlertBox().information("Der Game Leader hat verloren","Der Spielführer hat verloren!! Jeder Spieler bekommt nun die Punkte dieser Runde gutgeschrieben."
                                + "\n Es beginnt eine neue Runde, "
                                + playerList[MainController.nextPlayer - 1].getName() + " ist nun Spielführer."
                                + "\nIhr habt nur noch " + (miniGameRound - endRoundCounter)
                                + " Versuche und müsst immer noch auf 6 Würfel achten!");
                        labelList[MainController.labelListEnum.lastRoll.ordinal()].setText("Ihr müsst auf 6 Würfel achten.");

                    } else if (miniGameRound == 4) {
                        endRoundCounter = 0;
                        new AlertBox().information("Game Leader hat verloren","Der Spielführer hat verloren!! Jeder Spieler bekommt nun die Punkte dieser Runde gutgeschrieben."
                                + "\n Es beginnt eine neue Runde, "
                                + playerList[MainController.nextPlayer - 1].getName() + " ist nun Spielführer."
                                + "\nIhr seit nun in der Endrunde. eure Versuche verringern sich nun immer um 1."
                                + "\nNun habt ihr " + (miniGameRound + 1) + " Versuche und müsst auf "
                                + (miniGameRound + 2) + " Würfel achten!");
                        labelList[MainController.labelListEnum.lastRoll.ordinal()].setText("Ihr müsst auf " + (miniGameRound + 2) + " Würfel achten.");
                    } else {
                        new AlertBox().information("Game Leader hat verloren","Der Spielführer hat verloren!! Jeder Spieler bekommt nun die Punkte dieser Runde gutgeschrieben."
                                + "\n Es beginnt eine neue Runde, "
                                + playerList[MainController.nextPlayer - 1].getName() + " ist nun Spielführer."
                                + "\nNun habt ihr " + (miniGameRound + 1) + " Versuche und müsst auf "
                                + (miniGameRound + 2) + " Würfel achten!");
                        labelList[MainController.labelListEnum.lastRoll.ordinal()].setText("Ihr müsst auf " + (miniGameRound + 2) + " Würfel achten.");
                    }

                    for (CheckBox check : checkBoxList) {
                        check.setSelected(false);
                    }
                    for (Label cube : cubeList) {
                        cube.setText("-");
                    }

                    playerSeparatedCurrent[0] = true;

                    buttonList[MainController.buttonListEnum.nextPlayerButton.ordinal()].setDisable(true);
                    buttonList[MainController.buttonListEnum.rollADice.ordinal()].setDisable(false);

                    roundCounter = amountOfPlayersCurrent;
                    MainController.currentPlayer = amountOfPlayersCurrent - 1;

                    stateFirstPlayer = true;

                    chooseNextPlayer(0, 0, false);

                }
            }
        }
        return firstRoll;

    }

    private void resetPlayerList(boolean first) {

        Player holdPlayer;
        if (first) {
            for (int i = amountOfPlayersCurrent - 2; i >= 0; i--) {

                if (playerSeparatedCurrent[i]) {

                    if (i != amountOfPlayersCurrent - 1) {

                        holdPlayer = playerListNext[i];

                        for (int j = i; j < amountOfPlayersNext - 1; j++) {
                            playerListNext[j] = playerListNext[j + 1];
                            if (j == amountOfPlayersNext - 2) {
                                playerListNext[j + 1] = holdPlayer;
                            }
                        }

                        playerSeparatedNext[amountOfPlayersNext - 1] = true;

                        amountOfPlayersNext = 0;

                        for (int k = 0; k < playerList.length; k++) {
                            if (!playerSeparatedNext[k]) {
                                amountOfPlayersNext++;
                            } else {
                                break;
                            }
                        }
                    } /*else if (amountOfPlayersCurrent == 2) {
                        System.out.println("Das Spiel ist vorbei, " + playerList[0].getName()
                                + " hat gewonnen. Und bekommt eine Belohnung über...");

                        // Die Buttons disable setzen und den end game Button einblenden lassen.
                    }*/
                }
            }
        } else {

            if (playerSeparatedCurrent[amountOfPlayersCurrent - 1]) {
                holdPlayer = playerListNext[amountOfPlayersNext - 1];
                for (int i = amountOfPlayersNext - 1; i < amountOfPlayersCurrent - 1; i++) {
                    playerListNext[i] = playerListNext[i + 1];
                    if (i == amountOfPlayersCurrent - 2) {
                        playerListNext[amountOfPlayersCurrent - 1] = holdPlayer;
                    }
                }
                playerSeparatedNext[amountOfPlayersNext - 1] = true;
                amountOfPlayersNext--;
            }

            holdPlayer = playerList[0];
            for (int j = 0; j < amountOfPlayersNext; j++) {
                if (j != amountOfPlayersNext - 1) {
                    playerListNext[j] = playerListNext[j + 1];
                } else {
                    playerListNext[j] = holdPlayer;
                }
            }
        }
    }

}
