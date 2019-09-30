package structures;

public interface ITree <T> extends Iterable<T> {

    int getSizeTree();
    boolean add(T element);
    boolean remove(T element);
    boolean contains(T element);

    T getRoot();
    T getLeftChild();
    T getRightChild();
    T getParent();

}
