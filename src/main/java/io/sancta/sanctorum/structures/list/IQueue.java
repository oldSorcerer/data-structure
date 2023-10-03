package io.sancta.sanctorum.structures.list;

public interface IQueue<T> {

    void put(T elementToAdd);

    T peek();

    T poll();
}
