package specialCombinations;

import javafx.scene.control.Label;
import main.Input;
import userInterface.MainController;

public class LuckyCombiGains {

    int amountOfGains = 3;

    Label[][] attributeLabels;

    public LuckyCombiGains(double selectedCombination, Label[][] attributeLabels) {
        this.attributeLabels = attributeLabels;

        selectGain((int) (selectedCombination * amountOfGains));

    }

    private void selectGain(int selectedCombination) {
        switch (selectedCombination) {
            case 0:
                gainZero();
                break;
            case 1:
                gainOne();
                break;
            case 2:
                gainTwo();
                break;

        }
    }

    private void gainZero() {
        MainController.holdCurrentPlayer.setCredits(MainController.holdCurrentPlayer.getCredits() + 150);
        MainController.holdCurrentPlayer.setPoints(MainController.holdCurrentPlayer.getPoints() + 10);

        System.out.println("Du hast die Lucky Combi gewürfelt und bekkommst 150 Credits und 10 Punkte.");
    }

    private void gainOne() {
        MainController.holdCurrentPlayer.setCredits(MainController.holdCurrentPlayer.getCredits() + 300);

        System.out.println("Du hast die Lucky Combi gewürfelt und bekkommst 300 Credits.");
    }

    private void gainTwo() {

        System.out.println("Du hast die Lucky Combi gewürfelt und darfst jetzt zwischen folgenden skills wählen:"
                + "/n" + "(1)Change Dice 2x (2)Change Punishment (3)Change Oponent Dice" + "/n" + "Gib die zahl des Skills ein!");

        int userInput = Input.intInput();
        switch (userInput) {
            case 1:
                MainController.holdCurrentPlayer.setSkChangeDice(MainController.holdCurrentPlayer.getSkChangeDice() + 2);
                break;
            case 2:
                MainController.holdCurrentPlayer
                        .setSkChangePunishment(MainController.holdCurrentPlayer.getSkChangePunishment() + 1);
                break;
            case 3:
                MainController.holdCurrentPlayer
                        .setSkChangeOPDice(MainController.holdCurrentPlayer.getSkChangeOPDice() + 1);

        }
    }

}
