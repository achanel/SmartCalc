package edu.school21.calc.app.view;

import edu.school21.calc.app.presenter.Presenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CalcView extends JPanel{
    private Presenter presenter;
    private final JTextField output = new JTextField();
    private final JButton[] numbers = new JButton[10];
    private final JButton clear = new JButton("C");
    private final JButton backspace = new JButton("B");
    private final JButton functions = new JButton("F");
    private final JButton mod = new JButton("mod");
    private final JButton equ = new JButton("=");
    private final JButton plus = new JButton("+");
    private final JButton minus = new JButton("-");
    private final JButton multi = new JButton("*");
    private final JButton dev = new JButton("/");
    private final JButton leftBracket = new JButton("(");
    private final JButton rightBracket = new JButton(")");
    private final JButton history = new JButton("his");
    private final JButton clearHistory = new JButton(	"clh");
    private final JButton sin = new JButton("sin");
    private final JButton cos = new JButton("cos");
    private final JButton tan = new JButton("tan");
    private final JButton asin = new JButton("asin");
    private final JButton acos = new JButton("acos");
    private final JButton atan = new JButton("atan");

    private final JButton pm = new JButton("+/-");
    private final JButton dot = new JButton(".");

    private final JButton ln = new JButton("ln");
    private final JButton log = new JButton("log");
    private final JButton sqrt = new JButton("√");
    private final JButton pow = new JButton("x^y");

    private final JButton deposit = new JButton("DEP");
    private final JButton credit = new JButton("CRED");
    private final JButton readme = new JButton("?");

    public void addPresenter(final Presenter p){
        presenter = p;
    }
    public CalcView() {

        final JFrame window = new JFrame("SmartCalc");
        window.setSize(370, 447);
        window.add(this);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

        setLayout(null);
        setFocusable(true);
        grabFocus();

        ActionListener l = (ActionEvent e ) -> {
            JButton b = (JButton)e.getSource();
            if (b == clear) {
                output.setText("");
            } else if (b == backspace) {
                if (output.getText().length() > 0) {
                    StringBuilder back = new StringBuilder(output.getText());
                    back.deleteCharAt(output.getText().length() - 1);
                    output.setText(back.toString());
                }
            } else if (b == numbers[0]) {
                if (output.getText().equals("0"))
                    return;
                else
                    output.setText(output.getText() + "0");
            } else if (b == numbers[1]){
                output.setText(output.getText() + "1");
            } else if (b == numbers[2]){
                output.setText(output.getText() + "2");
            } else if (b == numbers[3]){
                output.setText(output.getText() + "3");
            } else if (b == numbers[4]){
                output.setText(output.getText() + "4");
            } else if (b == numbers[5]){
                output.setText(output.getText() + "5");
            } else if (b == numbers[6]){
                output.setText(output.getText() + "6");
            } else if (b == numbers[7]){
                output.setText(output.getText() + "7");
            } else if (b == numbers[8]){
                output.setText(output.getText() + "8");
            } else if (b == numbers[9]){
                output.setText(output.getText() + "9");
            } else if (b == dot) {
                output.setText(output.getText() + ".");
            } else if (b == leftBracket) {
                output.setText(output.getText() + "(");
            } else if (b == rightBracket) {
                output.setText(output.getText() + ")");
            } else if (b == plus) {
                output.setText(output.getText() + "+");
            } else if (b == minus) {
                output.setText(output.getText() + "-");
            } else if (b == multi) {
                output.setText(output.getText() + "*");
            } else if (b == dev) {
                output.setText(output.getText() + "/");
            } else if (b == sin) {
                output.setText(output.getText() + "sin(");
            } else if (b == cos) {
                output.setText(output.getText() + "cos(");
            } else if (b == tan) {
                output.setText(output.getText() + "tan(");
            } else if (b == asin) {
                output.setText(output.getText() + "asin(");
            } else if (b == acos) {
                output.setText(output.getText() + "acos(");
            } else if (b == atan) {
                output.setText(output.getText() + "atan(");
            } else if (b == ln) {
                output.setText(output.getText() + "ln(");
            } else if (b == log) {
                output.setText(output.getText() + "log(");
            } else if (b == sqrt) {
                output.setText(output.getText() + "sqrt(");
            } else if (b == pow) {
                output.setText(output.getText() + "^");
            } else if (b == mod) {
                output.setText(output.getText() + "mod");
            } else if (b == pm) {
                output.setText(output.getText() + "-");
            } else if (b == equ) {
                presenter.printHistory(output.getText(), 0);
                try {
                    presenter.keyPressed(output.getText());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            } else if (b == history) {
                output.setText(presenter.history("print"));
            } else if (b == clearHistory) {
                presenter.history("clear");
            } else if (b == functions) {
                SwingUtilities.invokeLater(() -> {
                    FunctionView functionView = new FunctionView();
                    functionView.addPresenter(presenter);
                });
            } else if (b == credit) {
                SwingUtilities.invokeLater(() -> {
                    CreditView creditView = new CreditView();
                    creditView.addPresenter(presenter);
                });
            } else if (b == deposit) {
                SwingUtilities.invokeLater(() -> {
                    DepositView depositView = new DepositView();
                    depositView.addPresenter(presenter);
                });
            } else if (b == readme) {
                SwingUtilities.invokeLater(ReadmeView::new);
            }
        };
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char symbol = e.getKeyChar();
                if (Character.isDigit(symbol) || symbol == '(' || symbol == ')' || symbol == '.' || symbol == 'E')
                    output.setText(output.getText() + symbol);
                else
                    return;
            }
        });

        pm.setBounds(70, 310, 50, 50);
        add(pm);
        pm.addActionListener(l);
        numbers[0] = new JButton("0");
        numbers[0].setBounds(130, 310, 50, 50 );
        Font font = new Font("SanSerif", Font.BOLD, 20);
        numbers[0].setFont(font);
        add(numbers[0]);
        dot.setBounds(190, 310, 50, 50);
        dot.setFont(font);
        add(dot);
        dot.addActionListener(l);

        plus.setBounds(250, 130, 50, 50);
        plus.setFont(font);
        add(plus);
        plus.addActionListener(l);
        minus.setBounds(250, 190, 50, 50);
        minus.setFont(font);
        add(minus);
        minus.addActionListener(l);
        multi.setBounds(250, 250, 50, 50);
        multi.setFont(font);
        add(multi);
        multi.addActionListener(l);
        dev.setBounds(250, 310, 50, 50);
        dev.setFont(font);
        add(dev);
        dev.addActionListener(l);

        leftBracket.setBounds(310, 370, 25, 50);
        leftBracket.setFont(font);
        add(leftBracket);
        leftBracket.addActionListener(l);
        rightBracket.setBounds(335, 370, 25, 50);
        rightBracket.setFont(font);
        add(rightBracket);
        rightBracket.addActionListener(l);

        sin.setBounds(10, 70, 50, 50);
        add(sin);
        sin.addActionListener(l);
        cos.setBounds(10, 130, 50, 50);
        add(cos);
        cos.addActionListener(l);
        tan.setBounds(10, 190, 50, 50);
        add(tan);
        tan.addActionListener(l);
        asin.setBounds(10, 250, 50, 50);
        add(asin);
        asin.addActionListener(l);
        acos.setBounds(10, 310, 50, 50);
        add(acos);
        acos.addActionListener(l);
        atan.setBounds(10, 370, 50, 50);
        add(atan);
        atan.addActionListener(l);

        ln.setBounds(310, 130, 50, 50);
        add(ln);
        ln.addActionListener(l);
        log.setBounds(310, 190, 50, 50);
        add(log);
        log.addActionListener(l);
        sqrt.setBounds(310, 250, 50, 50);
        add(sqrt);
        sqrt.addActionListener(l);
        pow.setBounds(310, 310, 50, 50);
        add(pow);
        pow.addActionListener(l);

        clear.setBounds(70, 70, 50, 50);
        clear.setFont(font);
        add(clear);
        clear.addActionListener(l);
        backspace.setBounds(130, 70, 50, 50);
        backspace.setFont(font);
        add(backspace);
        backspace.addActionListener(l);
        mod.setBounds(190, 70, 50, 50);
        add(mod);
        mod.addActionListener(l);
        equ.setBounds(130, 370, 110, 50);
        equ.setFont(font);
        add(equ);
        equ.addActionListener(l);
        deposit.setBounds(250, 70, 50, 50);
        add(deposit);
        deposit.addActionListener(l);
        credit.setBounds(310, 70, 50, 50);
        add(credit);
        credit.addActionListener(l);
        history.setBounds(70, 370, 50, 25);
        add(history);
        history.addActionListener(l);
        clearHistory.setBounds(70, 395, 50, 25);
        add(clearHistory);
        clearHistory.addActionListener(l);
        functions.setBounds(250, 370, 50, 50);
        functions.setFont(font);
        add(functions);
        functions.addActionListener(l);
        readme.setBounds(350, 15, 15, 15);
        add(readme);
        readme.addActionListener(l);

        for(int x = 0; x < 3; x++ )
            for(int y = 0; y < 3; y++){
                numbers[x * 3 + y + 1] = new JButton((x * 3 + y + 1) + "");
                numbers[x * 3 + y + 1].setBounds(y * 60 + 70, x * 60 + 130, 50, 50 );
                numbers[x * 3 + y + 1].setFont(font);
                add(numbers[x * 3 + y + 1]);
            }

        for(JButton b : numbers)
            b.addActionListener(l);

        output.setBounds(10, 10, 340, 50);
        output.setFont(font);
        output.setEditable(true);
        add(output);
    }
    public void printResult(Double result){
        if (Double.toString(result).endsWith(".0")) {
            output.setText(Double.toString(result).replace(".0", ""));
            presenter.printHistory(Double.toString(result).replace(".0", ""), 1);
        } else {
            output.setText(Double.toString(result));
            presenter.printHistory(Double.toString(result), 1);
        }
    }
}
