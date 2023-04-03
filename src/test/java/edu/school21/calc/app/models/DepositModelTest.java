package edu.school21.calc.app.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DepositModelTest {

    private final double sum = 600000;
    private final double duration = 24;
    private final double percent = 10;
    private final double tax = 13;
    @Test
    void simple() {
        DepositModel depositCalc = new DepositModel(sum, "2 года", 0.0, percent, tax,
                "Добавлять ко вкладу", "нет", "нет", "не предусмотрено", 0.0);
        double checkTotal = 704400;
        double checkTaxes = 15600;
        double checkProfit = 120000;

        Assertions.assertEquals(checkTotal, depositCalc.getFinalSum());
        Assertions.assertEquals(checkTaxes, depositCalc.getFinalTax());
        Assertions.assertEquals(checkProfit, depositCalc.getFinalPercent());
    }

    @Test
    void capitalizationMonthly() {
        DepositModel depositCalc = new DepositModel(sum, "2 года", 0.0, percent, tax,
                "Добавлять ко вкладу", "раз в месяц", "нет", "не предусмотрено", 0.0);
        double checkTotal = 732821.5809838716;
        double checkTaxes = 17266.805527903307;
        double checkProfit = 132821.5809838716;

        Assertions.assertEquals(checkTotal, depositCalc.getFinalSum());
        Assertions.assertEquals(checkTaxes, depositCalc.getFinalTax());
        Assertions.assertEquals(checkProfit, depositCalc.getFinalPercent());
    }

    @Test
    void replenishmentYear() {
        DepositModel depositCalc = new DepositModel(sum, "Задать свой", duration, percent, tax,
                "Добавлять ко вкладу", "нет", "нет", "раз в год", 10000);
        double checkTotal = 5.39072278908317E7;
        double checkTaxes = 19486.689419795217;
        double checkProfit = 149897.61092150168;

        Assertions.assertEquals(checkTotal, depositCalc.getFinalSum());
        Assertions.assertEquals(checkTaxes, depositCalc.getFinalTax());
        Assertions.assertEquals(checkProfit, depositCalc.getFinalPercent());
    }

    @Test
    void replenishmentCapitalization() {
        DepositModel depositCalc = new DepositModel(sum, "Задать свой", duration, percent, tax,
                "Добавлять ко вкладу", "раз в полгода", "нет", "раз в год", 10000);
        double checkTotal = 1.0341789565006415E9;
        double checkTaxes = 1.339024643450834E8;
        double checkProfit = 1.0300189565006415E9;

        Assertions.assertEquals(checkTotal, depositCalc.getFinalSum());
        Assertions.assertEquals(checkTaxes, depositCalc.getFinalTax());
        Assertions.assertEquals(checkProfit, depositCalc.getFinalPercent());
    }
}