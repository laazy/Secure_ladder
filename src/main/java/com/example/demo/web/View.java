package com.example.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class View
{
    @RequestMapping("/")
    public String view(){
        return "index.html";
    }
}
