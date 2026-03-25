package org.example;


import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BinarySearchController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/binarysearch")
    public BinarySearch app(@RequestParam(value = "list") int[] items, @RequestParam(value = "value") int value) {
        int output = Algorithm.logic(items, value);
        return new BinarySearch("binarySearch", items, value, output);

    }


}