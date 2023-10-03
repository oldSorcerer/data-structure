package io.sancta.sanctorum.structures.list;

import io.sancta.sanctorum.structures.AbstractCollection;

public class Stack <T> extends AbstractCollection<T> {

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

    public void push(T elementToAdd){
        if (elementToAdd == null) {
            throw new IllegalArgumentException("New element cannot be null");
        }
        Segment<T> newSegment = new Segment<>();
        newSegment.element = elementToAdd;
        newSegment.nextSegment = lastSegment;
        lastSegment = newSegment;
        size++;
    }

    public T pop(){
        if (size == 0)
            return null;

        T retElement = lastSegment.element;
        lastSegment = lastSegment.nextSegment;
        size--;
        return retElement;
    }

    public T peek(){
        if (size == 0) {
            return null;
        }
        return lastSegment.element;
    }
}
