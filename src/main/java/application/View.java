package application;

import java.util.List;

import application.projectmanagement.Employee;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class View extends Application {

    Model model;
    Controller controller;

    ListView<String> employeeListView;

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
        employeeListView = controller.getEmployListView();
        
        stage.setScene(scene);
        stage.show();
    }

    public void UpdateEmployeeList(List<Employee> employees) {
        var items = employeeListView.getItems();
        items.clear();

        for (Employee employee : employees) {
            employeeListView.getItems().add(employee.getInitials());
        }
    }
}