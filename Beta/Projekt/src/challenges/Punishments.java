package challenges;

import main.alertBox.AlertBox;
import gains.Ranks;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import main.MainGameMethods;
import main.Player;
import userInterface.MainController;


public class Punishments extends ChallengeMainMethods {
    public Punishments(Player[] playerList, Label[][] attributeLabels, Label[] cubeList, Label[] labelList,
                       CheckBox[] checkBoxList, Button[] buttonList) {
        super(playerList, attributeLabels, cubeList, labelList, checkBoxList, buttonList);
        // TODO Auto-generated constructor stub
    }

    Ranks ranks = new Ranks();

    // Methode f?r jede Challenge schreiben

    public void punishments(int selectedCombination, boolean usedSkill) {
        if (selectedCombination == 0) {
            combinationZero(usedSkill);
        } else if (selectedCombination == 1) {
            combinationOne(usedSkill);

        } else if (selectedCombination == 2) {
//In Check initialisiert

        } else if (selectedCombination == 3) {
            combinationThree(usedSkill);

        } else if (selectedCombination == 4) {
            combinationFour(usedSkill);

        } else if (selectedCombination == 5) {
            combinationFive(usedSkill);

        } else if (selectedCombination == 6) {
            combinationSix(usedSkill);

        } else if (selectedCombination == 7) {
            combinationSeven(usedSkill);

        } else if (selectedCombination == 8) {
            combinationEight(usedSkill);

        }
    }

    private void combinationZero(boolean usedSkill) {
        if (usedSkill) {
            MainController.holdCurrentPlayer.setPoints(MainController.holdCurrentPlayer.getPoints() + 2);
            MainController.holdCurrentPlayer.setCredits(MainController.holdCurrentPlayer.getCredits() + 30);

            MainController.lastPoints = 2;
            MainController.lastCredits = 30;

            ranks.generateRanks(MainController.playerInOrder);

            new AlertBox().information("Skill eingesetzt", "Dank deinen Skill hast du nun " + "2 Punkte und 30 Credits bekommen, hat sich eher nicht gelohnt."
                    + "\n" + MainController.holdCurrentPlayer.getRank() + "ten Rang!" + "\n"
                    + MainController.playerInOrder[MainController.nextPlayer - 1].getName() + " ist dran.");

            MainController.holdCurrentPlayer.setSkChangePunishment(MainController.holdCurrentPlayer.getSkChangePunishment() - 1);

            if (MainController.miniGameActive) {
                attributeLabels[MainController.currentPlayer - 1][7].setText(String.valueOf(MainController.holdCurrentPlayer.getSkChangePunishment()));
            } else {
                attributeLabels[MainController.holdCurrentPlayer.getRank() - 1][7].setText(String.valueOf(MainController.holdCurrentPlayer.getSkChangePunishment()));

            }
        } else {
            MainController.holdCurrentPlayer.setPoints(MainController.holdCurrentPlayer.getPoints() - 2);
            MainController.holdCurrentPlayer.setCredits(MainController.holdCurrentPlayer.getCredits() - 30);

            ranks.generateRanks(MainController.playerInOrder);


            new AlertBox().information("Nicht bestanden", "Du hast die Challenge nicht bestanden, wirst bestaft und bist jetzt auf dem "
                    + MainController.holdCurrentPlayer.getRank() + "ten Rang!" + "\n"
                    + MainController.playerInOrder[MainController.nextPlayer - 1].getName() + " ist dran.");
        }
    }

    private void combinationOne(boolean usedSkill) {
        if (usedSkill) {
            MainController.holdCurrentPlayer.setPoints(MainController.holdCurrentPlayer.getPoints() + 6);
            MainController.holdCurrentPlayer.setCredits(MainController.holdCurrentPlayer.getCredits() + 80);

            MainController.lastPoints = 6;
            MainController.lastCredits = 80;

            ranks.generateRanks(MainController.playerInOrder);


            new AlertBox().information("Skill eingesetzt", "Dank deinen Skill hast du nun "
                    + "6 Punkte und 80 Credits bekommen, Gl?ckwunsch, oder vielleicht auch nicht." + "\n"
                    + MainController.holdCurrentPlayer.getRank() + "ten Rang!" + "\n"
                    + MainController.playerInOrder[MainController.nextPlayer - 1].getName() + " ist dran.");
            MainController.holdCurrentPlayer.setSkChangePunishment(MainController.holdCurrentPlayer.getSkChangePunishment() - 1);

            if (MainController.miniGameActive) {
                attributeLabels[MainController.currentPlayer - 1][7].setText(String.valueOf(MainController.holdCurrentPlayer.getSkChangePunishment()));
            } else {
                attributeLabels[MainController.holdCurrentPlayer.getRank() - 1][7].setText(String.valueOf(MainController.holdCurrentPlayer.getSkChangePunishment()));

            }

        } else {
            MainController.holdCurrentPlayer.setPoints(MainController.holdCurrentPlayer.getPoints() - 6);
            MainController.holdCurrentPlayer.setCredits(MainController.holdCurrentPlayer.getCredits() - 80);

            ranks.generateRanks(MainController.playerInOrder);


            new AlertBox().information("Nicht bestanden", "Du hast die Challenge nicht bestanden, wirst bestaft und bist jetzt auf dem "
                    + MainController.holdCurrentPlayer.getRank() + "ten Rang!" + "\n"
                    + MainController.playerInOrder[MainController.nextPlayer - 1].getName() + " ist dran.");
        }
    }

    private void combinationThree(boolean usedSkill) {
        if (usedSkill) {
            MainController.holdCurrentPlayer.setPoints(MainController.holdCurrentPlayer.getPoints()
                    + MainGameMethods.currentDices[MainGameMethods.currentDices.length - 1] * 2);

            MainController.lastPoints = MainGameMethods.currentDices[MainGameMethods.currentDices.length - 1] * 2;
            MainController.lastCredits = 0;

            ranks.generateRanks(MainController.playerInOrder);


            new AlertBox().information("Skill eingesetzt", "Dank deinen Skill hast du nun "
                    + (MainGameMethods.currentDices[MainGameMethods.currentDices.length - 1] * 2)
                    + " Punkte bekommen.\n" + MainController.holdCurrentPlayer.getRank() + "ten Rang!" + "\n"
                    + MainController.playerInOrder[MainController.nextPlayer - 1].getName() + " ist dran.");
            MainController.holdCurrentPlayer.setSkChangePunishment(MainController.holdCurrentPlayer.getSkChangePunishment() - 1);

            if (MainController.miniGameActive) {
                attributeLabels[MainController.currentPlayer - 1][7].setText(String.valueOf(MainController.holdCurrentPlayer.getSkChangePunishment()));
            } else {
                attributeLabels[MainController.holdCurrentPlayer.getRank() - 1][7].setText(String.valueOf(MainController.holdCurrentPlayer.getSkChangePunishment()));

            }
        } else {
            MainController.holdCurrentPlayer.setPoints(MainController.holdCurrentPlayer.getPoints()
                    - MainGameMethods.currentDices[MainGameMethods.currentDices.length - 1] * 2);
            ranks.generateRanks(MainController.playerInOrder);


            new AlertBox().information("Nicht bestanden", "Du hast verloren und verlierst "
                    + (MainGameMethods.currentDices[MainGameMethods.currentDices.length - 1] * 2)
                    + " Punkte. du bist nun auf dem " + MainController.holdCurrentPlayer.getRank() + "ten Rang.\n"
                    + MainController.playerInOrder[MainController.nextPlayer - 1].getName() + " ist dran.");
        }
    }

    private void combinationFour(boolean usedSkill) {
        if (usedSkill) {
            MainController.holdCurrentPlayer.setPoints(MainController.holdCurrentPlayer.getPoints()
                    + (MainGameMethods.currentDices[MainGameMethods.currentDices.length - 3]
                    + MainGameMethods.currentDices[MainGameMethods.currentDices.length - 2]
                    + MainGameMethods.currentDices[MainGameMethods.currentDices.length - 1]));

            MainController.lastPoints = (MainGameMethods.currentDices[MainGameMethods.currentDices.length - 3]
                    + MainGameMethods.currentDices[MainGameMethods.currentDices.length - 2]
                    + MainGameMethods.currentDices[MainGameMethods.currentDices.length - 1]);
            MainController.lastCredits = 0;

            ranks.generateRanks(MainController.playerInOrder);


            new AlertBox().information("Skill eingesetzt", "Dank deinen Skill hast du nun "
                    + (MainGameMethods.currentDices[MainGameMethods.currentDices.length - 3]
                    + MainGameMethods.currentDices[MainGameMethods.currentDices.length - 2]
                    + MainGameMethods.currentDices[MainGameMethods.currentDices.length - 1])
                    + " Punkte bekommen.\n" + MainController.holdCurrentPlayer.getRank() + "ten Rang!" + "\n"
                    + MainController.playerInOrder[MainController.nextPlayer - 1].getName() + " ist dran.");

            MainController.holdCurrentPlayer.setSkChangePunishment(MainController.holdCurrentPlayer.getSkChangePunishment() - 1);

            if (MainController.miniGameActive) {
                attributeLabels[MainController.currentPlayer - 1][7].setText(String.valueOf(MainController.holdCurrentPlayer.getSkChangePunishment()));
            } else {
                attributeLabels[MainController.holdCurrentPlayer.getRank() - 1][7].setText(String.valueOf(MainController.holdCurrentPlayer.getSkChangePunishment()));

            }
        } else {

            MainController.holdCurrentPlayer.setPoints(MainController.holdCurrentPlayer.getPoints()
                    - (MainGameMethods.currentDices[MainGameMethods.currentDices.length - 3]
                    + MainGameMethods.currentDices[MainGameMethods.currentDices.length - 2]
                    + MainGameMethods.currentDices[MainGameMethods.currentDices.length - 1]));

            ranks.generateRanks(MainController.playerInOrder);


            new AlertBox().information("Nicht bestanden", "Du hast verloren und verlierst "
                    + (MainGameMethods.currentDices[MainGameMethods.currentDices.length - 3]
                    + MainGameMethods.currentDices[MainGameMethods.currentDices.length - 2]
                    + MainGameMethods.currentDices[MainGameMethods.currentDices.length - 1])
                    + " Punkte. Du bist nun auf dem " + MainController.holdCurrentPlayer.getRank() + "ten Rang.\n"
                    + MainController.playerInOrder[MainController.nextPlayer - 1].getName() + " ist dran.");
        }
    }

    private void combinationFive(boolean usedSkill) {
        if (usedSkill) {

            MainController.lastPoints = 0;
            MainController.lastCredits = 0;


            new AlertBox().information("Skill eingesetzt", "Dank deinem Skill bist du nun nicht letzter und hast evt. mehr als 30 Credits.\n"
                    + MainController.playerInOrder[MainController.nextPlayer - 1].getName() + " ist dran.");
            MainController.holdCurrentPlayer.setSkChangePunishment(MainController.holdCurrentPlayer.getSkChangePunishment() - 1);

            if (MainController.miniGameActive) {
                attributeLabels[MainController.currentPlayer - 1][7].setText(String.valueOf(MainController.holdCurrentPlayer.getSkChangePunishment()));
            } else {
                attributeLabels[MainController.holdCurrentPlayer.getRank() - 1][7].setText(String.valueOf(MainController.holdCurrentPlayer.getSkChangePunishment()));

            }
        } else {
            MainController.holdCurrentPlayer
                    .setPoints(playerList[MainController.playerInOrder.length - 1].getPoints() - 1);
            MainController.holdCurrentPlayer.setCredits(30);

            ranks.generateRanks(MainController.playerInOrder);


            new AlertBox().information("Nicht bestanden", "Du hast deine Challenge bestanden und dir damit keinen Gefallen getan. "
                    + "\nNun wanderst du auf den letzten Platz und zwar mit 30 Credits." + "\n"
                    + MainController.playerInOrder[MainController.nextPlayer - 1].getName() + " ist dran.");
        }
    }

    private void combinationSix(boolean usedSkill) {
        if (usedSkill) {
            int sum = 0;
            for (int i = 0; i < MainGameMethods.currentDices.length; i++) {
                sum += MainGameMethods.currentDices[i];
            }
            MainController.holdCurrentPlayer.setCredits(MainController.holdCurrentPlayer.getCredits() + (sum * 5));

            MainController.lastPoints = 0;
            MainController.lastCredits = (sum * 5);


            new AlertBox().information("Skill eingesetzt", "Dank deinen Skill hast du nun " + (sum * 5) + " Credits bekommen.\n" + "\n"
                    + MainController.playerInOrder[MainController.nextPlayer - 1].getName() + " ist dran.");

            MainController.holdCurrentPlayer.setSkChangePunishment(MainController.holdCurrentPlayer.getSkChangePunishment() - 1);

            if (MainController.miniGameActive) {
                attributeLabels[MainController.currentPlayer - 1][7].setText(String.valueOf(MainController.holdCurrentPlayer.getSkChangePunishment()));
            } else {
                attributeLabels[MainController.holdCurrentPlayer.getRank() - 1][7].setText(String.valueOf(MainController.holdCurrentPlayer.getSkChangePunishment()));

            }
        } else {

            int sum = 0;
            for (int i = 0; i < MainGameMethods.currentDices.length; i++) {
                sum += MainGameMethods.currentDices[i];
            }
            MainController.holdCurrentPlayer.setCredits(MainController.holdCurrentPlayer.getCredits() - (sum * 5));


            new AlertBox().information("Nicht bestanden", "Du hast verloren und verlierst damit " + (sum * 5) + " Credits. " + "\n"
                    + MainController.playerInOrder[MainController.nextPlayer - 1].getName() + " ist dran.");
        }
    }

    private void combinationSeven(boolean usedSkill) {
        if (usedSkill) {
            if (MainController.currentPlayer == 1) {
                MainController.holdCurrentPlayer.setCredits(MainController.holdCurrentPlayer.getCredits() + 200);

                MainController.lastPoints = 0;
                MainController.lastCredits = 200;


                new AlertBox().information("Skill eingesetzt", "Dank deinen Skill bist du nun nicht der Game Loser und bekommst 200 Credits.\n"
                        + "\n" + MainController.playerInOrder[MainController.nextPlayer - 1].getName() + " ist dran.");
                MainController.holdCurrentPlayer.setSkChangePunishment(MainController.holdCurrentPlayer.getSkChangePunishment() - 1);

                if (MainController.miniGameActive) {
                    attributeLabels[MainController.currentPlayer - 1][7].setText(String.valueOf(MainController.holdCurrentPlayer.getSkChangePunishment()));
                } else {
                    attributeLabels[MainController.holdCurrentPlayer.getRank() - 1][7].setText(String.valueOf(MainController.holdCurrentPlayer.getSkChangePunishment()));

                }
                super.chooseNextPlayer();

            } else {
                MainController.holdCurrentPlayer.setCredits(MainController.holdCurrentPlayer.getCredits() + 200);
                MainController.holdCurrentPlayer.setPoints(MainController.holdCurrentPlayer.getPoints() + 10);

                ranks.generateRanks(MainController.playerInOrder);


                new AlertBox().information("Skill eingesetzt", "Dank deinen Skill hast du nun 200 Credits und 10 Punkte bekommen.\n"
                        + MainController.holdCurrentPlayer.getRank() + "ten Rang!" + "\n"
                        + MainController.playerInOrder[MainController.nextPlayer - 1].getName() + " ist dran.");
            }
        } else {
            if (MainController.currentPlayer == 1) {
                MainController.holdCurrentPlayer.setCredits(MainController.holdCurrentPlayer.getCredits() - 200);
                MainController.holdCurrentPlayer
                        .setPoints(playerList[MainController.playerInOrder.length - 1].getPoints() - 1);

                new AlertBox().information("Nicht bestanden", "Du hast den zweiten Teil nicht bestanden und da du der Game Leader bist, wirst du jetzt zum Game Loser"
                        + "\nund verlierst 200 Credits." + "\n"
                        + MainController.playerInOrder[MainController.nextPlayer - 1].getName() + " ist dran.");
                ranks.generateRanks(playerList);
                super.chooseNextPlayer();

            } else {
                MainController.holdCurrentPlayer.setCredits(MainController.holdCurrentPlayer.getCredits() - 200);
                MainController.holdCurrentPlayer.setPoints(MainController.holdCurrentPlayer.getPoints() - 10);
                ranks.generateRanks(playerList);


                new AlertBox().information("Nicht bestanden", "Du hast die Challenge nicht bestanden und verlierst 10 Punkte und 200 Credits."
                        + "\nDu bist auf dem " + MainController.holdCurrentPlayer.getRank() + "ten Rang." + "\n"
                        + MainController.playerInOrder[MainController.nextPlayer - 1].getName() + " ist dran.");
            }
        }
    }

    private void combinationEight(boolean usedSkill) {
        if (usedSkill) {
            int sum = 0;
            for (int dice : MainGameMethods.currentDices) {
                sum += dice;
            }

            MainController.holdCurrentPlayer.setCredits(MainController.holdCurrentPlayer.getCredits() + sum * 15);

            MainController.lastCredits = sum * 15;

            if (Math.random() <= 0.3) {

                MainController.holdCurrentPlayer
                        .setPoints(MainController.holdCurrentPlayer.getPoints() + playerList[0].getPoints());

                MainController.lastPoints = playerList[0].getPoints();

                ranks.generateRanks(playerList);


                new AlertBox().information("Skill eingesetzt", "Also, du hast gerade durch deinen Skill " + (sum * 15) + " Credits und "
                        + playerList[0].getPoints() + " Punkte bekommen." + "\nDu bist nun auf dem "
                        + MainController.holdCurrentPlayer.getRank() + "ten Rang." + "\n"
                        + MainController.playerInOrder[MainController.nextPlayer - 1].getName() + " ist dran.");
                MainController.holdCurrentPlayer.setSkChangePunishment(MainController.holdCurrentPlayer.getSkChangePunishment() - 1);

                if (MainController.miniGameActive) {
                    attributeLabels[MainController.currentPlayer - 1][7].setText(String.valueOf(MainController.holdCurrentPlayer.getSkChangePunishment()));
                } else {
                    attributeLabels[MainController.holdCurrentPlayer.getRank() - 1][7].setText(String.valueOf(MainController.holdCurrentPlayer.getSkChangePunishment()));

                }
            } else {
                int holder = MainController.holdCurrentPlayer.getPoints();
                MainController.holdCurrentPlayer.setPoints(MainController.holdCurrentPlayer.getPoints() * 2);

                MainController.lastPoints = MainController.holdCurrentPlayer.getPoints() * 2;

                ranks.generateRanks(playerList);


                new AlertBox().information("Skill eingesetzt", "Du hast gerade durch deinen Skill " + holder + " Punkte und " + (sum * 15)
                        + " Credits bekommen." + "\nDu bist nun auf dem " + MainController.holdCurrentPlayer.getRank()
                        + "ten Rang." + "\n" + MainController.playerInOrder[MainController.nextPlayer - 1].getName()
                        + " ist dran.");


                MainController.holdCurrentPlayer.setSkChangePunishment(MainController.holdCurrentPlayer.getSkChangePunishment() - 1);

                if (MainController.miniGameActive) {
                    attributeLabels[MainController.currentPlayer - 1][7].setText(String.valueOf(MainController.holdCurrentPlayer.getSkChangePunishment()));
                } else {
                    attributeLabels[MainController.holdCurrentPlayer.getRank() - 1][7].setText(String.valueOf(MainController.holdCurrentPlayer.getSkChangePunishment()));

                }
            }

        } else {
            new AlertBox().information("Nicht bestanden", "Naja, was soll ich da sagen. Du hast jetzt eben verloren und, hm, eigentlich ganz witzig, ich suche mir jetzt aus, ob du die Punkte des "
                    + MainController.holdCurrentPlayer.getRank() + "st platzierten Spielers verlierst,"
                    + "\noder die, des 1st platzierten."
                    + "\nCredits verlierst du jetzt so oder so in H?he der Summe aller W?rfel mal 15.");
            int sum = 0;
            for (int dice : MainGameMethods.currentDices) {
                sum += dice;
            }

            MainController.holdCurrentPlayer.setCredits(MainController.holdCurrentPlayer.getCredits() - sum * 15);
            if (Math.random() <= 0.3) {

                MainController.holdCurrentPlayer
                        .setPoints(MainController.holdCurrentPlayer.getPoints() - playerList[0].getPoints());

                ranks.generateRanks(playerList);


                new AlertBox().information("Nicht bestanden", "Also, du hast gerade " + (sum * 15) + " Credits und " + playerList[0].getPoints()
                        + " Punkte verloren." + "\nDu bist nun auf dem " + MainController.holdCurrentPlayer.getRank()
                        + "ten Rang." + "\n" + MainController.playerInOrder[MainController.nextPlayer - 1].getName()
                        + " ist dran.");
            } else {
                int holder = MainController.holdCurrentPlayer.getPoints();
                MainController.holdCurrentPlayer.setPoints(0);
                ranks.generateRanks(playerList);

                new AlertBox().information("Nicht bestanden", "Du hast gerade " + holder + " Punkte und " + (sum * 15) + " Credits verloren."
                        + "\nDu bist nun auf dem " + MainController.holdCurrentPlayer.getRank() + "ten Rang." + "\n"
                        + MainController.playerInOrder[MainController.nextPlayer - 1].getName() + " ist dran.");

            }

        }
    }

}
