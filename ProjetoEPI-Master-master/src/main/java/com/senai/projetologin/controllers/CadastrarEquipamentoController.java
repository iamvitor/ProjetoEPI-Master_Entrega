package com.senai.projetologin.controllers;

import com.senai.projetologin.dtos.*;
import com.senai.projetologin.services.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping()
public class CadastrarEquipamentoController {

    @Autowired
    EquipamentoServices equipamentoServices;

    @Autowired
    CategoriaServices categoriaServices;

    @GetMapping("/equipamento")
    public String EquipamentosView(Model model, HttpServletRequest request) {
         HttpSession session = request.getSession(false); // Obtém a sessão sem criar uma nova
         
         if (session == null || session.getAttribute("loginDto") == null) {
            return "redirect:/login";
        }                

        EquipamentoDto equipamentoDto = new EquipamentoDto();
        model.addAttribute("equipamentoDto", equipamentoDto);
        model.addAttribute("equipamentos", equipamentoServices.obterListaEquipamento());

        return "listarequipamento";
    }

    @GetMapping("/cadastrarequipamento")
    public String exibirEquipamento(Model model,  HttpServletRequest request) {
        HttpSession session = request.getSession(false);
         
        if (session == null || session.getAttribute("loginDto") == null) {
           return "redirect:/login";
       }                

        EquipamentoDto equipamentoDto = new EquipamentoDto();

        model.addAttribute("equipamentoDto", equipamentoDto);
        model.addAttribute("categorias", categoriaServices.obterListaCategoria());

        return "cadastrarequipamento";
    }

    @PostMapping("/cadastrarequipamento")
    public String cadastrarEquipamento(@ModelAttribute EquipamentoDto equipamentoDto) {

        System.out.println("nao cadastrou.teste111");

        boolean sucesso = equipamentoServices.cadastrarEquipamento(equipamentoDto);
        if (sucesso) {

            return "sucesso.html";
        }

        System.out.println("nao cadastrou.teste22222");
        return "redirect:/equipamento?error";
    }

    @DeleteMapping("/equipamento/{id}")
    public ResponseEntity<String> excluirEquipamento(@PathVariable Long id) {

        boolean excluir = equipamentoServices.excluirEquipamento(id);

        if (excluir) {
            return ResponseEntity.ok("Equipamento excluído com sucesso.");

        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Não foi possivel excluir Equipamento.");

    }

}
