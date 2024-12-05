
package com.senai.projetologin.dtos;


import java.time.LocalDate;

import lombok.Data;

@Data
public class CadastrarDto {
   
    private String email;
    
    private String senha;
    
    private String nome;
      
    private String funcao;

    private LocalDate nascimento;
    
    
}
