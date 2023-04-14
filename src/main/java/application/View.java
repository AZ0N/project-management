package application;

import java.util.List;

import application.projectmanagement.Employee;
import application.projectmanagement.Project;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class View extends Application {

    Model model;
    Controller controller;

    ListView<String> employeeListView;
    ListView<String> projectListView;

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

        controller.setModelAndView(model, this);
        employeeListView = controller.getEmployListView();
        projectListView = controller.getProjecListView();
        
        stage.setScene(scene);
        stage.show();
    }

    public void updateEmployeeList(List<Employee> employees) {
        var items = employeeListView.getItems();
        items.clear();

        for (Employee employee : employees) {
            employeeListView.getItems().add(employee.getInitials());
        }
    }

    public void updateProjectList(List<Project> projects) {
        var items = projectListView.getItems();
        items.clear();

        for (Project project : projects) {
            projectListView.getItems().add(project.getProjectName());
        }
    }
}