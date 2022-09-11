package com.shwanlog.response;

import lombok.*;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class PostResponseDto {
    private Long id;

    private String title;

    private String content;

    @Builder
    public PostResponseDto(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
