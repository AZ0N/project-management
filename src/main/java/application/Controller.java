package application;

import application.projectmanagement.Employee;
import application.projectmanagement.Project;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
    @FXML VBox logInScreen;
    @FXML VBox mainScreen;
    @FXML TextField loginTextField;
    @FXML Label currentUserLabel;

    public void setModelAndView(Model model, View view) {
        this.model = model;
        this.view = view;

        employeeListView.getSelectionModel().selectedItemProperty().addListener((e, oldValue, newValue) -> {
            if (newValue != null) {
                System.out.println("Employee: " + newValue.getInitials());
            }
        });

        projectListView.getSelectionModel().selectedItemProperty().addListener((e, oldValue, newValue) -> {
            if (newValue != null) {
                System.out.println("Project: " + newValue.getProjectName());
            }
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

    public void loginButton() {
        model.login(loginTextField.getText());
    }

    public void logoutButton() {
        model.clearSelectedEmployee();
        view.toLoginScreen();
    }
}
