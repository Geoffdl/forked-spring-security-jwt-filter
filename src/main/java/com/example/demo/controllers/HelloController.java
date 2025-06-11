package com.example.demo.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/public")
    public String getHelloPublic() throws Exception {
        return "hello public";
    }

    @GetMapping("/private")
    public String getHelloPrivate() throws Exception {
        return "hello private";
    }
    
    
    @GetMapping({"/admin"})
    public String getHelloAdmin() {
        return "hello admin";
    }
    @GetMapping({"/user"})
    public String getHelloUser(){
        return "hello user";
    }
}
