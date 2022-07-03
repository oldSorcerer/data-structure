package structures.List;

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
    void put() {
        fillQueue();
        assertEquals(4, stringQueue.getSizeQueue());
        assertEquals("Привет", stringQueue.get());
        assertEquals("меня", stringQueue.get());
        assertEquals(2, stringQueue.getSizeQueue());
    }

    @Test
    void get() {
        assertNull(stringQueue.get());
        assertEquals(0, stringQueue.getSizeQueue());
    }

    @Test
    void firstElement() {
        fillQueue();
        assertEquals(4, stringQueue.getSizeQueue());
        assertEquals("Привет", stringQueue.firstElement());
        assertEquals("Привет", stringQueue.firstElement());
        assertEquals(4, stringQueue.getSizeQueue());
    }

    @Test
    void lastElement() {
        fillQueue();
        assertEquals(4, stringQueue.getSizeQueue());
        assertEquals("Собака", stringQueue.lastElement());
        assertEquals("Собака", stringQueue.lastElement());
        assertEquals(4, stringQueue.getSizeQueue());
    }

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