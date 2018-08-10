package userInterface;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class Main extends Application {

	private Stage primaryStage;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		mainWindow();

	}

	public void mainWindow() {
		FXMLLoader loader;

		try {
			loader = new FXMLLoader(Main.class.getResource("MainWindow.fxml"));
			AnchorPane pane = loader.load();

			// secondStage.setMinHeight(400);
			// secondStage.setMinWidth(500);
			primaryStage.setMinHeight(400.00);
			primaryStage.setMinWidth(500.00);

			MainController mainController = loader.getController();
			mainController.setMain(this);

			Scene scene = new Scene(pane);

			// secondStage.setScene(scene);
			primaryStage.setScene(scene);
			primaryStage.show();
			// secondStage.show();
		} catch (IOException e) {
			e.printStackTrace();

		}

		
	}


	public static void main(String[] args) {
		launch(args);
	}
}
