import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * This is a basic set of unit tests for HashMap.
 *
 * Passing these tests doesn't guarantee any grade on these assignments. These
 * student JUnits that we provide should be thought of as a sanity check to
 * help you get started on the homework and writing JUnits in general.
 *
 * We highly encourage you to write your own set of JUnits for each homework
 * to cover edge cases you can think of for each data structure. Your code must
 * work correctly and efficiently in all cases, which is why it's important
 * to write comprehensive tests to cover as many cases as possible.
 *
 * @author CS 1332 TAs
 * @version 1.0
 */
public class HashMapStudentTest {

    private static final int TIMEOUT = 200;
    private HashMap<Integer, String> map;

    @Before
    public void setUp() {
        map = new HashMap<>();
    }

    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        assertEquals(0, map.size());
        assertArrayEquals(new MapEntry[HashMap
            .INITIAL_CAPACITY], map.getTable());
    }

    @Test(timeout = TIMEOUT)
    @SuppressWarnings("unchecked")
    public void testPut() {
        // [(0, A), (1, B), (2, C), (3, D), (4, E), _, _, _, _, _, _, _, _]
        assertNull(map.put(0, "A"));
        assertNull(map.put(1, "B"));
        assertNull(map.put(2, "C"));
        assertNull(map.put(3, "D"));
        assertNull(map.put(4, "E"));

        assertEquals(5, map.size());
        MapEntry<Integer, String>[] expected =
            (MapEntry<Integer, String>[])
                new MapEntry[HashMap
                    .INITIAL_CAPACITY];
        expected[0] = new MapEntry<>(0, "A");
        expected[1] = new MapEntry<>(1, "B");
        expected[2] = new MapEntry<>(2, "C");
        expected[3] = new MapEntry<>(3, "D");
        expected[4] = new MapEntry<>(4, "E");
        assertArrayEquals(expected, map.getTable());
    }

    @Test(timeout = TIMEOUT)
    @SuppressWarnings("unchecked")
    public void testRemove() {
        String temp = "D";

        // [(0, A), (1, B), (2, C), (3, D), (4, E), _, _, _, _, _, _, _, _]
        assertNull(map.put(0, "A"));
        assertNull(map.put(1, "B"));
        assertNull(map.put(2, "C"));
        assertNull(map.put(3, temp));
        assertNull(map.put(4, "E"));
        assertEquals(5, map.size());

        // [(0, A), (1, B), (2, C), _, (4, E), _, _, _, _, _, _, _, _]
        assertSame(temp, map.remove(3));

        assertEquals(4, map.size());

        MapEntry<Integer, String>[] expected =
            (MapEntry<Integer, String>[])
                new MapEntry[HashMap
                    .INITIAL_CAPACITY];
        expected[0] = new MapEntry<>(0, "A");
        expected[1] = new MapEntry<>(1, "B");
        expected[2] = new MapEntry<>(2, "C");
        expected[4] = new MapEntry<>(4, "E");
        assertArrayEquals(expected, map.getTable());
    }

    @Test(timeout = TIMEOUT)
    public void testGet() {
        // [(0, A), (1, B), (2, C), (3, D), (4, E), _, _, _, _, _, _, _, _]
        assertNull(map.put(0, "A"));
        assertNull(map.put(1, "B"));
        assertNull(map.put(2, "C"));
        assertNull(map.put(3, "D"));
        assertNull(map.put(4, "E"));
        assertEquals(5, map.size());

        assertEquals("A", map.get(0));
        assertEquals("B", map.get(1));
        assertEquals("C", map.get(2));
        assertEquals("D", map.get(3));
        assertEquals("E", map.get(4));
    }

    @Test(timeout = TIMEOUT)
    public void testContainsKey() {
        // [(0, A), (1, B), (2, C), (3, D), (4, E), _, _, _, _, _, _, _, _]
        assertNull(map.put(0, "A"));
        assertNull(map.put(1, "B"));
        assertNull(map.put(2, "C"));
        assertNull(map.put(3, "D"));
        assertNull(map.put(4, "E"));
        assertEquals(5, map.size());

        assertTrue(map.containsKey(3));
        assertFalse(map.containsKey(5));
    }

    @Test(timeout = TIMEOUT)
    public void testKeySet() {
        // [(0, A), (1, B), (2, C), (3, D), (4, E), _, _, _, _, _, _, _, _]
        assertNull(map.put(0, "A"));
        assertNull(map.put(1, "B"));
        assertNull(map.put(2, "C"));
        assertNull(map.put(3, "D"));
        assertNull(map.put(4, "E"));
        assertEquals(5, map.size());

        Set<Integer> expected = new HashSet<>();
        expected.add(0);
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(4);
        assertEquals(expected, map.keySet());
    }

    @Test(timeout = TIMEOUT)
    public void testValues() {
        // [(0, A), (1, B), (2, C), (3, D), (4, E), _, _, _, _, _, _, _, _]
        assertNull(map.put(0, "A"));
        assertNull(map.put(1, "B"));
        assertNull(map.put(2, "C"));
        assertNull(map.put(3, "D"));
        assertNull(map.put(4, "E"));
        assertEquals(5, map.size());

        List<String> expected = new LinkedList<>();
        expected.add("A");
        expected.add("B");
        expected.add("C");
        expected.add("D");
        expected.add("E");
        assertEquals(expected, map.values());
    }

    @Test(timeout = TIMEOUT)
    @SuppressWarnings("unchecked")
    public void testResize() {
        // [(0, A), (1, B), (2, C), (3, D), (4, E), _, _, _, _, _, _, _, _]
        assertNull(map.put(0, "A"));
        assertNull(map.put(1, "B"));
        assertNull(map.put(2, "C"));
        assertNull(map.put(3, "D"));
        assertNull(map.put(4, "E"));
        assertEquals(5, map.size());

        // [(0, A), (1, B), (2, C), (3, D), (4, E)]
        map.resizeBackingTable(5);

        assertEquals(5, map.size());

        MapEntry<Integer, String>[] expected =
            (MapEntry<Integer, String>[])
                new MapEntry[5];
        expected[0] = new MapEntry<>(0, "A");
        expected[1] = new MapEntry<>(1, "B");
        expected[2] = new MapEntry<>(2, "C");
        expected[3] = new MapEntry<>(3, "D");
        expected[4] = new MapEntry<>(4, "E");
        assertArrayEquals(expected, map.getTable());
    }

    @Test(timeout = TIMEOUT)
    public void testClear() {
        // [(0, A), (1, B), (2, C), (3, D), (4, E), _, _, _, _, _, _, _, _]
        assertNull(map.put(0, "A"));
        assertNull(map.put(1, "B"));
        assertNull(map.put(2, "C"));
        assertNull(map.put(3, "D"));
        assertNull(map.put(4, "E"));
        assertEquals(5, map.size());

        map.clear();
        assertEquals(0, map.size());
        assertArrayEquals(new MapEntry[HashMap
            .INITIAL_CAPACITY], map.getTable());
    }
}
