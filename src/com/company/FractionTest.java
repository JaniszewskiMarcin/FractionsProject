package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FractionTest {

    @Test
    void getCounter() {
        try{

            Fraction[] testFractions = {
                    new Fraction(1),
                    new Fraction(1,2),
                    new Fraction(0,2),
                    new Fraction(-1,2),
            };

            assertEquals(0, testFractions[0].getCounter(), "Counter 0 detect from whole number problem!");
            assertEquals(1, testFractions[1].getCounter(), "Counter 1 detect problem!");
            assertEquals(0, testFractions[2].getCounter(), "Counter 0 detect problem!");
            assertEquals(-1, testFractions[3].getCounter(), "Counter -1 detect problem!");

            Fraction zeroDenominator = new Fraction(2,0);

        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    void getDenominator() {
    }

    @Test
    void getWholeNumber() {
    }

    @Test
    void reduceFraction() {
    }

    @Test
    void decimalFractionConvert() {
    }

    @Test
    void roundDecimal() {
    }

    @Test
    void mathOperationsOnTwoFractions() {
    }

    @Test
    void power() {
    }

    @Test
    void sqrFraction() {
    }

    @Test
    void exceptionHandler() {
    }

    @Test
    void testToString() {
    }

    @Test
    void compareTo() {
    }
}