package structures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriorityQueueTest {

    @Test
    void put() {
        PriorityQueue<Integer> queue = new PriorityQueue<>(true);
        queue.put(85);
        queue.put(59);
        queue.put(12);
        queue.put(9);
        queue.put(20);
        queue.put(31);
        queue.put(60);

        assertEquals(9, queue.firstElement());
    }

    @Test
    void get() {
        PriorityQueue<Integer> queue = new PriorityQueue<>(true);
        queue.put(85);
        queue.put(59);
        queue.put(12);
        queue.put(9);
        queue.put(20);
        queue.put(31);
        queue.put(60);

        assertEquals(9, queue.get());
        assertEquals(12, queue.get());
        assertEquals(20, queue.get());
        assertEquals(31, queue.get());
        assertEquals(59, queue.get());
        assertEquals(60, queue.get());
        assertEquals(85, queue.get());
    }

    @Test
    void getTwo() {
        PriorityQueue<Integer> queue = new PriorityQueue<>(true);
        queue.put(95);
        queue.put(59);
        queue.put(12);
        queue.put(9);
        queue.put(20);
        queue.put(31);
        queue.put(60);

        assertEquals(9, queue.get());
        assertEquals(12, queue.get());
        assertEquals(20, queue.get());
        assertEquals(31, queue.get());
        assertEquals(59, queue.get());
        assertEquals(60, queue.get());
        assertEquals(95, queue.get());
    }


}