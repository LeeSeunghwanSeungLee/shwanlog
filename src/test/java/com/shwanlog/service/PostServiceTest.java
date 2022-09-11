package com.shwanlog.service;

import com.shwanlog.domain.Post;
import com.shwanlog.repository.PostRepository;
import com.shwanlog.request.PostCreateDto;
import com.shwanlog.response.PostResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostServiceTest {

    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;

    @BeforeEach
    void deleteAll() {
        this.postRepository.deleteAll();
    }

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

    @Test
    @DisplayName("단건을 저장하고, id를 토대로 한개의 결과값만 반환한다.")
    void test_2() {
        // given
        PostCreateDto postCreateDto = PostCreateDto.builder()
                .title("제목_1")
                .content("내용_1")
                .build();

        // when
        postService.write(postCreateDto);

        // then
        PostResponseDto result = postService.findAll().get(0);
        assertThat(result.getTitle()).isEqualTo("제목_1");
        assertThat(result.getContent()).isEqualTo("내용_1");
        assertThat(postRepository.count()).isEqualTo(1);
    }

    @Test
    @DisplayName("단건을 조회하고, 존재하지 않는 id를 요청하여 예외가 발생한다.")
    void test_3() {
        // given
        PostCreateDto postCreateDto = PostCreateDto.builder()
                .title("제목_1")
                .content("내용_1")
                .build();

        // when
        postService.write(postCreateDto);

        // then
        Assertions.assertThatThrownBy(() -> { postService.get(2L); }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("조회 되지 않는 id값을 요청하였습니다.");
    }
}