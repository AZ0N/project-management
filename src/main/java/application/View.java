package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
        Scene scene;

        try {
            // Load outer scene and controller using FXML
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ui.fxml"));
            scene = fxmlLoader.load();
            controller = (Controller) fxmlLoader.getController();
        }
        catch (Exception e) {
            e.printStackTrace();
            return;
        }

        controller.SetModelAndView(model, this);
        label = controller.GetLabel();
        
        stage.setScene(scene);
        stage.show();

        UpdateText();
    }

    public void UpdateText() {
        label.setText("Value: " + model.GetValue());
    }
}