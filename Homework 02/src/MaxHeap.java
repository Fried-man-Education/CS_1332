import java.util.ArrayList;
import java.util.stream.IntStream;

import static java.util.Objects.isNull;

/**
 * Your implementation of a MaxHeap.
 *
 * @author Andrew Friedman
 * @version 1.0
 * @userid afriedman38
 * @GTID 903506792
 *
 * Collaborators: n/a
 *
 * Resources: n/a
 */
public class MaxHeap<T extends Comparable<? super T>> {

    /*
     * The initial capacity of the MaxHeap when created with the default
     * constructor.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new MaxHeap.
     *
     * The backing array should have an initial capacity of INITIAL_CAPACITY.
     */
    public MaxHeap() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Creates a properly ordered heap from a set of initial values.
     *
     * You must use the BuildHeap algorithm that was taught in lecture! Simply
     * adding the data one by one using the add method will not get any credit.
     * As a reminder, this is the algorithm that involves building the heap
     * from the bottom up by repeated use of downHeap operations.
     *
     * Before doing the algorithm, first copy over the data from the
     * ArrayList to the backingArray (leaving index 0 of the backingArray
     * empty). The data in the backingArray should be in the same order as it
     * appears in the passed in ArrayList before you start the BuildHeap
     * algorithm.
     *
     * The backingArray should have capacity 2n + 1 where n is the
     * number of data in the passed in ArrayList (not INITIAL_CAPACITY).
     * Index 0 should remain empty, indices 1 to n should contain the data in
     * proper order, and the rest of the indices should be empty.
     *
     * @param data a list of data to initialize the heap with
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public MaxHeap(ArrayList<T> data) {
        assert !isNull(data) : "cannot create heap: no data provided";
        backingArray = (T[]) new Comparable[data.size() * 2 + 1];


        for (int i = 0, dataSize = data.size(), counter = 1; i < dataSize; i++) {
            T element = data.get(i);
            assert !isNull(element) : "cannot create heap: null data provided";
            if (backingArray.length == ++size) {
                resize();
            }
            backingArray[counter++] = element;
        }

        for (int i = size >> 1; i > 0; i--) {
            int j = i;
            while (j <= size >> 1) {
                int maxChild = findMaxChild(j);

                if (backingArray[j].compareTo(backingArray[maxChild]) < 0) {
                    T temp  = backingArray[maxChild];
                    backingArray[maxChild] = backingArray[j];
                    backingArray[j] = temp;
                } else {
                    break;
                }
                j = maxChild;
            }
        }
    }

    /**
     * Adds the data to the heap.
     *
     * If sufficient space is not available in the backing array (the backing
     * array is full except for index 0), resize it to double the current
     * length.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        assert !isNull(data) : "data is null";

        if (backingArray.length == ++size) {
            resize();
        }

        backingArray[size] = data;

        int i = size;
        while (i > 1) {
            int root = i >> 1;
            int maxChild = findMaxChild(i >> 1);

            if (backingArray[root].compareTo(backingArray[maxChild]) < 0) {
                backingArray[i] = backingArray[root];
                backingArray[root] = data;
            }
            i >>= 1;
        }
    }

    /**
     * Helper method to resize backingArray when size of heap equals size of backing array.
     */
    private void resize() {
        T[] temp = (T[]) new Comparable[(backingArray.length << 1)];
        IntStream.range(0, backingArray.length).forEachOrdered(i -> temp[i] = backingArray[i]);
        backingArray = temp;
    }

    /**
     * Helper method to find max child of root index.
     * @param rootIndex index to find max child of
     * @return index of max child
     */
    private int findMaxChild(int rootIndex) {
        return (rootIndex << 1) + 1 >= backingArray.length
                || backingArray[(rootIndex << 1) + 1] == null
                || backingArray[(rootIndex << 1)].compareTo(backingArray[(rootIndex << 1) + 1]) > 0
                ? rootIndex << 1 : (rootIndex << 1) + 1;
    }

    /**
     * Removes and returns the root of the heap.
     *
     * Do not shrink the backing array.
     *
     * Replace any unused spots in the array with null.
     *
     * @return the data that was removed
     * @throws java.util.NoSuchElementException if the heap is empty
     */
    public T remove() {
        assert size != 0 : "cannot remove: heap is empty";

        T removed = backingArray[1];
        backingArray[1] = backingArray[size];
        backingArray[size--] = null;

        int maxChild = findMaxChild(1);
        for (int tempRoot = 1; tempRoot <= size >> 1; tempRoot = maxChild, maxChild = findMaxChild(tempRoot)) {
            if (backingArray[tempRoot].compareTo(backingArray[maxChild]) < 0) {
                T temp  = backingArray[maxChild];
                backingArray[maxChild] = backingArray[tempRoot];
                backingArray[tempRoot] = temp;
            }
        }

        return removed;
    }

    /**
     * Returns the maximum element in the heap.
     *
     * @return the maximum element
     * @throws java.util.NoSuchElementException if the heap is empty
     */
    public T getMax() {
        assert size != 0 : "cannot return max value: heap is empty";
        return backingArray[1];
    }

    /**
     * Returns whether or not the heap is empty.
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears the heap.
     *
     * Resets the backing array to a new array of the initial capacity and
     * resets the size.
     */
    public void clear() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Returns the backing array of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
