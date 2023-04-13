package application;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class Controller {

	// Fields
    private Model model;
    private View view;

    @FXML private TextField employeeInitialsTextField;
    @FXML private ListView<String> employeeListView;

    public void SetModelAndView(Model model, View view) {
        this.model = model;
        this.view = view;
    }
    
    public void addEmployee() {
        model.addEmployee(employeeInitialsTextField.getText());
    }


    public ListView<String> getEmployListView() {
        return employeeListView;
    }
}
