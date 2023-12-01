package cs401_project;

/**
 * Sort contains two sorting methods....
 * using two different algorithms namely bubble and merge sort.
 * 
 * @author Emmanuel MacDan Opoku-Ware
 * @version .1
 * @date 28/11/2023
 */
import java.util.Arrays;

public class Sort<T extends Comparable<T>> {

    /**
     * Merge sort
     */
    public SortResult<T> mergeSort(T[] input) {
        int[] comparisons = new int[1];
        T[] result = mergeSort(input, comparisons, 0);
        return new SortResult<>(result, comparisons[0]);
    }

    private T[] mergeSort(T[] input, int[] comparisons, int depth) {
        if (input.length <= 1) {
            return input;
        }

        T[] first = Arrays.copyOfRange(input, 0, input.length / 2);
        T[] second = Arrays.copyOfRange(input, input.length / 2, input.length);

        mergeSort(first, comparisons, depth + 1);
        mergeSort(second, comparisons, depth + 1);

        return merge(first, second, input, comparisons);
    }

    private T[] merge(T[] first, T[] second, T[] result, int[] comparisons) {
        int firstElement = 0;
        int secondElement = 0;
        int j = 0;

        while (firstElement < first.length && secondElement < second.length) {
            comparisons[0]++; // Increment comparison count
            if (first[firstElement].compareTo(second[secondElement]) < 0) {
                result[j] = first[firstElement];
                firstElement++;
            } else {
                result[j] = second[secondElement];
                secondElement++;
            }
            j++;
        }

        while (firstElement < first.length) {
            result[j] = first[firstElement];
            firstElement++;
            j++;
        }

        while (secondElement < second.length) {
            result[j] = second[secondElement];
            secondElement++;
            j++;
        }

        return result;
    }

    // When mergeSort is called:
//    T[] sortedArray = mergeSort(array);



    /**
     * Bubble sort
     */
    public SortResult<T> bubbleSort(T[] input) {
        int comparisons = 0; // Counter for the number of comparisons
        boolean swapped;

        do {
            swapped = false;
            for (int i = 0; i < input.length - 1; i++) {
                comparisons++; // Increment for each comparison
                if (input[i].compareTo(input[i + 1]) > 0) {
                    // Swap elements
                    T temp = input[i];
                    input[i] = input[i + 1];
                    input[i + 1] = temp;
                    swapped = true;
                }
            }
        } while (swapped);

        return new SortResult<>(input, comparisons);
    }
}
