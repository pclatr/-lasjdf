package userInterface;

import challenges.ChallengeMainMethods;
import gains.Credits;
import gains.Points;
import gains.Ranks;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import main.Input;
import main.MainGameInterfaceController;
import main.MainGameMethods;
import main.Player;
import main.standardMethods.SetAttributeLabels;
import main.standardMethods.SortPlayerList;
import minigames.MiniGameOneInterfaceController;
import minigames.MiniGameThreeInterfaceController;
import minigames.MiniGameTwoInterfaceController;
import shortSkills.ShortSkillChangeGains;
import shortSkills.ShortSkills;
import specialCombinations.BadCombination;
import specialCombinations.LuckyCombination;
import specialRights.SpecialRights;

//	Button[] skNormalButtonList = { buySkChangeDice, buySkChangeName,buyChangePunishment,buyPointChanger,useSkChangeDice,  useSkChangeName, useChangePunishment,  usePointChanger };

/**
 * @author Johannes Seith and Jakob Seith
 */
public class MainController {

    MainGameMethods mainGameMethods = new MainGameMethods();
    Points givePlayerPoints = new Points();
    Player player = new Player();
    Ranks ranks = new Ranks();
    Credits givePlayerCredits = new Credits();

    LuckyCombination luckyCombi;
    BadCombination badCombi;
    ShortSkills shortSkillNormal;
    ShortSkillChangeGains shortSkillChange;
    MainGameInterfaceController mainGame;
    MiniGameOneInterfaceController miniGameOne;
    MiniGameTwoInterfaceController miniGameTwo;
    MiniGameThreeInterfaceController miniGameThree;
    ChallengeMainMethods mainMethdosChallenge;
    SpecialRights specialRights;

    public static enum buttonListEnum {
        newGameButton, rollADice, nextPlayerButton, methodTest,
    }

    public static enum skNormalButtonListEnum {
        buySkChangeDice, buySkChangeName, buyChangePunishment, buyPointChanger, useSkChangeDice, useSkChangeName,
        usePointChanger

    }

    public static enum skChangeButtonListEnum {
        buySkChangeAwardAll, buySkChangeAwardGLE, buySkChangeAwardGLO, buySkChangeOPDice, useOtherTurnSkill
    }

    public static enum specialRightsButtonListEnum {
        buysRTTChangeDice, buyLastRoleChangeDice
    }

    public static enum labelListEnum {
        triesLabel, roundsLabel, lastRoll, luckyCombiLabel, badCombiLabel, currentPlayerLabel, playerNameTabel,
        playerPointsTabel, playerCreditsTabel, playerDrinksTabel, playerRanksTabel
    }

    public static enum skNormalLabelListEnum {
        priceOfSkChangeDice, priceOfSkChangeName, priceOfChangePunishment, priceOfSRPointChanger
    }

    public static enum skChangeLabelListEnum {
        priceOfSkChangeAwardAll, priceOfSkChangeAwardGLE, priceOfSkChangeAwardGLO, priceOfSkChangeOPDice

    }

    public static enum specialRightsLabelListEnum {
        priceOfTwoTimesChangeDice, priceOfLastRoleChangeDice, priceOfSRPointChanger

    }

    public static enum attributeLabelsEnum {
        playerOneName, playerOnePoints, playerOneCredits, playerOneDrinks, playerOneRank, player1amountOfSkN1,
        player1amountOfSkN2, player1amountOfSkN3, player1amountOfSkCH1, player1amountOfSkCH2, player1amountOfSkCH3,
        player1amountOfSkCH4
    }

    public Main main;

    public static Player holdLastPlayer;
    public static Player holdCurrentPlayer;
    public static Player holdNextPlayer;

    public static int tries;
    public static int currentRound;
    public static int currentPlayer;
    public static int nextPlayer;
    public static int amountOfAttributes = 12;
    public static int amountOfMiniGames = 3;
    public static int roundHolder;
    public static int rollCounter;
    public static int roundCounter;
    public static int amountOfSkNormal = 4;
    public static int amountOfSkChange = 4;
    public static int amountOfSpecialRights = 2;
    public static int maxAvaiablepointChangeUses = 3;
    public static int lastPoints;
    public static int lastCredits;
    public static int skNormalOnePrice = 100;
    public static int skNormalTwoPrice = 10;
    public static int skNormalThreePrice = 300;
    public static int skNormalFourPrice = 10;
    public static int skChangeOnePrice = 250;
    public static int skChangeTwoPrice = 200;
    public static int skChangeThreePrice = 150;
    public static int skChangeFourPrice = 300;
    public static int specialRightOnePrice = 1500;
    public static int specialRightTwoPrice = 1500;
    public static int specialRightThreePrice = 500;

    public static boolean miniGame;
    public static boolean challengeActive;
    public static boolean miniGameActive;
    public static boolean skipTurn;
    public static boolean miniGameIsOver = true;
    public static boolean challengeIsOver = true;

    private int miniGameSelection;
    private int maxAvaiableSkoSrBuys = 3;

    private boolean isGameReadyCheck = false;

    @FXML
    public Label cube1, cube2, cube3, cube4, cube5, cube6;

    @FXML
    public Label triesLabel, roundsLabel, lastRoll, luckyCombiLabel, badCombiLabel, currentPlayerLabel, playerNameTabel,
            playerPointsTabel, playerCreditsTabel, playerDrinksTabel, playerRanksTabel, priceOfSkChangeDice,
            priceOfSkChangeName, priceOfChangePunishment, priceOfSkChangeAwardAll, priceOfSkChangeAwardGLE,
            priceOfSkChangeAwardGLO, priceOfSkChangeOPDice, priceOfTwoTimesChangeDice, priceOfLastRoleChangeDice,
            priceOfSkNPointChanger, priceOfSRPointChanger, trueLabelSr1, trueLabelSr2, trueLabelSr3;

    @FXML
    public Label lastPointsLabel, lastCreditsLabel;

    @FXML
    public Label playerOneName, playerOnePoints, playerOneCredits, playerOneDrinks, playerOneRank, player1amountOfSkN1,
            player1amountOfSkN2, player1amountOfSkN3, player1amountOfSkCH1, player1amountOfSkCH2, player1amountOfSkCH3,
            player1amountOfSkCH4, playerTwoName, playerTwoPoints, playerTwoCredits, playerTwoDrinks, playerTwoRank,
            player2amountOfSkN1, player2amountOfSkN2, player2amountOfSkN3, player2amountOfSkCH1, player2amountOfSkCH2,
            player2amountOfSkCH3, player2amountOfSkCH4, playerThreeName, playerThreePoints, playerThreeCredits,
            playerThreeDrinks, playerThreeRank, player3amountOfSkN1, player3amountOfSkN2, player3amountOfSkN3,
            player3amountOfSkCH1, player3amountOfSkCH2, player3amountOfSkCH3, player3amountOfSkCH4, playerFourName,
            playerFourPoints, playerFourCredits, playerFourDrinks, playerFourRank, player4amountOfSkN1,
            player4amountOfSkN2, player4amountOfSkN3, player4amountOfSkCH1, player4amountOfSkCH2, player4amountOfSkCH3,
            player4amountOfSkCH4, playerFiveName, playerFivePoints, playerFiveCredits, playerFiveDrinks, playerFiveRank,
            player5amountOfSkN1, player5amountOfSkN2, player5amountOfSkN3, player5amountOfSkCH1, player5amountOfSkCH2,
            player5amountOfSkCH3, player5amountOfSkCH4, playerSixName, playerSixPoints, playerSixCredits,
            playerSixDrinks, playerSixRank, player6amountOfSkN1, player6amountOfSkN2, player6amountOfSkN3,
            player6amountOfSkCH1, player6amountOfSkCH2, player6amountOfSkCH3, player6amountOfSkCH4, playerSevenName,
            playerSevenPoints, playerSevenCredits, playerSevenDrinks, playerSevenRank, player7amountOfSkN1,
            player7amountOfSkN2, player7amountOfSkN3, player7amountOfSkCH1, player7amountOfSkCH2, player7amountOfSkCH3,
            player7amountOfSkCH4, playerEightName, playerEightPoints, playerEightCredits, playerEightDrinks,
            playerEightRank, player8amountOfSkN1, player8amountOfSkN2, player8amountOfSkN3, player8amountOfSkCH1,
            player8amountOfSkCH2, player8amountOfSkCH3, player8amountOfSkCH4, playerNineName, playerNinePoints,
            playerNineCredits, playerNineDrinks, playerNineRank, player9amountOfSkN1, player9amountOfSkN2,
            player9amountOfSkN3, player9amountOfSkCH1, player9amountOfSkCH2, player9amountOfSkCH3, player9amountOfSkCH4,
            playerTenName, playerTenPoints, playerTenCredits, playerTenDrinks, playerTenRank, player10amountOfSkN1,
            player10amountOfSkN2, player10amountOfSkN3, player10amountOfSkCH1, player10amountOfSkCH2,
            player10amountOfSkCH3, player10amountOfSkCH4, playerElevenName, playerElevenPoints, playerElevenCredits,
            playerElevenDrinks, playerElevenRank, player11amountOfSkN1, player11amountOfSkN2, player11amountOfSkN3,
            player11amountOfSkCH1, player11amountOfSkCH2, player11amountOfSkCH3, player11amountOfSkCH4,
            playerTwelveName, playerTwelvePoints, playerTwelveCredits, playerTwelveDrinks, playerTwelveRank,
            player12amountOfSkN1, player12amountOfSkN2, player12amountOfSkN3, player12amountOfSkCH1,
            player12amountOfSkCH2, player12amountOfSkCH3, player12amountOfSkCH4;

    @FXML
    public CheckBox checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6;

    @FXML
    public Button newGameButton, rollADice, nextPlayerButton, methodTest;
    @FXML
    public Button buySkChangeDice, useSkChangeDice, buySkChangeName, useSkChangeName, buyChangePunishment,
            buyPointChanger, usePointChanger;
    @FXML
    public Button buySkChangeAwardAll, buySkChangeAwardGLE, buySkChangeAwardGLO, buySkChangeOPDice, useOtherTurnSkill;
    @FXML
    public Button buysRTTChangeDice, buyLastRoleChangeDice;

    private Button[] buttonList;
    private Button[] skNormalButtonList;
    private Button[] skChangeButtonList;
    private Button[] specialRightsButtonList;

    private Label[] labelList;
    private Label[] skNormalLabelList;
    private Label[] skChangeLabelList;
    private Label[] specialRightsLabelList;
    private Label[][] attributeLabels = new Label[Player.amountOfPlayers][amountOfAttributes];
    private Label[] cubeLabels;

    private CheckBox[] checkBoxList;

    private Player[] playerList;

    public static Player[] playerInOrder;

    private int[] luckyCombination = new int[6];
    private int[] badCombination = new int[6];


    /**
     * To start the UI. You can start the game in a test modus.
     *
     * @param main reference to the mainclass
     */
    public void setMain(Main main) {
        this.main = main;

        Label[][] attributeLabels = {
                {playerOneName, playerOnePoints, playerOneCredits, playerOneDrinks, playerOneRank, player1amountOfSkN1,
                        player1amountOfSkN2, player1amountOfSkN3, player1amountOfSkCH1, player1amountOfSkCH2,
                        player1amountOfSkCH3, player1amountOfSkCH4},
                {playerTwoName, playerTwoPoints, playerTwoCredits, playerTwoDrinks, playerTwoRank, player2amountOfSkN1,
                        player2amountOfSkN2, player2amountOfSkN3, player2amountOfSkCH1, player2amountOfSkCH2,
                        player2amountOfSkCH3, player2amountOfSkCH4},
                {playerThreeName, playerThreePoints, playerThreeCredits, playerThreeDrinks, playerThreeRank,
                        player3amountOfSkN1, player3amountOfSkN2, player3amountOfSkN3, player3amountOfSkCH1,
                        player3amountOfSkCH2, player3amountOfSkCH3, player3amountOfSkCH4},
                {playerFourName, playerFourPoints, playerFourCredits, playerFourDrinks, playerFourRank,
                        player4amountOfSkN1, player4amountOfSkN2, player4amountOfSkN3, player4amountOfSkCH1,
                        player4amountOfSkCH2, player4amountOfSkCH3, player4amountOfSkCH4},
                {playerFiveName, playerFivePoints, playerFiveCredits, playerFiveDrinks, playerFiveRank,
                        player5amountOfSkN1, player5amountOfSkN2, player5amountOfSkN3, player5amountOfSkCH1,
                        player5amountOfSkCH2, player5amountOfSkCH3, player5amountOfSkCH4},
                {playerSixName, playerSixPoints, playerSixCredits, playerSixDrinks, playerSixRank, player6amountOfSkN1,
                        player6amountOfSkN2, player6amountOfSkN3, player6amountOfSkCH1, player6amountOfSkCH2,
                        player6amountOfSkCH3, player6amountOfSkCH4},
                {playerSevenName, playerSevenPoints, playerSevenCredits, playerSevenDrinks, playerSevenRank,
                        player7amountOfSkN1, player7amountOfSkN2, player7amountOfSkN3, player7amountOfSkCH1,
                        player7amountOfSkCH2, player7amountOfSkCH3, player7amountOfSkCH4},
                {playerEightName, playerEightPoints, playerEightCredits, playerEightDrinks, playerEightRank,
                        player8amountOfSkN1, player8amountOfSkN2, player8amountOfSkN3, player8amountOfSkCH1,
                        player8amountOfSkCH2, player8amountOfSkCH3, player8amountOfSkCH4},
                {playerNineName, playerNinePoints, playerNineCredits, playerNineDrinks, playerNineRank,
                        player9amountOfSkN1, player9amountOfSkN2, player9amountOfSkN3, player9amountOfSkCH1,
                        player9amountOfSkCH2, player9amountOfSkCH3, player9amountOfSkCH4},
                {playerTenName, playerTenPoints, playerTenCredits, playerTenDrinks, playerTenRank,
                        player10amountOfSkN1, player10amountOfSkN2, player10amountOfSkN3, player10amountOfSkCH1,
                        player10amountOfSkCH2, player10amountOfSkCH3, player10amountOfSkCH4},
                {playerElevenName, playerElevenPoints, playerElevenCredits, playerElevenDrinks, playerElevenRank,
                        player11amountOfSkN1, player11amountOfSkN2, player11amountOfSkN3, player11amountOfSkCH1,
                        player11amountOfSkCH2, player11amountOfSkCH3, player11amountOfSkCH4},
                {playerTwelveName, playerTwelvePoints, playerTwelveCredits, playerTwelveDrinks, playerTwelveRank,
                        player12amountOfSkN1, player12amountOfSkN2, player12amountOfSkN3, player12amountOfSkCH1,
                        player12amountOfSkCH2, player12amountOfSkCH3, player12amountOfSkCH4}};
        this.attributeLabels = attributeLabels;

        CheckBox[] checkBoxList = {checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6};
        this.checkBoxList = checkBoxList;

        Button[] buttonList = {newGameButton, rollADice, nextPlayerButton, methodTest,};
        this.buttonList = buttonList;

        Button[] skNormalButtonList = {buySkChangeDice, buySkChangeName, buyChangePunishment, buyPointChanger,
                useSkChangeDice, useSkChangeName, usePointChanger};
        this.skNormalButtonList = skNormalButtonList;

        Button[] skChangeButtonList = {buySkChangeAwardAll, buySkChangeAwardGLE, buySkChangeAwardGLO,
                buySkChangeOPDice, useOtherTurnSkill};
        this.skChangeButtonList = skChangeButtonList;

        Button[] specialRightsButtonList = {buysRTTChangeDice, buyLastRoleChangeDice};
        this.specialRightsButtonList = specialRightsButtonList;

        Label[] labelList = {triesLabel, roundsLabel, lastRoll, luckyCombiLabel, badCombiLabel, currentPlayerLabel,
                playerNameTabel, playerPointsTabel, playerCreditsTabel, playerDrinksTabel, playerRanksTabel,};
        this.labelList = labelList;

        Label[] skNormalLabelList = {priceOfSkChangeDice, priceOfSkChangeName, priceOfChangePunishment,
                priceOfSRPointChanger};
        this.skNormalLabelList = skNormalLabelList;

        Label[] skChangeLabelList = {priceOfSkChangeAwardAll, priceOfSkChangeAwardGLE, priceOfSkChangeAwardGLO,
                priceOfSkChangeOPDice};
        this.skChangeLabelList = skChangeLabelList;

        Label[] specialRightsLabelList = {priceOfTwoTimesChangeDice, priceOfLastRoleChangeDice,
                priceOfSRPointChanger};
        this.specialRightsLabelList = specialRightsLabelList;

        Label[] cubeLabels = {cube1, cube2, cube3, cube4, cube5, cube6};
        this.cubeLabels = cubeLabels;

    }

    /**
     * (Button) To generate the necessary data for a game.
     *
     * @param event to generate an action.
     * @see main.Player#generatePlayer()--> There you generate 2
     * prepared players in the test modus.
     */
    @FXML
    public void newGameButton(ActionEvent event) {

        boolean confirmResetGame = false;
        if (isGameReadyCheck) {

            System.out.println("Das derzeitige Spiel wird zurückgesetzt, mit j bestätigen.");

            if (Input.stringInput().equals("j")) {
                confirmResetGame = true;

                for (int i = 0; i < Player.amountOfPlayers; i++) {
                    for (int j = 0; j < MainController.amountOfAttributes; j++) {
                        attributeLabels[i][j].setVisible(false);
                    }
                }
            }

        }
        if (confirmResetGame || !isGameReadyCheck) {
            playerList = player.generatePlayer();
            MainController.currentRound = 1;
            playerInOrder = new Player[playerList.length];

            for (int i = 0; i < playerList.length; i++) {
                playerInOrder[i] = playerList[i];
            }
            holdCurrentPlayer = playerList[0];
            holdNextPlayer = playerList[1];

            mainGame = new MainGameInterfaceController(playerList, cubeLabels, labelList, attributeLabels, checkBoxList,
                    buttonList);

            miniGameOne = new MiniGameOneInterfaceController(playerList, cubeLabels, labelList, attributeLabels,
                    checkBoxList, buttonList);

            mainMethdosChallenge = new ChallengeMainMethods(playerList, attributeLabels, cubeLabels, labelList,
                    checkBoxList, buttonList);

            miniGameTwo = new MiniGameTwoInterfaceController(playerList, cubeLabels, labelList, attributeLabels,
                    checkBoxList, buttonList);

            miniGameThree = new MiniGameThreeInterfaceController(playerList, cubeLabels, labelList, attributeLabels,
                    checkBoxList, buttonList);

            badCombi = new BadCombination(attributeLabels);

            luckyCombi = new LuckyCombination(attributeLabels);

            shortSkillNormal = new ShortSkills(playerList, cubeLabels, attributeLabels, labelList, skNormalLabelList,
                    buttonList, skNormalButtonList);

            shortSkillChange = new ShortSkillChangeGains(playerList, cubeLabels, attributeLabels, labelList,
                    skChangeLabelList, buttonList, skChangeButtonList);

            specialRights = new SpecialRights(playerList, cubeLabels, attributeLabels, specialRightsLabelList,
                    specialRightsButtonList);

            mainGame.prepareMainGame();

            for (int i = 0; i < Player.amountOfPlayers; i++) {
                for (int j = 0; j < MainController.amountOfAttributes - 7; j++) {
                    attributeLabels[i][j].setVisible(true);
                }
            }

            roundHolder = currentRound;

            skNormalLabelList[skNormalLabelListEnum.priceOfSkChangeDice.ordinal()]
                    .setText(String.valueOf(skNormalOnePrice));
            skNormalLabelList[skNormalLabelListEnum.priceOfSkChangeName.ordinal()]
                    .setText(String.valueOf(skNormalTwoPrice));
            skNormalLabelList[skNormalLabelListEnum.priceOfChangePunishment.ordinal()]
                    .setText(String.valueOf(skNormalThreePrice));
            skNormalLabelList[skNormalLabelListEnum.priceOfSRPointChanger.ordinal()]
                    .setText(String.valueOf(specialRightThreePrice));

            skChangeLabelList[skChangeLabelListEnum.priceOfSkChangeAwardAll.ordinal()]
                    .setText(String.valueOf(skChangeOnePrice));
            skChangeLabelList[skChangeLabelListEnum.priceOfSkChangeAwardGLE.ordinal()]
                    .setText(String.valueOf(skChangeTwoPrice));
            skChangeLabelList[skChangeLabelListEnum.priceOfSkChangeAwardGLO.ordinal()]
                    .setText(String.valueOf(skChangeThreePrice));
            skChangeLabelList[skChangeLabelListEnum.priceOfSkChangeOPDice.ordinal()]
                    .setText(String.valueOf(skChangeFourPrice));

            specialRightsLabelList[specialRightsLabelListEnum.priceOfTwoTimesChangeDice.ordinal()]
                    .setText(String.valueOf(specialRightOnePrice));
            specialRightsLabelList[specialRightsLabelListEnum.priceOfLastRoleChangeDice.ordinal()]
                    .setText(String.valueOf(specialRightTwoPrice));

            buttonList[buttonListEnum.newGameButton.ordinal()].setDisable(true);

            luckyCombination = luckyCombi.generateLuckyCombination(miniGameIsOver);
            badCombination = badCombi.generatebadCombination(miniGameIsOver);

            labelList[labelListEnum.luckyCombiLabel.ordinal()].setText("Lucky Combi: "
                    + String.valueOf(luckyCombination[0]) + " | " + String.valueOf(luckyCombination[1]) + " | "
                    + String.valueOf(luckyCombination[2]) + " | " + String.valueOf(luckyCombination[3]) + " | "
                    + String.valueOf(luckyCombination[4]) + " | " + String.valueOf(luckyCombination[5]));

            labelList[labelListEnum.badCombiLabel.ordinal()].setText(
                    "Bad Combi: " + String.valueOf(badCombination[0]) + " | " + String.valueOf(badCombination[1])
                            + " | " + String.valueOf(badCombination[2]) + " | " + String.valueOf(badCombination[3])
                            + " | " + String.valueOf(badCombination[4]) + " | " + String.valueOf(badCombination[5]));

            miniGameIsOver = false;
        }
    }

    boolean methodTestBool = false;

    /**
     * (Button) To generate dices. You are able to set the dices by yourself, if you
     * chose the test modus.
     *
     * @param event to generate an action.
     */
    @FXML
    public void rollDicesButton(ActionEvent event) {
        resetBuyButtons(true);
        skipTurn = false;

        if (!challengeActive && !miniGameActive) {
            MainController.rollCounter++;

            if (!mainMethdosChallenge.isItTimeForChallenge() && !skipTurn) {
                mainGame.rollDices();

                // Für Method Test
                if (methodTestBool) {
                    System.out.println("Würfel setzen");
                    for (int i = 0; i < 6; i++) {
                        MainGameMethods.currentDices[i] = Input.intInput();
                    }
                    System.out.println("Würfel sind gesetzt.");
                    for (int i = 0; i < cubeLabels.length; i++) {
                        cubeLabels[i].setText(String.valueOf(MainGameMethods.currentDices[i]));
                    }
                    methodTestBool = false;
                }
                if (badCombi.checkCombination() || luckyCombi.checkCombination(checkBoxList)) {

                    ranks.generateRanks(playerList);

                    setAttributeLabels(true);
                }
            } else {
                skNormalButtonList[skNormalButtonListEnum.useSkChangeDice.ordinal()].setDisable(true);
            }

        } else if (challengeActive) {
            mainMethdosChallenge.rollDice();

            // Für Method Test
            if (methodTestBool) {
                System.out.println("Würfel setzen");
                for (int i = 0; i < 6; i++) {
                    MainGameMethods.currentDices[i] = Input.intInput();
                }
                System.out.println("Würfel sind gesetzt.");
                for (int i = 0; i < cubeLabels.length; i++) {
                    cubeLabels[i].setText(String.valueOf(MainGameMethods.currentDices[i]));
                }
                methodTestBool = false;
            }
            if (Integer.parseInt(attributeLabels[currentPlayer - 1][5].getText()) > 0) {
                skNormalButtonList[skNormalButtonListEnum.useSkChangeDice.ordinal()].setDisable(false);
            }

            if (badCombi.checkCombination() || luckyCombi.checkCombination(checkBoxList)) {

                ranks.generateRanks(playerList);
                setAttributeLabels(true);
            }

        } else if (miniGameSelection == 0 && miniGameActive) {
            miniGameOne.rollDices();

            // Für Method Test
            if (methodTestBool) {
                System.out.println("Würfel setzen");
                for (int i = 0; i < 6; i++) {
                    MainGameMethods.currentDices[i] = Input.intInput();
                }
                System.out.println("Würfel sind gesetzt.");
                for (int i = 0; i < cubeLabels.length; i++) {
                    cubeLabels[i].setText(String.valueOf(MainGameMethods.currentDices[i]));
                }
                methodTestBool = false;
            }
            if (badCombi.checkCombination() || luckyCombi.checkCombination(checkBoxList)) {

                setAttributeLabels(false);
            }

        } else if (miniGameSelection == 1 && miniGameActive) {
            miniGameTwo.rollDices();

            // Für Method Test
            if (methodTestBool) {
                System.out.println("Würfel setzen");
                for (int i = 0; i < 6; i++) {
                    MainGameMethods.currentDices[i] = Input.intInput();
                }
                System.out.println("Würfel sind gesetzt.");
                for (int i = 0; i < cubeLabels.length; i++) {
                    cubeLabels[i].setText(String.valueOf(MainGameMethods.currentDices[i]));
                }
                methodTestBool = false;
            }

            if (badCombi.checkCombination() || luckyCombi.checkCombination(checkBoxList)) {

                setAttributeLabels(false);
            }
        } else if (miniGameSelection == 2 && miniGameActive) {
            miniGameThree.rollDices();
            // Für Method Test
            if (methodTestBool) {
                System.out.println("Würfel setzen");
                for (int i = 0; i < 6; i++) {
                    MainGameMethods.currentDices[i] = Input.intInput();
                }
                System.out.println("Würfel sind gesetzt.");
                for (int i = 0; i < cubeLabels.length; i++) {
                    cubeLabels[i].setText(String.valueOf(MainGameMethods.currentDices[i]));
                }
                methodTestBool = false;
            }

            if (badCombi.checkCombination() || luckyCombi.checkCombination(checkBoxList)) {

                setAttributeLabels(false);
            }
        } else if (miniGameSelection == 3 && miniGameActive) {

        }

        if (MainController.skipTurn) {

            resetAllButtons(false);
            ShortSkills.amountOfChangeDiceUses = 0;
            lastPointsLabel.setText(String.valueOf(lastPoints));
            lastCreditsLabel.setText(String.valueOf(lastCredits));

        }

        if (MainController.tries == 0) {
            if (holdCurrentPlayer.getSkChangeDice() != 0
                    && ((holdCurrentPlayer.getSRLastRoleChangeDice() && ShortSkills.amountOfChangeDiceUses == 0)
                    || (holdCurrentPlayer.getSRLastRoleChangeDice() && ShortSkills.amountOfChangeDiceUses == 1
                    && holdCurrentPlayer.getSRTTChangeDice()))) {
                skNormalButtonList[skNormalButtonListEnum.useSkChangeDice.ordinal()].setDisable(false);
            } else {
                skNormalButtonList[skNormalButtonListEnum.useSkChangeDice.ordinal()].setDisable(true);
            }
        } else if (holdCurrentPlayer.getSkChangeDice() != 0 && ShortSkills.amountOfChangeDiceUses == 0) {
            skNormalButtonList[skNormalButtonListEnum.useSkChangeDice.ordinal()].setDisable(false);
        }

    }

    /**
     * (Button) To end your turn and get points for your result.
     *
     * @param event to generate an action.
     */
    @FXML
    public void nextPlayerButton(ActionEvent event) {

        if (!challengeActive && !miniGameActive) {
            holdLastPlayer = playerInOrder[currentPlayer - 1];
            holdLastPlayer.setPlayerPosition(holdLastPlayer.getRank());
            mainGame.evaluatePoints();
            holdCurrentPlayer = playerInOrder[currentPlayer - 1];
            holdCurrentPlayer.setPlayerPosition(holdCurrentPlayer.getRank());
            holdNextPlayer = playerInOrder[nextPlayer - 1];
            holdNextPlayer.setPlayerPosition(holdNextPlayer.getRank());
            maxAvaiableSkoSrBuys = 3;
        } else if (challengeActive) {
            holdLastPlayer = playerInOrder[currentPlayer - 1];
            holdLastPlayer.setPlayerPosition(holdLastPlayer.getRank());
            mainMethdosChallenge.nextRound();
            holdCurrentPlayer = playerInOrder[currentPlayer - 1];
            holdCurrentPlayer.setPlayerPosition(holdCurrentPlayer.getRank());
            holdNextPlayer = playerInOrder[nextPlayer - 1];
            holdNextPlayer.setPlayerPosition(holdNextPlayer.getRank());
            maxAvaiableSkoSrBuys = 3;
        } else if (miniGameSelection == 0 && miniGameActive) {
            holdLastPlayer = playerList[currentPlayer - 1];
            holdLastPlayer.setPlayerPosition(currentPlayer);
            miniGameOne.nextPlayer();
            holdCurrentPlayer = playerList[currentPlayer - 1];
            holdCurrentPlayer.setPlayerPosition(currentPlayer);
            holdNextPlayer = playerList[nextPlayer - 1];
            holdNextPlayer.setPlayerPosition(nextPlayer);
        } else if (miniGameSelection == 1 && miniGameActive) {
            holdLastPlayer = playerList[currentPlayer - 1];
            holdLastPlayer.setPlayerPosition(currentPlayer);
            miniGameTwo.nextPlayer();
            holdCurrentPlayer = playerList[currentPlayer - 1];
            holdCurrentPlayer.setPlayerPosition(currentPlayer);
        } else if (miniGameSelection == 2 && miniGameActive) {
            holdLastPlayer = playerList[currentPlayer - 1];
            holdLastPlayer.setPlayerPosition(currentPlayer);
            miniGameThree.nextPlayer();
            holdCurrentPlayer = playerList[currentPlayer - 1];
            holdCurrentPlayer.setPlayerPosition(currentPlayer);
            holdNextPlayer = playerList[nextPlayer - 1];
            holdNextPlayer.setPlayerPosition(nextPlayer);
        }

        if (roundHolder != currentRound && (!challengeActive && !miniGameActive)) {
            miniGameSelection = (int) (Math.random() * amountOfMiniGames);

            // Test
            // miniGameSelection = 1;
            if (miniGameSelection == 0) {
                miniGameOne.startGame();
            } else if (miniGameSelection == 1) {
                miniGameTwo.startGame();
            } else if (miniGameSelection == 2) {
                miniGameThree.startGame();
            }
            miniGameActive = true;
            resetAllButtons(false);
            lastPointsLabel.setText(String.valueOf(lastPoints));
            lastCreditsLabel.setText(String.valueOf(lastCredits));
        }

        if (miniGameIsOver) {
            luckyCombination = luckyCombi.generateLuckyCombination(miniGameIsOver);
            badCombination = badCombi.generatebadCombination(miniGameIsOver);

            labelList[labelListEnum.luckyCombiLabel.ordinal()].setText("Lucky Combi: "
                    + String.valueOf(luckyCombination[0]) + " | " + String.valueOf(luckyCombination[1]) + " | "
                    + String.valueOf(luckyCombination[2]) + " | " + String.valueOf(luckyCombination[3]) + " | "
                    + String.valueOf(luckyCombination[4]) + " | " + String.valueOf(luckyCombination[5]));

            labelList[labelListEnum.badCombiLabel.ordinal()].setText(
                    "Bad Combi: " + String.valueOf(badCombination[0]) + " | " + String.valueOf(badCombination[1])
                            + " | " + String.valueOf(badCombination[2]) + " | " + String.valueOf(badCombination[3])
                            + " | " + String.valueOf(badCombination[4]) + " | " + String.valueOf(badCombination[5]));

            maxAvaiablepointChangeUses = 3;
            miniGameIsOver = false;
        }

        if (challengeIsOver) {
            resetAllButtons(false);
        }
        ShortSkills.amountOfChangeDiceUses = 0;
    }

    private void resetAllButtons(boolean roll) {
//		Button[] skNormalButtonList = { buySkChangeDice, buySkChangeName,buyChangePunishment,buyPointChanger,useSkChangeDice,  useSkChangeName, useChangePunishment,  usePointChanger };

        for (int i = 0; i < amountOfSkNormal; i++) {
            if (i < amountOfSkNormal - 1) {
                if (holdCurrentPlayer.getCredits() < Integer.parseInt(skNormalLabelList[i].getText())) {
                    skNormalButtonList[i].setDisable(true);
                } else {
                    skNormalButtonList[i].setDisable(false);
                }
                if (Integer.parseInt(attributeLabels[holdCurrentPlayer.getPlayerPosition() - 1][5 + i].getText()) == 0
                        && i < amountOfSkNormal - 2) {
                    skNormalButtonList[4 + i].setDisable(true);
                } else {
                    skNormalButtonList[4 + i].setDisable(false);
                }
            } else {
                if (holdCurrentPlayer.getSRPointsChanger()) {
                    skNormalButtonList[skNormalButtonListEnum.buyPointChanger.ordinal()].setDisable(true);
                    skNormalButtonList[skNormalButtonListEnum.usePointChanger.ordinal()].setDisable(false);
                    trueLabelSr3.setVisible(true);
                } else {
                    skNormalButtonList[skNormalButtonListEnum.usePointChanger.ordinal()].setDisable(true);
                    trueLabelSr3.setVisible(false);
                    skNormalButtonList[skNormalButtonListEnum.buyPointChanger.ordinal()].setDisable(true);
                    if (holdCurrentPlayer.getCredits() >= specialRightThreePrice) {
                        skNormalButtonList[skNormalButtonListEnum.buyPointChanger.ordinal()].setDisable(false);
                    }
                }
            }
        }

        for (int i = 0; i < amountOfSkChange; i++) {
            if (holdCurrentPlayer.getCredits() < Integer.parseInt(skChangeLabelList[i].getText())) {
                skChangeButtonList[i].setDisable(true);
            } else {
                skChangeButtonList[i].setDisable(false);
            }

        }

        for (int i = 0; i < amountOfSpecialRights; i++) {

            if (i == 0 && holdCurrentPlayer.getSRTTChangeDice()) {
                specialRightsButtonList[i].setDisable(true);
                trueLabelSr1.setVisible(true);
            } else if (holdCurrentPlayer.getCredits() < Integer.parseInt(specialRightsLabelList[i].getText())) {
                specialRightsButtonList[i].setDisable(true);
                trueLabelSr1.setVisible(false);
            } else {
                specialRightsButtonList[i].setDisable(false);
                trueLabelSr1.setVisible(false);
            }
            i++;

            if (i == 1 && holdCurrentPlayer.getSRLastRoleChangeDice()) {
                specialRightsButtonList[i].setDisable(true);
                trueLabelSr2.setVisible(true);
            } else if (holdCurrentPlayer.getCredits() < Integer.parseInt(specialRightsLabelList[i].getText())) {
                specialRightsButtonList[i].setDisable(true);
                trueLabelSr2.setVisible(false);
            } else {
                specialRightsButtonList[i].setDisable(false);
                trueLabelSr2.setVisible(false);
            }
            i++;

        }

        if (miniGameActive || maxAvaiableSkoSrBuys == 0) {

            for (int i = 0; i < amountOfSkChange; i++) {
                if (i != skNormalButtonListEnum.buySkChangeName.ordinal()) {
                    if (i < amountOfSkNormal) {
                        skNormalButtonList[i].setDisable(true);
                    }
                }
                if (i < amountOfSkChange) {
                    skChangeButtonList[i].setDisable(true);
                }
                if (i < amountOfSpecialRights) {
                    specialRightsButtonList[i].setDisable(true);
                }
                skNormalButtonList[skNormalButtonListEnum.usePointChanger.ordinal()].setDisable(true);


            }

        }

        if (!roll) {
            skNormalButtonList[skNormalButtonListEnum.useSkChangeDice.ordinal()].setDisable(true);

        }
    }

    private void resetBuyButtons(boolean roll) {

//		Button[] skNormalButtonList = { buySkChangeDice, buySkChangeName,buyChangePunishment,buyPointChanger,useSkChangeDice,  useSkChangeName, useChangePunishment,  usePointChanger };

//		Label[] skNormalLabelList = { priceOfSkChangeDice, priceOfSkChangeName, priceOfChangePunishment,priceOfSRPointChanger };

        if (roll || miniGameActive || maxAvaiableSkoSrBuys <= 0) {

            for (int i = 0; i < amountOfSkChange; i++) {
                if (i != skNormalButtonListEnum.buySkChangeName.ordinal()) {
                    if (i < amountOfSkNormal) {
                        skNormalButtonList[i].setDisable(true);
                    }
                }
                if (i < amountOfSkChange) {
                    skChangeButtonList[i].setDisable(true);
                }
                if (i < amountOfSpecialRights) {
                    specialRightsButtonList[i].setDisable(true);
                }
                skNormalButtonList[skNormalButtonListEnum.usePointChanger.ordinal()].setDisable(true);

            }

        } else {
            for (int i = 0; i < amountOfSkNormal; i++) {

                if (holdCurrentPlayer.getCredits() < Integer.parseInt(skNormalLabelList[i].getText())) {
                    skNormalButtonList[i].setDisable(true);
                } else {
                    skNormalButtonList[i].setDisable(false);
                }

                if (holdCurrentPlayer.getSRPointsChanger() && i == 3) {
                    skNormalButtonList[skNormalButtonListEnum.buyPointChanger.ordinal()].setDisable(true);
                    if (maxAvaiablepointChangeUses > 0) {
                        skNormalButtonList[skNormalButtonListEnum.usePointChanger.ordinal()].setDisable(false);
                    }
                    trueLabelSr3.setVisible(true);
                }

            }

//			Button[] skChangeButtonList = { buySkChangeAwardAll, buySkChangeAwardGLE, buySkChangeAwardGLO,buySkChangeOPDice, useOtherTurnSkill };

//			Label[] skChangeLabelList = { priceOfSkChangeAwardAll, priceOfSkChangeAwardGLE, priceOfSkChangeAwardGLO,priceOfSkChangeOPDice };

            for (int i = 0; i < amountOfSkChange; i++) {
                if (holdCurrentPlayer.getCredits() < Integer.parseInt(skChangeLabelList[i].getText())) {
                    skChangeButtonList[i].setDisable(true);
                } else {
                    skChangeButtonList[i].setDisable(false);
                }

            }

//			Button[] specialRightsButtonList = { buysRTTChangeDice, buyLastRoleChangeDice };

//			Label[] specialRightsLabelList = { priceOfTwoTimesChangeDice, priceOfLastRoleChangeDice,priceOfSRPointChanger };

            for (int i = 0; i < amountOfSpecialRights; i++) {

                if (holdCurrentPlayer.getCredits() < Integer.parseInt(specialRightsLabelList[i].getText())) {
                    specialRightsButtonList[i].setDisable(true);
                } else {
                    specialRightsButtonList[i].setDisable(false);
                }

                if (i == 0 && holdCurrentPlayer.getSRTTChangeDice()) {
                    specialRightsButtonList[i].setDisable(true);

                } else if (i == 1 && holdCurrentPlayer.getSRLastRoleChangeDice()) {
                    specialRightsButtonList[i].setDisable(true);

                }

            }
        }
    }

    @FXML
    public void methodTest(ActionEvent event) {
        if (!methodTestBool) {
            methodTestBool = true;
            System.out.println("Würfel können bei rollDice selbst gessetzt werden. Nochmal drücken, um sie jetzt zu setzen.");
        } else {
            System.out.println("Würfel setzen");
            for (int i = 0; i < 6; i++) {
                MainGameMethods.currentDices[i] = Input.intInput();
            }
            for (int i = 0; i < cubeLabels.length; i++) {
                cubeLabels[i].setText(String.valueOf(MainGameMethods.currentDices[i]));
            }
        }
        if (!miniGameActive) {
            miniGameSelection = 2;
            miniGameActive = true;
            miniGameThree.startGame();
            methodTestBool = false;
        }
    }

    @FXML
    public void buySkChangeDice(ActionEvent event) {
        shortSkillNormal.buy(skNormalOnePrice, 1);
        maxAvaiableSkoSrBuys--;
        resetBuyButtons(false);
    }

    @FXML
    public void useSkChangeDice(ActionEvent event) {

        shortSkillNormal.changeDice();

    }

    @FXML
    public void buySkChangeName(ActionEvent event) {
        shortSkillNormal.buy(skNormalTwoPrice, 2);
        resetBuyButtons(false);
        skNormalButtonList[skNormalButtonListEnum.useSkChangeName.ordinal()].setDisable(false);

    }

    @FXML
    public void useSkChangeName(ActionEvent event) {
        shortSkillNormal.changeName();
    }

    @FXML
    public void buySkChangeOwnPunishment(ActionEvent event) {
        shortSkillNormal.buy(skNormalThreePrice, 3);
        maxAvaiableSkoSrBuys--;
        resetBuyButtons(false);
    }

    @FXML
    public void buySkChangeAwardAll(ActionEvent event) {
        shortSkillChange.buyChangeSkill(1, skChangeOnePrice);
        maxAvaiableSkoSrBuys--;
        resetBuyButtons(false);

    }

    @FXML
    public void buySkChangeAwardGLE(ActionEvent event) {
        shortSkillChange.buyChangeSkill(2, skChangeTwoPrice);
        maxAvaiableSkoSrBuys--;
        resetBuyButtons(false);
    }

    @FXML
    public void buySkChangeAwardGLO(ActionEvent event) {
        shortSkillChange.buyChangeSkill(3, skChangeThreePrice);
        maxAvaiableSkoSrBuys--;
        resetBuyButtons(false);
    }

    @FXML
    public void buySkChangeOPDice(ActionEvent event) {
        shortSkillChange.buyChangeSkill(4, skChangeFourPrice);
        maxAvaiableSkoSrBuys--;
        resetBuyButtons(false);
    }

    @FXML
    public void useOtherTurnSkill(ActionEvent event) {
        shortSkillChange.useChangeSkill();
    }

    @FXML
    public void buysRTTChangeDice(ActionEvent event) {
        specialRights.sRTTChangeDice(specialRightOnePrice);
        maxAvaiableSkoSrBuys--;
        resetBuyButtons(false);
        trueLabelSr1.setVisible(true);
    }

    @FXML
    public void buyLastRoleChangeDice(ActionEvent event) {
        specialRights.sRLastRoleChangeDice(specialRightTwoPrice);
        maxAvaiableSkoSrBuys--;
        resetBuyButtons(false);
        trueLabelSr2.setVisible(true);
    }

    private void setAttributeLabels(boolean sort) {
        if (sort) {
            new SortPlayerList().sortPlayerList(playerList);
        }
        new SetAttributeLabels().setAttributeLabels(playerList, attributeLabels);
    }

    @FXML
    public void buyPointChanger(ActionEvent event) {
        specialRights.sRPointChanger(specialRightThreePrice);
        maxAvaiableSkoSrBuys--;
        resetBuyButtons(false);
        skNormalButtonList[skNormalButtonListEnum.usePointChanger.ordinal()].setDisable(false);
        trueLabelSr3.setVisible(true);

    }

    @FXML
    public void useSkChangePoints() {
        shortSkillNormal.pointChanger(skNormalFourPrice);
        maxAvaiablepointChangeUses--;
        resetBuyButtons(false);

        if (!miniGameActive) {
            ranks.generateRanks(playerList);
            setAttributeLabels(true);
        }

        if (maxAvaiablepointChangeUses <= 0) {
            skNormalButtonList[skNormalButtonListEnum.usePointChanger.ordinal()].setDisable(true);

        }
    }
}

//	Label[] skNormalLabelList = { priceOfSkChangeDice, priceOfSkChangeName, priceOfChangePunishment,priceOfSRPointChanger };

//	Button[] skChangeButtonList = { buySkChangeAwardAll, buySkChangeAwardGLE, buySkChangeAwardGLO,buySkChangeOPDice, useOtherTurnSkill };

//	Label[] skChangeLabelList = { priceOfSkChangeAwardAll, priceOfSkChangeAwardGLE, priceOfSkChangeAwardGLO,priceOfSkChangeOPDice };

//	Button[] specialRightsButtonList = { buysRTTChangeDice, buyLastRoleChangeDice };

//	Label[] specialRightsLabelList = { priceOfTwoTimesChangeDice, priceOfLastRoleChangeDice,priceOfSRPointChanger };
