package edu.school21.calc.app;

import javax.swing.*;

public class Main {
    private JFrame window;
    public Main(){
        window = new JFrame("SmartCalc");
        window.setSize(370, 447);
        window.add(new Panel());
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
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