package io.sancta.sanctorum.structures;

public class Stack <T> extends AbstractCollection<T> {

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

    public void push(T element){
        if (element == null) {
            throw new IllegalArgumentException("New element cannot be null");
        }
        Node<T> node = new Node<>();
        node.element = element;
        node.next = last;
        last = node;
        size++;
    }

    public T pop(){
        if (size == 0) {
            return null;
        }

        T element = last.element;
        last = last.next;
        size--;
        return element;
    }

    public T peek(){
        if (size == 0) {
            return null;
        }
        return last.element;
    }
}
