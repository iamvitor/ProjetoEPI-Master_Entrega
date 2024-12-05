
package com.senai.projetologin.repositorys;


import com.senai.projetologin.models.EmprestimoModels;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmprestimoRepository extends JpaRepository<EmprestimoModels,Long> {


    public Optional<EmprestimoModels> findById(Long id);

}