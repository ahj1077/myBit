package com.an.mybit.board.controller;

import com.an.mybit.board.dto.PostDTO;
import com.an.mybit.board.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/board")
@AllArgsConstructor
public class BoardController {

    private BoardService boardService;

    @CrossOrigin
    @PostMapping("/newPost")
    public void createNewPost(@Valid PostDTO newPostDTO, BindingResult bindingResult){

        log.info("createNewPost = {}", newPostDTO);
        boardService.createNewPost(newPostDTO);
    }

    @CrossOrigin
    @GetMapping("/posts/{market}")
    public List<PostDTO> retrievePosts(@PathVariable(required = true) String market){

        log.info("retrievePosts market = {}", market);
        return boardService.retrievePosts(market);
    }

    @CrossOrigin
    @GetMapping("/post/{id}")
    public PostDTO retrievePost(@PathVariable(required = true) Integer id){

        log.info("retrievePost id = {}", id);
        return boardService.retrievePost(id);
    }

    @CrossOrigin
    @PatchMapping("/post")
    public void updatePost(@Valid PostDTO postDTO){

        log.info("updatePost = {}", postDTO);
        boardService.updatePost(postDTO);
    }

    @CrossOrigin
    @DeleteMapping("/post/{id}")
    public void removePost(@PathVariable(required = true) Integer id){

        log.info("removePost id = {}", id);
        boardService.removePost(id);
    }




}
