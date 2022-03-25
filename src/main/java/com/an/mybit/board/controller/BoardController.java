package com.an.mybit.Controller;

import com.an.mybit.DTO.NewPostDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/board")
public class BoardController {

    @CrossOrigin
    @PostMapping("/newPost")
    public void createNewPost(@Valid NewPostDTO newPostDTO, BindingResult bindingResult){
        log.info("title = {}", newPostDTO.getTitle());
        log.info("content = {}", newPostDTO.getContent());
    }
}
