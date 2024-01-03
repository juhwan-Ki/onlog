package com.onlog.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class PostCreate {

    @NotBlank(message = "타이틀을 입력해주세요.")
    private String title;
    @NotBlank(message = "컨텐츠를 입력해주세요.")
    private String content;

    @Builder
    public PostCreate(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // 빌더의 장점
    // - 가독성이 좋다.
    // - 값 생성에 대한 유효함
    // - 필요한 값만 받을 수 있다. (오버로딩 가능한 조건은?)
    // - 객체 불변성
}
