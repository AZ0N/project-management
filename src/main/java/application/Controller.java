package application;

import application.projectmanagement.Employee;
import application.projectmanagement.Project;
import application.projectmanagement.ProjectActivity;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

    // Login screen
    @FXML private VBox logInScreen;
    @FXML private TextField loginTextField;

    // "Employees" tab 
    @FXML private TextField employeeSearchField;
    @FXML private ListView<Employee> employeeListView;
    @FXML private ListView<Project> employeeProjectListView;
    @FXML private ListView<ProjectActivity> employeeActivityListView;
    @FXML private Label employeeInitialsLabel;

    // "Projects" tab
    @FXML private TextField projectSearchField;
    @FXML private ListView<Project> projectListView;
    @FXML private Label projectIDLabel;
    @FXML private Label projectNameLabel;
    @FXML private Label projectLeaderLabel;
    @FXML private ListView<ProjectActivity> projectActivityListView;
    @FXML private Button editProjectButton;

    // "Selected Project" tab
    @FXML private Tab selectedProjectTab;
    @FXML private Label selectedProjectIDLabel;
    @FXML private Label selectedProjectNameLabel;
    @FXML private Label selectedProjectLeaderLabel;
    @FXML private ListView<ProjectActivity> selectedProjectActivityListView;
    @FXML private ListView<Employee> selectedProjectActivityEmployeesListView;
    @FXML private Button appointProjectLeaderButton;

    // "My Overview" tab
    @FXML private Tab overviewTab;
    @FXML private ListView<Project> overviewProjectListView;
    @FXML private ListView<ProjectActivity> overviewActivityListView;

    // General UI elements    
    @FXML private VBox mainScreen;
    @FXML private TabPane tabPane;
    @FXML private Label currentUserLabel;

    public void setModelAndView(Model model, View view) {
        this.model = model;
        this.view = view;

        // Initialize eventhandlers for listviews
        employeeListView.getSelectionModel().selectedItemProperty().addListener((e, oldValue, newValue) -> {
            if (newValue != null) {
            	employeeInitialsLabel.setText("Initials: " + newValue.getInitials());
            	view.updateEmployeeActivityList(model.getAllActivitiesForEmployee(newValue));
            	view.updateEmployeeProjectList(model.getAllProjectsForEmployee(newValue));
            }
        });

        projectListView.getSelectionModel().selectedItemProperty().addListener((e, oldValue, newValue) -> {
            model.selectedProject(newValue);
            selectedProjectTab.setDisable(newValue == null);
            editProjectButton.setDisable(newValue == null);

            if (newValue != null) {
                selectedProjectTab.setText("Selected Project: " + newValue.getID());
                view.updateProjectDetails(newValue); //Still needs ID and activity updates
                view.updateSelectedProjectActivityListView(newValue.getProjectActivities());
            }
            else {
                selectedProjectTab.setText("Selected Project");
                view.clearProjectDetails();
            }
        });
        
        selectedProjectActivityListView.getSelectionModel().selectedItemProperty().addListener((e, oldValue, newValue) -> {
            if (newValue != null) {
            	model.selectedActivity(newValue);
            	view.updateSelectedActivityEmployeeListView(model.getSelectedActivityEmployees());
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

        // Eventlistener for when tabs change
        tabPane.getSelectionModel().selectedIndexProperty().addListener((event, oldIndex, newIndex) -> {
            // TODO Use enum for handling specific tab indexes
            if ((int)newIndex == 0) {
                view.updateOverview(model.getAllProjectsForEmployee(model.getCurrentEmployee()), model.getAllActivitiesForEmployee(model.getCurrentEmployee()));
            }
        });
    }
   
    // Button events
    public void addEmployee() {
        String inputResult = showDialogBox("Add Employee", "Enter employee initials:");
        if (inputResult != null) {
            model.addEmployee(inputResult.strip());
        }
    }
    
    public void createProject() {
        String inputResult = showDialogBox("Create Project", "Enter project name:");
        if (inputResult != null) {
            model.addProject(inputResult.strip());
        }
    }

    public void createProjectActivity() {
        String inputResult = showDialogBox("Create Activity", "Enter activity name:");
        if (inputResult != null) {
            model.createProjectActivity(inputResult.strip());
        }
    }
    
    public void assignEmployeeToActivity() {
        String inputResult = showDialogBox("Assign Employee", "Enter employee initials:");
        if (inputResult != null) {
            model.assignEmployeeToActivity(inputResult.strip());
        }
    }

    public void appointProjectLeader() {
        String inputResult = showDialogBox("Appoint Project Leader", "Enter employee initials:");
        if (inputResult != null) {
            model.appointProjectLeader(inputResult.strip());
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
        view.toLoginScreen();

        // Clear selected employee
        model.clearSelectedEmployee();

        // Clear UI elements
        view.clearProjectDetails();
        view.clearEmployeeTab();
        tabPane.getSelectionModel().select(overviewTab);
        projectSearchField.setText("");
        employeeSearchField.setText("");
    }
    
    public void editProjectButton() {
    	view.toSelectedProject();
    }

    // Getters for "Login Screen" UI elements
    public VBox getLogInScreen() { return logInScreen; }
    public TextField getLogInTextField() { return loginTextField; }

    // Getters for "Employees" UI elements
    public ListView<Employee> getEmployeeListView() { return employeeListView; }
    public ListView<Project> getEmployeeProjectListView(){ return employeeProjectListView; }
    public ListView<ProjectActivity> getEmployeeActivityListView(){ return employeeActivityListView; }

    // Getters for "Projects" UI elements
    public ListView<Project> getProjecListView() { return projectListView; }
    public Label getProjectIDLabel() { return projectIDLabel; }
    public Label getProjectNameLabel() { return projectNameLabel; }
    public Label getProjectLeaderLabel() { return projectLeaderLabel; }
    public ListView<ProjectActivity> getProjectActivityListView() { return projectActivityListView; }

    // Getters for "Selected Project" UI elements
    public Tab getSelectedProjecTab() { return selectedProjectTab; }
    public Label getSelectedProjectIDLabel() {return selectedProjectIDLabel; }
    public Label getSelectedProjectNameLabel() { return selectedProjectNameLabel; }
    public Label getSelectedProjectLeaderLabel() { return selectedProjectLeaderLabel; }
    public ListView<ProjectActivity> getSelectedProjectActivityListView() { return selectedProjectActivityListView; }
    public ListView<Employee> getSelectedProjectActivityEmployeesListView() { return selectedProjectActivityEmployeesListView; }
    public Button getAppointProjectLeaderButton() { return appointProjectLeaderButton; }

    // Getters for "My Overview" UI elements
    public ListView<Project> getOverviewProjectListView() { return overviewProjectListView; }
    public ListView<ProjectActivity> getOverViewActivityListView() { return overviewActivityListView; }

    // Getters for general UI elements
    public VBox getMainScreen() { return mainScreen; }
    public TabPane getTabPane() { return tabPane; }
    public Label getCurrentUserLabel() { return currentUserLabel; }
}
