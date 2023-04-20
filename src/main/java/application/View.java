package application;

import java.util.List;

import application.projectmanagement.Employee;
import application.projectmanagement.Project;
import application.projectmanagement.ProjectActivity;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class View extends Application {

    Model model;
    Controller controller;

    ListView<Employee> employeeListView;
    ListView<Project> projectListView;
    ListView<ProjectActivity> projectActivityListView;

    private Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;
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
        
        model = new Model(this);
        controller.setModelAndView(model, this);
        employeeListView = controller.getEmployeeListView();
        projectListView = controller.getProjecListView();
        projectActivityListView = controller.getProjectActivityListView();

        // Add admin employee
        try {
            model.addEmployee("admin");
        } catch (Exception e) {
        }
        
        toLoginScreen();

        stage.setScene(scene);
        stage.show();
    }

    public void updateEmployeeList(List<Employee> employees) {
        var items = employeeListView.getItems();
        items.clear();

        for (Employee employee : employees) {
            employeeListView.getItems().add(employee);
        }
    } 
    
    public void updateProjectActivityList(List<ProjectActivity> projectActivities) {
        var items = projectActivityListView.getItems();
        items.clear();

        for (ProjectActivity projectActivity : projectActivities) {
        	projectActivityListView.getItems().add(projectActivity);
        }
    }

    public void updateProjectList(List<Project> projects) {
        var items = projectListView.getItems();
        items.clear();

        for (Project project : projects) {
            projectListView.getItems().add(project);
        }
    }
    
    public void updateProjectDetails(Project project) {
    	controller.ViewProjectName.setText("Project name: " + project.getProjectName());
    	controller.ViewProjectLeader.setText("Project leader: " + project.getProjectLeader());
    	// Todo ID and activities
    }

    public void showError(String errorMessage) {
        Alert alert = new Alert(AlertType.WARNING, null, ButtonType.OK);
        alert.setHeaderText(errorMessage);
        alert.show();
    }

    public void close() {
        stage.close();
    }

    public void toMainScreen() {
        controller.currentUserLabel.setText("User: " + model.getCurrentEmployee().getInitials());
        controller.mainScreen.setVisible(true);
        controller.logInScreen.setVisible(false);
    }

    public void toLoginScreen() {
        controller.loginTextField.clear();
        controller.logInScreen.setVisible(true);
        controller.mainScreen.setVisible(false);
    }
    
    public void toSelectedProject() {
    	controller.Tabs.getSelectionModel().select(controller.SelectedProject);
    }
}