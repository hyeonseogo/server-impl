package com.koreait.restapi.mapper;

import com.koreait.restapi.dto.PostDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {

    // 게시글 작성
    void insert(PostDTO post);

    // 게시글 목록 조회 (페이징 처리 + 최신순)
    List<PostDTO> findAll(@Param("offset") int offset, @Param("size") int size);

    // 게시글 상세 조회
    PostDTO findById(@Param("id") int id);

    // 게시글 수정
    void update(PostDTO post);

    // 게시글 삭제
    void delete(@Param("id") int id);
}
