import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.Random;

/**
 * This file contains some improved JUnits to test for the min and max values, as well as testing for stability
 * by accounting for duplicates.
 *
 * @author William T. Kaye
 * @version 1.0
 */
public class StudentTest {

    private static final int TIMEOUT = 200;
    private Integer[] real;
    private Integer[] sorted;
    private ComparatorPlus<Integer> comp;

    @Before
    public void setUp() {
        /*
            Unsorted numbers:
                index 0: Integer.MAX_VALUE(a)
                index 1: 0
                index 2: 10
                index 3: Integer.MAX_VALUE(b)
                index 4: 3
                index 5: -3
                index 6: -6
                index 7: 5
                index 8: Integer.MIN_VALUE(a)
                index 9: Integer.MIN_VALUE(b)
         */

        /*
            Sorted Names:
                index 0: Integer.MIN_VALUE(a)
                index 1: Integer.MIN_VALUE(b)
                index 2: -6
                index 3: -3
                index 4: 0
                index 5: 3
                index 6: 5
                index 7: 10
                index 8: Integer.MAX_VALUE(a)
                index 9: Integer.MAX_VALUE(b)
         */

        int minA = Integer.MIN_VALUE;
        int minB = Integer.MIN_VALUE;
        int maxA = Integer.MAX_VALUE;
        int maxB = Integer.MAX_VALUE;
        real = new Integer[10];
        real[0] = maxA;
        real[1] = 0;
        real[2] = 10;
        real[3] = maxB;
        real[4] = 3;
        real[5] = -3;
        real[6] = -6;
        real[7] = 5;
        real[8] = minA;
        real[9] = minB;
        sorted = new Integer[10];
        sorted[0] = real[8];
        sorted[1] = real[9];
        sorted[2] = real[6];
        sorted[3] = real[5];
        sorted[4] = real[1];
        sorted[5] = real[4];
        sorted[6] = real[7];
        sorted[7] = real[2];
        sorted[8] = real[0];
        sorted[9] = real[3];

        comp = TeachingAssistant.getComparator();
    }

    @Test(timeout = TIMEOUT)
    public void testInsertionSort() {
        Sorting.insertionSort(real, comp);
        assertArrayEquals(sorted, real);
        for (int i = 0; i < sorted.length; i++) {
            assertTrue(sorted[i] == real[i]);
        }
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 50 && comp.getCount() != 0);
    }

    @Test
    public void testWorstCaseInsertionSort() {
        real = new Integer[]{Integer.MAX_VALUE, 4, 3, 2, 1, 0, -1, -2, -3, Integer.MIN_VALUE};
        sorted = new Integer[10];
        sorted[0] = real[9];
        sorted[1] = real[8];
        sorted[2] = real[7];
        sorted[3] = real[6];
        sorted[4] = real[5];
        sorted[5] = real[4];
        sorted[6] = real[3];
        sorted[7] = real[2];
        sorted[8] = real[1];
        sorted[9] = real[0];
        Sorting.insertionSort(real, comp);
        assertArrayEquals(sorted, real);
        for (int i = 0; i < sorted.length; i++) {
            assertTrue(sorted[i] == real[i]);
        }
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 81 && comp.getCount() != 0);
    }
    @Test(timeout = TIMEOUT)
    public void testCocktailSort() {
        Sorting.cocktailSort(real, comp);
        assertArrayEquals(sorted, real);
        for (int i = 0; i < sorted.length; i++) {
            assertTrue(sorted[i] == real[i]);
        }
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 50 && comp.getCount() != 0);
    }
    @Test
    public void testWorstCaseCocktailShakerSort() {
        real = new Integer[]{Integer.MAX_VALUE, 4, 3, 2, 1, 0, -1, -2, -3, Integer.MIN_VALUE};
        sorted = new Integer[10];
        sorted[0] = real[9];
        sorted[1] = real[8];
        sorted[2] = real[7];
        sorted[3] = real[6];
        sorted[4] = real[5];
        sorted[5] = real[4];
        sorted[6] = real[3];
        sorted[7] = real[2];
        sorted[8] = real[1];
        sorted[9] = real[0];
        Sorting.cocktailSort(real, comp);
        assertArrayEquals(sorted, real);
        for (int i = 0; i < sorted.length; i++) {
            assertTrue(sorted[i] == real[i]);
        }
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 81 && comp.getCount() != 0);
    }
    @Test(timeout = TIMEOUT)
    public void testMergeSort() {
        Sorting.mergeSort(real, comp);
        assertArrayEquals(sorted, real);
        for (int i = 0; i < sorted.length; i++) {
            assertTrue(sorted[i] == real[i]);
        }
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 24 && comp.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void testLsdRadixSort() {
        int[] unsortedArray = new int[10];
        for (int i = 0; i < real.length; i++) {
            unsortedArray[i] = real[i];
        }
        int[] sortedArray = new int[10];
        for (int i = 0; i < sorted.length; i++) {
            sortedArray[i] = sorted[i];
        }
        Sorting.lsdRadixSort(unsortedArray);
        assertArrayEquals(sortedArray, unsortedArray);
        for (int i = 0; i < sorted.length; i++) {
            assertTrue(sortedArray[i] == unsortedArray[i]);
        }
    }

    @Test(timeout = TIMEOUT)
    public void testQuickSort() {
        Sorting.quickSort(real, comp, new Random(234));
        assertArrayEquals(sorted, real);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 30 && comp.getCount() != 0);
    }

    /**
     * Class for testing proper sorting.
     */
    private static class TeachingAssistant {
        private String name;

        /**
         * Create a teaching assistant.
         *
         * @param name name of TA
         */
        public TeachingAssistant(String name) {
            this.name = name;
        }

        /**
         * Get the name of the teaching assistant.
         *
         * @return name of teaching assistant
         */
        public String getName() {
            return name;
        }

        /**
         * Create a comparator that compares the names of the teaching
         * assistants.
         *
         * @return comparator that compares the names of the teaching assistants
         */
        public static ComparatorPlus<Integer> getComparator() {
            return new ComparatorPlus<Integer>() {
                @Override
                public int compare(Integer val1,
                                   Integer val2) {
                    incrementCount();
                    return val1.compareTo(val2);
                }
            };
        }

        @Override
        public String toString() {
            return "Name: " + name;
        }

        @Override
        public boolean equals(Object other) {
            if (other == null) {
                return false;
            }
            if (this == other) {
                return true;
            }
            return other instanceof TeachingAssistant
                    && ((TeachingAssistant) other).name.equals(this.name);
        }
    }

    /**
     * Inner class that allows counting how many comparisons were made.
     */
    private abstract static class ComparatorPlus<T> implements Comparator<T> {
        private int count;

        /**
         * Get the number of comparisons made.
         *
         * @return number of comparisons made
         */
        public int getCount() {
            return count;
        }

        /**
         * Increment the number of comparisons made by one. Call this method in
         * your compare() implementation.
         */
        public void incrementCount() {
            count++;
        }
    }
}