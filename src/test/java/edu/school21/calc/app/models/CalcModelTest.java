package edu.school21.calc.app.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class CalcModelTest {
    private double x;
    private double y;
    Stack<CalcModel> stack = new Stack<>();
    private final CalcModel calculator = new CalcModel(0.0,"", (short) 0);
    @BeforeEach
    void getRandomInput() {
        x = ThreadLocalRandom.current().nextDouble(Double.MIN_VALUE, Double.MAX_VALUE);
        y = ThreadLocalRandom.current().nextDouble(Double.MIN_VALUE, Double.MAX_VALUE);
        stack.add(new CalcModel(x, "", (short) 0));
    }

    @ParameterizedTest
    @ValueSource(strings = {"(45+2", "3453543*3)", "()"})
    void validationBadStr(String str) {
        assertFalse(calculator.validation(str));
    }

    @ParameterizedTest
    @ValueSource(strings = {"(45+2)"})
    void validationGoodStr(String str) {
        assertTrue(calculator.validation(str));
    }

    @ParameterizedTest
    @ValueSource(chars = {'q', 'r', 'y', 'x', 'z', 'k', 'j', '1', ' ', '\t'})
    void isOperator(char symbol) {
        assertFalse(calculator.isOperator(symbol));
        assertTrue(calculator.isOperator('/'));
    }

    @ParameterizedTest
    @ValueSource(chars = {'q', 'r', 'y', 'x', 'z', 'k', 'j', '1', ' ', '\t'})
    void isFunction(char symbol) {
        assertFalse(calculator.isFunction(symbol));
        assertTrue(calculator.isFunction('s'));
    }

    @RepeatedTest(10)
    void plus() {
        stack.add(new CalcModel(y, "", (short) 0));
        stack.add(new CalcModel(0, "+", (short) 1));
        Double checkResult = x + y;
        Double testResult = calculator.calculateExpression(stack);
        Assertions.assertEquals(checkResult, testResult);
    }

    @RepeatedTest(10)
    void minus() {
        stack.add(new CalcModel(y, "", (short) 0));
        stack.add(new CalcModel(0, "-", (short) 1));
        Double checkResult = x - y;
        Double testResult = calculator.calculateExpression(stack);
        Assertions.assertEquals(checkResult, testResult);
    }

    @RepeatedTest(10)
    void multiply() {
        stack.add(new CalcModel(y, "", (short) 0));
        stack.add(new CalcModel(0, "*", (short) 2));
        Double checkResult = x * y;
        Double testResult = calculator.calculateExpression(stack);
        Assertions.assertEquals(checkResult, testResult);
    }

    @RepeatedTest(10)
    void division() {
        stack.add(new CalcModel(y, "", (short) 0));
        stack.add(new CalcModel(0, "/", (short) 2));
        Double checkResult = x / y;
        Double testResult = calculator.calculateExpression(stack);
        Assertions.assertEquals(checkResult, testResult);
    }

    @RepeatedTest(10)
    void power() {
        stack.add(new CalcModel(y, "", (short) 0));
        stack.add(new CalcModel(0, "^", (short) 3));
        Double checkResult = Math.pow(x, y);
        Double testResult = calculator.calculateExpression(stack);
        Assertions.assertEquals(checkResult, testResult);
    }

    @RepeatedTest(10)
    void mod() {
        stack.add(new CalcModel(y, "", (short) 0));
        stack.add(new CalcModel(0, "mod", (short) 4));
        Double checkResult = (double) Math.floorMod((int)x, (int)y);
        Double testResult = calculator.calculateExpression(stack);
        Assertions.assertEquals(checkResult, testResult);
    }

    @RepeatedTest(10)
    void sin() {
        stack.add(new CalcModel(0, "sin", (short) 4));
        Double checkResult = Math.sin(x);
        Double testResult = calculator.calculateExpression(stack);
        Assertions.assertEquals(checkResult, testResult);
    }

    @RepeatedTest(10)
    void cos() {
        stack.add(new CalcModel(0, "cos", (short) 4));
        Double checkResult = Math.cos(x);
        Double testResult = calculator.calculateExpression(stack);
        Assertions.assertEquals(checkResult, testResult);
    }

    @RepeatedTest(10)
    void tan() {
        stack.add(new CalcModel(0, "tan", (short) 4));
        Double checkResult = Math.tan(x);
        Double testResult = calculator.calculateExpression(stack);
        Assertions.assertEquals(checkResult, testResult);
    }

    @RepeatedTest(10)
    void ln() {
        stack.add(new CalcModel(0, "ln", (short) 4));
        Double checkResult = Math.log(x);
        Double testResult = calculator.calculateExpression(stack);
        Assertions.assertEquals(checkResult, testResult);
    }

    @RepeatedTest(10)
    void log() {
        stack.add(new CalcModel(0, "log", (short) 4));
        Double checkResult = Math.log10(x);
        Double testResult = calculator.calculateExpression(stack);
        Assertions.assertEquals(checkResult, testResult);
    }

    @RepeatedTest(10)
    void sqrt() {
        stack.add(new CalcModel(0, "sqrt", (short) 4));
        Double checkResult = Math.sqrt(x);
        Double testResult = calculator.calculateExpression(stack);
        Assertions.assertEquals(checkResult, testResult);
    }

    @RepeatedTest(10)
    void asin() {
        stack.add(new CalcModel(0, "asin", (short) 4));
        Double checkResult = Math.asin(x);
        Double testResult = calculator.calculateExpression(stack);
        Assertions.assertEquals(checkResult, testResult);
    }

    @RepeatedTest(10)
    void acos() {
        stack.add(new CalcModel(0, "acos", (short) 4));
        Double checkResult = Math.acos(x);
        Double testResult = calculator.calculateExpression(stack);
        Assertions.assertEquals(checkResult, testResult);
    }

    @RepeatedTest(10)
    void atan() {
        stack.add(new CalcModel(0, "atan", (short) 4));
        Double checkResult = Math.atan(x);
        Double testResult = calculator.calculateExpression(stack);
        Assertions.assertEquals(checkResult, testResult);
    }
}