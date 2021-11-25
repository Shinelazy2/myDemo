package com.example.demo.controller;

import com.example.demo.model.Board;
import com.example.demo.repository.BoardRepository;
import com.example.demo.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

// jpa = orm , class만 이용해서 db에 접근할 수 있는 기술
// hibernate 사용한다~
@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired // 디펜던시 인젝션? 인스턴스가 들어온다?
    private BoardRepository boardRepository;

    @Autowired
    private BoardValidator boardValidator;



    @GetMapping("/list")
    public String list(Model model, @PageableDefault(size = 2) Pageable pageable){
        Page<Board> boards = boardRepository.findAll(pageable); // 데이터를 다 가져옴
        int startPage = Math.max(1, boards.getPageable().getPageNumber() - 4); // 시작 페이지 최소값이 1
        int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 4);
//        int startPage = 1;
//        int endPage = boards.getTotalPages();
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boards", boards);
        return "board/list";
    }

    // http://localhost:8080/board/list?page=0&size=1
    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) Long id){ // 필수인지 아닌지 확인
        if(id == null){
            model.addAttribute("board", new Board());
        }else{
            Board board = boardRepository.findById(id).orElse(null);// 키값을 기반으로 찾음, 아무것도 없으면 null 넣음
            model.addAttribute("board", board);
        }
        return "board/form";
    }

    @PostMapping("/form")
    public String greetingSubmit(@Valid Board board, BindingResult bindingResult) {
        boardValidator.validate(board,bindingResult);
        if (bindingResult.hasErrors()) {
            return "board/form";
        }
        boardRepository.save(board);
        return "redirect:/board/list"; // 이거 잘못하면 에러남 board/list
    }

}
