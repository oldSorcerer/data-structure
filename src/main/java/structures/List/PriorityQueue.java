package structures.List;

import structures.AbstractCollection;
import structures.Utils;

public class PriorityQueue <T extends Comparable <T>> extends AbstractCollection<T> implements IQueue<T> {

    private final boolean min;
    private final LinearList<T> list = new LinearList<>();

    public PriorityQueue(boolean min) {
        this.min = min;
    }

    private int leftChild(int number){
        number = 2 * number + 1;
        if (number >= list.size())
            return -1;
        return number;
    }

    private int rightChild(int number) {
        number = 2 * number + 2;
        if (number >= list.size())
            return -1;
        return number;
    }

    private int parent(int number) {
        if (number == 0)
            return -1;
        return (number - 1) / 2;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void put(T element) {
        list.add(element);
        int index = list.size() - 1;

        while (parent(index) >= 0 && Utils.compare(list.get(index), list.get(parent(index)), min)) {
            list.set(index, list.get(parent(index)));
            list.set(parent(index), element);
            index = parent(index);
        }
    }

    @Override
    public T peek() {
        return list.get(0);
    }

    @Override
    public T poll() {
        T element = list.get(0);
        list.set(0, list.get(list.size() - 1));
        list.remove(list.size() - 1);
        int index = 0;

        while ( (leftChild(index) >= 0 && Utils.compare(list.get(leftChild(index)), list.get(index), min) )  ||
                (rightChild(index) >= 0 && Utils.compare(list.get(rightChild(index)), list.get(index), min) )   ) {
            // Меняем на правого ребёнка, когда есть правый ребёнок, который приоритетнее, чем левый
            // иначе меняем на левого
            if (rightChild(index) >= 0 && Utils.compare( list.get(rightChild(index)), list.get(leftChild(index)), min)) {
                T tmp = list.get(index);
                list.set(index, list.get(rightChild(index)));
                list.set(rightChild(index), tmp);
                index = rightChild(index);
            }
            else {
                T tmp = list.get(index);
                list.set(index, list.get(leftChild(index)));
                list.set(leftChild(index), tmp);
                index = leftChild(index);
            }
        }
        return element;
    }
}
