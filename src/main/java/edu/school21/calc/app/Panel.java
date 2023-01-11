package edu.school21.calc.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Panel extends JPanel{
    private  JButton numbers[] = new JButton[10];
    private Font font = new Font("SanSerif", Font.BOLD, 20);
    private JTextField output = new JTextField();
    private JButton backspace = new JButton("B");
    private JButton clear = new JButton("C");
    private JButton mod = new JButton("mod");
    private JButton equ = new JButton("=");
    private JButton plus = new JButton("+");
    private JButton minus = new JButton("-");
    private JButton multi = new JButton("*");
    private JButton dev = new JButton("/");

    private JButton sin = new JButton("sin");
    private JButton cos = new JButton("cos");
    private JButton tan = new JButton("tan");
    private JButton asin = new JButton("asin");
    private JButton acos = new JButton("acos");
    private JButton atan = new JButton("atan");

    private JButton dot = new JButton(".");
    private JButton pm = new JButton("+/-");

    private JButton ln = new JButton("ln");
    private JButton log = new JButton("log");
    private JButton sqrt = new JButton("\u221A");
    private JButton deg = new JButton("x^y");

    private JButton deposit = new JButton("DEP");
    private JButton credit = new JButton("CRED");
    private double numA = 0;
    private double numB = 0;
    private double result = 0;
    private int calc;
    public Panel(){
        setLayout(null);
        setFocusable(true);
        grabFocus();

        ActionListener l = (ActionEvent e ) -> {
            JButton b = (JButton)e.getSource();
            if (b == clear) {
                output.setText("");
            } else if (b == backspace) {
                int length = output.getText().length();
                int number = length - 1;
                if (length > 0) {
                    StringBuilder back = new StringBuilder(output.getText());
                    back.deleteCharAt(number);
                    output.setText(back.toString());
                } if (output.getText().endsWith(""));
                output.setText("");
            } else if (b == numbers[0]) {
                if (output.getText().equals("0"))
                    return;
                else
                    output.setText(output.getText() + "0");
            } else if (b == numbers[1]){
                output.setText(output.getText() + "1");
            } else if (b == numbers[2]){
                output.setText(output.getText() + "2");
            } else if (b == numbers[3]){
                output.setText(output.getText() + "3");
            } else if (b == numbers[4]){
                output.setText(output.getText() + "4");
            } else if (b == numbers[5]){
                output.setText(output.getText() + "5");
            } else if (b == numbers[6]){
                output.setText(output.getText() + "6");
            } else if (b == numbers[7]){
                output.setText(output.getText() + "7");
            } else if (b == numbers[8]){
                output.setText(output.getText() + "8");
            } else if (b == numbers[9]){
                output.setText(output.getText() + "9");
            } else if (b == dot) {
                if (output.getText().contains("."))
                    return;
                else
                    output.setText(output.getText() + ".");

            } else if (b == plus) {
                numA = Double.parseDouble(output.getText());
                calc = 1;
                output.setText("");
            }  else if (b == minus) {
                numA = Double.parseDouble(output.getText());
                calc = 2;
                output.setText("");
            } else if (b == equ) {
                numB = Double.parseDouble(output.getText());
                switch (calc){
                    case 1:
                        result = numA + numB;
                        break;
                    case 2:
                        result = numA - numB;
                        break;
                } if (Double.toString(result).endsWith(".0")) {
                    output.setText(Double.toString(result).replace(".0", ""));
                } else {
                    output.setText(Double.toString(result));
                }
            }
        };

        pm.setBounds(70, 310, 50, 50);
        add(pm);
        pm.addActionListener(l);
        numbers[0] = new JButton("0");
        numbers[0].setBounds(130, 310, 50, 50 );
        numbers[0].setFont(font);
        add(numbers[0]);
        dot.setBounds(190, 310, 50, 50);
        dot.setFont(font);
        add(dot);
        dot.addActionListener(l);

        plus.setBounds(250, 130, 50, 50);
        plus.setFont(font);
        add(plus);
        plus.addActionListener(l);
        minus.setBounds(250, 190, 50, 50);
        minus.setFont(font);
        add(minus);
        minus.addActionListener(l);
        multi.setBounds(250, 250, 50, 50);
        multi.setFont(font);
        add(multi);
        multi.addActionListener(l);
        dev.setBounds(250, 310, 50, 50);
        dev.setFont(font);
        add(dev);
        dev.addActionListener(l);

        sin.setBounds(10, 70, 50, 50);
        add(sin);
        sin.addActionListener(l);
        cos.setBounds(10, 130, 50, 50);
        add(cos);
        cos.addActionListener(l);
        tan.setBounds(10, 190, 50, 50);
        add(tan);
        tan.addActionListener(l);
        asin.setBounds(10, 250, 50, 50);
        add(asin);
        asin.addActionListener(l);
        acos.setBounds(10, 310, 50, 50);
        add(acos);
        acos.addActionListener(l);
        atan.setBounds(10, 370, 50, 50);
        add(atan);
        atan.addActionListener(l);

        ln.setBounds(310, 130, 50, 50);
        add(ln);
        ln.addActionListener(l);
        log.setBounds(310, 190, 50, 50);
        add(log);
        log.addActionListener(l);
        sqrt.setBounds(310, 250, 50, 50);
        add(sqrt);
        sqrt.addActionListener(l);
        deg.setBounds(310, 310, 50, 50);
        add(deg);
        deg.addActionListener(l);

        clear.setBounds(70, 70, 50, 50);
        clear.setFont(font);
        add(clear);
        clear.addActionListener(l);
        backspace.setBounds(130, 70, 50, 50);
        backspace.setFont(font);
        add(backspace);
        backspace.addActionListener(l);
        mod.setBounds(190, 70, 50, 50);
        add(mod);
        mod.addActionListener(l);
        equ.setBounds(130, 370, 110, 50);
        equ.setFont(font);
        add(equ);
        equ.addActionListener(l);
        deposit.setBounds(250, 70, 50, 50);
        add(deposit);
        deposit.addActionListener(l);
        credit.setBounds(310, 70, 50, 50);
        add(credit);
        credit.addActionListener(l);

        for(int x = 0; x < 3; x++ )
            for(int y = 0; y < 3; y++){
                numbers[x * 3 + y + 1] = new JButton((x * 3 + y + 1) + "");
                numbers[x * 3 + y + 1].setBounds(y * 60 + 70, x * 60 + 130, 50, 50 );
                numbers[x * 3 + y + 1].setFont(font);
                add(numbers[x * 3 + y + 1]);
            }

        output.setBounds(10, 10, 350, 50);
        output.setFont(font);
        output.setEditable(true);
        add(output);

        for(JButton b : numbers)
            b.addActionListener(l);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char symbol = e.getKeyChar();

                if (!Character.isDigit(symbol))
                    return;
                output.setText(output.getText() + symbol);
            }
        });
    }
}
