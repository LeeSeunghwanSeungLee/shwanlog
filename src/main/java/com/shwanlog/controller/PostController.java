package com.shwanlog.controller;

import com.shwanlog.request.PostCreateDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
public class PostController {
    @PostMapping("/posts")
    public String get(@RequestBody @Valid PostCreateDto postCreateDto) {
        return "Hello World";
    }
}
