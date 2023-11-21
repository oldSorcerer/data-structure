package io.sancta.sanctorum.structures.queue;

import io.sancta.sanctorum.structures.AbstractCollection;

public class SimplyQueue<T> extends AbstractCollection<T> implements Queue<T> {

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
    public void put(T element) {
        if (element == null) {
            throw new IllegalArgumentException("New element cannot be null");
        }

        Node<T> node = new Node<>();
        node.element = element;
        if (size == 0) {
            first = node;
        } else {
            last.next = node;
        }
        last = node;
        size++;
    }

    @Override
    public T peek() {
        if (size == 0) {
            return null;
        }
        return first.element;
    }

    @Override
    public T poll() {
        if (size == 0) {
            return null;
        }
        T retElement = first.element;
        first = first.next;
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
