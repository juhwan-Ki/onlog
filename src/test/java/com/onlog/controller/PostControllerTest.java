package com.onlog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlog.domain.Post;
import com.onlog.repository.PostRepository;
import com.onlog.request.PostCreate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@WebMvcTest ->
@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void clean() {
        postRepository.deleteAll();
    }

    @DisplayName("/post 요청 시 Hello world를 출력한다.")
    @Test
    public void test() throws Exception {
        // given
        PostCreate request = new PostCreate("제목입니다.", "내용입니다.");
        // JSON으로 변경해줌
        String json = objectMapper.writeValueAsString(request);

       // expected
        mockMvc.perform(post("/posts")
                        .contentType(APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @DisplayName("/post 요청 시 title 값이 필수.")
    @Test
    public void test2() throws Exception {
        // given
        PostCreate request = PostCreate.builder()
                .content("내용입니다.")
                .build();
        // JSON으로 변경해줌
        String json = objectMapper.writeValueAsString(request);

        // expected
        mockMvc.perform(post("/posts")
                        .contentType(APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
                .andDo(print());
    }

    @DisplayName("/post 요청 시 DB에 값이 저장된다")
    @Test
    public void test3() throws Exception {
        // given
        PostCreate request = PostCreate.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();
        // JSON으로 변경해줌
        String json = objectMapper.writeValueAsString(request);
        // when
        mockMvc.perform(post("/posts")
                        .contentType(APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andDo(print());
        // then
        assertEquals(1L, postRepository.count());

        Post post = postRepository.findAll().get(0);
        assertEquals("제목입니다.", post.getTitle());
        assertEquals("내용입니다.", post.getContent());
    }


   @DisplayName("글 1개 조회")
   @Test
   public void test4() throws Exception {
       Post requsetPost = Post.builder()
               .title("123451234512345")
               .content("bar")
               .build();
       postRepository.save(requsetPost);

      // expected (when + then)
       mockMvc.perform(get("/posts/{postId}", requsetPost.getId())
                       .contentType(APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").value(requsetPost.getId()))
               .andExpect(jsonPath("$.title").value("1234512345"))
               .andExpect(jsonPath("$.content").value("bar"))
               .andDo(print());
   }
}