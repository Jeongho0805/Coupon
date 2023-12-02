package com.coupon_project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import java.util.function.IntBinaryOperator;

@SpringBootTest
public class LamdaPracticeTest {

    @Autowired
    LamdaPractice lamdaPractice;

    @Test
    void lamdaTest1() {
        IntBinaryOperator operator = Integer::sum;
        lamdaPractice.callMethod(operator, 1, 5 );
    }

    @TestConfiguration
    static class LamdaPracticeTestConfig {

        @Bean
        LamdaPractice lamdaPractice() {
            return new LamdaPractice();
        }
    }

    static class LamdaPractice {

        public void callMethod(IntBinaryOperator method, int arg1, int arg2) {
            int result = method.applyAsInt(arg1, arg2);
            System.out.println("result = " + result);
        }
    }
}
