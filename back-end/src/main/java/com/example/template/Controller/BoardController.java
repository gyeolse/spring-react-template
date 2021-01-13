package com.example.template.Controller;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class BoardController {
    @GetMapping("/test")
    public String test() {
        return "hello 현재  서버시간은"+new Date() + "입니다. \n";
    }
}
