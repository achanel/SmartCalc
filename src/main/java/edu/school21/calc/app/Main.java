package edu.school21.calc.app;

import javax.swing.SwingUtilities;

public class Main {
    public Main(){
        final View view = new View();
        final Model model = new Model();
        new Presenter(view, model);
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