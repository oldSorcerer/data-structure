package io.sancta.sanctorum.structures.list;

import io.sancta.sanctorum.structures.AbstractCollection;
import io.sancta.sanctorum.structures.Utils;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SingleLinkedList<T> extends AbstractCollection<T> implements List<T> {

    private Segment<T> firstSegment;
    private Segment<T> lastSegment;
    private int size;

    private static class Segment<T> {
        private T element;
        private Segment<T> nextSegment;
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

        Segment<T> newSegment = new Segment<>();
        newSegment.element = element;

        if (index == size) {
            if (size == 0) {
                firstSegment = newSegment;
            } else {
                lastSegment.nextSegment = newSegment;
            }

            lastSegment = newSegment;
            size++;
            return;
        }

        if (index == 0) {
            newSegment.nextSegment = firstSegment;
            firstSegment = newSegment;
            size++;
            return;
        }
        Segment<T> previous = firstSegment;
        for (int i = 1; i < index; i++) {
            previous = previous.nextSegment;
        }
        newSegment.nextSegment = previous.nextSegment;
        previous.nextSegment = newSegment;
        size++;
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Not correct index");

        if (size == 1) {
            firstSegment = null;
            lastSegment = null;
            size--;
            return;
        }
        if (size == 2) {
            if (index == 0)
                firstSegment = lastSegment;
            else {
                lastSegment = firstSegment;
                firstSegment.nextSegment = null;
            }
            size--;
            return;
        }
        if (index == 0) {
            firstSegment = firstSegment.nextSegment;
            size--;
            return;
        }
        Segment<T> previous = firstSegment;
        for (int i = 1; i < index; i++)
            previous = previous.nextSegment;
        previous.nextSegment = previous.nextSegment.nextSegment;

        if (index == size - 1)
            lastSegment = previous;
        size--;
    }

    @Override
    public boolean remove(T element) {

        if (size == 0)
            return false;

        if (size == 1) {
            return removeWhenOne(element);
        }

        if (size == 2) {
            return removeWhenTwo(element);
        }
        return findAndRemove(element);
    }

    private boolean removeWhenOne(T element) {
        if (Objects.equals(firstSegment.element, element)) {
            firstSegment = lastSegment = null;
            size--;
            return true;
        } else
            return false;
    }

    private boolean removeWhenTwo(T element) {
        if (Objects.equals(firstSegment.element, element)) {
            firstSegment = lastSegment;
            size--;
            return true;
        } else if (Objects.equals(lastSegment.element, element)) {
            lastSegment = firstSegment;
            firstSegment.nextSegment = null;
            size--;
            return true;
        } else
            return false;
    }

    private boolean findAndRemove(T element) {

        if (Objects.equals(firstSegment.element, element)) {
            firstSegment = firstSegment.nextSegment;
            size--;
            return true;
        }

        Segment<T> previous = firstSegment;
        for (int i = 1; i < size; i++) {
            if (Objects.equals(previous.nextSegment.element, element)) {
                previous.nextSegment = previous.nextSegment.nextSegment;
                if (i == size - 1)
                    lastSegment = previous;
                size--;
                return true;
            }
            previous = previous.nextSegment;
        }
        return false;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Not correct index");

        Segment<T> segment = firstSegment;
        for (int i = 0; i < index; i++)
            segment = segment.nextSegment;

        return segment.element;
    }

    @Override
    public void set(int index, T change) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Not correct index");

        Segment<T> segment = firstSegment;
        for (int i = 0; i < index; i++) {
            segment = segment.nextSegment;
        }
        segment.element = change;
    }

    @Override
    public int indexOf(T element) {
        Segment<T> segment = firstSegment;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(segment.element, element))
                return i;
            segment = segment.nextSegment;
        }
        return -1;
    }

    @Override
    public void clear() {
        firstSegment = null;
        lastSegment = null;
        size = 0;
    }

    @Override
    public void sort(boolean back) {
        for (int i = size; i > 1; i--) {
            Segment<T> segment = firstSegment;
            for (int j = 1; j < i; j++) {
                if (Utils.compare(segment.element, segment.nextSegment.element, back)) {
                    T swap = segment.element;
                    segment.element = segment.nextSegment.element;
                    segment.nextSegment.element = swap;
                }
                segment = segment.nextSegment;
            }
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {

            private Segment<T> segment = firstSegment;

            @Override
            public boolean hasNext() {
                return segment != null;
            }

            @Override
            public T next() {
                if (segment == null)
                    throw new NoSuchElementException();
                T temp = segment.element;
                segment = segment.nextSegment;
                return temp;
            }
        };
    }
}
