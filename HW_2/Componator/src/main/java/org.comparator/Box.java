package org.comparator;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс Box представляет собой реализацию контейнера,
 * который может содержать другие контейнеры и фрукты.
 */
public class Box implements Container {
    private List<Container> items = new ArrayList<>();

    /**
     * Метод для добавления элемента в контейнер.
     * @param item Элемент, который добавляется в контейнер.
     */
    public void addItem(Container item) {
        if(item == null){
            throw new IllegalArgumentException("Контейнер не может быть пуст");
        }
        items.add(item);
    }

    /**
     * Переопределенный метод для получения общего веса контейнера.
     * @return Общий вес контейнера, включая вес всех элементов.
     */
    @Override
    public double getWeight() {
        double totalWeight = 0;
        for (Container item : items) {
            totalWeight += item.getWeight();
        }
        return totalWeight;
    }

    /**
     * Переопределенный метод для печати информации о контейнере.
     */
    @Override
    public void print() {
        System.out.println("В коробке (Общий вес: " + getWeight() + "КГ):");
        for (Container item : items) {
            item.print();
        }
    }
}
