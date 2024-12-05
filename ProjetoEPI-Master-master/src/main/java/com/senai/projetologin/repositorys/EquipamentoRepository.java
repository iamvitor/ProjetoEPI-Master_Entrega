
package com.senai.projetologin.repositorys;


import com.senai.projetologin.models.EquipamentoModels;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipamentoRepository extends JpaRepository<EquipamentoModels,Long> {
    

    public Optional<EquipamentoModels> findById(Long id);
    
}