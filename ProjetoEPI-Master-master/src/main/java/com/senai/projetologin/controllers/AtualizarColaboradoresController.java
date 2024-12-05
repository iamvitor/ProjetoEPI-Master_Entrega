
package com.senai.projetologin.controllers;

import com.senai.projetologin.dtos.*;
import com.senai.projetologin.services.ColaboradoresServices;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/atualizarcolaboradores/{id}")
public class AtualizarColaboradoresController {
    
    @Autowired
    ColaboradoresServices colaboradoresService;
    
    @GetMapping
    public String exibirAtualizarColaboradores(Model model, @PathVariable Long id, HttpServletRequest request){
        HttpSession session = request.getSession(false);
         
         if (session == null || session.getAttribute("loginDto") == null) {
            return "redirect:/login";
        }                
        ColaboradoresDto colaboradoresDto = colaboradoresService.obterColaboradores(id);
                
        model.addAttribute("colaboradoresDto", colaboradoresDto);
        
      
            return "atualizarcolaboradores";
        }
        
    @PostMapping
    public String atualizarColaboradores(@ModelAttribute("colaboradores") ColaboradoresDto colaboradoresDto, @PathVariable Long id) {
    boolean sucesso = colaboradoresService.atualizarColaboradores(colaboradoresDto, id);

    if (sucesso) {
        return "sucesso.html";
    }

    return "redirect:/atualizarcolaboradores?erro";
}
    
        
    
    
}
