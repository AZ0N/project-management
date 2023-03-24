package application;

public class Model {
    
    private View view;
    private int value;

    public Model(View view) {
        this.view = view;
        this.value = 0;
    }

    public void IncrementValue() {
        value += 1;
        view.UpdateText();
    }

    public int GetValue() {
        return value;
    }
}
