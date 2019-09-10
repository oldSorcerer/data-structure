package structures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SingleLinkedListTest {

    @Test
    void add (){
        SingleLinkedList<String> stringSingleLinkedList = new SingleLinkedList<>();
        stringSingleLinkedList.add ("Привет");
        stringSingleLinkedList.add ("меня");
        stringSingleLinkedList.add ("зовут");
        stringSingleLinkedList.add ("Собака");
        assertEquals(4,stringSingleLinkedList.getSizeList());
        assertEquals("Привет", stringSingleLinkedList.get(0) );
        assertEquals("меня", stringSingleLinkedList.get(1) );
        assertEquals("зовут", stringSingleLinkedList.get(2) );
        assertEquals("Собака", stringSingleLinkedList.get(3) );
    }

    @Test
    void addWithIndex(){
        SingleLinkedList<String> stringSingleLinkedList = new SingleLinkedList<>();
        stringSingleLinkedList.add ("меня");
        stringSingleLinkedList.add ("зовут");
        stringSingleLinkedList.add ("Собака");
        stringSingleLinkedList.add ("Привет", 0);
        stringSingleLinkedList.add(",", 1);
        assertEquals(5,stringSingleLinkedList.getSizeList());
        assertEquals("Привет", stringSingleLinkedList.get(0) );
        assertEquals(",", stringSingleLinkedList.get(1) );
        assertEquals("меня", stringSingleLinkedList.get(2) );
        assertEquals("зовут", stringSingleLinkedList.get(3) );
        assertEquals("Собака", stringSingleLinkedList.get(4) );
    }

    @Test
    void addWrong (){
        try {
            SingleLinkedList<String> stringSingleLinkedList = new SingleLinkedList<>();
            stringSingleLinkedList.add(" ", 2);
            fail("No exception");
        }
        catch (IllegalArgumentException ignored){

        }
    }

    @Test
    void set(){
        SingleLinkedList<String> stringSingleLinkedList = new SingleLinkedList<>();
        stringSingleLinkedList.add("Привет");
        stringSingleLinkedList.add("меня");
        stringSingleLinkedList.add("зовут");
        stringSingleLinkedList.add("Собака");
        stringSingleLinkedList.set("Кошка", 3);
        assertEquals(4,stringSingleLinkedList.getSizeList());
        assertEquals("Привет", stringSingleLinkedList.get(0) );
        assertEquals("меня", stringSingleLinkedList.get(1) );
        assertEquals("зовут", stringSingleLinkedList.get(2) );
        assertEquals("Кошка", stringSingleLinkedList.get(3));
    }

    @Test
    void setWrong(){
        try{
        SingleLinkedList<String> stringSingleLinkedList = new SingleLinkedList<>();
        stringSingleLinkedList.set(" ", 3);
        fail("No exception");
        }
        catch (IllegalArgumentException ignored){
        }
    }

    @Test
    void indexOf (){
        SingleLinkedList<String> stringSingleLinkedList = new SingleLinkedList<>();
        stringSingleLinkedList.add("Привет");
        stringSingleLinkedList.add("меня");
        stringSingleLinkedList.add("зовут");
        stringSingleLinkedList.add("Собака");
        assertEquals(3, stringSingleLinkedList.indexOf("Собака"));
        assertEquals(-1, stringSingleLinkedList.indexOf("Кошка"));
    }

    @Test
    void removeOneElement(){
        SingleLinkedList<String> stringSingleLinkedList = new SingleLinkedList<>();
        stringSingleLinkedList.add("Привет");
        stringSingleLinkedList.remove(0);
        assertEquals(0, stringSingleLinkedList.getSizeList());
        stringSingleLinkedList.add ("Привет");
        stringSingleLinkedList.add ("меня");
        stringSingleLinkedList.add ("зовут");
        stringSingleLinkedList.add ("Собака");
        assertEquals(4,stringSingleLinkedList.getSizeList());
        assertEquals("Привет", stringSingleLinkedList.get(0) );
        assertEquals("меня", stringSingleLinkedList.get(1) );
        assertEquals("зовут", stringSingleLinkedList.get(2) );
        assertEquals("Собака", stringSingleLinkedList.get(3) );
    }

    @Test
    void removeFirstOf2Elements(){
        SingleLinkedList<String> stringSingleLinkedList = new SingleLinkedList<>();
        stringSingleLinkedList.add("Привет");
        stringSingleLinkedList.add ("меня");
        stringSingleLinkedList.remove(0);
        assertEquals(1, stringSingleLinkedList.getSizeList());
        assertEquals("меня", stringSingleLinkedList.get(0));
        stringSingleLinkedList.add("Привет",0);
        stringSingleLinkedList.add("зовут");
        stringSingleLinkedList.add ("Собака");
        assertEquals(4,stringSingleLinkedList.getSizeList());
        assertEquals("Привет", stringSingleLinkedList.get(0) );
        assertEquals("меня", stringSingleLinkedList.get(1) );
        assertEquals("зовут", stringSingleLinkedList.get(2) );
        assertEquals("Собака", stringSingleLinkedList.get(3) );
    }

    @Test
    void removeSecondOf2Elements(){
        SingleLinkedList<String> stringSingleLinkedList = new SingleLinkedList<>();
        stringSingleLinkedList.add("Привет");
        stringSingleLinkedList.add ("меня");
        stringSingleLinkedList.remove(1);
        assertEquals(1, stringSingleLinkedList.getSizeList());
        assertEquals("Привет", stringSingleLinkedList.get(0));
        stringSingleLinkedList.add("меня",1);
        stringSingleLinkedList.add("зовут");
        stringSingleLinkedList.add ("Собака");
        assertEquals(4, stringSingleLinkedList.getSizeList());
        assertEquals("Привет", stringSingleLinkedList.get(0) );
        assertEquals("меня", stringSingleLinkedList.get(1) );
        assertEquals("зовут", stringSingleLinkedList.get(2) );
        assertEquals("Собака", stringSingleLinkedList.get(3) );
    }

    @Test
    void removeFirstOfMany(){
        SingleLinkedList<String> stringSingleLinkedList = new SingleLinkedList<>();
        stringSingleLinkedList.add ("Привет");
        stringSingleLinkedList.add ("меня");
        stringSingleLinkedList.add ("зовут");
        stringSingleLinkedList.add ("Собака");
        stringSingleLinkedList.remove(0);
        assertEquals(3, stringSingleLinkedList.getSizeList());
        assertEquals("меня", stringSingleLinkedList.get(0) );
        assertEquals("зовут", stringSingleLinkedList.get(1) );
        assertEquals("Собака", stringSingleLinkedList.get(2) );
        stringSingleLinkedList.add("Привет",0);
        assertEquals(4, stringSingleLinkedList.getSizeList());
        assertEquals("Привет", stringSingleLinkedList.get(0) );
        assertEquals("меня", stringSingleLinkedList.get(1) );
        assertEquals("зовут", stringSingleLinkedList.get(2) );
        assertEquals("Собака", stringSingleLinkedList.get(3) );

    }

    @Test
    void removeLastOfMany(){
        SingleLinkedList<String> stringSingleLinkedList = new SingleLinkedList<>();
        stringSingleLinkedList.add ("Привет");
        stringSingleLinkedList.add ("меня");
        stringSingleLinkedList.add ("зовут");
        stringSingleLinkedList.add ("Собака");
        stringSingleLinkedList.remove(3);
        assertEquals(3, stringSingleLinkedList.getSizeList());
        assertEquals("Привет", stringSingleLinkedList.get(0) );
        assertEquals("меня", stringSingleLinkedList.get(1) );
        assertEquals("зовут", stringSingleLinkedList.get(2) );
        stringSingleLinkedList.add("Собака",3);
        assertEquals(4, stringSingleLinkedList.getSizeList());
        assertEquals("Привет", stringSingleLinkedList.get(0) );
        assertEquals("меня", stringSingleLinkedList.get(1) );
        assertEquals("зовут", stringSingleLinkedList.get(2) );
        assertEquals("Собака", stringSingleLinkedList.get(3) );

    }

    @Test
    void removeOfMany(){
        SingleLinkedList<String> stringSingleLinkedList = new SingleLinkedList<>();
        stringSingleLinkedList.add("Привет");
        stringSingleLinkedList.add(",");
        stringSingleLinkedList.add("меня");
        stringSingleLinkedList.add("зовут");
        stringSingleLinkedList.add("Собака");
        stringSingleLinkedList.remove(2);
        assertEquals(4, stringSingleLinkedList.getSizeList());
        assertEquals("Привет", stringSingleLinkedList.get(0) );
        assertEquals(",", stringSingleLinkedList.get(1) );
        assertEquals("зовут", stringSingleLinkedList.get(2) );
        assertEquals("Собака", stringSingleLinkedList.get(3) );
    }

    @Test
    void removeElementOfElement(){
        SingleLinkedList<String> stringSingleLinkedList = new SingleLinkedList<>();
        stringSingleLinkedList.add("Привет");
        assertTrue(stringSingleLinkedList.remove("Привет"));
        assertEquals(0, stringSingleLinkedList.getSizeList());
        stringSingleLinkedList.add("Привет");
        stringSingleLinkedList.add("меня");
        stringSingleLinkedList.add("зовут");
        stringSingleLinkedList.add("Собака");
        assertEquals(4,stringSingleLinkedList.getSizeList());
        assertEquals("Привет", stringSingleLinkedList.get(0));
        assertEquals("меня", stringSingleLinkedList.get(1));
        assertEquals("зовут", stringSingleLinkedList.get(2));
        assertEquals("Собака", stringSingleLinkedList.get(3));
    }

    @Test
    void removeFirstOf2ElementsOfElement(){
        SingleLinkedList<String> stringSingleLinkedList = new SingleLinkedList<>();
        stringSingleLinkedList.add("Привет");
        stringSingleLinkedList.add("меня");
        assertTrue(stringSingleLinkedList.remove("Привет"));
        assertEquals(1, stringSingleLinkedList.getSizeList());
        assertEquals("меня", stringSingleLinkedList.get(0));
        stringSingleLinkedList.add("Привет",0);
        stringSingleLinkedList.add("зовут");
        stringSingleLinkedList.add("Собака");
        assertEquals(4,stringSingleLinkedList.getSizeList());
        assertEquals("Привет", stringSingleLinkedList.get(0));
        assertEquals("меня", stringSingleLinkedList.get(1));
        assertEquals("зовут", stringSingleLinkedList.get(2));
        assertEquals("Собака", stringSingleLinkedList.get(3));
    }

    @Test
    void removeSecondOf2ElementsOfElement(){
        SingleLinkedList<String> stringSingleLinkedList = new SingleLinkedList<>();
        stringSingleLinkedList.add("Привет");
        stringSingleLinkedList.add("меня");
        assertTrue(stringSingleLinkedList.remove("меня"));
        assertEquals(1, stringSingleLinkedList.getSizeList());
        assertEquals("Привет", stringSingleLinkedList.get(0));
        stringSingleLinkedList.add("меня",1);
        stringSingleLinkedList.add("зовут");
        stringSingleLinkedList.add ("Собака");
        assertEquals(4, stringSingleLinkedList.getSizeList());
        assertEquals("Привет", stringSingleLinkedList.get(0));
        assertEquals("меня", stringSingleLinkedList.get(1));
        assertEquals("зовут", stringSingleLinkedList.get(2));
        assertEquals("Собака", stringSingleLinkedList.get(3));
    }

    @Test
    void removeFirstOfManyOfElement(){
        SingleLinkedList<String> stringSingleLinkedList = new SingleLinkedList<>();
        stringSingleLinkedList.add("Привет");
        stringSingleLinkedList.add("меня");
        stringSingleLinkedList.add("зовут");
        stringSingleLinkedList.add("Собака");
        assertTrue(stringSingleLinkedList.remove("Привет"));
        assertEquals(3, stringSingleLinkedList.getSizeList());
        assertEquals("меня", stringSingleLinkedList.get(0));
        assertEquals("зовут", stringSingleLinkedList.get(1));
        assertEquals("Собака", stringSingleLinkedList.get(2));
        stringSingleLinkedList.add("Привет",0);
        assertEquals(4, stringSingleLinkedList.getSizeList());
        assertEquals("Привет", stringSingleLinkedList.get(0));
        assertEquals("меня", stringSingleLinkedList.get(1));
        assertEquals("зовут", stringSingleLinkedList.get(2));
        assertEquals("Собака", stringSingleLinkedList.get(3));
    }

    @Test
    void removeLastOfManyOfElement(){
        SingleLinkedList<String> stringSingleLinkedList = new SingleLinkedList<>();
        stringSingleLinkedList.add("Привет");
        stringSingleLinkedList.add("меня");
        stringSingleLinkedList.add("зовут");
        stringSingleLinkedList.add("Собака");
        assertTrue(stringSingleLinkedList.remove("Собака"));
        assertEquals(3, stringSingleLinkedList.getSizeList());
        assertEquals("Привет", stringSingleLinkedList.get(0));
        assertEquals("меня", stringSingleLinkedList.get(1));
        assertEquals("зовут", stringSingleLinkedList.get(2));
        stringSingleLinkedList.add("Собака",3);
        assertEquals(4, stringSingleLinkedList.getSizeList());
        assertEquals("Привет", stringSingleLinkedList.get(0));
        assertEquals("меня", stringSingleLinkedList.get(1));
        assertEquals("зовут", stringSingleLinkedList.get(2));
        assertEquals("Собака", stringSingleLinkedList.get(3));

    }

    @Test
    void removeOfManyOfElement(){
        SingleLinkedList<String> stringSingleLinkedList = new SingleLinkedList<>();
        stringSingleLinkedList.add ("Привет");
        stringSingleLinkedList.add(",");
        stringSingleLinkedList.add("меня");
        stringSingleLinkedList.add("зовут");
        stringSingleLinkedList.add("Собака");
        assertTrue(stringSingleLinkedList.remove("меня"));
        assertEquals(4, stringSingleLinkedList.getSizeList());
        assertEquals("Привет", stringSingleLinkedList.get(0));
        assertEquals(",", stringSingleLinkedList.get(1));
        assertEquals("зовут", stringSingleLinkedList.get(2));
        assertEquals("Собака", stringSingleLinkedList.get(3));
    }

    @Test
    void removeZeroElement() {
        SingleLinkedList<String> stringSingleLinkedList = new SingleLinkedList<>();
        assertFalse(stringSingleLinkedList.remove("Привет"));
        assertEquals(0, stringSingleLinkedList.getSizeList());
        stringSingleLinkedList.add("Привет");
        stringSingleLinkedList.add("меня");
        stringSingleLinkedList.add("зовут");
        stringSingleLinkedList.add("Собака");
        assertEquals(4, stringSingleLinkedList.getSizeList());
        assertEquals("Привет", stringSingleLinkedList.get(0));
        assertEquals("меня", stringSingleLinkedList.get(1));
        assertEquals("зовут", stringSingleLinkedList.get(2));
        assertEquals("Собака", stringSingleLinkedList.get(3));
    }

    @Test
    void removeZeroOfElement(){
        SingleLinkedList<String> stringSingleLinkedList = new SingleLinkedList<>();
        stringSingleLinkedList.add ("Привет");
        assertFalse(stringSingleLinkedList.remove("Привет!"));
        assertEquals(1, stringSingleLinkedList.getSizeList());
        stringSingleLinkedList.add("меня");
        stringSingleLinkedList.add("зовут");
        stringSingleLinkedList.add("Собака");
        assertEquals(4,stringSingleLinkedList.getSizeList());
        assertEquals("Привет", stringSingleLinkedList.get(0));
        assertEquals("меня", stringSingleLinkedList.get(1));
        assertEquals("зовут", stringSingleLinkedList.get(2));
        assertEquals("Собака", stringSingleLinkedList.get(3));
    }

    @Test
    void removeZeroOf2ElementsOfElement(){
        SingleLinkedList<String> stringSingleLinkedList = new SingleLinkedList<>();
        stringSingleLinkedList.add("Привет");
        stringSingleLinkedList.add("меня");
        assertFalse(stringSingleLinkedList.remove("Привет!"));
        assertEquals(2, stringSingleLinkedList.getSizeList());
        assertEquals("меня", stringSingleLinkedList.get(1));
        stringSingleLinkedList.add("зовут");
        stringSingleLinkedList.add("Собака");
        assertEquals(4,stringSingleLinkedList.getSizeList());
        assertEquals("Привет", stringSingleLinkedList.get(0));
        assertEquals("меня", stringSingleLinkedList.get(1));
        assertEquals("зовут", stringSingleLinkedList.get(2));
        assertEquals("Собака", stringSingleLinkedList.get(3));
    }

    @Test
    void removeZeroOfManyOfElement(){
        SingleLinkedList<String> stringSingleLinkedList = new SingleLinkedList<>();
        stringSingleLinkedList.add("Привет");
        stringSingleLinkedList.add("меня");
        stringSingleLinkedList.add("зовут");
        stringSingleLinkedList.add("Собака");
        assertFalse(stringSingleLinkedList.remove("Привет!"));
        assertEquals(4, stringSingleLinkedList.getSizeList());
        assertEquals("Привет", stringSingleLinkedList.get(0));
        assertEquals("меня", stringSingleLinkedList.get(1));
        assertEquals("зовут", stringSingleLinkedList.get(2));
        assertEquals("Собака", stringSingleLinkedList.get(3));

    }

    @Test
    void clear() {
        SingleLinkedList<String> stringSingleLinkedList = new SingleLinkedList<>();
        stringSingleLinkedList.add("Привет");
        stringSingleLinkedList.add("меня");
        stringSingleLinkedList.add("зовут");
        stringSingleLinkedList.add("Собака");
        stringSingleLinkedList.clear();
        assertEquals(0, stringSingleLinkedList.getSizeList());
    }

    @Test
    void sort() {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
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
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
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
}