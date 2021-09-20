import java.util.NoSuchElementException;

/**
 * Your implementation of a CircularSinglyLinkedList without a tail pointer.
 *
 * @author Andrew Friedman
 * @version 1.0
 * @userid afriedman38
 * @GTID 903506792
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class CircularSinglyLinkedList<T> {


    // Do not add new instance variables or modify existing ones.
    private LinkedNode<T> head;
    private int size;

    // Do not add a constructor.

    /**
     * Adds the data to the specified index.
     *
     * Must be O(1) for indices 0 and size and O(n) for all other cases.
     *
     * @param index the index at which to add the new data
     * @param data  the data to add at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index > size
     * @throws java.lang.IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("The index out of bounds.");
        } else if (data == null) {
            throw new IllegalArgumentException("The data is null.");
        } else if (head == null) {
            head = new LinkedNode<>(data);
            head.setNext(head);
            size++;
        } else if (index == 0) {
            addToFront(data);
        } else if (index == size) {
            addToBack(data);
        } else {
            LinkedNode<T> noted = head;
            for(int i  = 0; i < index - 1; i++) {
                noted = noted.getNext();
            }
            LinkedNode<T> tempNode = new LinkedNode<T>(data, noted.getNext());
            noted.setNext(tempNode);
            size++;
        }
    }

    /**
     * Adds the data to the front of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null");
        }
        if (head == null) {
            head = new LinkedNode<>(data);
            head.setNext(head);
            size++;
        } else {
            LinkedNode temp = new LinkedNode<>(head.getData());
            temp.setNext(head.getNext());
            head.setNext(temp);
            head.setData(data);
            size++;
        }
    }

    /**
     * Adds the data to the back of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null");
        }
        if (head == null) {
            head = new LinkedNode<>(data);
            head.setNext(head);
            head = head.getNext();
            size++;
        } else {
            LinkedNode<T> temp = new LinkedNode<>(head.getData());
            temp.setNext(head.getNext());
            head.setNext(temp);
            head.setData(data);
            head = head.getNext();
            size++;
        }
    }

    /**
     * Removes and returns the data at the specified index.
     *
     * Must be O(1) for index 0 and O(n) for all other cases.
     *
     * @param index the index of the data to remove
     * @return the data formerly located at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) { 
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is either negative or greater than the size of the list.");
        }
        T tempNode;
        if (size == 1) {
            tempNode = head.getData();
            head.setData(null);
            head.setNext(null);
            size--;
        } else if (index == 0) {
            tempNode = head.getData();
            head.setData(head.getNext().getData());
            head.setNext(head.getNext().getNext());
            size--;
        } else {
            LinkedNode<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            tempNode = current.getNext().getData();
            current.setNext(current.getNext().getNext());
            size--;
        }
        return tempNode;
    }

    /**
     * Removes and returns the first data of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() { 
        if (isEmpty()) {
            throw new NoSuchElementException("List in empty");
        }
        return removeAtIndex(0);
    }

    /**
     * Removes and returns the last data of the list.
     *
     * Must be O(n).
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() { 
        if (isEmpty()) {
            throw new NoSuchElementException("List in empty");
        }
        return removeAtIndex(size - 1);
    }

    /**
     * Returns the data at the specified index.
     *
     * Should be O(1) for index 0 and O(n) for all other cases.
     *
     * @param index the index of the data to get
     * @return the data stored at the index in the list
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        } else if (index == 0) {
            return head.getData();
        } else {
            LinkedNode<T> noted = head;
            for(int i = 0; i < index; i++) {
                noted = noted.getNext();
            }
            return noted.getData();
        }
    }

    /**
     * Returns whether or not the list is empty.
     *
     * Must be O(1).
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears the list.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        size = 0;
        head = null;
    }

    /**
     * Removes and returns the last copy of the given data from the list.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the list.
     *
     * Must be O(n).
     *
     * @param data the data to be removed from the list
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if data is not found
     */
    public T removeLastOccurrence(T data) { 
        T removed = null;
        if (data == null) {
            throw new IllegalArgumentException("Data is null");
        } else if (isEmpty()) {
            throw new NoSuchElementException("Data is not found");
        }else {
            LinkedNode<T> curr = getHead();
            LinkedNode<T> beforeCurr = new LinkedNode<>(null, null);
            for (int i = 0; i < size - 1; i++) {
                if (curr.getNext().getData().equals(data)) {
                    beforeCurr = curr;
                }
                curr = curr.getNext();
            }
            removed = beforeCurr.getNext().getData();
            beforeCurr.setNext(beforeCurr.getNext().getNext());
            size--;
        }
        if (removed == null) {
            throw new NoSuchElementException("The requested data was not found.");
        }
        return removed;
    }

    /**
     * Returns an array representation of the linked list.
     *
     * Must be O(n) for all cases.
     *
     * @return the array of length size holding all of the data (not the
     * nodes) in the list in the same order
     */
    public T[] toArray() { 
        T[] arr = (T[]) new Object[size];
        LinkedNode<T> noted;
        noted = head;
        for (int i = 0; i < size; i++) {
            arr[i] = noted.getData();
            noted = noted.getNext();
        }
        return arr;
    }

    /**
     * Returns the head node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the list
     */
    public LinkedNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
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
        // DO NOT MODIFY!
        return size;
    }
}
