package main.alertBox;


import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox implements EventHandler {
    private Stage alertBox;

    private Button confirmButton;
    private Button disConfirmButton;
    private Button closeButton;

    private boolean userInputInt;

    public static int userInputInteger;

    public static String userInputString;

    public static boolean confirm;

    public void information(String title, String message) {
        alertBox = new Stage();

        alertBox.initModality(Modality.APPLICATION_MODAL);
        alertBox.setTitle(title);
        alertBox.setMinWidth(250);
        alertBox.setMinHeight(150);

        Label label = new Label();
        label.setText(message);
        closeButton = new Button("Close");
        closeButton.setOnAction(e -> alertBox.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        alertBox.setScene(scene);
        alertBox.showAndWait();
    }

    public void confirm(String title, String message) {
        alertBox = new Stage();

        alertBox.initModality(Modality.APPLICATION_MODAL);
        alertBox.setTitle(title);
        alertBox.setMinWidth(250);
        alertBox.setMinHeight(150);

        Label label = new Label();
        label.setText(message);
        confirmButton = new Button("YES");
        confirmButton.setOnAction(this);
        disConfirmButton = new Button("NO");
        disConfirmButton.setOnAction(this);


        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, confirmButton, disConfirmButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        alertBox.setScene(scene);
        alertBox.showAndWait();
    }

    public void userInput(String title, String message, String textFieldTitle, boolean userInputInt) {
        alertBox = new Stage();

        alertBox.initModality(Modality.APPLICATION_MODAL);
        alertBox.setTitle(title);
        alertBox.setMinWidth(250);
        alertBox.setMinHeight(150);

        this.userInputInt = userInputInt;
        Label textLabel = new Label(message);
        TextField userInput = new TextField(textFieldTitle);
        Button confirmButton = new Button("OK");
        confirmButton.setOnAction(e -> evaluateUserInput(userInput));

        VBox layout = new VBox(10);
        layout.getChildren().addAll(userInput, textLabel, confirmButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        alertBox.setScene(scene);
        alertBox.showAndWait();
    }

    private void evaluateUserInput(TextField userInput) {
        if (userInputInt) {
            try {
                userInputInteger = Integer.parseInt(userInput.getText());
                userInput.setText(null);
                alertBox.close();
            } catch (NumberFormatException e) {
                new AlertBox().information("Error", "Please insert a number.");
            }
        } else {
            if (userInput.getText().equals("") || userInput.getText() == null) {
                new AlertBox().information("Error", "Bitte einen Namen eingeben.");
            } else {
                userInputString = userInput.getText();
                userInput.setText(null);
                alertBox.close();
            }
        }
    }

    @Override
    public void handle(Event event) {
        if (event.getSource() == confirmButton) {
            confirm = true;
            alertBox.close();
        } else if (event.getSource() == disConfirmButton) {
            confirm = false;
            alertBox.close();
        } else if (event.getSource() == null) {
            confirm = false;
            alertBox.close();
        }
    }
}
