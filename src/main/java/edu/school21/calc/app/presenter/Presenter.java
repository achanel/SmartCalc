package edu.school21.calc.app.presenter;

import edu.school21.calc.app.models.CalcModel;
import edu.school21.calc.app.models.CreditModel;
import edu.school21.calc.app.models.DepositModel;
import edu.school21.calc.app.models.FunctionModel;
import edu.school21.calc.app.view.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

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
    public void keyPressed(String str) {
        try {
            double answer = calcModel.doCalculation(str);
            calcView.printResult(answer);
        } catch (Exception e){
            e.printStackTrace();
        }
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
//            calcView.printError();
        }
    }
    @Override
    public String history(String history) {
        if (history.equals("print"))
            return (readHistory());
        else if (history.equals("clear")) {
            return (clearHistory());
        }
        return null;
    }

    @Override
    public String readHistory() {
        int skipLine = 0;
        List<String> stringList = new LinkedList<>();
        try {
            Scanner sc = new Scanner(new File("target/calcHistory.txt"));
            String strFromFile;
            while(sc.hasNextLine()){
                skipLine++;
                strFromFile = sc.nextLine();
                stringList.add(strFromFile);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        for (int i = skipLine; i > 1; i--){
            stringList = stringList.subList(1,stringList.size());
        }
        System.out.println(stringList);
        return stringList.toString();
    }

    @Override
    public String clearHistory() {
        String ret = "success";
        try {
            Files.delete(Paths.get("target/calcHistory.txt"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            ret = "fail";
        }
        return ret;
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
    public void doCredit(CreditView creditView, double sum, String duration, double percent, String type) throws Exception {
        CreditModel creditModel = new CreditModel(sum, duration, percent, type);
        creditView.printCredit(creditModel.getArray(),
                creditModel.getResult(), creditModel.getMonthPay(), creditModel.getOverpayment());
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

