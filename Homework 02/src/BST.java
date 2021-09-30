import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

import static java.util.Objects.isNull;

/**
 * Your implementation of a BST.
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
public class BST<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private BSTNode<T> root;
    private int size;

    /**
     * Constructs a new BST.
     *
     * This constructor should initialize an empty BST.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public BST() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Constructs a new BST.
     *
     * This constructor should initialize the BST with the data in the
     * Collection. The data should be added in the same order it is in the
     * Collection.
     *
     * Hint: Not all Collections are indexable like Lists, so a regular for loop
     * will not work here. However, all Collections are Iterable, so what type
     * of loop would work?
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public BST(Collection<T> data) {
        assert !isNull(data) : "collection of data is null";
        data.forEach(x -> {
            assert !isNull(x) : "data within collection is null";
            add(x);
        });
    }

    /**
     * Adds the data to the tree.
     *
     * This must be done recursively.
     *
     * The data becomes a leaf in the tree.
     *
     * Traverse the tree to find the appropriate location. If the data is
     * already in the tree, then nothing should be done (the duplicate
     * shouldn't get added, and size should not be incremented).
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        assert !isNull(data) : "provided data is null";

        root = addHelper(root, data);
        size++;
    }

    /**
     * Helper method to recursively add data to binary search tree.
     * @param node current node of traversed tree
     * @param data data to add
     * @return node at the location
     */
    private BSTNode<T> addHelper(BSTNode<T> node, T data) {
        if (isNull(node)) {
            return new BSTNode<T>(data);
        }

        int compare = data.compareTo(node.getData());
        if (compare < 0) {
            node.setLeft(addHelper(node.getLeft(), data));
        } else if (compare > 0) {
            node.setRight(addHelper(node.getRight(), data));
        } else {
            size--;
            return node;
        }
        return node;
    }

    /**
     * Removes and returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the successor to
     * replace the data. You MUST use recursion to find and remove the
     * successor (you will likely need an additional helper method to
     * handle this case efficiently).
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to remove
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T remove(T data) {
        assert !isNull(data) : "provided data is null";

        root = removeHelper(root, data);
        size--;
        return data;
    }

    /**
     * Helper method for remove func.
     * @param node current node of traversed tree
     * @param data the data to remove
     * @return new node for tree
     */
    private BSTNode<T> removeHelper(BSTNode<T> node, T data) {
        assert !isNull(node) : "provided data is not in tree";

        int compare = data.compareTo(node.getData());
        if (compare < 0) {
            node.setLeft(removeHelper(node.getLeft(), data));
        } else if (compare > 0) {
            node.setRight(removeHelper(node.getRight(), data));
        } else if (isNull(node.getLeft()) && isNull(node.getRight())) {
            return null;
        } else if (isNull(node.getLeft())) {
            return node.getRight();
        } else if (isNull(node.getRight())) {
            return node.getLeft();
        } else {
            BSTNode<T> newNode = findNext(node.getRight(), data);
            node.setData(newNode.getData());
            node.setRight(removeHelper(node.getRight(), newNode.getData()));
        }

        return node;
    }

    /**
     * Find next for given node.
     * @param node current node of traversed tree
     * @param data the data to remove
     * @return next node of the input node
     */
    private BSTNode<T> findNext(BSTNode<T> node, T data) {
        BSTNode<T> minNode = node;

        while (node.getLeft() != null) {
            minNode = node.getLeft();
            node = node.getLeft();
        }

        return minNode;
    }

    /**
     * Returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
     * @return the data in the tree equal to the parameter
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T get(T data) {
        assert !isNull(data) : "provided data is null";
        return getHelper(root, data);
    }

    /**
     * Helper method for the get method func.
     * @param node current node
     * @param data data trying to locate
     * @return the located data
     */
    private T getHelper(BSTNode<T> node, T data) {
        assert !isNull(node) : "provided data is not in tree";
        BSTNode<T> temp = node;

        int compareVal = data.compareTo(temp.getData());

        if (compareVal < 0) {
            return getHelper(temp.getLeft(), data);
        }
        return compareVal > 0 ? getHelper(temp.getRight(), data) : temp.getData();
    }

    /**
     * Returns whether or not data matching the given parameter is contained
     * within the tree.
     *
     * This must be done recursively.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
     * @return true if the parameter is contained within the tree, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public boolean contains(T data) {
        assert !isNull(data) : "provided data is null";

        try {
            get(data);
        } catch (NoSuchElementException noSuchElementException) {
            return false;
        }
        return true;
    }

    /**
     * Generate a pre-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the preorder traversal of the tree
     */
    public List<T> preorder() {
        return isNull(root) ? new ArrayList<T>() : preorderHelper(new ArrayList<T>(), root);
    }

    /**
     * Helper method for preorder traversal func.
     * @param list order of nodes for traversal
     * @param node node being traversed
     * @return order of nodes for traversal
     */
    private List<T> preorderHelper(List<T> list, BSTNode<T> node) {
        if (!isNull(node)) {
            list.add(node.getData());
        }
        if (!isNull(node.getLeft())) {
            preorderHelper(list, node.getLeft());
        }
        if (!isNull(node.getRight())) {
            preorderHelper(list, node.getRight());
        }

        return list;
    }

    /**
     * Generate an in-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the inorder traversal of the tree
     */
    public List<T> inorder() {
        return isNull(root) ? new ArrayList<T>() : inOrderHelper(new ArrayList<T>(), root);
    }

    /**
     * Helper method for inorder func.
     * @param list order of nodes for traversal
     * @param node node being traversed
     * @return order of nodes for traversal
     */
    private List<T> inOrderHelper(List<T> list, BSTNode<T> node) {
        if (!isNull(node.getLeft())) {
            inOrderHelper(list, node.getLeft());
        }
        if (!isNull(node)) {
            list.add(node.getData());
        }
        if (!isNull(node.getRight())) {
            inOrderHelper(list, node.getRight());
        }
        return list;
    }

    /**
     * Generate a post-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the postorder traversal of the tree
     */
    public List<T> postorder() {
        return isNull(root) ? new ArrayList<T>() : postOrderHelper(new ArrayList<T>(), root);
    }

    /**
     * Helper method for postorder method func.
     * @param list order of nodes for traversal
     * @param node node being traversed
     * @return order of nodes for traversal
     */
    private List<T> postOrderHelper(List<T> list, BSTNode<T> node) {
        if (!isNull(node.getLeft())) {
            postOrderHelper(list, node.getLeft());
        }
        if (!isNull(node.getRight())) {
            postOrderHelper(list, node.getRight());
        }
        if (!isNull(node)) {
            list.add(node.getData());
        }

        return list;
    }

    /**
     * Generate a level-order traversal of the tree.
     *
     * This does not need to be done recursively.
     *
     * Hint: You will need to use a queue of nodes. Think about what initial
     * node you should add to the queue and what loop / loop conditions you
     * should use.
     *
     * Must be O(n).
     *
     * @return the level order traversal of the tree
     */
    public List<T> levelorder() {
        if (isNull(root)) {
            return new ArrayList<T>();
        }

        Queue<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();
        queue.add(root);

        List<T> list = new ArrayList<T>();

        while (!queue.isEmpty()) {
            BSTNode<T> node = queue.poll();
            list.add(node.getData());
            if (!isNull(node.getLeft())) {
                queue.add(node.getLeft());
            }
            if (!isNull(node.getRight())) {
                queue.add(node.getRight());
            }
        }

        return list;
    }

    /**
     * Returns the height of the root of the tree.
     *
     * This must be done recursively.
     *
     * A node's height is defined as max(left.height, right.height) + 1. A
     * leaf node has a height of 0 and a null child has a height of -1.
     *
     * Must be O(n).
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        return isNull(root) ? -1 : Math.max(heightHelper(root.getLeft(), 0), heightHelper(root.getRight(), 0));
    }

    /**
     * Helper method for the height method func.
     * @param node node being traversed
     * @param height height at current point
     * @return height of tree starting with node
     */
    private int heightHelper(BSTNode<T> node, int height) {
        if (isNull(node)) {
            return height;
        }
        return Math.max(heightHelper(node.getLeft(), height + 1), heightHelper(node.getRight(), height + 1));
    }

    /**
     * Clears the tree.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        size = 0;
        root = null;
    }

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the tree
     */
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
