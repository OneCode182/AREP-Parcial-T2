package org.example;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LinealSearchController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/linearsearch")
    public LinearSearch app(@RequestParam(value = "list") int[] items, @RequestParam(value = "value") int value) {
        // TODO
        // return new LinearSearch(counter.incrementAndGet(), String.format(template, name));
        int output = Algorithm.logic(items, value);
        return new LinearSearch("linearSearch", items, value, output);

    }


}