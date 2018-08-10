package gains;

import main.Player;
/**
 * 
 * @author Johannes Seith and Jakob Seith
 *
 */

public class Points {
	private int points;

	public Points() {
		points = 0;
	}
/**
 * 
 * @param MainGame to decide which manner of game to player played 
 * @param game to decide which manner of game to player played 
 * @param miniGameOrCredits to decide which manner of game to player played 
 * @param value 
 * @param playerList to get the object
 * @param playerNumber to decide which object gained the points
 */
	public void setPlayerPoints(boolean MainGame, boolean game, boolean miniGameOrCredits, int value,
			Player[] playerList, int playerNumber) {

		if (MainGame) {
			givePlayerPoints(playerList, value, playerNumber);

		} else if (game) {
			if (miniGameOrCredits) {
				givePlayerPoints(playerList, calcMinigame(value), playerNumber);

			} else if (!miniGameOrCredits) {
				givePlayerPoints(playerList, calcChallenge(value), playerNumber);

			}
		} else if (miniGameOrCredits) {
			givePlayerPoints(playerList, calcCreditPoints(value), playerNumber);

		} else {
			givePlayerPoints(playerList, calcDrinkPoints(value), playerNumber);

		}

	}

	public void givePlayerPoints(Player[] playerList, int points, int playerNumber) {
		playerList[playerNumber - 1].setPoints(points + playerList[playerNumber - 1].getPoints());
	}

	private int calcMinigame(int value) {
		points = value;

		return points;

	}

	private int calcChallenge(int value) {
		points = 5 * value;

		return points;
	}

	private int calcCreditPoints(int credits) {
		points = 2 * credits;

		return points;
	}

	private int calcDrinkPoints(int drinks) {
		points = 3 * drinks;

		return points;
	}
}
