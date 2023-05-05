package application;

import java.util.Optional;

import application.projectmanagement.Employee;
import application.projectmanagement.Project;
import application.projectmanagement.ProjectActivity;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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

    //region UI element fields
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
    @FXML private Button deleteProjectButton;

    // "Selected Project" tab
    @FXML private Tab selectedProjectTab;
    @FXML private Label selectedProjectIDLabel;
    @FXML private Label selectedProjectNameLabel;
    @FXML private Label selectedProjectLeaderLabel;
    @FXML private ListView<ProjectActivity> selectedProjectActivityListView;
    @FXML private ListView<Employee> selectedProjectActivityEmployeesListView;
    @FXML private Button appointProjectLeaderButton;
    @FXML private Button createActivityButton;
    @FXML private Button assignEmployeeButton;
    @FXML private Button setEstimatedTimeButton;
    @FXML private Button addTimeUsedButton;

    // "My Overview" tab
    @FXML private Tab overviewTab;
    @FXML private ListView<Project> overviewProjectListView;
    @FXML private ListView<ProjectActivity> overviewActivityListView;

    // General UI elements    
    @FXML private VBox mainScreen;
    @FXML private TabPane tabPane;
    @FXML private Label currentUserLabel;
    //endregion

    public void initialize(Model model, View view) {
        this.model = model;
        this.view = view;

        // Initialize event handlers for ListViews and search fields
        initializeOverviewTab();
        initializeEmployeeTab();
        initializeProjectTab();
        initializeProjectDetailsTab();
        initializeTabpane();
    }

    //region Initialize event listeners
    private void initializeOverviewTab() {
        overviewProjectListView.getSelectionModel().selectedItemProperty().addListener((event, oldProject, newProject) -> {
            model.selectedProject(newProject);
            selectedProjectTab.setDisable(newProject == null);
            editProjectButton.setDisable(newProject == null);

            if (newProject != null) {
                selectedProjectTab.setText("Selected Project: " + newProject.getID());
                view.updateProjectDetails(newProject); //Still needs ID and activity updates
                view.updateSelectedProjectActivityListView(newProject.getProjectActivities());
            }
            else {
                selectedProjectTab.setText("Selected Project");
                view.clearProjectDetails();
            }
        });
    }
    
    private void initializeEmployeeTab() {
        employeeListView.getSelectionModel().selectedItemProperty().addListener((e, oldValue, newValue) -> {
            if (newValue != null) {
            	employeeInitialsLabel.setText("Initials: " + newValue.getInitials());
            	view.updateEmployeeActivityList(model.getAllActivitiesForEmployee(newValue));
            	view.updateEmployeeProjectList(model.getAllProjectsForEmployee(newValue));
            }
        });

        employeeSearchField.textProperty().addListener((e) -> {
            view.updateEmployeeList(model.searchEmployees(employeeSearchField.getText().strip()));
        });
    }

    private void initializeProjectTab() {
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

        projectSearchField.textProperty().addListener((e) -> {
            view.updateProjectList(model.searchProjects(projectSearchField.getText().strip()));
        });
    }

    private void initializeProjectDetailsTab() {
        selectedProjectActivityListView.getSelectionModel().selectedItemProperty().addListener((e, oldValue, newValue) -> {
            model.selectedActivity(newValue);
            if (newValue != null) {
            	view.updateSelectedActivityEmployeeListView(model.getSelectedActivityEmployees());
                view.updateSelectedActivityButtons();
            }
        });
    }

    private void initializeTabpane() {
        tabPane.getSelectionModel().selectedItemProperty().addListener((event, oldTab, newTab) -> {
            if (newTab == overviewTab) {
                view.updateOverview(model.getAllProjectsForEmployee(model.getLoggedInEmployee()), model.getAllActivitiesForEmployee(model.getLoggedInEmployee()));
            }
        });
    }
    //endregion

    //region Button events
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
    
    public void deleteProject() {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Delete Project");
    	alert.setHeaderText("You are about to delete project \"" + model.getSelectedProject() + "\"!");
    	alert.setContentText("Click OK to proceed");

    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == ButtonType.OK){
    	    model.deleteProject(model.getSelectedProject().getID());
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

    public void setEstimatedTime() {
        String inputResult = showDialogBox("Set Estimated Time", "Enter estimated time:");
        if (inputResult != null) {
            model.setEstimatedTime(inputResult);
        }
    }

    public void addTimeUsed() {
        String inputResult = showDialogBox("Add Time Used", "Enter hours used: ");
        if (inputResult != null) {
            model.addTimeUsed(inputResult);
        }
    }
    
    public void appointProjectLeader() {
        String inputResult = showDialogBox("Appoint Project Leader", "Enter employee initials:");
        if (inputResult != null) {
            model.appointProjectLeader(inputResult.strip());
        }
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
        model.clearLoggedInEmployee();

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
    //endregion
    
    private String showDialogBox(String title, String header) {
        TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.setTitle(title);
        textInputDialog.setHeaderText(header);
        textInputDialog.showAndWait();
        return textInputDialog.getResult();
    }

    //region Getters for UI elements
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
    public Button getCreateActivityButton() { return createActivityButton; }
    public Button getAssignEmployeeButton() { return assignEmployeeButton; }
    public Button getSetEstimatedTimeButton() { return setEstimatedTimeButton; }
    public Button getAddTimeUsedButton() { return addTimeUsedButton; }

    // Getters for "My Overview" UI elements
    public ListView<Project> getOverviewProjectListView() { return overviewProjectListView; }
    public ListView<ProjectActivity> getOverViewActivityListView() { return overviewActivityListView; }

    // Getters for general UI elements
    public VBox getMainScreen() { return mainScreen; }
    public TabPane getTabPane() { return tabPane; }
    public Label getCurrentUserLabel() { return currentUserLabel; }
    //endregion
}
