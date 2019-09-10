package structures;

import java.util.Objects;

public class DoubleLinkedList <T> implements IList <T> {

    private DoubleSegment<T> firstSegment;
    private DoubleSegment<T> lastSegment;
    private int sizeList;

    @Override
    public int getSizeList() {
        return sizeList;
    }

    @Override
    public void add(T elementToAdd) {
        add(elementToAdd, sizeList);
    }

    @Override
    public void add(T elementToAdd, int index) {
        if (index < 0 || index > sizeList)
            throw new IllegalArgumentException("Not correct index");

        DoubleSegment<T> newSegment = new DoubleSegment<>();
        newSegment.element = elementToAdd;

        if (sizeList == 0)
            firstSegment = lastSegment = newSegment;

        else if (sizeList == index){
            lastSegment.nextSegment = newSegment;
            newSegment.prevSegment = lastSegment;
            lastSegment = newSegment;
        }
        else if (index == 0){
            firstSegment.prevSegment = newSegment;
            newSegment.nextSegment = firstSegment;
            firstSegment = newSegment;
        }
        else {
            DoubleSegment<T> next = getSegment(index);
            newSegment.nextSegment = next;
            newSegment.prevSegment = next.prevSegment;
            next.prevSegment.nextSegment = newSegment;
            next.prevSegment = newSegment;
        }
        sizeList++;
    }

    @Override
    public void remove(int index) {

        if (index < 0 || index >= sizeList)
            throw new IllegalArgumentException("Not correct index");
        if (sizeList == 1) {
            firstSegment = null;
            lastSegment = null;
        }
        else if (sizeList == 2) {
            if (index == 0) {
                firstSegment = lastSegment;
                lastSegment.prevSegment = null;
            }
            else {
                lastSegment = firstSegment;
                firstSegment.nextSegment = null;
            }
        }
        else if (index == 0) {
            firstSegment = firstSegment.nextSegment;
            firstSegment.prevSegment = null;
        }
        else if (index == sizeList - 1 ) {
            lastSegment = lastSegment.prevSegment;
            lastSegment.nextSegment = null;
        }
        else {
            DoubleSegment<T> segment = getSegment(index);
            segment.prevSegment.nextSegment = segment.nextSegment;
            segment.nextSegment.prevSegment = segment.prevSegment;
        }
        sizeList--;
    }

    @Override
    public boolean remove(T element) {

        if (sizeList == 0)
            return false;
        if (sizeList == 1) {
            if (Objects.equals(firstSegment.element, element)) {
                firstSegment = null;
                lastSegment = null;
                sizeList--;
                return true;
            }
            else return false;
        }

        if (sizeList == 2)
            if (Objects.equals(firstSegment.element, element)) {
                firstSegment = lastSegment;
                lastSegment.prevSegment = null;
                sizeList--;
                return true;
            }
            else if (Objects.equals(lastSegment.element, element)) {
                lastSegment = firstSegment;
                firstSegment.nextSegment = null;
                sizeList--;
                return true;
            }
            else return false;

        if (Objects.equals(firstSegment.element, element)) {
            firstSegment = firstSegment.nextSegment;
            firstSegment.prevSegment = null;
            sizeList--;
            return true;
        }
        else {
            DoubleSegment<T> previous = firstSegment;
            for (int i = 1; i < sizeList ; i++) {
                if (Objects.equals(previous.nextSegment.element, element)) {
                    previous.nextSegment = previous.nextSegment.nextSegment;
                    if (i == sizeList - 1)
                        lastSegment = previous;
                    else
                        previous.nextSegment.prevSegment = previous;
                    sizeList--;
                    return true;
                }
                previous = previous.nextSegment;
            }
        }
        return false;
    }

    @Override
    public T get(int index) {
        return getSegment(index).element;
    }

    private DoubleSegment<T> getSegment(int index) {
        if (index < 0 || index >= sizeList)
            throw new IllegalArgumentException("Not correct index");

        if (index < sizeList/2) {
            DoubleSegment<T> segment = firstSegment;
            for (int i = 0; i < index; i++)
                segment = segment.nextSegment;
            return segment;
        }
        else{
            DoubleSegment<T> segment = lastSegment;
            for (int i = sizeList-1; i > index ; i--)
                segment = segment.prevSegment;
            return segment;
        }
    }

    @Override
    public T set(T change, int index) {
        DoubleSegment<T> segment = getSegment(index);
        T retElement = segment.element;
        segment.element = change;
        return retElement;
    }

    @Override
    public int indexOf(T element) {
        DoubleSegment<T> segment = firstSegment;
        for (int i = 0; i < sizeList; i++) {
            if (Objects.equals(segment.element, element))
                return i;
            segment = segment.nextSegment;
        }
        /*DoubleSegment<T> segmentFirst = firstSegment;
        DoubleSegment<T> segmentLast = lastSegment;
        for (int i = 0, j = sizeList - 1; i <= j; i++, j--) {
            if (Objects.equals(segmentFirst.element, element))
                return i;
            if (Objects.equals(segmentLast.element, element))
                return j;
            segmentFirst = segmentFirst.nextSegment;
            segmentLast = segmentLast.prevSegment;
        }*/
        return -1;
    }

    @Override
    public void clear() {
        firstSegment = null;
        lastSegment = null;
        sizeList = 0;
    }
}
