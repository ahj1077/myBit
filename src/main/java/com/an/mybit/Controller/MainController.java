package com.an.mybit.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class MainController {

    @RequestMapping(value="/vue")
    public String vue(){

        return "index";
    }

}