package com.shwanlog.service;

import com.shwanlog.domain.Post;
import com.shwanlog.repository.PostRepository;
import com.shwanlog.request.PostCreateDto;
import com.shwanlog.response.PostResponseDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<PostResponseDto> findAll() {
        List<Post> postList = this.postRepository.findAll();
        return postList.stream().map((post) -> {
                    return PostResponseDto.builder()
                            .id(post.getId())
                            .title(post.getTitle())
                            .content(post.getContent())
                            .build();
                }).collect(Collectors.toList());
    }
}
