package edu.school21.calc.app.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import java.util.concurrent.ThreadLocalRandom;

class FunctionModelTest {

    private double x;
    FunctionModel functionModel = new FunctionModel("sin(x)");
    @BeforeEach
    void getRandomInput() {
        x = ThreadLocalRandom.current().nextDouble(Double.MIN_VALUE, Double.MAX_VALUE);
    }

    @RepeatedTest(10)
     void alg() {
        double checkResult = Math.sin(x);
        double testResult = functionModel.alg(x);
        Assertions.assertEquals(checkResult, testResult);
    }
}