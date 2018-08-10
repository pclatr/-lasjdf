package main.standardMethods;

import main.Player;

public class SortPlayerList {

    public void sortPlayerList(Player[] playerList){
        Player holdPlayer;
        for (int i = 0; i < playerList.length; i++) {
            for (int j = playerList.length - 1; j > 0; j--) {
                if (playerList[j - 1].getRank() > playerList[j].getRank()) {
                    holdPlayer = playerList[j];
                    playerList[j] = playerList[j - 1];
                    playerList[j - 1] = holdPlayer;
                }
            }
        }
    }
}
