package ru.progwards.java2.lessons.gc;

public class Heap {
    int maxHeapSize;
    byte[] bytes;

    public Heap(int maxHeapSize) {
        this.maxHeapSize = maxHeapSize;
    }

    public int malloc(int size) {  //  "размещает", т.е. помечает как занятый блок памяти с количеством ячеек массива heap равным size
        return 0;
    }

    public void free(int ptr) { // "удаляет", т.е. помечает как свободный блок памяти по "указателю". Проверять валидность
        // указателя - т.е. то, что он соответствует началу ранее выделенного блока, а не его середине, или вообще, уже свободному
    }

    public void defrag() { // осуществляет дефрагментацию кучи

    }

    public void compact() {  // компактизация кучи - перенос всех занятых блоков в начало хипа, с копированием самих данных - элементов массива

    }

    class OutOfMemoryException extends Exception {  // нет свободного блока подходящего размера

        public OutOfMemoryException(String message) {
            super(message);
        }
    }

    class InvalidPointerException extends Exception { // неверный указатель. Возникает при освобождении блока,
        // если переданный указатель не является началом блока

        public InvalidPointerException(String message) {
            super(message);
        }
    }


}
