package com.example.timefut.service;

import com.example.timefut.model.Exception.ResourceNotFoundException;
import com.example.timefut.model.Jogador;
import com.example.timefut.shared.JogadorDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface JogadorService {

    List<JogadorDTO> obtertodos();

    JogadorDTO adicionar(JogadorDTO jogadorDTO);

    void deletar(Long idCliente) throws ResourceNotFoundException;

    Jogador atualizar(Long idCliente, Jogador jogador);

}
