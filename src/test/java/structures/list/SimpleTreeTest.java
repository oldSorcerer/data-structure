package structures.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import structures.tree.SimpleTree;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class SimpleTreeTest {

    private SimpleTree<Integer> tree;

    @BeforeEach
    void init() {
        tree = new SimpleTree<>();
    }

    @Test
    void add() {
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
        tree.add(4);
        tree.add(2);
        tree.add(6);
        tree.add(1);
        tree.add(3);
        tree.add(5);
        tree.add(7);
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
        Random r = new Random();

        for (int i = 0; i < 1000; i++) {
            tree.add(Math.abs(r.nextInt()));
        }

        int  previuos = -1;
        for (int element: tree) {
            System.out.println(element);
            assertTrue(previuos < element);
            previuos = element;
        }
    }

    @Test
    void contains() {
        tree.add(5);
        tree.add(91);
        tree.add(1);
        assertTrue(tree.contains(5));
        assertTrue(tree.contains(91));
        assertFalse(tree.contains(10));
    }

    @Test
    void remove() {
        tree.add(4);
        tree.add(2);
        tree.add(6);
        tree.add(1);
        tree.add(3);
        tree.add(5);
        tree.add(7);
        tree.add(5);
        tree.remove(6);
        assertFalse(tree.contains(6));

        int  previuos = -1;
        for (int element: tree) {
            System.out.println(element);
            assertTrue(previuos < element);
            previuos = element;
        }
    }
}