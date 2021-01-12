package ru.progwards.java2.lessons.basetypes;

import java.util.*;

public class DoubleHashTable<K extends DoubleHashTable.HashValue, V> implements Iterable<DoubleHashTable.DoubleHashItem<K, V>> {
    private static final double PERCENT_COLLISION = 0.1;
    private static final int EXPAND_SIZE_KOEFF = 2;

    public static void main(String[] args) {
        DoubleHashTable<IntKey, String> table = new DoubleHashTable<>(11);
        table.add(new IntKey(0), "0");
        table.add(new IntKey(1), "1");
        table.add(new IntKey(2), "2");
        table.add(new IntKey(3), "3");
        table.add(new IntKey(4), "4");
        table.add(new IntKey(5), "5");
        table.add(new IntKey(6), "6");
        table.add(new IntKey(7), "7");
        table.add(new IntKey(8), "8");
        table.add(new IntKey(9), "9");
        table.add(new IntKey(33), "33");
        for (DoubleHashItem<IntKey, String> item : table) {
            System.out.println(String.format("%s: %s", item.getKey(), item.getValue()));
        }

        StringKey stringKey = new StringKey("i-0");
        System.out.println(stringKey.getHash());
    }

    private void allocateMemory() {  // выделение большей памяти для таблицы
        int newSize = findPrime(table.length * EXPAND_SIZE_KOEFF);
        DoubleHashItem<K, V>[] newTable = new DoubleHashItem[newSize];
        List<DoubleHashItem<K, V>> tempList = new ArrayList<>();
        for (DoubleHashItem<K, V> item : this) {
            tempList.add(item);
        }
        table = newTable;
        for (DoubleHashItem<K, V> item : tempList) {
            add(item.getKey(), item.getValue());
        }
    }

    public int findPrime(int number) {  // Нахождение простого числа, ближайшего большего указанного
        int value = number;
        while (!checkPrime(value)) {
            value++;
        }
        return value;
    }

    private boolean checkPrime(int number) {  // проверка того, что число простое
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    private boolean isManyCollision() {   // проверка количества коллизий больше 10%
        double percent = ((double) countCollisions()) / table.length;
        return percent > PERCENT_COLLISION;
    }

    private int countCollisions() {  // подсчет кол-ва коллизий
        int count = 0;
        for (DoubleHashItem<K, V> tableElement : table) {
            if (tableElement != null) {
                while (tableElement.getNext() != null) {
                    tableElement = tableElement.getNext();
                    count++;
                }
            }
        }
        return count;
    }

    public static class DoubleHashItem<K1, V1> {
        private final K1 key;
        private final V1 value;
        private DoubleHashItem<K1, V1> next;

        public DoubleHashItem(K1 key, V1 value) {
            this.key = key;
            this.value = value;
        }

        K1 getKey() {
            return key;
        }

        V1 getValue() {
            return value;
        }

        void setNext(DoubleHashItem<K1, V1> item) {
            next = item;
        }

        DoubleHashItem<K1, V1> getNext() {
            return next;
        }

        boolean equals(DoubleHashItem<K1, V1> item) {
            return key == item.key;
        }


        @Override
        public String toString() {
            return key + ":" + value + (next != null ? " => " + next : "");
        }
    }

    private DoubleHashItem<K, V>[] table;

    public DoubleHashTable(int n) {
        table = new DoubleHashItem[n];
    }

    private static final float A = 0.618F;

    public int getHashAdd(int k) {      // хэш-функция, основанная на умножении - ВЫЧИСЛЯЕТ ШАГ ПРОБИРОВАНИЯ, k - ключ; N, A - параметры
        double d = A*k;
        return (int)(size()*(d-Math.floor(d)));
    }

    public int getHash(HashValue hashValue) {     // хэш-функция
        return (int) (hashValue.getHash() % table.length);
    }

    static final long UINT_MAX = 4294967295L;  // Хэш-функция для строк - вспомогательная
    static long unsignedInt(long num) {
        return num % UINT_MAX;
    }

    public void add(K key, V value) {     // добавить пару ключ-значение
        if (isManyCollision()) {
            System.out.printf("\nAllocation. Old size: %d, collisions: %d", table.length, countCollisions());
            allocateMemory();
            System.out.printf(", new size: %d\n", table.length);
        }
        int index = getHash(key);
        DoubleHashItem<K, V> newElem = new DoubleHashItem<>(key, value);
        DoubleHashItem<K, V> prev = null;
        DoubleHashItem<K, V> position = table[index];
        if (position == null) {
            table[index] = newElem;
            return;
        }
        while (true) {
            if (Objects.equals(position.getKey(), newElem.getKey())) {
                newElem.setNext(position.getNext());
                if (prev == null) {
                    table[index] = newElem;
                } else {
                    prev.setNext(newElem);
                }
                break;
            } else if (position.getNext() == null) {
                position.setNext(newElem);
                break;
            } else {
                prev = position;
                position = position.getNext();
            }
        }
    }

    private void replace(DoubleHashItem<K, V> oldElem, DoubleHashItem<K, V> newElem) {
        newElem.setNext(oldElem.getNext());

    }

    public V get(K key) {
        DoubleHashItem<K, V> elem = getElem(key);
        return elem != null ? elem.getValue() : null;
    }

    public DoubleHashItem<K, V> getElem(K key) {
        int index = getHash(key);
        DoubleHashItem<K, V> current = table[index];
        while (current != null) {
            if (Objects.equals(key, current.getKey()))
                return current;
            current = current.getNext();
        }
        return null;
    }

    public void remove(K key) {   // - удалить элемент по ключу
        int index = getHash(key);
        DoubleHashItem<K, V> prev = null;
        DoubleHashItem<K, V> current = table[index];
        while (current != null) {
            if (Objects.equals(key, current.getKey())) {
                if (prev == null) {
                    table[index] = current.getNext();
                } else {
                    prev.setNext(current.getNext());
                }
                return;
            } else {
                prev = current;
                current = current.getNext();
            }
        }
        throw new NoSuchElementException();
    }

    public void change(K key1, K key2) {  // - изменить значение ключа у элемента с key1 на key2
        V value = get(key1);
        if (value == null) {
            throw new NoSuchElementException();
        }
        remove(key1);
        add(key2, value);
    }

    public int size() {  // - получить количество элементов
        int count = 0;
        for (DoubleHashItem<K, V> item : this) {
            count++;
        }
        return count;
    }

    @Override
    public Iterator<DoubleHashItem<K, V>> iterator() {
        return itemIterator();
    }

    public Iterator<DoubleHashItem<K, V>> itemIterator() {
        return new Iterator<>() {
            private int index = -1;                      // номер текущей строки
            private DoubleHashItem<K, V> previous;  // последний возвращённый или null

            @Override
            public boolean hasNext() {
                return hasNextInCurrent() || nextIndexOrNull() != null;
            }

            @Override
            public DoubleHashItem<K, V> next() {
                if (hasNextInCurrent()) {
                    return nextInCurrent();
                } else {
                    Integer nextIndexOrNull = nextIndexOrNull();
                    if (nextIndexOrNull != null) {
                        index = nextIndexOrNull;
                        previous = table[nextIndexOrNull];
                        return previous;
                    } else {
                        throw new IndexOutOfBoundsException();
                    }
                }
            }

            private boolean hasNextInCurrent() {
                return previous != null && previous.getNext() != null;
            }

            private DoubleHashItem<K, V> nextInCurrent() {
                previous = previous.getNext();
                return previous;
            }

            private Integer nextIndexOrNull() {
                for (int i = index + 1; i < table.length; i++) {
                    if (table[i] != null) {
                        return i;
                    }
                }
                return null;
            }
        };
    }

    public static interface HashValue {
        long getHash();
    }

    public static class IntKey implements HashValue {
        private final int value;

        public IntKey(int value) {
            this.value = value;
        }
        public int getValue() {
            return value;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            IntKey intKey = (IntKey) o;
            return value == intKey.value;
        }
        @Override
        public String toString() {
            return "" + value;
        }
        @Override
        public long getHash() {
            return value;
        }
    }

    public static class StringKey implements HashValue {
        private final String value;

        public StringKey(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            StringKey stringKey = (StringKey) o;
            return Objects.equals(value, stringKey.value);
        }
        @Override
        public String toString() {
            return value;
        }
        @Override
        public long getHash() {
            long b = 378551;
            long a = 63689;
            long hash = 0;
            for (int i = 0; i < value.length(); i++) {
                hash = unsignedInt(hash * a + value.charAt(i));
                a = unsignedInt(a * b);
            }
            return hash;
        }
    }

}
