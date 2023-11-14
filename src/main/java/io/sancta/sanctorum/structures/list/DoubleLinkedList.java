package io.sancta.sanctorum.structures.list;

import io.sancta.sanctorum.structures.AbstractCollection;
import io.sancta.sanctorum.structures.Utils;

import java.util.*;

public class DoubleLinkedList<T> extends AbstractCollection<T> implements List<T> {

    private Node<T> first;
    private Node<T> last;
    private int size;

    public boolean quickSort;

    public static class Node<T> {
        public T element;
        public Node<T> next;
        public Node<T> prev;
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
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Not correct index");
        }

        Node<T> node = new Node<>();
        node.element = element;

        if (size == 0) {
            first = node;
            last = node;
        } else if (size == index) {
            last.next = node;
            node.prev = last;
            last = node;
        } else if (index == 0) {
            first.prev = node;
            node.next = first;
            first = node;
        } else {
            Node<T> corruntNode = getNode(index);
            node.next = corruntNode;
            node.prev = corruntNode.prev;
            corruntNode.prev.next = node;
            corruntNode.prev = node;
        }
        size++;
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Not correct index");
        }

        if (size == 1) {
            first = null;
            last = null;
        } else if (size == 2) {
            if (index == 0) {
                first = last;
                last.prev = null;
            } else {
                last = first;
                first.next = null;
            }
        } else if (index == 0) {
            first = first.next;
            first.prev = null;
        } else if (index == size - 1) {
            last = last.prev;
            last.next = null;
        } else {
            Node<T> corruntNode = getNode(index);
            corruntNode.prev.next = corruntNode.next;
            corruntNode.next.prev = corruntNode.prev;
        }
        size--;
    }

    @Override
    public boolean remove(T element) {
        if (size == 0) {
            return false;
        }

        if (size == 1) {
            if (Objects.equals(first.element, element)) {
                first = null;
                last = null;
                size--;
                return true;
            }
            return false;
        }

        if (size == 2) {
            if (Objects.equals(first.element, element)) {
                first = last;
                last.prev = null;
                size--;
                return true;
            } else if (Objects.equals(last.element, element)) {
                last = first;
                first.next = null;
                size--;
                return true;
            } else {
                return false;
            }
        }

        if (Objects.equals(first.element, element)) {
            first = first.next;
            first.prev = null;
            size--;
            return true;
        } else {
            Node<T> previous = first;
            for (int i = 1; i < size; i++) {
                if (Objects.equals(previous.next.element, element)) {
                    previous.next = previous.next.next;
                    if (i == size - 1) {
                        last = previous;
                    } else {
                        previous.next.prev = previous;
                    }
                    size--;
                    return true;
                }
                previous = previous.next;
            }
        }
        return false;
    }

    @Override
    public T get(int index) {
        return getNode(index).element;
    }

    private Node<T> getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Not correct index");
        }

        Node<T> node;
        if (index < size / 2) {
            node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
        }
        return node;
    }

    @Override
    public void set(int index, T element) {
        Node<T> corruntNode = getNode(index);
        corruntNode.element = element;
    }

    @Override
    public int indexOf(T element) {
        Node<T> segment = first;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(segment.element, element))
                return i;
            segment = segment.next;
        }
        /*DoubleSegment<T> segmentFirst = firstSegment;
        DoubleSegment<T> segmentLast = lastSegment;
        for (int i = 0, j = sizeList - 1; i <= j; i++, j--) {
            if (Objects.equals(segmentFirst.element, element))
                return i;
            if (Objects.equals(segmentLast.element, element))
                return j;
            segmentFirst = segmentFirst.nextSegment;
            segmentLast = segmentLast.prevSegment;
        }*/
        return -1;
    }

    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }


    @Override
    public void sort(boolean back) {
        if (quickSort)
            sort(first, size, back);
        else {
            for (int i = size; i > 1; i--) {
                Node<T> segment = first;
                for (int j = 1; j < i; j++) {
                    if (Utils.compare(segment.element, segment.next.element, back)) {
                        T swap = segment.element;
                        segment.element = segment.next.element;
                        segment.next.element = swap;
                    }
                    segment = segment.next;
                }
            }
        }
    }

    private void sort(Node<T> begin, int size, boolean back) {

        if (size <= 1) {
            return;
        }
        Random random = new Random();

        Node<T> segment = begin;
        for (int i = random.nextInt(size); i > 0; i--) {
            segment = segment.next;
        }

        T tmp = segment.element;
        segment.element = begin.element;
        begin.element = tmp;
        segment = begin.next;
        int index = 0;

        Node<T> current = begin;

        for (int i = 1; i < size; i++) {
            if (Utils.compare(tmp, segment.element, back)) {
                current.element = segment.element;
                segment.element = current.next.element;
                current.next.element = tmp;
                current = current.next;
                index++;
            }
            segment = segment.next;
        }

        sortParts(begin, size, back, index, current);
    }

    private void sortParts(Node<T> begin, int size, boolean back, int idx, Node<T> current) {
        if (Runtime.getRuntime().availableProcessors() > 1) { // Проверка количество ядер процессора
            // Реализация многопоточности!
            Thread t = new Thread(() -> sort(begin, idx, back)); // Создаём поток и передаём ему лямбда-выражение
            // - что будет выполняться в этом потоке
            t.start(); // Запускаем фоновый поток
            sort(current.next, size - idx - 1, back); // В текущем потоке сортируем вторую часть списка
            try {
                t.join(); // Ждём завершения фонового потока, если первая часть списка оказалась больше второй
            } catch (InterruptedException e) {
                // Во время ожидания поток может быть прерван - это проверяемое исключение
            }
        } else {
            sort(begin, idx, back);
            sort(current.next, size - idx - 1, back);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator() {
            private Node<T> segment = first;

            @Override
            public boolean hasNext() {
                return segment != null;
            }

            @Override
            public T next() {
                if (segment == null)
                    throw new NoSuchElementException();
                T temp = segment.element;
                segment = segment.next;
                return temp;
            }
        };
    }
}
