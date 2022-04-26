package com.h2.jpa.study.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateBoardDto {

    private String title;

    private String content;

    @Builder
    public CreateBoardDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
