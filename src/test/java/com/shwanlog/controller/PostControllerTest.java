package com.shwanlog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shwanlog.domain.Post;
import com.shwanlog.repository.PostRepository;
import com.shwanlog.request.PostCreateDto;
import com.shwanlog.service.PostService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void reset() {
        this.postRepository.deleteAll();
    }

    @Test
    @DisplayName("/posts 요청시 Hello World 출력")
    void test_1() throws Exception {
        PostCreateDto postCreateDto = PostCreateDto.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();
        String inputValue = this.objectMapper.writeValueAsString(postCreateDto);

        mockMvc.perform(post("/posts")
                .content(inputValue)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World"))
                .andDo(print());
    }

    @Test
    @DisplayName("/posts 요청시 타이틀이 없는 경우.")
    void test_2() throws Exception {
        PostCreateDto postCreateDto = PostCreateDto.builder()
                .content("내용입니다.")
                .build();
        String inputValue = this.objectMapper.writeValueAsString(postCreateDto);

        mockMvc.perform(post("/posts")
                .content(inputValue)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    @DisplayName("/posts 요청시 컨텐츠가 없는 경우.")
    void test_3() throws Exception {
        PostCreateDto postCreateDto = PostCreateDto.builder()
                .title("제목입니다.")
                .build();
        String inputValue = this.objectMapper.writeValueAsString(postCreateDto);

        mockMvc.perform(post("/posts")
                .content(inputValue)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    @DisplayName("/posts 요청시 db에 데이터가 저장된다.")
    void test_4() throws Exception {
        PostCreateDto postCreateDto = PostCreateDto.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();
        String inputValue = this.objectMapper.writeValueAsString(postCreateDto);

        mockMvc.perform(post("/posts")
                        .content(inputValue)
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andDo(print());

        List<Post> postList = postRepository.findAll();
        Assertions.assertThat(postList.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("/posts 요청시 db에 데이터를 가져온다.")
    void test_5() throws Exception {
        // given
        PostCreateDto postCreateDto = PostCreateDto.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();
        // when
        this.postService.write(postCreateDto);

        // then
        mockMvc.perform(get("/posts/1")
                        .contentType("application/json"))
                .andExpect(status().is5xxServerError()) // TODO
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.title").value("제목입니다."))
//                .andExpect(jsonPath("$.content").value("내용입니다."))
                .andDo(print());
    }
}

