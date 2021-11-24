package com.example.demo.controller;

import com.example.demo.model.Board;
import com.example.demo.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// jpa = orm , class만 이용해서 db에 접근할 수 있는 기술
// hibernate 사용한다~
@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired // 디펜던시 인젝션? 인스턴스가 들어온다?
    private BoardRepository boardRepository;
    @GetMapping("/list")
    public String list(Model model){
        List<Board> boards = boardRepository.findAll(); // 데이터를 다 가져옴
        model.addAttribute("boards",boards);
        return "board/list";
    }

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
    public String greetingSubmit(@ModelAttribute Board board) {
        boardRepository.save(board);
        return "redirect:/board/list"; // 이거 잘못하면 에러남 board/list
    }

}
