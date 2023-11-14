package io.sancta.sanctorum.structures.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListTest {

    private ArrayList<String> stringsList;

    @BeforeEach
    void init() {
        stringsList = new ArrayList<>();
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

    private void m() {
        assertAll("Проверка расположения элементов в листе",
                () -> assertEquals("Привет", stringsList.get(0)),
                () -> assertEquals("меня", stringsList.get(1)),
                () -> assertEquals("зовут", stringsList.get(2)),
                () -> assertEquals("Собака", stringsList.get(3))
        );
    }

    @Test
    @DisplayName("indexOf")
    void indexOf() {
        stringsList = new ArrayList<>(3);
        stringsList.add("меня");
        stringsList.add("зовут");
        stringsList.add("Собака");
        stringsList.add(0, "Привет");
        stringsList.add("!");
        assertAll("Сценарий проверки метода indexOf()",
                () -> m(),
                () -> assertEquals(4, stringsList.indexOf("!")),
                () -> assertEquals(-1, stringsList.indexOf("Привет!"))
        );
    }

    @Test
    @DisplayName("Проверка метода add с добавлением элемента в конец")
    void add() {
        fillListFourStrings();
        assertAll("Сценарий проверки метода add()",
                () -> assertEquals(4, stringsList.size()),
                () -> assertEquals("Привет", stringsList.get(0)),
                () -> assertEquals("меня", stringsList.get(1)),
                () -> assertEquals("зовут", stringsList.get(2)),
                () -> assertEquals("Собака", stringsList.get(3))
        );
    }

    @Test
    @DisplayName("addWithCapacity")
    void addWithCapacity() {
        stringsList = new ArrayList<>(2);
        fillListFourStrings();
        assertAll("Сценарий проверки метода add() с увеличением вместимости",
                () -> assertEquals(4, stringsList.size()),
                () -> assertEquals("Привет", stringsList.get(0)),
                () -> assertEquals("меня", stringsList.get(1)),
                () -> assertEquals("зовут", stringsList.get(2)),
                () -> assertEquals("Собака", stringsList.get(3))
        );
    }

    @Test
    @DisplayName("add проверка добавления в начало")
    void addWithRecreateLeft() {
        stringsList = new ArrayList<>(3);
        stringsList.add("меня");
        stringsList.add("зовут");
        stringsList.add("Собака");
        stringsList.add(0, "Привет");
        stringsList.add("!");
        assertAll("Сценарий проверки метода add() со сдвигом элементов в лево",
                () -> assertEquals(5, stringsList.size()),
                () -> assertEquals("Привет", stringsList.get(0)),
                () -> assertEquals("меня", stringsList.get(1)),
                () -> assertEquals("зовут", stringsList.get(2)),
                () -> assertEquals("Собака", stringsList.get(3)),
                () -> assertEquals("!", stringsList.get(4))
        );
    }

    @Test
    @DisplayName("addWithLeftReserve")
    void addWithLeftReserve() {
        stringsList = new ArrayList<>(3);
        stringsList.add("зовут");
        stringsList.add("Собака");
        stringsList.add("!");
        stringsList.add(0, "Привет");
        stringsList.add(1, "меня");
        assertAll(" ",
                () -> assertEquals(5, stringsList.size()),
                () -> assertEquals("Привет", stringsList.get(0)),
                () -> assertEquals("меня", stringsList.get(1)),
                () -> assertEquals("зовут", stringsList.get(2)),
                () -> assertEquals("Собака", stringsList.get(3)),
                () -> assertEquals("!", stringsList.get(4))
        );
    }

    @Test
    @DisplayName("addWithLeftShift")
    void addWithLeftShift() {
        stringsList = new ArrayList<>(4);
        stringsList.add("Привет");
        stringsList.add("зовут");
        stringsList.add("Собака");
        stringsList.add(1, "меня");
        assertAll(" ",
                () -> assertEquals(4, stringsList.size()),
                () -> assertEquals("Привет", stringsList.get(0)),
                () -> assertEquals("меня", stringsList.get(1)),
                () -> assertEquals("зовут", stringsList.get(2)),
                () -> assertEquals("Собака", stringsList.get(3))
        );
    }

    @Test
    @DisplayName("addWithRightShift")
    void addWithRightShift() {
        stringsList = new ArrayList<>(4);
        stringsList.add("меня");
        stringsList.add("зовут");
        stringsList.add(0, "Привет");
        stringsList.add("Собака");
        assertAll(" ",
                () -> assertEquals(4, stringsList.size()),
                () -> assertEquals("Привет", stringsList.get(0)),
                () -> assertEquals("меня", stringsList.get(1)),
                () -> assertEquals("зовут", stringsList.get(2)),
                () -> assertEquals("Собака", stringsList.get(3))
        );
    }

    @Test
    @DisplayName("removeLeft")
    void removeLeft() {
        fillList5Strings();
        stringsList.remove(1);
        assertAll(" ",
                () -> assertEquals(4, stringsList.size()),
                () -> assertEquals("меня", stringsList.get(1)),
                () -> assertEquals("Привет", stringsList.get(0)),
                () -> assertEquals("зовут", stringsList.get(2)),
                () -> assertEquals("Собака", stringsList.get(3))
        );
    }

    @Test
    @DisplayName("removeRight")
    void removeRight() {
        fillList5Strings();
        stringsList.remove(3);
        assertAll(" ",
                () -> assertEquals(4, stringsList.size()),
                () -> assertEquals("Привет", stringsList.get(0)),
                () -> assertEquals(",", stringsList.get(1)),
                () -> assertEquals("меня", stringsList.get(2)),
                () -> assertEquals("Собака", stringsList.get(3))
        );
    }

    @Test
    @DisplayName("remove")
    void remove() throws NoSuchFieldException, IllegalAccessException {
        stringsList = new ArrayList<>(100);
        fillList5Strings();
        stringsList.remove(1);
        assertAll(" ",
                () -> assertEquals(4, stringsList.size()),
                () -> assertEquals("Привет", stringsList.get(0)),
                () -> assertEquals("меня", stringsList.get(1)),
                () -> assertEquals("зовут", stringsList.get(2)),
                () -> assertEquals("Собака", stringsList.get(3))
        );
        Field f = ArrayList.class.getDeclaredField("items");
        f.setAccessible(true);
        assertEquals(50, ((Object[]) f.get(stringsList)).length);
    }

    @Test
    @DisplayName("clear")
    void clear() {
        stringsList = new ArrayList<>();
        fillList5Strings();
        stringsList.clear();
        assertEquals(0, stringsList.size());
    }

    @Test
    void sort() {
        ArrayList<Integer> list = new ArrayList<>();
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
        ArrayList<Integer> list = new ArrayList<>();
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
        ArrayList<Integer> list = new ArrayList<>();
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