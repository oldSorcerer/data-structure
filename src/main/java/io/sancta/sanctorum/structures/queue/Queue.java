package io.sancta.sanctorum.structures.queue;

public interface Queue<T> {

    void put(T elementToAdd);

    T peek();

    T poll();
}
