package application;

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
    @FXML private ListView<Project> employeeProjectListView;
    @FXML private ListView<ProjectActivity> projectActivityListView;
    @FXML private ListView<ProjectActivity> SPActivityListView;
    @FXML private ListView<ProjectActivity> employeeActivityListView;
    @FXML private ListView<Employee> SAEmployeeListView;

    @FXML VBox logInScreen;
    @FXML VBox mainScreen;
    @FXML TabPane Tabs;
    @FXML Tab SelectedProject;
    @FXML TextField loginTextField;
    @FXML Label currentUserLabel;

    @FXML Label ViewProjectName;
    @FXML Label ViewProjectLeader;
    @FXML Label SPViewProjectName;
    @FXML Label SPViewProjectLeader;
    @FXML Label employeeInitials;


    public void setModelAndView(Model model, View view) {
        this.model = model;
        this.view = view;

        // Initialize eventhandlers for listviews
        employeeListView.getSelectionModel().selectedItemProperty().addListener((e, oldValue, newValue) -> {
            if (newValue != null) {
            	employeeInitials.setText("Initials: " + newValue.getInitials());
            	view.updateEmployeeActivityList(model.showEmployeeActivityListView(newValue));
            	view.updateEmployeeProjectList(model.showEmployeeProjectListView(newValue));
            }
        });

        projectListView.getSelectionModel().selectedItemProperty().addListener((e, oldValue, newValue) -> {
            if (newValue != null) {
                model.selectedProject(newValue);
                view.updateProjectDetails(newValue); //Still needs ID and activity updates
                view.updateSPActivityListView(newValue.getProjectActivities());
            }
        });
        
        SPActivityListView.getSelectionModel().selectedItemProperty().addListener((e, oldValue, newValue) -> {
            if (newValue != null) {
            	model.selectedActivity(newValue);
            	view.updateSAEmployeeListView(model.getSelectedActivityEmployees());
            	// TODO update the list of employees on the specific activity
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
   
    // Button events
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

    public void createProjectActivity() {
        TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.setHeaderText("Enter activity name:");
        textInputDialog.setTitle("Create Activity");
        textInputDialog.showAndWait();

        if (textInputDialog.getResult() != null) {
            model.createProjectActivity(textInputDialog.getResult());
        }
    }
    
    public void assignEmployeeToActivity() {
    	TextInputDialog textInputDialog = new TextInputDialog();
    	textInputDialog.setHeaderText("Enter employee initials:");
        textInputDialog.setTitle("Assign Employee");
        textInputDialog.showAndWait();
        
        if (textInputDialog.getResult() != null) {
        	model.assignEmployeeToActivity(textInputDialog.getResult());
        }
    }

    public void closeApplication() {
        view.close();
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

    // Getters for UI elements
    public ListView<Employee> getEmployeeListView() { return employeeListView; }
    public ListView<Project> getProjecListView() { return projectListView; }
    public ListView<ProjectActivity> getProjectActivityListView() { return projectActivityListView; }
    public ListView<ProjectActivity> getSPActivityListView() { return SPActivityListView; }
    public ListView<ProjectActivity> getEmployeeActivityListView(){ return employeeActivityListView; }
    public ListView<Project> getEmployeeProjectListView(){ return employeeProjectListView; }
    public ListView<Employee> getSAEmployeeListView() { return SAEmployeeListView; }
}
