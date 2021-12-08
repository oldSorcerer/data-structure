package structures.List;

public class Stack <T> {

    private Segment<T> lastSegment;
    private int sizeStack;

    public int getSizeStack() {
        return sizeStack;
    }

    public void push(T elementToAdd){
        if (elementToAdd == null)
            throw new IllegalArgumentException("New element cannot be null");

        Segment<T> newSegment = new Segment<>();
        newSegment.element = elementToAdd;
        newSegment.nextSegment = lastSegment;
        lastSegment = newSegment;
        sizeStack++;
    }

    public T pop(){
        if (sizeStack == 0)
            return null;

        T retElement = lastSegment.element;
        lastSegment = lastSegment.nextSegment;
        sizeStack--;
        return retElement;
    }

    public T peek(){
        if (sizeStack == 0)
            return null;

        return lastSegment.element;
    }
}
