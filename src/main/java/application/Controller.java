package application;

public class Controller {

	// Fields
    private Model model;
    private View view;
    
    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        // test comments. Delete this
    }

    public void IncrementButton() {
        // Increment value
        model.IncrementValue();
    }
}
