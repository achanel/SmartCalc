package edu.school21.calc.app;

import java.io.FileOutputStream;
import java.io.IOException;

public class Presenter{
    private final CalcView calcView;
    private final CalcModel calcModel;

    public Presenter(final CalcView calcView, final CalcModel calcModel) {
        this.calcView = calcView;
        this.calcModel = calcModel;
        calcView.addPresenter(this);
    }

    public void keyPressed(String str){
        calcView.printResult(Double.valueOf(Double.toString(calcModel.doCalculation(str))));
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
            calcView.printError("ERROR!");
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
}
