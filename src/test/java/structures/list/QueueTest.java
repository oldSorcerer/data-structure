package structures.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest {
    private Queue<String> stringQueue;

    @BeforeEach
    void init() {
        stringQueue = new Queue<>();
    }

    void fillQueue() {
        stringQueue.put("Привет");
        stringQueue.put("меня");
        stringQueue.put("зовут");
        stringQueue.put("Собака");
    }

    @Test
    void poll() {
        fillQueue();
        assertEquals(4, stringQueue.size());
        assertEquals("Привет", stringQueue.poll());
        assertEquals("меня", stringQueue.poll());
        assertEquals(2, stringQueue.size());
    }

    @Test
    void get() {
        assertNull(stringQueue.poll());
        assertEquals(0, stringQueue.size());
    }

    @Test
    void peek() {
        fillQueue();
        assertEquals(4, stringQueue.size());
        assertEquals("Привет", stringQueue.peek());
        assertEquals("Привет", stringQueue.peek());
        assertEquals(4, stringQueue.size());
    }

//    @Test
//    void lastElement() {
//        fillQueue();
//        assertEquals(4, stringQueue.size());
//        assertEquals("Собака", stringQueue.lastElement());
//        assertEquals("Собака", stringQueue.lastElement());
//        assertEquals(4, stringQueue.size());
//    }

    @Test
    void putNullException() {
        assertThrows(IllegalArgumentException.class, () -> stringQueue.put(null));
    }

    @Test
    void putNullMessage() {
        try {
            stringQueue.put(null);
            fail("No exception");
        } catch (IllegalArgumentException e) {
            assertEquals("New element cannot be null", e.getMessage());
        }
    }
}