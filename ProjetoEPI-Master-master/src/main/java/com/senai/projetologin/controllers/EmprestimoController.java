package com.senai.projetologin.controllers;

import com.senai.projetologin.dtos.*;
import com.senai.projetologin.services.*;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoServices emprestimoService;
    @Autowired
    private ColaboradoresServices colaboradorService;
    @Autowired
    private EquipamentoServices equipamentoService;


    @GetMapping
    public String listarEmprestimos(Model model, HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loginDto") == null) {
            return "redirect:/login";
        }

        model.addAttribute("emprestimos", emprestimoService.listarEmprestimosNaoDevolvidos());
        return "listaemprestimos";

    }


    @GetMapping("/cadastraremprestimo")
    public String mostrarFormularioCadastro(Model model) {

        model.addAttribute("emprestimoDto", new EmprestimoDto());

        model.addAttribute("colaboradores", colaboradorService.obterListaColaboradores());
        model.addAttribute("equipamentos", equipamentoService.obterListaEquipamento());

        return "cadastraremprestimo";
    }

    @PostMapping("/cadastraremprestimo")
    public String salvarEmprestimo(@ModelAttribute EmprestimoDto emprestimoDto) {
        EmprestimoDto emprestimoSalvo = emprestimoService.registrarEmprestimo(emprestimoDto);
        if (emprestimoSalvo != null) {
            return "sucesso.html";
        } else {
            return "redirect:/emprestimos/cadastraremprestimo?erro";
        }
    }

    @GetMapping("/visualizaremprestimo/{id}")
    public String visualizarEmprestimo(@PathVariable Long id, Model model) {
        Optional<EmprestimoDto> emprestimo = emprestimoService.buscarEmprestimoPorId(id);
        emprestimo.ifPresent(value -> model.addAttribute("emprestimo", value));
        return "visualizaremprestimo";
    }

    @GetMapping("/excluiremprestimo/{id}")
    public String excluirEmprestimo(@PathVariable Long id) {
        emprestimoService.excluirEmprestimo(id);
        return "redirect:/emprestimos";
    }

    @GetMapping("/devolucao/{id}")
    public String abrirPaginaDevolucao(@PathVariable Long id, Model model) {

        Optional<EmprestimoDto> emprestimo = emprestimoService.buscarEmprestimoPorId(id);
        emprestimo.ifPresent(value -> model.addAttribute("emprestimo", value));

        return "devolucaoemprestimo";

    }

    @PostMapping("/devolucao/{id}")
    public String registrarDevolucao(@PathVariable Long id, @RequestParam LocalDate dataDevolucao, String feedback) {
        EmprestimoDto emprestimoDto = emprestimoService.registrarDevolucao(id, dataDevolucao, feedback);
        if (emprestimoDto != null) {
            return "sucesso.html";
        }
        return "redirect:/emprestimos?erro";
    }

    @GetMapping("/devolvidos")
    public String listarEmprestimosDevolvidos(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loginDto") == null) {
            return "redirect:/login";
        }

        model.addAttribute("emprestimos", emprestimoService.listarEmprestimosDevolvidos());
        return "listaemprestimosdevolvidos";
    }

}
