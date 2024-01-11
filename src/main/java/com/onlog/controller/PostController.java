package com.onlog.controller;

import com.onlog.request.PostCreate;
import com.onlog.request.PostEdit;
import com.onlog.request.PostSearch;
import com.onlog.response.PostResponse;
import com.onlog.serivce.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/")
    public String get() {
        return "Hello world";
    }

    @PostMapping("/posts")
    public void post(@RequestBody @Valid PostCreate request) throws Exception {
        request.validate();
        postService.write(request);
    }

    @GetMapping("/posts/{postId}")
    public PostResponse get(@PathVariable(name = "postId") Long id) {
        return postService.get(id);
    }

    @GetMapping("/posts")
    public List<PostResponse> getList(@ModelAttribute PostSearch postSearch) {
        return postService.getList(postSearch);
    }

    @PatchMapping("/posts/{postId}")
    public void edit(@PathVariable(name = "postId") Long id, @RequestBody @Valid PostEdit request) {
        postService.edit(id, request);
    }

    @DeleteMapping("/posts/{postId}")
    public void delete(@PathVariable(name = "postId") Long id) {
        postService.delete(id);
    }
}
