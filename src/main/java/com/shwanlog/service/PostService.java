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
        Post post = Post.builder()
                        .title(postCreateDto.getTitle())
                        .content(postCreateDto.getContent())
                        .build();
        log.info("saved data = {}", post);
        this.postRepository.save(post);
    }
}
