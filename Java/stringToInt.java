/**
Implement atoi to convert a string to an integer.
**/

public class Solution {
    public int myAtoi(String str) {
        int whiteSpaceFlag = 0;
        char sign = '\0';
        char number[] = new char[10];
        int numberIndex = 0;
        // strip whitespace & invalid chars. Save sign
        for (char digit: str.toCharArray()) {
            if (whiteSpaceFlag == 0) {
                if(digit != ' ') {
                    whiteSpaceFlag = 1;
                    if (digit == '+' || digit == '-') {
                        sign = digit;
                        continue;
                    }
                } else {
                    continue;
                }
            }
            if (Character.isDigit(digit)) {
                if (numberIndex == 10) {
                    return outOfBounds(sign);
                }
                number[numberIndex++] = digit;
            } else {
                break;
            }
        }
        //If no number was found, return 0
        if (numberIndex == 0) {
            return 0;
        }
        int index = 0;
        double integer = 0;
        double totalInteger = 0;
        //For each char, convert it to a int using its power and ascii value. Add it to total
        for (int power = numberIndex - 1; power >= 0; power--) {
            integer = getIntFromChar(number[index++], power);
            totalInteger += integer;
        }
        //error check overflow
        if(totalInteger > 2147483647) {
            return outOfBounds(sign);
        } else {
            totalInteger = (int) totalInteger;
        }
        //Add sign
        if (sign == '-') {
            totalInteger = totalInteger * -1;
        }
        return (int) totalInteger;
    }
    //helper function that returns the correct out of bounds int depending on sign
    private int outOfBounds(char sign) {
        if (sign == '\0' || sign == '+') {
            return 2147483647;
        } else if (sign == '-') {
            return -2147483648;
        }
        return 0;
    }
    //convert char and its place in the array to an integer
    private double getIntFromChar(char c, int power) {
        int ascii = (int) c;
        double integer = ascii - 48;
        integer = integer * Math.pow(10, power);
        return integer;
    }
}