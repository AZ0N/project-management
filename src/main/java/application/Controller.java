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

/**
 * @author Christian Brix Saksager - s224777
 */
public class Controller {

    private Model model;
    private View view;

    //region UI element fields. Populated by ui.fxml
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
    @FXML private Tab projectsTab;
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
            // Select the selected project
            model.selectedProject(newProject);
            // Update UI buttons to be enabled/disabled based on if the selected project is null
            selectedProjectTab.setDisable(newProject == null);
            editProjectButton.setDisable(newProject == null);
            deleteProjectButton.setDisable(newProject == null);

            // If the selected project isn't null, update the UI
            if (newProject != null) {
                selectedProjectTab.setText("Selected Project: " + newProject.getID());
                view.updateProjectDetails(newProject); //Still needs ID and activity updates
                view.updateSelectedProjectActivityListView(newProject.getProjectActivities());
            }
            // Else clear the selected project tab
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
            deleteProjectButton.setDisable(newValue == null);

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
            else if (newTab == projectsTab) {
                projectListView.getSelectionModel().clearSelection();
            }
        });
    }
    //endregion

    //region Button events
    public void addEmployee() {
        String employeeInitials = showDialogBox("Add Employee", "Enter employee initials:");
        if (employeeInitials != null) {
            model.addEmployee(employeeInitials.strip());
        }
    }
    
    public void createProject() {
        String projectName = showDialogBox("Create Project", "Enter project name:");
        if (projectName != null) {
            model.addProject(projectName.strip());
        }
    }

    public void createProjectActivity() {
        String activityName = showDialogBox("Create Activity", "Enter activity name:");
        if (activityName != null) {
            model.createProjectActivity(activityName.strip());
        }
    }
    
    public void assignEmployeeToActivity() {
        String employeeInitials = showDialogBox("Assign Employee", "Enter employee initials:");
        if (employeeInitials != null) {
            model.assignEmployeeToActivity(employeeInitials.strip());
        }
    }

    public void setEstimatedTime() {
        String estimatedTime = showDialogBox("Set Estimated Time", "Enter estimated time:");
        if (estimatedTime != null) {
            model.setEstimatedTime(estimatedTime);
        }
    }

    public void addTimeUsed() {
        String timeUsed = showDialogBox("Add Time Used", "Enter hours used: ");
        if (timeUsed != null) {
            model.addTimeUsed(timeUsed);
        }
    }
    
    public void appointProjectLeader() {
        String employeeInitials = showDialogBox("Appoint Project Leader", "Enter employee initials:");
        if (employeeInitials != null) {
            model.appointProjectLeader(employeeInitials.strip());
        }
    }
        
    public void deleteProject() {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Delete Project");
    	alert.setHeaderText("You are about to delete project \"" + model.getSelectedProject() + "\"!");
    	alert.setContentText("Click OK to proceed");
        alert.initOwner(tabPane.getScene().getWindow());

    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == ButtonType.OK){
    	    model.deleteProject(model.getSelectedProject().getID());
    	}
    }

    public void closeApplication() {
        view.close();
    }

    public void loginButton() {
        model.login(loginTextField.getText());
    }

    public void logoutButton() {
        // Show login screen
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
    
    // Utility method used to easily show a text dialog box with a given title and header,
    // and return the string provided.
    private String showDialogBox(String title, String header) {
        TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.initOwner(tabPane.getScene().getWindow());
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
