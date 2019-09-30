package structures;

import java.util.Iterator;

public interface IList <T> extends Iterable<T> {

    int getSizeList();
    void add(T elementToAdd);
    void add(T elementToAdd, int index);
    void remove(int index);
    boolean remove(T element);
    T get(int index);
    T set(T change, int index);
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
