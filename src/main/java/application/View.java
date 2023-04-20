package application;

import java.util.List;

import application.projectmanagement.Employee;
import application.projectmanagement.Project;
import application.projectmanagement.ProjectActivity;
import application.projectmanagement.ProjectManager;
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

    private Stage stage;

    public static void main(String[] args) {
        launch(args);
        ProjectManager projectManager = new ProjectManager();
        launch(args);
        Employee employee = new Employee("jens");
        try {
			projectManager.addEmployee(employee);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Project project = new Project("jensefar");
        System.out.println(projectManager.getEmployees());
        try {
			projectManager.addProject(project);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(projectManager.getProjects());
        try {
			project.appointProjectLeader("jens");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(project.getProjectLeader());
        ProjectActivity projectActivity = new ProjectActivity("jensemor");
        project.addActivity(projectActivity);
        System.out.println(project.getProjectActivities());
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

    public void updateProjectList(List<Project> projects) {
        var items = projectListView.getItems();
        items.clear();

        for (Project project : projects) {
            projectListView.getItems().add(project);
        }
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
        controller.MainScreen.setVisible(true);
        controller.LogInScreen.setVisible(false);
    }

    public void toLoginScreen() {
        controller.loginTextField.clear();
        controller.LogInScreen.setVisible(true);
        controller.MainScreen.setVisible(false);
    }
}