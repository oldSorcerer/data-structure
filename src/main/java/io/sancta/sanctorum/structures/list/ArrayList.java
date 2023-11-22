package io.sancta.sanctorum.structures.list;

import io.sancta.sanctorum.structures.AbstractCollection;

import java.util.*;

public class ArrayList<T> extends AbstractCollection<T> implements List<T> {

    private Object[] items;
    private int size;
    private int start;

    public ArrayList() {
        items = new Object[10];
    }

    public ArrayList(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Capacity must be greater than zero");
        }
        items = new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T element) {
        add(size, element);
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Not correct index");

        if (size == 0) {
            items[start] = element;
        } else if (size < items.length) { // Резерв есть!
            if (index <= size / 2) { // Вставляем в первую половну списка
                if (start == 0) { // Нет резерва в начале массива
                    // Добавить резерв слева, вставить элемент слева и весь список передвинуть вправо
                    addLeftShift(index, element);
                } else { // Есть резерв в начале массива
                    // Вставляем элемент влево и все что левее смещаем влево
                    addLeft(index, element);
                }
            } else { // Вставляем во вторую половину списка
                if (start + size < items.length) { // Есть резерв в конце массива
                    // Вставляем элемент вправо и остаток списка двигаем вправо
                    addRight(index, element);
                } else { // Нет резерва в конце массива
                    // Вставляем элемент слева, всё что левее его смещаем влево
                    addRightShift(index, element);
                }
            }
        } else { // Резерва нет!! пересоздаение массива!
            recreateItems(index, element);
        }
        size++;
    }

    private void addLeftShift(int index, T element) {

        for (int i = size - 1; i >= index; i--) {
            items[start + i + 1] = items[i];
        }
        items[start + index] = element;

        for (int i = index - 1; i >= 0; i--) {
            items[start + i] = items[i];
        }
        Arrays.fill(items, 0, start, null);
    }

    private void addLeft(int index, T element) {
        for (int i = 0; i < index; i++) {
            items[start + i - 1] = items[start + i];
        }
        start--;
        items[start + index] = element;
    }

    private void addRight(int index, T element) {
        for (int i = size; i > index; i--) {
            items[start + i] = items[start + i - 1];
        }
        items[start + index] = element;
    }

    private void addRightShift(int index, T element) {
        int newStart = start / 2;
        for (int i = 0; i < index; i++) {
            items[newStart + i] = items[start + i];
        }
        items[newStart + index] = element;

        if (newStart != start - 1) {
            for (int i = index; i < size; i++) {
                items[newStart + i + 1] = items[start + i];
            }
        }
        start = newStart;
        Arrays.fill(items, start + size, items.length, null);
    }

    private void recreateItems(int index, T element) {
        int newStart = start;

        if (newStart == 0 && index <= size / 2) {
            newStart = (items.length * 2 - size) / 2;
        }
        Object[] newItems = new Object[items.length * 2];

        for (int i = 0; i < index; i++) {
            newItems[newStart + i] = items[start + i];
        }// if (index >= 0) System.arraycopy(items, start + 0, newItems, newStart + 0, index);

        newItems[newStart + index] = element;

        for (int i = index; i < size; i++) {
            newItems[newStart + i + 1] = items[start + i];
        }
        items = newItems;
        start = newStart;
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Not correct index");

        if (items.length > size * 4) {
            recreateItems(index);
        } else {
            if (index <= size / 2) {
                for (int i = index; i > 0; i--) {
                    items[start + i] = items[start + i - 1];
                }
                items[start] = null;
                start++;
            } else {
                for (int i = index; i < size - 1; i++) {
                    items[start + i] = items[start + i + 1];
                }
                items[size] = null;
            }
        }
        size--;
    }

    private void recreateItems(int index) {
        int newStart = start;

        if (newStart == 0) {
            newStart = (items.length / 2 - size) / 2;
        }
        Object[] newItems = new Object[items.length / 2];

        for (int i = 0; i < index; i++) {
            newItems[newStart + i] = items[start + i];
        }
        for (int i = index; i < size - 1; i++) {
            newItems[newStart + i] = items[start + i + 1];  // проверить на ошибку
        }
        items = newItems;
        start = newStart;
    }

    @Override
    public boolean remove(T element) {
        int index = indexOf(element);
        if (index >= 0) {
            remove(index);
            return true;
        }
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Not correct index");
        }
        return (T) items[start + index];
    }

    @Override
    public void set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Not correct index");
        }
        items[start + index] = element;
    }

    @Override
    public int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(items[start + i], element)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void clear() {
        Arrays.fill(items, null);
        size = 0;
    }

    @Override
    public void sort(Comparator<T> comparator) {

    }
}