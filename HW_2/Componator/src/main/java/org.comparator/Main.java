package org.comparator;

public class Main {
    public static void main(String[] args) {
        // Создаем фрукты с весом
        Container apple = new Fruit("Apple", 150);
        Container orange = new Fruit("Orange", 120);

        // Создаем ящик и добавляем фрукты
        Box box1 = new Box();
        box1.addItem(apple);
        box1.addItem(orange);

        // Создаем еще один ящик и добавляем в него фрукты
        Box box2 = new Box();
        Container banana = new Fruit("Banana", 200);
        Container cherry = new Fruit("Cherry", 50);
        box2.addItem(banana);
        box2.addItem(cherry);

        // Добавляем в первый ящик второй ящик
        box1.addItem(box2);

        // Выводим содержимое первого ящика
        box1.print();
//      box2.print();
    }
}

