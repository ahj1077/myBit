package com.an.mybit.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;


import lombok.AllArgsConstructor;

@Slf4j
@Controller
@AllArgsConstructor
public class MainController {

    @CrossOrigin
    @GetMapping(value="/")
    public String vue(){

        return "index";
    }
    
}