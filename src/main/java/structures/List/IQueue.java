package structures.List;

public interface IQueue<T> {

    void put(T elementToAdd);

    T peek();

    T poll();
}
