package edu.school21.calc.app;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CreditModel {
    private final double sum;
    private final Double percent;
    private double duration;
    private final List<String> array = new ArrayList<>();

    private Double result = 0.0;
    DecimalFormat decimalFormat = new DecimalFormat("#.##");
    private Double monthPay = 0.0;

    public List<String> getArray() {
        return array;
    }

    public Double getResult() {
        return result;
    }

    public Double getMonthPay() {
        return monthPay;
    }

    public CreditModel(Double sum, String durationIn, Double percent, String type) {
        this.sum = sum;
        this.percent = percent;
        this.duration = 0.0;

        switch (durationIn){
            case ("1 месяц"):
                duration = 1.0;
                break;
            case ("3 месяца"):
                duration = 3.0;
                break;
            case ("6 месяцев"):
                duration = 6.0;
                break;
            case ("9 месяцев"):
                duration = 9.0;
                break;
            case ("1 год"):
                duration = 12.0;
                break;
            case ("1,5 года"):
                duration = 18.0;
                break;
            case ("2 года"):
                duration = 24.0;
                break;
            case ("3 года"):
                duration = 36.0;
                break;
            case ("4 года"):
                duration = 48.0;
                break;
            case ("5 лет"):
                duration = 60.0;
                break;
            case ("6 лет"):
                duration = 72.0;
                break;
            case ("7 лет"):
                duration = 84.0;
                break;
            case ("10 лет"):
                duration = 120.0;
                break;
            case ("15 лет"):
                duration = 180.0;
                break;
            case ("20 лет"):
                duration = 240.0;
                break;
            case ("25 лет"):
                duration = 300.0;
                break;
            case ("30 лет"):
                duration = 360.0;
                break;
        }

        if (Objects.equals(type, "аннуитетный"))
            annCalc();
        else if (Objects.equals(type, "дифференцированный"))
            defCalc();
    }

    private void defCalc(){
        double mpCount = duration;
        double rest = sum;
        double mpReal = sum / mpCount;
        double mp;
        while (mpCount != 0){
            mp = mpReal + (rest * percent / 1200);
            array.add(decimalFormat.format(mp));
            rest = rest - mpReal;
            mpCount = mpCount - 1;
        }
        for(String element : array){
            try {
                result = result + DecimalFormat.getNumberInstance().parse(element).doubleValue();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        decimalFormat.format(result);
    }

    private void annCalc() {
        double mpCount = duration;
        double r = (duration / 12) / 1200;
        double ak = (r * Math.pow((1 + r), mpCount) / (Math.pow((1 + r), mpCount) - 1));
        monthPay = sum * ak;
        decimalFormat.format(monthPay);
        result = monthPay * mpCount;
        decimalFormat.format(result);
    }


}
