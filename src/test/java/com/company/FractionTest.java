package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FractionTest {

    @Test
    void reduceFraction() {
        try{
            Fraction[] testFractions = {
                    new Fraction(6),
                    new Fraction(-6),
                    new Fraction(36,6),
                    new Fraction(-36,6),
                    new Fraction(27, 60),
                    new Fraction(6,3, 4),
                    new Fraction(6,3, 4),
                    new Fraction(0,7),
                    new Fraction(4,5, 0),
                    new Fraction(3,7),
                    new Fraction(6.06d,2.02d),
            };

            assertEquals(6, testFractions[0].reduceFraction(true).getWholeNumber(), "Root element from whole number problem!");
            assertEquals(6, testFractions[0].reduceFraction(false).getWholeNumber(), "Root element from whole number problem!");

            assertEquals(-6, testFractions[1].reduceFraction(true).getWholeNumber(), "Root element from whole number problem!");

            assertArrayEquals(new double[] {6, 0, 0}, new double[] {testFractions[2].reduceFraction(false).getWholeNumber(), testFractions[2].reduceFraction(false).getCounter(), testFractions[2].reduceFraction(false).getDenominator()}, "Simple fraction and whole number positive values power problem!");
            assertArrayEquals(new double[] {0, 6, 1}, new double[] {testFractions[2].reduceFraction(true).getWholeNumber(), testFractions[2].reduceFraction(true).getCounter(), testFractions[2].reduceFraction(true).getDenominator()}, "Simple fraction and whole number positive values power problem!");

        } catch(Exception e){
            System.out.println(e.getMessage());
        }
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
        try{
            Fraction[] testFractions = {
                    new Fraction(6),
                    new Fraction(5,7),
                    new Fraction(4,5, 3),
                    new Fraction(-6),
                    new Fraction(-5,7),
                    new Fraction(4,-5, 3),
                    new Fraction(0,7),
                    new Fraction(4,5, 0),
            };

            assertEquals(36, testFractions[0].power(2, 4).getWholeNumber(), "Whole number positive values power problem!");

            assertArrayEquals(new double[] {25, 49}, new double[] {testFractions[1].power(2, 4).getCounter(), testFractions[1].power(2, 4).getDenominator()}, "Simple fraction positive values power problem!");
            assertArrayEquals(new double[] {361, 25}, new double[] {testFractions[2].power(2, 4).getCounter(), testFractions[2].power(2, 4).getDenominator()}, "Simple fraction and whole number positive values power problem!");

            assertEquals(36, testFractions[3].power(2,4).getWholeNumber(), "Whole number negative values power problem!");
            assertEquals(-216, testFractions[3].power(3,4).getWholeNumber(), "Whole number negative values power problem!");

            assertArrayEquals(new double[] {25, 49}, new double[] {testFractions[4].power(2,4).getCounter(), testFractions[4].power(2,4).getDenominator()}, "Simple fraction negative values power problem!");
            assertArrayEquals(new double[] {121, 25}, new double[] {testFractions[5].power(2,4).getCounter(), testFractions[5].power(2,4).getDenominator()}, "Simple fraction and whole number negative values power problem!");

            assertArrayEquals(new double[] {0, 49}, new double[] {testFractions[6].power(2,4).getCounter(), testFractions[6].power(2,4).getDenominator()}, "Simple fraction with 0 value in counter power problem!");
            assertArrayEquals(new double[] {16, 25, 0}, new double[] {testFractions[7].power(2,4).getCounter(), testFractions[7].power(2,4).getDenominator(), testFractions[7].power(2, 4).getWholeNumber()}, "Simple fraction and value of whole number equals 0 values power problem!");

            assertEquals(0.027d, testFractions[0].power(-2,3).getWholeNumber(), "Whole number positive values power problem!");

            assertArrayEquals(new double[] {0.04d, 0.02d}, new double[] {testFractions[1].power(-2,2).getCounter(), testFractions[1].power(-2,2).getDenominator()}, "Simple fraction positive values power problem!");
            assertArrayEquals(new double[] {0.0027d, 0.04d}, new double[] {testFractions[2].power(-2,4).getCounter(), testFractions[2].power(-2,4).getDenominator()}, "Simple fraction and whole number positive values power problem!");

            assertEquals(1, testFractions[0].power(0, 4).getWholeNumber(), "Whole number positive values power problem!");

            assertArrayEquals(new double[] {1, 1}, new double[] {testFractions[1].power(0, 4).getCounter(), testFractions[1].power(2, 4).getDenominator()}, "Simple fraction positive values power problem!");
            assertArrayEquals(new double[] {1, 1}, new double[] {testFractions[2].power(0, 4).getCounter(), testFractions[2].power(2, 4).getDenominator()}, "Simple fraction and whole number positive values power problem!");

        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    void sqrFraction() {
        try{
            Fraction[] testFractions = {
                    new Fraction(2),
                    new Fraction(4,7),
                    new Fraction(1,2, 2),
                    new Fraction(-2),
                    new Fraction(-4,7),
                    new Fraction(1,-2, 2)
            };

            assertEquals(1.4142d, testFractions[0].sqrFraction(2, 4), "Root element from whole number problem!");
            assertEquals(0.7559d, testFractions[1].sqrFraction(2, 4), "Root element from simple fraction problem!");
            assertEquals(1.5811d, testFractions[2].sqrFraction(2, 4), "Root element from whole number and simple fraction problem!");

            assertThrows(FractionException.class, () -> testFractions[3].sqrFraction(2, 4),"Throwing FractionException in negative whole number root element problem!");
            assertThrows(FractionException.class, () -> testFractions[4].sqrFraction(2, 4), "Throwing FractionException in negative simple fraction root element problem!");
            assertThrows(FractionException.class, () -> testFractions[5].sqrFraction(2, 4), "Throwing FractionException in negative whole number and simple fraction root element problem!");

            assertThrows(FractionException.class, () -> testFractions[0].sqrFraction(-2, 4),"Throwing FractionException when wrong sqrDegree argument problem!");

            assertThrows(FractionException.class, () -> testFractions[0].sqrFraction(2, -4), "Throwing FractionException when wrong howManyDecimals argument problem!");

        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    void testToString() {
        try{
            Fraction[] testFractions = {
                    new Fraction(1),
                    new Fraction(1,2),
                    new Fraction(1,4, 3),
            };

            assertEquals("1.0", testFractions[0].toString(), "Whole number to string problem!");
            assertEquals("1.0/2.0", testFractions[1].toString(), "Simple fraction to string problem!");
            assertEquals("3.0 1.0/4.0", testFractions[2].toString(), "Whole number with simple fraction to string problem!");

        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    void compareTo() {
        try{
            Fraction[] testFractions = {
                    new Fraction(1),
                    new Fraction(2),
                    new Fraction(-2),
                    new Fraction(1,2),
                    new Fraction(4,6),
                    new Fraction(-1,2),
                    new Fraction(1,2, 2),
                    new Fraction(5,4, 6),
                    new Fraction(-1,2, 2)
            };

            assertEquals(0, testFractions[0].compareTo(testFractions[0]), "Whole numbers equal test problem!");
            assertEquals(1, testFractions[1].compareTo(testFractions[2]), "Whole numbers greater test problem!");
            assertEquals(-1, testFractions[2].compareTo(testFractions[1]), "Whole numbers less test problem!");

            assertEquals(0, testFractions[3].compareTo(testFractions[3]), "Simple fractions equal test problem!");
            assertEquals(1, testFractions[4].compareTo(testFractions[5]), "Simple fractions greater test problem!");
            assertEquals(-1, testFractions[5].compareTo(testFractions[4]), "Simple fractions less test problem!");

            assertEquals(0, testFractions[6].compareTo(testFractions[6]), "Whole numbers and simple fractions equal test problem!");
            assertEquals(1, testFractions[7].compareTo(testFractions[8]), "Whole numbers and simple fractions greater test problem!");
            assertEquals(-1, testFractions[8].compareTo(testFractions[7]), "Whole numbers and simple fractions less test problem!");

        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    void getCounter() {
        try{
            Fraction[] testFractions = {
                    new Fraction(1),
                    new Fraction(1,2),
                    new Fraction(0,2),
                    new Fraction(-1,2),
            };

            assertEquals(0, testFractions[0].getCounter(), "Get Counter 0 detect from whole number problem!");
            assertEquals(1, testFractions[1].getCounter(), "Get Counter 1 detect problem!");
            assertEquals(0, testFractions[2].getCounter(), "Get Counter 0 detect problem!");
            assertEquals(-1, testFractions[3].getCounter(), "Get Counter -1 detect problem!");

        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    void getDenominator() {
        try{
            Fraction[] testFractions = {
                    new Fraction(1),
                    new Fraction(1,2),
                    new Fraction(1,4, 3),
            };

            assertEquals(0, testFractions[0].getDenominator(), "Get Denominator from whole number test problem!");
            assertEquals(2, testFractions[1].getDenominator(), "Get Denominator from simple fraction test problem!");
            assertEquals(4, testFractions[2].getDenominator(), "Get Denominator from whole number and simple fraction test problem!");

        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    void getWholeNumber() {
        try{
            Fraction[] testFractions = {
                    new Fraction(1),
                    new Fraction(1,2),
                    new Fraction(1,4, 3),
            };

            assertEquals(1, testFractions[0].getWholeNumber(), "Get Whole number from whole number test problem!");
            assertEquals(0, testFractions[1].getWholeNumber(), "Get Whole number from simple fraction test problem!");
            assertEquals(3, testFractions[2].getWholeNumber(), "Get Whole number from whole number and simple fraction test problem!");

        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}