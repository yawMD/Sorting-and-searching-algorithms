package cs401_project;

class SortResult<T> {
    T[] sortedArray;
    int comparisons;

    SortResult(T[] sortedArray, int comparisons) {
        this.sortedArray = sortedArray;
        this.comparisons = comparisons;
    }
}
