package structures.tree;

class Node <T extends Comparable<T>>  {

    public T element;
    public Node<T> leftChild;
    public Node<T> rightChild;
    public Node<T> parent;

}
