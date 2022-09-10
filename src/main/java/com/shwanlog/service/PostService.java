package com.shwanlog.service;

import com.shwanlog.domain.Post;
import com.shwanlog.repository.PostRepository;
import com.shwanlog.request.PostCreateDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    public void write(PostCreateDto postCreateDto) {
        log.info("service layer = {}", postCreateDto.toString());
        Post post = new Post(postCreateDto.getTitle(), postCreateDto.getContent());
        log.info("saved data = {}", post);
        this.postRepository.save(post);
    }
}
