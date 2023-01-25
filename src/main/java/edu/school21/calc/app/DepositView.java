package edu.school21.calc.app;

import javax.swing.*;
import java.text.DecimalFormat;
import java.util.Objects;

public class DepositView extends JPanel{
    final JFrame depositWindow = new JFrame("Credit Calculator");

    public DepositView() {
        depositWindow.setSize(330, 537);
        depositWindow.add(this);
        depositWindow.setResizable(false);
        depositWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        depositWindow.setVisible(true);

        setLayout(null);
        setFocusable(true);
        grabFocus();

        JButton calculate = new JButton("CALC");
        JButton clear = new JButton("CLEAR");
        JTextField sumLabel = new JTextField("Сумма Вклада: ");
        JTextField durationLabel = new JTextField("Срок Размещения: ");
        JTextField monthLabel = new JTextField("месяцы");
        JTextField percentLabel = new JTextField("Процентная ставка, %");
        JTextField taxLabel = new JTextField("Налоговая Ставка, %");
        JTextField accrualLabel = new JTextField("Начислено процентов:");
        JTextField capitalizationLabel = new JTextField("Капитализация: ");
        JTextField paymentLabel = new JTextField("Выплаты: ");
        JTextField repLabel = new JTextField("Пополнение: ");
        JTextField repSumLabel = new JTextField("Сумма пополнения: ");

        JTextField sumInput = new JTextField("");
        JTextField durationInput = new JTextField("");
        String[] durLine = {"1 месяц", "3 месяца", "6 месяцев", "9 месяцев", "1 год",
                "1,5 года", "2 года", "3 года", "5 лет", "Задать свой"};
        JComboBox<String> durationField = new JComboBox<>(durLine);
        JTextField percentInput = new JTextField("");
        JTextField taxInput = new JTextField("");
        String[] capLine = {"нет", "ежедневно", "еженедельно", "раз в месяц", "раз в 2 месяца",
                "раз в квартал", "раз в 4 месяца", "раз в полгода", "раз в год"};
        JComboBox<String> capitalizationInput= new JComboBox<>(capLine);
        String[] accrualLine = {"Добавлять ко вкладу", "Выплачивать"};
        JComboBox<String> accrualInput = new JComboBox<>(accrualLine);
        String[] payLine = {"нет", "в конце срока", "ежедневно", "еженедельно", "раз в месяц",
                "раз в 2 месяца", "раз в квартал", "раз в 4 месяца", "раз в полгода", "раз в год"};
        JComboBox<String> paymentInput = new JComboBox<>(payLine);
        String[] repLine = {"не предусмотрено", "раз в месяц",
                "раз в 2 месяца", "раз в квартал", "раз в 4 месяца", "раз в полгода", "раз в год"};
        JComboBox<String> replenishmentInput = new JComboBox<>(repLine);
        JTextField repSumInput = new JTextField("");

        calculate.setBounds(10, 10 ,50,50);
        add(calculate);
        clear.setBounds(70, 10 ,50,50);
        add(clear);

        sumLabel.setBounds(10, 60, 160, 30);
        sumLabel.setEditable(false);
        add(sumLabel);
        sumInput.setBounds(170, 60, 150, 30);
        sumInput.setEditable(true);
        add(sumInput);

        durationLabel.setBounds(10, 90, 160, 30);
        durationLabel.setEditable(false);
        add(durationLabel);
        durationField.setBounds(170, 90, 150, 30);
        add(durationField);
        durationInput.setBounds(170, 120, 75, 30);
        durationInput.setEditable(false);
        add(durationInput);
        monthLabel.setBounds(245,120,75, 30);
        monthLabel.setEditable(false);
        add(monthLabel);

        percentLabel.setBounds(10, 150, 160, 30);
        percentLabel.setEditable(false);
        add(percentLabel);
        percentInput.setBounds(170, 150, 150, 30);
        percentInput.setEditable(true);
        add(percentInput);

        taxLabel.setBounds(10, 180, 160, 30);
        taxLabel.setEditable(false);
        add(taxLabel);
        taxInput.setBounds(170, 180, 150, 30);
        taxInput.setEditable(true);
        add(taxInput);

        accrualLabel.setBounds(10, 210, 160, 30);
        accrualLabel.setEditable(false);
        add(accrualLabel);
        accrualInput.setBounds(170, 210, 150, 30);
        add(accrualInput);

        capitalizationLabel.setBounds(10, 240, 160, 30);
        capitalizationLabel.setEditable(false);
        add(capitalizationLabel);
        capitalizationInput.setBounds(170, 240, 150, 30);
        capitalizationInput.setEnabled(true);
        add(capitalizationInput);

        paymentLabel.setBounds(10, 270, 160, 30);
        paymentLabel.setEditable(false);
        add(paymentLabel);
        paymentInput.setBounds(170, 270, 150, 30);
        paymentInput.setEnabled(false);
        add(paymentInput);

        repLabel.setBounds(10, 300, 160, 30);
        repLabel.setEditable(false);
        add(repLabel);
        replenishmentInput.setBounds(170, 300, 150, 30);
        add(replenishmentInput);

        repSumLabel.setBounds(10, 330, 160, 30);
        repSumLabel.setEditable(false);
        add(repSumLabel);
        repSumInput.setBounds(170, 330, 150, 30);
        repSumInput.setEditable(false);
        add(repSumInput);

        JTextArea output = new JTextArea("Результаты расчета:\n\n");
        JScrollPane scrollPane = new JScrollPane(output,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(10, 360, 310, 150);
        output.setLineWrap(true);
        output.setEditable(false);
        output.setVisible(true);
        add(scrollPane);

        calculate.addActionListener(e -> {
            output.setText("Результаты расчета:\n\n");
            try{
                double sumCheck = Double.parseDouble(sumInput.getText());
                double durationCheck = 0.0;
                if (Objects.equals(durationField.getSelectedItem(), "Задать свой"))
                    durationCheck = Double.parseDouble(durationInput.getText());
                double percentCheck = Double.parseDouble(percentInput.getText());
                double taxCheck = Double.parseDouble(taxInput.getText());
                double repCheck = 0.0;
                if (!Objects.equals(replenishmentInput.getSelectedItem(), "не предусмотрено"))
                    repCheck = Double.parseDouble(repSumInput.getText());
                if((percentCheck < 0 || percentCheck > 100.0) ||
                        (taxCheck < 0 || taxCheck > 100) || sumCheck < 0 || durationCheck < 0)
                    output.append("Wrong Input!");
                else {
                    DecimalFormat decimalFormat = new DecimalFormat("#.##");
                    DepositModel depositModelModel = new DepositModel( sumCheck,
                            Objects.requireNonNull(durationField.getSelectedItem()).toString(),
                            durationCheck, percentCheck, taxCheck,
                            Objects.requireNonNull(accrualInput.getSelectedItem()).toString(),
                            Objects.requireNonNull(capitalizationInput.getSelectedItem()).toString(),
                            Objects.requireNonNull(paymentInput.getSelectedItem()).toString(),
                            Objects.requireNonNull(replenishmentInput.getSelectedItem()).toString(),
                            repCheck);
                    if (paymentInput.getSelectedItem().equals("нет")) {
                        output.append("Начисленные проценты: " + decimalFormat.format(depositModelModel.getFinalPercent()) +
                                "\nCумма налога: " + decimalFormat.format(depositModelModel.getFinalTax()) +
                                "\nCумма на вкладе к концу срока :" + decimalFormat.format(depositModelModel.getFinalSum()));
                    } else {
                        output.append("Начисленные проценты: " + decimalFormat.format(depositModelModel.getFinalPercent()) +
                                "\nCумма налога: " + decimalFormat.format(depositModelModel.getFinalTax()) +
                                "\nCумма на вкладе к концу срока :" + decimalFormat.format(depositModelModel.getFinalSum()) +
                                "\nКаждая выплата: " + decimalFormat.format(depositModelModel.getEveryPayment()));
                    }
                }
            } catch (Exception ex){
                output.append("Wrong Input!");
            }
        });

        accrualInput.addActionListener(e -> {
            if(Objects.equals(accrualInput.getSelectedItem(), "Добавлять ко вкладу")){
                paymentInput.setSelectedIndex(0);
                paymentInput.setEnabled(false);
                capitalizationInput.setEnabled(true);
            } else if (Objects.equals(accrualInput.getSelectedItem(), "Выплачивать")) {
                capitalizationInput.setSelectedIndex(0);
                capitalizationInput.setEnabled(false);
                paymentInput.setEnabled(true);
            }
        });

        replenishmentInput.addActionListener(e -> {
            repSumInput.setEditable(!Objects.equals(replenishmentInput.getSelectedItem(), "не предусмотрено"));
        });

        durationField.addActionListener(e -> {
            if (Objects.equals(durationField.getSelectedItem(), "Задать свой")) {
                durationInput.setEditable(true);
            } else {
                durationInput.setText("");
            }
        });

        clear.addActionListener(e-> output.setText("Результаты расчета:\n\n"));
    }
}
