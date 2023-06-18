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

    void fillListFourStrings() {
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
        stringsList = new LinearList<>(3);
        stringsList.add("меня");
        stringsList.add("зовут");
        stringsList.add("Собака");
        stringsList.add(0, "Привет");
        stringsList.add("!");
        assertEquals(0, stringsList.indexOf("Привет"));
        assertEquals(1, stringsList.indexOf("меня"));
        assertEquals(2, stringsList.indexOf("зовут"));
        assertEquals(3, stringsList.indexOf("Собака"));
        assertEquals(4, stringsList.indexOf("!"));
        assertEquals(-1, stringsList.indexOf("Привет!"));
    }

    @Test
    void add() {
        fillListFourStrings();
        assertEquals(4, stringsList.size());
        assertEquals("Привет", stringsList.get(0));
        assertEquals("меня", stringsList.get(1));
        assertEquals("зовут", stringsList.get(2));
        assertEquals("Собака", stringsList.get(3));
    }

    @Test
    void addWithCapacity() {
        stringsList = new LinearList<>(2);
        fillListFourStrings();
        assertEquals(4, stringsList.size());
        assertEquals("Привет", stringsList.get(0));
        assertEquals("меня", stringsList.get(1));
        assertEquals("зовут", stringsList.get(2));
        assertEquals("Собака", stringsList.get(3));
    }

    @Test
    void addWithRecreateLeft() {
        stringsList = new LinearList<>(3);
        stringsList.add("меня");
        stringsList.add("зовут");
        stringsList.add("Собака");
        stringsList.add(0, "Привет");
        stringsList.add("!");
        assertEquals(5, stringsList.size());
        assertEquals("Привет", stringsList.get(0));
        assertEquals("меня", stringsList.get(1));
        assertEquals("зовут", stringsList.get(2));
        assertEquals("Собака", stringsList.get(3));
        assertEquals("!", stringsList.get(4));
    }

    @Test
    void addWithLeftReserve() {
        stringsList = new LinearList<>(3);
        stringsList.add("зовут");
        stringsList.add("Собака");
        stringsList.add("!");
        stringsList.add(0, "Привет");
        stringsList.add(1, "меня");
        assertEquals(5, stringsList.size());
        assertEquals("Привет", stringsList.get(0));
        assertEquals("меня", stringsList.get(1));
        assertEquals("зовут", stringsList.get(2));
        assertEquals("Собака", stringsList.get(3));
        assertEquals("!", stringsList.get(4));
    }

    @Test
    void addWithLeftShift() {
        stringsList = new LinearList<>(4);
        stringsList.add("Привет");
        stringsList.add("зовут");
        stringsList.add("Собака");
        stringsList.add(1, "меня");
        assertEquals(4, stringsList.size());
        assertEquals("Привет", stringsList.get(0));
        assertEquals("меня", stringsList.get(1));
        assertEquals("зовут", stringsList.get(2));
        assertEquals("Собака", stringsList.get(3));
    }

    @Test
    void addWithRightShift() {
        stringsList = new LinearList<>(4);
        stringsList.add("меня");
        stringsList.add("зовут");
        stringsList.add(0, "Привет");
        stringsList.add("Собака");
        assertEquals(4, stringsList.size());
        assertEquals("Привет", stringsList.get(0));
        assertEquals("меня", stringsList.get(1));
        assertEquals("зовут", stringsList.get(2));
        assertEquals("Собака", stringsList.get(3));
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
        stringsList.remove(3);
        assertEquals(4, stringsList.size());
        assertEquals("Привет", stringsList.get(0));
        assertEquals(",", stringsList.get(1));
        assertEquals("меня", stringsList.get(2));
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
        stringsList = new LinearList<>();
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