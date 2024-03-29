package io.sancta.sanctorum.structures.tree;

public interface Tree<T> extends Iterable<T> {

    int getSize();

    boolean add(T element);

    boolean remove(T element);

    boolean contains(T element);

    T getRoot();
}
