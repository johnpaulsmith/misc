/**
 * @author John Paul Smith
 *
 * This class contains two approaches for generating all possible permutations
 * of a given input sequence of characters. This is meant as a demonstration of
 * O(n!) growth as generating the permutations of even just the alphabet would
 * result in 26! = 403,291,461,126,605,635,584,000,000 permutations. This code
 * might be useful in situations where you need a quick way to generate all
 * permutations of a very small set (ie. less than 10 elements). 
 *
 * Between the two approaches, the iterative approach is faster and does not
 * generate redundant permutations on input with repeat elements (the input of
 * "aaa" will only produce one single permutation). The one caveat is that the
 * data set needs to be sorted for the algorithm to work correctly, so this is
 * another algorithm for which sorting is a fundamental sub-problem of a larger
 * algorithm. Don Knuth called this one simply "Algorithm L".
 *
 * The recursive approach is fairly inferior and mostly there to answer the
 * inevitable "but what about a recursive solution?" question. It isn't
 * asymptotically slower but the overhead of removing items from one list and
 * appending them to another slows it down by significant constant factor.
 */
package misc;

import java.util.Arrays;

public class Permutations {

    private Permutations() {
    }

    /**
     *
     * Recursively generate and output all possible permutations of a given
     * input String. Strings with duplicate characters will create redundant
     * permutations.
     *
     * @param s the String containing the character sequence of which all
     * possible permutations are to be generated.
     */
    public static void recursivePermute(String s) {
        recursivePermutations(new StringBuilder(s), new StringBuilder());
    }

    /**
     *
     * Recursively generate and output all possible permutations of a given
     * input String. Strings with duplicate characters will create redundant
     * permutations.
     *
     * @param a the StringBuilder containing the sub-sequence of the original
     * input that remains to be added to one of the possible output sequences. a
     * should be the complete original input sequence on the first call to this
     * method.
     * @param b the StringBuilder containing a permutation of the original
     * input. If a is empty, then b is a complete permutation of the original
     * input sequence, else b is an incomplete permutation of the original input
     * sequence to be completed by appending the remaining characters from a. b
     * should be an empty StringBuilder on the first call to this method.
     */
    public static void recursivePermutations(StringBuilder a, StringBuilder b) {

        /**
         * If a is empty then b is a permutation of the original input.
         */
        if (a.length() == 0) {

            //TODO the desired action to take on the permutation.
            //Eg. System.out.println(Arrays.toString(a)); or write out to a file
            return;
        }

        /**
         * For each character in a, remove it then append to b and recurse.
         */
        for (int x = 0; x < a.length(); ++x) {

            StringBuilder t = new StringBuilder(a.toString());

            StringBuilder q = new StringBuilder(b.toString());

            q.append(t.charAt(x));

            recursivePermutations(t.deleteCharAt(x), q);
        }
    }

    /**
     *
     * Iteratively generate and output all unique permutations of a given
     * character array.
     *
     * @param A the character array containing the list of which all possible
     * permutations are to be generated.
     */
    public static void uniquePermutations(final char[] A) {

        /**
         * The algorithm requires rearrangement of the input array so a copy is
         * made to preserve the order of the original.
         */
        char[] a = Arrays.copyOf(A, A.length);

        /**
         * Output the size 1 or empty array cases and return.
         */
        if (a.length <= 1) {

            //TODO the desired action to take on the permutation.
            //Eg. System.out.println(Arrays.toString(a)); or write out to a file
            return;
        }

        /**
         * The data set must be sorted for the algorithm to perform correctly.
         */
        Arrays.sort(a);

        int i, j, k;
        char t;

        while (true) {

            /**
             * Output the current permutation of the data set as the first step
             * of the algorithm.
             */
            //TODO the desired action to take on the permutation.
            //Eg. System.out.println(Arrays.toString(a)); or write out to a file           
            /**
             * Set i as the index of the second to the last element. Decrement i
             * until a[i] < a[i + 1]. Return if i < 0 (test if the array is
             * reverse-sorted). A reverse-sorted array is the exit case for the
             * algorithm.
             */
            i = a.length - 2;

            while (a[i] >= a[i + 1]) {

                if (--i < 0) {
                    return;
                }
            }

            /**
             * Set j as the index of the last element
             * Decrement j until a[i] < a[j] then exchange a[i] with a[j]
             */
            j = a.length - 1;

            while (a[i] >= a[j]) {
                --j;
            }

            t = a[i];
            a[i] = a[j];
            a[j] = t;

            /**
             * Reverse the order of all elements above a[i]
             */
            j = a.length - 1;
            k = i + 1;

            while (k < j) {

                t = a[k];
                a[k++] = a[j];
                a[j--] = t;
            }
        }
    }
}
