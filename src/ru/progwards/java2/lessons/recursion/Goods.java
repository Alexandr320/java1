package ru.progwards.java2.lessons.recursion;

import java.time.Instant;

public class Goods {
    public String name;  // наименование
    public String number; // артикул
    public int available; // количество
    public double price; // цена
    public Instant expired; // срок годности

    public Goods(String name, String number, int available, double price, Instant expired) {
        this.name = name;
        this.number = number;
        this.available = available;
        this.price = price;
        this.expired = expired;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "number='" + number + '\'' +
                ", available=" + available +
                "}\n";
    }
}
