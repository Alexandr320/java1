package ru.progwards.java2.lessons.recursion;

public class AsNumbersSum {
    public static void main(String[] args) {

        System.out.println(asNumbersSum(7));
    }

    public static String asNumbersSum(int number) {
        return myRecursion(number, number,"");
    }

    private static String myRecursion(int num, int decompos, String s) {
        if (decompos <= 0) {
            return s;
        }
        int min = Math.min(num, decompos);
        String prefixWithMin = "".equals(s) ? "" + min : s + "+" + min;
        String result = myRecursion(min,decompos - min, prefixWithMin);
        if (min > 1) {
            result += " = " + myRecursion(min - 1, decompos, s);
        }
        return result;
    }


}
