package structures;

public class Queue <T> {

    private Segment<T> firstSegment;
    private Segment<T> lastSegment;
    private int sizeQueue;

    public int getSizeQueue() {
        return sizeQueue;
    }

    public void put(T elementToAdd){
        if (elementToAdd == null)
            throw new IllegalArgumentException("New element cannot be null");

        Segment<T> newSegment = new Segment<>();
        newSegment.element = elementToAdd;
        if (sizeQueue == 0)
            firstSegment = newSegment;
        else
            lastSegment.nextSegment = newSegment;

        lastSegment = newSegment;
        sizeQueue++;
    }

    public T firstElement(){
        if (sizeQueue == 0)
            return null;
        return firstSegment.element;
    }

    public T lastElement(){
        if (sizeQueue == 0)
            return null;
        return lastSegment.element;
    }

    public T get(){
        if (sizeQueue == 0)
            return null;

        T retElement = firstSegment.element;
        firstSegment = firstSegment.nextSegment;
        sizeQueue--;
        return retElement;
    }
}
