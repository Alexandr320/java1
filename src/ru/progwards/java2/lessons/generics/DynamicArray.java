package ru.progwards.java2.lessons.generics;

public class DynamicArray<T> {
    private Object[] myArray = new Object[10];
    private int index;  // Первый свободный индекс (так же равен количеству элементов)

    public void add(T element) {  // добавляет элемент в конец массива
        if (needNewSize()) {
            newSize();
        }
        myArray[index++] = element;
    }

    public void insert(int pos, T element) { // добавляет элемент в заданную позицию массива
        if (pos > index || pos < 0) {
            throw  new IndexOutOfBoundsException("Ошибка размера");
        }
        if (needNewSize()) {
            newSize();
        }
        for (int i = pos; i < index; i++) {
            myArray[i+1] = myArray[i];
        }
        myArray[pos] = element;
    }

    public void remove(int pos) {  // удаляет элемент в позиции pos массива
        if (pos >= index || pos < 0) {
            throw  new IndexOutOfBoundsException("Ошибка размера");
        }
        // Осталось сделать удаление элемента
        for (int i = pos+1; i < index; i++) {
            myArray[i-1] = myArray[i];  // ВЕРНО ЛИ?
        }
        myArray[index-1] = null;
    }

    public T get(int pos) {  // возвращает элемент по индексу pos
        if (pos >= index || pos < 0) {
            throw  new IndexOutOfBoundsException("Ошибка размера");
        } else {
            return (T) myArray[pos];
        }
    }

    public int size() {  // возвращает текущий реальный объем массива
        return index;
    }

    private boolean needNewSize() {
        return index >= myArray.length;
    }

    private void newSize() {
        Object[] myArray1 = new Object[myArray.length * 2];
        //Object[] myArray1 = new Object[Math.round(myArray.length * 1.5F)];
        // F - чтобы сделать float, т.к. есть два разных round и только round(float) возвращает int
        for (int i = 0; i < myArray.length; i++) {
            myArray1[i] = myArray[i];
        }
        myArray = myArray1;
    }

}
