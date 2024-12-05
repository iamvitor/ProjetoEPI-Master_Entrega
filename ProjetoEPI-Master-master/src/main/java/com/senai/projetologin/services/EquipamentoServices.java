package com.senai.projetologin.services;


import com.senai.projetologin.dtos.CategoriaDto;
import com.senai.projetologin.dtos.EquipamentoDto;
import com.senai.projetologin.models.CategoriaModels;
import com.senai.projetologin.models.EquipamentoModels;
import com.senai.projetologin.repositorys.CategoriaRepository;
import com.senai.projetologin.repositorys.EquipamentoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipamentoServices {

    @Autowired
    private EquipamentoRepository repositorioEquipamento;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public boolean cadastrarEquipamento(EquipamentoDto equipamentoDto) {


        if (equipamentoDto.getCategoriaId() == null) {
            return false;
        }


        CategoriaModels categoria = categoriaRepository.findById(equipamentoDto.getCategoriaId()).orElse(null);

        if (categoria == null) {
            return false;
        }


        EquipamentoModels equipamento = new EquipamentoModels();
        equipamento.setNome(equipamentoDto.getNome());
        equipamento.setCategoria(categoria);

        // Salva o equipamento
        repositorioEquipamento.save(equipamento);
        return true;
    }







    public List<EquipamentoDto> obterListaEquipamento() {

        List<EquipamentoModels> listaEquipamentoModels = repositorioEquipamento.findAll();

        List<EquipamentoDto> listaEquipamentos = new ArrayList<>();

        for (EquipamentoModels equipamentos : listaEquipamentoModels) {

            EquipamentoDto equipamentoDto = new EquipamentoDto();

            equipamentoDto.setId(equipamentos.getId());
            equipamentoDto.setNome(equipamentos.getNome());
            equipamentoDto.setCategoria(equipamentos.getCategoria().getNome());




            listaEquipamentos.add(equipamentoDto);
        }

        return listaEquipamentos;

    }

    public boolean excluirEquipamento(Long id) {

        Optional<EquipamentoModels> optionalEquipamento = repositorioEquipamento.findById(id);

        if (optionalEquipamento.isPresent()) {
            repositorioEquipamento.deleteById(id);
            return true;
        }

        return false;
    }
    public EquipamentoDto obterEquipamento(Long id) {

        Optional<EquipamentoModels> optionalEquipamento = repositorioEquipamento.findById(id);

        EquipamentoDto equipamentoDto = new EquipamentoDto();

        if (!optionalEquipamento.isPresent()) {
            equipamentoDto.setId(0L);
            return equipamentoDto;
        }
        equipamentoDto.setId(optionalEquipamento.get().getId());
        equipamentoDto.setNome(optionalEquipamento.get().getNome());

        return equipamentoDto;
    }

    public boolean atualizarEquipamento(EquipamentoDto equipamentoDto, Long id) {

        Optional<EquipamentoModels> equipamentoOptional = repositorioEquipamento.findById(id);

        if (!equipamentoOptional.isPresent()) {
            System.out.println("Equipamento não encontrado com o ID: " + id);
            return false;
        }

        EquipamentoModels equipamentoAtt = equipamentoOptional.get();
        equipamentoAtt.setNome(equipamentoDto.getNome());


        Optional<CategoriaModels> categoriaOptional = categoriaRepository.findById(equipamentoDto.getCategoriaId());
        if (!categoriaOptional.isPresent()) {
            System.out.println("Categoria não encontrada com o ID: " + equipamentoDto.getCategoriaId());
            return false;
        }

        CategoriaModels novaCategoria = categoriaOptional.get();
        equipamentoAtt.setCategoria(novaCategoria);

        repositorioEquipamento.save(equipamentoAtt);

        return true;
    }


}
