package gains;

import main.Player;

/**
 * 
 * @author Johannes Seith and Jakob Seith
 *
 */

public class Ranks {

	/**
	 * To evaluate the rank of each player
	 * @param playerList to get the current objectlist
	 */
	
	public void generateRanks(Player[] playerList) {

		for (int i = 0; i < playerList.length; i++) {
			int rankCounter = playerList.length;
			for (int j = 0; j < playerList.length; j++) {
				if (playerList[i].getPoints() > playerList[j].getPoints()) {
					rankCounter--;
				}else if(playerList[i].getPoints() == playerList[j].getPoints() && playerList[i].getRank()< playerList[j].getRank()) {
					rankCounter--;
				}
				
			}
			setPlayerRank(playerList, rankCounter, i+1);
		}
	}
	
	/**
	 * To set the player rank
	 * @param playerList playerList to get the object
	 * @param rank the rank the player have to get
	 * @param playerNumber o select the right object of the objectlist
	 */
	private void setPlayerRank(Player[] playerList, int rank, int playerNumber) {
		playerList[playerNumber - 1].setRank(rank);
		playerList[playerNumber-1].setPlayerPosition(rank);
	}


	
}
