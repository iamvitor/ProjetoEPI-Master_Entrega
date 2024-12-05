package com.senai.projetologin.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public String home(Model model, HttpServletRequest request) {



        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loginDto") == null) {
            return "redirect:/login";
        }

        return "home";

    }

    @PostMapping
    public String voltarParaLogin() {

        return "redirect:/login";
    }

}
