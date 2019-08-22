package structures;

import org.junit.jupiter.api.Test;

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
    void addWith () {
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
}