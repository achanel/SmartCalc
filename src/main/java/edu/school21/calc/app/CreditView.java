package edu.school21.calc.app;

import javax.swing.*;
import java.text.DecimalFormat;
import java.util.Objects;

public class CreditView extends JPanel {
    final JFrame creditWindow = new JFrame("Credit Calculator");

    public CreditView() {
        creditWindow.setSize(320, 357);
        creditWindow.add(this);
        creditWindow.setResizable(false);
        creditWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        creditWindow.setVisible(true);

        setLayout(null);
        setFocusable(true);
        grabFocus();

        JButton calculate = new JButton("CALC");
        JButton clear = new JButton("CLEAR");
        JTextField sumLabel = new JTextField("Сумма Кредита");
        JTextField durationLabel = new JTextField("Срок Размещения");
        JTextField percentLabel = new JTextField("Процентная Ставка %");
        JTextField typeLabel = new JTextField("Тип Платежа");
        JTextField sumInput = new JTextField("");
        String[] durationLine = {"1 месяц", "3 месяца", "6 месяцев", "9 месяцев", "1 год",
                "1,5 года", "2 года", "3 года", "4 года", "5 лет", "6 лет", "7 лет", "10 лет", "15 лет",
                "20 лет", "25 лет", "30 лет"};
        JComboBox<String> durationInput = new JComboBox<>(durationLine);
        JTextField percentInput = new JTextField("");
        String[] typeLine = {"аннуитетный", "дифференцированный"};
        JComboBox<String> typeInput = new JComboBox<>(typeLine);

        calculate.setBounds(10, 10 ,50,50);
        add(calculate);
        clear.setBounds(70, 10 ,50,50);
        add(clear);

        sumLabel.setBounds(10, 60, 150, 30);
        sumLabel.setEditable(false);
        add(sumLabel);
        sumInput.setBounds(160, 60, 150, 30);
        sumInput.setEditable(true);
        add(sumInput);

        durationLabel.setBounds(10, 90, 150, 30);
        durationLabel.setEditable(false);
        add(durationLabel);
        durationInput.setBounds(160, 90, 150, 30);
        add(durationInput);

        percentLabel.setBounds(10, 120, 150, 30);
        percentLabel.setEditable(false);
        add(percentLabel);
        percentInput.setBounds(160, 120, 150, 30);
        percentInput.setEditable(true);
        add(percentInput);

        typeLabel.setBounds(10, 150, 150, 30);
        typeLabel.setEditable(false);
        add(typeLabel);
        typeInput.setBounds(160, 150, 150, 30);
        add(typeInput);

        JTextArea output = new JTextArea("Результаты расчета:\n\n");
        JScrollPane scrollPane = new JScrollPane(output,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(10, 180, 300, 150);
        output.setLineWrap(true);
        output.setEditable(false);
        output.setVisible(true);
        add(scrollPane);
        calculate.addActionListener(e -> {
            try {
                double sumCheck = Double.parseDouble(sumInput.getText());
                double percentCheck = Double.parseDouble(percentInput.getText());
            if(percentCheck < 0 || percentCheck > 100.0)
                output.append("Wrong Input!");
            else {
                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                CreditModel creditModel = new CreditModel(Double.parseDouble(sumInput.getText()),
                        Objects.requireNonNull(durationInput.getSelectedItem()).toString(),
                        Double.parseDouble(percentInput.getText()),
                        Objects.requireNonNull(typeInput.getSelectedItem()).toString());
                if (Objects.requireNonNull(typeInput.getSelectedItem()).toString().equals("дифференцированный")){
                    int monthCount = 1;
                    for (String month : creditModel.getArray()) {
                        output.append(monthCount++ + "месяц: " + month + "\n");
                    }
                    double overpayment = creditModel.getResult() - Double.parseDouble(sumInput.getText());
                    output.append("\nПереплата: " + decimalFormat.format(overpayment) + "\n\n");
                    output.append("Общая выплата: " + decimalFormat.format(creditModel.getResult()));
                } else if (Objects.requireNonNull(typeInput.getSelectedItem()).toString().equals("аннуитетный")) {
                    output.append("Ежемесячная плата месяц: " +
                            decimalFormat.format(creditModel.getMonthPay()) + "\n");
                    double overpayment = creditModel.getResult() - Double.parseDouble(sumInput.getText());
                    output.append("\nПереплата: " + decimalFormat.format(overpayment) + "\n\n");
                    output.append("Общая выплата: " + decimalFormat.format(creditModel.getResult()));
                }
            }
            } catch (Exception ex) {
                output.append("Wrong Input!");
            }
        });
        clear.addActionListener(e-> output.setText("Результаты расчета:\n\n"));
    }
}
