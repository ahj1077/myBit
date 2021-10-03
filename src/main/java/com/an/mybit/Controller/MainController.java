package com.an.mybit.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class MainController {
    
    @GetMapping(value="/vue")
    public String vue(){

        return "index";
    }
    
}