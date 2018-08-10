package specialCombinations;

import javafx.scene.control.Label;
import main.MainGameMethods;

public class BadCombination {
    int[] badCombination = new int[6];

    Label[][] attributeLabels;

    public BadCombination(Label[][] attributeLabels) {
        this.attributeLabels = attributeLabels;

    }

    public int[] generatebadCombination(boolean generateNewCombination) {

        if (generateNewCombination) {
            for (int i = 0; i < badCombination.length; i++) {
                badCombination[i] = (int) (Math.random() * 6 + 1);
            }
        }

        int hold;
        for (int i = 0; i < badCombination.length; i++) {
            for (int j = badCombination.length - 1; j > 0; j--) {

                if (badCombination[j - 1] > badCombination[j]) {
                    hold = badCombination[j];
                    badCombination[j] = badCombination[j - 1];
                    badCombination[j - 1] = hold;
                }
            }
        }
        return badCombination;
    }

    public boolean checkCombination() {
        boolean check = false;
        boolean[] diceCheck = {false, false, false, false, false, false};
        int trueCounter = 0;

        for (int i = 0; i < badCombination.length; i++) {
            for (int j = 0; j < badCombination.length; j++) {
                if (badCombination[i] == MainGameMethods.currentDices[j] && !diceCheck[j]) {
                    diceCheck[j] = true;
                    trueCounter++;
                    break;
                } else if (j == badCombination.length - 1) {
                    i = badCombination.length;
                    break;
                }
            }
        }

        if (trueCounter == diceCheck.length) {

            new BadCombiPunishments(Math.random(), attributeLabels);

            check = true;
        }

        return check;
    }
}
