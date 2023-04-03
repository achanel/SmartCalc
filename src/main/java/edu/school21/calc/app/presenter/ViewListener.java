package edu.school21.calc.app.presenter;

import edu.school21.calc.app.view.CreditView;
import edu.school21.calc.app.view.DepositView;

public interface ViewListener {
    void keyPressed(String str) throws Exception;
    void printHistory(String str, int flag);
    String history(String history);
    String readHistory();
    String clearHistory();
    void doFunctions(double x1, double x2, double y1, double y2, String f);
    double doAlg(double x);
    void doCredit(CreditView creditView, double sum, String duration, double percent, String type) throws Exception;
    void doDeposit(DepositView depositView, double sum, String durationS, double durationD,
                   double percent, double tax, String accrual, String capitalization,
                   String payment, String replenishment, double replenishmentD);
}
