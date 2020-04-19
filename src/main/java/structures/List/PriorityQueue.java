package structures.List;

import structures.Utils;

public class PriorityQueue <T extends Comparable <T>> implements IQueue<T> {

    private boolean min;
    private LinearList<T> list = new LinearList<>();

    public PriorityQueue(boolean min) {
        this.min = min;
    }

    private int leftChild(int number){
        number = 2 * number + 1;
        if (number >= list.getSizeList())
            return -1;
        return number;
    }

    private int rightChild(int number) {
        number = 2 * number + 2;
        if (number >= list.getSizeList())
            return -1;
        return number;
    }

    private int parent(int number) {
        if (number == 0)
            return -1;
        return (number - 1) / 2;
    }

    @Override
    public int getSizeQueue() {
        return list.getSizeList();
    }

    @Override
    public void put(T elementToAdd) {
        list.add(elementToAdd);
        int idx = list.getSizeList() - 1;

        while (parent(idx) >= 0 && Utils.compare(list.get(idx), list.get(parent(idx)), min)) {
            list.set(list.get(parent(idx)), idx);
            list.set(elementToAdd, parent(idx));
            idx = parent(idx);
        }
    }

    @Override
    public T firstElement() {
        return list.get(0);
    }

    @Override
    public T get() {
        T element = list.get(0);
        list.set(list.get(list.getSizeList() - 1), 0);
        list.remove(list.getSizeList() - 1);
        int idx = 0;

        while ( (leftChild(idx) >= 0 && Utils.compare(list.get(leftChild(idx)), list.get(idx), min) )  ||
                (rightChild(idx) >= 0 && Utils.compare(list.get(rightChild(idx)), list.get(idx), min) )   ) {
            // Меняем на правого ребёнка, когда есть правый ребёнок, который приоритетнее, чем левый
            // иначе меняем на левого
            if (rightChild(idx) >= 0 && Utils.compare( list.get(rightChild(idx)), list.get(leftChild(idx)), min)) {
                T tmp = list.get(idx);
                list.set(list.get(rightChild(idx)), idx);
                list.set(tmp, rightChild(idx));
                idx = rightChild(idx);
            }
            else {
                T tmp = list.get(idx);
                list.set(list.get(leftChild(idx)), idx);
                list.set(tmp, leftChild(idx));
                idx = leftChild(idx);
            }
        }

        return element;
    }
}
