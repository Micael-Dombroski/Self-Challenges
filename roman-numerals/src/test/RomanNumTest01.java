package test;

import domain.RomanNum;

public class RomanNumTest01 {
    public static void main(String[] args) {
        System.out.println(RomanNum.romanToInt("VI"));
        System.out.println(RomanNum.romanToInt("III"));
        System.out.println(RomanNum.romanToInt("CM"));
        System.out.println(RomanNum.romanToInt("MMMVII"));
        System.out.println(RomanNum.intToRoman(5));
        System.out.println(RomanNum.intToRoman(154));
        System.out.println(RomanNum.romanToInt(RomanNum.intToRoman(3949)));
        System.out.println(RomanNum.intToRoman(0));
        System.out.println(RomanNum.romanToInt("Rogerio"));
    }
}
