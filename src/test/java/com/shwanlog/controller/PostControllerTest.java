package com.shwanlog.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PostController.class)
class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("/posts 요청시 Hello World 출력")
    void test_1() throws Exception {
        mockMvc.perform(post("/posts")
                .content("{\"title\": \"제목입니다.\", \"content\": \"내용입니다.\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World"))
                .andDo(print());
    }

    @Test
    @DisplayName("/posts 요청시 타이틀이 없는 경우.")
    void test_2() throws Exception {
        mockMvc.perform(post("/posts")
                        .content("{\"title\": \"\", \"content\": \"내용입니다.\"}"))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    @DisplayName("/posts 요청시 컨텐츠가 없는 경우.")
    void test_3() throws Exception {
        mockMvc.perform(post("/posts")
                        .content("{\"title\": \"제목입니다.\", \"content\": \"\"}"))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }
}

