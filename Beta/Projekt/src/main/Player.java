package main;

import main.alertBox.AlertBox;

public class Player {

    /**
     * create a person
     */

    public static enum playerAttributesEnum{
        name,points, credits, drinks, rank, skChangeDice, skChangeName, skChangePunishment,
        skChangeGainsG, skChangeAwardGLE, skChangeAwardGLO, skChangeOPDice
    }
    private String name;
    private int points, credits, drinks, rank, playerPosition, skChangeDice, skChangeName, skChangePunishment,
            skChangeGainsG, skChangeAwardGLE, skChangeAwardGLO, skChangeOPDice;

    private boolean sRTTChangeDice, sRlastRoleChangeDice, sRPointsChanger;

    public static int amountOfPlayers;

    public Player() {
    }

    /*
     * Constructor to generate a player
     */

    public Player(String name, int points, int credits, int drinks, int rank, int playerPosition, int skChangeDice,
                  int skChangeName, int skChangePunishment, int skChangeGainsG, int skChangeAwardGLE, int skChangeAwardGLO,
                  int skChangeOPDice, boolean sRTTChangeDice, boolean sRlastRoleChangeDice, boolean sRPointsChanger) {

        this.name = name;
        this.points = points;
        this.credits = credits;
        this.drinks = drinks;
        this.rank = rank;
        this.playerPosition = playerPosition;
        this.skChangeDice = skChangeDice;
        this.skChangeName = skChangeName;
        this.skChangePunishment = skChangePunishment;
        this.skChangeGainsG = skChangeGainsG;
        this.skChangeAwardGLE = skChangeAwardGLE;
        this.skChangeAwardGLO = skChangeAwardGLO;
        this.skChangeOPDice = skChangeOPDice;
        this.sRTTChangeDice = sRTTChangeDice;
        this.sRlastRoleChangeDice = sRlastRoleChangeDice;
        this.sRPointsChanger = sRPointsChanger;

    }

    /**
     * Method to generate a player and a playerlist with all objects. Also in this
     * method will be called the constructor to set the attributes of a standard
     * value. In addition there are a test section, where 2 prepared players will be
     * created.
     *
     * @return the method returns a playerlist with all generated objects.
     */
    public Player[] generatePlayer() {
        Player[] playerList = new Player[amountOfPlayers];

        // Scanner MainConr = new Scanner(System.in);
        int loopCounter = 0;
        do {

            if (loopCounter == 0) {

                new AlertBox().userInput("Anzahl Spieler",null,"Spieleranzhal",true);
            } else {

                new AlertBox().information("Error","Eine Zahl zwischen 2 und 12 eingeben.");
                new AlertBox().userInput("Anzahl Spieler",null,"Spieleranzhal",true);
            }
            amountOfPlayers = AlertBox.userInputInteger;

            loopCounter++;
        } while (!(2 <= amountOfPlayers && 12 >= amountOfPlayers));

        playerList = new Player[amountOfPlayers];

        // Create objects Player in playerList
        for (int i = 0; i < amountOfPlayers; i++) {


            new AlertBox().userInput("Spielernamen eingeben",null,"Bitte Spielernamen " + (i+1) + " eingeben",false);

            String playerName = AlertBox.userInputString;

           /* while (playerName.equals("")) {
                playerName = Input.stringInput();
            }*/
            playerList[i] = new Player(playerName, 0, 0, 0, i + 1, i+1, 0, 0, 0, 0, 0, 0, 0, false, false, false);
        }

        // MainController.scannerclose();

        return playerList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getDrinks() {
        return drinks;
    }

    public void setDrinks(int drinks) {
        this.drinks = drinks;
    }

    public int getSkChangeDice() {
        return skChangeDice;
    }

    public void setSkChangeDice(int skChangeDice) {
        this.skChangeDice = skChangeDice;
    }

    public int getSkChangeName() {
        return skChangeName;
    }

    public void setSkChangeName(int skChangeName) {
        this.skChangeName = skChangeName;
    }

    public int getSkChangePunishment() {
        return skChangePunishment;
    }

    public void setSkChangePunishment(int skChangePunishment) {
        this.skChangePunishment = skChangePunishment;
    }

    public int getSkChangeGainsG() {
        return skChangeGainsG;
    }

    public void setSkChangeGainsG(int skChangeGainsG) {
        this.skChangeGainsG = skChangeGainsG;
    }

    public int getSkChangeAwardGLE() {
        return skChangeAwardGLE;
    }

    public void setSkChangeAwardGLE(int skChangeAwardGLE) {
        this.skChangeAwardGLE = skChangeAwardGLE;
    }

    public int getSkChangeAwardGLO() {
        return skChangeAwardGLO;
    }

    public void setSkChangeAwardGLO(int skChangeAwardGLO) {
        this.skChangeAwardGLO = skChangeAwardGLO;
    }

    public int getSkChangeOPDice() {
        return skChangeOPDice;
    }

    public void setSkChangeOPDice(int skChangeOPDice) {
        this.skChangeOPDice = skChangeOPDice;
    }

    public int getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(int playerPosition) {
        this.playerPosition = playerPosition;
    }

    public boolean getSRTTChangeDice() {
        return sRTTChangeDice;
    }

    public void setSRTTChangeDice(boolean sRTTChangeDice) {
        this.sRTTChangeDice = sRTTChangeDice;
    }

    public boolean getSRLastRoleChangeDice() {
        return sRlastRoleChangeDice;
    }

    public void setSRLastRoleChangeDice(boolean lastRoleChangeDice) {
        this.sRlastRoleChangeDice = lastRoleChangeDice;
    }

    public boolean getSRPointsChanger() {
        return sRPointsChanger;
    }

    public void setSRPointsChanger(boolean sRPointsChanger) {
        this.sRPointsChanger = sRPointsChanger;
    }

}
