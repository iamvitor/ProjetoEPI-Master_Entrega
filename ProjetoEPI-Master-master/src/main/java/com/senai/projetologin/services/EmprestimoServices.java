package com.senai.projetologin.services;

import com.senai.projetologin.dtos.EmprestimoDto;
import com.senai.projetologin.models.EmprestimoModels;
import com.senai.projetologin.models.ColaboradoresModels;
import com.senai.projetologin.models.EquipamentoModels;
import com.senai.projetologin.repositorys.EmprestimoRepository;
import com.senai.projetologin.repositorys.ColaboradoresRepository;
import com.senai.projetologin.repositorys.EquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmprestimoServices {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private ColaboradoresRepository colaboradorRepository;

    @Autowired
    private EquipamentoRepository equipamentoRepository;





    private EmprestimoDto converterParaDto(EmprestimoModels emprestimoModels) {
        EmprestimoDto emprestimoDto = new EmprestimoDto();
        emprestimoDto.setId(emprestimoModels.getId());

      
        ColaboradoresModels colaborador = emprestimoModels.getColaborador();
        if (colaborador != null) {
            emprestimoDto.setColaboradorId(colaborador.getId());
            emprestimoDto.setColaboradorNome(colaborador.getNome());
            
        }

        EquipamentoModels equipamento = emprestimoModels.getEquipamento();
        if (equipamento != null) {
            emprestimoDto.setEquipamentoId(equipamento.getId());
            emprestimoDto.setEquipamentoNome(equipamento.getNome());
        }

        emprestimoDto.setDataEmprestimo(emprestimoModels.getDataEmprestimo());
        emprestimoDto.setDataDevolucao(emprestimoModels.getDataDevolucao());
        emprestimoDto.setFeedback(emprestimoModels.getFeedback());

        return emprestimoDto;
    }


    public List<EmprestimoDto> listarEmprestimos() {
        List<EmprestimoModels> emprestimos = emprestimoRepository.findAll();
        return emprestimos.stream()
                .map(this::converterParaDto)
                .collect(Collectors.toList());
    }


    public EmprestimoDto registrarEmprestimo(EmprestimoDto emprestimoDto) {

        Optional<ColaboradoresModels> colaboradorOptional = colaboradorRepository.findById(emprestimoDto.getColaboradorId());
        Optional<EquipamentoModels> equipamentoOptional = equipamentoRepository.findById(emprestimoDto.getEquipamentoId());

        if (colaboradorOptional.isPresent() && equipamentoOptional.isPresent()) {
            EmprestimoModels emprestimoModels = new EmprestimoModels();
            emprestimoModels.setColaborador(colaboradorOptional.get());
            emprestimoModels.setEquipamento(equipamentoOptional.get());
            emprestimoModels.setDataEmprestimo(emprestimoDto.getDataEmprestimo());
            emprestimoModels.setDataDevolucao(emprestimoDto.getDataDevolucao());


            emprestimoModels = emprestimoRepository.save(emprestimoModels);
            return converterParaDto(emprestimoModels);
        } else {

            return null;
        }
    }

    // Método para buscar um empréstimo por ID
    public Optional<EmprestimoDto> buscarEmprestimoPorId(Long id) {
        Optional<EmprestimoModels> emprestimo = emprestimoRepository.findById(id);
        return emprestimo.map(this::converterParaDto);
    }

    // Método para excluir um empréstimo
    public void excluirEmprestimo(Long id) {
        emprestimoRepository.deleteById(id);
    }

    // Método para registrar a devolução de um empréstimo
    public EmprestimoDto registrarDevolucao(Long id, LocalDate dataDevolucao, String feedback) {
        Optional<EmprestimoModels> emprestimoOpt = emprestimoRepository.findById(id);
        if (emprestimoOpt.isPresent()) {
           
            EmprestimoModels emprestimo = emprestimoOpt.get();
            emprestimo.setDataDevolucao(dataDevolucao);
            emprestimo.setFeedback(feedback);
            emprestimo = emprestimoRepository.save(emprestimo);
            return converterParaDto(emprestimo);

        }
        return null;
    }
    // Método para listar apenas empréstimos devolvidos
    public List<EmprestimoDto> listarEmprestimosDevolvidos() {
    List<EmprestimoModels> emprestimos = emprestimoRepository.findAll();
    return emprestimos.stream()
            .filter(emprestimo -> emprestimo.getDataDevolucao() != null) 
            .map(this::converterParaDto) 
            .collect(Collectors.toList());
}

// Método para listar apenas empréstimos não devolvidos
public List<EmprestimoDto> listarEmprestimosNaoDevolvidos() {
    List<EmprestimoModels> emprestimos = emprestimoRepository.findAll();
    return emprestimos.stream()
            .filter(emprestimo -> emprestimo.getDataDevolucao() == null) // Filtra apenas os não devolvidos
            .map(this::converterParaDto) // Converte para DTO
            .collect(Collectors.toList());
    }



}
