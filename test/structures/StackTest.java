package structures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {

    @Test
    void push() {
        Stack <String> stringStack = new Stack<>();
        stringStack.push("Привет");
        stringStack.push("меня");
        stringStack.push("зовут");
        stringStack.push("Собака");
        assertEquals(4, stringStack.getSizeStack());
        assertEquals("Собака", stringStack.pop());
        assertEquals("зовут", stringStack.pop());
        assertEquals(2, stringStack.getSizeStack());
    }

    @Test
    void pop() {
        Stack <String> stringStack = new Stack<>();
        assertNull(stringStack.pop());
        assertEquals(0, stringStack.getSizeStack());

    }

    @Test
    void peek() {
        Stack <String> stringStack = new Stack<>();
        stringStack.push("Привет");
        stringStack.push("меня");
        stringStack.push("зовут");
        stringStack.push("Собака");
        assertEquals(4, stringStack.getSizeStack());
        assertEquals("Собака", stringStack.peek());
        assertEquals("Собака", stringStack.peek());
        assertEquals(4, stringStack.getSizeStack());
    }

    @Test
    void pushNull(){
        try {
            Stack<String> stringStack = new Stack<>();
            stringStack.push(null);
            fail("No exception");
        }
        catch (IllegalArgumentException e){
           // assertEquals("New element cannot be null", e.getMessage());
       }


    }
}