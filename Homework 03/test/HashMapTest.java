import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * This is an additional set of unit tests for HashMap.
 * Please first pass the basic unit tests before using this set of tests.
 * Please keep in mind that passing these tests in no way guarantees a perfect score in the homework.
 *
 * @author Vignesh Sreedhar (credit to CS 1332 TAs for model tests)
 * @version 1.0
 */
public class HashMapTest {

    private static final int TIMEOUT = 200;
    private HashMap<Integer, String> map;

    @Before
    public void setUp() {
        map = new HashMap<>();
    }

    /**
     * Adapted from testInitialization() from HashMapStudentTest
     */
    @Test(timeout = TIMEOUT)
    public void testConstructorChaining() {
        assertEquals(0, map.size());
        assertArrayEquals(new MapEntry[HashMap
            .INITIAL_CAPACITY], map.getTable());
    }

    @Test(timeout = TIMEOUT)
    @SuppressWarnings("unchecked")
    public void testPutNegative() {
        // [(0, A), (-1, B), (-2, C), (-3, D), _, _, _, _, _, _, _, _, _]
        assertNull(map.put(0, "A"));
        assertNull(map.put(-1, "B"));
        assertNull(map.put(-2, "C"));
        assertNull(map.put(-3, "D"));

        assertEquals(4, map.size());
        MapEntry<Integer, String>[] expected =
                (MapEntry<Integer, String>[])
                        new MapEntry[HashMap
                                .INITIAL_CAPACITY];
        expected[0] = new MapEntry<>(0, "A");
        expected[1] = new MapEntry<>(-1, "B");
        expected[2] = new MapEntry<>(-2, "C");
        expected[3] = new MapEntry<>(-3, "D");
        assertArrayEquals(expected, map.getTable());
    }

    /**
     * Adapted from testPut() from HashMapStudentTest
     */
    @Test(timeout = TIMEOUT)
    @SuppressWarnings("unchecked")
    public void testPutSimple() {
        // [(0, A), (1, B), (2, C), (3, D), _, _, _, _, _, _, _, _, _]
        assertNull(map.put(0, "A"));
        assertNull(map.put(1, "B"));
        assertNull(map.put(2, "C"));
        assertNull(map.put(3, "D"));

        assertEquals(4, map.size());
        MapEntry<Integer, String>[] expected =
            (MapEntry<Integer, String>[])
                new MapEntry[HashMap
                    .INITIAL_CAPACITY];
        expected[0] = new MapEntry<>(0, "A");
        expected[1] = new MapEntry<>(1, "B");
        expected[2] = new MapEntry<>(2, "C");
        expected[3] = new MapEntry<>(3, "D");
        assertArrayEquals(expected, map.getTable());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPutException() {
        map.put(0, null);
    }

    @Test(timeout = TIMEOUT)
    @SuppressWarnings("unchecked")
    public void testPutChaining() {
        // [(26, H) -> (13, F) -> (0, A), (14, G) -> (1, B), (2, C), (3, D), (4, E), _, _, _, _, _, _, _, _]
        assertNull(map.put(0, "A"));
        assertNull(map.put(1, "B"));
        assertNull(map.put(2, "C"));
        assertNull(map.put(3, "D"));
        assertNull(map.put(4, "E"));
        assertNull(map.put(13, "F"));
        assertNull(map.put(14, "G"));
        assertNull(map.put(26, "H"));

        assertEquals(8, map.size());
        MapEntry<Integer, String>[] expected =
                (MapEntry<Integer, String>[])
                        new MapEntry[HashMap
                                .INITIAL_CAPACITY];
        expected[0] = new MapEntry<>(26, "H",
                new MapEntry<>(13, "F", new MapEntry<>(0, "A")));
        expected[1] = new MapEntry<>(14, "G", new MapEntry<>(1, "B"));
        expected[2] = new MapEntry<>(2, "C");
        expected[3] = new MapEntry<>(3, "D");
        expected[4] = new MapEntry<>(4, "E");
        assertArrayEquals(expected, map.getTable());
    }

    @Test(timeout = TIMEOUT)
    @SuppressWarnings("unchecked")
    public void testPutDuplicates() {
        // [(0, A), (1, B), (2, C), (3, D), (4, E), _, _, _, _, _, _, _, _]
        assertNull(map.put(0, "A"));
        assertNull(map.put(1, "B"));
        assertNull(map.put(2, "C"));
        assertNull(map.put(3, "D"));
        assertNull(map.put(4, "E"));
        assertEquals("A", map.put(0, "A"));

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
    public void testResizeLoadFactorSimple() {
        // [(0, A), (1, B), (2, C), (3, D), (4, E), (5, F), (6, G), (7, H), (8, I), _, _, _,
        // _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]
        assertNull(map.put(0, "A"));
        assertNull(map.put(1, "B"));
        assertNull(map.put(2, "C"));
        assertNull(map.put(3, "D"));
        assertNull(map.put(4, "E"));
        assertNull(map.put(5, "F"));
        assertNull(map.put(6, "G"));
        assertNull(map.put(7, "H"));
        assertNull(map.put(8, "I"));

        assertEquals(9, map.size());
        assertEquals(27, map.getTable().length);
        MapEntry<Integer, String>[] expected =
                (MapEntry<Integer, String>[])
                        new MapEntry[HashMap
                                .INITIAL_CAPACITY * 2 + 1];
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

    @Test(timeout = TIMEOUT)
    @SuppressWarnings("unchecked")
    public void testResizeLoadFactorRehashSimple() {
        // [(0, A), (1, B), (2, C), (3, D), (4, E), _, _, _, (8, I), _, _, _,
        // _, (13, F), (14, G), (15, H), _, _, _, _, _, _, _, _, _, _, (26, I)]
        assertNull(map.put(0, "A"));
        assertNull(map.put(1, "B"));
        assertNull(map.put(2, "C"));
        assertNull(map.put(3, "D"));
        assertNull(map.put(4, "E"));
        assertNull(map.put(13, "F"));
        assertNull(map.put(14, "G"));
        assertNull(map.put(15, "H"));
        assertNull(map.put(26, "I"));

        assertEquals(9, map.size());
        assertEquals(27, map.getTable().length);
        MapEntry<Integer, String>[] expected =
                (MapEntry<Integer, String>[])
                        new MapEntry[HashMap
                                .INITIAL_CAPACITY * 2 + 1];
        expected[0] = new MapEntry<>(0, "A");
        expected[1] = new MapEntry<>(1, "B");
        expected[2] = new MapEntry<>(2, "C");
        expected[3] = new MapEntry<>(3, "D");
        expected[4] = new MapEntry<>(4, "E");
        expected[13] = new MapEntry<>(13, "F");
        expected[14] = new MapEntry<>(14, "G");
        expected[15] = new MapEntry<>(15, "H");
        expected[26] = new MapEntry<>(26, "I");
        assertArrayEquals(expected, map.getTable());
    }

    @Test(timeout = TIMEOUT)
    @SuppressWarnings("unchecked")
    public void testResizeLoadFactorRehashChaining() {
        // [(0, A), (1, B), (2, C), (3, D), (4, E), _, _, _, (8, I), _, _, _,
        // _, (40, I) -> (13, F), (14, G), _, _, _, _, _, _, _, _, _, _, _, (26, H)]
        assertNull(map.put(0, "A"));
        assertNull(map.put(1, "B"));
        assertNull(map.put(2, "C"));
        assertNull(map.put(3, "D"));
        assertNull(map.put(4, "E"));
        assertNull(map.put(13, "F"));
        assertNull(map.put(14, "G"));
        assertNull(map.put(26, "H"));
        assertNull(map.put(40, "I"));

        assertEquals(9, map.size());
        assertEquals(27, map.getTable().length);
        MapEntry<Integer, String>[] expected =
                (MapEntry<Integer, String>[])
                        new MapEntry[HashMap
                                .INITIAL_CAPACITY * 2 + 1];
        expected[0] = new MapEntry<>(0, "A");
        expected[1] = new MapEntry<>(1, "B");
        expected[2] = new MapEntry<>(2, "C");
        expected[3] = new MapEntry<>(3, "D");
        expected[4] = new MapEntry<>(4, "E");
        expected[13] = new MapEntry<>(40, "I", new MapEntry<>(13, "F"));
        expected[14] = new MapEntry<>(14, "G");
        expected[26] = new MapEntry<>(26, "H");
        assertArrayEquals(expected, map.getTable());
    }

    @Test(timeout = TIMEOUT)
    @SuppressWarnings("unchecked")
    public void testRemoveNegative() {
        String temp = "C";

        // [(0, A), (-1, B), (-2, C), (-3, D), _, _, _, _, _, _, _, _, _]
        assertNull(map.put(0, "A"));
        assertNull(map.put(-1, "B"));
        assertNull(map.put(-2, temp));
        assertNull(map.put(-3, "D"));
        assertEquals(4, map.size());

        // [(0, A), (1, B), _, (3, D), _, _, _, _, _, _, _, _, _]
        assertSame(temp, map.remove(-2));

        assertEquals(3, map.size());

        MapEntry<Integer, String>[] expected =
                (MapEntry<Integer, String>[])
                        new MapEntry[HashMap
                                .INITIAL_CAPACITY];
        expected[0] = new MapEntry<>(0, "A");
        expected[1] = new MapEntry<>(-1, "B");
        expected[3] = new MapEntry<>(-3, "D");
        assertArrayEquals(expected, map.getTable());
    }

    /**
     * Adapted from testRemove() from HashMapStudentTest
     */
    @Test(timeout = TIMEOUT)
    @SuppressWarnings("unchecked")
    public void testRemoveSimple() {
        String temp = "C";

        // [(0, A), (1, B), (2, C), (3, D), _, _, _, _, _, _, _, _, _]
        assertNull(map.put(0, "A"));
        assertNull(map.put(1, "B"));
        assertNull(map.put(2, temp));
        assertNull(map.put(3, "D"));
        assertEquals(4, map.size());

        // [(0, A), (1, B), _, (3, D), _, _, _, _, _, _, _, _, _]
        assertSame(temp, map.remove(2));

        assertEquals(3, map.size());

        MapEntry<Integer, String>[] expected =
            (MapEntry<Integer, String>[])
                new MapEntry[HashMap
                    .INITIAL_CAPACITY];
        expected[0] = new MapEntry<>(0, "A");
        expected[1] = new MapEntry<>(1, "B");
        expected[3] = new MapEntry<>(3, "D");
        assertArrayEquals(expected, map.getTable());
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveException() {
        // [(0, A), (1, B), (2, C), (3, D), _, _, _, _, _, _, _, _, _]
        assertNull(map.put(0, "A"));
        assertNull(map.put(1, "B"));
        assertNull(map.put(2, "C"));
        assertNull(map.put(3, "D"));
        assertEquals(4, map.size());
        map.remove(4);
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveNegativeException() {
        // [(0, A), (1, B), (2, C), (3, D), _, _, _, _, _, _, _, _, _]
        assertNull(map.put(0, "A"));
        assertNull(map.put(1, "B"));
        assertNull(map.put(-2, "C"));
        assertNull(map.put(3, "D"));
        assertEquals(4, map.size());
        map.remove(2);
    }

    @Test(timeout = TIMEOUT)
    @SuppressWarnings("unchecked")
    public void testRemoveEndChain() {
        // [(26, H) -> (13, F) -> (0, A), (14, G) -> (1, B), (2, C), (3, D), (4, E), _, _, _, _, _, _, _, _]
        assertNull(map.put(0, "A"));
        assertNull(map.put(1, "B"));
        assertNull(map.put(2, "C"));
        assertNull(map.put(3, "D"));
        assertNull(map.put(4, "E"));
        assertNull(map.put(13, "F"));
        assertNull(map.put(14, "G"));
        assertNull(map.put(26, "H"));
        assertEquals(8, map.size());


        // [(26, H) -> (13, F), (14, G) -> (1, B), (2, C), (3, D), (4, E), _, _, _, _, _, _, _, _]
        assertEquals("A", map.remove(0));
        assertEquals(7, map.size());

        MapEntry<Integer, String>[] expected =
                (MapEntry<Integer, String>[])
                        new MapEntry[HashMap
                                .INITIAL_CAPACITY];
        expected[0] = new MapEntry<>(26, "H", new MapEntry<>(13, "F"));
        expected[1] = new MapEntry<>(14, "G", new MapEntry<>(1, "B"));
        expected[2] = new MapEntry<>(2, "C");
        expected[3] = new MapEntry<>(3, "D");
        expected[4] = new MapEntry<>(4, "E");
        assertArrayEquals(expected, map.getTable());
    }

    @Test(timeout = TIMEOUT)
    @SuppressWarnings("unchecked")
    public void testRemoveMidChain() {
        // [(26, H) -> (13, F) -> (0, A), (14, G) -> (1, B), (2, C), (3, D), (4, E), _, _, _, _, _, _, _, _]
        assertNull(map.put(0, "A"));
        assertNull(map.put(1, "B"));
        assertNull(map.put(2, "C"));
        assertNull(map.put(3, "D"));
        assertNull(map.put(4, "E"));
        assertNull(map.put(13, "F"));
        assertNull(map.put(14, "G"));
        assertNull(map.put(26, "H"));
        assertEquals(8, map.size());


        // [(26, H) -> (0, A), (14, G) -> (1, B), (2, C), (3, D), (4, E), _, _, _, _, _, _, _, _]
        assertEquals("F", map.remove(13));
        assertEquals(7, map.size());

        MapEntry<Integer, String>[] expected =
                (MapEntry<Integer, String>[])
                        new MapEntry[HashMap
                                .INITIAL_CAPACITY];
        expected[0] = new MapEntry<>(26, "H", new MapEntry<>(0, "A"));
        expected[1] = new MapEntry<>(14, "G", new MapEntry<>(1, "B"));
        expected[2] = new MapEntry<>(2, "C");
        expected[3] = new MapEntry<>(3, "D");
        expected[4] = new MapEntry<>(4, "E");
        assertArrayEquals(expected, map.getTable());
    }

    @Test(timeout = TIMEOUT)
    @SuppressWarnings("unchecked")
    public void testRemoveStartChain() {
        // [(26, H) -> (13, F) -> (0, A), (14, G) -> (1, B), (2, C), (3, D), (4, E), _, _, _, _, _, _, _, _]
        assertNull(map.put(0, "A"));
        assertNull(map.put(1, "B"));
        assertNull(map.put(2, "C"));
        assertNull(map.put(3, "D"));
        assertNull(map.put(4, "E"));
        assertNull(map.put(13, "F"));
        assertNull(map.put(14, "G"));
        assertNull(map.put(26, "H"));
        assertEquals(8, map.size());


        // [(13, F) -> (0, A), (14, G) -> (1, B), (2, C), (3, D), (4, E), _, _, _, _, _, _, _, _]
        assertEquals("H", map.remove(26));
        assertEquals(7, map.size());

        MapEntry<Integer, String>[] expected =
                (MapEntry<Integer, String>[])
                        new MapEntry[HashMap
                                .INITIAL_CAPACITY];
        expected[0] = new MapEntry<>(13, "F", new MapEntry<>(0, "A"));
        expected[1] = new MapEntry<>(14, "G", new MapEntry<>(1, "B"));
        expected[2] = new MapEntry<>(2, "C");
        expected[3] = new MapEntry<>(3, "D");
        expected[4] = new MapEntry<>(4, "E");
        assertArrayEquals(expected, map.getTable());
    }

    @Test(timeout = TIMEOUT)
    public void testGetNegative() {
        // [(0, A), (-1, B), (-2, C), (-3, D), _, _, _, _, _, _, _, _, _]
        assertNull(map.put(0, "A"));
        assertNull(map.put(-1, "B"));
        assertNull(map.put(-2, "C"));
        assertNull(map.put(-3, "D"));
        assertEquals(4, map.size());

        assertEquals("A", map.get(0));
        assertEquals("B", map.get(-1));
        assertEquals("C", map.get(-2));
        assertEquals("D", map.get(-3));
    }

    /**
     * Adapted from testGet() from HashMapStudentTest
     */
    @Test(timeout = TIMEOUT)
    public void testGetSimple() {
        // [(0, A), (1, B), (2, C), (3, D), _, _, _, _, _, _, _, _, _]
        assertNull(map.put(0, "A"));
        assertNull(map.put(1, "B"));
        assertNull(map.put(2, "C"));
        assertNull(map.put(3, "D"));
        assertEquals(4, map.size());

        assertEquals("A", map.get(0));
        assertEquals("B", map.get(1));
        assertEquals("C", map.get(2));
        assertEquals("D", map.get(3));
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetException() {
        // [(0, A), (1, B), (2, C), (3, D), _, _, _, _, _, _, _, _, _]
        assertNull(map.put(0, "A"));
        assertNull(map.put(1, "B"));
        assertNull(map.put(2, "C"));
        assertNull(map.put(3, "D"));
        assertEquals(4, map.size());

        assertEquals("A", map.get(0));
        assertEquals("B", map.get(1));
        assertEquals("C", map.get(2));
        assertEquals("D", map.get(3));
        assertEquals("D", map.get(4));
    }

    @Test(timeout = TIMEOUT)
    public void testGetChaining() {
        // [(26, H) -> (13, F) -> (0, A), (14, G) -> (1, B), (2, C), (3, D), (4, E), _, _, _, _, _, _, _, _]
        assertNull(map.put(0, "A"));
        assertNull(map.put(1, "B"));
        assertNull(map.put(2, "C"));
        assertNull(map.put(3, "D"));
        assertNull(map.put(4, "E"));
        assertNull(map.put(13, "F"));
        assertNull(map.put(14, "G"));
        assertNull(map.put(26, "H"));

        assertEquals(8, map.size());

        assertEquals("A", map.get(0));
        assertEquals("B", map.get(1));
        assertEquals("C", map.get(2));
        assertEquals("D", map.get(3));
        assertEquals("E", map.get(4));
        assertEquals("F", map.get(13));
        assertEquals("G", map.get(14));
        assertEquals("H", map.get(26));
    }

    @Test(timeout = TIMEOUT)
    public void testContainsKeyNegative() {
        // [(0, A), (-1, B), (2, C), (-3, D), _, _, _, _, _, _, _, _, _]
        assertNull(map.put(0, "A"));
        assertNull(map.put(-1, "B"));
        assertNull(map.put(2, "C"));
        assertNull(map.put(-3, "D"));
        assertEquals(4, map.size());

        assertTrue(map.containsKey(-1));
        assertFalse(map.containsKey(-2));
        assertTrue(map.containsKey(2));
        assertFalse(map.containsKey(3));
    }

    /**
     * Adapted from testContainsKey() from HashMapStudentTest
     */
    @Test(timeout = TIMEOUT)
    public void testContainsKeySimple() {
        // [(0, A), (1, B), (2, C), (3, D), _, _, _, _, _, _, _, _, _]
        assertNull(map.put(0, "A"));
        assertNull(map.put(1, "B"));
        assertNull(map.put(2, "C"));
        assertNull(map.put(3, "D"));
        assertEquals(4, map.size());

        assertTrue(map.containsKey(0));
        assertFalse(map.containsKey(5));
        assertTrue(map.containsKey(1));
        assertFalse(map.containsKey(6));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testContainsKeyException() {
        map.containsKey(null);
    }

    @Test(timeout = TIMEOUT)
    public void testContainsKeyChaining() {
        // [(26, H) -> (13, F) -> (0, A), (14, G) -> (1, B), (2, C), (3, D), (4, E), _, _, _, _, _, _, _, _]
        assertNull(map.put(0, "A"));
        assertNull(map.put(1, "B"));
        assertNull(map.put(2, "C"));
        assertNull(map.put(3, "D"));
        assertNull(map.put(4, "E"));
        assertNull(map.put(13, "F"));
        assertNull(map.put(14, "G"));
        assertNull(map.put(26, "H"));
        assertEquals(8, map.size());

        assertTrue(map.containsKey(0));
        assertFalse(map.containsKey(15));
        assertTrue(map.containsKey(13));
        assertFalse(map.containsKey(15));
        assertTrue(map.containsKey(26));
        assertFalse(map.containsKey(16));
        assertTrue(map.containsKey(1));
        assertFalse(map.containsKey(7));
        assertTrue(map.containsKey(14));
        assertFalse(map.containsKey(8));
    }

    @Test(timeout = TIMEOUT)
    public void testKeySetNegative() {
        // [(0, A), (-1, B), (2, C), (-3, D), _, _, _, _, _, _, _, _, _]
        assertNull(map.put(0, "A"));
        assertNull(map.put(-1, "B"));
        assertNull(map.put(2, "C"));
        assertNull(map.put(-3, "D"));
        assertEquals(4, map.size());

        // [0, 1, 2, 3] in any order
        Set<Integer> hashSet = map.keySet();
        assertTrue(hashSet.contains(0));
        assertTrue(hashSet.contains(-1));
        assertTrue(hashSet.contains(2));
        assertTrue(hashSet.contains(-3));
        assertEquals(4, hashSet.size());
    }

    /**
     * Adapted from testKeySet() from HashMapStudentTest
     */
    @Test(timeout = TIMEOUT)
    public void testKeySetSimple() {
        // [(0, A), (1, B), (2, C), (3, D), _, _, _, _, _, _, _, _, _]
        assertNull(map.put(0, "A"));
        assertNull(map.put(1, "B"));
        assertNull(map.put(2, "C"));
        assertNull(map.put(3, "D"));
        assertEquals(4, map.size());

        // [0, 1, 2, 3] in any order
        Set<Integer> hashSet = map.keySet();
        assertTrue(hashSet.contains(0));
        assertTrue(hashSet.contains(1));
        assertTrue(hashSet.contains(2));
        assertTrue(hashSet.contains(3));
        assertEquals(4, hashSet.size());
    }

    @Test(timeout = TIMEOUT)
    public void testKeySetChaining() {
        // [(26, H) -> (13, F) -> (0, A), (14, G) -> (1, B), (2, C), (3, D), (4, E), _, _, _, _, _, _, _, _]
        assertNull(map.put(0, "A"));
        assertNull(map.put(1, "B"));
        assertNull(map.put(2, "C"));
        assertNull(map.put(3, "D"));
        assertNull(map.put(4, "E"));
        assertNull(map.put(13, "F"));
        assertNull(map.put(14, "G"));
        assertNull(map.put(26, "H"));
        assertEquals(8, map.size());

        // [26, 13, 0, 14, 1, 2, 3, 4] in any order
        Set<Integer> hashSet = map.keySet();
        assertTrue(hashSet.contains(26));
        assertTrue(hashSet.contains(13));
        assertTrue(hashSet.contains(0));
        assertTrue(hashSet.contains(14));
        assertTrue(hashSet.contains(1));
        assertTrue(hashSet.contains(2));
        assertTrue(hashSet.contains(3));
        assertTrue(hashSet.contains(4));
        assertEquals(8, hashSet.size());
    }

    @Test(timeout = TIMEOUT)
    public void testValuesNegative() {
        // [(0, A), (1, B), (2, C), (3, D), _, _, _, _, _, _, _, _, _]
        assertNull(map.put(0, "A"));
        assertNull(map.put(-1, "B"));
        assertNull(map.put(2, "C"));
        assertNull(map.put(-3, "D"));
        assertEquals(4, map.size());

        // [A, B, C, D]
        List<String> expected = new ArrayList<>();
        expected.add("A");
        expected.add("B");
        expected.add("C");
        expected.add("D");
        assertEquals(expected, map.values());
    }

    /**
     * Adapted from testValues() from HashMapStudentTest
     */
    @Test(timeout = TIMEOUT)
    public void testValuesSimple() {
        // [(0, A), (1, B), (2, C), (3, D), _, _, _, _, _, _, _, _, _]
        assertNull(map.put(0, "A"));
        assertNull(map.put(1, "B"));
        assertNull(map.put(2, "C"));
        assertNull(map.put(3, "D"));
        assertEquals(4, map.size());

        // [A, B, C, D]
        List<String> expected = new ArrayList<>();
        expected.add("A");
        expected.add("B");
        expected.add("C");
        expected.add("D");
        assertEquals(expected, map.values());
    }

    @Test(timeout = TIMEOUT)
    public void testValuesChaining() {
        // [(26, H) -> (13, F) -> (0, A), (14, G) -> (1, B), (2, C), (3, D), (4, E), _, _, _, _, _, _, _, _]
        assertNull(map.put(0, "A"));
        assertNull(map.put(1, "B"));
        assertNull(map.put(2, "C"));
        assertNull(map.put(3, "D"));
        assertNull(map.put(4, "E"));
        assertNull(map.put(13, "F"));
        assertNull(map.put(14, "G"));
        assertNull(map.put(26, "H"));
        assertEquals(8, map.size());

        // [H, F, A, G, B, C, D, E]
        List<String> expected = new LinkedList<>();
        expected.add("H");
        expected.add("F");
        expected.add("A");
        expected.add("G");
        expected.add("B");
        expected.add("C");
        expected.add("D");
        expected.add("E");
        assertEquals(expected, map.values());
    }

    @Test(timeout = TIMEOUT)
    public void testValuesChainingNegative() {
        // [(-26, H) -> (-13, F) -> (0, A), (14, G) -> (1, B), (2, C), (3, D), (4, E), _, _, _, _, _, _, _, _]
        assertNull(map.put(0, "A"));
        assertNull(map.put(1, "B"));
        assertNull(map.put(2, "C"));
        assertNull(map.put(3, "D"));
        assertNull(map.put(4, "E"));
        assertNull(map.put(-13, "F"));
        assertNull(map.put(14, "G"));
        assertNull(map.put(-26, "H"));
        assertEquals(8, map.size());

        // [H, F, A, G, B, C, D, E]
        List<String> expected = new LinkedList<>();
        expected.add("H");
        expected.add("F");
        expected.add("A");
        expected.add("G");
        expected.add("B");
        expected.add("C");
        expected.add("D");
        expected.add("E");
        assertEquals(expected, map.values());
    }

    @Test(timeout = TIMEOUT)
    @SuppressWarnings("unchecked")
    public void testResizeNegative() {
        // [(0, A), (-1, B), (2, C), (-3, D), _, _, _, _, _, _, _, _, _]
        assertNull(map.put(0, "A"));
        assertNull(map.put(-1, "B"));
        assertNull(map.put(2, "C"));
        assertNull(map.put(-3, "D"));
        assertEquals(4, map.size());

        // [(0, A), (1, B), (2, C), (3, D), (4, E)]
        map.resizeBackingTable(4);

        assertEquals(4, map.size());

        MapEntry<Integer, String>[] expected =
                (MapEntry<Integer, String>[])
                        new MapEntry[4];
        expected[0] = new MapEntry<>(0, "A");
        expected[1] = new MapEntry<>(-1, "B");
        expected[2] = new MapEntry<>(2, "C");
        expected[3] = new MapEntry<>(-3, "D");
        assertArrayEquals(expected, map.getTable());
    }

    /**
     * Adapted from testResize() from HashMapStudentTest
     */
    @Test(timeout = TIMEOUT)
    @SuppressWarnings("unchecked")
    public void testResizeSimple() {
        // [(0, A), (1, B), (2, C), (3, D), _, _, _, _, _, _, _, _, _]
        assertNull(map.put(0, "A"));
        assertNull(map.put(1, "B"));
        assertNull(map.put(2, "C"));
        assertNull(map.put(3, "D"));
        assertEquals(4, map.size());

        // [(0, A), (1, B), (2, C), (3, D), (4, E)]
        map.resizeBackingTable(4);

        assertEquals(4, map.size());

        MapEntry<Integer, String>[] expected =
            (MapEntry<Integer, String>[])
                new MapEntry[4];
        expected[0] = new MapEntry<>(0, "A");
        expected[1] = new MapEntry<>(1, "B");
        expected[2] = new MapEntry<>(2, "C");
        expected[3] = new MapEntry<>(3, "D");
        assertArrayEquals(expected, map.getTable());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testResizeException() {
        // [(26, H) -> (13, F) -> (0, A), (14, G) -> (1, B), (2, C), (3, D), (4, E), _, _, _, _, _, _, _, _]
        assertNull(map.put(0, "A"));
        assertNull(map.put(1, "B"));
        assertNull(map.put(2, "C"));
        assertNull(map.put(3, "D"));
        assertNull(map.put(4, "E"));
        assertNull(map.put(13, "F"));
        assertNull(map.put(14, "G"));
        assertNull(map.put(26, "H"));
        assertEquals(8, map.size());

        map.resizeBackingTable(7);
    }

    /**
     * Adapted from testClear() from HashMapStudentTest
     */
    @Test(timeout = TIMEOUT)
    public void testClearSimple() {
        // [(0, A), (1, B), (2, C), (3, D), _, _, _, _, _, _, _, _, _]
        assertNull(map.put(0, "A"));
        assertNull(map.put(1, "B"));
        assertNull(map.put(2, "C"));
        assertNull(map.put(3, "D"));
        assertEquals(4, map.size());

        map.clear();
        assertEquals(0, map.size());
        assertArrayEquals(new MapEntry[HashMap
            .INITIAL_CAPACITY], map.getTable());
    }

    @Test(timeout = TIMEOUT)
    public void testClearChaining() {
        // [(26, H) -> (13, F) -> (0, A), (14, G) -> (1, B), (2, C), (3, D), (4, E), _, _, _, _, _, _, _, _]
        assertNull(map.put(0, "A"));
        assertNull(map.put(1, "B"));
        assertNull(map.put(2, "C"));
        assertNull(map.put(3, "D"));
        assertNull(map.put(4, "E"));
        assertNull(map.put(13, "F"));
        assertNull(map.put(14, "G"));
        assertNull(map.put(26, "H"));
        assertEquals(8, map.size());

        map.clear();
        assertEquals(0, map.size());
        assertArrayEquals(new MapEntry[HashMap
                .INITIAL_CAPACITY], map.getTable());
    }
}
