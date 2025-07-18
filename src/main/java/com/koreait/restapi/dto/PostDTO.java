package com.koreait.restapi.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDTO {
    private int id;              // 게시글 고유 ID
    private String title;        // 제목
    private String content;      // 내용
    private int writerId;        // 작성자 (Member의 id)
    private String writerName;   // 작성자 이름 (옵션, 필요 시 포함)
    private LocalDateTime createdAt;  // 작성일시
    private LocalDateTime updatedAt;  // 수정일시 (옵션)
}
