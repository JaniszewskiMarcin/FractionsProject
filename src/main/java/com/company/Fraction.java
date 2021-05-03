package com.company;

import lombok.SneakyThrows;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Fraction implements Comparable{

    @lombok.Getter
    private double counter;
    @lombok.Getter
    private double denominator;
    @lombok.Getter
    private double wholeNumber = 0;

    private boolean simpleFraction = true;

    Fraction(){
        this.counter = 1;
        this.denominator = 1;
        this.wholeNumber = 1;
        this.simpleFraction = false;
    }

    Fraction(double wholeNumber){
        this.wholeNumber = wholeNumber;
        this.counter = 0;
        this.denominator = 0;
        this.simpleFraction = false;
    }

    Fraction(double counter, double denominator) throws FractionException{
        this.counter = counter;
        this.denominator = denominator;
        if(this.denominator == 0){
            throw new FractionException("Denominator can not have value of 0!");
        }

        this.simpleFraction = true;
    }

    Fraction(double counter, double denominator, double wholeNumber)throws FractionException{
        this(counter, denominator);
        this.wholeNumber = wholeNumber;
        this.simpleFraction = false;
    }

    public Fraction reduceFraction(boolean simpleFraction) throws FractionException{

        double finalCounter;
        double finalDenominator;
        int divideNumber = 1;

        if(this.counter == 0 && this.denominator == 0){
            return new Fraction(this.wholeNumber);
        }

        if(this.counter == 0){
            return new Fraction(0);
        }

        int i = (int) Math.min(Math.abs(this.counter), Math.abs(this.denominator));

            while(this.counter%i != 0 || this.denominator%i != 0) {
                i--;
            }

            finalCounter = this.counter/i;
            finalDenominator = this.denominator/i;

            if(!simpleFraction) {
                if(finalCounter > finalDenominator){
                    while(true){
                        if(finalDenominator * divideNumber >= finalCounter){
                            divideNumber--;
                            double counterAfterCheck = finalCounter - (finalDenominator * divideNumber);
                            if(counterAfterCheck == finalDenominator){
                                divideNumber += 1;
                                return new Fraction(divideNumber);
                            }
                            checkException(finalDenominator);
                            return new Fraction(counterAfterCheck, finalDenominator, divideNumber);
                        } else {
                            divideNumber++;
                        }
                    }
                }
            }

        checkException(finalDenominator);
        return new Fraction(finalCounter, finalDenominator);
    }

    public double decimalFractionConvert(int howManyDecimals) throws FractionException {

        double newCounter = this.counter;
        double newDenominator = this.denominator;
        double newWholeNumber = this.wholeNumber;
        double result;

        if(newDenominator == 0 && newCounter == 0){
            result = newWholeNumber;
        } else {
            result = (newCounter + (newWholeNumber * newDenominator))/ newDenominator;
        }

        return roundDecimal(result, howManyDecimals, true);
    }

    public double roundDecimal(double value, int howManyDecimals, boolean onlyCutNumbers) throws FractionException {
        if(howManyDecimals < 0){
            throw new FractionException("Invalid decimals argument! Can not input negative values!");
        }

        if(onlyCutNumbers){
            BigDecimal bd = new BigDecimal(value).setScale(howManyDecimals, RoundingMode.UNNECESSARY);
            return bd.doubleValue();
        } else {
            double howMany = Math.pow(10, howManyDecimals);
            return Math.round(value * howMany)/howMany;
        }
    }

    public Fraction mathOperationsOnTwoFractions(Operations operationType, Fraction other) throws FractionException{

        double finalCounter = 1;
        double finalDenominator = 1;

        switch(operationType){
            case ADD:
                if(this.denominator == other.denominator){
                finalCounter = this.counter + other.counter;
                finalDenominator = this.denominator;
                }

                else {
                    finalDenominator = this.denominator * other.denominator;
                    finalCounter = (this.counter * other.denominator) + (other.counter * this.denominator);
                }

                break;

            case SUBTRACK:
                if(this.denominator == other.denominator){
                    finalCounter = this.counter - other.counter;
                    finalDenominator = this.denominator;
                }

                else {
                    finalDenominator = this.denominator * other.denominator;
                    finalCounter = (this.counter * other.denominator) - (other.counter * this.denominator);
                }

                break;

            case MULTIPLY:
                finalCounter = this.counter * other.counter;
                finalDenominator = this.denominator * other.denominator;

                break;

            case DIVIDE:
                finalCounter = this.counter * other.denominator;
                finalDenominator = this.denominator * other.counter;

                break;
        }

        checkException(finalDenominator);

        return new Fraction(finalCounter, finalDenominator);
    }

    public Fraction power(int powerValue, int howManyDecimals) throws FractionException {
        double finalCounter;
        double finalDenominator;
        double finalWholeNumber;

        finalWholeNumber = Math.pow(this.wholeNumber, powerValue);

        if(this.denominator == 0 && this.counter == 0){
            return new Fraction(roundDecimal(finalWholeNumber, howManyDecimals, true));
        }

        finalCounter = Math.pow(this.counter + (this.wholeNumber * this.denominator), powerValue);
        finalDenominator = Math.pow(this.denominator, powerValue);

        return new Fraction(roundDecimal(finalCounter, howManyDecimals, true), roundDecimal(finalDenominator, howManyDecimals, true));
    }

    public double sqrFraction(int sqrDegree, int howManyDecimals) throws FractionException{
        double finalCounter;
        double finalDenominator;

        if((this.denominator < 0  || this.counter < 0 || this.wholeNumber < 0) || sqrDegree < 0){
            throw new FractionException("You can not do this operation on negative numbers!");
        }

        if(this.denominator == 0 && this.counter == 0){
            return roundDecimal(Math.pow(this.wholeNumber, (1d/sqrDegree)), howManyDecimals, true);
        }

        finalCounter = Math.pow(this.counter + (this.wholeNumber * this.denominator),(1d/sqrDegree));
        finalDenominator = Math.pow(this.denominator, (1d/sqrDegree));

        double finalResult = finalCounter/finalDenominator;

        return roundDecimal(finalResult, howManyDecimals, true);
    }

    public static void checkException(double denominator) throws FractionException {
        if(denominator == 0){
            throw new FractionException("Denominator can not have value of 0!");
        }
    }

    @Override
    public String toString(){
        if(this.wholeNumber == 0){
            return "" + this.counter + "/" + this.denominator;
        }
        else if(this.denominator == 0 && this.counter == 0){
            return "" + this.wholeNumber;
        } else {
            return "" + this.wholeNumber + " " + this.counter + "/" + this.denominator;
        }
    }

    @SneakyThrows
    @Override
    public int compareTo(Object other) {

        Fraction otherFractionObject = (Fraction) other;

        double thisFraction = this.decimalFractionConvert(4);
        double otherFraction = otherFractionObject.decimalFractionConvert(4);

        return Double.compare(thisFraction, otherFraction);
    }
}
