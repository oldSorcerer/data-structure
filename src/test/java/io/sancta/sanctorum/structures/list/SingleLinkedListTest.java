package io.sancta.sanctorum.structures.list;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SingleLinkedListTest {

    @Test
    void add() {
        SingleLinkedList<String> list = new SingleLinkedList<>();
        list.add("Привет");
        list.add("меня");
        list.add("зовут");
        list.add("Собака");
        assertEquals(4, list.size());
        assertEquals("Привет", list.get(0));
        assertEquals("меня", list.get(1));
        assertEquals("зовут", list.get(2));
        assertEquals("Собака", list.get(3));
    }

    @Test
    void addWithIndex() {
        SingleLinkedList<String> list = new SingleLinkedList<>();
        list.add("меня");
        list.add("зовут");
        list.add("Собака");
        list.add(0, "Привет");
        list.add(1, ",");
        assertEquals(5, list.size());
        assertEquals("Привет", list.get(0));
        assertEquals(",", list.get(1));
        assertEquals("меня", list.get(2));
        assertEquals("зовут", list.get(3));
        assertEquals("Собака", list.get(4));
    }

    @Test
    void addWrong() {
        SingleLinkedList<String> list = new SingleLinkedList<>();

        assertThrows(IllegalArgumentException.class, () -> list.add(2, " "));
    }

    @Test
    void set() {
        SingleLinkedList<String> list = new SingleLinkedList<>();
        list.add("Привет");
        list.add("меня");
        list.add("зовут");
        list.add("Собака");
        list.set(3, "Кошка");
        assertEquals(4, list.size());
        assertEquals("Привет", list.get(0));
        assertEquals("меня", list.get(1));
        assertEquals("зовут", list.get(2));
        assertEquals("Кошка", list.get(3));
    }

    @Test
    void setWrong() {
        try {
            SingleLinkedList<String> list = new SingleLinkedList<>();
            list.set(3, " ");
            fail("No exception");
        } catch (IndexOutOfBoundsException ignored) {
        }
    }

    @Test
    void indexOf() {
        SingleLinkedList<String> list = new SingleLinkedList<>();
        list.add("Привет");
        list.add("меня");
        list.add("зовут");
        list.add("Собака");
        assertEquals(3, list.indexOf("Собака"));
        assertEquals(-1, list.indexOf("Кошка"));
    }

    @Test
    void removeOneElement() {
        SingleLinkedList<String> list = new SingleLinkedList<>();
        list.add("Привет");
        list.remove(0);
        assertEquals(0, list.size());
        list.add("Привет");
        list.add("меня");
        list.add("зовут");
        list.add("Собака");
        assertEquals(4, list.size());
        assertEquals("Привет", list.get(0));
        assertEquals("меня", list.get(1));
        assertEquals("зовут", list.get(2));
        assertEquals("Собака", list.get(3));
    }

    @Test
    void removeFirstOf2Elements() {
        SingleLinkedList<String> list = new SingleLinkedList<>();
        list.add("Привет");
        list.add("меня");
        list.remove(0);
        assertEquals(1, list.size());
        assertEquals("меня", list.get(0));
        list.add(0, "Привет");
        list.add("зовут");
        list.add("Собака");
        assertEquals(4, list.size());
        assertEquals("Привет", list.get(0));
        assertEquals("меня", list.get(1));
        assertEquals("зовут", list.get(2));
        assertEquals("Собака", list.get(3));
    }

    @Test
    void removeSecondOf2Elements() {
        SingleLinkedList<String> list = new SingleLinkedList<>();
        list.add("Привет");
        list.add("меня");
        list.remove(1);
        assertEquals(1, list.size());
        assertEquals("Привет", list.get(0));
        list.add(1, "меня");
        list.add("зовут");
        list.add("Собака");
        assertEquals(4, list.size());
        assertEquals("Привет", list.get(0));
        assertEquals("меня", list.get(1));
        assertEquals("зовут", list.get(2));
        assertEquals("Собака", list.get(3));
    }

    @Test
    void removeFirstOfMany() {
        SingleLinkedList<String> list = new SingleLinkedList<>();
        list.add("Привет");
        list.add("меня");
        list.add("зовут");
        list.add("Собака");
        list.remove(0);
        assertEquals(3, list.size());
        assertEquals("меня", list.get(0));
        assertEquals("зовут", list.get(1));
        assertEquals("Собака", list.get(2));
        list.add(0, "Привет");
        assertEquals(4, list.size());
        assertEquals("Привет", list.get(0));
        assertEquals("меня", list.get(1));
        assertEquals("зовут", list.get(2));
        assertEquals("Собака", list.get(3));
    }

    @Test
    void removeLastOfMany() {
        SingleLinkedList<String> list = new SingleLinkedList<>();
        list.add("Привет");
        list.add("меня");
        list.add("зовут");
        list.add("Собака");
        list.remove(3);
        assertEquals(3, list.size());
        assertEquals("Привет", list.get(0));
        assertEquals("меня", list.get(1));
        assertEquals("зовут", list.get(2));
        list.add(3, "Собака");
        assertEquals(4, list.size());
        assertEquals("Привет", list.get(0));
        assertEquals("меня", list.get(1));
        assertEquals("зовут", list.get(2));
        assertEquals("Собака", list.get(3));
    }

    @Test
    void removeOfMany() {
        SingleLinkedList<String> list = new SingleLinkedList<>();
        list.add("Привет");
        list.add(",");
        list.add("меня");
        list.add("зовут");
        list.add("Собака");
        list.remove(2);
        assertEquals(4, list.size());
        assertEquals("Привет", list.get(0));
        assertEquals(",", list.get(1));
        assertEquals("зовут", list.get(2));
        assertEquals("Собака", list.get(3));
    }

    @Test
    void removeElementOfElement() {
        SingleLinkedList<String> list = new SingleLinkedList<>();
        list.add("Привет");
        assertTrue(list.remove("Привет"));
        assertEquals(0, list.size());
        list.add("Привет");
        list.add("меня");
        list.add("зовут");
        list.add("Собака");
        assertEquals(4, list.size());
        assertEquals("Привет", list.get(0));
        assertEquals("меня", list.get(1));
        assertEquals("зовут", list.get(2));
        assertEquals("Собака", list.get(3));
    }

    @Test
    void removeFirstOf2ElementsOfElement() {
        SingleLinkedList<String> list = new SingleLinkedList<>();
        list.add("Привет");
        list.add("меня");
        assertTrue(list.remove("Привет"));
        assertEquals(1, list.size());
        assertEquals("меня", list.get(0));
        list.add(0, "Привет");
        list.add("зовут");
        list.add("Собака");
        assertEquals(4, list.size());
        assertEquals("Привет", list.get(0));
        assertEquals("меня", list.get(1));
        assertEquals("зовут", list.get(2));
        assertEquals("Собака", list.get(3));
    }

    @Test
    void removeSecondOf2ElementsOfElement() {
        SingleLinkedList<String> list = new SingleLinkedList<>();
        list.add("Привет");
        list.add("меня");
        assertTrue(list.remove("меня"));
        assertEquals(1, list.size());
        assertEquals("Привет", list.get(0));
        list.add(1, "меня");
        list.add("зовут");
        list.add("Собака");
        assertEquals(4, list.size());
        assertEquals("Привет", list.get(0));
        assertEquals("меня", list.get(1));
        assertEquals("зовут", list.get(2));
        assertEquals("Собака", list.get(3));
    }

    @Test
    void removeFirstOfManyOfElement() {
        SingleLinkedList<String> list = new SingleLinkedList<>();
        list.add("Привет");
        list.add("меня");
        list.add("зовут");
        list.add("Собака");
        assertTrue(list.remove("Привет"));
        assertEquals(3, list.size());
        assertEquals("меня", list.get(0));
        assertEquals("зовут", list.get(1));
        assertEquals("Собака", list.get(2));
        list.add(0, "Привет");
        assertEquals(4, list.size());
        assertEquals("Привет", list.get(0));
        assertEquals("меня", list.get(1));
        assertEquals("зовут", list.get(2));
        assertEquals("Собака", list.get(3));
    }

    @Test
    void removeLastOfManyOfElement() {
        SingleLinkedList<String> list = new SingleLinkedList<>();
        list.add("Привет");
        list.add("меня");
        list.add("зовут");
        list.add("Собака");
        assertTrue(list.remove("Собака"));
        assertEquals(3, list.size());
        assertEquals("Привет", list.get(0));
        assertEquals("меня", list.get(1));
        assertEquals("зовут", list.get(2));
        list.add(3, "Собака");
        assertEquals(4, list.size());
        assertEquals("Привет", list.get(0));
        assertEquals("меня", list.get(1));
        assertEquals("зовут", list.get(2));
        assertEquals("Собака", list.get(3));

    }

    @Test
    void removeOfManyOfElement() {
        SingleLinkedList<String> list = new SingleLinkedList<>();
        list.add("Привет");
        list.add(",");
        list.add("меня");
        list.add("зовут");
        list.add("Собака");
        assertTrue(list.remove("меня"));
        assertEquals(4, list.size());
        assertEquals("Привет", list.get(0));
        assertEquals(",", list.get(1));
        assertEquals("зовут", list.get(2));
        assertEquals("Собака", list.get(3));
    }

    @Test
    void removeZeroElement() {
        SingleLinkedList<String> list = new SingleLinkedList<>();
        assertFalse(list.remove("Привет"));
        assertEquals(0, list.size());
        list.add("Привет");
        list.add("меня");
        list.add("зовут");
        list.add("Собака");
        assertEquals(4, list.size());
        assertEquals("Привет", list.get(0));
        assertEquals("меня", list.get(1));
        assertEquals("зовут", list.get(2));
        assertEquals("Собака", list.get(3));
    }

    @Test
    void removeZeroOfElement() {
        SingleLinkedList<String> stringSingleLinkedList = new SingleLinkedList<>();
        stringSingleLinkedList.add("Привет");
        assertFalse(stringSingleLinkedList.remove("Привет!"));
        assertEquals(1, stringSingleLinkedList.size());
        stringSingleLinkedList.add("меня");
        stringSingleLinkedList.add("зовут");
        stringSingleLinkedList.add("Собака");
        assertEquals(4, stringSingleLinkedList.size());
        assertEquals("Привет", stringSingleLinkedList.get(0));
        assertEquals("меня", stringSingleLinkedList.get(1));
        assertEquals("зовут", stringSingleLinkedList.get(2));
        assertEquals("Собака", stringSingleLinkedList.get(3));
    }

    @Test
    void removeZeroOf2ElementsOfElement() {
        SingleLinkedList<String> stringSingleLinkedList = new SingleLinkedList<>();
        stringSingleLinkedList.add("Привет");
        stringSingleLinkedList.add("меня");
        assertFalse(stringSingleLinkedList.remove("Привет!"));
        assertEquals(2, stringSingleLinkedList.size());
        assertEquals("меня", stringSingleLinkedList.get(1));
        stringSingleLinkedList.add("зовут");
        stringSingleLinkedList.add("Собака");
        assertEquals(4, stringSingleLinkedList.size());
        assertEquals("Привет", stringSingleLinkedList.get(0));
        assertEquals("меня", stringSingleLinkedList.get(1));
        assertEquals("зовут", stringSingleLinkedList.get(2));
        assertEquals("Собака", stringSingleLinkedList.get(3));
    }

    @Test
    void removeZeroOfManyOfElement() {
        SingleLinkedList<String> stringSingleLinkedList = new SingleLinkedList<>();
        stringSingleLinkedList.add("Привет");
        stringSingleLinkedList.add("меня");
        stringSingleLinkedList.add("зовут");
        stringSingleLinkedList.add("Собака");
        assertFalse(stringSingleLinkedList.remove("Привет!"));
        assertEquals(4, stringSingleLinkedList.size());
        assertEquals("Привет", stringSingleLinkedList.get(0));
        assertEquals("меня", stringSingleLinkedList.get(1));
        assertEquals("зовут", stringSingleLinkedList.get(2));
        assertEquals("Собака", stringSingleLinkedList.get(3));
    }

    @Test
    void clear() {
        SingleLinkedList<String> list = new SingleLinkedList<>();
        list.add("Привет");
        list.add("меня");
        list.add("зовут");
        list.add("Собака");
        list.clear();
        assertEquals(0, list.size());
    }
}
/*
* test{Method}_Should{Do}_When{Condition}
*
* */