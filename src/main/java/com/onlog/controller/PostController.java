package com.onlog.controller;

import com.onlog.request.PostCreate;
import com.onlog.serivce.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/posts")
    public String get() {
        return "Hello world";
    }

    @PostMapping("/posts")
    public Map<String, String> post(@RequestBody @Valid PostCreate request) throws Exception {
        postService.write(request);
        return Map.of(); // map을 초기화 하는 메소드
    }

}
