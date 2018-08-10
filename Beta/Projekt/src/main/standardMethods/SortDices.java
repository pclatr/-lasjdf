package main.standardMethods;


public class SortDices {
    public void sortDices(int[] dices){
        int hold;
        for (int i = 0; i < dices.length; i++) {
            for (int j = dices.length - 1; j > 0; j--) {
                if (dices[j - 1] > dices[j]) {
                    hold = dices[j];
                    dices[j] = dices[j - 1];
                    dices[j - 1] = hold;
                }
            }
        }
    }
}
