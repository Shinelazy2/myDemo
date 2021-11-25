package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountCotroller {

    @Autowired
    private UserService userService;
    @GetMapping("/login")
    public String login(){
        return "account/login";
    }

    @PostMapping("/register")
    public String register(User user){
        userService.save_(user);
        return "redirect:/";
    }

    @GetMapping("/register")
    public String register(){
        return "account/register";
    }
}
