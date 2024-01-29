package org.comparator;

/**
 * Интерфейс Container описывает общий функционал для элементов контейнера.
 */
public interface Container {

    /**
     * Метод для получения веса контейнера.
     * @return Вес контейнера.
     */
    double getWeight();

    /**
     * Метод для печати информации о контейнере.
     */
    void print();
}
