package ru.progwards.java2.lessons.recursion;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GoodsWithLambda {
    List<Goods> list = new ArrayList<Goods>();

    public void setGoods(List<Goods> list) {
        this.list = list;
    }

    public List<Goods> sortByName() {  // вернуть список, отсортированный по наименованию
        return list.stream().sorted((a, b) -> a.name.compareTo(b.name)).collect(Collectors.toList());
        // return list.stream().sorted(Comparator.comparing(a -> a.name)).collect(Collectors.toList());
    }

    public List<Goods> sortByNumber() {  // вернуть список, отсортированный по артикулу, без учета регистра
        return list.stream().sorted((a, b) -> a.number.compareToIgnoreCase(b.number)).collect(Collectors.toList());
    }

    public List<Goods> sortByPartNumber() { // вернуть список, отсортированный по первым 3-м символам артикула, без учета регистра
       return list.stream().sorted((a, b) -> a.number.substring(0, 3).compareToIgnoreCase(b.number.substring(0, 3))).collect(Collectors.toList());
    }

    public List<Goods> sortByAvailabilityAndNumber() {
        // вернуть список, отсортированный по количеству, а для одинакового количества, по артикулу, без учета регистра
        Comparator<Goods> myAvailable = Comparator.comparing(a -> a.available); // сортировка по количеству (int)
        Comparator<Goods> myNumber = Comparator.comparing(a -> a.number); // сортировка по артикулу (String)
        Comparator<Goods> myTernar = myAvailable.thenComparing(myNumber);
        //Comparator<Goods> myTernar = (a, b) -> (a.available != b.available) ? myAvailable : myNumber;
        //return list.stream().sorted(Comparator.comparing(a -> a.available)).sorted((a, b) -> a.number.compareToIgnoreCase(b.number)).collect(Collectors.toList());
        return list.stream().sorted(myTernar).collect(Collectors.toList());
    }

    public List<Goods> expiredAfter(Instant date) { // вернуть список, с товаром, который будет просрочен после указанной даты,
        // отсортированный по дате годности
        return list.stream().filter(a -> a.expired.isBefore(date)).sorted(Comparator.comparing(b -> b.expired)).collect(Collectors.toList());
    }

    public List<Goods> сountLess(int count) { //  вернуть список, с товаром, количество на складе которого меньше указанного,
        // отсортированный по количеству
        return list.stream().filter(a -> a.available < count).sorted(Comparator.comparing(b -> b.available)).collect(Collectors.toList());
    }

    public List<Goods> сountBetween(int count1, int count2) { // вернуть список, с товаром, количество на складе
        // которого больше count1 и меньше count2, отсортированный по количеству
        return list.stream().filter(a -> a.available > count1).filter(b -> b.available < count2).
                sorted(Comparator.comparing(c -> c.available)).collect(Collectors.toList());
        // СДЕЛАТЬ ЧЕРЕЗ AND
    }
}
