package io.sancta.sanctorum.structures.list;

import io.sancta.sanctorum.structures.Utils;

public interface List<T> extends Iterable<T> {

    int size();

    void add(T element);

    void add(int index, T element);

    void remove(int index);

    boolean remove(T element);

    T get(int index);

    void set(int index, T element);

    int indexOf(T element);

    void clear();

    void sort(boolean back);

    default T min() {
        return Utils.extremum(this, false);
    }

    default T max() {
        return Utils.extremum(this, true);
    }
}
