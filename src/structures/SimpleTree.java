package structures;

import java.util.Iterator;

public class SimpleTree<T extends Comparable<T>> implements ITree<T> {

    private Node<T> root;
    private int sizeTree;

    @Override
    public int getSizeTree() {
        return sizeTree;
    }

    @Override
    public boolean add(T element) {

        Node<T> parent = findNode(element, true);

        if (parent == null) {
            root = new Node<T>();
            root.element = element;
            sizeTree++;
            return true;
        }
        else if (element.compareTo(parent.element) > 0) {
            parent.rightChild = new Node<T>();
            parent.rightChild.parent = parent;
            parent.rightChild.element = element;
            sizeTree++;
            return true;
        }
        else if (element.compareTo(parent.element) < 0) {
            parent.leftChild = new Node<T>();
            parent.leftChild.parent = parent;
            parent.leftChild.element = element;
            sizeTree++;
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean remove(T element) {
        return false;
    }

    @Override
    public boolean contains(T element) {
        return findNode(element, false) != null;
    }

    @Override
    public T getRoot() {
        return root.element;
    }

    @Override
    public T getLeftChild() {
        return null;
    }

    @Override
    public T getRightChild() {
        return null;
    }

    @Override
    public T getParent() {
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        SingleLinkedList<T> list = new SingleLinkedList<>();
        addNode(list, root);

        return list.iterator();
    }

    private Node<T> findNode(T element, boolean parent) {
        if (root == null)
            return null;

        Node<T> current = root;
        Node<T> parentNode = null;

        do {
            if (element.compareTo(current.element) > 0) {
                parentNode = current;
                current = current.rightChild;
            }
            else  if ( element.compareTo(current.element) < 0) {
                parentNode = current;
                current = current.leftChild;
            }
            else return current;
        } while (current != null);

        return parent ? parentNode : null;
    }

    private void addNode(SingleLinkedList<T> list, Node<T> node) {
        if (node == null)
            return;
        addNode(list, node.leftChild);
        list.add(node.element);
        addNode(list, node.rightChild);
    }
}
