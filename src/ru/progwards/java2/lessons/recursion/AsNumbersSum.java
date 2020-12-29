package ru.progwards.java2.lessons.recursion;

import java.util.*;
import java.util.stream.Collectors;

public class AsNumbersSum {
    public static void main(String[] args) {
        System.out.println(asNumbersSum(4));
    }

    public static String asNumbersSum(int number) {
        String result = "";
        Set<List<Integer>> combinations = getSetList(number);
        for (List<Integer> combination : combinations) {
            String combinationStr = combination.stream().map(e -> "" + e).collect(Collectors.joining("+"));
            if (!"".equals(result)) {
                result += " = ";
            }
            result += combinationStr;
        }
        return result;
    }

    public static Set<List<Integer>> getSetList(int number) {
        if (number <= 1) {
            return new LinkedHashSet<>(Set.of(new ArrayList<>(List.of(1))));
        }
        Set<List<Integer>> result = new LinkedHashSet<>();
        for (int a = number - 1; a >= 1; a--) {
            int b = number - a;
            Set<List<Integer>> bCombinations = getSetList(b);
            for (List<Integer> combination : bCombinations) {
                combination.add(0, a);
                Collections.sort(combination);
                Collections.reverse(combination);
                result.add(combination);
            }
        }
        return result;
    }


}
