package application;

import java.awt.Button;

import application.projectmanagement.Employee;
import application.projectmanagement.Project;
import application.projectmanagement.ProjectActivity;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;

public class Controller {

    private Model model;
    private View view;

    @FXML private TextField employeeSearchField;
    @FXML private TextField projectSearchField;
    @FXML private ListView<Employee> employeeListView;
    @FXML private ListView<Project> projectListView;
    @FXML private ListView<ProjectActivity> projectActivityListView;
    @FXML VBox logInScreen;
    @FXML VBox mainScreen;
    @FXML TabPane Tabs;
    @FXML Tab SelectedProject;
    @FXML TextField loginTextField;
    @FXML Label currentUserLabel;

    @FXML Label ViewProjectName;
    @FXML Label ViewProjectLeader;

    @FXML Label employeeInitials;


    public void setModelAndView(Model model, View view) {
        this.model = model;
        this.view = view;

        employeeListView.getSelectionModel().selectedItemProperty().addListener((e, oldValue, newValue) -> {
            if (newValue != null) {
            	employeeInitials.setText("Initials: " + newValue.getInitials());
            }
        });

        projectListView.getSelectionModel().selectedItemProperty().addListener((e, oldValue, newValue) -> {
            if (newValue != null) {
                view.updateProjectDetails(newValue); //Still needs ID and activity updates
            }
        });

        // Add eventlisterner to search fields for Employees and Projects
        employeeSearchField.textProperty().addListener((e) -> {
            view.updateEmployeeList(model.searchEmployees(employeeSearchField.getText().strip()));
        });

        projectSearchField.textProperty().addListener((e) -> {
            view.updateProjectList(model.searchProjects(projectSearchField.getText().strip()));
        });
    }
    
    public void addEmployee() {
    	TextInputDialog textInputDialog = new TextInputDialog();
    	textInputDialog.setHeaderText("Enter employee initials");
    	textInputDialog.setTitle("Add employee");
    	textInputDialog.showAndWait();
    	
    	if (textInputDialog.getResult() != null) {
            model.addEmployee(textInputDialog.getResult());
    	}
    }
    
    public void createProject() {
        TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.setHeaderText("Enter project name:");
        textInputDialog.setTitle("Create Project");
        textInputDialog.showAndWait();

        if (textInputDialog.getResult() != null) {
            model.addProject(textInputDialog.getResult());
        }
    }

    public void closeApplication() {
        view.close();
    }

    public ListView<Employee> getEmployeeListView() {
        return employeeListView;
    }

    public ListView<Project> getProjecListView() {
        return projectListView;
    }
    
    public ListView<ProjectActivity> getProjectActivityListView() {
    	return projectActivityListView;
    }
    public void loginButton() {
        model.login(loginTextField.getText());
    }

    public void logoutButton() {
        model.clearSelectedEmployee();
        view.toLoginScreen();
    }
    
    public void editProjectButton() {
    	view.toSelectedProject();
    }
}
