package structures;

import java.util.Objects;

public class LinearList<T> implements IList<T> {

    private Object[] items;
    private int sizeList;
    private int start;

    public LinearList() {
        items = new Object[8];
    }
    public LinearList(int capacity) {
        if (capacity < 1)
            throw new IllegalArgumentException("Capacity must be greater than zero");
        items = new Object[capacity];
    }

    @Override
    public int getSizeList() {
        return sizeList;
    }

    @Override
    public void add(T elementToAdd) {
        add(elementToAdd, sizeList);
    }

    @Override
    public void add(T elementToAdd, int index) {
        if (index < 0 || index > sizeList)
            throw new IllegalArgumentException("Not correct index");

        if (sizeList == 0)
            items[start] = elementToAdd;

        else if (sizeList < items.length) { // Резерв есть!
            if (index <= sizeList / 2) { // Вставляем в первую половну списка
                if (start == 0) { // Нет резерва в начале массива
                    // Добавить резерв слева, вставить элемент слева и весь список передвинуть вправо
                    addLeftShift(elementToAdd,index);
                }
                else { // Есть резерв в начале массива
                    // Вставляем элемент влево и все что левее смещаем влево
                    addLeft(elementToAdd, index);
                }
            }
            else { // Вставляем во вторую половину списка
                if (start + sizeList < items.length) { // Есть резерв в конце массива
                    // Вставляем элемент вправо и остаток списка двигаем вправо
                    addRight(elementToAdd, index);
                }
                else { // Нет резерва в конце массива
                    // Вставляем элемент слева, всё что левее его смещаем влево
                    addRightShift(elementToAdd, index);
                }
            }
        }
        else // Резерва нет!! пересоздаение массива!
            recreateItems(elementToAdd, index);

        sizeList++;
    }

    private void addLeftShift (T elementToAdd, int index) {
        start = (items.length - sizeList) / 2;

        for (int i = sizeList - 1; i >= index ; i--)
            items[start + i + 1] = items[i];

        items[start + index] = elementToAdd;

        for (int i = index; i >= 0 ; i--)
            items[start + i] = items[i];
    }

    private void addLeft (T elementToAdd, int index) {
        for (int i = 0; i < index ; i++)
            items[start + i - 1] = items[start + i];
        start--;
        items[start + index] = elementToAdd;
    }

    private void addRight (T elementToAdd, int index) {
        for (int i = sizeList; i > index ; i--)
            items[start + i] = items[start + i - 1];
        items[start + index] = elementToAdd;
    }

    private void addRightShift (T elementToAdd, int index) {
        int newStart = start / 2;
        for (int i = 0; i < index ; i++)
            items[newStart + i] = items[start + i];
        items[newStart + index] = elementToAdd;

        if (newStart != start - 1) {
            for (int i = index; i < sizeList; i++)
                items[newStart + i + 1] = items[start + i];
        }
        start = newStart;
    }

    private void recreateItems(T elementToAdd, int index) {
        int newStart = start;

        if (newStart == 0 && index <= sizeList / 2)
            newStart = (items.length * 2 - sizeList) / 2;

        Object[] newItems = new Object[items.length * 2];
        for (int i = 0; i < index; i++)
            newItems[newStart + i] = items[start + i];

        newItems[newStart + index] = elementToAdd;

        for (int i = index; i < sizeList ; i++)
            newItems[newStart + i + 1] = items[start + i];

        items = newItems;
        start = newStart;
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= sizeList)
            throw new IllegalArgumentException("Not correct index");

        if (items.length > sizeList * 4) {
            recreateItems(index);
        }
        else {
            if (index <= sizeList / 2) {
                for (int i = index; i > 0 ; i--)
                    items[start + i] = items[start + i - 1];
                start++;
            }
            else {
                for (int i = index; i < sizeList - 1 ; i++)
                    items[start + i] = items[start + i + 1];
            }
        }
        sizeList--;
    }

    private void recreateItems(int index) {
        int newStart = start;

        if (newStart == 0)
            newStart = (items.length / 2 - sizeList) / 2;

        Object[] newItems = new Object[items.length / 2];
        for (int i = 0; i < index; i++)
            newItems[newStart + i] = items[start + i];

        for (int i = index; i < sizeList - 1; i++)
            newItems[newStart + i] = items[start + i + 1];

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
    public T get(int index) {
        if (index < 0 || index >= sizeList)
            throw new IllegalArgumentException("Not correct index");

        return (T) items[start + index];
    }

    @Override
    public T set(T change, int index) {
        if (index < 0 || index >= sizeList)
            throw new IllegalArgumentException("Not correct index");

        T retElement = (T) items[start + index];
        items[start + index] = change;
        return retElement;
    }

    @Override
    public int indexOf(T element) {
        for (int i = 0; i < sizeList ; i++) {
            if (Objects.equals(items[start + i], element))
                return i;
        }
        return -1;
    }
}
