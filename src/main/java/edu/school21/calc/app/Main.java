package edu.school21.calc.app;

import javax.swing.SwingUtilities;

public class Main {
    public Main(){
        final CalcView calcView = new CalcView();
        final CalcModel calcModel = new CalcModel();
        new Presenter(calcView, calcModel);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }
}