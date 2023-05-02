package application;

import java.util.List;
import application.projectmanagement.Employee;
import application.projectmanagement.Project;
import application.projectmanagement.ProjectActivity;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class View extends Application {

	Model model;
	Controller controller;

	private Stage stage;

	public static void main(String[] args) {
		launch(args);
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
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		model = new Model(this);
		controller.setModelAndView(model, this);

		// Add admin employee
		try {
			model.addEmployee("adm");
		}
		catch (Exception e) {

		}

		toLoginScreen();

		stage.setScene(scene);
		stage.show();
	}

	public void updateEmployeeList(List<Employee> employees) {
		var items = controller.getEmployeeListView().getItems();
		items.clear();

		for (Employee employee : employees) {
			items.add(employee);
		}
	}

	public void updateProjectActivityList(List<ProjectActivity> projectActivities) {
		var items = controller.getProjectActivityListView().getItems();
		items.clear();
		for (ProjectActivity projectActivity : projectActivities) {
			items.add(projectActivity);
		}
	}

	public void updateEmployeeActivityList(List<ProjectActivity> projectActivities) {
		var items = controller.getEmployeeActivityListView().getItems();
		items.clear();
		for (ProjectActivity projectActivity : projectActivities) {
			items.add(projectActivity);
		}
	}

	public void updateEmployeeProjectList(List<Project> projects) {
		var items = controller.getEmployeeProjectListView().getItems();
		items.clear();
		for (Project project : projects) {
			items.add(project);
		}
	}

	public void updateProjectList(List<Project> projects) {
		var items = controller.getProjecListView().getItems();
		items.clear();
		for (Project project : projects) {
			items.add(project);
		}
	}

	public void updateProjectDetails(Project project) {
		// Update "Projects" tab
		controller.getProjectIDLabel().setText("Project ID: " + project.getID());
		controller.getProjectNameLabel().setText("Project name: " + project.getProjectName());
		updateProjectActivityList(project.getProjectActivities());
		
		// Update "Selected Project" tab
		controller.getSelectedProjectIDLabel().setText("Project ID: " + project.getID());
		controller.getSelectedProjectNameLabel().setText("Project name: " + project.getProjectName());
		updateSelectedProjectActivityListView(project.getProjectActivities());
		
		// Update Project Leader fields
		if (project.getProjectLeader() != null) {
			controller.getProjectLeaderLabel().setText("Project leader: " + project.getProjectLeader().getInitials());
			controller.getSelectedProjectLeaderLabel().setText("Project leader: " + project.getProjectLeader().getInitials());
		}
		else {
			controller.getProjectLeaderLabel().setText("Project leader: ");
			controller.getSelectedProjectLeaderLabel().setText("Project leader: ");
		}
	}

	public void clearProjectDetails() {
		// Clear "Projects" tab
		controller.getProjectIDLabel().setText("Project ID: ");
		controller.getProjectNameLabel().setText("Project name: ");
		controller.getProjectLeaderLabel().setText("Project leader: ");
		controller.getProjectActivityListView().getItems().clear();

		// Clear "Selected Project" tab
		controller.getSelectedProjectIDLabel().setText("Project ID: ");
		controller.getSelectedProjectNameLabel().setText("Project name: ");
		controller.getSelectedProjectLeaderLabel().setText("Project leader: ");
		controller.getSelectedProjectActivityListView().getItems().clear();
		controller.getSelectedProjectActivityEmployeesListView().getItems().clear();
	}

	public void clearEmployeeTab() {
		controller.getEmployeeProjectListView().getItems().clear();
		controller.getEmployeeActivityListView().getItems().clear();
	}

	public void updateSelectedProjectActivityListView(List<ProjectActivity> projectActivities) {
		var items = controller.getSelectedProjectActivityListView().getItems();
		items.clear();
		for (ProjectActivity projectActivity : projectActivities) {
			items.add(projectActivity);
		}
	}

	public void updateSelectedActivityEmployeeListView(List<Employee> newValue) {
		var items = controller.getSelectedProjectActivityEmployeesListView().getItems();
		items.clear();
		for (Employee employees : model.getSelectedProjectActivity().getAssignedEmployees()) {
			items.add(employees);
		}
	}

	public void updateOverview(List<Project> projects, List<ProjectActivity> activities) {
		var projectItems = controller.getOverviewProjectListView().getItems();
		projectItems.clear();
		projectItems.addAll(projects);

		var activityItems = controller.getOverViewActivityListView().getItems();
		activityItems.clear();
		activityItems.addAll(activities);
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
		controller.getCurrentUserLabel().setText("User: " + model.getCurrentEmployee().getInitials());
		controller.getMainScreen().setVisible(true);
		controller.getLogInScreen().setVisible(false);
	}

	public void toLoginScreen() {
		controller.getLogInTextField().clear();
		controller.getLogInScreen().setVisible(true);
		controller.getMainScreen().setVisible(false);
	}

	public void toSelectedProject() {
		controller.getTabPane().getSelectionModel().select(controller.getSelectedProjecTab());
	}
}