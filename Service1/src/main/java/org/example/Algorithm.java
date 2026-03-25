package org.example;

public class Algorithm {
    public static int logic(int[] items, int value) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
