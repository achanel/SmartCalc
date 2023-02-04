package edu.school21.calc.app.presenter;

import edu.school21.calc.app.models.CalcModel;
import edu.school21.calc.app.models.CreditModel;
import edu.school21.calc.app.models.DepositModel;
import edu.school21.calc.app.models.FunctionModel;
import edu.school21.calc.app.view.*;

import java.io.FileOutputStream;
import java.io.IOException;

public class Presenter implements ViewListener {
    private FunctionModel functionModel;
    private final CalcView calcView;
    private final CalcModel calcModel;

    public Presenter(final CalcView calcView, final CalcModel calcModel) {
        this.calcView = calcView;
        this.calcModel = calcModel;
        calcView.addPresenter(this);
    }
    @Override
    public void keyPressed(String str) throws Exception {
        calcModel.setIn(str);
        double answer = calcModel.doCalculation();
        calcView.printResult(answer);
    }

    @Override
    public void printHistory(String str, int flag){
        try (FileOutputStream fileOutputStream = new FileOutputStream("target/calcHistory.txt", true)) {
            if (!str.equals("")) {
                if (flag == 0) {
                    fileOutputStream.write((str + " = ").getBytes());
                } else {
                    fileOutputStream.write((str + "\n").getBytes());
                }
            } else return;
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
            calcView.printError();
        }
    }
    @Override
    public String history(String history) {
        if (history.equals("print"))
            return (calcModel.readHistory());
        else if (history.equals("clear")) {
            return (calcModel.clearHistory());
        }
        return null;
    }

    @Override
    public void doFunctions(double x1, double x2, double y1, double y2, String s) {
        FunctionDraw functionDraw = new FunctionDraw(x1, x2, y1, y2, s);
        functionDraw.addPresenter(this);
        functionModel = new FunctionModel(s);
    }

    @Override
    public double doAlg(double x){
        return (functionModel.alg(x));
    }
    @Override
    public void doCredit(CreditView creditView, double sum, String duration, double percent, String type){
        CreditModel creditModel = new CreditModel(sum, duration, percent, type);
        creditView.printCredit(creditModel.getArray(),
                creditModel.getResult(), creditModel.getMonthPay());
    }
    @Override
    public void doDeposit(DepositView depositView, double sum, String durationS, double durationD,
                          double percent, double tax, String accrual, String capitalization,
                          String payment, String replenishment, double replenishmentD){
        DepositModel depositModel = new DepositModel(sum, durationS, durationD, percent,
                tax, accrual, capitalization, payment, replenishment, replenishmentD);
        depositView.printDeposit(depositModel.getFinalSum(), depositModel.getFinalPercent(),
                depositModel.getFinalTax(), depositModel.getEveryPayment());
    }
}

