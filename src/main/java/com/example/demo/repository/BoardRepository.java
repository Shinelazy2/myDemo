package com.example.demo.repository;

import com.example.demo.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByTitle(String title);
    List<Board> findByTitleOrContent(String title, String Content);

    // 문자열이 포함된 문자열을 찾는다~
    Page<Board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);
}
