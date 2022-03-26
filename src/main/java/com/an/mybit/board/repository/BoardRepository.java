package com.an.mybit.board.repository;

import com.an.mybit.board.dto.PostDTO;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class BoardRepository {

    //DB가 미정이므로 우선 메모리에 저장
    private Map<Integer, PostDTO> boardStore = new ConcurrentHashMap<>();

    private static Integer sequance = 5;

    BoardRepository(){
        boardStore.put(1, new PostDTO(1, "BTC", "비트코인1", "내용1", "작성자1", 1, "2022/03/25 16:51:00"));
        boardStore.put(2, new PostDTO(2, "BTC", "비트코인2", "내용2", "작성자2", 2, "2022/03/25 16:52:00"));
        boardStore.put(3, new PostDTO(3, "ETH", "이더리움1", "내용1", "작성자1", 3, "2022/03/25 16:53:00"));
        boardStore.put(4, new PostDTO(4, "ETH", "이더리움2", "내용2", "작성자2", 4, "2022/03/25 16:54:00"));
    }

    public void createNewPost(PostDTO newPostDTO) {
        newPostDTO.setId(sequance++);
        newPostDTO.setWriteDttm(getNowDttm());
        boardStore.put(sequance - 1, newPostDTO);
    }

    public List<PostDTO> retrievePosts(String market){

        return boardStore.values().stream()
                .filter(post -> market.equals(post.getCoinSymbol()))
                .sorted(Comparator.comparing(PostDTO::getId).reversed())
                .collect(Collectors.toList());
    }

    public PostDTO retrievePost(Integer id){

        return boardStore.get(id);
    }

    public void updatePost(PostDTO postDTO){
        int id = postDTO.getId();

        boardStore.get(id).setContent(postDTO.getContent());
    }

    public void removePost(Integer id){

        boardStore.remove(id);
    }

    private String getNowDttm(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
    }
}
