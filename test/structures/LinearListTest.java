package structures;

import org.junit.jupiter.api.Test;
import structures.List.LinearList;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class LinearListTest {

    @Test
    void indexOf() {
        LinearList<String> list = new LinearList<>(3);
        list.add("меня");
        list.add("зовут");
        list.add("Собака");
        list.add("Привет",0);
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
        LinearList<String> list = new LinearList<>();
        list.add("Привет");
        list.add("меня");
        list.add("зовут");
        list.add("Собака");
        assertEquals(4,list.getSizeList());
        assertEquals("Привет",list.get(0));
        assertEquals("меня",list.get(1));
        assertEquals("зовут",list.get(2));
        assertEquals("Собака",list.get(3));
    }

    @Test
    void addWithCapacity() {
        LinearList<String> list = new LinearList<>(2);
        list.add("Привет");
        list.add("меня");
        list.add("зовут");
        list.add("Собака");
        assertEquals(4,list.getSizeList());
        assertEquals("Привет",list.get(0));
        assertEquals("меня",list.get(1));
        assertEquals("зовут",list.get(2));
        assertEquals("Собака",list.get(3));
    }

    @Test
    void addWithRecreateLeft() {
        LinearList<String> list = new LinearList<>(3);
        list.add("меня");
        list.add("зовут");
        list.add("Собака");
        list.add("Привет",0);
        list.add("!");
        assertEquals(5,list.getSizeList());
        assertEquals("Привет",list.get(0));
        assertEquals("меня",list.get(1));
        assertEquals("зовут",list.get(2));
        assertEquals("Собака",list.get(3));
        assertEquals("!",list.get(4));
    }

    @Test
    void addWithLeftReserve() {
        LinearList<String> list = new LinearList<>(3);
        list.add("зовут");
        list.add("Собака");
        list.add("!");
        list.add("Привет",0);
        list.add("меня",1);
        assertEquals(5,list.getSizeList());
        assertEquals("Привет",list.get(0));
        assertEquals("меня",list.get(1));
        assertEquals("зовут",list.get(2));
        assertEquals("Собака",list.get(3));
    }

    @Test
    void addWithLeftShift() {
        LinearList<String> list = new LinearList<>(4);
        list.add("Привет");
        list.add("зовут");
        list.add("Собака");
        list.add("меня",1);
        assertEquals(4, list.getSizeList());
        assertEquals("Привет",list.get(0));
        assertEquals("меня",list.get(1));
        assertEquals("зовут",list.get(2));
        assertEquals("Собака",list.get(3));
    }

    @Test
    void addWithRightShift() {
        LinearList<String> list = new LinearList<>(4);
        list.add("меня");
        list.add("зовут");
        list.add("Привет",0);
        list.add("Собака");
        assertEquals(4, list.getSizeList());
    }

    @Test
    void removeLeft() {
        LinearList<String> list = new LinearList<>();
        list.add("Привет");
        list.add(",");
        list.add("меня");
        list.add("зовут");
        list.add("Собака");
        list.remove(1);
        assertEquals(4,list.getSizeList());
        assertEquals("Привет",list.get(0));
        assertEquals("меня",list.get(1));
        assertEquals("зовут",list.get(2));
        assertEquals("Собака",list.get(3));

    }

    @Test
    void removeRight() {
        LinearList<String> list = new LinearList<>();
        list.add("Привет");
        list.add("меня");
        list.add("зовут");
        list.add(",");
        list.add("Собака");
        list.remove(3);
        assertEquals(4,list.getSizeList());
        assertEquals("Привет",list.get(0));
        assertEquals("меня",list.get(1));
        assertEquals("зовут",list.get(2));
        assertEquals("Собака",list.get(3));
    }

    @Test
    void remove() throws NoSuchFieldException, IllegalAccessException {
        LinearList<String> list = new LinearList<>(100);
        list.add("Привет");
        list.add("меня");
        list.add("зовут");
        list.add(",");
        list.add("Собака");
        list.remove(3);
        assertEquals(4,list.getSizeList());
        assertEquals("Привет",list.get(0));
        assertEquals("меня",list.get(1));
        assertEquals("зовут",list.get(2));
        assertEquals("Собака",list.get(3));
        Field f = LinearList.class.getDeclaredField("items");
        f.setAccessible(true);
        assertEquals(50, ((Object[])f.get(list)).length);
    }
    @Test
    void clear() {
        LinearList<String> list = new LinearList<>();
        list.add("Привет");
        list.add("меня");
        list.add("зовут");
        list.add(",");
        list.add("Собака");
        list.clear();
        assertEquals(0, list.getSizeList());
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
        for (int i = 1; i < list.getSizeList(); i++) {
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
        for (int i = 1; i < list.getSizeList(); i++) {
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

        for (Integer i : list)
            assertEquals(list.get(idx++), i);

        assertEquals(list.getSizeList(), idx);
    }

}