package com.an.mybit.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class MainController {
    
    @RequestMapping(value="/vue", method=RequestMethod.GET)
    public String vue(){

        return "";
    }
    
}