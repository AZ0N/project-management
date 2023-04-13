package application;

import application.projectmanagement.Projectmanager;

public class Model {
    
    private View view;
    private int value;

    private Projectmanager projectmanager;

    public Model(View view) {
        this.view = view;
        this.value = 0;

        projectmanager = new Projectmanager();
    }

    public void IncrementValue() {
        value += 1;
        view.UpdateText();
    }

    public int GetValue() {
        return value;
    }
}
