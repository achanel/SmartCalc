package edu.school21.calc.app.program;

import edu.school21.calc.app.models.CalcModel;
import edu.school21.calc.app.view.CalcView;
import edu.school21.calc.app.presenter.Presenter;

import javax.swing.SwingUtilities;

public class Main {
    public Main(){
        final CalcView calcView = new CalcView();
        final CalcModel calcModel = new CalcModel(0, "", (short) 0);
        new Presenter(calcView, calcModel);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}
