package edu.school21.calc.app.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CreditModelTest {
    private final double sum = 1000000;
    private final String duration = "1 год";
    private final double percent = 10;

    @Test
    void annCalc() throws Exception {
        double checkMonthlyPayment = 83785.41155580529;
        double checkOverPayment = 5424.938669663388;
        double checkTotalPayment = 1005424.9386696634;

        CreditModel creditCalc = new CreditModel(sum, duration, percent, "аннуитетный");
        creditCalc.annCalc();
        double monthlyPayment = creditCalc.getMonthPay();
        double overPayment = creditCalc.getOverpayment();
        double totalPayment = creditCalc.getResult();

        Assertions.assertEquals(checkMonthlyPayment, monthlyPayment);
        Assertions.assertEquals(checkOverPayment, overPayment);
        Assertions.assertEquals(checkTotalPayment, totalPayment);

        Assertions.assertThrows(Exception.class, () -> new CreditModel(sum, duration, 0.0, "аннуитетный"));
    }

    @Test
    void defCalc() throws Exception {
        String checkFirstPayment = "91666,67";
        String checkLastPayment = "84027,78";
        double checkOverPayment = 54166.67000000016;
        double checkTotalPayment = 1054166.6700000002;

        CreditModel creditCalc = new CreditModel(sum, duration, percent, "дифференцированный");

        String firstMonthlyPayment = creditCalc.getArray().get(0);
        double overPayment = creditCalc.getOverpayment();
        double totalPayment = creditCalc.getResult();
        String lastMonthlyPayment = creditCalc.getArray().get(11);
        creditCalc.defCalc();

        Assertions.assertEquals(checkOverPayment, overPayment);
        Assertions.assertEquals(checkTotalPayment, totalPayment);
        Assertions.assertEquals(checkFirstPayment, firstMonthlyPayment);
        Assertions.assertEquals(checkLastPayment, lastMonthlyPayment);

        Assertions.assertThrows(Exception.class, () -> new CreditModel(sum, duration, 0.0, "дифференцированный"));
    }
}