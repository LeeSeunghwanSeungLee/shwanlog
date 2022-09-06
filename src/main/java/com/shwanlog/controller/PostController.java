package com.shwanlog.controller;

import com.shwanlog.request.PostCreate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class PostController {
    @PostMapping("/posts")
    public String get(@ModelAttribute PostCreate postCreate) {
        log.info("posts cards = {}", postCreate);
        return "Hello World";
    }
}
