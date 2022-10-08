package com.fazz.fazzconnect.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardController {

    @GetMapping("/fazz")
    public String fazz() {
        return "<h1>Welcome to fazz on line</h1>";
    }
}
