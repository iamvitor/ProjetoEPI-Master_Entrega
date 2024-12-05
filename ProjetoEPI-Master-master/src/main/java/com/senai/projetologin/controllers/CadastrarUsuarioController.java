package com.senai.projetologin.controllers;

import com.senai.projetologin.dtos.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class CadastrarUsuarioController {
   
    
    @GetMapping("/cadastrarusuario")
    public String exibirUsuarios(Model model, HttpServletRequest request){
        
        HttpSession session = request.getSession(false);
         
         if (session == null || session.getAttribute("loginDto") == null) {
            return "redirect:/login";
         }
        
        CadastrarDto cadastrarDto = new CadastrarDto();
        
        model.addAttribute("usuarioDto", cadastrarDto);
        
        return "cadastrarusuario";
    }
    
    
}
