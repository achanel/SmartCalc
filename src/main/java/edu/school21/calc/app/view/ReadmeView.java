package edu.school21.calc.app.view;

import javax.swing.*;

public class ReadmeView extends JPanel{
    final JFrame readmeWindow = new JFrame("ReadMe");

    public ReadmeView() {
        readmeWindow.setSize(320, 357);
        readmeWindow.add(this);
        readmeWindow.setResizable(false);
        readmeWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        readmeWindow.setVisible(true);

        setLayout(null);
        setFocusable(true);
        grabFocus();

        JTextArea readmeLabel = new JTextArea(
                "DEP    - депозитный калькулятор\n" +
                "CRED - кредитный калькулятор\n" +
                "F        - отрисовка графиков\n" +
                "his     - последняя операция из истории\n" +
                "clh     - очистка истории\n");
        readmeLabel.setBounds(0, 0, 320, 357);
        readmeLabel.setEditable(false);
        add(readmeLabel);
    }
}
