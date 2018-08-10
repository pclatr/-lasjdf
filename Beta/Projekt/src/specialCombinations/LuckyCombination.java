package specialCombinations;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import main.MainGameMethods;

public class LuckyCombination {

    Label[][] attributeLabels;

    int[] luckyCombination = new int[6];

    public LuckyCombination(Label[][] attributeLabels) {
        this.attributeLabels = attributeLabels;
    }

    public int[] generateLuckyCombination(boolean generateNewCombination) {

        if (generateNewCombination) {
            for (int i = 0; i < luckyCombination.length; i++) {
                luckyCombination[i] = (int) (Math.random() * 6 + 1);
            }
        }
        int hold;
        for (int i = 0; i < luckyCombination.length; i++) {
            for (int j = luckyCombination.length - 1; j > 0; j--) {

                if (luckyCombination[j - 1] > luckyCombination[j]) {
                    hold = luckyCombination[j];
                    luckyCombination[j] = luckyCombination[j - 1];
                    luckyCombination[j - 1] = hold;
                }

            }
        }
        return luckyCombination;
    }

    public boolean checkCombination(CheckBox[] checkBoxValues) {
        boolean check = false;
        boolean[] diceCheck = {false, false, false, false, false, false};
        int trueCounter = 0;

        for (int i = 0; i < luckyCombination.length; i++) {
            for (int j = 0; j < luckyCombination.length; j++) {
                if (luckyCombination[i] == MainGameMethods.currentDices[j] && !diceCheck[j]) {
                    diceCheck[j] = true;
                    trueCounter++;
                    break;
                } else if (j == luckyCombination.length - 1) {
                    i = luckyCombination.length;
                    break;
                }
            }
        }


        if (trueCounter == diceCheck.length) {

            new LuckyCombiGains(Math.random(), attributeLabels);
        }

        // Test
//			System.out.println("Lucky " + check);
        return check;

    }

}
