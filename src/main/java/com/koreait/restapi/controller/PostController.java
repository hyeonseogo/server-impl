package com.koreait.restapi.controller;

import com.koreait.restapi.dto.PostDTO;
import com.koreait.restapi.jwt.JwtUtil;
import com.koreait.restapi.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final JwtUtil jwtUtil;

    // 게시글 작성 (로그인한 사용자만)
    @PostMapping
    public ResponseEntity<?> create(@RequestBody PostDTO post, HttpServletRequest request) {
        int userId = jwtUtil.getUserIdFromRequest(request);
        post.setWriterId(userId);
        postService.insertPost(post);
        return ResponseEntity.ok("게시글 작성 성공");
    }

    // 게시글 목록 조회 (최신순 + 페이징)
    @GetMapping
    public ResponseEntity<?> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        List<PostDTO> posts = postService.getPosts(page, size);
        int totalCount = postService.getTotalCount();

        Map<String, Object> result = new HashMap<>();
        result.put("posts", posts);
        result.put("totalCount", totalCount);

        return ResponseEntity.ok(result);
    }


    // 게시글 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<?> detail(@PathVariable int id) {
        PostDTO post = postService.getPostById(id);
        if (post != null) {
            return ResponseEntity.ok(post);
        } else {
            return ResponseEntity.status(404).body("게시글을 찾을 수 없습니다.");
        }
    }

    // 게시글 수정 (작성자만 가능)
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id,
                                    @RequestBody PostDTO post,
                                    HttpServletRequest request) {
        int userId = jwtUtil.getUserIdFromRequest(request);
        boolean isUpdated = postService.updatePost(id, userId, post);
        if (isUpdated) {
            return ResponseEntity.ok("게시글 수정 성공");
        } else {
            return ResponseEntity.status(403).body("수정 권한이 없습니다.");
        }
    }

    // 게시글 삭제 (작성자만 가능)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id,
                                    HttpServletRequest request) {
        int userId = jwtUtil.getUserIdFromRequest(request);
        boolean isDeleted = postService.deletePost(id, userId);
        if (isDeleted) {
            return ResponseEntity.ok("게시글 삭제 성공");
        } else {
            return ResponseEntity.status(403).body("삭제 권한이 없습니다.");
        }
    }
}
