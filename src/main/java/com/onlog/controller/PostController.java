package com.onlog.controller;

import com.onlog.domain.Post;
import com.onlog.request.PostCreate;
import com.onlog.response.PostResponse;
import com.onlog.serivce.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // SSR -> jsp, thymeleaf 와 같은
        // -> html rendering
    // SPA -> vue, react 와 같은
        // -> js + <-> API(JSON)
    @GetMapping("/")
    public String get() {
        return "Hello world";
    }

    @PostMapping("/posts")
    public void post(@RequestBody @Valid PostCreate request) throws Exception {
        postService.write(request);
    }

    /*
    * /posts -> 글 전체 조회(검색 + 페이징)
    * /posts/{postId} -> 글 한개만 조회
    * */
    @GetMapping("/posts/{postId}")
    public PostResponse get(@PathVariable(name = "postId") Long id) {
        return postService.get(id);
    }

    @GetMapping("/posts")
    public List<PostResponse> getList(Pageable pageable) {
        return postService.getList(pageable);
    }
}
