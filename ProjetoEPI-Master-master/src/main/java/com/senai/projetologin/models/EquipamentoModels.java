package com.senai.projetologin.models;

import lombok.Data;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;


@Entity
@Table(name = "EQUIPAMENTO")
@Data

public class EquipamentoModels {

    @Id
    @Column(name = "id_equipamento")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_equipamento", nullable = false, length = 90)
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    private CategoriaModels categoria; 

}
