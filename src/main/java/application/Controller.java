package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller {

	// Feilds
    private Model model;
    private View view;

    @FXML private Label label;

    public void SetModelAndView(Model model, View view) {
        this.model = model;
        this.view = view;
    }
    
    public void IncrementButton() {
        // Increment value
        model.IncrementValue();
    }

    public Label GetLabel() {
        return label;
    }
}
