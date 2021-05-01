package com.company;

public class Fraction implements Comparable{

    private int counter;
    private int denominator;
    private int wholeNumber = 0;

    boolean simpleFraction = true;

    public int getCounter() {
        return counter;
    }

    public int getDenominator() {
        return denominator;
    }

    public int getWholeNumber() {
        return wholeNumber;
    }

    Fraction(){
        this.counter = 1;
        this.denominator = 1;
        this.wholeNumber = 1;
        this.simpleFraction = false;
    }

    Fraction(int wholeNumber){
        this.wholeNumber = wholeNumber;
        this.simpleFraction = false;
    }

    Fraction(int counter, int denominator) throws FractionException{
        this.counter = counter;
        this.denominator = denominator;
        if(this.denominator == 0){
            throw new FractionException("Denominator can not have value of 0!");
        }

        this.simpleFraction = true;
    }

    Fraction(int counter, int denominator, int wholeNumber)throws FractionException{
        this(counter, denominator);
        this.wholeNumber = wholeNumber;
        this.simpleFraction = false;
    }

    public Fraction reduceFraction(boolean simpleFraction) throws FractionException{

        int finalCounter;
        int finalDenominator;
        int divideNumber = 1;

        if(this.counter == 0){
            return new Fraction(0);
        }

        int i = Math.min(Math.abs(this.counter), Math.abs(this.denominator));

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
                            int counterAfterCheck = finalCounter - (finalDenominator * divideNumber);
                            if(counterAfterCheck == finalDenominator){
                                divideNumber += 1;
                                return new Fraction(divideNumber);
                            }
                            ExceptionHandler(finalDenominator);
                            return new Fraction(counterAfterCheck, finalDenominator, divideNumber);
                        } else {
                            divideNumber++;
                        }
                    }
                }
            }

        ExceptionHandler(finalDenominator);
        return new Fraction(finalCounter, finalDenominator);
    }

    public double decimalFractionConvert(double howManyDecimals){

        double newCounter = (double) this.counter;
        double newDenominator = (double) this.denominator;
        double newWholeNUmber = (double) this.wholeNumber;

        double result = (newCounter + (newWholeNUmber * newDenominator))/ newDenominator;

        return roundDecimal(result, howManyDecimals);
    }

    public double roundDecimal(double value, double howManyDecimals){
        if(howManyDecimals < 0){
            throw new IllegalArgumentException();
        }

        double howMany = Math.pow(10, howManyDecimals);

        return Math.round(value * howMany)/howMany;
    }

    public Fraction mathOperationsOnTwoFractions(Operations operationType, Fraction other) throws FractionException{

        int finalCounter = 1;
        int finalDenominator = 1;

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

        ExceptionHandler(finalDenominator);

        return new Fraction(finalCounter, finalDenominator);
    }

    public Fraction power(int powerValue) throws FractionException{
        double finalCounter;
        double finalDenominator;

        finalCounter = Math.pow(this.counter + (this.wholeNumber * this.denominator), powerValue);
        finalDenominator = Math.pow(this.denominator, powerValue);

        ExceptionHandler((int)finalDenominator);

        return new Fraction((int) finalCounter, (int) finalDenominator);
    }

    public double sqrFraction(int sqrDegree, double howManyDecimals){
        double finalCounter;
        double finalDenominator;

        finalCounter = Math.pow(this.counter + (this.wholeNumber * this.denominator), (double)(1d/sqrDegree));
        finalDenominator = Math.pow(this.denominator, (double)(1d/sqrDegree));

        double finalResult = finalCounter/finalDenominator;

        return roundDecimal(finalResult, howManyDecimals);
    }

    public static void ExceptionHandler(int denominator) throws FractionException {
        if(denominator == 0){
            throw new FractionException("Denominator can not have value of 0!");
        }
    }

    @Override
    public String toString(){
        if(this.wholeNumber == 0){
            return "" + this.counter + "/" + this.denominator;
        }
        else{
            return "" + this.wholeNumber + " " + this.counter + "/" + this.denominator;
        }
    }

    @Override
    public int compareTo(Object other) {

        Fraction otherFractionObject = (Fraction) other;

        double thisFraction = this.decimalFractionConvert(2);
        double otherFraction = otherFractionObject.decimalFractionConvert(2);

        if(thisFraction > otherFraction){
            return 1;
        } else if (thisFraction == otherFraction){
            return 0;
        } else return -1;
    }
}
