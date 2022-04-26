package com.h2.jpa.study.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateBoardDto {

    private Long id;

    private String title;

    private String content;

    @Builder
    public UpdateBoardDto(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
    
}
