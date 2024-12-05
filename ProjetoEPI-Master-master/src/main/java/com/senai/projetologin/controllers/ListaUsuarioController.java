package com.senai.projetologin.controllers;

import com.senai.projetologin.services.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class ListaUsuarioController {
    
    @Autowired
    UsuarioServices usuarioServices;
    
    @GetMapping("/listausuarios")
    public String exibirUsuarios(Model model, HttpServletRequest request){
      HttpSession session = request.getSession(false); // Obtém a sessão sem criar uma nova
         
         if (session == null || session.getAttribute("loginDto") == null) {
            return "redirect:/login";
        }                
        
        model.addAttribute("usuarios",usuarioServices.obterListaUsuarios());
        
        return "listausuarios";
    }
    
}
