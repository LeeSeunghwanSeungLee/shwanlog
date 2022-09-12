package com.shwanlog.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class PostCreateDto {
    @NotBlank(message = "PostCreateDto.title = { 제목이 비어있습니다.}")
    private String title;

    @NotBlank(message = "PostCreatDto.content = { 컨텐츠가 비어있습니다. }")
    private String content;

    @Builder
    public PostCreateDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
