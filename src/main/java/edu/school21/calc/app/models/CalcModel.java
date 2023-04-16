package edu.school21.calc.app.models;

import org.apache.log4j.Logger;

import java.util.Stack;
import java.util.Objects;

import static java.lang.Character.isDigit;

public class CalcModel {

    final static Logger logger = Logger.getLogger(CalcModel.class);
    short NUMBER = 0;
    short PLUS_MINUS = 1;
    short MUL_DIV_MOD = 2;
    short POWER = 3;
    short MATH_OPERATOR = 4;
    short BRACKETS = 5;

    private final short priority;
    private final String operator;

    private double value;

    public CalcModel(double value, String operator, short priority) {
        this.value = value;
        this.operator = operator;
        this.priority = priority;
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

    public void setValue(double value) {
        this.value = value;
    }

    public double doCalculation(String in) {
        double result;
        Stack<CalcModel> stack = new Stack<>();
        replace(in);
        if(!validation(in))
            throw new RuntimeException();
        mainParser(in, stack);
        stack = polishNotation(stack);
        result = calculateExpression(stack);
        logger.info(in + " = " + result);
        return(result);
    }

    void operatorParser(char symbol, Stack<CalcModel> stack) {
        if (symbol == '+') {
            CalcModel lexeme = new CalcModel(0, "+", PLUS_MINUS);
            stack.add(lexeme);
        } else if (symbol == '-') {
            CalcModel lexeme = new CalcModel(0, "-", PLUS_MINUS);
            stack.add(lexeme);
        } else if (symbol == '*') {
            CalcModel lexeme = new CalcModel(0, "*", MUL_DIV_MOD);
            stack.add(lexeme);
        } else if (symbol == '/') {
            CalcModel lexeme = new CalcModel(0, "/", MUL_DIV_MOD);
            stack.add(lexeme);
        } else if (symbol == 'm') {
            CalcModel lexeme = new CalcModel(0, "mod", MUL_DIV_MOD);
            stack.add(lexeme);
        } else if (symbol == '^') {
            CalcModel lexeme = new CalcModel(0, "^", POWER);
            stack.add(lexeme);
        }
    }

    void mathFuncParser(char i, char next, Stack<CalcModel> stack) {
        if (i == 's') {
            if (next == 'i') {
                CalcModel lexeme = new CalcModel(0, "sin", MATH_OPERATOR);
                stack.add(lexeme);
            } else if (next == 'q') {
                CalcModel lexeme = new CalcModel(0, "sqrt", MATH_OPERATOR);
                stack.add(lexeme);
            }
        } else if (i == 'a') {
            if (next == 's') {
                CalcModel lexeme = new CalcModel(0, "asin", MATH_OPERATOR);
                stack.add(lexeme);
            } else if (next == 'c') {
                CalcModel lexeme = new CalcModel(0, "acos", MATH_OPERATOR);
                stack.add(lexeme);
            } else if (next == 't') {
                CalcModel lexeme = new CalcModel(0, "atan", MATH_OPERATOR);
                stack.add(lexeme);
            }
        } else if (i == 'l') {
            if (next == 'o') {
                CalcModel lexeme = new CalcModel(0, "log", MATH_OPERATOR);
                stack.add(lexeme);
            } else if (next == 'n') {
                CalcModel lexeme = new CalcModel(0, "ln", MATH_OPERATOR);
                stack.add(lexeme);
            }
        } else if (i == 't') {
            CalcModel lexeme = new CalcModel(0, "tan", MATH_OPERATOR);
            stack.add(lexeme);
        } else if (i == 'c') {
            CalcModel lexeme = new CalcModel(0, "cos", MATH_OPERATOR);
            stack.add(lexeme);
        }
    }
    
    private void mainParser(String in, Stack<CalcModel> stack) {
        for (int i = 0; i < in.length(); i++) {
            if (isDigit(in.charAt(i)) || in.charAt(i) == '.') {
                StringBuilder num = new StringBuilder();
                while (isDigit(in.charAt(i)) || in.charAt(i) == '.'){
                    num.append(in.charAt(i));
                    i++;
                    if (i == in.length())
                        break;
                }
                i--;
                CalcModel num_lex = new CalcModel(Double.parseDouble(num.toString()), "", NUMBER);
                stack.add(num_lex);
            }
            if (isOperator(in.charAt(i))) {
                operatorParser(in.charAt(i), stack);
            } else if (isFunction(in.charAt(i))) {
                mathFuncParser(in.charAt(i), in.charAt(i + 1), stack);
                if (in.charAt(i) == 'a' && in.charAt(i + 1) != 'n') i += 3;
                if (in.charAt(i + 1) == 'q') i += 3;
            } else if (in.charAt(i) == '(') {
                CalcModel lexeme = new CalcModel(0, "(", BRACKETS);
                stack.add(lexeme);
            } else if (in.charAt(i) == ')') {
                CalcModel lexeme = new CalcModel(0, ")", BRACKETS);
                stack.add(lexeme);
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
        for (int i = 0; i < str.length(); i++) {
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
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '.') {
                if (i == 0) {
                    if (!isDigit(str.charAt(i + 1))) {
                        result = false;
                        break;
                    }
                } else {
                    if (!isDigit(str.charAt(i + 1)) || !isDigit(str.charAt(i - 1))) {
                        result = false;
                        break;
                    }
                }
                result = false;
            } else if (str.charAt(i) == '(' || str.charAt(i) == ')') {
                if (i != 0 && str.charAt(i) != str.charAt(str.length() - 1)) {
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
        str = str.replace("e+", "*10^");
        str = str.replace("e-", "*10^");
        str = str.replace("(+", "(0+");
        str = str.replace("(-", "(0-");
    }

    Stack<CalcModel> polishNotation(Stack<CalcModel> stack) {
        Stack<CalcModel> ready = new Stack<>();
        Stack<CalcModel> support = new Stack<>();
        for (int j = 0; j < stack.size(); j++) {
            if (stack.get(j).getOperator().equals("-") && stack.get(j + 1).getPriority() == NUMBER) {
                double x = stack.get(j + 1).getValue();
                stack.get(j + 1).setValue(0 - x);
                stack.remove(j);
            }
        }
        for (CalcModel i : stack) {
            if (i.getPriority() == NUMBER) {
                ready.add(i);
            } else {
                if (Objects.equals(i.getOperator(), "(") &&
                        i.getPriority() ==
                                BRACKETS) {
                    support.add(i);
                } else {
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
                            if (Objects.equals(support.get(support.size() - 1).getOperator(), "(")) break;
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

    double calculateExpression(Stack<CalcModel> stack) {
        Stack<Double> tmp_list = new Stack<>();
        for (CalcModel i : stack) {
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

    void calculateIsOperators(Stack<Double> tmp_stack, String operator) {
        double tmp_num, result;
        tmp_num = tmp_stack.get(tmp_stack.size() - 1);
        tmp_stack.remove(tmp_stack.size() - 1);
        if (Objects.equals(operator, "+")) {
            result = tmp_stack.get(tmp_stack.size() - 1) + tmp_num;
            tmp_stack.remove(tmp_stack.size() - 1);
            tmp_stack.add(result);
        } else if (Objects.equals(operator, "-")) {
            result = tmp_stack.get(tmp_stack.size() - 1) - tmp_num;
            tmp_stack.remove(tmp_stack.size() - 1);
            tmp_stack.add(result);
        } else if (Objects.equals(operator, "*")) {
            result = tmp_stack.get(tmp_stack.size() - 1) * tmp_num;
            tmp_stack.remove(tmp_stack.size() - 1);
            tmp_stack.add(result);
        } else if (Objects.equals(operator, "/")) {
            result = tmp_stack.get(tmp_stack.size() - 1) / tmp_num;
            tmp_stack.remove(tmp_stack.size() - 1);
            tmp_stack.add(result);
        } else if (Objects.equals(operator, "mod")) {
            result = tmp_stack.get(tmp_stack.size() - 1);
            tmp_stack.remove(tmp_stack.size() - 1);
            tmp_stack.add((double) Math.floorMod((int) result, (int) tmp_num));
        } else if (Objects.equals(operator, "^")) {
            result = tmp_stack.get(tmp_stack.size() - 1);
            tmp_stack.remove(tmp_stack.size() - 1);
            tmp_stack.add(Math.pow(result, tmp_num));
        }
    }

    void calculateIsMathFunction(Stack<Double> tmp_stack, String operator) {
        double result;
        result = tmp_stack.get(tmp_stack.size() - 1);
        tmp_stack.remove(tmp_stack.size() - 1);
        if (Objects.equals(operator, "sin")) {
            tmp_stack.add(Math.sin(result));
        } else if (Objects.equals(operator, "cos")) {
            tmp_stack.add(Math.cos(result));
        } else if (Objects.equals(operator, "tan")) {
            tmp_stack.add(Math.tan(result));
        } else if (Objects.equals(operator, "ln")) {
            tmp_stack.add(Math.log(result));
        } else if (Objects.equals(operator, "log")) {
            tmp_stack.add(Math.log10(result));
        } else if (Objects.equals(operator, "sqrt")) {
            tmp_stack.add(Math.sqrt(result));
        } else if (Objects.equals(operator, "asin")) {
            tmp_stack.add(Math.asin(result));
        } else if (Objects.equals(operator, "acos")) {
            tmp_stack.add(Math.acos(result));
        } else if (Objects.equals(operator, "atan")) {
            tmp_stack.add(Math.atan(result));
        }
    }
}
