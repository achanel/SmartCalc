package edu.school21.calc.app.models;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;

import static java.nio.file.Files.delete;

public class CalcModel {
    public void setIn(String in) {
        this.in = in;
    }

    private String in;
    private int ch;
    private int pos = -1;


    public double doCalculation() throws Exception {
        nextChar();
        double x = parseExpression();
        return x;
    }
    private void nextChar() {
        ch = (++pos < in.length()) ? in.charAt(pos) : -1;
    }
    private boolean eat(int charToEat) {
        while (ch == ' ') nextChar();
        if (ch == charToEat) {
            nextChar();
            return true;
        }
        return false;
    }

    double parse() throws Exception {
        nextChar();
        double x = parseExpression();
        if (pos < in.length()) throw new RuntimeException("Unexpected: " + (char)ch);
        return x;
    }
    private double parseExpression() throws Exception {
        double x = parseTerm();
        for (;;) {
            if      (eat('+')) x += parseTerm(); // addition
            else if (eat('-')) x -= parseTerm(); // subtraction
            else return x;
        }
    }

    private double parseTerm() throws Exception {
        double x = parseFactor();
        for (;;) {
            if      (eat('*')) x *= parseFactor();
            else if (eat('/')) {
                double parseF = parseFactor();
                if (parseF == 0.0) {
                    throw new Exception("Division by zero");
                } else {
                    x /= parseF;
                }
            }
            else if (eat('%')) {
                double parseF = parseFactor();
                if (parseF == 0.0) {
                    throw new Exception("Division by zero");
                }
                x %= parseF;
            }
            else if (eat('E')) x = x * java.lang.Math.pow(10, parseFactor());
            else if (eat('^')) x = java.lang.Math.pow(x, parseFactor());
            else return x;
        }
    }
    double parseFactor() throws Exception {
        if (eat('+')) return +parseFactor(); // unary plus
        if (eat('-')) return -parseFactor(); // unary minus

        double x;
        int startPos = this.pos;
        if (eat('(')) { // parentheses
            x = parseExpression();
            if (!eat(')')) throw new RuntimeException("Missing ')'");
        } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
            while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
            x = Double.parseDouble(in.substring(startPos, this.pos));
        } else if (ch >= 'a' && ch <= 'z') { // functions
            while (ch >= 'a' && ch <= 'z') nextChar();
            String func = in.substring(startPos, this.pos);
            if (eat('(')) {
                x = parseExpression();
                if (!eat(')')) throw new RuntimeException("Missing ')' after argument to " + func);
            } else {
                x = parseFactor();
            }
            switch (func) {
                case "sqrt":
                    x = Math.sqrt(x);
                    break;
                    case "sin":
                        x = Math.sin(Math.toRadians(x));
                        break;
                        case "cos":
                            x = Math.cos(Math.toRadians(x));
                            break;
                        case "tan":
                            x = Math.tan(Math.toRadians(x));
                            break;
                        case "asin":
                            x = Math.asin(Math.toRadians(x));
                            break;
                        case "acos":
                            x = Math.acos(Math.toRadians(x));
                            break;
                        case "atan":
                            x = Math.atan(x);
                            break;
                        case "ln":
                            x = Math.log(x);
                            break;
                        case "log":
                            x = Math.log10(x);
                            break;
                        default:
                            throw new RuntimeException("Unknown function: " + func);
                    }
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
    }
    public String readHistory() {
                StringBuilder history = new StringBuilder();
                try (FileInputStream fileInputStream = new FileInputStream("target/calcHistory.txt")){
                    int i;
                    while ((i=fileInputStream.read())!= -1) {
                        if (i == 61)
                            break;
                        history.append(Character.toString((char) i));
                    }
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                return history.toString();
    }

    public String clearHistory() {
        String ret = "success";
        try {
            delete(Paths.get("target/calcHistory.txt"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            ret = "fail";
        }
        return ret;
    }
}