package com.koreait.restapi.service;

import com.koreait.restapi.dto.PostDTO;
import com.koreait.restapi.jwt.JwtUtil;
import com.koreait.restapi.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostMapper postMapper;
    private final JwtUtil jwtUtil;

    // 게시글 작성
    @Override
    public void insertPost(PostDTO post) {
        post.setCreatedAt(LocalDateTime.now());
        postMapper.insert(post);
    }

    // 게시글 목록 조회 (최신순 + 페이징)
    @Override
    public List<PostDTO> getPosts(int page, int size) {
        int offset = page * size;
        return postMapper.findAll(offset, size);
    }

    // 게시글 상세 조회
    @Override
    public PostDTO getPostById(int id) {
        return postMapper.findById(id);
    }

    // 게시글 수정 (작성자만 가능)
    @Override
    public boolean updatePost(int postId, int userId, PostDTO post) {
        PostDTO original = postMapper.findById(postId);
        if (original != null && original.getWriterId() == userId) {
            post.setId(postId);
            post.setUpdatedAt(LocalDateTime.now());
            postMapper.update(post);
            return true;
        }
        return false;
    }

    // 게시글 삭제 (작성자만 가능)
    @Override
    public boolean deletePost(int postId, int userId) {
        PostDTO original = postMapper.findById(postId);
        if (original != null && original.getWriterId() == userId) {
            postMapper.delete(postId);
            return true;
        }
        return false;
    }
}
