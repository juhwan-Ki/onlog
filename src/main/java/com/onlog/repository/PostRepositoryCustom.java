package com.onlog.repository;

import com.onlog.domain.Post;
import com.onlog.request.PostSearch;

import java.util.List;

public interface PostRepositoryCustom {

    List<Post> getList(PostSearch postSearch);
}
