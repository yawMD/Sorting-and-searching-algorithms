package cs401_project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Main file contains a couple of helper methods together with main method
 *
 * @author Emmanuel MacDan Opoku-Ware
 * @version .1
 * @date 28/11/2023
 */

public class Main {

    public static Person[] readDataFromFile(String filename) throws IOException {
        int count = countLines(filename);
        Person[] people = new Person[count];

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                people[i++] = new Person(parts[0], Integer.parseInt(parts[1]));
            }
        }
        return people;
    }

    private static int countLines(String filename) throws IOException {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            while (reader.readLine() != null) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            int selection = scanner.nextInt();

            switch (selection) {
                case 1:
                    sortData();
                    break;
                case 2:
                    searchData(scanner);
                    break;
                case 3:
                    System.out.println("\nExiting... Thank you!");
                    return;
                default:
                    System.out.println("\nINVALID SELECTION\n");
            }
        }
    }
    public static void sortData() {
        try {
            Person[] people = readDataFromFile("data.txt");

            Integer[] numbers = new Integer[people.length];
            for (int i = 0; i < people.length; i++) {
                numbers[i] = people[i].number;
            }

            Sort<Integer> sorter = new Sort<>();
            SortResult<Integer> mergeSortResult = sorter.mergeSort(numbers.clone());
            System.out.println("\n--- Merge Sort Results ---");
            System.out.println("Comparisons: " + mergeSortResult.comparisons);
            printArraySummary(mergeSortResult.sortedArray, "Sorted Array: ");

            SortResult<Integer> bubbleSortResult = sorter.bubbleSort(numbers.clone());
            System.out.println("\n--- Bubble Sort Results ---");
            System.out.println("Comparisons: " + bubbleSortResult.comparisons);
            printArraySummary(bubbleSortResult.sortedArray, "Sorted Array: ");

            int bubleSortCount =  bubbleSortResult.comparisons;
            int mergeSortCount =  mergeSortResult.comparisons;

            if (bubleSortCount > mergeSortCount) {
                System.out.println("Merge Sort is better with " + mergeSortCount + " comparisons compared to Bubble Sort's " + bubleSortCount + " comparisons. This is because Merge Sort's time complexity is O(n log n) which is generally more efficient than Bubble Sort's O(n^2) for large datasets such as the provided.");
            } else {
                System.out.println("Bubble Sort is better with " + bubleSortCount + " comparisons compared to Merge Sort's " + mergeSortCount + " comparisons. However, this might be due to a smaller dataset size or already sorted data. Generally, Merge Sort is more efficient for larger datasets.");
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private static void printArraySummary(Integer[] array, String message) {
        System.out.print(message);
        if (array.length > 10) {
            for (int i = 0; i < 5; i++) {
                System.out.print(array[i] + ", ");
            }
            System.out.print("..., ");
            for (int i = array.length - 5; i < array.length; i++) {
                System.out.print(array[i]);
                if (i < array.length - 1) System.out.print(", ");
            }
        } else {
            for (int i = 0; i < array.length; i++) {
                System.out.print(array[i]);
                if (i < array.length - 1) System.out.print(", ");
            }
        }
        System.out.println();
    }

    private static void printMenu() {
        System.out.println("\n+--------------------------------+");
        System.out.println("|       Sorting & Searching      |");
        System.out.println("+--------------------------------+");
        System.out.println("| 1. Sorting                     |");
        System.out.println("| 2. Searching                   |");
        System.out.println("| 3. Exit                        |");
        System.out.println("+--------------------------------+");
        System.out.print("Select an option [1-3]: ");
    }

    private static void printSearchResult(String searchType, int index, Integer[] sortedNumbers, int numberToSearch, long duration) {
        String colorReset = "\u001B[0m"; // Reset color
        String colorRed = "\u001B[91m";   // Red color
        String colorGreen = "\u001B[32m"; // Green color

        System.out.println("\n" + getArt(searchType) + " " + searchType + " Result:");
        if (index >= 0) {
            System.out.println(colorGreen + "Found number " + sortedNumbers[index] + " at index: " + index + colorReset);
        } else {
            System.out.println(colorRed + "Number " + numberToSearch + " not found." + colorReset);
        }
        System.out.println("Search Time: " + duration + " ns");
    }


    public static void searchData(Scanner scanner) {
        try {
            Person[] people = readDataFromFile("data.txt");

            Integer[] numbers = new Integer[people.length];
            for (int i = 0; i < people.length; i++) {
                numbers[i] = people[i].number;
            }

            Search<Integer> searcher = new Search<>();
            System.out.println("Enter the number to search for:");
            int numberToSearch = scanner.nextInt();

            SortResult<Integer> sortResult = new Sort<Integer>().mergeSort(numbers.clone());
            Integer[] sortedNumbers = sortResult.sortedArray;

            //linear search
            long startTime = System.nanoTime();
            int linearIndex = searcher.linearSearch(sortedNumbers, numberToSearch);
            long endTime = System.nanoTime();
            printSearchResult("Linear Search", linearIndex, sortedNumbers, numberToSearch, endTime - startTime);

            //binary search
            startTime = System.nanoTime();
            int binaryIndex = searcher.binarySearch(sortedNumbers, numberToSearch);
            endTime = System.nanoTime();
            printSearchResult("Binary Search", binaryIndex, sortedNumbers, numberToSearch, endTime - startTime);

            // Hash Function Search
            startTime = System.nanoTime();
            int hashIndex = searcher.hashSearch(sortedNumbers, numberToSearch);
            endTime = System.nanoTime();
            printSearchResult("Hash Function Search", hashIndex, sortedNumbers, numberToSearch, endTime - startTime);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private static String getArt(String searchType) {
       //Art for style in my code Lol #Emmanuel
        if ("Linear Search".equals(searchType)) {
            return "⚫⚫⚫⚫⚫"; //
        } else if ("Binary Search".equals(searchType)) {
            return "▲▲▲▲▲"; //
        } else if ("Hash Function Search".equals(searchType)) {
            return "■■■■■"; //
        } else {
            return "";
        }
    }
}

