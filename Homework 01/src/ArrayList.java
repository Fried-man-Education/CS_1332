import java.util.NoSuchElementException;

/**
 * Your implementation of an ArrayList.
 *
 * @author Andrew Friedman
 * @version 1.0
 * @userid afriedman38
 * @GTID 903506792
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: Java Documentation(https://docs.oracle.com/en/java/javase/11/docs/api/index.html)
 */
public class ArrayList<T> {

    /**
     * The initial capacity of the ArrayList.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 9;

    // Do not add new instance variables or modify existing ones.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new ArrayList.
     *
     * Java does not allow for regular generic array creation, so you will have
     * to cast an Object[] to a T[] to get the generic typing.
     */
    public ArrayList() {
        size = 0;
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    /**
     * Adds the element to the specified index.
     *
     * Remember that this add may require elements to be shifted.
     *
     * Must be amortized O(1) for index size and O(n) for all other cases.
     *
     * @param index the index at which to add the new element
     * @param temp  the temp to add at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index > size
     * @throws java.lang.IllegalArgumentException  if temp is null
     */
    public void addAtIndex(int index, T temp) {
        if (temp == null) {
            throw new IllegalArgumentException("The data cannot be null");
        } else if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        } else if (backingArray.length == index) {
            T[] newArr = (T[]) new Object[backingArray.length * 2];
            for (int i = 0; i < size; i++) {
                newArr[i] = backingArray[i];
            }
        } else {
            for (int j = size; j > index; j--) {
                backingArray[j] = backingArray[j - 1];
            }
        }
        backingArray[index] = temp;
        size++;
    }

    /**
     * Adds the element to the front of the list.
     *
     * Remember that this add may require elements to be shifted.
     *
     * Must be O(n).
     *
     * @param temp the temp to add to the front of the list
     * @throws java.lang.IllegalArgumentException if temp is null
     */
    public void addToFront(T temp) {
        if (temp == null) {
            throw new IllegalArgumentException("The data cannot be null");
        }
        addAtIndex(0, temp);
    }

    /**
     * Adds the element to the back of the list.
     *
     * Must be amortized O(1).
     *
     * @param temp the temp to add to the back of the list
     * @throws java.lang.IllegalArgumentException if temp is null
     */
    public void addToBack(T temp) {
        if (temp == null) {
            throw new IllegalArgumentException("The data cannot be null");
        }
        addAtIndex(size, temp);
    }

    /**
     * Removes and returns the element at the specified index.
     *
     * Remember that this remove may require elements to be shifted.
     *
     * Must be O(1) for index size - 1 and O(n) for all other cases.
     *
     * @param index the index of the element to remove
     * @return the temp formerly located at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        T temp = null;
        if (index == size - 1) {
            temp = backingArray[index];
            backingArray[index] = null;
        } else {
            temp = backingArray[index];
            backingArray[index] = null;
            for (int i = index; i < size - 1; i++) {
                backingArray[i] = backingArray[i + 1];
            }
            backingArray[size - 1] = null;
        }
        size--;
        return temp;
    }

    /**
     * Removes and returns the first element of the list.
     *
     * Remember that this remove may require elements to be shifted.
     *
     * Must be O(n).
     *
     * @return the temp formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        if (size == 0) {
            throw new NoSuchElementException("list is empty");
        }
        return removeAtIndex(0);
    }

    /**
     * Removes and returns the last element of the list.
     *
     * Must be O(1).
     *
     * @return the temp formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
       if (size == 0) {
           throw new NoSuchElementException("list is empty");
       }
       return removeAtIndex(size - 1);
    }

    /**
     * Returns the element at the specified index.
     *
     * Must be O(1).
     *
     * @param index the index of the element to get
     * @return the temp stored at the index in the list
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return backingArray[index];
    }

    /**
     * Returns whether or not the list is empty.
     *
     * Must be O(1).
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        if(size == 0) {
            return true;
        }
        return false;
    }

    /**
     * Clears the list.
     *
     * Resets the backing array to a new array of the initial capacity and
     * resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        T[] arr = (T[]) new Object[INITIAL_CAPACITY];
        for (int i = 0; i < INITIAL_CAPACITY; i++) {
            backingArray[i] = arr[i];
        }
        size = 0;
    }

    /**
     * Returns the backing array of the list.
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
     * Returns the size of the list.
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
