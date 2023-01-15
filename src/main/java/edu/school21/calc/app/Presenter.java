package edu.school21.calc.app;

public class Presenter{
    private final View view;
    private final Model model;

    public Presenter(final View view, final Model model) {
        this.view = view;
        this.model = model;
        view.addPresenter(this);
    }

    public void keyPressed(String str){
        view.printResult(Double.valueOf(Double.toString(model.doCalculation(str))));
    }
}
