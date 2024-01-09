package com.onlog.domain;

import com.onlog.request.PostEdit;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String content;

    @Builder
    public Post(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public void change(PostEdit postEdit){
        this.title = postEdit.getTitle() != null ? postEdit.getTitle() : this.title;
        this.content = postEdit.getContent() != null ? postEdit.getContent() : this.content;
    }
}
