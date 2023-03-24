package application;

public class Controller {

    private Model model;
    private View view;
    
    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
    }

    public void IncrementButton() {
        model.IncrementValue();
    }
}
