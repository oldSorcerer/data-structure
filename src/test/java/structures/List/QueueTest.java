package structures.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest {

    @Test
    void put() {
        Queue<String> stringQueue = new Queue<>();
        stringQueue.put("Привет");
        stringQueue.put("меня");
        stringQueue.put("зовут");
        stringQueue.put("Собака");
        assertEquals(4, stringQueue.getSizeQueue());
        assertEquals("Привет", stringQueue.get());
        assertEquals("меня", stringQueue.get());
        assertEquals(2, stringQueue.getSizeQueue());   }

    @Test
    void get() {
        Queue <String> stringQueue = new Queue<>();
        assertNull(stringQueue.get());
        assertEquals(0, stringQueue.getSizeQueue());
    }

    @Test
    void firstElement() {
        Queue <String> stringStack = new Queue<>();
        stringStack.put("Привет");
        stringStack.put("меня");
        stringStack.put("зовут");
        stringStack.put("Собака");
        assertEquals(4, stringStack.getSizeQueue());
        assertEquals("Привет", stringStack.firstElement());
        assertEquals("Привет", stringStack.firstElement());
        assertEquals(4, stringStack.getSizeQueue());

    }

    @Test
    void lastElement() {
        Queue <String> stringStack = new Queue<>();
        stringStack.put("Привет");
        stringStack.put("меня");
        stringStack.put("зовут");
        stringStack.put("Собака");
        assertEquals(4, stringStack.getSizeQueue());
        assertEquals("Собака", stringStack.lastElement());
        assertEquals("Собака", stringStack.lastElement());
        assertEquals(4, stringStack.getSizeQueue());
    }

    @Test
    void putNull(){
        try {
            Queue<String> stringStack = new Queue<>();
            stringStack.put(null);
            fail("No exception");
        }
        catch (IllegalArgumentException e){
            // assertEquals("New element cannot be null", e.getMessage());
        }


    }



}