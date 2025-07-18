package com.koreait.restapi.service;

import com.koreait.restapi.dto.PostDTO;

import java.util.List;

public interface PostService {

    // 게시글 작성
    void insertPost(PostDTO post);

    // 게시글 목록 조회 (최신순 + 페이징)
    List<PostDTO> getPosts(int page, int size);

    // 게시글 상세 조회
    PostDTO getPostById(int id);

    // 게시글 수정 (작성자만 가능)
    boolean updatePost(int postId, int userId, PostDTO post);

    // 게시글 삭제 (작성자만 가능)
    boolean deletePost(int postId, int userId);

    int getTotalCount();
}
