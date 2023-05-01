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

    @FXML private VBox logInScreen;
    @FXML private TextField loginTextField;
    @FXML private VBox mainScreen;
    @FXML private TabPane Tabs;
    @FXML private Tab SelectedProject;
    @FXML private Label currentUserLabel;

    @FXML private Label ViewProjectName;
    @FXML private Label ViewProjectLeader;
    @FXML private Label SPViewProjectName;
    @FXML private Label SPViewProjectLeader;
    @FXML private Label employeeInitials;


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
        String inputResult = showDialogBox("Add Employee", "Enter employee initials:");
        if (inputResult != null) {
            model.addEmployee(inputResult);
        }
    }
    
    public void createProject() {
        String inputResult = showDialogBox("Create Project", "Enter project name:");
        if (inputResult != null) {
            model.addProject(inputResult);
        }
    }

    public void createProjectActivity() {
        String inputResult = showDialogBox("Create Activity", "Enter activity name:");
        if (inputResult != null) {
            model.createProjectActivity(inputResult);
        }
    }
    
    public void assignEmployeeToActivity() {
        String inputResult = showDialogBox("Assign Employee", "Enter employee initials:");
        if (inputResult != null) {
            model.assignEmployeeToActivity(inputResult);
        }
    }

    private String showDialogBox(String title, String header) {
        TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.setTitle(title);
        textInputDialog.setHeaderText(header);
        textInputDialog.showAndWait();
        return textInputDialog.getResult();
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

    public VBox getLogInScreen() { return logInScreen; }
    public TextField getLogInTextField() { return loginTextField; }
    public VBox getMainScreen() { return mainScreen; }
    public TabPane getTabPane() { return Tabs; }
    public Tab getSelectedProjecTab() { return SelectedProject; }
    public Label getCurrentUserLabel() { return currentUserLabel; }

    public Label getProjectNameLabel() { return ViewProjectName; }
    public Label getProjectLeaderlabel() { return ViewProjectLeader; }
    public Label getSelectedProjectNameLabel() { return SPViewProjectName; }
    public Label getSelectedProjectLeaderLabel() { return SPViewProjectLeader; }
    public Label getEmployeeInitialsLabel() { return employeeInitials; }
}
