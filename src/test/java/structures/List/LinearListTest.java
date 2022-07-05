package structures.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class LinearListTest {

    private LinearList<String> stringsList;

    @BeforeEach
    void init() {
        stringsList = new LinearList<>();
    }

    void fillList4Strings() {
        stringsList.add("Привет");
        stringsList.add("меня");
        stringsList.add("зовут");
        stringsList.add("Собака");
    }

    void fillList5Strings() {
        stringsList.add("Привет");
        stringsList.add(",");
        stringsList.add("меня");
        stringsList.add("зовут");
        stringsList.add("Собака");
    }


    @Test
    void indexOf() {
        LinearList<String> list = new LinearList<>(3);
        list.add("меня");
        list.add("зовут");
        list.add("Собака");
        list.add(0, "Привет");
        list.add("!");
        assertEquals(0, list.indexOf("Привет"));
        assertEquals(1, list.indexOf("меня"));
        assertEquals(2, list.indexOf("зовут"));
        assertEquals(3, list.indexOf("Собака"));
        assertEquals(4, list.indexOf("!"));
        assertEquals(-1, list.indexOf("Привет!"));
    }

    @Test
    void add() {
        fillList4Strings();
        assertEquals(4, stringsList.size());
        assertEquals("Привет", stringsList.get(0));
        assertEquals("меня", stringsList.get(1));
        assertEquals("зовут", stringsList.get(2));
        assertEquals("Собака", stringsList.get(3));
    }

    @Test
    void addWithCapacity() {
        stringsList = new LinearList<>(2);
        fillList4Strings();
        assertEquals(4, stringsList.size());
        assertEquals("Привет", stringsList.get(0));
        assertEquals("меня", stringsList.get(1));
        assertEquals("зовут", stringsList.get(2));
        assertEquals("Собака", stringsList.get(3));
    }

    @Test
    void addWithRecreateLeft() {
        LinearList<String> list = new LinearList<>(3);
        list.add("меня");
        list.add("зовут");
        list.add("Собака");
        list.add(0, "Привет");
        list.add("!");
        assertEquals(5, list.size());
        assertEquals("Привет", list.get(0));
        assertEquals("меня", list.get(1));
        assertEquals("зовут", list.get(2));
        assertEquals("Собака", list.get(3));
        assertEquals("!", list.get(4));
    }

    @Test
    void addWithLeftReserve() {
        LinearList<String> list = new LinearList<>(3);
        list.add("зовут");
        list.add("Собака");
        list.add("!");
        list.add(0, "Привет");
        list.add(1, "меня");
        assertEquals(5, list.size());
        assertEquals("Привет", list.get(0));
        assertEquals("меня", list.get(1));
        assertEquals("зовут", list.get(2));
        assertEquals("Собака", list.get(3));
    }

    @Test
    void addWithLeftShift() {
        LinearList<String> list = new LinearList<>(4);
        list.add("Привет");
        list.add("зовут");
        list.add("Собака");
        list.add(1, "меня");
        assertEquals(4, list.size());
        assertEquals("Привет", list.get(0));
        assertEquals("меня", list.get(1));
        assertEquals("зовут", list.get(2));
        assertEquals("Собака", list.get(3));
    }

    @Test
    void addWithRightShift() {
        LinearList<String> list = new LinearList<>(4);
        list.add("меня");
        list.add("зовут");
        list.add(0, "Привет");
        list.add("Собака");
        assertEquals(4, list.size());
    }

    @Test
    void removeLeft() {
        fillList5Strings();
        stringsList.remove(1);
        assertEquals(4, stringsList.size());
        assertEquals("Привет", stringsList.get(0));
        assertEquals("меня", stringsList.get(1));
        assertEquals("зовут", stringsList.get(2));
        assertEquals("Собака", stringsList.get(3));

    }

    @Test
    void removeRight() {
        fillList5Strings();
        stringsList.remove(1);
        assertEquals(4, stringsList.size());
        assertEquals("Привет", stringsList.get(0));
        assertEquals("меня", stringsList.get(1));
        assertEquals("зовут", stringsList.get(2));
        assertEquals("Собака", stringsList.get(3));
    }

    @Test
    void remove() throws NoSuchFieldException, IllegalAccessException {
        stringsList = new LinearList<>(100);
        fillList5Strings();
        stringsList.remove(1);
        assertEquals(4, stringsList.size());
        assertEquals("Привет", stringsList.get(0));
        assertEquals("меня", stringsList.get(1));
        assertEquals("зовут", stringsList.get(2));
        assertEquals("Собака", stringsList.get(3));
        Field f = LinearList.class.getDeclaredField("items");
        f.setAccessible(true);
        assertEquals(50, ((Object[]) f.get(stringsList)).length);
    }

    @Test
    void clear() {
        fillList5Strings();
        stringsList.clear();
        assertEquals(0, stringsList.size());
    }

    @Test
    void sort() {
        LinearList<Integer> list = new LinearList<>();
        list.add(5);
        list.add(4);
        list.add(-9);
        list.add(-25);
        list.add(50);
        list.add(78);
        list.add(20);
        list.add(-25);
        list.sort(false);

        System.out.println(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            assertTrue(list.get(i - 1) <= list.get(i));
            System.out.println(list.get(i));
        }
    }

    @Test
    void sortBack() {
        LinearList<Integer> list = new LinearList<>();
        list.add(5);
        list.add(4);
        list.add(-9);
        list.add(-25);
        list.add(50);
        list.add(78);
        list.add(20);
        list.add(-25);
        list.sort(true);

        System.out.println(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            assertTrue(list.get(i - 1) >= list.get(i));
            System.out.println(list.get(i));
        }
    }

    @Test
    void iterator() {
        LinearList<Integer> list = new LinearList<>();
        list.add(5);
        list.add(4);
        list.add(-9);
        list.add(-25);
        list.add(50);
        list.add(78);
        list.add(20);
        list.add(-25);

        int idx = 0;

        for (Integer i : list) {
            assertEquals(list.get(idx++), i);
        }
        assertEquals(list.size(), idx);
    }
}