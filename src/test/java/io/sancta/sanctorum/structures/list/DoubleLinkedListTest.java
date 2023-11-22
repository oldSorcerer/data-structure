package io.sancta.sanctorum.structures.list;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoubleLinkedListTest {

    @Test
    void add() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
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
    void addFirst(){
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.add("меня");
        list.add("зовут");
        list.add("Собака");
        list.add(0, "Привет");
        assertEquals(4, list.size());
        assertEquals("Привет", list.get(0));
        assertEquals("меня", list.get(1));
        assertEquals("зовут", list.get(2));
        assertEquals("Собака", list.get(3));
    }

    @Test
    void addFirstHalf(){
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.add("Привет");
        list.add("меня");
        list.add("зовут");
        list.add("Собака");
        list.add(1, ",");
        assertEquals(5, list.size());
        assertEquals("Привет", list.get(0));
        assertEquals(",", list.get(1));
        assertEquals("меня", list.get(2));
        assertEquals("зовут", list.get(3));
        assertEquals("Собака", list.get(4));
    }

    @Test
    void addSecondHalf(){
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.add("Привет");
        list.add("меня");
        list.add("зовут");
        list.add("Собака");
        list.add(3,",");
        assertEquals(5, list.size());
        assertEquals("Привет", list.get(0));
        assertEquals("меня", list.get(1));
        assertEquals("зовут", list.get(2));
        assertEquals(",", list.get(3));
        assertEquals("Собака", list.get(4));
    }

    @Test
    void indexOf(){
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.add("Привет");
        list.add("меня");
        list.add("зовут");
        list.add("Собака");
        assertEquals(0, list.indexOf("Привет"));
        assertEquals(1, list.indexOf("меня"));
        assertEquals(2, list.indexOf("зовут"));
        assertEquals(3, list.indexOf("Собака"));
        assertEquals(-1, list.indexOf("Привет!"));
    }

    @Test
    void removeWrongIndex() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.add("Привет");
        try {
            list.remove(10);
            fail("No error");
        }
        catch (IllegalArgumentException ignore) {
        }
    }

    @Test
    void removeSingle () {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.add("Привет");
        list.remove(0);
        assertEquals(0, list.size());
        list.add("Привет");
        list.add("меня");
        list.add("зовут");
        list.add("Собака");
        check(list);
    }

    void check (DoubleLinkedList<String> list){
        assertEquals("Привет", list.get(0));
        assertEquals("меня", list.get(1));
        assertEquals("зовут", list.get(2));
        assertEquals("Собака", list.get(3));
        assertEquals(4, list.size());
    }

    @Test
    void removeFirstOf2 (){
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.add("Привет");
        list.add("меня");
        list.remove(0);
        assertEquals(1, list.size());
        assertEquals("меня", list.get(0));
        list.add(0, "Привет");
        list.add("зовут");
        list.add("Собака");
        check(list);
    }

    @Test
    void removeSecondOf2 (){
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.add("Привет");
        list.add("меня");
        list.remove(1);
        assertEquals(1, list.size());
        assertEquals("Привет", list.get(0));
        list.add("меня");
        list.add("зовут");
        list.add("Собака");
        check(list);
    }

    @Test
    void removeFirstOfMany() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.add("Привет");
        list.add("меня");
        list.add("зовут");
        list.add("Собака");
        list.remove(0);
        assertEquals(3,list.size());
        assertEquals("меня",list.get(0));
        assertEquals("зовут",list.get(1));
        assertEquals("Собака",list.get(2));
        list.add(0, "Привет");
        check(list);
    }

    @Test
    void removeLastOfMany() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.add("Привет");
        list.add("меня");
        list.add("зовут");
        list.add("Собака");
        list.remove(3);
        assertEquals(3,list.size());
        assertEquals("Привет", list.get(0));
        assertEquals("меня",list.get(1));
        assertEquals("зовут",list.get(2));
        list.add("Собака");
        check(list);
    }

    @Test
    void removeMiddleOfMany() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.add("Привет");
        list.add(",");
        list.add("меня");
        list.add("зовут");
        list.add("Собака");
        list.remove(1);
        check(list);
    }

    @Test
    void removeSingleElement() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.add("Привет");
        assertTrue(list.remove("Привет"));
        assertEquals(0, list.size());
        list.add("Привет");
        list.add("меня");
        list.add("зовут");
        list.add("Собака");
        check(list);
    }

    @Test
    void removeFirstOf2Elements (){
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.add("Привет");
        list.add("меня");
        assertTrue(list.remove("Привет"));
        assertEquals(1, list.size());
        assertEquals("меня", list.get(0));
        list.add(0, "Привет");
        list.add("зовут");
        list.add("Собака");
        check(list);
    }

    @Test
    void removeSecondOf2Elements (){
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.add("Привет");
        list.add("меня");
        assertTrue(list.remove("меня"));
        assertEquals(1, list.size());
        assertEquals("Привет", list.get(0));
        list.add("меня");
        list.add("зовут");
        list.add("Собака");
        check(list);
    }

    @Test
    void removeFirstOfManyElements() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.add("Привет");
        list.add("меня");
        list.add("зовут");
        list.add("Собака");
        assertTrue(list.remove("Привет"));
        assertEquals(3,list.size());
        assertEquals("меня",list.get(0));
        assertEquals("зовут",list.get(1));
        assertEquals("Собака",list.get(2));
        list.add(0, "Привет");
        check(list);
    }

    @Test
    void removeLastOfManyElements() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.add("Привет");
        list.add("меня");
        list.add("зовут");
        list.add("Собака");
        assertTrue(list.remove("Собака"));
        assertEquals(3,list.size());
        assertEquals("Привет", list.get(0));
        assertEquals("меня",list.get(1));
        assertEquals("зовут",list.get(2));
        list.add("Собака");
        check(list);
    }

    @Test
    void removeMiddleOfManyElements() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.add("Привет");
        list.add(",");
        list.add("меня");
        list.add("зовут");
        list.add("Собака");
        assertTrue(list.remove(","));
        check(list);
    }

    @Test
    void removeZeroOfManyElements() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.add("Привет");
        list.add("меня");
        list.add("зовут");
        list.add("Собака");
        assertFalse(list.remove("Привет!"));
        check(list);
    }

    @Test
    void clear() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.add("Привет");
        list.add("меня");
        list.add("зовут");
        list.add("Собака");
        list.clear();
        assertEquals(0, list.size());
    }
}