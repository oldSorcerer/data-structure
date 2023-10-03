package structures.tree;

import java.util.Iterator;
import java.util.Random;

public class SimpleTree<T extends Comparable<T>> implements Tree<T> {

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
        } else if (element.compareTo(parent.element) > 0) {
            parent.rightChild = new Node<T>();
            parent.rightChild.parent = parent;
            parent.rightChild.element = element;
            sizeTree++;
            return true;
        } else if (element.compareTo(parent.element) < 0) {
            parent.leftChild = new Node<T>();
            parent.leftChild.parent = parent;
            parent.leftChild.element = element;
            sizeTree++;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean remove(T element) {

        Node<T> removeNode = findNode(element, false);

        if (removeNode == null) {
            return false;
        } else if (removeNode.leftChild == null && removeNode.rightChild == null) {
            if (removeNode == root) {
                root = null;
            } else if (removeNode.parent.leftChild == removeNode) {
                removeNode.parent.leftChild = null;
            } else {
                removeNode.parent.rightChild = null;
            }
        } else if (removeNode.leftChild != null && removeNode.rightChild == null) {
            removeWithOneChild(removeNode, true);
        } else if (removeNode.leftChild == null && removeNode.rightChild != null) {
            removeWithOneChild(removeNode, false);
        } else {
            if (removeNode == root) {
                if (new Random().nextBoolean()) {
                    root = removeNode.leftChild;
                    root.parent = null;
                    hangRight(removeNode);
                } else {
                    root = removeNode.rightChild;
                    root.parent = null;
                    hangLeft(removeNode);
                }
            } else if (removeNode.parent.leftChild == removeNode) {
                removeBothChildren(removeNode, true);
            } else {
                removeBothChildren(removeNode, false);
            }
        }
        sizeTree--;
        return true;
    }

    private void removeBothChildren(Node<T> removeNode, boolean isLeft) {
        if (new Random().nextBoolean()) {
            if (isLeft) {
                removeNode.parent.leftChild = removeNode.leftChild;
            } else {
                removeNode.parent.rightChild = removeNode.leftChild;
            }

            removeNode.leftChild.parent = removeNode.parent;
            hangRight(removeNode);
        } else {
            if (isLeft) {
                removeNode.parent.leftChild = removeNode.rightChild;
            } else {
                removeNode.parent.rightChild = removeNode.rightChild;
            }
            removeNode.rightChild.parent = removeNode.parent;
            hangLeft(removeNode);
        }
    }

    private void hangLeft(Node<T> removeNode) {
        Node<T> left = mostChild(removeNode.rightChild, true);
        left.leftChild = removeNode.leftChild;
        removeNode.leftChild.parent = left;
    }

    private void hangRight(Node<T> removeNode) {
        Node<T> right = mostChild(removeNode.leftChild, false);
        right.rightChild = removeNode.rightChild;
        removeNode.rightChild.parent = right;
    }

    private void removeWithOneChild(Node<T> removeNode, boolean isLeft) {
        if (removeNode == root) {
            root = (isLeft ? removeNode.leftChild : removeNode.rightChild);
            root.parent = null;
        } else if (removeNode.parent.leftChild == removeNode) {
            removeNode.parent.leftChild = (isLeft ? removeNode.leftChild : removeNode.rightChild);
            (isLeft ? removeNode.leftChild : removeNode.rightChild).parent = removeNode.parent;
        } else {
            removeNode.parent.rightChild = (isLeft ? removeNode.leftChild : removeNode.rightChild);
            (isLeft ? removeNode.leftChild : removeNode.rightChild).parent = removeNode.parent;
        }
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
                    } else {
                        Node<T> current = currentNode.parent;

                        while (current != null && current.parent != null && current == current.parent.rightChild) {
                            current = current.parent;
                        }
                        if (current != null) {
                            current = current.parent;
                        }
                        currentNode = current;
                    }
                } else {
                    currentNode = mostChild(currentNode.rightChild, true);
                }

                counter++;
                return result.element;
            }
        };
    }

    private Node<T> mostChild(Node<T> current, boolean isLeft) {
        while (current != null && (isLeft ? current.leftChild : current.rightChild) != null) {
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
            } else if (element.compareTo(current.element) < 0) {
                parentNode = current;
                current = current.leftChild;
            } else return current;
        } while (current != null);

        return parent ? parentNode : null;
    }
}
