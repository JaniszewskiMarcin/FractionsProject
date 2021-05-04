package com.company;

import lombok.SneakyThrows;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Fraction implements Comparable{

    @lombok.Getter
    private double counter;
    @lombok.Getter
    private double denominator;
    @lombok.Getter
    private double wholeNumber = 0;

    private boolean simpleFraction = true;

    Fraction(){
        this.counter = 0;
        this.denominator = 0;
        this.wholeNumber = 0;
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

    public Fraction reduceFraction(boolean simpleFraction) throws FractionException{    //Method reducing fractions only with integer values

        double finalCounter;
        double finalDenominator;
        int divideNumber = 1;
        double counterAfterCheck;

        if ((this.wholeNumber == Math.floor(this.wholeNumber)) && !Double.isInfinite(this.wholeNumber) &&
                (this.counter == Math.floor(this.counter)) && !Double.isInfinite(this.counter) &&
                (this.denominator == Math.floor(this.denominator)) && !Double.isInfinite(this.denominator)) {   //Checking if values are integers

            if(this.counter == 0 && this.denominator == 0){
                return new Fraction(this.wholeNumber);
            }

            if(this.counter == 0){
                return new Fraction(0);
            }

            double temp = this.counter + (this.wholeNumber * this.denominator);

            int i = (int) Math.min(Math.abs(temp), Math.abs(this.denominator));     //Searching for greatest common factor

            while(temp%i != 0 || this.denominator%i != 0) {
                i--;
            }

            finalCounter = temp/i;
            finalDenominator = this.denominator/i;

            if(!simpleFraction) {   //If fraction should be with whole number
                if(Math.abs(finalCounter) > Math.abs(finalDenominator)){
                    while(true){
                        if(Math.abs(finalDenominator * divideNumber) >= Math.abs(finalCounter)){
                            if(finalCounter < 0 || finalDenominator < 0){
                                divideNumber *= -1;
                            }
                            counterAfterCheck = finalCounter - (finalDenominator * divideNumber);
                            divideNumber--;
                            if(counterAfterCheck == 0){
                                divideNumber++;
                                return new Fraction(divideNumber);
                            }

                            checkException(finalDenominator);

                            if(finalCounter < 0 || finalDenominator < 0){
                                divideNumber *= -1;
                            }
                            counterAfterCheck = finalCounter - (finalDenominator * divideNumber);

                            return new Fraction(counterAfterCheck, finalDenominator, divideNumber);
                        } else {
                            divideNumber++;
                        }
                    }
                }
            }

            checkException(finalDenominator);
            return new Fraction(finalCounter, finalDenominator);
        } else {
            throw new FractionException("You can not reduce fractions if it has a decimal values!");
        }
    }

    public static Fraction decimalToFractionConvert(double value) throws FractionException{ //Method that converts double values to simple fraction
        double finalCounter;
        double finalDenominator;

        int howManyDecimals = new BigDecimal(value).scale();    //Checking how many decimals are in value

        finalCounter = value * Math.pow(10d, howManyDecimals);
        finalDenominator = Math.pow(10d, howManyDecimals);


        return new Fraction(finalCounter, finalDenominator).reduceFraction(true);
    }

    public double fractionToDecimalConvert(int howManyDecimals) throws FractionException {      //Method that converts fractions to double value

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

    public static double roundDecimal(double value, int howManyDecimals, boolean onlyCutNumbers) throws FractionException { //Method that round or just cut off decimals to specific value

        if(howManyDecimals < 0){
            throw new FractionException("Invalid decimals argument! Can not input negative values!");
        }

        if(onlyCutNumbers){
            return ((long)(value * Math.pow(10d, howManyDecimals))) / Math.pow(10d, howManyDecimals);
        } else {
            double howMany = Math.pow(10, howManyDecimals);
            return Math.round(value * howMany)/howMany;
        }
    }

    public Fraction mathOperationsOnTwoFractions(Operations operationType, Fraction other) throws FractionException{    //Method that handles basic operations on two fractions

        double finalCounter = 1;
        double finalDenominator = 1;

        switch(operationType){
            case ADD:

                if(this.denominator == 0 && this.counter == 0 &&
                    other.denominator == 0 && other.counter == 0){
                    return new Fraction(this.wholeNumber + other.wholeNumber);
                }

                if(this.denominator == 0 && this.counter == 0){
                    this.counter = decimalToFractionConvert(this.wholeNumber).getCounter();
                    this.denominator = decimalToFractionConvert(this.wholeNumber).getDenominator();
                    this.wholeNumber = 0;
                } else if(other.denominator == 0 && other.counter == 0){
                    other.counter = decimalToFractionConvert(other.wholeNumber).getCounter();
                    other.denominator = decimalToFractionConvert(other.wholeNumber).getDenominator();
                    other.wholeNumber = 0;
                }

                if(this.denominator < 0){
                    this.counter *= -1;
                    this.denominator *= -1;
                    this.wholeNumber *= -1;
                } else if(other.denominator < 0){
                    other.counter *= -1;
                    other.denominator *= -1;
                    other.wholeNumber *= -1;
                }

                if(this.denominator == other.denominator){
                    finalCounter = (this.counter + (this.denominator * this.wholeNumber)) + (other.counter + (other.denominator * other.wholeNumber));
                    finalDenominator = this.denominator;
                }

                else {
                    finalDenominator = this.denominator * other.denominator;
                    finalCounter = ((this.counter + (this.denominator * this.wholeNumber)) * other.denominator) + ((other.counter + (other.denominator * other.wholeNumber)) * this.denominator);
                }

                break;

            case SUBTRACK:

                if(this.denominator == 0 && this.counter == 0 &&
                        other.denominator == 0 && other.counter == 0){
                    return new Fraction(this.wholeNumber - other.wholeNumber);
                }

                if(this.denominator == 0 && this.counter == 0){
                    this.counter = decimalToFractionConvert(this.wholeNumber).getCounter();
                    this.denominator = decimalToFractionConvert(this.wholeNumber).getDenominator();
                    this.wholeNumber = 0;
                } else if(other.denominator == 0 && other.counter == 0){
                    other.counter = decimalToFractionConvert(other.wholeNumber).getCounter();
                    other.denominator = decimalToFractionConvert(other.wholeNumber).getDenominator();
                    other.wholeNumber = 0;
                }

                if(this.denominator < 0){
                    this.counter *= -1;
                    this.denominator *= -1;
                    this.wholeNumber *= -1;
                } else if(other.denominator < 0){
                    other.counter *= -1;
                    other.denominator *= -1;
                    other.wholeNumber *= -1;
                }

                if(this.denominator == other.denominator){
                    finalCounter = (this.counter + (this.denominator * this.wholeNumber)) - (other.counter + (other.denominator * other.wholeNumber));
                    finalDenominator = this.denominator;
                }

                else {
                    finalDenominator = this.denominator * other.denominator;
                    finalCounter = ((this.counter + (this.denominator * this.wholeNumber)) * other.denominator) - ((other.counter + (other.denominator * other.wholeNumber)) * this.denominator);
                }

                break;

            case MULTIPLY:

                if(this.denominator == 0 && this.counter == 0 &&
                        other.denominator == 0 && other.counter == 0){
                    return new Fraction((this.wholeNumber * other.wholeNumber));
                }

                if(this.denominator == 0 && this.counter == 0){
                    this.counter = decimalToFractionConvert(this.wholeNumber).getCounter();
                    this.denominator = decimalToFractionConvert(this.wholeNumber).getDenominator();
                    this.wholeNumber = 0;
                } else if(other.denominator == 0 && other.counter == 0){
                    other.counter = decimalToFractionConvert(other.wholeNumber).getCounter();
                    other.denominator = decimalToFractionConvert(other.wholeNumber).getDenominator();
                    other.wholeNumber = 0;
                }

                if(this.denominator < 0){
                    this.counter *= -1;
                    this.denominator *= -1;
                    this.wholeNumber *= -1;
                } else if(other.denominator < 0){
                    other.counter *= -1;
                    other.denominator *= -1;
                    other.wholeNumber *= -1;
                }

                finalCounter = (this.counter + (this.denominator * this.wholeNumber)) * (other.counter + (other.denominator * other.wholeNumber));
                finalDenominator = this.denominator * other.denominator;

                break;

            case DIVIDE:

                if(this.denominator == 0 && this.counter == 0 &&
                        other.denominator == 0 && other.counter == 0){
                    return new Fraction((this.wholeNumber / other.wholeNumber));
                }

                if(this.denominator == 0 && this.counter == 0){
                    this.counter = decimalToFractionConvert(this.wholeNumber).getCounter();
                    this.denominator = decimalToFractionConvert(this.wholeNumber).getDenominator();
                    this.wholeNumber = 0;
                } else if(other.denominator == 0 && other.counter == 0){
                    other.counter = decimalToFractionConvert(other.wholeNumber).getCounter();
                    other.denominator = decimalToFractionConvert(other.wholeNumber).getDenominator();
                    other.wholeNumber = 0;
                }

                if(this.denominator < 0){
                    this.counter *= -1;
                    this.denominator *= -1;
                    this.wholeNumber *= -1;
                } else if(other.denominator < 0){
                    other.counter *= -1;
                    other.denominator *= -1;
                    other.wholeNumber *= -1;
                }

                finalCounter = (this.counter + (this.denominator * this.wholeNumber)) * other.denominator;
                finalDenominator = this.denominator * (other.counter + (other.denominator * other.wholeNumber));

                if(finalDenominator < 0){
                    finalCounter *= -1;
                    finalDenominator *= -1;
                }

                break;
        }

        checkException(finalDenominator);

        return new Fraction(finalCounter, finalDenominator).reduceFraction(true);
    }

    public Fraction power(int powerValue, int howManyDecimals) throws FractionException {   //Method counting power of fractions
        double finalCounter;
        double finalDenominator;
        double finalWholeNumber;

        finalWholeNumber = Math.pow(this.wholeNumber, powerValue);

        if(this.denominator == 0 && this.counter == 0){ //If fraction contains only whole number do the power only for it and return
            return new Fraction(roundDecimal(finalWholeNumber, howManyDecimals, true));
        }

        finalCounter = Math.pow(this.counter + (this.wholeNumber * this.denominator), powerValue);  //Count separately counter and denominator power of fraction
        finalDenominator = Math.pow(this.denominator, powerValue);

        return new Fraction(roundDecimal(finalCounter, howManyDecimals, true), roundDecimal(finalDenominator, howManyDecimals, true)); //Return values as a new Fraction with rounded final values
    }

    public double sqrFraction(int sqrDegree, int howManyDecimals) throws FractionException{     //Method counting square root from fraction, returning double value
        double finalCounter;
        double finalDenominator;

        if((this.denominator < 0  || this.counter < 0 || this.wholeNumber < 0) || sqrDegree < 0){   //Checking for exception
            throw new FractionException("You can not do this operation on negative numbers!");
        }

        if(this.denominator == 0 && this.counter == 0){     //If fraction contains only whole number do square root only on that number and return
            return roundDecimal(Math.pow(this.wholeNumber, (1d/sqrDegree)), howManyDecimals, true);
        }

        finalCounter = Math.pow(this.counter + (this.wholeNumber * this.denominator),(1d/sqrDegree));   //Reducing fraction to simple ones and counting square root on denominator and counter separately
        finalDenominator = Math.pow(this.denominator, (1d/sqrDegree));

        double finalResult = finalCounter/finalDenominator; //Combine two values in one by divide

        return roundDecimal(finalResult, howManyDecimals, true);    //Returning rounded final value
    }

    public static void checkException(double denominator) throws FractionException {    //Method that check if FractionException should occur
        if(denominator == 0){
            throw new FractionException("Denominator can not have value of 0!");
        }
    }

    @Override
    public String toString(){
        if(this.wholeNumber == 0){  //If whole number is 0 write only counter and denominator
            return "" + this.counter + "/" + this.denominator;
        }
        else if(this.denominator == 0 && this.counter == 0){    //If denominator and counter equals 0 write only whole number
            return "" + this.wholeNumber;
        } else {                                                //In other case write all down
            return "" + this.wholeNumber + " " + this.counter + "/" + this.denominator;
        }
    }

    @SneakyThrows
    @Override
    public int compareTo(Object other) {    //Method to compare different fraction

        Fraction otherFractionObject = (Fraction) other;

        double thisFraction = this.fractionToDecimalConvert(4);     //Converting both fraction to decimal form
        double otherFraction = otherFractionObject.fractionToDecimalConvert(4);

        return Double.compare(thisFraction, otherFraction);     //Using Double.compare we compare two double values and returning 1, 0 or -1
    }
}
