package ru.progwards.java2.lessons;

import java.util.Arrays;

// Java-программа для реализации
// односвязный список


public class LinkedList {
    Node head; // заголовок списка

    public static void main(String[] args) {

        LinkedList list = new LinkedList(); // создали пустой список

        // Вставляем значения:
        insert(list, 1);
        insert(list, 2);
        insert(list, 3);
        insert(list, 4);
        insert(list, 5);
        insert(list, 6);
        insert(list, 7);
        insert(list, 8);


        printList(list); // Распечатать LinkedList

        // ****** УДАЛЕНИЕ ПО КЛЮЧУ ******
        deleteByKey(list, 1);  // Удалить узел со значением 1
        printList(list); // Распечатать LinkedList

        deleteByKey(list, 4); // Удалить узел со значением 4
        printList(list);        // Распечатать LinkedList

        deleteByKey(list, 10); // Удалить узел со значением 10, в этом случае ключ отсутствует
        printList(list);            // Распечатать LinkedList


        // ****** УДАЛЕНИЕ НА ПОЗИЦИИ ******
        deleteAtPosition(list, 0);// Удалить узел в позиции 0
        printList(list);            // Распечатать LinkedList

        deleteAtPosition(list, 2);  // Удалить узел в позиции 2
        printList(list);            // Распечатать LinkedList

        deleteAtPosition(list, 10); // Удалить узел в позиции 10, в этом случае ключ отсутствует
        printList(list);                // Распечатать LinkedList

        System.out.println(Arrays.toString(maxListValues(list, 2))); // для двух значений
        System.out.println(Arrays.toString(maxListValues(list, 4)));  // больше двух значений
    }

    // Узел связного списка. Этот внутренний класс сделан статическим, чтобы main () мог получить к нему доступ
    static class Node {
        int data;
        Node next;

        Node(int d)     {  // Конструктор
            data = d;
            next = null;
        }
    }

    // ************** ВСТАВКА **************

    // Метод для вставки нового узла
    public static void insert(LinkedList list, int data) {

        // Создать новый узел с заданными данными
        Node new_node = new Node(data);
        new_node.next = null;

        // Если связанный список пуст, делаем новый узел головой
        if (list.head == null) {
            list.head = new_node;
        }
        else {
            // Остальное до последнего узла и вставляем туда новый узел
            Node last = list.head;
            while (last.next != null) {
                last = last.next;
            }
            // Вставляем new_node в последний узел
            last.next = new_node;
        }
        // Возвращаем список по голове
    }

    // ************** ОБХОД СПИСКА **************

    // Метод для печати LinkedList.
    public static void printList(LinkedList list)
    {
        Node currNode = list.head;
        System.out.print("\nLinkedList: ");

        // Пройдемся по LinkedList
        while (currNode != null) {
            // Распечатать данные на текущем узле
            System.out.print(currNode.data + " ");

            // Перейти к следующему узлу
            currNode = currNode.next;
        }
        System.out.println("\n");
    }

    // ************** УДАЛЕНИЕ ПО КЛЮЧУ **************

    // Метод для удаления узла в LinkedList с помощью KEY
    public static void deleteByKey(LinkedList list, int key) {

        // Сохраняем головной узел
        Node currNode = list.head, prev = null;

        // Если головной узел сам содержит ключ для удаления
        if (currNode != null && currNode.data == key) {
            list.head = currNode.next; // Измененная голова
            System.out.println(key + " found and deleted"); // Показать сообщение
            return; // Возвращаем обновленный список
        }

        // Если ключ находится где-то кроме головы, то:
        // Поиск ключа, который нужно удалить, отслеживаем предыдущий узел
        // как нужно поменять currNode.next
        while (currNode != null && currNode.data != key) {

            // Если currNode не держит ключ, перейти к следующему узлу
            prev = currNode;
            currNode = currNode.next;
        }

        // Если ключ присутствовал, он должен быть в currNode, поэтому currNode не должен быть нулевым

        if (currNode != null) {
            // Поскольку ключ находится в currNode, Отключить currNode от связанного списка
            prev.next = currNode.next;
            // Показать сообщение
            System.out.println(key + " found and deleted");
        }

        // Если ключ не присутствовал в связанном списке, currNode должен быть нулевым
        if (currNode == null) {
            System.out.println(key + " not found"); // Показать сообщение
        }
    }

    // ************** УДАЛЕНИЕ НА ПОЗИЦИИ **************
    // Метод для удаления узла в LinkedList по POSITION

    public static void deleteAtPosition(LinkedList list, int index) {

        // Сохраняем головной узел
        Node currNode = list.head, prev = null;

        // Если индекс равен 0, то сам головной узел должен быть удален
        if (index == 0 && currNode != null) {
            list.head = currNode.next; // Измененная голова
            // Показать сообщение
            System.out.println(index + " position element deleted");
            // Возвращаем обновленный список
            return;
        }

        // Если индекс больше 0, но меньше размера LinkedList:

        int counter = 0; // Счетчик
        // Считаем индекс для удаления, отслеживаем предыдущий узел
        // как нужно поменять currNode.next
        while (currNode != null) {
            if (counter == index) {
                // Поскольку currNode является обязательной позицией, отключить currNode от связанного списка
                prev.next = currNode.next;
                System.out.println(index + " position element deleted"); // Показать сообщение
                break;
            }
            else {
                // Если текущая позиция не является индексом,  перейти к следующему узлу
                prev = currNode;
                currNode = currNode.next;
                counter++;
            }
        }

        // Если элемент позиции был найден, он должен быть в currNode, поэтому currNode не должен быть нулевым

        // Индекс больше, чем размер LinkedList, в этом случае currNode должен быть нулевым
        if (currNode == null) {
            System.out.println(index + " position element not found");  // Показать сообщение
        }
    }

    // Реализуйте поиск заданного количества элементов (N) с максимальным значением в этом списке. Например, если список
    // содержит значения [5,2,4,1] то для N=2 результат будет [4,5]
    public static int[] maxListValues(LinkedList list, int amount) {
        int[] maxValues = new int[amount];
        Node currNode = list.head;

        for (int i = 0; i < amount; i++) {
            int tmp = 0;
            while (currNode != null) {
                if (currNode.data > maxValues[i]) {
                    if (i == 0) {
                        maxValues[i] = currNode.data;
                    } else  {
                        for (int j = 0; j < i; j++) {
                            if (currNode.data < maxValues[j] ) {
                                tmp = currNode.data;
                                //System.out.println(tmp);
                                break;
                            }
                        } maxValues[i] = tmp;
                    }
                }
                currNode = currNode.next; // Перейти к следующему узлу
            } currNode = list.head;
        }
        return maxValues;
    }
}
