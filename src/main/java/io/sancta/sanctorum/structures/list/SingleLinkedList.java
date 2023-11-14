package io.sancta.sanctorum.structures.list;

import io.sancta.sanctorum.structures.AbstractCollection;
import io.sancta.sanctorum.structures.Utils;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SingleLinkedList<T> extends AbstractCollection<T> implements List<T> {

    private Node<T> first;
    private Node<T> last;
    private int size;

    private static class Node<T> {
        private T element;
        private Node<T> next;
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

        if (index == size) {
            if (size == 0) {
                first = node;
            } else {
                last.next = node;
            }

            last = node;
            size++;
            return;
        }

        if (index == 0) {
            node.next = first;
            first = node;
            size++;
            return;
        }
        Node<T> previous = first;
        for (int i = 1; i < index; i++) {
            previous = previous.next;
        }
        node.next = previous.next;
        previous.next = node;
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
            size--;
            return;
        }
        if (size == 2) {
            if (index == 0) {
                first = last;
            } else {
                last = first;
                first.next = null;
            }
            size--;
            return;
        }
        if (index == 0) {
            first = first.next;
            size--;
            return;
        }
        Node<T> previous = first;
        for (int i = 1; i < index; i++) {
            previous = previous.next;
        }

        previous.next = previous.next.next;

        if (index == size - 1) {
            last = previous;
        }
        size--;
    }

    @Override
    public boolean remove(T element) {

        if (size == 0) {
            return false;
        }

        if (size == 1) {
            return removeWhenOne(element);
        }

        if (size == 2) {
            return removeWhenTwo(element);
        }

        return findAndRemove(element);
    }

    private boolean removeWhenOne(T element) {
        if (Objects.equals(first.element, element)) {
            first = null;
            last = null;
            size--;
            return true;
        } else {
            return false;
        }
    }

    private boolean removeWhenTwo(T element) {
        if (Objects.equals(first.element, element)) {
            first = last;
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

    private boolean findAndRemove(T element) {

        if (Objects.equals(first.element, element)) {
            first = first.next;
            size--;
            return true;
        }

        Node<T> previous = first;
        for (int i = 1; i < size; i++) {
            if (Objects.equals(previous.next.element, element)) {
                previous.next = previous.next.next;
                if (i == size - 1)
                    last = previous;
                size--;
                return true;
            }
            previous = previous.next;
        }
        return false;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Not correct index");
        }

        Node<T> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }

        return node.element;
    }

    @Override
    public void set(int index, T change) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Not correct index");
        }

        Node<T> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        node.element = change;
    }

    @Override
    public int indexOf(T element) {
        Node<T> node = first;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(node.element, element)) {
                return i;
            }
            node = node.next;
        }
        return -1;
    }

    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    public boolean contains(T element){
        return indexOf(element) >= 0;
    }

    @Override
    public void sort(boolean back) {
        for (int i = size; i > 1; i--) {
            Node<T> node = first;
            for (int j = 1; j < i; j++) {
                if (Utils.compare(node.element, node.next.element, back)) {
                    T swap = node.element;
                    node.element = node.next.element;
                    node.next.element = swap;
                }
                node = node.next;
            }
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {

            private Node<T> node = first;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (node == null)
                    throw new NoSuchElementException();
                T temp = node.element;
                node = node.next;
                return temp;
            }
        };
    }
}
