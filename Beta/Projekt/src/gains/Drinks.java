package gains;

import main.Player;
/**
 * 
 * @author Johannes Seith and Jakob Seith
 *
 */

public class Drinks {
	/**
	 * 
	 * @param playerList playerList to get the object
	 * @param drinks the drinks the player got
	 * @param playerNumber o select the right object of the objectlist
	 */

	public void givePlayerDrinks(Player[] playerList, int drinks, int playerNumber) {
		playerList[playerNumber - 1].setPoints(drinks + playerList[playerNumber - 1].getPoints());
	}

	
}
