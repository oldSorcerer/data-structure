package structures;

import java.util.Iterator;
import java.util.Random;

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

        Node<T> removeNode = findNode(element, false);

        if (removeNode == null)
            return false;
        else if (removeNode.leftChild == null && removeNode.rightChild == null) {
            if (removeNode == root )
                root = null;
            else if (removeNode.parent.leftChild == removeNode)
                removeNode.parent.leftChild = null;
            else removeNode.parent.rightChild = null;
        }
        else if (removeNode.leftChild != null && removeNode.rightChild == null) {
            if (removeNode == root) {
                root = removeNode.leftChild;
                root.parent = null;
            }
            else if (removeNode.parent.leftChild == removeNode) {
                removeNode.parent.leftChild = removeNode.leftChild;
                removeNode.leftChild.parent = removeNode.parent;
            }
            else {
                removeNode.parent.rightChild = removeNode.leftChild;
                removeNode.leftChild.parent = removeNode.parent;
            }
        }
        else if (removeNode.leftChild == null && removeNode.rightChild != null) {
            if (removeNode == root) {
                root = removeNode.rightChild;
                root.parent = null;
            }
            else if (removeNode.parent.leftChild == removeNode) {
                removeNode.parent.leftChild = removeNode.rightChild;
                removeNode.rightChild.parent = removeNode.parent;
            }
            else {
                removeNode.parent.rightChild = removeNode.rightChild;
                removeNode.rightChild.parent = removeNode.parent;
            }
        }
        else {
            if (removeNode == root) {
                if (new Random().nextBoolean()) {
                    root = removeNode.leftChild;
                    root.parent = null;
                    Node<T> right = mostChild(removeNode.leftChild, false);
                    right.rightChild = removeNode.rightChild;
                    removeNode.rightChild.parent = right;
                }
                else {
                    root = removeNode.rightChild;
                    root.parent = null;
                    Node<T> left = mostChild(removeNode.rightChild, true);
                    left.leftChild = removeNode.leftChild;
                    removeNode.leftChild.parent = left;
                }
            }
            else if (removeNode.parent.leftChild == removeNode) {
                if (new Random().nextBoolean()) {
                    removeNode.parent.leftChild = removeNode.leftChild;
                    removeNode.leftChild.parent = removeNode.parent;
                    Node<T> right = mostChild(removeNode.leftChild, false);
                    right.rightChild = removeNode.rightChild;
                    removeNode.rightChild.parent = right;
                }
                else {
                    removeNode.parent.leftChild = removeNode.rightChild;
                    removeNode.rightChild.parent = removeNode.parent;
                    Node<T> left = mostChild(removeNode.rightChild, true);
                    left.leftChild = removeNode.leftChild;
                    removeNode.leftChild.parent = left;
                }

            }
            else {
                if (new Random().nextBoolean()) {
                    removeNode.parent.rightChild = removeNode.leftChild;
                    removeNode.leftChild.parent = removeNode.parent;
                    Node<T> right = mostChild(removeNode.leftChild, false);
                    right.rightChild = removeNode.rightChild;
                    removeNode.rightChild.parent = right;
                }
                else {
                    removeNode.parent.rightChild = removeNode.rightChild;
                    removeNode.rightChild.parent = removeNode.parent;
                    Node<T> left = mostChild(removeNode.rightChild, true);
                    left.leftChild = removeNode.leftChild;
                    removeNode.leftChild.parent = left;
                }
            }
        }
        sizeTree--;
        return true;
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
            private Node<T> currentNode = mostChild(root, true);

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
                else currentNode = mostChild(currentNode.rightChild, true);

                counter++;
                return result.element;
            }
        };
    }

    private Node<T> mostChild(Node<T> current, boolean isLeft) {
        while (current != null && (isLeft ? current.leftChild : current.rightChild)  !=null) {
            current = isLeft ? current.leftChild : current.rightChild;
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
