package structures;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SingleLinkedList <T> implements IList <T> {

    private Segment<T> firstSegment;
    private Segment<T> lastSegment;
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

        Segment <T> newSegment = new Segment<>();
        newSegment.element = elementToAdd;

        if (index == sizeList) {
          if (sizeList == 0)
                firstSegment = newSegment;
            else
                lastSegment.nextSegment = newSegment;

            lastSegment = newSegment;
            sizeList++;
            return;
        }

        if (index == 0){
            newSegment.nextSegment = firstSegment;
            firstSegment = newSegment;
            sizeList++;
            return;
        }
        Segment<T> previous = firstSegment;
        for (int i = 1; i < index ; i++) {
            previous = previous.nextSegment;
        }
        newSegment.nextSegment = previous.nextSegment;
        previous.nextSegment = newSegment;
        sizeList++;
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= sizeList)
            throw new IllegalArgumentException("Not correct index");
        if (sizeList == 1){
            firstSegment = null;
            lastSegment = null;
            sizeList--;
            return;
        }
        if (sizeList == 2){
            if (index == 0)
                firstSegment = lastSegment;
            else {
                lastSegment = firstSegment;
                firstSegment.nextSegment = null;
            }
             sizeList--;
            return;
        }
        if (index == 0){
            firstSegment = firstSegment.nextSegment;
            sizeList--;
          return;
        }
        Segment <T> previous = firstSegment;
        for (int i = 1; i < index ; i++)
            previous = previous.nextSegment;
        previous.nextSegment = previous.nextSegment.nextSegment;

        if (index == sizeList-1)
            lastSegment = previous;
        sizeList--;
    }

    @Override
    public boolean remove(T element) {

        if (sizeList == 0)
            return false;

        if (sizeList == 1){
            return removeWhenOne(element);
        }

        if (sizeList == 2){
            return removeWhenTwo(element);
        }
        return findAndRemove(element);
    }

    private boolean removeWhenOne(T element) {
        if (Objects.equals(firstSegment.element,element)){
            firstSegment = lastSegment = null;
            sizeList--;
            return true;
        }
        else
            return false;
    }

    private boolean findAndRemove(T element) {

        if (Objects.equals(firstSegment.element, element)){
            firstSegment = firstSegment.nextSegment;
            sizeList--;
            return true;
        }

        Segment<T> previous = firstSegment;
        for (int i = 1; i <sizeList ; i++) {
            if (Objects.equals(previous.nextSegment.element, element)){
                previous.nextSegment = previous.nextSegment.nextSegment;
                if (i == sizeList - 1)
                    lastSegment = previous;
                sizeList--;
                return  true;
            }
            previous = previous.nextSegment;
        }
        return false;
    }

    private boolean removeWhenTwo(T element) {
        if (Objects.equals(firstSegment.element, element)){
            firstSegment = lastSegment;
            sizeList--;
            return true;
        }
        else if (Objects.equals(lastSegment.element, element)){
            lastSegment = firstSegment;
            firstSegment.nextSegment = null;
            sizeList--;
            return true;
        }
        else
            return false;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= sizeList)
            throw new IllegalArgumentException("Not correct index");

        Segment<T> segment = firstSegment;
        for(int i = 0; i < index; i++)
            segment = segment.nextSegment;

        return segment.element;
    }

    @Override
    public T set(T change, int index) {
        if (index < 0 || index >= sizeList)
            throw new IllegalArgumentException("Not correct index");

        Segment<T> segment = firstSegment;
        for(int i = 0; i < index; i++)
            segment = segment.nextSegment;

        T retElement = segment.element;
        segment.element = change;
        return retElement;
    }

    @Override
    public int indexOf(T element) {
        Segment<T> segment = firstSegment;
        for (int i = 0; i < sizeList; i++) {
            if (Objects.equals(segment.element, element))
                return i;
            segment = segment.nextSegment;
        }
        return -1;
    }

    @Override
    public void clear() {
        firstSegment = null;
        lastSegment = null;
        sizeList = 0;

    }

    @Override
    public void sort(boolean back) {

        for (int i = sizeList ; i > 1 ; i--) {
            Segment<T> segment = firstSegment;
            for (int j = 1; j < i; j++) {
                if (Utils.compare(segment.element, segment.nextSegment.element, back)) {
                    T swap = segment.element;
                    segment.element = segment.nextSegment.element;
                    segment.nextSegment.element = swap;
                }
                segment = segment.nextSegment;
            }
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator() {

            private Segment<T> segment = firstSegment;

            @Override
            public boolean hasNext() {
                return segment != null;
            }

            @Override
            public T next() {
                if (segment == null)
                    throw new NoSuchElementException();
                T temp = segment.element;
                segment = segment.nextSegment;
                return temp;
            }
        };
    }
}
