package org.example;

public class BinarySearchAlgorithm {

    public static int logic(int[] items, int value) {
        return binarySearchRecursive(items, value, 0, items.length - 1);
    }

    private static int binarySearchRecursive(int[] items, int value, int low, int high) {
        if (low > high) {
            return -1;
        }

        int mid = low + (high - low) / 2;

        if (items[mid] == value) {
            if (mid > low && items[mid - 1] == value) {
                return binarySearchRecursive(items, value, low, mid - 1);
            }
            return mid;
        } else if (items[mid] > value) {
            return binarySearchRecursive(items, value, low, mid - 1);
        } else {
            return binarySearchRecursive(items, value, mid + 1, high);
        }
    }
}
