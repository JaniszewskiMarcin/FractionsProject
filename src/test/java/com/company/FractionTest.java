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
                    new Fraction(6,3, -4),
                    new Fraction(0,7),
                    new Fraction(6,4, 0),
                    new Fraction(3,7),
                    new Fraction(6.06d,2.02d),
            };

            //Reducing from whole number positive value fraction to simple fraction and complex fraction
            assertEquals(6, testFractions[0].reduceFraction(true).getWholeNumber());
            assertEquals(6, testFractions[0].reduceFraction(false).getWholeNumber());

            //Reducing from whole number negative value fraction to simple fraction
            assertEquals(-6, testFractions[1].reduceFraction(true).getWholeNumber());

            //Reducing from simple fraction with positive values to whole number and to simple fraction
            assertArrayEquals(new double[] {6, 0, 0}, new double[] {testFractions[2].reduceFraction(false).getWholeNumber(), testFractions[2].reduceFraction(false).getCounter(), testFractions[2].reduceFraction(false).getDenominator()});
            assertArrayEquals(new double[] {0, 6, 1}, new double[] {testFractions[2].reduceFraction(true).getWholeNumber(), testFractions[2].reduceFraction(true).getCounter(), testFractions[2].reduceFraction(true).getDenominator()});

            //Reducing from simple fraction with negative values to whole number and to simple fraction
            assertArrayEquals(new double[] {-6, 0, 0}, new double[] {testFractions[3].reduceFraction(false).getWholeNumber(), testFractions[3].reduceFraction(false).getCounter(), testFractions[2].reduceFraction(false).getDenominator()});
            assertArrayEquals(new double[] {0, -6, 1}, new double[] {testFractions[3].reduceFraction(true).getWholeNumber(), testFractions[3].reduceFraction(true).getCounter(), testFractions[2].reduceFraction(true).getDenominator()});

            assertArrayEquals(new double[] {0, 9, 20}, new double[] {testFractions[4].reduceFraction(false).getWholeNumber(), testFractions[4].reduceFraction(false).getCounter(), testFractions[4].reduceFraction(false).getDenominator()});
            assertArrayEquals(new double[] {0, 9, 20}, new double[] {testFractions[4].reduceFraction(true).getWholeNumber(), testFractions[4].reduceFraction(true).getCounter(), testFractions[4].reduceFraction(true).getDenominator()});

            assertArrayEquals(new double[] {6, 0, 0}, new double[] {testFractions[5].reduceFraction(false).getWholeNumber(), testFractions[5].reduceFraction(false).getCounter(), testFractions[5].reduceFraction(false).getDenominator()});
            assertArrayEquals(new double[] {0, 6, 1}, new double[] {testFractions[5].reduceFraction(true).getWholeNumber(), testFractions[5].reduceFraction(true).getCounter(), testFractions[5].reduceFraction(true).getDenominator()});

            assertArrayEquals(new double[] {-2, 0, 0}, new double[] {testFractions[6].reduceFraction(false).getWholeNumber(), testFractions[6].reduceFraction(false).getCounter(), testFractions[6].reduceFraction(false).getDenominator()});
            assertArrayEquals(new double[] {0, -2, 1}, new double[] {testFractions[6].reduceFraction(true).getWholeNumber(), testFractions[6].reduceFraction(true).getCounter(), testFractions[6].reduceFraction(true).getDenominator()});

            //Reducing fraction with 0 values
            assertArrayEquals(new double[] {0, 0, 0}, new double[] {testFractions[7].reduceFraction(false).getWholeNumber(), testFractions[7].reduceFraction(false).getCounter(), testFractions[7].reduceFraction(false).getDenominator()});
            assertArrayEquals(new double[] {0, 0, 0}, new double[] {testFractions[7].reduceFraction(true).getWholeNumber(), testFractions[7].reduceFraction(true).getCounter(), testFractions[7].reduceFraction(true).getDenominator()});

            assertArrayEquals(new double[] {1, 1, 2}, new double[] {testFractions[8].reduceFraction(false).getWholeNumber(), testFractions[8].reduceFraction(false).getCounter(), testFractions[8].reduceFraction(false).getDenominator()});
            assertArrayEquals(new double[] {0, 3, 2}, new double[] {testFractions[8].reduceFraction(true).getWholeNumber(), testFractions[8].reduceFraction(true).getCounter(), testFractions[8].reduceFraction(true).getDenominator()});

            //Reducing simple fraction that can not be reduced more
            assertArrayEquals(new double[] {0, 3, 7}, new double[] {testFractions[9].reduceFraction(false).getWholeNumber(), testFractions[9].reduceFraction(false).getCounter(), testFractions[9].reduceFraction(false).getDenominator()});
            assertArrayEquals(new double[] {0, 3, 7}, new double[] {testFractions[9].reduceFraction(true).getWholeNumber(), testFractions[9].reduceFraction(true).getCounter(), testFractions[9].reduceFraction(true).getDenominator()});

            //Checking for exception with double values are provided which is forbidden in case of reducing fraction
            assertThrows(FractionException.class, () -> testFractions[10].reduceFraction(false));
            assertThrows(FractionException.class, () -> testFractions[10].reduceFraction(true));

        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    void decimalToFractionConvert(){
        try{
            double[] testDouble = {
                    1d,
                    0.5d,
                    3.25d,
                    -1d,
                    -0.5d,
                    -2.75d
            };

            //Converting positive decimal value to a simple fraction
            assertArrayEquals(new double[] {1, 1}, new double[] {Fraction.decimalToFractionConvert(testDouble[0]).getCounter(), Fraction.decimalToFractionConvert(testDouble[0]).getDenominator()});
            assertArrayEquals(new double[] {1, 2}, new double[] {Fraction.decimalToFractionConvert(testDouble[1]).getCounter(), Fraction.decimalToFractionConvert(testDouble[1]).getDenominator()});
            assertArrayEquals(new double[] {13, 4}, new double[] {Fraction.decimalToFractionConvert(testDouble[2]).getCounter(), Fraction.decimalToFractionConvert(testDouble[2]).getDenominator()});

            //Converting negative decimal value to a simple fraction
            assertArrayEquals(new double[] {-1, 1}, new double[] {Fraction.decimalToFractionConvert(testDouble[3]).getCounter(), Fraction.decimalToFractionConvert(testDouble[3]).getDenominator()});
            assertArrayEquals(new double[] {-1, 2}, new double[] {Fraction.decimalToFractionConvert(testDouble[4]).getCounter(), Fraction.decimalToFractionConvert(testDouble[4]).getDenominator()});
            assertArrayEquals(new double[] {-11, 4}, new double[] {Fraction.decimalToFractionConvert(testDouble[5]).getCounter(), Fraction.decimalToFractionConvert(testDouble[5]).getDenominator()});

        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    void fractionToDecimalConvert() {
        try{
            Fraction[] testFractions = {
                    new Fraction(1),
                    new Fraction(1,2),
                    new Fraction(1,4, 3),
                    new Fraction(-1),
                    new Fraction(-1,2),
                    new Fraction(1,4, -3),
                    new Fraction(1.48d),
                    new Fraction(1.67d,2.98d),
                    new Fraction(1.34d,4.67d, 3),
                    new Fraction(0),
                    new Fraction(0,2),
                    new Fraction(1,4, 0),
            };

            //Changing fractions to a decimal number, all positive values fraction types
            assertEquals(1, testFractions[0].fractionToDecimalConvert(2));
            assertEquals(0.5d, testFractions[1].fractionToDecimalConvert(2));
            assertEquals(3.25d, testFractions[2].fractionToDecimalConvert(2));

            //Changing fractions to a decimal number, all negative values fraction types
            assertEquals(-1, testFractions[3].fractionToDecimalConvert(2));
            assertEquals(-0.5d, testFractions[4].fractionToDecimalConvert(2));
            assertEquals(-2.75d, testFractions[5].fractionToDecimalConvert(2));

            //Changing fractions to a decimal number, all positive double values fraction types
            assertEquals(1.48d, testFractions[6].fractionToDecimalConvert(2));
            assertEquals(0.56d, testFractions[7].fractionToDecimalConvert(2));
            assertEquals(3.28d, testFractions[8].fractionToDecimalConvert(2));

            //Changing fractions to a decimal number with fractions that contains 0
            assertEquals(0, testFractions[9].fractionToDecimalConvert(2));
            assertEquals(0, testFractions[10].fractionToDecimalConvert(2));
            assertEquals(0.25d, testFractions[11].fractionToDecimalConvert(2));

        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    void roundDecimal() {
        try{
            double[] doubleTest = {
                    1.5195483d,
                    0.98374d,
                    0,
                    -13453.4373d,
                    -0.93742d
            };

            double result = 123.23123d;

            //Rounding decimals and cutting decimals with higher positive value than 1
            assertEquals(1.51d, Fraction.roundDecimal(doubleTest[0], 2, true));
            assertEquals(1.52d, Fraction.roundDecimal(doubleTest[0], 2, false));

            //Rounding decimals and cutting decimals with lower positive value than 1
            assertEquals(0.9d, Fraction.roundDecimal(doubleTest[1], 1, true));
            assertEquals(1.0d, Fraction.roundDecimal(doubleTest[1], 1, false));

            //Rounding decimals and cutting decimals with 0 value
            assertEquals(0, Fraction.roundDecimal(doubleTest[2], 1, true));
            assertEquals(0, Fraction.roundDecimal(doubleTest[2], 1, false));

            //Rounding decimals and cutting decimals with higher negative value than 1
            assertEquals(-13453.43d, Fraction.roundDecimal(doubleTest[3], 2, true));
            assertEquals(-13453.44d, Fraction.roundDecimal(doubleTest[3], 2, false));

            //Rounding decimals and cutting decimals with lower negative value than 1
            assertEquals(-0.93d, Fraction.roundDecimal(doubleTest[4], 2, true));
            assertEquals(-0.94d, Fraction.roundDecimal(doubleTest[4], 2, false));

        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    void mathOperationsOnTwoFractions() {
        try{
            //----------------------------------------ADD OPERATION

            Fraction[] testFractionsAdd = {
                    new Fraction(1),
                    new Fraction(1,2),
                    new Fraction(1,4, 3),
                    new Fraction(-1,2),
                    new Fraction(1,-2),
                    new Fraction(1,4, -3),
                    new Fraction(1.98d,-2.53d),
                    new Fraction(0,2),
                    new Fraction(1,4, 0),
            };

            //  1+1
            assertEquals(2, testFractionsAdd[0].mathOperationsOnTwoFractions(Operations.ADD, testFractionsAdd[0]).getWholeNumber());
            //  1/2 + 1/2
            assertArrayEquals(new double[] {0, 1, 1}, new double[] {testFractionsAdd[1].mathOperationsOnTwoFractions(Operations.ADD, testFractionsAdd[1]).getWholeNumber(), testFractionsAdd[1].mathOperationsOnTwoFractions(Operations.ADD, testFractionsAdd[1]).getCounter(), testFractionsAdd[1].mathOperationsOnTwoFractions(Operations.ADD, testFractionsAdd[1]).getDenominator()});
            //  1 + 1/2
            assertArrayEquals(new double[] {0, 3, 2}, new double[] {testFractionsAdd[0].mathOperationsOnTwoFractions(Operations.ADD, testFractionsAdd[1]).getWholeNumber(), testFractionsAdd[0].mathOperationsOnTwoFractions(Operations.ADD, testFractionsAdd[1]).getCounter(), testFractionsAdd[0].mathOperationsOnTwoFractions(Operations.ADD, testFractionsAdd[1]).getDenominator()});
            // 1 + 3 1/4
            assertArrayEquals(new double[] {0, 17, 4}, new double[] {testFractionsAdd[0].mathOperationsOnTwoFractions(Operations.ADD, testFractionsAdd[2]).getWholeNumber(), testFractionsAdd[0].mathOperationsOnTwoFractions(Operations.ADD, testFractionsAdd[2]).getCounter(), testFractionsAdd[0].mathOperationsOnTwoFractions(Operations.ADD, testFractionsAdd[2]).getDenominator()});
            // 1/2 + 3 1/4
            assertArrayEquals(new double[] {0, 15, 4}, new double[] {testFractionsAdd[1].mathOperationsOnTwoFractions(Operations.ADD, testFractionsAdd[2]).getWholeNumber(), testFractionsAdd[1].mathOperationsOnTwoFractions(Operations.ADD, testFractionsAdd[2]).getCounter(), testFractionsAdd[1].mathOperationsOnTwoFractions(Operations.ADD, testFractionsAdd[2]).getDenominator()});
            // 3 1/4 + 3 1/4
            assertArrayEquals(new double[] {0, 13, 2}, new double[] {testFractionsAdd[2].mathOperationsOnTwoFractions(Operations.ADD, testFractionsAdd[2]).getWholeNumber(), testFractionsAdd[2].mathOperationsOnTwoFractions(Operations.ADD, testFractionsAdd[2]).getCounter(), testFractionsAdd[2].mathOperationsOnTwoFractions(Operations.ADD, testFractionsAdd[2]).getDenominator()});
            // 1 + (-1/2)
            assertArrayEquals(new double[] {0, 1, 2}, new double[] {testFractionsAdd[0].mathOperationsOnTwoFractions(Operations.ADD, testFractionsAdd[3]).getWholeNumber(), testFractionsAdd[0].mathOperationsOnTwoFractions(Operations.ADD, testFractionsAdd[3]).getCounter(), testFractionsAdd[0].mathOperationsOnTwoFractions(Operations.ADD, testFractionsAdd[3]).getDenominator()});
            // (-1/2) + (-1/2)
            assertArrayEquals(new double[] {0, -1, 1}, new double[] {testFractionsAdd[3].mathOperationsOnTwoFractions(Operations.ADD, testFractionsAdd[3]).getWholeNumber(), testFractionsAdd[3].mathOperationsOnTwoFractions(Operations.ADD, testFractionsAdd[3]).getCounter(), testFractionsAdd[3].mathOperationsOnTwoFractions(Operations.ADD, testFractionsAdd[3]).getDenominator()});
            // -3 1/4 + (-1/2)
            assertArrayEquals(new double[] {0, -13, 4}, new double[] {testFractionsAdd[5].mathOperationsOnTwoFractions(Operations.ADD, testFractionsAdd[3]).getWholeNumber(), testFractionsAdd[5].mathOperationsOnTwoFractions(Operations.ADD, testFractionsAdd[3]).getCounter(), testFractionsAdd[5].mathOperationsOnTwoFractions(Operations.ADD, testFractionsAdd[3]).getDenominator()});
            // -3 1/4 + (1/-2)
            assertArrayEquals(new double[] {0, -13, 4}, new double[] {testFractionsAdd[5].mathOperationsOnTwoFractions(Operations.ADD, testFractionsAdd[4]).getWholeNumber(), testFractionsAdd[5].mathOperationsOnTwoFractions(Operations.ADD, testFractionsAdd[4]).getCounter(), testFractionsAdd[5].mathOperationsOnTwoFractions(Operations.ADD, testFractionsAdd[4]).getDenominator()});
            // -3 1/4 + (1.98/-2.53)
            assertThrows(FractionException.class, () -> testFractionsAdd[5].mathOperationsOnTwoFractions(Operations.ADD, testFractionsAdd[6]));
            // (1.98/-2.53) + (1.98/-2.53)
            assertThrows(FractionException.class, () -> testFractionsAdd[6].mathOperationsOnTwoFractions(Operations.ADD, testFractionsAdd[6]));
            // 1/2 + (0/2)
            assertArrayEquals(new double[] {0, 1, 2}, new double[] {testFractionsAdd[1].mathOperationsOnTwoFractions(Operations.ADD, testFractionsAdd[7]).getWholeNumber(), testFractionsAdd[1].mathOperationsOnTwoFractions(Operations.ADD, testFractionsAdd[7]).getCounter(), testFractionsAdd[1].mathOperationsOnTwoFractions(Operations.ADD, testFractionsAdd[7]).getDenominator()});
            // 1/2 + 0 1/4
            assertArrayEquals(new double[] {0, 3, 4}, new double[] {testFractionsAdd[1].mathOperationsOnTwoFractions(Operations.ADD, testFractionsAdd[8]).getWholeNumber(), testFractionsAdd[1].mathOperationsOnTwoFractions(Operations.ADD, testFractionsAdd[8]).getCounter(), testFractionsAdd[1].mathOperationsOnTwoFractions(Operations.ADD, testFractionsAdd[8]).getDenominator()});

            //----------------------------------------SUBTRACK OPERATION

            Fraction[] testFractionsSubtrack = {
                    new Fraction(1),
                    new Fraction(1,2),
                    new Fraction(1,4, 3),
                    new Fraction(-1,2),
                    new Fraction(1,-2),
                    new Fraction(1,4, -3),
                    new Fraction(1.98d,-2.53d),
                    new Fraction(0,2),
                    new Fraction(1,4, 0),
            };


            //  1-1
            assertEquals(0, testFractionsSubtrack[0].mathOperationsOnTwoFractions(Operations.SUBTRACK, testFractionsSubtrack[0]).getWholeNumber());
            //  1/2 - 1/2
            assertArrayEquals(new double[] {0, 0, 0}, new double[] {testFractionsSubtrack[1].mathOperationsOnTwoFractions(Operations.SUBTRACK, testFractionsSubtrack[1]).getWholeNumber(), testFractionsSubtrack[1].mathOperationsOnTwoFractions(Operations.SUBTRACK, testFractionsSubtrack[1]).getCounter(), testFractionsSubtrack[1].mathOperationsOnTwoFractions(Operations.SUBTRACK, testFractionsSubtrack[1]).getDenominator()});
            //  1 - 1/2
            assertArrayEquals(new double[] {0, 1, 2}, new double[] {testFractionsSubtrack[0].mathOperationsOnTwoFractions(Operations.SUBTRACK, testFractionsSubtrack[1]).getWholeNumber(), testFractionsSubtrack[0].mathOperationsOnTwoFractions(Operations.SUBTRACK, testFractionsSubtrack[1]).getCounter(), testFractionsSubtrack[0].mathOperationsOnTwoFractions(Operations.SUBTRACK, testFractionsSubtrack[1]).getDenominator()});
            // 1 - 3 1/4
            assertArrayEquals(new double[] {0, -9, 4}, new double[] {testFractionsSubtrack[0].mathOperationsOnTwoFractions(Operations.SUBTRACK, testFractionsSubtrack[2]).getWholeNumber(), testFractionsSubtrack[0].mathOperationsOnTwoFractions(Operations.SUBTRACK, testFractionsSubtrack[2]).getCounter(), testFractionsSubtrack[0].mathOperationsOnTwoFractions(Operations.SUBTRACK, testFractionsSubtrack[2]).getDenominator()});
            // 1/2 - 3 1/4
            assertArrayEquals(new double[] {0, -11, 4}, new double[] {testFractionsSubtrack[1].mathOperationsOnTwoFractions(Operations.SUBTRACK, testFractionsSubtrack[2]).getWholeNumber(), testFractionsSubtrack[1].mathOperationsOnTwoFractions(Operations.SUBTRACK, testFractionsSubtrack[2]).getCounter(), testFractionsSubtrack[1].mathOperationsOnTwoFractions(Operations.SUBTRACK, testFractionsSubtrack[2]).getDenominator()});
            // 3 1/4 - 3 1/4
            assertArrayEquals(new double[] {0, 0, 0}, new double[] {testFractionsSubtrack[2].mathOperationsOnTwoFractions(Operations.SUBTRACK, testFractionsSubtrack[2]).getWholeNumber(), testFractionsSubtrack[2].mathOperationsOnTwoFractions(Operations.SUBTRACK, testFractionsSubtrack[2]).getCounter(), testFractionsSubtrack[2].mathOperationsOnTwoFractions(Operations.SUBTRACK, testFractionsSubtrack[2]).getDenominator()});
            // 1 - (-1/2)
            assertArrayEquals(new double[] {0, 3, 2}, new double[] {testFractionsSubtrack[0].mathOperationsOnTwoFractions(Operations.SUBTRACK, testFractionsSubtrack[3]).getWholeNumber(), testFractionsSubtrack[0].mathOperationsOnTwoFractions(Operations.SUBTRACK, testFractionsSubtrack[3]).getCounter(), testFractionsSubtrack[0].mathOperationsOnTwoFractions(Operations.SUBTRACK, testFractionsSubtrack[3]).getDenominator()});
            // (-1/2) - (-1/2)
            assertArrayEquals(new double[] {0, 0, 0}, new double[] {testFractionsSubtrack[3].mathOperationsOnTwoFractions(Operations.SUBTRACK, testFractionsSubtrack[3]).getWholeNumber(), testFractionsSubtrack[3].mathOperationsOnTwoFractions(Operations.SUBTRACK, testFractionsSubtrack[3]).getCounter(), testFractionsSubtrack[3].mathOperationsOnTwoFractions(Operations.SUBTRACK, testFractionsSubtrack[3]).getDenominator()});
            // -3 1/4 - (-1/2)
            assertArrayEquals(new double[] {0, -9, 4}, new double[] {testFractionsSubtrack[5].mathOperationsOnTwoFractions(Operations.SUBTRACK, testFractionsSubtrack[3]).getWholeNumber(), testFractionsSubtrack[5].mathOperationsOnTwoFractions(Operations.SUBTRACK, testFractionsSubtrack[3]).getCounter(), testFractionsSubtrack[5].mathOperationsOnTwoFractions(Operations.SUBTRACK, testFractionsSubtrack[3]).getDenominator()});
            // -3 1/4 - (1/-2)
            assertArrayEquals(new double[] {0, -9, 4}, new double[] {testFractionsSubtrack[5].mathOperationsOnTwoFractions(Operations.SUBTRACK, testFractionsSubtrack[4]).getWholeNumber(), testFractionsSubtrack[5].mathOperationsOnTwoFractions(Operations.SUBTRACK, testFractionsSubtrack[4]).getCounter(), testFractionsSubtrack[5].mathOperationsOnTwoFractions(Operations.SUBTRACK, testFractionsSubtrack[4]).getDenominator()});
            // -3 1/4 - (1.98/-2.53)
            assertThrows(FractionException.class, () -> testFractionsSubtrack[5].mathOperationsOnTwoFractions(Operations.SUBTRACK, testFractionsSubtrack[6]));
            // (1.98/-2.53) - (1.98/-2.53)
            assertThrows(FractionException.class, () -> testFractionsSubtrack[6].mathOperationsOnTwoFractions(Operations.SUBTRACK, testFractionsSubtrack[6]));
            // 1/2 - (0/2)
            assertArrayEquals(new double[] {0, 1, 2}, new double[] {testFractionsSubtrack[1].mathOperationsOnTwoFractions(Operations.SUBTRACK, testFractionsSubtrack[7]).getWholeNumber(), testFractionsSubtrack[1].mathOperationsOnTwoFractions(Operations.SUBTRACK, testFractionsSubtrack[7]).getCounter(), testFractionsSubtrack[1].mathOperationsOnTwoFractions(Operations.SUBTRACK, testFractionsSubtrack[7]).getDenominator()});
            // 1/2 - 0 1/4
            assertArrayEquals(new double[] {0, 1, 4}, new double[] {testFractionsSubtrack[1].mathOperationsOnTwoFractions(Operations.SUBTRACK, testFractionsSubtrack[8]).getWholeNumber(), testFractionsSubtrack[1].mathOperationsOnTwoFractions(Operations.SUBTRACK, testFractionsSubtrack[8]).getCounter(), testFractionsSubtrack[1].mathOperationsOnTwoFractions(Operations.SUBTRACK, testFractionsSubtrack[8]).getDenominator()});

            //----------------------------------------MULTIPLY OPERATION

            Fraction[] testFractionsMultiply = {
                    new Fraction(1),
                    new Fraction(1,2),
                    new Fraction(1,4, 3),
                    new Fraction(-1,2),
                    new Fraction(1,-2),
                    new Fraction(1,4, -3),
                    new Fraction(1.98d,-2.53d),
                    new Fraction(0,2),
                    new Fraction(1,4, 0),
            };

            //  1*1
            assertArrayEquals(new double[] {1}, new double[] {testFractionsMultiply[0].mathOperationsOnTwoFractions(Operations.MULTIPLY, testFractionsMultiply[0]).getWholeNumber()});
            //  1/2 * 1/2
            assertArrayEquals(new double[] {0, 1, 4}, new double[] {testFractionsMultiply[1].mathOperationsOnTwoFractions(Operations.MULTIPLY, testFractionsMultiply[1]).getWholeNumber(), testFractionsMultiply[1].mathOperationsOnTwoFractions(Operations.MULTIPLY, testFractionsMultiply[1]).getCounter(), testFractionsMultiply[1].mathOperationsOnTwoFractions(Operations.MULTIPLY, testFractionsMultiply[1]).getDenominator()});
            //  1 * 1/2
            assertArrayEquals(new double[] {0, 1, 2}, new double[] {testFractionsMultiply[0].mathOperationsOnTwoFractions(Operations.MULTIPLY, testFractionsMultiply[1]).getWholeNumber(), testFractionsMultiply[0].mathOperationsOnTwoFractions(Operations.MULTIPLY, testFractionsMultiply[1]).getCounter(), testFractionsMultiply[0].mathOperationsOnTwoFractions(Operations.MULTIPLY, testFractionsMultiply[1]).getDenominator()});
            // 1 * 3 1/4
            assertArrayEquals(new double[] {0, 13, 4}, new double[] {testFractionsMultiply[0].mathOperationsOnTwoFractions(Operations.MULTIPLY, testFractionsMultiply[2]).getWholeNumber(), testFractionsMultiply[0].mathOperationsOnTwoFractions(Operations.MULTIPLY, testFractionsMultiply[2]).getCounter(), testFractionsMultiply[0].mathOperationsOnTwoFractions(Operations.MULTIPLY, testFractionsMultiply[2]).getDenominator()});
            // 1/2 * 3 1/4
            assertArrayEquals(new double[] {0, 13, 8}, new double[] {testFractionsMultiply[1].mathOperationsOnTwoFractions(Operations.MULTIPLY, testFractionsMultiply[2]).getWholeNumber(), testFractionsMultiply[1].mathOperationsOnTwoFractions(Operations.MULTIPLY, testFractionsMultiply[2]).getCounter(), testFractionsMultiply[1].mathOperationsOnTwoFractions(Operations.MULTIPLY, testFractionsMultiply[2]).getDenominator()});
            // 3 1/4 * 3 1/4
            assertArrayEquals(new double[] {0, 169, 16}, new double[] {testFractionsMultiply[2].mathOperationsOnTwoFractions(Operations.MULTIPLY, testFractionsMultiply[2]).getWholeNumber(), testFractionsMultiply[2].mathOperationsOnTwoFractions(Operations.MULTIPLY, testFractionsMultiply[2]).getCounter(), testFractionsMultiply[2].mathOperationsOnTwoFractions(Operations.MULTIPLY, testFractionsMultiply[2]).getDenominator()});
            // 1 * (-1/2)
            assertArrayEquals(new double[] {0, -1, 2}, new double[] {testFractionsMultiply[0].mathOperationsOnTwoFractions(Operations.MULTIPLY, testFractionsMultiply[3]).getWholeNumber(), testFractionsMultiply[0].mathOperationsOnTwoFractions(Operations.MULTIPLY, testFractionsMultiply[3]).getCounter(), testFractionsMultiply[0].mathOperationsOnTwoFractions(Operations.MULTIPLY, testFractionsMultiply[3]).getDenominator()});
            // (-1/2) * (-1/2)
            assertArrayEquals(new double[] {0, 1, 4}, new double[] {testFractionsMultiply[3].mathOperationsOnTwoFractions(Operations.MULTIPLY, testFractionsMultiply[3]).getWholeNumber(), testFractionsMultiply[3].mathOperationsOnTwoFractions(Operations.MULTIPLY, testFractionsMultiply[3]).getCounter(), testFractionsMultiply[3].mathOperationsOnTwoFractions(Operations.MULTIPLY, testFractionsMultiply[3]).getDenominator()});
            // -3 1/4 * (-1/2)
            assertArrayEquals(new double[] {0, 11, 8}, new double[] {testFractionsMultiply[5].mathOperationsOnTwoFractions(Operations.MULTIPLY, testFractionsMultiply[3]).getWholeNumber(), testFractionsMultiply[5].mathOperationsOnTwoFractions(Operations.MULTIPLY, testFractionsMultiply[3]).getCounter(), testFractionsMultiply[5].mathOperationsOnTwoFractions(Operations.MULTIPLY, testFractionsMultiply[3]).getDenominator()});
            // -3 1/4 * (1/-2)
            assertArrayEquals(new double[] {0, 11, 8}, new double[] {testFractionsMultiply[5].mathOperationsOnTwoFractions(Operations.MULTIPLY, testFractionsMultiply[4]).getWholeNumber(), testFractionsMultiply[5].mathOperationsOnTwoFractions(Operations.MULTIPLY, testFractionsMultiply[4]).getCounter(), testFractionsMultiply[5].mathOperationsOnTwoFractions(Operations.MULTIPLY, testFractionsMultiply[4]).getDenominator()});
            // -3 1/4 * (1.98/-2.53)
            assertThrows(FractionException.class, () -> testFractionsMultiply[5].mathOperationsOnTwoFractions(Operations.MULTIPLY, testFractionsMultiply[6]));
            // (1.98/-2.53) * (1.98/-2.53)
            assertThrows(FractionException.class, () -> testFractionsMultiply[6].mathOperationsOnTwoFractions(Operations.MULTIPLY, testFractionsMultiply[6]));
            // 1/2 * (0/2)
            assertArrayEquals(new double[] {0, 0, 0}, new double[] {testFractionsMultiply[1].mathOperationsOnTwoFractions(Operations.MULTIPLY, testFractionsMultiply[7]).getWholeNumber(), testFractionsMultiply[1].mathOperationsOnTwoFractions(Operations.MULTIPLY, testFractionsMultiply[7]).getCounter(), testFractionsMultiply[1].mathOperationsOnTwoFractions(Operations.MULTIPLY, testFractionsMultiply[7]).getDenominator()});
            // 1/2 * 0 1/4
            assertArrayEquals(new double[] {0, 1, 8}, new double[] {testFractionsMultiply[1].mathOperationsOnTwoFractions(Operations.MULTIPLY, testFractionsMultiply[8]).getWholeNumber(), testFractionsMultiply[1].mathOperationsOnTwoFractions(Operations.MULTIPLY, testFractionsMultiply[8]).getCounter(), testFractionsMultiply[1].mathOperationsOnTwoFractions(Operations.MULTIPLY, testFractionsMultiply[8]).getDenominator()});

            //----------------------------------------DIVIDE OPERATION

            Fraction[] testFractionsDivide = {
                    new Fraction(1),
                    new Fraction(1,2),
                    new Fraction(1,4, 3),
                    new Fraction(-1,2),
                    new Fraction(1,-2),
                    new Fraction(1,4, -3),
                    new Fraction(1.98d,-2.53d),
                    new Fraction(0,2),
                    new Fraction(1,4, 0),
            };

            //  1 / 1
            assertArrayEquals(new double[] {1}, new double[] {testFractionsDivide[0].mathOperationsOnTwoFractions(Operations.DIVIDE, testFractionsDivide[0]).getWholeNumber()});
            //  1/2 / 1/2
            assertArrayEquals(new double[] {0, 1, 1}, new double[] {testFractionsDivide[1].mathOperationsOnTwoFractions(Operations.DIVIDE, testFractionsDivide[1]).getWholeNumber(), testFractionsDivide[1].mathOperationsOnTwoFractions(Operations.DIVIDE, testFractionsDivide[1]).getCounter(), testFractionsDivide[1].mathOperationsOnTwoFractions(Operations.DIVIDE, testFractionsDivide[1]).getDenominator()});
            //  1 / 1/2
            assertArrayEquals(new double[] {0, 2, 1}, new double[] {testFractionsDivide[0].mathOperationsOnTwoFractions(Operations.DIVIDE, testFractionsDivide[1]).getWholeNumber(), testFractionsDivide[0].mathOperationsOnTwoFractions(Operations.DIVIDE, testFractionsDivide[1]).getCounter(), testFractionsDivide[0].mathOperationsOnTwoFractions(Operations.DIVIDE, testFractionsDivide[1]).getDenominator()});
            // 1 / 3 1/4
            assertArrayEquals(new double[] {0, 4, 13}, new double[] {testFractionsDivide[0].mathOperationsOnTwoFractions(Operations.DIVIDE, testFractionsDivide[2]).getWholeNumber(), testFractionsDivide[0].mathOperationsOnTwoFractions(Operations.DIVIDE, testFractionsDivide[2]).getCounter(), testFractionsDivide[0].mathOperationsOnTwoFractions(Operations.DIVIDE, testFractionsDivide[2]).getDenominator()});
            // 1/2 / 3 1/4
            assertArrayEquals(new double[] {0, 2, 13}, new double[] {testFractionsDivide[1].mathOperationsOnTwoFractions(Operations.DIVIDE, testFractionsDivide[2]).getWholeNumber(), testFractionsDivide[1].mathOperationsOnTwoFractions(Operations.DIVIDE, testFractionsDivide[2]).getCounter(), testFractionsDivide[1].mathOperationsOnTwoFractions(Operations.DIVIDE, testFractionsDivide[2]).getDenominator()});
            // 3 1/4 / 3 1/4
            assertArrayEquals(new double[] {0, 1, 1}, new double[] {testFractionsDivide[2].mathOperationsOnTwoFractions(Operations.DIVIDE, testFractionsDivide[2]).getWholeNumber(), testFractionsDivide[2].mathOperationsOnTwoFractions(Operations.DIVIDE, testFractionsDivide[2]).getCounter(), testFractionsDivide[2].mathOperationsOnTwoFractions(Operations.DIVIDE, testFractionsDivide[2]).getDenominator()});
            // 1 / (-1/2)
            assertArrayEquals(new double[] {0, -2, 1}, new double[] {testFractionsDivide[0].mathOperationsOnTwoFractions(Operations.DIVIDE, testFractionsDivide[3]).getWholeNumber(), testFractionsDivide[0].mathOperationsOnTwoFractions(Operations.DIVIDE, testFractionsDivide[3]).getCounter(), testFractionsDivide[0].mathOperationsOnTwoFractions(Operations.DIVIDE, testFractionsDivide[3]).getDenominator()});
            // (-1/2) / (-1/2)
            assertArrayEquals(new double[] {0, 1, 1}, new double[] {testFractionsDivide[3].mathOperationsOnTwoFractions(Operations.DIVIDE, testFractionsDivide[3]).getWholeNumber(), testFractionsDivide[3].mathOperationsOnTwoFractions(Operations.DIVIDE, testFractionsDivide[3]).getCounter(), testFractionsDivide[3].mathOperationsOnTwoFractions(Operations.DIVIDE, testFractionsDivide[3]).getDenominator()});
            // -3 1/4 / (-1/2)
            assertArrayEquals(new double[] {0, 11, 2}, new double[] {testFractionsDivide[5].mathOperationsOnTwoFractions(Operations.DIVIDE, testFractionsDivide[3]).getWholeNumber(), testFractionsDivide[5].mathOperationsOnTwoFractions(Operations.DIVIDE, testFractionsDivide[3]).getCounter(), testFractionsDivide[5].mathOperationsOnTwoFractions(Operations.DIVIDE, testFractionsDivide[3]).getDenominator()});
            // -3 1/4 / (1/-2)
            assertArrayEquals(new double[] {0, 11, 2}, new double[] {testFractionsDivide[5].mathOperationsOnTwoFractions(Operations.DIVIDE, testFractionsDivide[4]).getWholeNumber(), testFractionsDivide[5].mathOperationsOnTwoFractions(Operations.DIVIDE, testFractionsDivide[4]).getCounter(), testFractionsDivide[5].mathOperationsOnTwoFractions(Operations.DIVIDE, testFractionsDivide[4]).getDenominator()});
            // -3 1/4 / (1.98/-2.53)
            assertThrows(FractionException.class, () -> testFractionsDivide[5].mathOperationsOnTwoFractions(Operations.DIVIDE, testFractionsDivide[6]));
            // (1.98/-2.53) / (1.98/-2.53)
            assertThrows(FractionException.class, () -> testFractionsDivide[6].mathOperationsOnTwoFractions(Operations.DIVIDE, testFractionsDivide[6]));
            // 1/2 / (0/2)
            assertThrows(FractionException.class, () -> testFractionsDivide[1].mathOperationsOnTwoFractions(Operations.DIVIDE, testFractionsDivide[7]));
            // 1/2 / 0 1/4
            assertArrayEquals(new double[] {0, 2, 1}, new double[] {testFractionsDivide[1].mathOperationsOnTwoFractions(Operations.DIVIDE, testFractionsDivide[8]).getWholeNumber(), testFractionsDivide[1].mathOperationsOnTwoFractions(Operations.DIVIDE, testFractionsDivide[8]).getCounter(), testFractionsDivide[1].mathOperationsOnTwoFractions(Operations.DIVIDE, testFractionsDivide[8]).getDenominator()});

        } catch(Exception e){
            System.out.println(e.getMessage());
        }
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

            assertArrayEquals(new double[] {1, 1}, new double[] {testFractions[1].power(0, 4).getCounter(), testFractions[1].power(0, 4).getDenominator()}, "Simple fraction positive values power problem!");
            assertArrayEquals(new double[] {1, 1}, new double[] {testFractions[2].power(0, 4).getCounter(), testFractions[2].power(0, 4).getDenominator()}, "Simple fraction and whole number positive values power problem!");

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
                    new Fraction(1,-2),
                    new Fraction(1,-4, 3),
            };

            assertEquals(0, testFractions[0].getDenominator(), "Get Denominator from whole number test problem!");
            assertEquals(2, testFractions[1].getDenominator(), "Get Denominator from simple fraction test problem!");
            assertEquals(4, testFractions[2].getDenominator(), "Get Denominator from whole number and simple fraction test problem!");

            assertEquals(-2, testFractions[3].getDenominator(), "Get Denominator from simple fraction test problem!");
            assertEquals(-4, testFractions[4].getDenominator(), "Get Denominator from whole number and simple fraction test problem!");

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
                    new Fraction(-1),
                    new Fraction(-1,2),
                    new Fraction(1,-4, -3),
            };

            assertEquals(1, testFractions[0].getWholeNumber(), "Get Whole number from whole number test problem!");
            assertEquals(0, testFractions[1].getWholeNumber(), "Get Whole number from simple fraction test problem!");
            assertEquals(3, testFractions[2].getWholeNumber(), "Get Whole number from whole number and simple fraction test problem!");

            assertEquals(-1, testFractions[3].getWholeNumber(), "Get Whole number from whole number test problem!");
            assertEquals(0, testFractions[4].getWholeNumber(), "Get Whole number from simple fraction test problem!");
            assertEquals(-3, testFractions[5].getWholeNumber(), "Get Whole number from whole number and simple fraction test problem!");

        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}