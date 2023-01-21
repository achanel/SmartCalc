package edu.school21.calc.app;

import javax.swing.*;
import java.text.DecimalFormat;
import java.util.Objects;

public class DepositView extends JPanel{
    final JFrame depositWindow = new JFrame("Credit Calculator");

    public DepositView() {
        depositWindow.setSize(320, 447);
        depositWindow.add(this);
        depositWindow.setResizable(false);
        depositWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        depositWindow.setVisible(true);

        setLayout(null);
        setFocusable(true);
        grabFocus();

        JButton calculate = new JButton("CALC");
        JButton clear = new JButton("CLEAR");
        JTextField sumLabel = new JTextField("Сумма Вклада");
        JTextField durationLabel = new JTextField("Срок Размещения, дней");
        JTextField percentLabel = new JTextField("Процентная ставка, %");
        JTextField taxLabel = new JTextField("Налоговая Ставка, %");
        JTextField capitalizationLabel = new JTextField("Капитализация: ");
        JTextField paymentLabel = new JTextField("Выплаты: ");
        JTextField repLabel = new JTextField("Пополнение: ");
        JTextField sumInput = new JTextField("");
        JTextField durationInput = new JTextField("");
        JTextField percentInput = new JTextField("");
        JTextField taxInput = new JTextField("");
        String[] capLine = {"ежедневно", "еженедельно", "раз в месяц", "раз в 2 месяца",
                "раз в квартал", "раз в 4 месяца", "раз в полгода", "раз в год"};
        JComboBox<String> capitalizationInput= new JComboBox<>(capLine);
        String[] payLine = {"в конце срока", "ежедневно", "еженедельно", "раз в месяц",
                "раз в 2 месяца", "раз в квартал", "раз в 4 месяца", "раз в полгода", "раз в год"};
        JComboBox<String> paymentInput= new JComboBox<>(payLine);
        String[] repLine = {"не предусмотрено", "раз в месяц",
                "раз в 2 месяца", "раз в квартал", "раз в 4 месяца", "раз в полгода", "раз в год"};
        JComboBox<String> replenishmentInput= new JComboBox<>(repLine);

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
        durationInput.setEditable(true);
        add(durationInput);

        percentLabel.setBounds(10, 120, 150, 30);
        percentLabel.setEditable(false);
        add(percentLabel);
        percentInput.setBounds(160, 120, 150, 30);
        percentInput.setEditable(true);
        add(percentInput);

        taxLabel.setBounds(10, 150, 150, 30);
        taxLabel.setEditable(false);
        add(taxLabel);
        taxInput.setBounds(160, 150, 150, 30);
        taxInput.setEditable(true);
        add(taxInput);

        capitalizationLabel.setBounds(10, 180, 150, 30);
        capitalizationLabel.setEditable(false);
        add(capitalizationLabel);
        capitalizationInput.setBounds(160, 180, 150, 30);
        add(capitalizationInput);

        paymentLabel.setBounds(10, 210, 150, 30);
        paymentLabel.setEditable(false);
        add(paymentLabel);
        paymentInput.setBounds(160, 210, 150, 30);
        add(paymentInput);

        repLabel.setBounds(10, 240, 150, 30);
        repLabel.setEditable(false);
        add(repLabel);
        replenishmentInput.setBounds(160, 240, 150, 30);
        add(replenishmentInput);

        JTextArea output = new JTextArea("Результаты расчета:\n\n");
        JScrollPane scrollPane = new JScrollPane(output,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(10, 270, 300, 150);
        output.setLineWrap(true);
        output.setEditable(false);
        output.setVisible(true);
        add(scrollPane);

        calculate.addActionListener(e -> {
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            DepositModel depositModelModel = new DepositModel();
        });
        clear.addActionListener(e-> output.setText("Результаты расчета:\n\n"));
    }
}
