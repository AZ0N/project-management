package application;

import application.projectmanagement.Employee;
import application.projectmanagement.Project;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

public class Controller {

	// Fields
    private Model model;
    private View view;

    @FXML private TextField employeeInitialsTextField;
    @FXML private TextField projectNameTextField;
    @FXML private ListView<Employee> employeeListView;
    @FXML private ListView<Project> projectListView;

    public void setModelAndView(Model model, View view) {
        this.model = model;
        this.view = view;

        employeeListView.getSelectionModel().selectedItemProperty().addListener((e, oldValue, newValue) -> {
            System.out.println("Employee: " + newValue.getInitials());
        });

        projectListView.getSelectionModel().selectedItemProperty().addListener((e, oldValue, newValue) -> {
            System.out.println("Project: " + newValue.getProjectName());
        });
    }
    
    public void addEmployee() {
        model.addEmployee(employeeInitialsTextField.getText());
    }

    public void addProject() {
        model.addProject(projectNameTextField.getText());
    }

    public ListView<Employee> getEmployeeListView() {
        return employeeListView;
    }

    public ListView<Project> getProjecListView() {
        return projectListView;
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
}
