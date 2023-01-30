package edu.school21.calc.app.presenter;

import edu.school21.calc.app.models.CalcModel;
import edu.school21.calc.app.models.CreditModel;
import edu.school21.calc.app.view.CalcView;
import edu.school21.calc.app.view.CreditView;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

public class Presenter implements ViewListner {
    private CreditView creditView;
    private final CalcView calcView;
    private final CalcModel calcModel;

    public Presenter(final CalcView calcView, final CalcModel calcModel) {
        this.calcView = calcView;
        this.calcModel = calcModel;
        calcView.addPresenter(this);
    }

    public void keyPressed(String str) throws Exception {
        calcModel.setIn(str);
        double answer = calcModel.doCalculation();
        calcView.printResult(answer);
    }

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

    public String history(String history) {
        if (history.equals("print"))
            return (calcModel.readHistory());
        else if (history.equals("clear")) {
            return (calcModel.clearHistory());
        }
        return null;
    }

    public void moreCalc(int flag){
        switch (flag) {
            case 1:
                creditView = new CreditView();
                creditView.addPresenter(this);
        }

    }

    public void doFunctions(){
    }

    public void doCredit(double sum, String duration, double percent, String type){
        CreditModel creditModel = new CreditModel(sum, duration, percent, type);
        creditView.printCredit(creditModel.getArray(),
                creditModel.getResult(), creditModel.getMonthPay());
    }

    public void doDeposit(){
    }
}

