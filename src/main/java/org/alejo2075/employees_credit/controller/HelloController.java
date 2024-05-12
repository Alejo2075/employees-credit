package org.alejo2075.employees_credit.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/helloworld")
    public String registerUser() {
        return "Hello World";
    }
}
