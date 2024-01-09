package com.onlog.serivce;

import com.onlog.domain.Post;
import com.onlog.repository.PostRepository;
import com.onlog.request.PostCreate;
import com.onlog.request.PostEdit;
import com.onlog.request.PostSearch;
import com.onlog.response.PostResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void write(PostCreate postCreate) {
        postRepository.save(Post.builder()
                .title(postCreate.getTitle())
                .content(postCreate.getContent())
                .build());
    }

    public PostResponse get(Long id) {
       Post post = postRepository.findById(id)
               .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다."));

       return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();
    }

    public List<PostResponse> getList(PostSearch postSearch) {
        return postRepository.getList(postSearch).stream()
                .map(PostResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void edit(Long id, PostEdit postEdit) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다."));

        post.change(postEdit);
    }

    public void delete(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다."));

        postRepository.delete(post);
    }
}
