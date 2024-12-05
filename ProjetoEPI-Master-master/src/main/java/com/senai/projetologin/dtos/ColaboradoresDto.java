
package com.senai.projetologin.dtos;


import java.time.LocalDate;

import lombok.Data;

@Data
public class ColaboradoresDto {
  
    private Long id;
    
    private String nome;
        
    private String email;
   
    private String funcao;
    
    private LocalDate nascimento;
    

    
}
