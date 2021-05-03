package com.company;

public class Calculator {

    public static void main(String[] args) {
        try{
            Fraction firstFraction = new Fraction(4,6);
            Fraction secondFraction = new Fraction(2, 4);

            Fraction a = firstFraction.reduceFraction(false);

            System.out.println(a);

            System.out.println(firstFraction.decimalFractionConvert(2));
            System.out.println(secondFraction.decimalFractionConvert(2));

            System.out.println(firstFraction.compareTo(secondFraction));

        } catch(FractionException e){
            System.out.println(e.getMessage());
        }
    }
}
