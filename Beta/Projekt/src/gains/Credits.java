package gains;

import main.Player;

/**
 * 
 * @author Johannes Seith and Jakob Seith
 *
 */

public class Credits {
	/**
	 * To set the new value of the credits.
	 * 
	 * @param playerList
	 *            to get the object
	 * @param value
	 *            the earned credits
	 * @param playerNumber
	 *            to select the right object of the objectlist
	 */
	public void givePlayerCredits(boolean mainGame, boolean game, boolean miniGame, int value, Player[] playerList, int playerNumber) {
		// One point are 10 credits.
		if (mainGame) {
			playerList[playerNumber - 1].setCredits(value * 10 + playerList[playerNumber - 1].getCredits());
		}
//One drink are 5 credits.
		if (miniGame) {
			playerList[playerNumber - 1].setCredits(value + playerList[playerNumber - 1].getCredits());
		}
		if(!mainGame && !game) {
			playerList[playerNumber - 1].setCredits( playerList[playerNumber - 1].getCredits() - value);
		}
	}
	
	

}
