package specialRights;


import javafx.scene.control.Button;
import javafx.scene.control.Label;
import main.MainGameMethods;
import main.Player;
import userInterface.MainController;

public class SpecialRights {

	MainGameMethods mainGame = new MainGameMethods();

	Player[] playerList;

	Label[][] attributeLabels;

	Label[] specialRightsLabelList;
	Label[] cubeList;

	Button[] specialRightsButtonList;

	public SpecialRights(Player[] playerList, Label[] cubeList, Label[][] attributeLabels,
			Label[] specialRightsLabelList, Button[] specialRightsButtonList) {
		this.playerList = playerList;
		this.cubeList = cubeList;
		this.attributeLabels = attributeLabels;
		this.specialRightsLabelList = specialRightsLabelList;
		this.specialRightsButtonList = specialRightsButtonList;
	}

	public void sRTTChangeDice(int costs) {
		if (MainController.holdCurrentPlayer.getCredits() >= costs) {

			MainController.holdCurrentPlayer.setCredits(MainController.holdCurrentPlayer.getCredits() - costs);

			attributeLabels[MainController.holdCurrentPlayer.getRank() - 1][2]

					.setText(String.valueOf(MainController.holdCurrentPlayer.getCredits()));

			MainController.holdCurrentPlayer.setSRTTChangeDice(true);

			specialRightsButtonList[MainController.specialRightsButtonListEnum.buysRTTChangeDice.ordinal()].setDisable(true);

		} else {
			System.out.println("Du hast nicht genug Credits!");
		}
	}

	public void sRLastRoleChangeDice(int costs) {

		if (MainController.holdCurrentPlayer.getCredits() >= costs) {

			MainController.holdCurrentPlayer.setCredits(MainController.holdCurrentPlayer.getCredits() - costs);
			attributeLabels[MainController.holdCurrentPlayer.getRank() - 1][2]
					.setText(String.valueOf(MainController.holdCurrentPlayer.getCredits()));

			MainController.holdCurrentPlayer.setSRLastRoleChangeDice(true);

			specialRightsButtonList[MainController.specialRightsButtonListEnum.buyLastRoleChangeDice.ordinal()].setDisable(true);

		} else {
			System.out.println("Du hast nicht genug Credits!");
		}
	}

	public void sRPointChanger(int costs) {
		if (MainController.holdCurrentPlayer.getCredits() >= costs) {

			MainController.holdCurrentPlayer.setCredits(MainController.holdCurrentPlayer.getCredits() - costs);

			attributeLabels[MainController.holdCurrentPlayer.getRank() - 1][2]
					.setText(String.valueOf(MainController.holdCurrentPlayer.getCredits()));

			MainController.holdCurrentPlayer.setSRPointsChanger(true);
			

		} else {
			System.out.println("Du hast nicht genug Credits!");
		}
	}
}
