package org.comparator;

/**
 * Класс Fruit представляет собой реализацию элемента контейнера - фрукта.
 */
public class Fruit implements Container{
    private String name;
    private double weight;

    /**
     * Конструктор класса Fruit.
     * @param name   Название фрукта.
     * @param weight Вес фрукта.
     */
    public Fruit(String name, double weight){
        if (weight < 0) {
            throw new IllegalArgumentException("Вес не может быть ниже нуля");
        }
        this.name = name;
        this.weight = weight;
    }

    /**
     * Переопределенный метод для получения веса фрукта.
     * @return Вес фрукта.
     */
    @Override
    public double getWeight() {
        return weight;
    }

    /**
     * Переопределенный метод для печати информации о фрукте.
     */
    @Override
    public void print() {
        System.out.println("Фрукт: " + name + " Вес: " + weight + "КГ");
    }
}
