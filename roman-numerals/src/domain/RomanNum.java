
package domain;

public class RomanNum {
    private String rNum;

    // Mapping the roman values and symbols in descending order into arrays
    private static final int[] VALUES = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final String[] SYMBOLS = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    public RomanNum(int num) {
        this.rNum = intToRoman(num);
    }

    public RomanNum(String rNum) {
        Integer value = romanToInt(rNum);
        if (value == null || value <= 0) {
            throw new IllegalArgumentException("Invalid roman numeral: " + rNum);
        }
        this.rNum = rNum.toUpperCase();
    }

    public static String intToRoman(int num) {
        if (num <= 0) {
            return null;
        }

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < VALUES.length; i++) {
            while (num >= VALUES[i]) {
                /*
                    num >= 1000 -> result += "M" -> num -= 1000
                    //repeat the process until num < 1000
                    num >= 900 -> result += "CM" -> num -= 900
                    num >= 500 -> result += "D" -> num -= 500
                    num >= 100 -> result += "C" -> num -= 100
                    //repeat the process until num < 100
                    ...
                */
                result.append(SYMBOLS[i]);
                num -= VALUES[i];
            }
        }

        return result.toString();
    }

    public static Integer romanToInt(String rNum) {
        if (rNum == null || rNum.isEmpty()) {
            return null;
        }

        rNum = rNum.toUpperCase();
        int result = 0;
        int i = 0;

        // Travel through the values
        for (int j = 0; j < VALUES.length && i < rNum.length(); j++) {
            // Verify how many times the current symbol appears sequentially
            while (i < rNum.length() && rNum.startsWith(SYMBOLS[j], i)) {
                //current position < number of integers places && rNum.startsWith("M", current position)
                result += VALUES[j];
                i += SYMBOLS[j].length();
                /*
                    if rNum starts with "M", for example:
                    result += values in the position 0 have a value equivalent to symbols[0],
                    so result+=1000
                    i += 1, because the length of "M" is 1
                */
            }
        }

        // If it doesn't process the entire String it's because there are invalid chars
        if (i < rNum.length()) {
            return null;
        }

        return result;
    }
    public String getRomanNumber() {
        return rNum;
    }

    public int getIntValue() {
        return romanToInt(rNum);
    }

    @Override
    public String toString() {
        return rNum;
    }
}