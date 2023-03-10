package edu.school21.calc.app.models;

import java.util.Objects;

public class DepositModel {

    private final double finalPercent;
    private final double finalTax;
    private double finalSum;

    private double everyPayment = 0.0;

    public double getFinalPercent() {
        return finalPercent;
    }

    public double getFinalTax() {
        return finalTax;
    }

    public double getFinalSum() {
        return finalSum;
    }

    public double getEveryPayment() {
        return everyPayment;
    }
    public DepositModel(double sum, String durSts, double dur,
                        double percent, double tax, String accrual,
                        String cap, String pay, String repStr, double rep) {
        double duration = 0.0;
        if (Objects.equals(durSts, "Задать свой")) {
            duration = dur * 30;
        } else {
            switch (durSts) {
                case "1 месяц":
                    duration = 30;
                    break;
                case "3 месяца":
                    duration = 90;
                    break;
                case "6 месяцев":
                    duration = 180;
                    break;
                case "9 месяцев":
                    duration = 270;
                    break;
                case "1 год":
                    duration = 365;
                    break;
                case "1,5 года":
                    duration = 548;
                    break;
                case "2 года":
                    duration = 730;
                    break;
                case "3 года":
                    duration = 1095;
                    break;
                case "5 лет":
                    duration = 1825;
                    break;
            }
        }
        double capital = 0.0;
        double payment = -1.0;
        if (Objects.equals(accrual, "Добавлять ко вкладу")){
            switch (cap) {
                case "ежедневно":
                    capital = 365.0;
                    break;
                case "еженедельно":
                    capital = 52.0;
                    break;
                case "раз в месяц":
                    capital = 12.0;
                    break;
                case "раз в 2 месяца":
                    capital = 6.0;
                    break;
                case "раз в квартал":
                    capital = 4.0;
                    break;
                case "раз в 4 месяца":
                    capital = 3.0;
                    break;
                case "раз в полгода":
                    capital = 2.0;
                    break;
                case "раз в год":
                    capital = 1.0;
                    break;
            }
        } else if (Objects.equals(accrual, "Выплачивать")) {
            switch (pay) {
                case "в конце срока":
                    payment = 0.0;
                    break;
                case "ежедневно":
                    payment = 365.0;
                    break;
                case "еженедельно":
                    payment = 52.0;
                    break;
                case "раз в месяц":
                    payment = 12.0;
                    break;
                case "раз в 2 месяца":
                    payment = 6.0;
                    break;
                case "раз в квартал":
                    payment = 4.0;
                    break;
                case "раз в 4 месяца":
                    payment = 3.0;
                    break;
                case "раз в полгода":
                    payment = 2.0;
                    break;
                case "раз в год":
                    payment = 1.0;
                    break;
            }
        }
        double replenishment = 0.0;
        switch (repStr) {
            case "не предусмотрено":
                replenishment = 0.0;
                break;
            case "раз в месяц":
                replenishment = 12.0;
                break;
            case "раз в 2 месяца":
                replenishment = 6.0;
                break;
            case "раз в квартал":
                replenishment = 4.0;
                break;
            case "раз в 4 месяца":
                replenishment = 3.0;
                break;
            case "раз в полгода":
                replenishment = 2.0;
                break;
            case "раз в год":
                replenishment = 1.0;
                break;
        }

        double profit = 0.0;
        double years = duration % 365 + 1;
        if (rep == 0.0) {
            if (capital == 0.0) {
                profit = (sum * percent * duration / 365) / 100;
                finalTax = profit * (tax / 100);
                finalSum = sum + profit - finalTax;
                if (payment == 0.0) everyPayment = profit;
                else everyPayment = profit / (payment * years);
            } else {
                percent /= 100;
                finalSum = sum * Math.pow((1 + percent/365), duration);
                profit = finalSum - sum;
                finalTax = profit * (tax / 100);
            }
        } else {
            double period = years * replenishment;
            if (capital == 0.0) {
                finalSum = sum;
                for (double i = 0.0; i < period; i++) {
                    profit = ((sum + profit + rep) * percent * duration / 365) / 100;
                    finalSum += profit;
                }
                finalTax = profit * (tax / 100);
                finalSum -= finalTax;
                if (payment == 0.0) everyPayment = profit;
                else everyPayment = profit / (payment * years);
            } else {//---
                percent /= 100;
                for (double i = 0.0; i < period; i++) {
                    sum += rep;
                    profit = sum * Math.pow((1 + percent / 365), duration);
                    finalSum += profit;
                }
                profit = finalSum - sum;
                finalTax = profit * (tax / 100);
            }
        }
        finalPercent = profit;
        }
}