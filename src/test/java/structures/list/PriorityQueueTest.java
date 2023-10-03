package structures.list;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

        assertEquals(9, queue.peek());
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

        assertEquals(9, queue.poll());
        assertEquals(12, queue.poll());
        assertEquals(20, queue.poll());
        assertEquals(31, queue.poll());
        assertEquals(59, queue.poll());
        assertEquals(60, queue.poll());
        assertEquals(85, queue.poll());
    }
}