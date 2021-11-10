import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

/**
 * Your implementations of various string searching algorithms.
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
public class PatternMatching {

    /**
     * Knuth-Morris-Pratt (KMP) algorithm relies on the failure table (also
     * called failure function). Works better with small alphabets.
     *
     * Make sure to implement the buildFailureTable() method before implementing
     * this method.
     *
     * @param pattern    the pattern you are searching for in a body of text
     * @param text       the body of text where you search for pattern
     * @param comparator you MUST use this to check if characters are equal
     * @return list containing the starting index for each match found
     * @throws java.lang.IllegalArgumentException if the pattern is null or has
     *                                            length 0
     * @throws java.lang.IllegalArgumentException if text or comparator is null
     */
    public static List<Integer> kmp(CharSequence pattern, CharSequence text,
                                       CharacterComparator comparator) {
        if (Objects.isNull(comparator)) {
            throw new IllegalArgumentException("null comparator not allowed...");
        }
        if (Objects.isNull(text)) {
            throw new IllegalArgumentException("null text not allowed...");
        }
        if (Objects.isNull(pattern) || pattern.length() == 0) {
            throw new IllegalArgumentException("Current pattern not allowed...");
        }
        List<Integer> tempList;
        tempList = new ArrayList<>();
        if (pattern.length() <= text.length()) {
            int[] tempFail;
            tempFail = buildFailureTable(pattern, comparator);
            for (int i = 0, j = 0; i < text.length();) {
                if (text.length() - i >= pattern.length() - j) {
                    int x;
                    x = comparator.compare(text.charAt(i), pattern.charAt(j));
                    if (x != 0) {
                        if (j != 0) {
                            j = tempFail[j - 1];
                        } else {
                            i++;
                        }
                    } else {
                        i++;
                        if (++j == pattern.length()) {
                            tempList.add(i - j);
                            j = tempFail[j - 1];
                        }
                    }
                } else {
                    return tempList;
                }
            }
            return tempList;
        }
        return tempList;
    }

    /**
     * Builds failure table that will be used to run the Knuth-Morris-Pratt
     * (KMP) algorithm.
     *
     * The table built should be the length of the input text.
     *
     * Note that a given index i will contain the length of the largest prefix
     * of the pattern indices [0..i] that is also a suffix of the pattern
     * indices [1..i]. This means that index 0 of the returned table will always
     * be equal to 0
     *
     * Ex. pattern = ababac
     *
     * table[0] = 0
     * table[1] = 0
     * table[2] = 1
     * table[3] = 2
     * table[4] = 3
     * table[5] = 0
     *
     * If the pattern is empty, return an empty array.
     *
     * @param pattern    a pattern you're building a failure table for
     * @param comparator you MUST use this to check if characters are equal
     * @return integer array holding your failure table
     * @throws java.lang.IllegalArgumentException if the pattern or comparator
     *                                            is null
     */
    public static int[] buildFailureTable(CharSequence pattern,
                                          CharacterComparator comparator) {
        if (Objects.isNull(comparator)) {
            throw new IllegalArgumentException("null comparator not allowed...");
        }
        if (Objects.isNull(pattern) || pattern.length() == 0) {
            throw new IllegalArgumentException("Current pattern not allowed...");
        }
        int[] tempTable;
        tempTable = new int[pattern.length()];
        for (int i = 0, j = 1; j < pattern.length();) {
            char x;
            x = pattern.charAt(i);
            char y;
            y = pattern.charAt(j);
            if (comparator.compare(x, y) != 0) {
                if (i != 0) {
                    i = tempTable[i - 1];
                } else {
                    tempTable[j++] = 0;
                }
            } else {
                tempTable[j++] = i++ + 1;
            }
        }
        return tempTable;
    }

    /**
     * Boyer Moore algorithm that relies on last occurrence table. Works better
     * with large alphabets.
     *
     * Make sure to implement the buildLastTable() method before implementing
     * this method.
     *
     * Note: You may find the getOrDefault() method from Java's Map class
     * useful.
     *
     * @param pattern    the pattern you are searching for in a body of text
     * @param text       the body of text where you search for the pattern
     * @param comparator you MUST use this to check if characters are equal
     * @return list containing the starting index for each match found
     * @throws java.lang.IllegalArgumentException if the pattern is null or has
     *                                            length 0
     * @throws java.lang.IllegalArgumentException if text or comparator is null
     */
    public static List<Integer> boyerMoore(CharSequence pattern,
                                              CharSequence text,
                                              CharacterComparator comparator) {
        if (Objects.isNull(comparator)) {
            throw new IllegalArgumentException("null comparator not allowed...");
        }
        if (Objects.isNull(text)) {
            throw new IllegalArgumentException("null text not allowed...");
        }
        if (Objects.isNull(pattern) || pattern.length() == 0) {
            throw new IllegalArgumentException("Current pattern not allowed...");
        }
        List<Integer> tempList;
        tempList = new ArrayList<>();
        if (text.length() >= pattern.length()) {
            Map<Character, Integer> tempMap = buildLastTable(pattern);
            for (int i = 0; i <= text.length() - pattern.length(); i += 0) {
                if (pattern.length() + i > text.length()) {
                    return tempList;
                }
                int j;
                for (j = pattern.length() - 1; j >= 0 && comparator.compare(text.charAt(i + j),
                        pattern.charAt(j)) == 0; j--) {
                    assert true;
                }
                if (j != -1) {
                    int k;
                    if (!tempMap.containsKey(text.charAt(i + j))) {
                        k = -1;
                    } else {
                        k = tempMap.get(text.charAt(i + j));
                    }
                    if (k >= j) {
                        i++;
                    } else {
                        i = i + j - k;
                    }
                } else {
                    tempList.add(i++);
                }
            }
            return tempList;
        }
        return tempList;
    }

    /**
     * Builds last occurrence table that will be used to run the Boyer Moore
     * algorithm.
     *
     * Note that each char x will have an entry at table.get(x).
     * Each entry should be the last index of x where x is a particular
     * character in your pattern.
     * If x is not in the pattern, then the table will not contain the key x,
     * and you will have to check for that in your Boyer Moore implementation.
     *
     * Ex. pattern = octocat
     *
     * table.get(o) = 3
     * table.get(c) = 4
     * table.get(t) = 6
     * table.get(a) = 5
     * table.get(everything else) = null, which you will interpret in
     * Boyer-Moore as -1
     *
     * If the pattern is empty, return an empty map.
     *
     * @param pattern a pattern you are building last table for
     * @return a Map with keys of all of the characters in the pattern mapping
     * to their last occurrence in the pattern
     * @throws java.lang.IllegalArgumentException if the pattern is null
     */
    public static Map<Character, Integer> buildLastTable(CharSequence pattern) {
        if (Objects.isNull(pattern) || pattern.length() == 0) {
            throw new IllegalArgumentException("Current pattern not allowed...");
        }
        Map<Character, Integer> tempMap = new HashMap<>();
        int i = 0;
        while (i < pattern.length()) {
            tempMap.put(pattern.charAt(i), i++);
        }
        return tempMap;
    }

    /**
     * Prime base used for Rabin-Karp hashing.
     * DO NOT EDIT!
     */
    private static final int BASE = 113;

    /**
     * Runs the Rabin-Karp algorithm. This algorithms generates hashes for the
     * pattern and compares this hash to substrings of the text before doing
     * character by character comparisons.
     *
     * When the hashes are equal and you do character comparisons, compare
     * starting from the beginning of the pattern to the end, not from the end
     * to the beginning.
     *
     * You must use the Rabin-Karp Rolling Hash for this implementation. The
     * formula for it is:
     *
     * sum of: c * BASE ^ (pattern.length - 1 - i)
     *   c is the integer value of the current character, and
     *   i is the index of the character
     *
     * We recommend building the hash for the pattern and the first m characters
     * of the text by starting at index (m - 1) to efficiently exponentiate the
     * BASE. This allows you to avoid using Math.pow().
     *
     * Note that if you were dealing with very large numbers here, your hash
     * will likely overflow; you will not need to handle this case.
     * You may assume that all powers and calculations CAN be done without
     * overflow. However, be careful with how you carry out your calculations.
     * For example, if BASE^(m - 1) is a number that fits into an int, it's
     * possible for BASE^m will overflow. So, you would not want to do
     * BASE^m / BASE to calculate BASE^(m - 1).
     *
     * Ex. Hashing "bunn" as a substring of "bunny" with base 113
     * = (b * 113 ^ 3) + (u * 113 ^ 2) + (n * 113 ^ 1) + (n * 113 ^ 0)
     * = (98 * 113 ^ 3) + (117 * 113 ^ 2) + (110 * 113 ^ 1) + (110 * 113 ^ 0)
     * = 142910419
     *
     * Another key point of this algorithm is that updating the hash from
     * one substring to the next substring must be O(1). To update the hash,
     * subtract the oldChar times BASE raised to the length - 1, multiply by
     * BASE, and add the newChar as shown by this formula:
     * (oldHash - oldChar * BASE ^ (pattern.length - 1)) * BASE + newChar
     *
     * Ex. Shifting from "bunn" to "unny" in "bunny" with base 113
     * hash("unny") = (hash("bunn") - b * 113 ^ 3) * 113 + y
     *              = (142910419 - 98 * 113 ^ 3) * 113 + 121
     *              = 170236090
     *
     * Keep in mind that calculating exponents is not O(1) in general, so you'll
     * need to keep track of what BASE^(m - 1) is for updating the hash.
     *
     * Do NOT use Math.pow() in this method.
     *
     * @param pattern    a string you're searching for in a body of text
     * @param text       the body of text where you search for pattern
     * @param comparator you MUST use this to check if characters are equal
     * @return list containing the starting index for each match found
     * @throws java.lang.IllegalArgumentException if the pattern is null or has
     *                                            length 0
     * @throws java.lang.IllegalArgumentException if text or comparator is null
     */
    public static List<Integer> rabinKarp(CharSequence pattern,
                                             CharSequence text,
                                             CharacterComparator comparator) {
        if (Objects.isNull(comparator) || Objects.isNull(text)) {
            throw new IllegalArgumentException("Comparator or text is null...");
        }
        if (pattern.length() == 0 || Objects.isNull(pattern)) {
            throw new IllegalArgumentException("Pattern has zero length or is null...");
        }
        List<Integer> indexL;
        indexL = new ArrayList<>();
        if (pattern.length() <= text.length()) {
            int hashP;
            hashP = hasher(pattern);
            int hashN;
            hashN = hasher(text.subSequence(0, pattern.length()));

            if (hashP == hashN && checker(pattern,
                    text.subSequence(0, pattern.length()), comparator)) {
                indexL.add(0);
            }
            int i = 1;
            while (i < text.length() - pattern.length() + 1) {
                hashN = (hashN - (text.charAt(i - 1)
                        * pow(pattern.length() - 1)))
                        * BASE + text.charAt(i + pattern.length() - 1);
                if (hashN == hashP && checker(pattern,
                        text.subSequence(i, i + pattern.length()), comparator)) {
                    indexL.add(i);
                }
                i++;
            }
            return indexL;
        }
        return indexL;
    }

    /**
     * Checks if text and pattern matches
     *
     * @param pattern string in a body of text
     * @param text text where you search
     * @param comparator comparator to use
     * @return boolean for pattern and text matching
     */
    private static boolean checker(CharSequence pattern, CharSequence text,
                                 CharacterComparator comparator) {
        int i = 0;
        while (i < pattern.length()) {
            if (comparator.compare(pattern.charAt(i), text.charAt(i++)) != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Math.pow() homemade
     *
     * @param exp the power to raise
     * @return BASE^exp
     */
    private static int pow(int exp) {
        int num;
        num = 1;
        if (exp != 0) {
            if (exp != 1) {
                num *= IntStream.rangeClosed(1, exp).map(i -> BASE).reduce(1, (a, b) -> a * b);
            } else {
                return BASE;
            }
        } else {
            return 1;
        }
        return num;
    }

    /**
     * Calculates hash
     *
     * @param text string to calculate
     * @return hash value
     */
    private static int hasher(CharSequence text) {
        int tmp = 0;
        int i = 0;
        while (i < text.length()) {
            tmp += text.charAt(i) * pow(text.length() - 1 - i++);
        }
        return tmp;
    }
}