package edu.school21.calc.app.models;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class CalcModel {
    short NUMBER = 0;
    short PLUS_MINUS = 1;
    short MUL_DIV_MOD = 2;
    short POWER = 3;
    short MATH_OPERATOR = 4;
    short BRACKETS = 5;

    private String in;

    public CalcModel(double value, String in, short priority) {
        this.in = in;
    }

    public short getPriority() {
        return priority;
    }

    public String getOperator() {
        return operator;
    }

    public double getValue() {
        return value;
    }

    private short priority;
    private String operator;
    private double value;

    public void setIn(String in) {
        this.in = in;
    }

    public double doCalculation(){
        List<CalcModel> list = new ArrayList<>();
        replace(in);
        if(!validation(in))
            throw new RuntimeException();
        mainParser(in, list);
        list = polishNotation(list);
        return(calculateExpression(list));
    }

    void operatorParser(char symbol, List<CalcModel> list) {
        if (symbol == '+') {
            CalcModel lexeme = new CalcModel(0, "+", PLUS_MINUS);
            list.add(lexeme);
        } else if (symbol == '-') {
            CalcModel lexeme = new CalcModel(0, "-", PLUS_MINUS);
            list.add(lexeme);
        } else if (symbol == '*') {
            CalcModel lexeme = new CalcModel(0, "*", MUL_DIV_MOD);
            list.add(lexeme);
        } else if (symbol == '/') {
            CalcModel lexeme = new CalcModel(0, "/", MUL_DIV_MOD);
            list.add(lexeme);
        } else if (symbol == 'm') {
            CalcModel lexeme = new CalcModel(0, "mod", MUL_DIV_MOD);
            list.add(lexeme);
        } else if (symbol == '^') {
            CalcModel lexeme = new CalcModel(0, "^", POWER);
            list.add(lexeme);
        }
    }

    void mathFuncParser(char i, char next, List<CalcModel> list) {
        if (i == 's') {
            if (next == 'i') {
                CalcModel lexeme = new CalcModel(0, "sin", MATH_OPERATOR);
                list.add(lexeme);
            } else if (next == 'q') {
                CalcModel lexeme = new CalcModel(0, "sqrt", MATH_OPERATOR);
                list.add(lexeme);
            }
        } else if (i == 'a') {
            if (next == 's') {
                CalcModel lexeme = new CalcModel(0, "asin", MATH_OPERATOR);
                list.add(lexeme);
            } else if (next == 'c') {
                CalcModel lexeme = new CalcModel(0, "acos", MATH_OPERATOR);
                list.add(lexeme);
            } else if (next == 't') {
                CalcModel lexeme = new CalcModel(0, "atan", MATH_OPERATOR);
                list.add(lexeme);
            }
        } else if (i == 'l') {
            if (next == 'o') {
                CalcModel lexeme = new CalcModel(0, "log", MATH_OPERATOR);
                list.add(lexeme);
            } else if (next == 'n') {
                CalcModel lexeme = new CalcModel(0, "ln", MATH_OPERATOR);
                list.add(lexeme);
            }
        } else if (i == 't') {
            CalcModel lexeme = new CalcModel(0, "tan", MATH_OPERATOR);
            list.add(lexeme);
        } else if (i == 'c') {
            CalcModel lexeme = new CalcModel(0, "cos", MATH_OPERATOR);
            list.add(lexeme);
        }
    }
    
    private void mainParser(String in, List<CalcModel> list) {
        for (int i = 0; i != in.length() - 1; i++) {
            if (Character.isDigit(in.charAt(i)) || in.charAt(i) == '.') {
                StringBuilder num = new StringBuilder();
                for (; Character.isDigit(in.charAt(i)) || in.charAt(i) == '.'; i++) num.append(in.charAt(i));
                i--;
                CalcModel num_lex = new CalcModel(Double.parseDouble(num.toString()), "", NUMBER);
                list.add(num_lex);
            }
            if (isOperator(in.charAt(i))) {
                operatorParser(in.charAt(i), list);
            } else if (isFunction(in.charAt(i))) {
                mathFuncParser(in.charAt(i), in.charAt(i + 1), list);
                if (in.charAt(i) == 'a' && in.charAt(i + 1) != 'n') i += 3;
                if (in.charAt(i + 1) == 'q') i += 3;
            } else if (in.charAt(i) == '(') {
                CalcModel lexeme = new CalcModel(0, "(", BRACKETS);
                list.add(lexeme);
            } else if (in.charAt(i) == ')') {
                CalcModel lexeme = new CalcModel(0, ")", BRACKETS);
                list.add(lexeme);
            }
        }
    }

    boolean validation(String str){
        return validationBrackets(str) && operatorAndDigitsValidation(str);
    }

    private boolean validationBrackets(String str) {
        boolean result = false;
        boolean emptyBody = false;
        short bracketsCheck = 0;
        for (int i = 0; i != str.length(); i++) {
            if (str.charAt(i) == '(') {
                bracketsCheck++;
                if (str.charAt(i + 1) == ')') emptyBody = true;
            } else if (str.charAt(i) == ')') {
                bracketsCheck--;
                if (bracketsCheck < 0) result = false;
            }
        }
        if (bracketsCheck == 0 && !emptyBody) result = true;
        return result;
    }

    private boolean operatorAndDigitsValidation(String str) {
        boolean result = true;
        for (int i = 0; i != str.length(); i++) {
            if (str.charAt(i) == '.' && (!Character.isDigit(str.charAt(i + 1)) || !Character.isDigit(str.charAt(i - 1)))) {
                result = false;
            } else if (isOperator(str.charAt(i))) {
                if (str.charAt(i) == 'm' && isOperator(str.charAt(i - 1))) {
                    result = false;
                } else if (str.charAt(i) == '+' || str.charAt(i) == '-') {
                    if (isOperator(str.charAt(i + 1)) || isOperator(str.charAt(i - 1)))
                    result = false;
        else if (str.charAt(i + 1) == ')')
                    result = false;
                } else if (str.charAt(i) == '*' || str.charAt(i) == '/' || str.charAt(i) == '^') {
                    if (isOperator(str.charAt(i + 1)) || isOperator(str.charAt(i - 1)))
                    result = false;
        else if (str.charAt(i + 1) == ')' || str.charAt(i - 1) == '(')
                    result = false;
                } else if (isFunction(str.charAt(i))) {
                    if (!isOperator(str.charAt(i - 1))) result = false;
                } else if (str.charAt(i) == 'm' && !Character.isDigit(str.charAt(i - 1))) {
                    result = false;
                }
            } else if (isFunction(str.charAt(i)) && str.charAt(i - 1) == ')') {
                result = false;
            } else if (str.charAt(i) == '(' || str.charAt(i) == ')') {
                if (i != 0 && str.charAt(i) != str.charAt(str.length())) {
                    if (!isOperator(str.charAt(i - 1)) && str.charAt(i) == '(' && str.charAt(i - 1) != '(' &&
                            (!isFunction(str.charAt(i - 3)) && !isFunction(str.charAt(i - 2)) &&
                    !isFunction(str.charAt(i - 4)))) {
                        result = false;
                    } else if (!isOperator(str.charAt(i + 1)) && str.charAt(i) == ')' && str.charAt(i + 1) != ')') {
                        result = false;
                    }
                }
            }
        }
        return result;
    }

    boolean isOperator(char symbol) {
        return symbol == '^' || symbol == '*' || symbol == '+' || symbol == '-' ||
                symbol == '/' || symbol == 'm';
    }

    boolean isFunction(char symbol) {
        return symbol == 's' || symbol == 'c' || symbol == 't' || symbol == 'a' ||
                symbol == 'l';
    }

    void replace(String str){
        str.replace("e+", "*10^");
        str.replace("e-", "*10^");
        str.replace("(+", "(0+");
        str.replace("(-", "(0-");
    }

    List<CalcModel> polishNotation(List<CalcModel> list) {
        List<CalcModel> ready = new ArrayList<>();
        List<CalcModel> support = new ArrayList<>();
        for (CalcModel i : list) {
            if (i.getPriority() == NUMBER) {
                ready.add(i);
            } else {
                if (Objects.equals(i.getOperator(), "(") &&
                        i.getPriority() ==
                                BRACKETS) {  // Если скобка открылась, то пушим ее в саппорт
                    support.add(i);
                } else {
                    // Если скобка закрылась, начинаем доставать из саппорта все, пока не
                    // встретим открытую скобку
                    if (Objects.equals(i.getOperator(), ")")) {
                        while (!support.isEmpty() && !Objects.equals(support.get(support.size() - 1).getOperator(), "(")) {
                            CalcModel elem = support.get(support.size() - 1);
                            support.remove(support.size() - 1);
                            ready.add(elem);
                        }
                        if (!support.isEmpty()) support.remove(support.size() - 1);
                    } else if (!support.isEmpty() &&
                            i.getPriority() <= support.get(support.size() - 1).getPriority()) {
                        while (!support.isEmpty() &&
                                i.getPriority() <= support.get(support.size() - 1).getPriority()) {
                            if (support.get(support.size() - 1).getOperator() == "(") break;
                            CalcModel elem = support.get(support.size() - 1);
                            support.remove(support.size() - 1);
                            ready.add(elem);
                        }
                        support.add(i);
                    } else {
                        support.add(i);
                    }
                }
            }
        }
        while (!support.isEmpty()) {
            CalcModel elem = support.get(support.size() - 1);
            support.remove(support.size() - 1);
            ready.add(elem);
        }
        return ready;
    }

    double calculateExpression(List<CalcModel> list) {
        List<Double> tmp_list = new ArrayList<>();
        for (CalcModel i : list) {
            if (i.priority == NUMBER) {
                tmp_list.add(i.value);
            } else if (isOperator(i.operator.charAt(0))) {
                calculateIsOperators(tmp_list, i.operator);
            } else if (isFunction(i.operator.charAt(0))) {
                calculateIsMathFunction(tmp_list, i.operator);
            }
        }
        double result = tmp_list.get(tmp_list.size() - 1);
        tmp_list.remove(tmp_list.size() - 1);
        return result;
    }

    void calculateIsOperators(List<Double> tmp_list, String oper) {
        double tmp_num, result;
        tmp_num = tmp_list.get(tmp_list.size() - 1);
        tmp_list.remove(tmp_list.size() - 1);
        if (Objects.equals(oper, "+")) {
            result = tmp_list.get(tmp_list.size() - 1) + tmp_num;
            tmp_list.remove(tmp_list.size() - 1);
            tmp_list.add(result);
        } else if (Objects.equals(oper, "-")) {
            result = tmp_list.get(tmp_list.size() - 1) - tmp_num;
            tmp_list.remove(tmp_list.size() - 1);
            tmp_list.add(result);
        } else if (Objects.equals(oper, "*")) {
            result = tmp_list.get(tmp_list.size() - 1) * tmp_num;
            tmp_list.remove(tmp_list.size() - 1);
            tmp_list.add(result);
        } else if (Objects.equals(oper, "/")) {
            result = tmp_list.get(tmp_list.size() - 1) / tmp_num;
            tmp_list.remove(tmp_list.size() - 1);
            tmp_list.add(result);
        } else if (Objects.equals(oper, "mod")) {
            result = tmp_list.get(tmp_list.size() - 1);
            tmp_list.remove(tmp_list.size() - 1);
            tmp_list.add((double) Math.floorMod((int) result, (int) tmp_num));
        } else if (Objects.equals(oper, "^")) {
            result = tmp_list.get(tmp_list.size() - 1);
            tmp_list.remove(tmp_list.size() - 1);
            tmp_list.add(Math.pow(result, tmp_num));
        }
    }

    void calculateIsMathFunction(List<Double> tmp_list, String oper) {
        double result;
        result = tmp_list.get(tmp_list.size() - 1);
        tmp_list.remove(tmp_list.size() - 1);
        if (Objects.equals(oper, "sin")) {
            tmp_list.add(Math.sin(result));
        } else if (Objects.equals(oper, "cos")) {
            tmp_list.add(Math.cos(result));
        } else if (Objects.equals(oper, "tan")) {
            tmp_list.add(Math.tan(result));
        } else if (Objects.equals(oper, "ln")) {
            tmp_list.add(Math.log(result));
        } else if (Objects.equals(oper, "log")) {
            tmp_list.add(Math.log10(result));
        } else if (Objects.equals(oper, "sqrt")) {
            tmp_list.add(Math.sqrt(result));
        } else if (Objects.equals(oper, "asin")) {
            tmp_list.add(Math.asin(result));
        } else if (Objects.equals(oper, "acos")) {
            tmp_list.add(Math.acos(result));
        } else if (Objects.equals(oper, "atan")) {
            tmp_list.add(Math.atan(result));
        }
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
            Files.delete(Paths.get("target/calcHistory.txt"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            ret = "fail";
        }
        return ret;
    }
}
