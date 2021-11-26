package com.example.demo.controller;
import java.util.List;

import com.example.demo.model.Board;

import com.example.demo.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

// DB값을 API로 사용
@RestController
@RequestMapping("/api")
public class BoardApiController {


    @Autowired
    private final BoardRepository boardRepository;

    public BoardApiController(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }


    // Aggregate root
        // tag::get-aggregate-root[]
        // http://localhost:8080/api/boards?title=하하
        @GetMapping("/boards")
        List<Board> all(@RequestParam(required = false, defaultValue = "") String title,@RequestParam(required = false, defaultValue = "") String content){
            if (StringUtils.isEmpty(title) && StringUtils.isEmpty(content)){
                return boardRepository.findAll();
            }else{
                return boardRepository.findByTitleOrContent(title,content);
            }

        }
        // end::get-aggregate-root[]

        @PostMapping("/boards")
        Board newBoard(@RequestBody Board newBoard) {
            return boardRepository.save(newBoard);
        }

        // Single item
        //http://localhost:8080/api/boards/1
        // {"id":1,"title":"제목","content":"내용2"}

        @GetMapping("/boards/{id}")
        Board one(@PathVariable Long id) {
            return boardRepository.findById(id).orElse(null);
        }

        @PutMapping("/boards/{id}")
        Board replaceBoard(@RequestBody Board newBoard, @PathVariable Long id) {

            return boardRepository.findById(id)
                    .map(Board -> {
                        Board.setTitle(newBoard.getTitle());
                        Board.setContent(newBoard.getContent());
                        return boardRepository.save(Board);
                    })
                    .orElseGet(() -> {
                        newBoard.setId(id);
                        return boardRepository.save(newBoard);
                    });
        }

        @DeleteMapping("/boards/{id}")
        void deleteBoard(@PathVariable Long id) {
            boardRepository.deleteById(id);
        }
}

