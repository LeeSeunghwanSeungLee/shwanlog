package com.shwanlog.service;

import com.shwanlog.domain.Post;
import com.shwanlog.request.PostCreateDto;
import com.shwanlog.response.PostResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostServiceTest {

    @Autowired
    PostService postService;

    @Test
    @DisplayName("단건을 저장하고, repository에서 조회했을때 결과 값을 확인한다")
    void test_1() {
        // given
        PostCreateDto postCreateDto = PostCreateDto.builder()
                .title("제목_1")
                .content("내용_1")
                .build();

        // when
        postService.write(postCreateDto);

        // then
        List<PostResponseDto> result = postService.findAll();
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getId()).isEqualTo(1L);
        assertThat(result.get(0).getTitle()).isEqualTo("제목_1");
        assertThat(result.get(0).getContent()).isEqualTo("내용_1");
    }

}