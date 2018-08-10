package specialCombinations;

import javafx.scene.control.Label;
import userInterface.MainController;

public class BadCombiPunishments {

    private int amountOfPunishments = 3;

    Label[][] attributeLabels;

    public BadCombiPunishments(double selectedPunishment, Label[][] attributeLabels) {
        this.attributeLabels = attributeLabels;
        selectPunishment((int) (selectedPunishment * amountOfPunishments));

    }

    private void selectPunishment(int selectedPunishment) {
        switch (selectedPunishment) {
            case 0:
                punishmentZero();
                break;
            case 1:
                punishmentOne();
                break;
            case 2:
                punishmentTwo();
                break;

        }
    }

    private void punishmentZero() {
        MainController.holdCurrentPlayer.setCredits(MainController.holdCurrentPlayer.getCredits() - 150);
        MainController.holdCurrentPlayer.setPoints(MainController.holdCurrentPlayer.getPoints() - 10);

        System.out.println("Du hast die Bad Combination gewürfelt und verlierst damit 150 Credits und 10 Punkte.");
    }

    private void punishmentOne() {
        MainController.holdCurrentPlayer.setCredits(MainController.holdCurrentPlayer.getCredits() - 200);
        MainController.holdCurrentPlayer.setPoints(MainController.holdCurrentPlayer.getPoints() - 15);

        System.out.println("Du hast die Bad Combination gewürfelt und verlierst damit 200 Credits und 15 Punkte.");
    }

    private void punishmentTwo() {
        int amountOfSkills = MainController.amountOfSkNormal + MainController.amountOfSkChange - 1;
        int numberOfFirstSkill = 5;
        int currentPlayerTabelnumber;
        if (MainController.miniGameActive) {
            currentPlayerTabelnumber = MainController.currentPlayer - 1;
        } else {
            currentPlayerTabelnumber = MainController.holdCurrentPlayer.getRank() - 1;
        }
        int skillCounter = 0;
        String[] chosenableSkills = {"Change Dice", "Change Name", "Change Punishment", "Change Gains General",
                "Change Award Game Leader", "Change Award Game Loser", "Change Oponent Dice"};
        for (int i = numberOfFirstSkill; i < numberOfFirstSkill + amountOfSkills; i++) {

            if (Integer.parseInt(attributeLabels[currentPlayerTabelnumber][i].getText()) != 0) {
                if (skillCounter == 0) {
                    System.out.println(
                            "Du hast die Bad Combination gewürfelt, und musst nun einen deiner Skills abgeben." + "/n"
                                    + "Du kannst zwischen diesen Skills wählen: ");
                }
                skillCounter++;
                System.out.print("(" + (i - numberOfFirstSkill + 1) + ")" + chosenableSkills[i - numberOfFirstSkill]);
            } else if (skillCounter == 0 && i == numberOfFirstSkill + amountOfSkills - 1) {
                MainController.holdCurrentPlayer.setCredits(MainController.holdCurrentPlayer.getCredits() - 300);

                System.out.println("Du hast die Bad Combination gewürfelt und müsstets nun einen deiner Skills abgeben."
                        + "/n" + "Da du aber keine Skills hast, verlierst du nun 300 Credits;");
            }
        }
    }

}
