package com.onlog.serivce;

import com.onlog.domain.Post;
import com.onlog.repository.PostRepository;
import com.onlog.request.PostCreate;
import com.onlog.request.PostSearch;
import com.onlog.response.PostResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void clean () {
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("글 작성")
    void test1() {
        // given
        PostCreate request = PostCreate.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();

        // when
        postService.write(request);

        // then
        assertEquals(1L, postRepository.count());
        Post post = postRepository.findAll().get(0);
        assertEquals("제목입니다.", post.getTitle());
        assertEquals("내용입니다.", post.getContent());
    }

    @Test
    @DisplayName("글 1개 조회")
    void tes2() {
        // given
        Post requsetPost = Post.builder()
                .title("foo")
                .content("bar")
                .build();
        postRepository.save(requsetPost);

        // when
        PostResponse postResponse = postService.get(requsetPost.getId());

        // then
        assertNotNull(postResponse);
        assertEquals("foo", postResponse.getTitle());
        assertEquals("bar", postResponse.getContent());
    }

    @Test
    @DisplayName("글 1페이지 조회")
    void tes3() {
        // given
        List<Post> requestPosts = IntStream.range(0, 20)
                .mapToObj(i -> Post.builder()
                            .title("제목 - " + i)
                            .content("반포자이 - " + i)
                            .build())
                .collect(Collectors.toList());

        postRepository.saveAll(requestPosts);

        PostSearch postSearch = PostSearch.builder()
                .page(1)
                .size(10)
                .build();

        // when
        List<PostResponse> posts = postService.getList(postSearch);

        // then
        assertEquals(10L, posts.size());
        assertEquals("제목 - 19", posts.get(0).getTitle());

    }

    @Test
    @DisplayName("페이지를 0으로 요청하면 첫 페이지를 가져온다")
    void tes4() {
        // given
        List<Post> requestPosts = IntStream.range(0, 20)
                .mapToObj(i -> Post.builder()
                        .title("제목 - " + i)
                        .content("반포자이 - " + i)
                        .build())
                .collect(Collectors.toList());

        postRepository.saveAll(requestPosts);

        PostSearch postSearch = PostSearch.builder()
                .page(0)
                .size(10)
                .build();

        // when
        List<PostResponse> posts = postService.getList(postSearch);

        // then
        assertEquals(10L, posts.size());
        assertEquals("제목 - 19", posts.get(0).getTitle());

    }
}