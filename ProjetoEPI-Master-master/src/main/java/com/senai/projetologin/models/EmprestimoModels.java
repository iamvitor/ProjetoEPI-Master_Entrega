package com.senai.projetologin.models;

import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDate;

@Entity
@Table(name = "EMPRESTIMO")
@Data

public class EmprestimoModels {

    @Id
    @Column(name = "id_emprestimo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "colaborador_id", nullable = false)
    private ColaboradoresModels colaborador; // Assumindo

    @ManyToOne
    @JoinColumn(name = "equipamento_id", nullable = false)
    private EquipamentoModels equipamento;

    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    @Column(length = 1000)
    private String feedback;


}
