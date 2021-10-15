import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * @author JZ
 * @version 2.0
 */

public class HashMapTestOne {

    private HashMap<Integer, String> map;

    @Before
    public void setup() {
        map = new HashMap<>();
    }

    //put method tests
    @Test
    public void addOneElement() {
        assertNull(map.put(0, "A"));

        assertEquals(1, map.size());

        MapEntry<Integer, String>[] expected = (MapEntry<Integer, String>[]) new MapEntry[HashMap.INITIAL_CAPACITY];

        expected[0] = new MapEntry<>(0, "A");
        assertArrayEquals(expected, map.getTable());
    }

    @Test
    public void addTwoElements() {
        assertNull(map.put(0, "A"));
        assertNull(map.put(1, "B"));

        assertEquals(2, map.size());

        MapEntry<Integer, String>[] expected = (MapEntry<Integer, String>[]) new MapEntry[HashMap.INITIAL_CAPACITY];

        expected[0] = new MapEntry<>(0, "A");
        expected[1] = new MapEntry<>(1, "B");
        assertArrayEquals(expected, map.getTable());
    }

    @Test
    public void addFourElements() {
        assertNull(map.put(0, "A"));
        assertNull(map.put(1, "B"));
        assertNull(map.put(2, "C"));
        assertNull(map.put(3, "D"));

        assertEquals(4, map.size());

        MapEntry<Integer, String>[] expected = (MapEntry<Integer, String>[]) new MapEntry[HashMap.INITIAL_CAPACITY];

        expected[0] = new MapEntry<>(0, "A");
        expected[1] = new MapEntry<>(1, "B");
        expected[2] = new MapEntry<>(2, "C");
        expected[3] = new MapEntry<>(3, "D");
        assertArrayEquals(expected, map.getTable());
    }

    @Test
    public void addThreeElementsToSameIndex() {
        assertNull(map.put(0, "A"));
        assertNull(map.put(13, "B"));
        assertNull(map.put(26, "C"));

        assertEquals(3, map.size());
    }

    @Test
    public void resizeCase() {
        assertNull(map.put(0, "A"));
        assertNull(map.put(1, "B"));
        assertNull(map.put(2, "C"));
        assertNull(map.put(3, "D"));
        assertNull(map.put(4, "E"));
        assertNull(map.put(5, "F"));
        assertNull(map.put(6, "G"));
        assertNull(map.put(7, "H"));
        assertNull(map.put(8, "I"));
        assertNull(map.put(9, "J"));
        assertNull(map.put(10, "K"));
        assertNull(map.put(11, "L"));
        assertNull(map.put(12, "M"));

        assertEquals(13, map.size());

        MapEntry<Integer, String>[] expected = (MapEntry<Integer, String>[]) new MapEntry[27];

        expected[0] = new MapEntry<>(0, "A");
        expected[1] = new MapEntry<>(1, "B");
        expected[2] = new MapEntry<>(2, "C");
        expected[3] = new MapEntry<>(3, "D");
        expected[4] = new MapEntry<>(4, "E");
        expected[5] = new MapEntry<>(5, "F");
        expected[6] = new MapEntry<>(6, "G");
        expected[7] = new MapEntry<>(7, "H");
        expected[8] = new MapEntry<>(8, "I");
        expected[9] = new MapEntry<>(9, "J");
        expected[10] = new MapEntry<>(10, "K");
        expected[11] = new MapEntry<>(11, "L");
        expected[12] = new MapEntry<>(12, "M");

        assertArrayEquals(expected, map.getTable());
    }

    @Test
    public void superDuperResizeAndStuff() {
        assertNull(map.put(0, "A"));
        assertNull(map.put(1, "B"));
        assertNull(map.put(2, "C"));
        assertNull(map.put(3, "D"));
        assertNull(map.put(4, "E"));
        assertNull(map.put(5, "F"));
        assertNull(map.put(6, "G"));
        assertNull(map.put(7, "H"));
        assertNull(map.put(8, "I"));
        assertNull(map.put(9, "J"));
        assertNull(map.put(10, "K"));
        assertNull(map.put(11, "L"));
        assertNull(map.put(12, "M"));
        assertNull(map.put(13, "N"));
        assertNull(map.put(14, "O"));
        assertNull(map.put(15, "P"));
        assertNull(map.put(16, "Q"));
        assertNull(map.put(17, "R"));
        assertNull(map.put(18, "S"));
        assertNull(map.put(19, "T"));
        assertNull(map.put(20, "U"));
        assertNull(map.put(21, "V"));
        assertNull(map.put(22, "W"));
        assertNull(map.put(23, "X"));
        assertNull(map.put(24, "Y"));
        assertNull(map.put(25, "Z"));

        assertEquals(26, map.size());

        MapEntry<Integer, String>[] expected = (MapEntry<Integer, String>[]) new MapEntry[55];

        expected[0] = new MapEntry<>(0, "A");
        expected[1] = new MapEntry<>(1, "B");
        expected[2] = new MapEntry<>(2, "C");
        expected[3] = new MapEntry<>(3, "D");
        expected[4] = new MapEntry<>(4, "E");
        expected[5] = new MapEntry<>(5, "F");
        expected[6] = new MapEntry<>(6, "G");
        expected[7] = new MapEntry<>(7, "H");
        expected[8] = new MapEntry<>(8, "I");
        expected[9] = new MapEntry<>(9, "J");
        expected[10] = new MapEntry<>(10, "K");
        expected[11] = new MapEntry<>(11, "L");
        expected[12] = new MapEntry<>(12, "M");
        expected[13] = new MapEntry<>(13, "N");
        expected[14] = new MapEntry<>(14, "O");
        expected[15] = new MapEntry<>(15, "P");
        expected[16] = new MapEntry<>(16, "Q");
        expected[17] = new MapEntry<>(17, "R");
        expected[18] = new MapEntry<>(18, "S");
        expected[19] = new MapEntry<>(19, "T");
        expected[20] = new MapEntry<>(20, "U");
        expected[21] = new MapEntry<>(21, "V");
        expected[22] = new MapEntry<>(22, "W");
        expected[23] = new MapEntry<>(23, "X");
        expected[24] = new MapEntry<>(24, "Y");
        expected[25] = new MapEntry<>(25, "Z");

        assertArrayEquals(expected, map.getTable());
    }

    @Test
    public void resizeAnotherCase() {
        assertNull(map.put(0, "A"));
        assertNull(map.put(1, "B"));
        assertNull(map.put(2, "C"));
        assertNull(map.put(3, "D"));
        assertNull(map.put(4, "E"));
        assertNull(map.put(5, "F"));
        assertNull(map.put(6, "G"));
        assertNull(map.put(7, "H"));
        assertNull(map.put(8, "I"));
        assertNull(map.put(9, "J"));
        assertNull(map.put(10, "K"));
        assertNull(map.put(11, "L"));
        assertNull(map.put(12, "M"));
        assertNull(map.put(13, "N"));
        assertNull(map.put(14, "O"));
        assertNull(map.put(15, "P"));

        assertEquals(16, map.size());

        //In the resize case, the new capacity becomes 2 * n + 1, which is 2 * 13 + 1 or 27
        MapEntry<Integer, String>[] expected = (MapEntry<Integer, String>[]) new MapEntry[27];

        expected[0] = new MapEntry<>(0, "A");
        expected[1] = new MapEntry<>(1, "B");
        expected[2] = new MapEntry<>(2, "C");
        expected[3] = new MapEntry<>(3, "D");
        expected[4] = new MapEntry<>(4, "E");
        expected[5] = new MapEntry<>(5, "F");
        expected[6] = new MapEntry<>(6, "G");
        expected[7] = new MapEntry<>(7, "H");
        expected[8] = new MapEntry<>(8, "I");
        expected[9] = new MapEntry<>(9, "J");
        expected[10] = new MapEntry<>(10, "K");
        expected[11] = new MapEntry<>(11, "L");
        expected[12] = new MapEntry<>(12, "M");
        expected[13] = new MapEntry<>(13, "N");
        expected[14] = new MapEntry<>(14, "O");
        expected[15] = new MapEntry<>(15, "P");

        assertArrayEquals(expected, map.getTable());
    }

    @Test
    public void resizeWithChain() {
        assertNull(map.put(0, "A"));
        assertNull(map.put(1, "B"));
        assertNull(map.put(2, "C"));
        assertNull(map.put(3, "D"));

        /*
            A B C D
         */

        assertNull(map.put(13, "E"));
        assertNull(map.put(14, "F"));
        assertNull(map.put(15, "G"));
        assertNull(map.put(16, "H"));

        /*
            A B C D
            | | | |
            E F G H
         */

        assertNull(map.put(26, "I"));
        assertNull(map.put(27, "J"));
        assertNull(map.put(28, "K"));

        /* RESIZE
            A B
            | |
            J K C D _ _ _ _ _ _ _ _ _ E F G H _ _ _ _ _ _ _ _ _ I
         */

        assertEquals(11, map.size());

        //In the resize case, the new capacity becomes 2 * n + 1, which is 2 * 13 + 1 or 27
        MapEntry<Integer, String>[] expected = (MapEntry<Integer, String>[]) new MapEntry[27];

        expected[0] = new MapEntry<>(27, "J");
        expected[1] = new MapEntry<>(28, "K");
        expected[2] = new MapEntry<>(2, "C");
        expected[3] = new MapEntry<>(3, "D");

        expected[13] = new MapEntry<>(13, "E");
        expected[14] = new MapEntry<>(14, "F");
        expected[15] = new MapEntry<>(15, "G");
        expected[16] = new MapEntry<>(16, "H");

        expected[26] = new MapEntry<>(26, "I");

        MapEntry<Integer, String> curr = map.getTable()[0];
        assertEquals("J", curr.getValue());
        curr = curr.getNext();
        assertEquals("A", curr.getValue());

        curr = map.getTable()[1];
        assertEquals("K", curr.getValue());
        curr = curr.getNext();
        assertEquals("B", curr.getValue());

        assertArrayEquals(expected, map.getTable());
    }


    //remove method tests
    @Test
    public void removeOneElement() {
        assertNull(map.put(0, "A"));

        assertEquals(1, map.size());
        assertEquals("A", map.remove(0));

        MapEntry<Integer, String>[] expected = (MapEntry<Integer, String>[]) new MapEntry[13];
        assertArrayEquals(expected, map.getTable());
    }

    @Test
    public void removeSecondElement() {
        assertNull(map.put(0, "A"));
        assertNull(map.put(1, "B"));

        assertEquals(2, map.size());
        assertEquals("B", map.remove(1));
        assertEquals(1, map.size());

        MapEntry<Integer, String>[] expected = (MapEntry<Integer, String>[]) new MapEntry[13];
        expected[0] = new MapEntry<>(0, "A");
        expected[1] = null;

        assertArrayEquals(expected, map.getTable());
    }


    @Test
    public void removeThirdElement() {
        assertNull(map.put(0, "A"));
        assertNull(map.put(1, "B"));
        assertNull(map.put(2, "C"));

        assertEquals(3, map.size());
        assertEquals("C", map.remove(2));
        assertEquals(2, map.size());

        MapEntry<Integer, String>[] expected = (MapEntry<Integer, String>[]) new MapEntry[13];
        expected[0] = new MapEntry<>(0, "A");
        expected[1] = new MapEntry<>(1, "B");
        expected[2] = null;

        assertArrayEquals(expected, map.getTable());
    }

    @Test
    public void removeLinkedListHead() {
        assertNull(map.put(0, "A"));
        assertNull(map.put(13, "B"));
        assertNull(map.put(26, "C"));
        assertNull(map.put(39, "D"));
        assertNull(map.put(52, "E"));
        assertNull(map.put(65, "F"));                     //Linked List: F-E-D-C-B-A

        assertEquals(6, map.size());            //Linked List: E-D-C-B-A
        assertEquals("F", map.remove(65));
        assertEquals(5, map.size());

        MapEntry<Integer, String> curr = map.getTable()[0];
        assertEquals("E", curr.getValue());
        curr = curr.getNext();
        assertEquals("D", curr.getValue());
        curr = curr.getNext();
        assertEquals("C", curr.getValue());
        curr = curr.getNext();
        assertEquals("B", curr.getValue());
        curr = curr.getNext();
        assertEquals("A", curr.getValue());
    }

    @Test
    public void removeLinkedListSecond() {
        assertNull(map.put(0, "A"));
        assertNull(map.put(13, "B"));
        assertNull(map.put(26, "C"));
        assertNull(map.put(39, "D"));
        assertNull(map.put(52, "E"));
        assertNull(map.put(65, "F"));                     //Linked List: F-E-D-C-B-A

        assertEquals(6, map.size());            //Linked List: F-D-C-B-A
        assertEquals("E", map.remove(52));
        assertEquals(5, map.size());

        MapEntry<Integer, String> curr = map.getTable()[0];
        assertEquals("F", curr.getValue());
        curr = curr.getNext();
        assertEquals("D", curr.getValue());
        curr = curr.getNext();
        assertEquals("C", curr.getValue());
        curr = curr.getNext();
        assertEquals("B", curr.getValue());
        curr = curr.getNext();
        assertEquals("A", curr.getValue());
    }

    @Test
    public void removeLinkedListThird() {
        assertNull(map.put(0, "A"));
        assertNull(map.put(13, "B"));
        assertNull(map.put(26, "C"));
        assertNull(map.put(39, "D"));
        assertNull(map.put(52, "E"));
        assertNull(map.put(65, "F"));                     //Linked List: F-E-D-C-B-A

        assertEquals(6, map.size());                      //Linked List: F-E-C-B-A
        assertEquals("D", map.remove(39));
        assertEquals(5, map.size());

        MapEntry<Integer, String> curr = map.getTable()[0];
        assertEquals("F", curr.getValue());
        curr = curr.getNext();
        assertEquals("E", curr.getValue());
        curr = curr.getNext();
        assertEquals("C", curr.getValue());
        curr = curr.getNext();
        assertEquals("B", curr.getValue());
        curr = curr.getNext();
        assertEquals("A", curr.getValue());
    }

    @Test
    public void removeLinkedListFourth() {
        assertNull(map.put(0, "A"));
        assertNull(map.put(13, "B"));
        assertNull(map.put(26, "C"));
        assertNull(map.put(39, "D"));
        assertNull(map.put(52, "E"));
        assertNull(map.put(65, "F"));                     //Linked List: F-E-D-C-B-A

        assertEquals(6, map.size());             //Linked List: F-E-D-B-A
        assertEquals("C", map.remove(26));
        assertEquals(5, map.size());

        MapEntry<Integer, String> curr = map.getTable()[0];
        assertEquals("F", curr.getValue());
        curr = curr.getNext();
        assertEquals("E", curr.getValue());
        curr = curr.getNext();
        assertEquals("D", curr.getValue());
        curr = curr.getNext();
        assertEquals("B", curr.getValue());
        curr = curr.getNext();
        assertEquals("A", curr.getValue());
    }

    @Test
    public void removeLinkedListFifth() {
        assertNull(map.put(0, "A"));
        assertNull(map.put(13, "B"));
        assertNull(map.put(26, "C"));
        assertNull(map.put(39, "D"));
        assertNull(map.put(52, "E"));
        assertNull(map.put(65, "F"));                     //Linked List: F-E-D-C-B-A

        assertEquals(6, map.size());            //Linked List: F-E-D-C-A
        assertEquals("B", map.remove(13));
        assertEquals(5, map.size());

        MapEntry<Integer, String> curr = map.getTable()[0];
        assertEquals("F", curr.getValue());
        curr = curr.getNext();
        assertEquals("E", curr.getValue());
        curr = curr.getNext();
        assertEquals("D", curr.getValue());
        curr = curr.getNext();
        assertEquals("C", curr.getValue());
        curr = curr.getNext();
        assertEquals("A", curr.getValue());
    }

    @Test
    public void removeLinkedListSixth() {
        assertNull(map.put(0, "A"));
        assertNull(map.put(13, "B"));
        assertNull(map.put(26, "C"));
        assertNull(map.put(39, "D"));
        assertNull(map.put(52, "E"));
        assertNull(map.put(65, "F"));                     //Linked List: F-E-D-C-B-A

        assertEquals(6, map.size());            //Linked List: F-E-D-C-B
        assertEquals("A", map.remove(0));
        assertEquals(5, map.size());

        MapEntry<Integer, String> curr = map.getTable()[0];
        assertEquals("F", curr.getValue());
        curr = curr.getNext();
        assertEquals("E", curr.getValue());
        curr = curr.getNext();
        assertEquals("D", curr.getValue());
        curr = curr.getNext();
        assertEquals("C", curr.getValue());
        curr = curr.getNext();
        assertEquals("B", curr.getValue());
    }

    @Test
    public void removeLinkedListLastElement() {
        assertNull(map.put(0, "A"));
        assertNull(map.put(13, "B"));
        assertNull(map.put(26, "C"));
        assertNull(map.put(39, "D"));
        assertNull(map.put(52, "E"));               //Before Removal: F-E-D-C-B-A
        assertNull(map.put(65, "F"));               //After Removal:  F-E-D-C-B

        assertEquals(6, map.size());
        assertEquals("A", map.remove(0));
        assertEquals(5, map.size());

        MapEntry<Integer, String> curr = map.getTable()[0];

        assertEquals("F", curr.getValue());
        curr = curr.getNext();
        assertEquals("E", curr.getValue());
        curr = curr.getNext();
        assertEquals("D", curr.getValue());
        curr = curr.getNext();
        assertEquals("C", curr.getValue());
        curr = curr.getNext();
        assertEquals("B", curr.getValue());
    }

    //get method tests
    @Test
    public void getLinkedListElements() {
        assertNull(map.put(0, "A"));
        assertNull(map.put(13, "B"));
        assertNull(map.put(26, "C"));
        assertNull(map.put(39, "D"));
        assertNull(map.put(52, "E"));
        assertNull(map.put(65, "F"));                     //Linked List: F-E-D-C-B-A

        MapEntry<Integer, String> curr = map.getTable()[0];
        assertEquals("F", curr.getValue());
        curr = curr.getNext();
        assertEquals("E", curr.getValue());
        curr = curr.getNext();
        assertEquals("D", curr.getValue());
        curr = curr.getNext();
        assertEquals("C", curr.getValue());
        curr = curr.getNext();
        assertEquals("B", curr.getValue());
        curr = curr.getNext();
        assertEquals("A", curr.getValue());

        assertEquals(6, map.size());

        assertEquals("A", map.get(0));
        assertEquals("B", map.get(13));
        assertEquals("C", map.get(26));
        assertEquals("D", map.get(39));
        assertEquals("E", map.get(52));
        assertEquals("F", map.get(65));
    }

    //containsKey method tests
    @Test
    public void containsTestLinkedList() {
        assertNull(map.put(0, "A"));
        assertNull(map.put(13, "B"));
        assertNull(map.put(26, "C"));
        assertNull(map.put(39, "D"));
        assertNull(map.put(52, "E"));
        assertNull(map.put(65, "F"));                     //Linked List: F-E-D-C-B-A

        MapEntry<Integer, String> curr = map.getTable()[0];
        assertEquals("F", curr.getValue());
        curr = curr.getNext();
        assertEquals("E", curr.getValue());
        curr = curr.getNext();
        assertEquals("D", curr.getValue());
        curr = curr.getNext();
        assertEquals("C", curr.getValue());
        curr = curr.getNext();
        assertEquals("B", curr.getValue());
        curr = curr.getNext();
        assertEquals("A", curr.getValue());

        assertEquals(6, map.size());
        assertEquals(true, map.containsKey(0));
        assertEquals(true, map.containsKey(13));
        assertEquals(true, map.containsKey(26));
        assertEquals(true, map.containsKey(39));
        assertEquals(true, map.containsKey(52));
        assertEquals(true, map.containsKey(65));

        assertEquals(false, map.containsKey(1));
        assertEquals(false, map.containsKey(2));
        assertEquals(false, map.containsKey(3));
        assertEquals(false, map.containsKey(4));
    }

    @Test
    public void containsTestNoLinkedList() {
        assertNull(map.put(0, "A"));
        assertNull(map.put(1, "B"));
        assertNull(map.put(2, "C"));
        assertNull(map.put(3, "D"));
        assertNull(map.put(4, "E"));
        assertNull(map.put(5, "F"));                     //A-B-C-D-E-F

        assertEquals(6, map.size());

        MapEntry<Integer, String>[] expected = (MapEntry<Integer, String>[]) new MapEntry[13];

        expected[0] = new MapEntry<>(0, "A");
        expected[1] = new MapEntry<>(1, "B");
        expected[2] = new MapEntry<>(2, "C");
        expected[3] = new MapEntry<>(3, "D");
        expected[4] = new MapEntry<>(4, "E");
        expected[5] = new MapEntry<>(5, "F");

        assertArrayEquals(expected, map.getTable());

        assertEquals(6, map.size());
        assertEquals(true, map.containsKey(0));
        assertEquals(true, map.containsKey(1));
        assertEquals(true, map.containsKey(2));
        assertEquals(true, map.containsKey(3));
        assertEquals(true, map.containsKey(4));
        assertEquals(true, map.containsKey(5));

        assertEquals(false, map.containsKey(6));
        assertEquals(false, map.containsKey(7));
        assertEquals(false, map.containsKey(8));
        assertEquals(false, map.containsKey(9));
    }

    //keySet method tests
    @Test
    public void keySetTest() {
        assertNull(map.put(0, "A"));
        assertNull(map.put(1, "B"));
        assertNull(map.put(2, "C"));
        assertNull(map.put(3, "D"));                    //Values: A-B-C-D-E-F
        assertNull(map.put(4, "E"));                    //Keys: 0,1,2,3,4,5
        assertNull(map.put(5, "F"));
        assertEquals(6, map.size());

        Set<Integer> set = new HashSet();
        set.add(5);
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        set.add(0);

        assertEquals(set, map.keySet());
    }

    @Test
    public void keySetLinkedList() {
        assertNull(map.put(0, "A"));
        assertNull(map.put(13, "B"));
        assertNull(map.put(26, "C"));
        assertNull(map.put(39, "D"));
        assertNull(map.put(52, "E"));
        assertNull(map.put(65, "F"));                       //Linked List: F-E-D-C-B-A
        assertEquals(6, map.size());               //Keys: 0,13,26,39,52,65
        Set<Integer> set = new HashSet();
        set.add(0);
        set.add(13);
        set.add(26);
        set.add(39);
        set.add(52);
        set.add(65);
        assertEquals(set, map.keySet());
    }

    //values method tests
    @Test
    public void valuesElements() {
        assertNull(map.put(0, "A"));
        assertNull(map.put(1, "B"));
        assertNull(map.put(2, "C"));
        assertNull(map.put(3, "D"));
        assertNull(map.put(4, "E"));
        assertNull(map.put(5, "F"));                      //A-B-C-D-E-F
        assertEquals(6, map.size());

        List<String> list = new LinkedList<>();
        list.add(0, "A");
        list.add(1, "B");
        list.add(2, "C");
        list.add(3, "D");
        list.add(4, "E");
        list.add(5, "F");                   //A-B-C-D-E-F

        assertEquals(list, map.values());
    }

    @Test
    public void valuesElementsLinkedList() {
        assertNull(map.put(0, "A"));
        assertNull(map.put(1, "B"));
        assertNull(map.put(2, "C"));               //A-B-C
        assertEquals(3, map.size());

        assertNull(map.put(13, "q"));
        assertNull(map.put(26, "w"));
        assertNull(map.put(39, "x"));
        assertNull(map.put(52, "k"));
        assertNull(map.put(65, "t"));
        assertEquals(8, map.size());

        /*
                    A
                    |
                    q
                    |
                    w
                    |
                    x
                    |
                    k
                    |
                    t-B-C
         */

        List<String> list = new LinkedList<>();

        list.add(0, "t");
        list.add(1, "k");
        list.add(2, "x");
        list.add(3, "w");
        list.add(4, "q");
        list.add(5, "A");                   //A-B-C

        list.add(6, "B");
        list.add(7, "C");                   //t-k-x-w-q-A-B-C

        assertEquals(list, map.values());
    }

    //resizeBackingTable method tests
    @Test
    public void resizeBackingTableToSize() {
        assertNull(map.put(0, "A"));
        assertNull(map.put(1, "B"));
        assertNull(map.put(2, "C"));
        assertNull(map.put(3, "D"));
        assertNull(map.put(4, "E"));
        assertNull(map.put(5, "F"));
        assertNull(map.put(6, "G"));

        map.resizeBackingTable(7);

        assertEquals(7, map.size());            //Table: A B C D E F G

        MapEntry<Integer, String>[] expected = (MapEntry<Integer, String>[]) new MapEntry[7];
        expected[0] = new MapEntry<>(0, "A");
        expected[1] = new MapEntry<>(1, "B");
        expected[2] = new MapEntry<>(2, "C");
        expected[3] = new MapEntry<>(3, "D");
        expected[4] = new MapEntry<>(4, "E");
        expected[5] = new MapEntry<>(5, "F");
        expected[6] = new MapEntry<>(6, "G");

        assertArrayEquals(expected, map.getTable());
    }

    @Test
    public void resizeGreaterSize() {
        assertNull(map.put(0, "A"));
        assertNull(map.put(1, "B"));
        assertNull(map.put(2, "C"));
        assertNull(map.put(3, "D"));
        assertNull(map.put(4, "E"));
        assertNull(map.put(5, "F"));
        assertNull(map.put(6, "G"));
        assertNull(map.put(7, "H"));
        assertNull(map.put(8, "I"));            //Table: A B C D E F G H I

        map.resizeBackingTable(20);

        assertEquals(9, map.size());

        MapEntry<Integer, String>[] expected = (MapEntry<Integer, String>[]) new MapEntry[20];
        expected[0] = new MapEntry<>(0, "A");
        expected[1] = new MapEntry<>(1, "B");
        expected[2] = new MapEntry<>(2, "C");
        expected[3] = new MapEntry<>(3, "D");
        expected[4] = new MapEntry<>(4, "E");
        expected[5] = new MapEntry<>(5, "F");
        expected[6] = new MapEntry<>(6, "G");
        expected[7] = new MapEntry<>(7, "H");
        expected[8] = new MapEntry<>(8, "I");

        assertArrayEquals(expected, map.getTable());
    }

    @Test
    public void getElements() {
        assertNull(map.put(0, "A"));
        assertNull(map.put(13, "B"));
        assertNull(map.put(26, "C"));
        assertNull(map.put(39, "D"));
        assertNull(map.put(52, "E"));
        assertNull(map.put(65, "F"));               //Linked List at A: F-E-D-C-B-A

        assertNull(map.put(1, "G"));
        assertNull(map.put(2, "H"));
        /*
                    A
                    |
                    B
                    |
                    C
                    |
                    D
                    |
                    E
                    |
                    F-G-H
         */

        /**
         * Resize here as the ninth element is being added because
         * the new load factor will be greater than the maximum.
         */
        assertNull(map.put(3, "I"));

        /**
         * The resized table will have length 27 and will look like
         *
         *  A G H I _ _ _ _ _ _ _ F D B _ _ _ _ _ _ _ _ _ _ _ E C
         */

        MapEntry<Integer, String> curr = map.getTable()[0];

        MapEntry<Integer, String>[] expected = (MapEntry<Integer, String>[]) new MapEntry[27];
        expected[0] = new MapEntry<>(0, "A");
        expected[1] = new MapEntry<>(1, "G");
        expected[2] = new MapEntry<>(2, "H");
        expected[3] = new MapEntry<>(3, "I");
        expected[11] = new MapEntry<>(65, "F");
        expected[12] = new MapEntry<>(39, "D");
        expected[13] = new MapEntry<>(13, "B");
        expected[25] = new MapEntry<>(52, "E");
        expected[26] = new MapEntry<>(26, "C");

        assertEquals(9, map.size());
        assertEquals("A", map.get(0));
        assertEquals("B", map.get(13));
        assertEquals("C", map.get(26));
        assertEquals("D", map.get(39));
        assertEquals("E", map.get(52));
        assertEquals("F", map.get(65));

        assertEquals("G", map.get(1));
        assertEquals("H", map.get(2));
        assertEquals("I", map.get(3));
    }
}