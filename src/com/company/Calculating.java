package com.company;

public class Calculating {

    public static void main(String[] args) {

        Fraction firstFraction = new Fraction(2,4);
        Fraction secondFraction = new Fraction(2, 4);

        System.out.println(firstFraction.decimalFractionConvert(2));
        System.out.println(secondFraction.decimalFractionConvert(2));

        System.out.println(firstFraction.compareTo(secondFraction));
    }
}
