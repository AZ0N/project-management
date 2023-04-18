package application;

import application.projectmanagement.Employee;
import application.projectmanagement.Project;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

public class Controller {

	// Fields
    private Model model;
    private View view;

    @FXML private TextField employeeSearchField;
    @FXML private TextField projectSearchField;
    @FXML private ListView<Employee> employeeListView;
    @FXML private ListView<Project> projectListView;

    @FXML private TabPane tabPane;
    @FXML private Tab employeeTab;
    @FXML private Tab projectTab;
    @FXML private Tab activityTab;

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
    	TextInputDialog textInputDialog = new TextInputDialog();
    	textInputDialog.setHeaderText("Enter employee initials");
    	textInputDialog.setTitle("Add employee");
    	textInputDialog.showAndWait();
    	
    	if (textInputDialog.getResult() != null) {
            model.addEmployee(textInputDialog.getResult());
            tabPane.getSelectionModel().select(employeeTab);
    	}
    }
    
    public void createProject() {
        TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.setHeaderText("Enter project name:");
        textInputDialog.setTitle("Create Project");
        textInputDialog.showAndWait();

        if (textInputDialog.getResult() != null) {
            model.addProject(textInputDialog.getResult());
            tabPane.getSelectionModel().select(projectTab);
        }
    }

    public ListView<Employee> getEmployeeListView() {
        return employeeListView;
    }

    public ListView<Project> getProjecListView() {
        return projectListView;
    }
}
