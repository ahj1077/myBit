package com.an.mybit.board.service;

import com.an.mybit.board.dto.PostDTO;
import com.an.mybit.board.repository.BoardRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class BoardService {

    private BoardRepository repository;

    public void createNewPost(PostDTO newPostDTO) {

        repository.createNewPost(newPostDTO);
    }

    public List<PostDTO> retrievePosts(String market){

        return repository.retrievePosts(market);
    }

    public PostDTO retrievePost(Integer id){

        return repository.retrievePost(id);
    }

    public void updatePost(PostDTO postDTO){

        repository.updatePost(postDTO);
    }

    public void removePost(Integer id){

        repository.removePost(id);
    }
}
