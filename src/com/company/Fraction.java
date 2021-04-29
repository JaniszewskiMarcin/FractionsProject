package com.company;

public class Fraction implements Comparable{

    private int counter;

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        if(counter == 0){
            System.out.println("Valid value!");
            throw new IllegalArgumentException();
        } else {
            this.counter = counter;
        }
    }

    private int denominator;

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) {
        if(denominator == 0){
            System.out.println("You can not set denominator to 0");
            throw new ArithmeticException();
        } else {
            this.denominator = denominator;
        }
    }

    private int wholeNumber = 0;

    boolean simpleFraction = true;

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

    Fraction(int counter, int denominator){
        setCounter(counter);
        setDenominator(denominator);
        this.simpleFraction = true;
    }

    Fraction(int counter, int denominator, int wholeNumber){
        this.wholeNumber = wholeNumber;
        setCounter(counter);
        setDenominator(denominator);
        this.simpleFraction = false;
    }

    public Fraction reduceFraction(boolean simpleFraction){

        int finalCounter;
        int finalDenominator;
        int divideNumber = 1;

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
                            return new Fraction(counterAfterCheck, finalDenominator, divideNumber);
                        } else {
                            divideNumber++;
                        }
                    }
                }
            }

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

    public Fraction mathOperationsOnTwoFractions(Operations operationType, Fraction other){

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

        return new Fraction(finalCounter, finalDenominator);
    }

    public Fraction power(int powerValue){
        double finalCounter;
        double finalDenominator;

        finalCounter = Math.pow(this.counter + (this.wholeNumber * this.denominator), powerValue);
        finalDenominator = Math.pow(this.denominator, powerValue);

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
