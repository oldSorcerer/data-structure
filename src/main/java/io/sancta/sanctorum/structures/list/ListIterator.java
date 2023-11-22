package io.sancta.sanctorum.structures.list;

import java.util.Iterator;

public interface ListIterator<T> extends Iterator<T> {

    boolean hasNext();

    T next();

    boolean hasPrevious();

    int nextIndex();

    int previousIndex();

    void set(T t);

}
