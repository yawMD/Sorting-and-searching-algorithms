package cs401_project;

import java.util.HashMap;
import java.util.Map;


public class Search<T extends Comparable<T>> {

    public int linearSearch(T[] arr, T target) {
        // Loop through the array elements one by one
        for (int i = 0; i < arr.length; i++) {
            // If the current element matches the target, return its index
            if (arr[i].equals(target)) {
                return i; // Target found, return the index
            }
        }
        // If the target is not found, return -1 to indicate that it's not in the array
        return -1; // Target not found
    }

    public int binarySearch(T[] arr, T target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            int comparisonResult = arr[mid].compareTo(target);
            if (comparisonResult == 0) {
                return mid; // Element found
            } else if (comparisonResult < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1; // Element not found
    }

    // Hash function search method
    public int hashSearch(T[] arr, T target) {
        Map<T, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            hashMap.put(arr[i], i);
        }

        return hashMap.getOrDefault(target, -1); // Returns the index if found, -1 otherwise
    }
}