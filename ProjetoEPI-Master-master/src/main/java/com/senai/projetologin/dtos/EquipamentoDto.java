
package com.senai.projetologin.dtos;

import lombok.Data;

@Data
public class EquipamentoDto {
    
    private Long id; 
    private String nome; 
    private Long categoriaId;
    private String categoria;
    
}