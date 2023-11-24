package com.onlog.serivce;

import com.onlog.domain.Post;
import com.onlog.repository.PostRepository;
import com.onlog.request.PostCreate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void write(PostCreate postCreate) {
        postRepository.save(Post.builder().title(postCreate.getTitle()).content(postCreate.getContent()).build());
    }
}
