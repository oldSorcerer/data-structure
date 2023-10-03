package io.sancta.sanctorum.structures.queue;

import io.sancta.sanctorum.structures.AbstractCollection;

public class SimplyQueue<T> extends AbstractCollection<T> implements Queue<T> {

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
    public void put(T elementToAdd) {

        if (elementToAdd == null) {
            throw new IllegalArgumentException("New element cannot be null");
        }
        Segment<T> newSegment = new Segment<>();
        newSegment.element = elementToAdd;
        if (size == 0) {
            firstSegment = newSegment;
        } else {
            lastSegment.nextSegment = newSegment;
        }
        lastSegment = newSegment;
        size++;
    }

    @Override
    public T peek() {
        if (size == 0) {
            return null;
        }
        return firstSegment.element;
    }

    @Override
    public T poll() {
        if (size == 0) {
            return null;
        }
        T retElement = firstSegment.element;
        firstSegment = firstSegment.nextSegment;
        size--;
        return retElement;
    }

//    public T lastElement(){
//        if (size == 0) {
//            return null;
//        }
//        return lastSegment.element;
//    }
}