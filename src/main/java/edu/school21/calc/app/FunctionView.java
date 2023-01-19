package edu.school21.calc.app;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FunctionView extends JPanel {
    String f = "";
    double x1;
    double x2;
    double y1;
    double y2;
    final JFrame functionsWindow = new JFrame("Functions");
    final JLabel error = new JLabel("");
    public FunctionView() {
        functionsWindow.setSize(320, 257);
        functionsWindow.add(this);
        functionsWindow.setResizable(false);
        functionsWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        functionsWindow.setVisible(true);

        setLayout(null);
        setFocusable(true);
        grabFocus();

        JButton draw = new JButton("DRAW");
        JTextField fInputText = new JTextField("Function F(x)");
        JTextField fInput = new JTextField("");
        JTextField maxInputTextX = new JTextField("Maximum X Value =");
        JTextField minInputTextX = new JTextField("Minimum X Value =");
        JTextField maxInputX = new JTextField("");
        JTextField minInputX = new JTextField("");
        JTextField maxInputTextY = new JTextField("Maximum Y Value =");
        JTextField minInputTextY = new JTextField("Minimum Y Value =");
        JTextField maxInputY = new JTextField("");
        JTextField minInputY = new JTextField("");

        draw.setBounds(10, 10 ,50,50);
        add(draw);

        fInputText.setBounds(10, 60, 150, 30);
        fInputText.setEditable(false);
        add(fInputText);
        fInput.setBounds(160, 60, 150, 30);
        fInput.setEditable(true);
        add(fInput);

        minInputTextX.setBounds(10, 90, 150, 30);
        minInputTextX.setEditable(false);
        add(minInputTextX);
        minInputX.setBounds(160, 90, 150, 30);
        minInputX.setEditable(true);
        add(minInputX);

        maxInputTextX.setBounds(10, 120, 150, 30);
        maxInputTextX.setEditable(false);
        add(maxInputTextX);
        maxInputX.setBounds(160, 120, 150, 30);
        maxInputX.setEditable(true);
        add(maxInputX);

        minInputTextY.setBounds(10, 150, 150, 30);
        minInputTextY.setEditable(false);
        add(minInputTextY);
        minInputY.setBounds(160, 150, 150, 30);
        minInputY.setEditable(true);
        add(minInputY);

        maxInputTextY.setBounds(10, 180, 150, 30);
        maxInputTextY.setEditable(false);
        add(maxInputTextY);
        maxInputY.setBounds(160, 180, 150, 30);
        maxInputY.setEditable(true);
        add(maxInputY);

        error.setLocation(75, 25);
        error.setSize(170, 20);
        add(error);

        draw.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f = fInput.getText();
                if (!f.equals("")) {
                    set("");
                    x1 = Double.parseDouble(minInputX.getText());
                    x2 = Double.parseDouble(maxInputX.getText());
                    try {
                        if (x1 < x2) {
                            set("");
                            y1 = Double.parseDouble(minInputY.getText());
                            y2 = Double.parseDouble(maxInputY.getText());
                            try {
                                if (y1 < y2) {
                                    set("");
                                    set("Good");
                                    try {
                                        FunctionDraw p = new FunctionDraw(x1, x2, y1, y2, f);
                                    } catch (Exception w3) {
                                        set("Draw error");
                                        System.out.println(w3);
                                    }
                                } else {
                                    set("Bad Y");
                                }
                            } catch (Exception w1) {
                                set("Bad location");
                            }
                        } else {
                            set("Bad X");
                        }

                    } catch (Exception w2) {
                        set("некорректные координаты");
                    }
                } else {
                    set("Need a function");
                }

            }
        });
    }

    public void set(String s) {
        error.setText(s);
    }
}
