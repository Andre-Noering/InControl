package com.entra21.LojaSimulator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/teste")
    public String get() {
        return "Oi";
    }
}
