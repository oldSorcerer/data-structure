package structures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleTreeTest {

    @Test
    void add() {
        SimpleTree<Integer> tree = new SimpleTree<>();
        assertTrue(tree.add(5));
        assertTrue(tree.add(91));
        assertTrue(tree.add(1));
        assertTrue(tree.add(18));
        assertTrue(tree.add(24));

        assertFalse(tree.add(5));
        assertFalse(tree.add(1));
        assertEquals(5, tree.getSizeTree());
    }

    @Test
    void iterator() {
        SimpleTree<Integer> tree = new SimpleTree<>();
        tree.add(5);
        tree.add(91);
        tree.add(1);
        tree.add(18);
        tree.add(24);
        tree.add(10);
        tree.add(31);
        tree.add(24);
        tree.add(15);
        tree.add(63);

        int  previuos = -1;
        for (int element: tree) {
            assertTrue(previuos < element);
            previuos = element;
        }

    }
}