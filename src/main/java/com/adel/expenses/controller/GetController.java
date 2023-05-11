package com.adel.expenses.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(value = "/get")
public class GetController {

    @GetMapping(value = "/test1")
    public String test1() {
        return new Date().toString();
    }

    @GetMapping(value = "/test2")
    public String test2(@RequestParam(value = "x", defaultValue = "0") int x,
                        @RequestParam(value = "y", defaultValue = "0") int y,
                        @RequestHeader(value = "sec-ch-ua-platform",required = false) String temp) {
        System.out.println(temp);
        return String.format("%d x %d = %d", x, y, x * y);
    }

    @GetMapping(value = "/test3/{x}/{y}")
    public String test3(@PathVariable(value = "x") int x,
                        @PathVariable(value = "y") int y) {
        return String.format("%d x %d = %d", x, y, x * y);
    }

    @GetMapping(value = "/test4/{x}")
    public String test4(@PathVariable(value = "x") int x,
                        @RequestHeader(value = "y") int y) {
        return String.format("%d x %d = %d", x, y, x * y);
    }

}


