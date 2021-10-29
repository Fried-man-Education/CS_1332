import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Your implementation of various sorting algorithms.
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

public class Sorting {

    /**
     * Implement insertion sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n)
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (Objects.isNull(arr)) {
            throw new IllegalArgumentException("Array can't be null");
        } else if (Objects.isNull(comparator)) {
            throw new IllegalArgumentException("Comparator can't be null");
        }

        for (int n = 0; n < arr.length - 1; n++) {
            for (int i = n + 1; i != 0 && comparator.compare(arr[i], arr[i - 1]) < 0; i--) {
                T temp = arr[i];
                arr[i] = arr[i - 1];
                arr[i - 1] = temp;
            }
        }
    }

    /**
     * Implement cocktail sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n)
     *
     * NOTE: See pdf for last swapped optimization for cocktail sort. You
     * MUST implement cocktail sort with this optimization
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void cocktailSort(T[] arr, Comparator<T> comparator) {
        if (Objects.isNull(arr)) {
            throw new IllegalArgumentException("Array can't be null");
        } else if (Objects.isNull(comparator)) {
            throw new IllegalArgumentException("Comparator can't be null");
        }

        boolean swapsMade = true;
        for (int startIdx = 0, endIdx = arr.length - 1; swapsMade;) {
            swapsMade = false;
            for (int i = startIdx, lastSwappedEnd = endIdx; i < lastSwappedEnd; i++) {
                if (comparator.compare(arr[i], arr[i + 1]) > 0) {
                    T temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    swapsMade = true;
                    endIdx = i;
                }
            }
            if (swapsMade) {
                swapsMade = false;
                for (int i = endIdx, lastSwappedStart = startIdx; i > lastSwappedStart; i--) {
                    if (comparator.compare(arr[i - 1], arr[i]) > 0) {
                        T temp = arr[i];
                        arr[i] = arr[i - 1];
                        arr[i - 1] = temp;
                        swapsMade = true;
                        startIdx = i;
                    }
                }
            }
        }
    }

    /**
     * Implement merge sort.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n log n)
     *
     * And a best case running time of:
     * O(n log n)
     *
     * You can create more arrays to run merge sort, but at the end, everything
     * should be merged back into the original T[] which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * Hint: If two data are equal when merging, think about which subarray
     * you should pull from first
     *
     * @param <T>        data type to sort
     * @param arr        the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (Objects.isNull(arr)) {
            throw new IllegalArgumentException("Array can't be null");
        } else if (Objects.isNull(comparator)) {
            throw new IllegalArgumentException("Comparator can't be null");
        }

        if (arr.length <= 1) {
            return;
        }

        int leftCap = arr.length / 2;
        int rightCap = arr.length - leftCap;
        T[] leftArr = (T[]) (new Object[leftCap]);
        T[] rightArr = (T[]) (new Object[rightCap]);
        for (int i = 0; i < leftCap; i++) {
            leftArr[i] = arr[i];
        }
        for (int i = 0; i < rightCap; i++) {
            rightArr[i] = arr[i + leftCap];
        }

        mergeSort(leftArr, comparator);
        mergeSort(rightArr, comparator);

        mergeSortHelper(leftArr, rightArr, arr, comparator);
    }

    /**
     * Helper method for quick sort
     *
     * @param <T>        data type to sort
     * @param arr        array that must be sorted after the method runs
     * @param left       left index
     * @param right      right index
     * @param comparator Comparator used to compare the data in arr
     */
    private static <T> void mergeSortHelper(T[] left, T[] right, T[] arr, Comparator<T> comparator) {
        int i = 0;
        int j = 0;
        while (j < right.length && i < left.length) {
            if (comparator.compare(left[i], right[j]) <= 0) {
                arr[i + j] = left[i++];
            } else {
                arr[i + j] = right[j++];
            }
        }

        while (i < left.length) {
            arr[i + j] = left[i++];
        }
        while (j < right.length) {
            arr[i + j] = right[j++];
        }
    }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not implement the one we have taught you!
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code! Doing so may result in a 0 for the implementation.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(kn)
     *
     * And a best case running time of:
     * O(kn)
     *
     * You are allowed to make an initial O(n) passthrough of the array to
     * determine the number of iterations you need.
     *
     * At no point should you find yourself needing a way to exponentiate a
     * number; any such method would be non-O(1). Think about how how you can
     * get each power of BASE naturally and efficiently as the algorithm
     * progresses through each digit.
     *
     * Refer to the PDF for more information on LSD Radix Sort.
     *
     * You may use ArrayList or LinkedList if you wish, but it may only be
     * used inside radix sort and any radix sort helpers. Do NOT use these
     * classes with other sorts. However, be sure the List implementation you
     * choose allows for stability while being as efficient as possible.
     *
     * Do NOT use anything from the Math class except Math.abs().
     *
     * @param arr the array to be sorted
     * @throws java.lang.IllegalArgumentException if the array is null
     */
    public static void lsdRadixSort(int[] arr) {
        if (Objects.isNull(arr)) {
            throw new IllegalArgumentException("Array can't be null");
        }

        ArrayList<Integer>[] buckets;
        buckets = IntStream.range(0, 19).<ArrayList<Integer>>mapToObj(i -> new ArrayList<>()).toArray(ArrayList[]::new);

        int max = Math.abs(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            if (Math.abs(arr[i]) > max) {
                max = Math.abs(arr[i]);
            }
        }

        int k;
        for (k = 0; max > 0; k++) {
            max /= 10;
        }

        for (int i = 0; i < k; i++) {
            int i1 = 0;
            while (i1 < arr.length) {
                int value = arr[i1];
                int currDigit = value;
                for (int j = 0; j < i; j++) {
                    currDigit /= 10;
                }
                currDigit %= 10;
                buckets[currDigit + 9].add(value);
                i1++;
            }
            int n = 0;
            for (int j = 0, bucketsLength = buckets.length; j < bucketsLength; j++) {
                ArrayList<Integer> bucket = buckets[j];
                while (!bucket.isEmpty()) {
                    arr[n++] = bucket.get(0);
                    bucket.remove(0);
                }
            }
        }
    }

    /**
     * Implement quick sort.
     *
     * Use the provided random object to select your pivots. For example if you
     * need a pivot between a (inclusive) and b (exclusive) where b > a, use
     * the following code:
     *
     * int pivotIndex = rand.nextInt(b - a) + a;
     *
     * If your recursion uses an inclusive b instead of an exclusive one,
     * the formula changes by adding 1 to the nextInt() call:
     *
     * int pivotIndex = rand.nextInt(b - a + 1) + a;
     *
     * It should be:
     * in-place
     * unstable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n log n)
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not receive
     * credit if you do not use the one we have taught you!
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand       the Random object used to select pivots
     * @throws java.lang.IllegalArgumentException if the array or comparator or
     *                                            rand is null
     */
    public static <T> void quickSort(T[] arr, Comparator<T> comparator,
                                     Random rand) {
        if (Objects.isNull(arr)) {
            throw new IllegalArgumentException("Cannot sort null array");
        } else if (Objects.isNull(comparator)) {
            throw new IllegalArgumentException(
                    "Cannot sort with null comparator");
        } else if (Objects.isNull(rand)) {
            throw new IllegalArgumentException(
                    "Cannot sort with null random");
        }
        quickSortHelper(arr, 0, arr.length - 1, comparator, rand);
    }

    /**
     * Helper method for quick sort
     *
     * @param <T>        data type to sort
     * @param arr        array that must be sorted after the method runs
     * @param left       left index
     * @param right      right index
     * @param comparator Comparator used to compare the data in arr
     * @param rand       Random object used to select pivots
     */
    private static <T> void quickSortHelper(T[] arr, int left, int right,
                                   Comparator<T> comparator, Random rand) {
        if (left >= right) {
            return;
        }
        int l = left + 1;
        int r = right;
        int pivot = rand.nextInt(right - left + 1) + left;
        T p = arr[pivot];
        arr[pivot] = arr[left];
        arr[left] = p;
        while (l <= r) {
            while (l <= r) {
                if (comparator.compare(arr[l], p) <= 0) {
                    l++;
                } else {
                    break;
                }
            }
            while (l < r) {
                if (comparator.compare(arr[r], p) >= 0) {
                    r--;
                } else {
                    break;
                }
            }
            if (l == r) {
                r--;
            }
            if (l < r) {
                T temp = arr[l];
                arr[l++] = arr[r];
                arr[r--] = temp;
            }
        }
        T temp = arr[r];
        arr[r] = arr[left];
        arr[left] = temp;
        quickSortHelper(arr, left, r - 1, comparator, rand);
        quickSortHelper(arr, r + 1, right, comparator, rand);
    }
}
