package com.shwanlog.controller;

import com.shwanlog.request.PostCreateDto;
import com.shwanlog.response.PostResponseDto;
import com.shwanlog.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostService postService;

    @PostMapping("/posts")
    public String postNewPosting(@RequestBody @Valid PostCreateDto postCreateDto) {
        log.info("data = {}", postCreateDto.toString());
        this.postService.write(postCreateDto);
        return "Hello World";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public PostResponseDto getPosting(@PathVariable Long id) {
        return this.postService.get(id);
    }
}
