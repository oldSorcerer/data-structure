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
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int counter = 0;
            private Node<T> currentNode = mostLeftChild(root);

            @Override
            public boolean hasNext() {
                return counter < sizeTree;
            }

            @Override
            public T next() {
                Node<T> result = currentNode;

                if (currentNode.rightChild == null) {
                    if (currentNode.parent != null && currentNode.parent.leftChild == currentNode) {
                        currentNode = currentNode.parent;
                    }
                    else {
                        Node<T> current = currentNode.parent;

                        while (current != null && current.parent != null && current == current.parent.rightChild) {
                            current = current.parent;
                        }
                        if (current != null)
                            current = current.parent;
                        currentNode = current;
                    }
                }
                else currentNode = mostLeftChild(currentNode.rightChild);

                counter++;
                return result.element;
            }
        };
    }

    private Node<T> mostLeftChild(Node<T> current) {
        while (current != null && current.leftChild !=null) {
            current = current.leftChild;
        }
        return current;
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
}
