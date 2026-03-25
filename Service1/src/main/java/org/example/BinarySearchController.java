package org.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BinarySearchController {

    @GetMapping("/binarysearch")
    public BinarySearch app(@RequestParam(value = "list") int[] items, @RequestParam(value = "value") int value) {
        int output = BinarySearchAlgorithm.logic(items, value);
        return new BinarySearch("binarySearch", items, value, output);
    }
}
