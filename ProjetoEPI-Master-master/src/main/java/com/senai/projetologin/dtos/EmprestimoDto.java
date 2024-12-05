package com.senai.projetologin.dtos;

import com.senai.projetologin.models.EquipamentoModels;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data


public class EmprestimoDto {


    private Long id;


    private String nome;

    private Long equipamento;

    private Long colaborador;

    private LocalDate dataEmprestimo = LocalDate.now();

    private LocalDate dataDevolucao;

    private Long colaboradorId;
    
    private String colaboradorNome;

    private Long equipamentoId;
    
    private String equipamentoNome;

    private String feedback;



}
