package io.sancta.sanctorum.structures.list;

import io.sancta.sanctorum.structures.AbstractCollection;

import java.util.*;
import java.util.function.Consumer;

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
        checkPositionIndex(index);

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

    public void addAll(Collection<? extends T> collection) {
        addAll(size, collection);
    }


    public void addAll(int index, Collection<? extends T> collection) {
        checkPositionIndex(index);
    }

    @Override
    public void remove(int index) {
        checkElementIndex(index);

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
        checkElementIndex(index);

        Node<T> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }

        return node.element;
    }

    @Override
    public void set(int index, T element) {
        checkElementIndex(index);

        Node<T> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        node.element = element;
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

    public boolean contains(T element) {
        return indexOf(element) >= 0;
    }

    public Object[] toArray() {
        Object[] result = new Object[size];
        Node<T> node = first;
        for (int i = 0; i < size; i++) {
            result[i] = node.element;
            node = node.next;
        }
        return result;
    }

    @Override
    public void sort(Comparator<T> comparator) {

        Object[] array = this.toArray();
        Arrays.sort(array, (Comparator) comparator);

        ListIterator<T> iterator = this.listIterator();

        for (Object object : array) {
            iterator.next();
            iterator.set((T) object);
        }
    }

    /* дополниельный код */

    int modCount = 0;

    Node<T> node(int index) {

        Node<T> node = first;
        for (int i = 0; i < index; i++)
            node = node.next;
        return node;
    }

    public ListIterator<T> listIterator() {
        return listIterator(0);
    }

    public ListIterator<T> listIterator(int index) {
        checkPositionIndex(index);
        return new ListItr(index);
    }

    private class ListItr implements ListIterator<T> {
        private Node<T> lastReturned;
        private Node<T> next;
        private int nextIndex;
        private int expectedModCount = modCount;

        ListItr(int index) {
            // assert isPositionIndex(index);
            next = (index == size) ? null : node(index);
            nextIndex = index;
        }

        public boolean hasNext() {
            return nextIndex < size;
        }

        public T next() {
            checkForComodification();
            if (!hasNext())
                throw new NoSuchElementException();

            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.element;
        }

        public boolean hasPrevious() {
            return nextIndex > 0;
        }

        public int nextIndex() {
            return nextIndex;
        }

        public int previousIndex() {
            return nextIndex - 1;
        }

        public void set(T e) {
            if (lastReturned == null)
                throw new IllegalStateException();
            checkForComodification();
            lastReturned.element = e;
        }

        public void forEachRemaining(Consumer<? super T> action) {
            Objects.requireNonNull(action);
            while (modCount == expectedModCount && nextIndex < size) {
                action.accept(next.element);
                lastReturned = next;
                next = next.next;
                nextIndex++;
            }
            checkForComodification();
        }

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }


    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
}
