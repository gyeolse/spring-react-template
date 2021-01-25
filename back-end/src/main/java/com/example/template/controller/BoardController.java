package com.example.template.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class BoardController {
    @GetMapping("test")
    public String test() {
        return "hello 현재  서버시간은"+new Date() + "입니다. \n";
    }

    @GetMapping("hell-mvc")
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model) {
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody //http에서 body부에 이 데이터를 직접 넣어주겠다라는 뜻.
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; //그대로 보내준다!
    }

    //JSON 형식으로 반환 된다. 객체로 받아서 전달하면, 자동으로 JSON으로 세팅이 됨.
    //과거에는 xml이 많이 쓰였지만, 현재는 JSON이 많이 쓰이는 추세
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }


    //클래스 안에 클래스 선언 가능함.
    static class Hello {
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}
