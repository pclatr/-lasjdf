package minigames;


import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import main.MainGameMethods;
import main.Player;
import main.standardMethods.EndMiniGame;
import main.standardMethods.SetSkillAttributes;
import main.standardMethods.SortDices;
import main.alertBox.AlertBox;
import main.standardMethods.SortPlayerList;
import userInterface.MainController;

public class MiniGameThreeInterfaceController {

    MainGameMethods mainGameMethods = new MainGameMethods();

    private boolean firstRoll = false;

    private int roundCounter;
    private int miniGameRound = 1;
    private int triesGameLeader;
    private int amountOfDicesInStreetGameLeader = 1;
    private int amountOfPairsInPairstreet = 1;
    private int pairInPairStreet;
    private int outCounter = 0;

    private boolean gameLeaderHasRolled = false;
    private boolean street;
    private boolean pairStreet;
    private boolean pairStreetWithSinglePair;
    private boolean pairs;
    private boolean nextRound = false;
    private boolean gameOver = false;

    private Player[] playerList;

    public Player[] playerListMiniGame;

    private int[] amountOfSameDices = new int[3];
    private int[] dicesGameLeader;
    private int[] playerEarnedPoint;

    private Label[][] attributeLabels;
    private Label[] cubeList;
    private Label[] labelList;

    private CheckBox[] checkBoxList;

    private Button[] buttonList;

    private boolean[] checkBoxValues;

    public MiniGameThreeInterfaceController() {

    }

    public MiniGameThreeInterfaceController(Player[] playerList, Label[] cubes, Label[] labelList, Label[][] attributeLabels, CheckBox[] checkBoxList, Button[] buttonList) {
        this.playerList = playerList;
        this.cubeList = cubes;
        this.labelList = labelList;
        this.attributeLabels = attributeLabels;
        this.checkBoxList = checkBoxList;
        this.buttonList = buttonList;
        checkBoxValues = new boolean[checkBoxList.length];
    }

    public void startGame() {

        new AlertBox().information("Los geht's","Mini Game 3 wurde ausgewählt.");
        playerEarnedPoint = new int[playerList.length];
        playerListMiniGame = new Player[playerList.length];
        for (int i = 0; i < playerEarnedPoint.length; i++) {
            playerEarnedPoint[i] = 0;
        }
        for (int i = 0; i < playerList.length; i++) {
            attributeLabels[i][MainController.attributeLabelsEnum.playerOneRank.ordinal()].setText(String.valueOf(playerEarnedPoint[i]));
            playerListMiniGame[i] = playerList[i];
            playerListMiniGame[i].setRank(0);
        }
        //labelList[MainController.labelListEnum.playerRanksTabel.ordinal()].setText("Earned Points");
        playerListMiniGame[0].setRank(1);
        attributeLabels[playerListMiniGame[0].getPlayerPosition() - 1][Player.playerAttributesEnum.rank.ordinal()].setText("GameLeader");
        labelList[MainController.labelListEnum.roundsLabel.ordinal()].setText("MiniGame Round: 1");
        labelList[MainController.labelListEnum.currentPlayerLabel.ordinal()].setText("Player " + playerListMiniGame[0].getName() + "'s turn. Good Luck!");
        labelList[MainController.labelListEnum.triesLabel.ordinal()].setText("GameLeader Tries: 0");
        labelList[MainController.labelListEnum.lastRoll.ordinal()].setText("Task: Wait for GameLeader.");
        for (CheckBox check : checkBoxList) {
            check.setSelected(false);
        }
        for (Label cubeLabel : cubeList) {
            cubeLabel.setText("-");
        }
        buttonList[MainController.buttonListEnum.nextPlayerButton.ordinal()].setDisable(true);
        buttonList[MainController.buttonListEnum.rollADice.ordinal()].setDisable(false);
    }

    public void rollDices() {
        if (!firstRoll) {
            firstRoll();
        } else {
            if (MainController.tries == triesGameLeader) {
                for (CheckBox checkBox : checkBoxList) {
                    checkBox.setSelected(false);
                }
            }
            for (int i = 0; i < checkBoxValues.length; i++) {
                checkBoxValues[i] = checkBoxList[i].isSelected();
            }
            mainGameMethods.generateDices(checkBoxValues);
            for (int i = 0; i < cubeList.length; i++) {
                cubeList[i].setText(String.valueOf(MainGameMethods.currentDices[i]));
            }
            labelList[MainController.labelListEnum.triesLabel.ordinal()].setText("Tries: " + MainController.tries);
            buttonList[MainController.buttonListEnum.nextPlayerButton.ordinal()].setDisable(false);
            if (MainController.tries == 0) {
                buttonList[MainController.buttonListEnum.rollADice.ordinal()].setDisable(true);
            }
        }
    }

    private void firstRoll() {
        if (!gameLeaderHasRolled) {
            triesGameLeader = 0;
            for (CheckBox checkBox : checkBoxList) {
                checkBox.setSelected(false);
            }
        }
        for (int i = 0; i < checkBoxValues.length; i++) {
            checkBoxValues[i] = checkBoxList[i].isSelected();

        }
        gameLeaderHasRolled = true;
        MainController.tries = 2;
        mainGameMethods.generateDices(checkBoxValues);
        triesGameLeader++;
        for (int i = 0; i < cubeList.length; i++) {
            cubeList[i].setText(String.valueOf(MainGameMethods.currentDices[i]));
        }
        labelList[MainController.labelListEnum.triesLabel.ordinal()].setText("GameLeader Tries: " + triesGameLeader);
        buttonList[MainController.buttonListEnum.nextPlayerButton.ordinal()].setDisable(false);
    }

    public void nextPlayer() {
        new SortDices().sortDices(MainGameMethods.currentDices);
        if (!firstRoll) {
            int trueCounter = 0;
            for (int i = 0; i < checkBoxValues.length; i++) {
                checkBoxValues[i] = checkBoxList[i].isSelected();
                if (checkBoxValues[i]) {
                    trueCounter++;
                }
            }
            if (trueCounter > 1) {
                gameLeaderHasRolled = false;
                if (mainGameMethods.checkHoldDices(checkBoxValues, MainGameMethods.currentDices)) {
                    int counter = 0;
                    for (int i = 0; i < checkBoxValues.length; i++) {
                        if (checkBoxValues[i]) {
                            MainGameMethods.currentDices[counter] = MainGameMethods.currentDices[i];
                            counter++;
                        }
                    }
                    dicesGameLeader = new int[counter];
                    for (int i = 0; i < dicesGameLeader.length; i++) {
                        dicesGameLeader[i] = MainGameMethods.currentDices[i];
                    }
                    street = false;
                    pairStreet = false;
                    pairStreetWithSinglePair = false;
                    pairs = false;
                    MainController.tries = triesGameLeader;
                    checkRollResult(dicesGameLeader);
                }
            } else {
                new AlertBox().information("Achtung", "Du hälst nicht genug Würfel!");
            }
        } else {
            checkRollResult(MainGameMethods.currentDices);
        }
    }

    private void checkRollResult(int[] checkedDices) {
        if (!firstRoll) {
            if (MainController.currentPlayer++ == playerListMiniGame.length - 1) {
                MainController.nextPlayer = 1;
            } else {
                MainController.currentPlayer = MainController.nextPlayer++;
            }
            if (checkStreet(checkedDices) != 1) {
                street = true;
                new AlertBox().information("Game Leader", "Der Game Leader hat eine " + amountOfDicesInStreetGameLeader + "er Straße gewürfelt.\n" +
                        playerListMiniGame[MainController.currentPlayer - 1].getName() + " ist dran.");
                labelList[MainController.labelListEnum.lastRoll.ordinal()].setText("Task: " + amountOfDicesInStreetGameLeader + "er Straße.");
            } else {
                int result = checkPairStreet(checkedDices);
                if (result != 1) {
                    switch (result) {
                        case 2:
                            pairStreet = true;
                            new AlertBox().information("Game Leader", "Der Game Leader hat eine Paarstraße aus " + amountOfPairsInPairstreet + " Paaren gewürfelt.\n"
                                    + playerListMiniGame[MainController.currentPlayer - 1].getName() + " ist dran.");
                            labelList[MainController.labelListEnum.lastRoll.ordinal()].setText("Task: " + amountOfPairsInPairstreet + "er Paarstraße.");
                            break;
                        case 3:
                            pairStreet = true;
                            new AlertBox().information("Game Leader", "Der Game Leader hat eine Paarstraße aus " + amountOfPairsInPairstreet + " Paaren gewürfelt.\n"
                                    + playerListMiniGame[MainController.currentPlayer - 1].getName() + " ist dran.");
                            labelList[MainController.labelListEnum.lastRoll.ordinal()].setText("Task: " + amountOfPairsInPairstreet + "er Paarstraße.\n"
                                    + playerListMiniGame[MainController.currentPlayer - 1].getName() + " ist dran.");
                            break;
                        case 4:
                            pairStreetWithSinglePair = true;
                            new AlertBox().information("Game Leader", "Der Game Leader hat eine Paarstraße aus 2 Paaren und 1 weiteres Paar gewürfelt.\n"
                                    + playerListMiniGame[MainController.currentPlayer - 1].getName() + " ist dran.");
                            labelList[MainController.labelListEnum.lastRoll.ordinal()].setText("Task: 2er Paarstraße und ein weiteres einzelnes Paar.");
                            break;
                    }
                } else {
                    int[] pairValues = checkPairs(checkedDices);
                    if (pairValues[0] != 1) {
                        pairs = true;
                        if (pairValues[1] == 1) {
                            new AlertBox().information("Game Leader", "Der Game Leader hat " + pairValues[0] + " gleiche Gewürfelt.\n"
                                    + playerListMiniGame[MainController.currentPlayer - 1].getName() + " ist dran.");
                            labelList[MainController.labelListEnum.lastRoll.ordinal()].setText("Task: " + pairValues[0] + " gleiche.");
                        } else if (pairValues[2] == 1) {
                            new AlertBox().information("Game Leader", "Der Game Leader hat " + pairValues[0] + " gleiche und " + pairValues[1] + " gleiche gewürfelt.\n"
                                    + playerListMiniGame[MainController.currentPlayer - 1].getName() + " ist dran.");
                            labelList[MainController.labelListEnum.lastRoll.ordinal()].setText("Task: Einmal " + pairValues[0] + " und einmal " + pairValues[1] + " gleiche.");
                        } else {
                            new AlertBox().information("Game Leader", "Der Game Leader hat 3-mal 2 gleiche gewürfelt.\n"
                                    + playerListMiniGame[MainController.currentPlayer - 1].getName() + " ist dran.");
                            labelList[MainController.labelListEnum.lastRoll.ordinal()].setText("Task: 3-mal 2 gleiche.");
                        }
                    }
                }
            }
            firstRoll = true;
            for (int i = 0; i < cubeList.length; i++) {
                cubeList[i].setText("-");
                checkBoxList[i].setSelected(false);
                checkBoxValues[i] = checkBoxList[i].isSelected();
            }
            MainController.tries = triesGameLeader;
            labelList[MainController.labelListEnum.triesLabel.ordinal()].setText("Tries: " + triesGameLeader);
            labelList[MainController.labelListEnum.currentPlayerLabel.ordinal()].setText("Player " + playerListMiniGame[MainController.currentPlayer - 1].getName() + "'s turn. Good Luck!");
            buttonList[MainController.buttonListEnum.rollADice.ordinal()].setDisable(false);
            buttonList[MainController.buttonListEnum.nextPlayerButton.ordinal()].setDisable(true);
        } else {
            if (street) {
                int playerResult = checkStreet(checkedDices);
                if (amountOfDicesInStreetGameLeader == playerResult) {
                    evaluatePoints(true, amountOfDicesInStreetGameLeader, playerResult);
                } else {
                    evaluatePoints(false, amountOfDicesInStreetGameLeader, playerResult);
                }
            } else if (pairStreet || pairStreetWithSinglePair) {
                int playerResult = checkPairStreet(checkedDices);
                if (amountOfPairsInPairstreet == playerResult) {
                    evaluatePoints(true, amountOfPairsInPairstreet, playerResult);
                } else {
                    evaluatePoints(false, amountOfPairsInPairstreet, playerResult);
                }
            } else {
                int[] checkPairValues = checkPairs(checkedDices);
                boolean[] selected = new boolean[3];
                int checkedCounter = 0;
                for (int i = 0; i < amountOfSameDices.length; i++) {
                    if (amountOfSameDices[i] == 1) {
                        selected[i] = true;
                        checkedCounter++;//
                    }
                }
                int gameLeaderResult = 3 - checkedCounter;
                int playerResult = 0;
                for (int i = 0; i < checkPairValues.length; i++) {
                    if (checkPairValues[i] != 1) {
                        playerResult++;
                    }
                }
                if (!(playerResult < gameLeaderResult)) {
                    for (int i = 0; i < checkPairValues.length; i++) {
                        for (int j = 0; j < checkPairValues.length; j++) {//Optimieren
                            if (checkPairValues[i] != 1) {
                                if (checkedCounter == 3) {
                                    i = checkPairValues.length;
                                    break;
                                }
                                if (checkPairValues[i] == amountOfSameDices[j] && !selected[j]) {
                                    selected[j] = true;
                                    checkedCounter++;
                                    break;
                                }
                            }
                        }
                    }
                }
                if (checkedCounter == 3) {
                    evaluatePoints(true, gameLeaderResult, playerResult);
                } else {
                    evaluatePoints(false, gameLeaderResult, playerResult);
                }
            }
        }
    }

    private int checkStreet(int[] checkedDices) {
        int[] firstStreet = {0, 0, 0, 0, 0, 0};
        int[] secondStreet = {0, 0, 0, 0, 0, 0};
        if (!firstRoll) {
            for (int i = 0; i < checkedDices.length - 1; i++) {
                if (checkedDices[i] + 1 == checkedDices[i + 1]) {
                    amountOfDicesInStreetGameLeader++;
                } else {
                    break;
                }
            }
            return amountOfDicesInStreetGameLeader;
        } else {
            int firstStreetLength = 1;
            int secondStreetLength = 1;
            boolean checkSecondStreet = false;
            int toCheckDiceSecondStreet = 0;
            int toCheckDiceFirstStreet = checkedDices[0];
            for (int j = 1; j < checkedDices.length; j++) {
                if (toCheckDiceFirstStreet + 1 == checkedDices[j]) {
                    if (firstStreet[0] == 0) {
                        firstStreet[0] = toCheckDiceFirstStreet;
                    }
                    firstStreet[firstStreetLength] = checkedDices[j];
                    firstStreetLength++;
                    toCheckDiceFirstStreet = checkedDices[j];
                } else if (toCheckDiceFirstStreet == checkedDices[j]) {
                    //aussitzen
                } else if (checkSecondStreet) {
                    if (toCheckDiceSecondStreet + 1 == checkedDices[j]) {
                        if (secondStreet[0] == 0) {
                            secondStreet[0] = toCheckDiceSecondStreet;
                        }
                        secondStreet[secondStreetLength] = checkedDices[j];
                        secondStreetLength++;
                        toCheckDiceSecondStreet = checkedDices[j];
                    }
                } else {
                    toCheckDiceSecondStreet = checkedDices[j];
                    checkSecondStreet = true;
                }
            }
            if (firstStreetLength >= secondStreetLength || firstStreetLength == amountOfDicesInStreetGameLeader) {
                MainGameMethods.currentDices = firstStreet;
                return firstStreetLength;
            } else {
                MainGameMethods.currentDices = secondStreet;
                return secondStreetLength;
            }
        }
    }

    private int checkPairStreet(int[] checkedDices) {
        if (checkedDices.length % 2 == 0) {
            if (!firstRoll) {
                for (int i = 0; i < checkedDices.length - 2; i += 2) {
                    if (checkedDices[i] == checkedDices[i + 1] && checkedDices[i + 1] + 1 == checkedDices[i + 2] && checkedDices[i + 2] == checkedDices[i + 3]) {
                        amountOfPairsInPairstreet++;
                    } else if (checkedDices.length == 6) {
                        switch (amountOfPairsInPairstreet) {
                            case 1:
                                if (checkedDices[i] == checkedDices[i + 1]) {
                                    amountOfPairsInPairstreet += 2;
                                } else {
                                    return 1;
                                }
                                break;
                            case 2:
                                if (checkedDices[i] == checkedDices[i + 1]) {
                                    return amountOfPairsInPairstreet + 2;
                                } else {
                                    return 1;
                                }
                            default:
                                return 1;
                        }
                    } else {
                        return 1;
                    }
                }
                return amountOfPairsInPairstreet;
            } else {
                boolean startPairStreet = false;
                boolean foundPair = false;
                int counter = 1;
                for (int i = 0; i < MainGameMethods.currentDices.length - 3; i += 2) {
                    switch (amountOfPairsInPairstreet) {
                        case 2:
                            if (MainGameMethods.currentDices[i] == MainGameMethods.currentDices[i + 1] &&
                                    MainGameMethods.currentDices[i + 1] + 1 == MainGameMethods.currentDices[i + 2] && checkedDices[i + 2] == checkedDices[i + 3]) {
                                for (int j = 0; j < 4; j++) {
                                    MainGameMethods.currentDices[j] = MainGameMethods.currentDices[i];
                                }
                                return ++counter;
                            }
                            i--;
                            break;
                        case 3:
                            if (MainGameMethods.currentDices[i] == MainGameMethods.currentDices[i + 1] &&
                                    MainGameMethods.currentDices[i + 1] + 1 == MainGameMethods.currentDices[i + 2] && checkedDices[i + 2] == checkedDices[i + 3]) {
                                counter++;
                            } else {
                                return counter;
                            }
                            break;
                        case 4:
                            if (MainGameMethods.currentDices[i] == MainGameMethods.currentDices[i + 1] &&
                                    MainGameMethods.currentDices[i + 1] + 1 == MainGameMethods.currentDices[i + 2] && checkedDices[i + 2] == checkedDices[i + 3]) {
                                MainGameMethods.currentDices[0] = MainGameMethods.currentDices[i];
                                if (!startPairStreet) {
                                    startPairStreet = true;
                                    counter++;
                                } else {
                                    counter++;
                                }
                            } else if (MainGameMethods.currentDices[i] == MainGameMethods.currentDices[i + 1]) {
                                MainGameMethods.currentDices[1] = MainGameMethods.currentDices[i];
                                if (foundPair) {
                                    return counter;
                                } else {
                                    switch (counter) {
                                        case 1:
                                            foundPair = true;
                                            pairInPairStreet = MainGameMethods.currentDices[i];
                                            counter += 2;
                                            break;
                                        case 2:
                                            counter += 2;
                                            return counter;

                                        default:
                                            return counter;
                                    }
                                }
                            } else {
                                return counter;
                            }
                            break;
                    }
                }
                return counter;
            }
        } else {
            return 1;
        }
    }

    private int[] checkPairs(int[] checkedDices) {
        if (!firstRoll) {
            for (int i = 0; i < amountOfSameDices.length; i++) {
                amountOfSameDices[i] = 1;
            }
            int amountOfSameDicesCounter = 0;

            for (int i = 0; i < checkedDices.length - 1; i++) {
                if (checkedDices[i] == checkedDices[i + 1]) {
                    amountOfSameDices[amountOfSameDicesCounter]++;
                } else if (amountOfSameDices[amountOfSameDicesCounter] > 1) {
                    amountOfSameDicesCounter++;
                }
            }
            return amountOfSameDices;
        } else {
            int[] checkPairsValues = {1, 1, 1};
            int checkPairsValuesCounter = 0;
            for (int i = 0; i < MainGameMethods.currentDices.length - 1; i++) {
                if (MainGameMethods.currentDices[i] == MainGameMethods.currentDices[i + 1]) {
                    checkPairsValues[checkPairsValuesCounter]++;
                    MainGameMethods.currentDices[checkPairsValuesCounter] = MainGameMethods.currentDices[i];
                } else if (checkPairsValues[checkPairsValuesCounter] > 1) {
                    checkPairsValuesCounter++;
                }
            }
            return checkPairsValues;
        }
    }

    private void evaluatePoints(boolean pased, int gameLeaderResult, int playerResult) {//1 Punkte darf nicht möglich sein.
        if (pased) {
            if (street) {
                chooseNextPlayer(true, evaluatePointsStreet(playerResult), gameLeaderResult, playerResult);
            } else if (pairStreet || pairStreetWithSinglePair) {
                chooseNextPlayer(true, evaluatePointsPairstreet(playerResult), gameLeaderResult, playerResult);
            } else {
                chooseNextPlayer(true, evaluatePointsPairs(), gameLeaderResult, playerResult);
            }
        } else {
            chooseNextPlayer(false, evaluatePointsGameLeader(gameLeaderResult), gameLeaderResult, playerResult);
        }
    }

    private int evaluatePointsStreet(int playerResult) {
        int increaser = MainController.tries;
        int gainsFactor = 0;
        for (int i = 0; i < playerResult; i++) {
            gainsFactor += MainGameMethods.currentDices[i];
        }
        boolean secondFactor;
        switch (increaser) {
            case 0:
                break;
            case 1:
                if (MainGameMethods.currentDices[1] == 2) {
                    gainsFactor *= 2;
                } else {
                    gainsFactor += 2;
                }
                break;
            case 2:
                secondFactor = true;
                if (MainGameMethods.currentDices[0] != 1) {
                    secondFactor = false;
                }
                if (secondFactor) {
                    switch (playerResult) {
                        case 2:
                            gainsFactor += 8;
                            break;
                        case 3:
                            gainsFactor += 10;
                            break;
                        case 4:
                            gainsFactor += 2;
                            gainsFactor *= 3;
                            break;
                        default:
                            gainsFactor += 6;
                            gainsFactor *= 2;
                    }
                } else {
                    gainsFactor += 6;
                }
                break;
            case 3:
                secondFactor = true;
                if (MainGameMethods.currentDices[0] != 1) {
                    secondFactor = false;
                }
                if (secondFactor) {
                    switch (playerResult) {
                        case 2:
                            gainsFactor += 10;
                            break;
                        case 3:
                            gainsFactor += 12;
                            break;
                        case 4:
                            gainsFactor += 4;
                            gainsFactor *= 3;
                            break;
                        default:
                            gainsFactor += 10;
                            gainsFactor *= 2;

                    }
                } else {
                    gainsFactor += 8;
                }
                break;
            case 4:
                secondFactor = true;
                if (MainGameMethods.currentDices[0] != 1) {
                    secondFactor = false;
                }
                if (secondFactor) {
                    switch (playerResult) {
                        case 2:
                            gainsFactor += 14;
                            break;
                        case 3:
                            gainsFactor += 16;
                            break;
                        case 4:
                            gainsFactor += 5;
                            gainsFactor *= 3;
                            break;
                        default:
                            gainsFactor += 14;
                            gainsFactor *= 2;
                    }
                } else {
                    gainsFactor += 12;
                }
                break;
            default:
                gainsFactor += increaser;
                gainsFactor *= increaser;
        }
        return gainsFactor;
    }

    private int evaluatePointsPairstreet(int playerResult) {
        int increaser = MainController.tries;
        int gainsFactor = 0;
        switch (playerResult) {
            case 2:
                switch (MainGameMethods.currentDices[0]) {
                    case 1:
                        gainsFactor += (2 * increaser) + 10;
                        break;
                    case 2:
                        gainsFactor += (2 * increaser) + 15;
                        break;
                    case 3:
                        gainsFactor += ((2 * increaser) * 3);
                        break;
                    case 4:
                        gainsFactor += ((2 * increaser) * 4);
                        break;
                    case 5:
                        gainsFactor += ((2 * increaser) * 5);
                        break;
                }
                break;
            case 3:
                switch (MainGameMethods.currentDices[0]) {
                    case 1:
                        gainsFactor += (3 * increaser) + 10;
                        break;
                    case 2:
                        gainsFactor += (3 * increaser) + 15;
                        break;
                    case 3:
                        gainsFactor += 4;
                        gainsFactor *= (increaser * 3);
                        break;
                    case 4:
                        gainsFactor += 5;
                        gainsFactor *= (increaser * 4);
                        break;
                }
                break;
            case 4:
                switch (MainGameMethods.currentDices[0]) {
                    case 1:
                        gainsFactor += (MainGameMethods.currentDices[1] * increaser) + 10;
                        break;
                    case 2:
                        gainsFactor += (MainGameMethods.currentDices[1] * increaser) + 15;
                        break;
                    case 3:
                        gainsFactor += 4;
                        gainsFactor *= (increaser * MainGameMethods.currentDices[1]);
                        break;
                    case 4:
                        gainsFactor += 5;
                        gainsFactor *= (increaser * MainGameMethods.currentDices[1]);
                        break;
                    case 5:
                        gainsFactor += ((2 * increaser) * MainGameMethods.currentDices[1]);
                        break;
                }
                break;
        }
        return gainsFactor;
    }

    private int evaluatePointsPairs() {
        int gainsFactor;
        int increaser = MainController.tries;
        switch (increaser) {
            case 0:
                gainsFactor = MainGameMethods.currentDices[0] + MainGameMethods.currentDices[1] + MainGameMethods.currentDices[2];
                break;
            case 1:
                gainsFactor = MainGameMethods.currentDices[0] * 2 + MainGameMethods.currentDices[1] + MainGameMethods.currentDices[2];
                break;
            case 2:
                gainsFactor = MainGameMethods.currentDices[0] * 2 + MainGameMethods.currentDices[1] * 2 + MainGameMethods.currentDices[2];
                break;
            case 3:
                gainsFactor = MainGameMethods.currentDices[0] * 2 + MainGameMethods.currentDices[1] * 2 + MainGameMethods.currentDices[2] * 2;
                break;
            default:
                gainsFactor = (increaser - 2) * (MainGameMethods.currentDices[0] * 2 + MainGameMethods.currentDices[1] * 2 + MainGameMethods.currentDices[2] * 2);
                break;
        }
        return gainsFactor;
    }

    private int evaluatePointsGameLeader(int gameLeaderResult) {
        int gainsFactor = 0;
        int sum = 0;
        for (int dice : dicesGameLeader) {
            sum += dice;
        }
        if (street) {
            switch (triesGameLeader) {
                case 1:
                    if (amountOfDicesInStreetGameLeader == 6) {
                        gainsFactor = 50;
                    } else {
                        gainsFactor = (int) (sum / 3);
                    }
                    break;
                case 2:
                    gainsFactor = (int) (sum / 2);
                    break;
                case 3:
                    gainsFactor = sum;
                    break;
                case 4:
                    gainsFactor = sum + 4;
                    break;
                default:
                    gainsFactor = sum + triesGameLeader * 2;
            }
        } else if (pairStreet || pairStreetWithSinglePair) {
            switch (triesGameLeader) {
                case 1:
                    if (dicesGameLeader[0] == 1 && (dicesGameLeader[dicesGameLeader.length - 1] == 3 || dicesGameLeader[dicesGameLeader.length - 1] == 6)) {
                        if (pairStreet) {
                            gainsFactor = 55;
                        } else {
                            gainsFactor = 45;
                        }
                    } else {
                        gainsFactor = (int) (sum / 2);
                    }
                    break;
                case 2:
                    gainsFactor = sum;
                    break;
                case 3:
                    gainsFactor = sum + dicesGameLeader[0];
                    break;
                case 4:
                    gainsFactor = sum + dicesGameLeader[2];
                    break;
                case 5:
                    gainsFactor = sum + dicesGameLeader[dicesGameLeader.length - 1];
                    break;
                default:
                    gainsFactor = sum + dicesGameLeader[dicesGameLeader.length - 1] + triesGameLeader * 2;
            }
        } else {
            switch (triesGameLeader) {
                case 1:
                    if (gameLeaderResult == 1 && amountOfSameDices[0] == 6) {
                        gainsFactor = 50;
                    } else {
                        gainsFactor = (int) (sum / 3);
                    }
                    break;
                case 2:
                    gainsFactor = sum - dicesGameLeader[0];
                    break;
                case 3:
                    gainsFactor = sum + dicesGameLeader[0];
                    break;
                case 4:
                    gainsFactor = sum;
                    sum = 0;
                    for (int i = 0; i < amountOfSameDices[0]; i++) {
                        sum += dicesGameLeader[i];
                    }
                    gainsFactor += sum;
                    break;
                case 5:
                    gainsFactor = sum;
                    sum = 0;
                    for (int i = 0; i < amountOfSameDices[0]; i++) {
                        sum += dicesGameLeader[i] + gameLeaderResult;
                    }
                    gainsFactor += sum;
                    break;
                default:
                    gainsFactor = sum;
                    sum = 0;
                    for (int i = 0; i < amountOfSameDices[0]; i++) {
                        sum += dicesGameLeader[i] + gameLeaderResult + triesGameLeader;
                    }
                    gainsFactor += sum;
                    break;
            }
        }
        return gainsFactor;
    }

    private void chooseNextPlayer(boolean pased, int gainsFactor, int gameLeaderResult, int playerResult) {
        nextRound = false;
        int pointDecider = playerResult - gameLeaderResult;
        if (!pased) {
            attributeLabels[MainController.holdCurrentPlayer.getPlayerPosition() - 1][MainController.attributeLabelsEnum.playerOneRank.ordinal()].setText("OUT");
            evaluatePlayerCons();
            outCounter++;
            pointDecider *= -1;
            int pointsGameLeader;
            if (triesGameLeader == 3) {
                pointsGameLeader = gainsFactor + (outCounter + pointDecider) * 2;
            } else {
                pointsGameLeader = gainsFactor + pointDecider * 2;
            }
            int creditsGameLeader = pointsGameLeader * 10;
            playerListMiniGame[0].setPoints(playerListMiniGame[0].getPoints() + pointsGameLeader);
            playerListMiniGame[0].setCredits(playerListMiniGame[0].getCredits() + creditsGameLeader);
            if (outCounter == playerListMiniGame.length - 1) {
                new AlertBox().information("Nicht Bestanden", "Du hast nicht bestanden und bist damit aus dem Spiel ausgeschieden und das Spiel damit beendet.\n" +
                        "Der GameLeader bekommt nun " + pointsGameLeader + " Punkte und " + creditsGameLeader + " Credits.\n");
                gameOver = true;
                gameOver();
            } else if (nextRound) {
                playerListMiniGame = evaluateNextPlayerList();
                new AlertBox().information("Nicht Bestanden", "Du hast nicht bestanden und bist damit aus dem Spiel ausgeschieden.\n" +
                        "Der GameLeader bekommt nun " + pointsGameLeader + " Punkte und " + creditsGameLeader + " Credits.\n"
                        + "Es beginnt die " + miniGameRound + " Runde.\n"
                        + playerListMiniGame[MainController.currentPlayer - 1].getName() + " ist Spielführer.");
            } else {
                new AlertBox().information("Nicht Bestanden", "Du hast nicht bestanden und bist damit aus dem Spiel ausgeschieden.\n" +
                        "Der GameLeader bekommt nun " + pointsGameLeader + " Punkte und " + creditsGameLeader + " Credits.\n"
                        + playerListMiniGame[MainController.currentPlayer - 1].getName() + " ist dran.");
            }
        } else {
            evaluatePlayerCons();
            if (street) {
                MainController.holdCurrentPlayer.setPoints(MainController.holdCurrentPlayer.getPoints() + (int) (gainsFactor / 2));
                MainController.holdCurrentPlayer.setRank((int) (gainsFactor / 2));
                attributeLabels[MainController.holdCurrentPlayer.getPlayerPosition() - 1][MainController.attributeLabelsEnum.playerOneRank.ordinal()].setText(String.valueOf(MainController.holdCurrentPlayer.getRank()));
                MainController.holdCurrentPlayer.setCredits(MainController.holdCurrentPlayer.getCredits() + (int) (gainsFactor * 10));
                if (nextRound) {
                    playerListMiniGame = evaluateNextPlayerList();
                    new AlertBox().information("Bestanden", "Du hast " + (int) (gainsFactor / 2) + " Punkte und " + (int) (gainsFactor * 10) + " Credits bekommen.\n" +
                            "Es beginnt die " + miniGameRound + " Runde. " + playerListMiniGame[MainController.currentPlayer - 1].getName() + " ist Spielführer.");
                } else {
                    new AlertBox().information("Bestanden", "Du hast " + (int) (gainsFactor / 2) + " Punkte und " + (int) (gainsFactor * 10) + " Credits bekommen.\n"
                            + playerListMiniGame[MainController.currentPlayer - 1].getName() + " ist am Zug");
                }
            } else if (pairStreet) {
                switch (pointDecider) {
                    case 0:
                        MainController.holdCurrentPlayer.setPoints(MainController.holdCurrentPlayer.getPoints() + (int) (gainsFactor / 2));
                        MainController.holdCurrentPlayer.setRank((int) (gainsFactor / 2));
                        attributeLabels[MainController.holdCurrentPlayer.getPlayerPosition() - 1][MainController.attributeLabelsEnum.playerOneRank.ordinal()].setText(String.valueOf(MainController.holdCurrentPlayer.getRank()));
                        MainController.holdCurrentPlayer.setCredits(MainController.holdCurrentPlayer.getCredits() + (int) (gainsFactor * 5));
                        if (nextRound) {
                            playerListMiniGame = evaluateNextPlayerList();
                            new AlertBox().information("Bestanden", "Du hast " + (int) (gainsFactor / 2) + " Punkte und " + (int) (gainsFactor * 5) + " Credits bekommen.\n" +
                                    "Es beginnt die " + miniGameRound + " Runde. " + playerListMiniGame[MainController.currentPlayer - 1].getName() + " ist Spielführer.");
                        } else {
                            new AlertBox().information("Bestanden", "Du hast " + (int) (gainsFactor / 2) + " Punkte und " + (int) (gainsFactor * 5) + " Credits bekommen.\n"
                                    + playerListMiniGame[MainController.currentPlayer - 1].getName() + " ist am Zug");
                        }
                        break;
                    case 1:
                        MainController.holdCurrentPlayer.setPoints(MainController.holdCurrentPlayer.getPoints() + (int) (gainsFactor));
                        MainController.holdCurrentPlayer.setRank((int) (gainsFactor));
                        attributeLabels[MainController.holdCurrentPlayer.getPlayerPosition() - 1][MainController.attributeLabelsEnum.playerOneRank.ordinal()].setText(String.valueOf(MainController.holdCurrentPlayer.getRank()));
                        MainController.holdCurrentPlayer.setCredits(MainController.holdCurrentPlayer.getCredits() + (int) (gainsFactor * 10));
                        if (nextRound) {
                            playerListMiniGame = evaluateNextPlayerList();
                            new AlertBox().information("Bestanden", "Du hast " + (int) (gainsFactor) + " Punkte und " + (int) (gainsFactor * 10) + " Credits bekommen.\n" +
                                    "Es beginnt die " + miniGameRound + " Runde. " + playerListMiniGame[MainController.currentPlayer - 1].getName() + " ist Spielführer.");
                        } else {
                            new AlertBox().information("Bestanden", "Du hast " + (int) (gainsFactor) + " Punkte und " + (int) (gainsFactor * 10) + " Credits bekommen.\n"
                                    + playerListMiniGame[MainController.currentPlayer - 1].getName() + " ist am Zug");
                        }
                        break;
                    case 2:
                        MainController.holdCurrentPlayer.setPoints(MainController.holdCurrentPlayer.getPoints() + (int) (gainsFactor * 1.25));
                        MainController.holdCurrentPlayer.setRank((int) (gainsFactor * 1.25));
                        attributeLabels[MainController.holdCurrentPlayer.getPlayerPosition() - 1][MainController.attributeLabelsEnum.playerOneRank.ordinal()].setText(String.valueOf(MainController.holdCurrentPlayer.getRank()));
                        MainController.holdCurrentPlayer.setCredits(MainController.holdCurrentPlayer.getCredits() + (int) (gainsFactor * 7.5));

                        if (nextRound) {
                            playerListMiniGame = evaluateNextPlayerList();
                            new AlertBox().information("Bestanden", "Du hast " + (int) (gainsFactor * 1.25) + " Punkte und " + (int) (gainsFactor * 7.5) + " Credits bekommen.\n" +
                                    "Es beginnt die " + miniGameRound + " Runde. " + playerListMiniGame[MainController.currentPlayer - 1].getName() + " ist Spielführer.");
                        } else {
                            new AlertBox().information("Bestanden", "Du hast " + (int) (gainsFactor * 1.25) + " Punkte und " + (int) (gainsFactor * 7.5) + " Credits bekommen.\n"
                                    + playerListMiniGame[MainController.currentPlayer - 1].getName() + " ist am Zug");
                        }
                        break;
                }
            } else if (pairs) {
                switch (pointDecider) {
                    case 0:
                        MainController.holdCurrentPlayer.setPoints(MainController.holdCurrentPlayer.getPoints() + (int) (gainsFactor / 3));
                        MainController.holdCurrentPlayer.setRank((int) (gainsFactor / 3));
                        attributeLabels[MainController.holdCurrentPlayer.getPlayerPosition() - 1][MainController.attributeLabelsEnum.playerOneRank.ordinal()].setText(String.valueOf(MainController.holdCurrentPlayer.getRank()));
                        MainController.holdCurrentPlayer.setCredits(MainController.holdCurrentPlayer.getCredits() + (int) (gainsFactor * 6));
                        if (nextRound) {
                            playerListMiniGame = evaluateNextPlayerList();
                            new AlertBox().information("Bestanden", "Du hast " + (int) (gainsFactor / 3) + " Punkte und " + (int) (gainsFactor * 6) + " Credits bekommen.\n" +
                                    "Es beginnt die " + miniGameRound + " Runde. " + playerListMiniGame[MainController.currentPlayer - 1].getName() + " ist Spielführer.");
                        } else {
                            new AlertBox().information("Bestanden", "Du hast " + (int) (gainsFactor / 3) + " Punkte und " + (int) (gainsFactor * 6) + " Credits bekommen.\n"
                                    + playerListMiniGame[MainController.currentPlayer - 1].getName() + " ist am Zug");
                        }
                        break;
                    case 1:
                        MainController.holdCurrentPlayer.setPoints(MainController.holdCurrentPlayer.getPoints() + (int) (gainsFactor / 2));
                        MainController.holdCurrentPlayer.setRank((int) (gainsFactor / 2));
                        attributeLabels[MainController.holdCurrentPlayer.getPlayerPosition() - 1][MainController.attributeLabelsEnum.playerOneRank.ordinal()].setText(String.valueOf(MainController.holdCurrentPlayer.getRank()));
                        MainController.holdCurrentPlayer.setCredits(MainController.holdCurrentPlayer.getCredits() + (int) (gainsFactor * 8));
                        if (nextRound) {
                            playerListMiniGame = evaluateNextPlayerList();
                            new AlertBox().information("Bestanden", "Du hast " + (int) (gainsFactor / 2) + " Punkte und " + (int) (gainsFactor * 8) + " Credits bekommen.\n" +
                                    "Es beginnt die " + miniGameRound + " Runde. " + playerListMiniGame[MainController.currentPlayer - 1].getName() + " ist Spielführer.");
                        } else {
                            new AlertBox().information("Bestanden", "Du hast " + (int) (gainsFactor / 2) + " Punkte und " + (int) (gainsFactor * 8) + " Credits bekommen.\n"
                                    + playerListMiniGame[MainController.currentPlayer - 1].getName() + " ist am Zug");
                        }
                        break;
                    case 2:
                        MainController.holdCurrentPlayer.setPoints(MainController.holdCurrentPlayer.getPoints() + (int) (gainsFactor * 0.75));
                        MainController.holdCurrentPlayer.setRank((int) (gainsFactor * 0.75));
                        attributeLabels[MainController.holdCurrentPlayer.getPlayerPosition() - 1][MainController.attributeLabelsEnum.playerOneRank.ordinal()].setText(String.valueOf(MainController.holdCurrentPlayer.getRank()));
                        MainController.holdCurrentPlayer.setCredits(MainController.holdCurrentPlayer.getCredits() + (int) (gainsFactor * 10));
                        if (nextRound) {
                            playerListMiniGame = evaluateNextPlayerList();
                            new AlertBox().information("Bestanden", "Du hast " + (int) (gainsFactor * 0.75) + " Punkte und " + (int) (gainsFactor * 10) + " Credits bekommen.\n" +
                                    "Es beginnt die " + miniGameRound + " Runde. " + playerListMiniGame[MainController.currentPlayer - 1].getName() + " ist Spielführer.");
                        } else {
                            new AlertBox().information("Bestanden", "Du hast " + (int) (gainsFactor * 0.75) + " Punkte und " + (int) (gainsFactor * 10) + " Credits bekommen.\n"
                                    + playerListMiniGame[MainController.currentPlayer - 1].getName() + " ist am Zug");
                        }
                        break;
                }
            }
        }
        if (!gameOver) {
            if (nextRound) {
                firstRoll = false;
                nextRound = false;
                triesGameLeader = 0;
                outCounter = 0;
                miniGameRound++;
                labelList[MainController.labelListEnum.roundsLabel.ordinal()].setText("MiniGame Round: " + miniGameRound);
                labelList[MainController.labelListEnum.lastRoll.ordinal()].setText("Task: Wait for GameLeader.");
                labelList[MainController.labelListEnum.triesLabel.ordinal()].setText("GameLeader Tries: " + triesGameLeader);
            }
            MainController.tries = triesGameLeader;
            labelList[MainController.labelListEnum.triesLabel.ordinal()].setText("Tries: " + triesGameLeader);
            buttonList[MainController.buttonListEnum.rollADice.ordinal()].setDisable(false);
            buttonList[MainController.buttonListEnum.nextPlayerButton.ordinal()].setDisable(true);
            for (int i = 0; i < cubeList.length; i++) {
                cubeList[i].setText("-");
                checkBoxList[i].setSelected(false);
                checkBoxValues[i] = checkBoxList[i].isSelected();
            }
            labelList[MainController.labelListEnum.currentPlayerLabel.ordinal()].setText(playerListMiniGame[MainController.currentPlayer - 1].getName() + " ist dran. Good Luck!");
            playerListMiniGame[0].setRank(1);
            attributeLabels[playerListMiniGame[0].getPlayerPosition() - 1][Player.playerAttributesEnum.rank.ordinal()].setText("GameLeader");
            setAttributeLabels();
        }
    }

    private Player[] evaluateNextPlayerList() {
       new SortPlayerList().sortPlayerList(playerListMiniGame);
        int amountOfOutPlayer = 0;
        int amountOfInPlayer = 0;
        for (int i = 0; i < playerListMiniGame.length; i++) {
            if (playerListMiniGame[i].getRank() == 0) {
                amountOfInPlayer = i;
                amountOfOutPlayer = playerList.length - i;
                break;
            } else if (i == playerListMiniGame.length - 1) {
                amountOfInPlayer = playerListMiniGame.length;
            }
        }
        Player[] outPlayers = new Player[amountOfOutPlayer];
        Player[] inPlayers = new Player[amountOfInPlayer];
        for (int i = 0, j = 0; i < playerListMiniGame.length; ) {
            if (i < amountOfInPlayer) {
                inPlayers[i] = playerListMiniGame[i];
                inPlayers[i].setRank(0);
                attributeLabels[i][MainController.attributeLabelsEnum.playerOneRank.ordinal()].setText("0");
                i++;
            } else {
                outPlayers[j] = playerListMiniGame[i];
                i++;
                j++;
            }
        }
        return inPlayers;
    }

    private void gameOver() {
        new EndMiniGame().endMiniGame(playerList, buttonList, checkBoxList, checkBoxValues, cubeList, labelList, attributeLabels);
    }

    private void evaluatePlayerCons() {
        if (MainController.currentPlayer == playerListMiniGame.length) {
            miniGameRound++;
            nextRound = true;
            MainController.currentPlayer = MainController.nextPlayer++;
        } else if (MainController.currentPlayer++ == playerListMiniGame.length - 1) {
            MainController.nextPlayer = 1;
        } else {
            MainController.currentPlayer = MainController.nextPlayer++;
        }
    }

    private void setAttributeLabels() {
        for (int i = 0; i < playerListMiniGame.length; i++) {
            for (int j = 0; j < MainController.amountOfAttributes; ) {
                playerList[i].setPlayerPosition(i+1);
                attributeLabels[i][j].setText(playerListMiniGame[i].getName());
                j++;
                attributeLabels[i][j].setText(String.valueOf(playerListMiniGame[i].getPoints()));
                j++;
                attributeLabels[i][j].setText(String.valueOf(playerListMiniGame[i].getCredits()));
                j++;
                attributeLabels[i][j].setText(String.valueOf(playerListMiniGame[i].getDrinks()));
                j++;
                if (!attributeLabels[i][j].getText().equals("OUT") && i != 0) {
                    attributeLabels[i][j].setText(String.valueOf(playerListMiniGame[i].getRank()));
                }
                j++;
                j = new SetSkillAttributes().setSkillAttributes(playerListMiniGame, attributeLabels, i, j);
            }
        }

    }

}