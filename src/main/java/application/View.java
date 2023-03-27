package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class View extends Application {

    Model model;
    Controller controller;

    Label label;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        model = new Model(this);
        controller = new Controller(this, model);

        label = new Label();

        Button button = new Button("Click here!");
        button.setOnAction(event -> {controller.IncrementButton();});

        VBox vbox = new VBox(label, button);
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox, 640, 480);
        stage.setScene(scene);
        stage.show();

        UpdateText();
    }

    public void UpdateText() {
        label.setText("Value: " + model.GetValue());
    }
    
}
