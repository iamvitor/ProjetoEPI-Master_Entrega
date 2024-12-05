package com.senai.projetologin.controllers;



import com.senai.projetologin.dtos.*;
import com.senai.projetologin.services.*;
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
@RequestMapping("/atualizarequipamento/{id}")

public class AtualizarEquipamentoController {

    @Autowired
    EquipamentoServices equipamentoService;
    @Autowired
    CategoriaServices categoriaServices;

    @GetMapping
    public String exibirAtualizarEquipamento(Model model, @PathVariable Long id, HttpServletRequest request) {
        HttpSession session = request.getSession(false); 

        
        if (session == null || session.getAttribute("loginDto") == null) {
            return "redirect:/login"; 
        }

        EquipamentoDto equipamentoDto = equipamentoService.obterEquipamento(id);

       
        model.addAttribute("categorias", categoriaServices.obterListaCategoria());
        model.addAttribute("equipamentoDto", equipamentoDto);

        return "atualizarequipamento";
    }

    @PostMapping
    public String atualizarEquipamento(@ModelAttribute("equipamentos")EquipamentoDto equipamentoDto, @PathVariable Long id) {
        
        boolean sucesso = equipamentoService.atualizarEquipamento(equipamentoDto, id);

        if (sucesso) {
            return "sucesso.html";
        }

        return "redirect:/atualizarequipamento?erro";
    }

}